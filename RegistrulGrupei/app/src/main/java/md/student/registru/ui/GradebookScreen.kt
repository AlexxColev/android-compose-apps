package md.student.registru.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import md.student.registru.data.Cell
import md.student.registru.data.CellKey
import md.student.registru.data.Lesson
import md.student.registru.data.Student
import md.student.registru.viewmodel.GradebookUiState
import md.student.registru.viewmodel.GradebookViewModel

@Composable
fun GradebookRoute(
    onBackClick: () -> Unit,
    viewModel: GradebookViewModel = viewModel()
) {
    val state by viewModel.uiState.collectAsState()
    GradebookScreen(
        state = state,
        onBackClick = onBackClick,
        onCellClick = viewModel::selectCell,
        onSetGrade = viewModel::setGrade,
        onMarkAbsent = viewModel::markAbsent,
        onCloseDialog = viewModel::closeDialog
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GradebookScreen(
    state: GradebookUiState,
    onBackClick: () -> Unit,
    onCellClick: (CellKey) -> Unit,
    onSetGrade: (Int) -> Unit,
    onMarkAbsent: () -> Unit,
    onCloseDialog: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(state.data?.course?.name ?: "Gradebook") },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            if (state.isLoading) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            } else state.data?.let { data ->
                val scrollState = rememberScrollState()

                Column(modifier = Modifier.fillMaxSize()) {
                    HeaderRow(
                        lessons = data.lessons,
                        scrollState = scrollState
                    )
                    HorizontalDivider(color = Color.Gray, thickness = 1.dp)

                    LazyColumn(modifier = Modifier.fillMaxWidth()) {
                        // Replace items(data.students) with this:
                        items(data.students.sortedByDescending { student -> state.calculateGPA(student.id) ?: -1.0 }) { student ->
                            StudentRow(
                                student = student,
                                lessons = data.lessons,
                                state = state,
                                scrollState = scrollState,
                                onCellClick = onCellClick
                            )
                            HorizontalDivider(color = Color(0xFFE0E0E0), thickness = 0.5.dp)
                        }
                    }
                }

                state.selectedCell?.let { key ->
                    val student = data.students.find { it.id == key.studentId }
                    val lesson = data.lessons.find { it.number == key.lessonNumber }
                    GradeDialog(
                        studentName = student?.name ?: "",
                        lessonDate = lesson?.date ?: "",
                        onSetGrade = onSetGrade,
                        onMarkAbsent = onMarkAbsent,
                        onDismiss = onCloseDialog
                    )
                }
            }
        }
    }
}

@Composable
fun HeaderRow(
    lessons: List<Lesson>,
    scrollState: androidx.compose.foundation.ScrollState
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFFE8F5E9))
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Student",
            modifier = Modifier.width(110.dp).padding(start = 8.dp),
            fontWeight = FontWeight.Bold
        )

        Row(
            modifier = Modifier
                .weight(1f)
                .horizontalScroll(scrollState)
        ) {
            lessons.forEach { lesson ->
                Box(
                    modifier = Modifier
                        .size(44.dp)
                        .border(0.5.dp, Color(0xFFB9C6BD)),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = lesson.date,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }

        Text(
            text = "Abs.",
            modifier = Modifier.width(44.dp),
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            fontSize = 12.sp
        )

        Text(
            text = "GPA",
            modifier = Modifier.width(52.dp),
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            fontSize = 12.sp
        )
    }
}

@Composable
fun StudentRow(
    student: Student,
    lessons: List<Lesson>,
    state: GradebookUiState,
    scrollState: androidx.compose.foundation.ScrollState,
    onCellClick: (CellKey) -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = student.name,
            modifier = Modifier.width(110.dp).padding(start = 8.dp),
            fontSize = 14.sp
        )

        Row(
            modifier = Modifier
                .weight(1f)
                .horizontalScroll(scrollState)
        ) {
            lessons.forEach { lesson ->
                val key = CellKey(student.id, lesson.number)
                CellView(
                    cell = state.getCell(key),
                    onClick = { onCellClick(key) }
                )
            }
        }

        val absences = state.calculateAbsences(student.id)
        Text(
            text = if (absences > 0) absences.toString() else "-",
            modifier = Modifier.width(44.dp),
            textAlign = TextAlign.Center,
            fontSize = 13.sp,
            color = if (absences > 0) Color.Red else Color.Unspecified
        )

        val gpa = state.calculateGPA(student.id)
        Text(
            text = gpa?.let { "%.2f".format(it) } ?: "-",
            modifier = Modifier.width(52.dp),
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Medium,
            fontSize = 13.sp
        )
    }
}

@Composable
fun CellView(
    cell: Cell,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .size(44.dp)
            .border(0.5.dp, Color(0xFFB9C6BD))
            .background(if (cell.isPresent) Color.White else Color(0xFFF6D5CC))
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = if (!cell.isPresent) "a" else cell.grade?.toString() ?: "",
            color = if (!cell.isPresent) Color.Red else Color.Unspecified,
            fontWeight = if (!cell.isPresent) FontWeight.Bold else FontWeight.Normal
        )
    }
}

@Composable
fun GradeDialog(
    studentName: String,
    lessonDate: String,
    onSetGrade: (Int) -> Unit,
    onMarkAbsent: () -> Unit,
    onDismiss: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(text = "$studentName ($lessonDate)") },
        text = {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text("Select Grade:")
                Spacer(modifier = Modifier.height(12.dp))
                Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                    (1..5).forEach { grade ->
                        OutlinedButton(
                            onClick = { onSetGrade(grade) },
                            contentPadding = PaddingValues(2.dp),
                            modifier = Modifier.width(36.dp)
                        ) {
                            Text(grade.toString())
                        }
                    }
                }
                Spacer(modifier = Modifier.height(4.dp))
                Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                    (6..10).forEach { grade ->
                        OutlinedButton(
                            onClick = { onSetGrade(grade) },
                            contentPadding = PaddingValues(2.dp),
                            modifier = Modifier.width(36.dp)
                        ) {
                            Text(grade.toString())
                        }
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))
                Button(
                    onClick = onMarkAbsent,
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error)
                ) {
                    Text("Mark Absent (a)")
                }
            }
        },
        confirmButton = {},
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancel")
            }
        }
    )
}