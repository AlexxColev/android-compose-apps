package md.student.registru.viewmodel

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import md.student.registru.data.Cell
import md.student.registru.data.CellKey
import md.student.registru.data.GradebookRepository

class GradebookViewModel(
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val courseId: String = checkNotNull(savedStateHandle["courseId"])
    private val _uiState = MutableStateFlow(GradebookUiState())
    val uiState: StateFlow<GradebookUiState> = _uiState.asStateFlow()

    init {
        Log.d("CICLU", "GradebookViewModel created for course: $courseId")
        loadGradebook()
    }

    private fun loadGradebook() {
        viewModelScope.launch {
            val gradebookData = GradebookRepository.getGradebook(courseId)
            _uiState.update { currentState ->
                currentState.copy(
                    isLoading = false,
                    data = gradebookData
                )
            }
        }
    }

    fun selectCell(key: CellKey) {
        _uiState.update { it.copy(selectedCell = key) }
    }

    fun closeDialog() {
        _uiState.update { it.copy(selectedCell = null) }
    }

    fun setGrade(grade: Int) {
        val key = _uiState.value.selectedCell ?: return
        _uiState.update { state ->
            val currentData = state.data ?: return@update state
            val updatedCell = Cell(isPresent = true, grade = grade)
            val updatedMap = currentData.cells + (key to updatedCell)
            state.copy(
                data = currentData.copy(cells = updatedMap),
                selectedCell = null
            )
        }
    }

    fun markAbsent() {
        val key = _uiState.value.selectedCell ?: return
        _uiState.update { state ->
            val currentData = state.data ?: return@update state
            val updatedCell = Cell(isPresent = false, grade = null)
            val updatedMap = currentData.cells + (key to updatedCell)
            state.copy(
                data = currentData.copy(cells = updatedMap),
                selectedCell = null
            )
        }
    }

    override fun onCleared() {
        super.onCleared()
        Log.d("CICLU", "GradebookViewModel destroyed - coroutines cancelled")
    }
}