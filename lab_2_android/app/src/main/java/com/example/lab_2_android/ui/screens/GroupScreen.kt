package com.example.lab_2_android.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.lab_2_android.data.FacultyData

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GroupScreen(
    groupId: String,
    onStudentClick: (Int) -> Unit,
    onBack: () -> Unit
) {
    val groupInfo = FacultyData.group(groupId)
    val studentsList = FacultyData.studentsInGroup(groupId)

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Group $groupId") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp)
        ) {
            groupInfo?.let { group ->
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text("Study Year: ${group.year}")
                        Text("Student Count: ${group.studentCount}")
                        Text("Email: ${group.email}")
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))
            }

            Text("Students", style = MaterialTheme.typography.titleMedium)
            Spacer(modifier = Modifier.height(8.dp))

            LazyColumn {
                items(studentsList) { s ->
                    ListItem(
                        headlineContent = { Text(s.name) },
                        supportingContent = { Text(s.studentIdNumber) },
                        modifier = Modifier.clickable { onStudentClick(s.id) }
                    )
                    HorizontalDivider()
                }
            }
        }
    }
}