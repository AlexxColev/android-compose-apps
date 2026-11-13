package com.example.lab_2_android.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.lab_2_android.BuildConfig
import com.example.lab_2_android.data.FacultyData

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlanScreen(onRoomClick: (String) -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(BuildConfig.FACULTY_NAME) }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Box(
                modifier = Modifier
                    .size(340.dp, 240.dp)
                    .border(2.dp, Color(0xFF1B2420))
            ) {
                FacultyData.rooms.forEach { room ->
                    Box(
                        modifier = Modifier
                            .offset(room.x.dp, room.y.dp)
                            .size(room.w.dp, room.h.dp)
                            .background(Color(0xFFD7E9DD))
                            .border(1.dp, Color(0xFF3B7A4F))
                            .clickable { onRoomClick(room.id) },
                        contentAlignment = Alignment.Center
                    ) {
                        Text(room.id, style = MaterialTheme.typography.titleMedium)
                    }
                }
                Text(
                    text = "corridor",
                    modifier = Modifier.align(Alignment.Center),
                    color = Color.Gray
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            Text("Tap a room on the map", style = MaterialTheme.typography.bodyMedium)
        }
    }
}