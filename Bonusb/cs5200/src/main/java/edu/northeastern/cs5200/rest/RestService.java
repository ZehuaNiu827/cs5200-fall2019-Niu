package edu.northeastern.cs5200.rest;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
public class RestService {

    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Value("${spring.datasource.url}")
    private String datasource;

    @Value("${spring.datasource.username}")
    private String username;

    @Value("${spring.datasource.password}")
    private String password;

    private Connection connection;
    private Gson gson = new Gson();

    private PreparedStatement getPrepareStatement(String sql) throws SQLException {
        if (connection == null)
            connection = DriverManager.getConnection(datasource, username, password);
        return connection.prepareStatement(sql);
    }

    private void createTable(Set<String> columns, String tableName) throws SQLException {
        createTable(columns, tableName, "varchar(255) null");
    }

    private void createTable(Set<String> columns, String tableName, String dataType) throws SQLException {
        StringBuilder createString = new StringBuilder("create table " + '`' + tableName + '`' + " (");
        boolean hasId = false;
        for (String s : columns) {
            if (s.equalsIgnoreCase("id") && !hasId) {
                createString.append('`' + s + '`' + " int auto_increment, ");
                hasId = true;
            }
            else
                createString.append('`' + s + "` " + dataType + ", ");
        }
        if (!hasId)
            createString.append("`id`" + " int auto_increment, ");
        createString.append("constraint " + tableName + "_pk primary key (id));");
        getPrepareStatement(createString.toString()).execute();
    }

    private void alterTable(Set<String> _columns, String tableName) throws SQLException {
        Set<String> columns = new LinkedHashSet<String>();
        columns.addAll(_columns);
        ResultSet rs = getPrepareStatement("select * from " + tableName).executeQuery();
        int columnCount = rs.getMetaData().getColumnCount();
        Set<String> originalColumns = new LinkedHashSet<>();
        for (int i = 1; i <= columnCount; i++) {
            originalColumns.add(rs.getMetaData().getColumnName(i));
        }
        columns.removeAll(originalColumns);
        for (String s : columns)
            getPrepareStatement("alter table " + '`' + tableName + '`' + " add " + '`' + s + '`' + " varchar(255) null;").execute();
    }

    private JsonArray queryImpl(String sqlString) throws SQLException {
        ResultSet rs;
        try {
            rs = getPrepareStatement(sqlString).executeQuery();
        } catch (MySQLSyntaxErrorException ex) {
            if (ex.getMessage().contains("doesn't exist")) {
                return null;
            }
            else throw ex;
        }
        int columnCount = rs.getMetaData().getColumnCount();
        ResultSetMetaData metaData = rs.getMetaData();
        JsonArray jsonArray = new JsonArray();
        while (rs.next()) {
            JsonObject jsonObject = new JsonObject();
            for (int i = 1; i <= columnCount; i++)
                jsonObject.addProperty(metaData.getColumnName(i), rs.getString(i));
            jsonArray.add(jsonObject);
        }
        return jsonArray;
    }

    private String getRelationTableName(String tableName1, String tableName2) {
        ArrayList<String> temp = new ArrayList<>();
        temp.add(tableName1);
        temp.add(tableName2);
        temp.sort(null);
        return temp.get(0) + "_" + temp.get(1);
    }

    private boolean createRelationTable(String tableName1, String tableName2) throws SQLException {
        HashSet<String> tableNames = new HashSet<>();
        tableNames.add(tableName1 + ".id");
        tableNames.add(tableName2 + ".id");
        String tableName = getRelationTableName(tableName1, tableName2);
        createTable(tableNames, tableName, "int not null");
        String addFKString1 = "alter table `" + tableName +
                "` add constraint `" + tableName1 +
                "_fk` foreign key (`" + tableName1 + ".id`) references `" + tableName1 +
                "` (`id`) on update cascade on delete cascade;";
        String addFKString2 = "alter table `" + tableName +
                "` add constraint `" + tableName2 +
                "_fk` foreign key (`" + tableName2 + ".id`) references `" + tableName2 +
                "` (`id`) on update cascade on delete cascade;";
        try {
            getPrepareStatement(addFKString1).execute();
            getPrepareStatement(addFKString2).execute();
        } catch (SQLException ex) {
            if (ex.getMessage().contains("Cannot"))
                return false;
            else throw ex;
        }
        return true;
    }

    private String parsePredicates(String originalSQL, String predicates) {
        String[] splitedQueryString = predicates.split("&");
        Pattern p = Pattern.compile("((%[0-9A-Z][0-9A-Z])|[^a-zA-Z0-9_.])*");
        StringBuilder sqlQuery = new StringBuilder(originalSQL);
        for (String s : splitedQueryString) {
            Matcher m = p.matcher(s);
            String operator = null;
            while (m.find()) {
                operator = m.group();
                if (!operator.equals(""))
                    break;
            }
            StringBuilder operatorSb = new StringBuilder(operator);
            int pos;
            while ((pos = operatorSb.indexOf("%")) != -1)
                operatorSb.replace(pos, pos + 3, String.valueOf((char)(Integer.parseInt(operatorSb.substring(pos + 1, pos + 3), 16))));
            sqlQuery.append('`' + s.substring(0, s.indexOf(operator)) + "` " + operatorSb.toString() + ' ' + s.substring(s.indexOf(operator) + operator.length()) + " and ");
        }
        sqlQuery.delete(sqlQuery.length() - 4, sqlQuery.length() - 1);
        return sqlQuery.toString();
    }

    @DeleteMapping("/api/{table1}/{id}/{table2}")
    public String deleteAllRelation(@PathVariable("table1") String tableName1, @PathVariable("id") String id,
                                 @PathVariable("table2") String tableName2) throws SQLException {
        getPrepareStatement("delete from `" + getRelationTableName(tableName1, tableName2) + "` where `" + tableName1 + ".id` = " +
                Integer.parseInt(id)).execute();
        return "success";
    }

    @DeleteMapping("/api/{table1}/{id1}/{table2}/{id2}")
    public String deleteRelation(@PathVariable("table1") String tableName1, @PathVariable("id1") String id1,
                                 @PathVariable("table2") String tableName2, @PathVariable("id2") String id2) throws SQLException {
        getPrepareStatement("delete from `" + getRelationTableName(tableName1, tableName2) + "` where `" + tableName1 + ".id` = " +
                                    Integer.parseInt(id1) + " and `" + tableName2 + ".id` = " + Integer.parseInt(id2)).execute();
        return "success";
    }

    @PostMapping("/api/{table1}/{id1}/{table2}/{id2}")
    public String addRelation(@PathVariable("table1") String tableName1, @PathVariable("id1") String id1,
                                 @PathVariable("table2") String tableName2, @PathVariable("id2") String id2) throws SQLException {
        String tableName = getRelationTableName(tableName1, tableName2);
        String insertString = "insert into `" + tableName + "` (`" + tableName1 + ".id`, `" + tableName2 + ".id`) values (" +
                                Integer.parseInt(id1) + ", " + Integer.parseInt(id2) + ')';
        String deleteString = "delete from `" + tableName + "` where `" + tableName1 + ".id` = " + Integer.parseInt(id1) +
                                " and `" + tableName2 + ".id` = " + Integer.parseInt(id2);
        try {
            getPrepareStatement(deleteString).execute();
            getPrepareStatement(insertString).execute();
        } catch (MySQLSyntaxErrorException ex) {
            if (ex.getMessage().contains("doesn't exist")) {
                if (createRelationTable(tableName1, tableName2)) {
                    getPrepareStatement(deleteString).execute();
                    getPrepareStatement(insertString).execute();
                }
                else {
                    getPrepareStatement("drop table `" + tableName + '`').execute();
                    return "error: either " + tableName1 + " or " + tableName2 + " doesn't exist";
                }
            }
            else throw ex;
        }
        return queryImpl("select * from `" + tableName + "` where id = LAST_INSERT_ID();").toString();
    }

    @DeleteMapping("/api/{table}")
    public String trunctaeTable(@PathVariable("table") String tableName) throws SQLException {
        getPrepareStatement("truncate `" + tableName + '`').execute();
        return "success";
    }

    @DeleteMapping("/api/{table}/{id}")
    public String deleteById(@PathVariable("table") String tableName, @PathVariable("id") String id) throws SQLException {
        JsonArray jsonArray = queryImpl("select * from `" + tableName + "` where `id` = " + Integer.parseInt(id));
        if (jsonArray == null || jsonArray.size() == 0)
            return "null";
        getPrepareStatement("delete from `" + tableName + "` where `id` = " + Integer.parseInt(id)).execute();
        return "success";
    }

    @GetMapping("/api/{table}/{id}")
    public String getTableById(@PathVariable("table") String tableName, @PathVariable("id") String idString) throws SQLException {
        JsonArray jsonArray = queryImpl("select * from `" + tableName + "` where id = " + Integer.parseInt(idString) + ";");
        return jsonArray == null ? "null" : jsonArray.toString();
    }

    @GetMapping("/api/{table}")
    public String getTable(@PathVariable("table") String tableName, HttpServletRequest request) throws SQLException {
        String queryString = request.getQueryString();
        if (queryString == null) {
            JsonArray jsonArray = queryImpl("select * from `" + tableName + '`');
            return jsonArray == null ? "null" : jsonArray.toString();
        }
        String sqlQuery = "select * from `" + tableName + "` where ";
        sqlQuery = parsePredicates(sqlQuery, queryString);
        JsonArray jsonArray = queryImpl(sqlQuery.toString());
        return jsonArray == null ? "null" : jsonArray.toString();
    }

    @GetMapping("/api/{table1}/{id}/{table2}")
    public String retriveRecordsFromRelation(@PathVariable("table1") String tableName1, @PathVariable("id") String id,
                                             @PathVariable("table2") String tableName2, HttpServletRequest request) throws SQLException {
        String requestQueryString = request.getQueryString();
        String tableName = getRelationTableName(tableName1, tableName2);
        String sqlQueryString = "select `" + tableName2 + "`.* from `" + tableName2 + "`, `" + tableName + "`, `" + tableName1 +
                "` where `" + tableName2 + "`.`id` = `" + tableName + "`.`" + tableName2 + ".id`" +
                " and `" + tableName1 + "`.`id` = `" + tableName + "`.`" + tableName1 +
                ".id` and `" + tableName1 + "`.`id` = " + Integer.parseInt(id) + ' ';
        if (requestQueryString == null) {
            JsonArray jsonArray = queryImpl(sqlQueryString.toString());
            return (jsonArray == null || jsonArray.size() == 0) ? (new JsonArray()).toString() : jsonArray.toString();
        }
        sqlQueryString = parsePredicates(sqlQueryString + " and ", requestQueryString);
        JsonArray jsonArray = queryImpl(sqlQueryString);
        return (jsonArray == null || jsonArray.size() == 0) ? (new JsonArray()).toString() : jsonArray.toString();
    }

    @PostMapping("/api/{table}")
    public String postTable(@PathVariable("table") String tableName, @RequestBody String jsonBody) throws SQLException {

        JsonObject jsonObject = gson.fromJson(jsonBody, JsonObject.class);
        Set<String> jsonKeySet = jsonObject.keySet();
        String sqlInsertJson = "insert into " + '`' + tableName + '`' + ' ';
        StringBuilder sqlInsertJsonFirstPart = new StringBuilder("(");
        StringBuilder sqlInsertJsonLastPart = new StringBuilder("(");
        String idString = null;
        for (String s : jsonKeySet) {
            if (s.equalsIgnoreCase("id"))
                idString = s;
            sqlInsertJsonFirstPart.append('`' + s + '`' + ", ");
            sqlInsertJsonLastPart.append('\'' + jsonObject.get(s).getAsString() + '\'' + ", ");
        }
        sqlInsertJsonFirstPart.deleteCharAt(sqlInsertJsonFirstPart.length() - 2).append(')');
        sqlInsertJsonLastPart.deleteCharAt(sqlInsertJsonLastPart.length() - 2).append(')');
        sqlInsertJson = sqlInsertJson + sqlInsertJsonFirstPart + " values " + sqlInsertJsonLastPart + ";";
        PreparedStatement pstmt = getPrepareStatement(sqlInsertJson);
        try {
            pstmt.execute();
        } catch (MySQLSyntaxErrorException ex) {
            if (ex.getMessage().contains("Unknown column")) {
                alterTable(jsonKeySet, tableName);
                pstmt.execute();
            }
            else if (ex.getMessage().contains("doesn't exist")) {
                createTable(jsonKeySet, tableName);
                pstmt.execute();
            }
            else throw ex;
        }
        JsonArray jsonArray;
        if (idString != null)
            jsonArray = queryImpl("select * from `" + tableName + "` where id = " + jsonObject.get(idString).getAsInt());
        else jsonArray = queryImpl("select * from `" + tableName + "` where id = LAST_INSERT_ID();");
        return jsonArray == null ? "null" : jsonArray.toString();
    }

    @PutMapping("/api/{table}/{id}")
    public String updateTable(@PathVariable("table") String tableName, @PathVariable("id") String id, @RequestBody String jsonBody) throws SQLException {
        JsonObject jsonObject = gson.fromJson(jsonBody, JsonObject.class);
        Set<String> jsonKeySet = jsonObject.keySet();
        JsonArray jsonArray = queryImpl("select * from `" + tableName + "` where `id` = " + Integer.parseInt(id));
        if (jsonArray == null || jsonArray.size() == 0)
            return "null";
        StringBuilder sqlUpdateJson = new StringBuilder("update `" + tableName + "` set ");
        for (String s : jsonKeySet) {
            if (s.equalsIgnoreCase("id"))
                sqlUpdateJson.append("`id` = " + jsonObject.get(s).getAsInt() + ", ");
            else sqlUpdateJson.append('`' + s + "` = \'" + jsonObject.get(s).getAsString() + "\', ");
        }
        sqlUpdateJson.deleteCharAt(sqlUpdateJson.length() - 2);
        sqlUpdateJson.append("where `id` = " + Integer.parseInt(id));
        try {
            getPrepareStatement(sqlUpdateJson.toString()).execute();
        } catch (MySQLSyntaxErrorException ex) {
            if (ex.getMessage().contains("Unknown column")) {
                alterTable(jsonKeySet, tableName);
                getPrepareStatement(sqlUpdateJson.toString()).execute();
            }
            else throw ex;
        }
        return queryImpl("select * from `" + tableName + "` where `id` = " + Integer.parseInt(id)).toString();
    }
}
