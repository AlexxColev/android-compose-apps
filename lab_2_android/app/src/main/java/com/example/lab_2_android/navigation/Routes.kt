package com.example.lab_2_android.navigation


object Routes {
    const val PLAN = "plan"
    const val ROOM = "room/{roomId}"
    const val GROUP = "group/{groupId}"
    const val STUDENT = "student/{studentId}"

    fun room(id: String) = "room/$id"
    fun group(id: String) = "group/$id"
    fun student(id: Int) = "student/$id"
}