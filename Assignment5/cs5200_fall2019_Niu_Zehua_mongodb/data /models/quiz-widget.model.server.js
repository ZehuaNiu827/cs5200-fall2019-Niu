const mongoose = require("mongoose")
const quizwidgetSchema = require('./quiz-widget.schema.sever')
module.exports = mongoose.model('quizwidgetModel', quizwidgetSchema)