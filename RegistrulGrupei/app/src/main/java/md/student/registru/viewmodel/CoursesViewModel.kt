package md.student.registru.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import md.student.registru.data.GradebookRepository
import java.io.IOException

class CoursesViewModel : ViewModel() {

    private val _uiState = MutableStateFlow<CoursesUiState>(CoursesUiState.Loading)
    val uiState: StateFlow<CoursesUiState> = _uiState.asStateFlow()

    init {
        loadCourses()
    }

    fun loadCourses() {
        viewModelScope.launch {
            _uiState.value = CoursesUiState.Loading
            _uiState.value = try {
                CoursesUiState.Success(GradebookRepository.getCourses())
            } catch (e: IOException) {
                CoursesUiState.Error(e.message ?: "Unknown network error")
            }
        }
    }
}