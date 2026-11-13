package com.example.lab_2_android.navigation


import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.lab_2_android.ui.screens.GroupScreen
import com.example.lab_2_android.ui.screens.PlanScreen
import com.example.lab_2_android.ui.screens.RoomScreen
import com.example.lab_2_android.ui.screens.StudentScreen

@Composable
fun AppNavHost() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Routes.PLAN
    ) {
        composable(Routes.PLAN) {
            PlanScreen(
                onRoomClick = { roomId ->
                    navController.navigate(Routes.room(roomId))
                }
            )
        }

        composable(
            route = Routes.ROOM,
            arguments = listOf(navArgument("roomId") { type = NavType.StringType })
        ) { entry ->
            val roomId = entry.arguments?.getString("roomId") ?: return@composable
            RoomScreen(
                roomId = roomId,
                onGroupClick = { groupId ->
                    navController.navigate(Routes.group(groupId))
                },
                onBack = { navController.popBackStack() }
            )
        }

        composable(
            route = Routes.GROUP,
            arguments = listOf(navArgument("groupId") { type = NavType.StringType })
        ) { entry ->
            val groupId = entry.arguments?.getString("groupId") ?: return@composable
            GroupScreen(
                groupId = groupId,
                onStudentClick = { studentId ->
                    navController.navigate(Routes.student(studentId))
                },
                onBack = { navController.popBackStack() }
            )
        }

        composable(
            route = Routes.STUDENT,
            arguments = listOf(navArgument("studentId") { type = NavType.IntType })
        ) { entry ->
            val studentId = entry.arguments?.getInt("studentId") ?: return@composable
            StudentScreen(
                studentId = studentId,
                onBackToPlan = {
                    navController.navigate(Routes.PLAN) {
                        popUpTo(Routes.PLAN) { inclusive = false }
                        launchSingleTop = true
                    }
                },
                onBack = { navController.popBackStack() }
            )
        }
    }
}