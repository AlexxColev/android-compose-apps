package com.example.lab_2_android.data


data class Room(
    val id: String,
    val x: Int,
    val y: Int,
    val w: Int,
    val h: Int
)

data class ScheduleEntry(
    val day: String,
    val time: String,
    val subject: String,
    val professor: String,
    val roomId: String,
    val groupId: String
)

data class Group(
    val id: String,
    val year: Int,
    val studentCount: Int,
    val email: String
)

data class Student(
    val id: Int,
    val name: String,
    val studentIdNumber: String,
    val email: String,
    val gpa: Double,
    val groupId: String
)
