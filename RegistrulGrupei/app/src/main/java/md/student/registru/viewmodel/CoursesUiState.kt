package md.student.registru.viewmodel

import md.student.registru.data.Course

sealed interface CoursesUiState {
    data object Loading : CoursesUiState
    data class Success(val courses: List<Course>) : CoursesUiState
    data class Error(val message: String) : CoursesUiState
}