package md.student.registru.viewmodel

import md.student.registru.data.Cell
import md.student.registru.data.CellKey
import md.student.registru.data.GradebookData
import md.student.registru.data.Student

data class GradebookUiState(
    val isLoading: Boolean = true,
    val data: GradebookData? = null,
    val selectedCell: CellKey? = null
) {
    // Add this property to auto-sort students by GPA (highest first)
    val sortedStudents: List<Student>
        get() = data?.students?.sortedByDescending { calculateGPA(it.id) ?: -1.0 } ?: emptyList()

    fun getCell(key: CellKey): Cell {
        return data?.cells?.get(key) ?: Cell()
    }

    fun calculateGPA(studentId: Int): Double? {
        val grades = data?.lessons?.mapNotNull { lesson ->
            val cell = getCell(CellKey(studentId, lesson.number))
            if (cell.isPresent) cell.grade else null
        } ?: emptyList()

        return if (grades.isNotEmpty()) grades.average() else null
    }

    fun calculateAbsences(studentId: Int): Int {
        return data?.lessons?.count { lesson ->
            val cell = getCell(CellKey(studentId, lesson.number))
            !cell.isPresent
        } ?: 0
    }
}