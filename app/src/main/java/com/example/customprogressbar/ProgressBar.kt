package com.example.customprogressbar

import android.widget.Toast
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.customprogressbar.ui.theme.Purple40
import com.example.customprogressbar.ui.theme.Purple80

@Composable
fun CustomProgressBar() {

    val context = LocalContext.current
    var progressCount: Int by remember { mutableStateOf(0) }
    var progress by remember { mutableStateOf(0f) }

    // this when statement converts the count when we increase or decrease the progress
    // from the button press from the float value that we will pass in the size
    when (progressCount) {
        0 -> progress = 0.0f
        1 -> progress = 0.1f
        2 -> progress = 0.2f
        3 -> progress = 0.3f
        4 -> progress = 0.4f
        5 -> progress = 0.5f
        6 -> progress = 0.6f
        7 -> progress = 0.7f
        8 -> progress = 0.8f
        9 -> progress = 0.9f
        10 -> progress = 1.0f
    }

    val size by animateFloatAsState(
        targetValue = progress,
        tween(
            durationMillis = 1000,
            delayMillis = 200,
            easing = LinearOutSlowInEasing
        )
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 100.dp, start = 30.dp, end = 30.dp)
    ) {
        // for the text above progressBar
        Row(
            modifier = Modifier
                .widthIn(min = 30.dp)
                .fillMaxWidth(size),
            horizontalArrangement = Arrangement.End
        ) {
            Text(text = "$progress")
        }

        //Progress Bar
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(17.dp)
        ) {
            // for the background of the ProgressBar
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(9.dp))
                    .background(Purple40)
            )
            // for the progress of the ProgressBar
            Box(
                modifier = Modifier
                    .fillMaxWidth(size)
                    .fillMaxHeight()
                    .clip(RoundedCornerShape(9.dp))
                    .background(Purple80)
                    .animateContentSize()
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 30.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            //Decrease Button
            OutlinedButton(onClick = {
                if (progressCount > 0) {
                    progressCount -= 2
                } else {
                    Toast.makeText(context, "You cannot decrease anymore", Toast.LENGTH_SHORT)
                        .show()
                }
            }) {
                Text(text = "Decrease")
            }
            //Increase Button
            Button(onClick = {
                if (progressCount < 10) {
                    progressCount += 2
                } else {
                    Toast.makeText(context, "You cannot increase anymore", Toast.LENGTH_SHORT)
                        .show()
                }
            }) {
                Text(text = "Increase")
            }
        }
    }

//    LaunchedEffect(key1 = true){
//        progress = 0.7f
//    }
}



