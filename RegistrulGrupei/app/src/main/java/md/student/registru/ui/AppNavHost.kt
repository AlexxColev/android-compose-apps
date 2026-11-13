package md.student.registru.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

object Routes {
    const val COURSES = "courses"
    const val GRADEBOOK = "gradebook/{courseId}"
    fun gradebook(courseId: String) = "gradebook/$courseId"
}

@Composable
fun AppNavHost(
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = Routes.COURSES
    ) {
        composable(Routes.COURSES) {
            CoursesRoute(
                onCourseClick = { courseId ->
                    navController.navigate(Routes.gradebook(courseId))
                }
            )
        }

        composable(
            route = Routes.GRADEBOOK,
            arguments = listOf(
                navArgument("courseId") { type = NavType.StringType }
            )
        ) {
            GradebookRoute(
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }
    }
}