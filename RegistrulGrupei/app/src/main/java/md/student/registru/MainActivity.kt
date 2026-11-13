package md.student.registru

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import md.student.registru.ui.AppNavHost

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("CICLU", "onCreate")

        setContent {
            MaterialTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    AppNavHost()
                }
            }
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d("CICLU", "onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d("CICLU", "onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d("CICLU", "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d("CICLU", "onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("CICLU", "onDestroy")
    }
}