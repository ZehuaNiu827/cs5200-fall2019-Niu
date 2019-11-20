require('./db')();
const universityDao = require('./daos/university.dao.server');
const assert = require('assert')
universityDao.truncateDatabase()
    .then(() => universityDao.populateDatabase())
    .then(() => testStudentsInitialCount())
    .then(() => testQuestionsInitialCount())
    .then(() => testAnswersInitialCount())
    .then(() => testDeleteAnswer())
    .then(() => testDeleteQuestion())
    .then(() => testDeleteStudent())

const assertEquals = (actual, expected) => {
    assert.strictEqual(actual, expected, `Test failed: The actual value ${actual} is not equivalent to the expected value ${expected}.`);
};

testStudentsInitialCount = () => {
    return universityDao.findAllStudents()
        .then(students => {
            assertEquals(students.length, 2);
        })
        .then(() => console.log('testStudentsInitialCount pass'))
        .catch(err => {
            console.log('testStudentsInitialCount not pass the test.');
            console.log(err.message);
        });
};

testQuestionsInitialCount = () => {
    return universityDao.findAllQuestions()
        .then(questions => {
            assertEquals(questions.length, 4);
        })
        .then(() => console.log('testQuestionsInitialCount pass'))
        .catch(err => {
            console.log('testQuestionsInitialCount did not pass.');
            console.log(err.message);
        });
};

testAnswersInitialCount = () => {
    return universityDao.findAllAnswers()
        .then(answers => {
            assertEquals(answers.length, 8);
        })
        .then(() => console.log('testAnswersInitialCount pass'))
        .catch(err => {
            console.log('testAnswersInitialCount did not pass.');
            console.log(err.message);
        });
};

testDeleteAnswer = () => {
    return universityDao.deleteAnswer(890)
        .then(() => universityDao.findAllAnswers())
        .then(answers => {
            assertEquals(answers.length, 7);
        })
        .then(() => universityDao.findAnswersByStudent(234))
        .then(answers => {
            assertEquals(answers.length, 3);
        })
        .then(() => universityDao.findAnswersByQuestion(654))
        .then(answers => {
            assertEquals(answers.length, 1);
        })
        .then(() => console.log('testDeleteAnswer pass'))
        .catch(err => {
            console.log('testDeleteAnswer did not pass.');
            console.log(err.message);
        });
};

testDeleteQuestion = () => {
    return universityDao.deleteQuestion(321)
        .then(() => universityDao.findAllQuestions())
        .then(questions => {
            assertEquals(questions.length, 3);
        })
        .then(() => console.log('testDeleteQuestion pass'))
        .catch(err => {
            console.log('testDeleteQuestion did not pass.');
            console.log(err.message);
        });
};

testDeleteStudent = () => {
    return universityDao.deleteStudent(234)
        .then(() => universityDao.findAllStudents())
        .then(students => {
            assertEquals(students.length, 1);
        })
        .then(() => console.log('testDeleteStudent pass'))
        .catch(err => {
            console.log('testDeleteStudent did not pass.');
            console.log(err.message);
        });
};








