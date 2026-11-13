package com.example.lab_2_android.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.lab_2_android.data.FacultyData

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RoomScreen(
    roomId: String,
    onGroupClick: (String) -> Unit,
    onBack: () -> Unit
) {
    val scheduleItems = FacultyData.scheduleForRoom(roomId)

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Room $roomId Schedule") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { innerPadding ->
        if (scheduleItems.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                contentAlignment = Alignment.Center
            ) {
                Text("No classes scheduled in this room.")
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(scheduleItems) { entry ->
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
                    ) {
                        Row(
                            modifier = Modifier
                                .padding(16.dp)
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column {
                                Text("${entry.day} ${entry.time}", style = MaterialTheme.typography.labelMedium, color = Color.Gray)
                                Text(entry.subject, style = MaterialTheme.typography.titleMedium)
                                Text(entry.professor, style = MaterialTheme.typography.bodySmall)
                            }
                            SuggestionChip(
                                onClick = { onGroupClick(entry.groupId) },
                                label = { Text(entry.groupId) }
                            )
                        }
                    }
                }
            }
        }
    }
}