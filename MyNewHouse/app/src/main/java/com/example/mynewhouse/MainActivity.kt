package com.example.mynewhouse

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.GenericShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.House
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge() //stretching on all devices
        setContent {
            MaterialTheme {
                HouseScreen()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HouseScreen() {
    var steps by rememberSaveable { mutableStateOf(HouseRepository.initialSteps) } // stores the current list of stages
    var showSheet by remember { mutableStateOf(false) } // check if panel is open or not
    val doneCount = steps.count { it.isDone }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Building a house") },
                actions = {
                    IconButton(
                        onClick = { steps = HouseRepository.initialSteps }
                    ) {
                        Icon(
                            imageVector = Icons.Default.Refresh,
                            contentDescription = "Reset"
                        )
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { showSheet = true }) {
                Icon(
                    imageVector = Icons.Default.Build,
                    contentDescription = "Stage"
                )
            }
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentAlignment = Alignment.Center
        ) {
            if (doneCount == 0) {
                Text(
                    text = "Empty field",
                    style = MaterialTheme.typography.bodyLarge
                )
            } else {
                HouseDrawing(steps = steps)
            }

            if (doneCount == 8) {
                Text(
                    text = "House is ready,\nLight weight baby!!!",
                    style = MaterialTheme.typography.headlineMedium,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .align(Alignment.TopCenter)
                        .padding(top = 16.dp)
                )
            }
        }
    }

    if (showSheet) {
        ModalBottomSheet(onDismissRequest = { showSheet = false },
                containerColor = Color.White.copy(alpha = 0.85f)
        ) {
            HouseBottomSheetContent(
                steps = steps,
                onStepToggle = { index ->
                    steps = steps.mapIndexed { i, step ->
                        if (i == index) step.copy(isDone = !step.isDone)
                        else step
                    }
                }
            )
        }
    }
}

@Composable
fun HouseBottomSheetContent(
    steps: List<BuildStep>,
    onStepToggle: (Int) -> Unit
) {
    val doneCount = steps.count { it.isDone }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(
            text = "Starting the house building",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        steps.forEachIndexed { index, step ->
            val isEnabled = index == doneCount || index == doneCount - 1

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 1.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Checkbox(
                    checked = step.isDone,
                    enabled = isEnabled,
                    onCheckedChange = { onStepToggle(index) }
                )
                Text(
                    text = "${step.id}. ${step.title}",
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(start = 8.dp)
                )
            }
        }
    }
}

enum class HousePart { //all house elements
    FOUNDATION,
    WALLS,
    ROOF,
    LEFT_WINDOW,

    RIGHT_WINDOW,

    RIGHTDOWN_WINDOW,
    LEFTDOWN_WINDOW,
    DOOR
}

data class BuildStep(
    val id: Int, // numbers from 1 to 6
    val title: String, // step
    val part: HousePart,
    val isDone: Boolean = false // checking if the step is done or not
)

val TriangleShape = GenericShape { size, _ ->
    moveTo(size.width / 2f, 0f)
    lineTo(size.width, size.height)
    lineTo(0f, size.height)
    close()
}

object HouseRepository {
    val initialSteps = listOf(
        BuildStep(1, "Building a foundation", HousePart.FOUNDATION),
        BuildStep(2, "Raising walls", HousePart.WALLS),
        BuildStep(3, "Building a roof", HousePart.ROOF),
        BuildStep(4, "Making a left window", HousePart.LEFT_WINDOW),
        BuildStep(5, "Making a right window", HousePart.RIGHT_WINDOW),
        BuildStep(6, "Making down window right", HousePart.RIGHTDOWN_WINDOW),
        BuildStep(7, "Making down left window", HousePart.LEFTDOWN_WINDOW),
        BuildStep(8, "Door installation", HousePart.DOOR)
    )
}

@Composable
fun HouseDrawing(
    steps: List<BuildStep>,
    modifier: Modifier = Modifier
) {
    fun isDone(part: HousePart): Boolean {
        return steps.find { it.part == part }?.isDone == true
    }

    Box(
        modifier = modifier
            .size(width = 320.dp, height = 300.dp)
            .background(Color(0xFFF4F3EF)),
        contentAlignment = Alignment.Center
    ) {
        if (isDone(HousePart.FOUNDATION)) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(4.dp)
                    .align(Alignment.BottomCenter)
                    .background(Color(0xFFBFC9C2))
            )
        }
        if (isDone(HousePart.WALLS)) {
            Box(
                modifier = Modifier
                    .size(width = 200.dp, height = 180.dp)
                    .align(Alignment.BottomCenter)
                    .offset(y = (-4).dp)
                    .background(Color.White)
                    .border(2.dp, Color(0xFF1B2420))
            )
        }
        if (isDone(HousePart.ROOF)) {
            Box(
                modifier = Modifier
                    .size(width = 240.dp, height = 110.dp)
                    .align(Alignment.BottomCenter)
                    .offset(y = (-184).dp)
                    .background(Color(0xFF3B7A4F), TriangleShape)
            )
        }
        if (isDone(HousePart.LEFT_WINDOW)) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .align(Alignment.BottomCenter)
                    .offset(x = (-50).dp, y = (-110).dp)
                    .background(Color(0xFFB3E5C5))
            )
        }
        if (isDone(HousePart.RIGHT_WINDOW)) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .align(Alignment.BottomCenter)
                    .offset(x = 50.dp, y = (-110).dp)
                    .background(Color(0xFFB3E5C5))
            )
        }
        if (isDone(HousePart.RIGHTDOWN_WINDOW)) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .align(Alignment.BottomCenter)
                    .offset(x = 50.dp, y = (-54).dp)
                    .background(Color(0xFFB3E5C5))
            )
        }
        if (isDone(HousePart.LEFTDOWN_WINDOW)) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .align(Alignment.BottomCenter)
                    .offset(x = (-50).dp, y = (-54).dp)
                    .background(Color(0xFFB3E5C5))
            )
        }
        if (isDone(HousePart.DOOR)) {
            Box(
                modifier = Modifier
                    .size(width = 50.dp, height = 90.dp)
                    .align(Alignment.BottomCenter)
                    .offset(y = (-4).dp)
                    .background(Color(0xFF1B2420))
            )
        }
    }
}