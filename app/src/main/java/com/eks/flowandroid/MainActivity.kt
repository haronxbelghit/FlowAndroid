package com.eks.flowandroid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.eks.flowandroid.ui.theme.FlowAndroidTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FlowAndroidTheme {
                // reference the viewModel
                val viewModel = viewModel<MainViewModel>()
                // collecting the flow we created as state
                val time = viewModel.countDownFlow.collectAsState(initial = 10)
                Box(
                    modifier = Modifier.fillMaxSize()
                ){
                    Text(
                        // taking value of the state
                        text = time.value.toString(),
                        fontSize = 30.sp,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            }
        }
    }
}