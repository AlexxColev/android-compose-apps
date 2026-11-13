package com.example.lab_2_android.data


object FacultyData {
    val rooms = listOf(
        Room("201", x = 16, y = 18, w = 92, h = 75),
        Room("202", x = 124, y = 18, w = 92, h = 75),
        Room("203", x = 232, y = 18, w = 92, h = 75),
        Room("204", x = 232, y = 147, w = 92, h = 75),
        Room("205", x = 124, y = 147, w = 92, h = 75),
        Room("206", x = 16, y = 147, w = 92, h = 75)
    )

    val schedule = listOf(
        ScheduleEntry("Monday", "08:00", "Android Development", "Lect. A. Popescu", "203", "TI-231"),
        ScheduleEntry("Monday", "09:45", "Databases", "Lect. A. Popescu", "203", "TI-232"),
        ScheduleEntry("Tuesday", "11:30", "Networks", "Lect. A. Popescu", "203", "CR-231"),
        ScheduleEntry("Thursday", "08:00", "Android Development", "Lect. A. Popescu", "203", "TI-232"),
        ScheduleEntry("Monday", "08:00", "Web Programming", "Prof. I. Munteanu", "201", "TI-231"),
        ScheduleEntry("Wednesday", "10:00", "Mathematics", "Dr. V. Ceban", "202", "CR-231"),
        ScheduleEntry("Wednesday", "12:00", "Operating Systems", "Lect. E. Moraru", "204", "TI-232"),
        ScheduleEntry("Friday", "09:00", "Software Architecture", "Prof. S. Rusu", "205", "TI-231"),
        ScheduleEntry("Friday", "11:00", "Cybersecurity", "Dr. N. Cojocaru", "206", "CR-231")
    )

    val groups = listOf(
        Group("TI-231", year = 2, studentCount = 5, email = "ti231@student.md"),
        Group("TI-232", year = 2, studentCount = 4, email = "ti232@student.md"),
        Group("CR-231", year = 2, studentCount = 4, email = "cr231@student.md")
    )

    val students = listOf(
        Student(1, "Ana Rusu", "TI231001", "ana.rusu@student.md", 9.50, "TI-231"),
        Student(2, "Ion Ceban", "TI231002", "ion.ceban@student.md", 8.75, "TI-231"),
        Student(3, "Maria Lungu", "TI231004", "maria.lungu@student.md", 9.25, "TI-231"),
        Student(4, "Dan Morari", "TI231005", "dan.morari@student.md", 7.80, "TI-231"),
        Student(5, "Elena Cojocaru", "TI231006", "elena.cojocaru@student.md", 9.10, "TI-231"),
        Student(6, "Victor Botnaru", "TI232001", "victor.b@student.md", 8.40, "TI-232"),
        Student(7, "Cristina Sirbu", "TI232002", "cristina.s@student.md", 9.60, "TI-232")
    )

    fun scheduleForRoom(id: String): List<ScheduleEntry> = schedule.filter { it.roomId == id }
    fun group(id: String): Group? = groups.find { it.id == id }
    fun studentsInGroup(id: String): List<Student> = students.filter { it.groupId == id }
    fun student(id: Int): Student? = students.find { it.id == id }
}