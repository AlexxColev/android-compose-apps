package md.student.registru.data

import kotlinx.coroutines.delay
import java.io.IOException

object GradebookRepository {
    private val courses = listOf(
        Course("PAM", "Mobile Application Development", "Lect. A. Popescu"),
        Course("DB", "Database Systems", "Assoc. Prof. M. Ionescu"),
        Course("CN", "Computer Networks", "Lect. V. Rusu"),
        Course("DM", "Discrete Mathematics", "Assoc. Prof. E. Lungu")
    )

    private var firstAttempt = true

    suspend fun getCourses(): List<Course> {
        delay(1500)
        if (firstAttempt) {
            firstAttempt = false
            throw IOException("Connection lost. Please check your network and retry.")
        }
        return courses
    }

    suspend fun getGradebook(courseId: String): GradebookData {
        delay(800)
        val course = courses.find { it.id == courseId }
            ?: Course(courseId, "Course $courseId", "Instructor")

        val students = listOf(
            Student(1, "Ana R."),
            Student(2, "Ion C."),
            Student(3, "Maria L."),
            Student(4, "Dan M."),
            Student(5, "Elena C."),
            Student(6, "Victor B.")
        )

        val lessons = listOf(
            Lesson(1, "02.09"),
            Lesson(2, "09.09"),
            Lesson(3, "16.09"),
            Lesson(4, "23.09"),
            Lesson(5, "30.09"),
            Lesson(6, "07.10")
        )

        val cells = mutableMapOf<CellKey, Cell>(
            CellKey(1, 1) to Cell(isPresent = true, grade = 9),
            CellKey(1, 3) to Cell(isPresent = true, grade = 10),
            CellKey(2, 2) to Cell(isPresent = false, grade = null),
            CellKey(2, 3) to Cell(isPresent = true, grade = 7),
            CellKey(3, 1) to Cell(isPresent = true, grade = 8),
            CellKey(3, 5) to Cell(isPresent = false, grade = null),
            CellKey(4, 2) to Cell(isPresent = false, grade = null),
            CellKey(5, 2) to Cell(isPresent = true, grade = 6),
            CellKey(5, 4) to Cell(isPresent = true, grade = 7),
            CellKey(6, 1) to Cell(isPresent = true, grade = 10),
            CellKey(6, 4) to Cell(isPresent = false, grade = null)
        )

        return GradebookData(
            course = course,
            lessons = lessons,
            students = students,
            cells = cells
        )
    }
}