package md.student.registru.data

data class Course(
    val id: String,
    val name: String,
    val instructor: String
)

data class Student(
    val id: Int,
    val name: String
)

data class Lesson(
    val number: Int,
    val date: String
)

data class CellKey(
    val studentId: Int,
    val lessonNumber: Int
)

data class Cell(
    val isPresent: Boolean = true,
    val grade: Int? = null
)

data class GradebookData(
    val course: Course,
    val lessons: List<Lesson>,
    val students: List<Student>,
    val cells: Map<CellKey, Cell>
)