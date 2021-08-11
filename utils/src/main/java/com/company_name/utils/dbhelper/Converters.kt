package com.company_name.utils.dbhelper

//open class Converters {
//
//    @TypeConverter
//    fun fromCourse(course: Course?): String? {
//        if (course == null) {
//            return null
//        }
//
//        return course.toJson()
//    }
//
//    @TypeConverter
//    fun toCourse(json: String?): Course? {
//        if (json == null) {
//            return null
//        }
//
//        return json.fromJson()
//    }
//
//    @TypeConverter
//    fun fromContent(content: Content?): String? {
//        if (content == null) {
//            return null
//        }
//
//        return content.toJson()
//    }
//
//    @TypeConverter
//    fun toContent(json: String?): Content? {
//        if (json == null) {
//            return null
//        }
//
//        return json.fromJson()
//    }
//
//    @TypeConverter
//    fun fromListAnswers(listAnswers: List<Quiz.Answers>): String? {
//        return listAnswers.toJson()
//    }
//
//    @TypeConverter
//    fun toListAnswers(json: String?): List<Quiz.Answers> {
//        if (json == null) {
//            return Collections.emptyList()
//        }
//
//        return json.fromJson()
//    }
//}