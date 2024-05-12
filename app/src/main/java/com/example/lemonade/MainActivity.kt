package com.example.lemonade

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lemonade.ui.theme.LemonadeTheme

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LemonadeTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) {
                    LemonadeApp()
                }
            }
        }
    }
}

@Preview
@Composable
fun LemonadeAppPreview() {
    LemonadeApp()
}

@Composable
fun LemonadeApp() {
    val painter: Painter
    val contentDescription: String
    val text: String
    val action: () -> Unit
    var randomCount = (2..4).random()

    var currentStep by remember { mutableIntStateOf(1) }

    when(currentStep) {
        1 -> {
            painter = painterResource(id = R.drawable.lemon_tree)
            contentDescription = stringResource(id = R.string.content_description_lemon_tree)
            text = stringResource(id = R.string.lemon_tree)
            action = { currentStep = 2 }
        }
        2 -> {
            painter = painterResource(id = R.drawable.lemon_squeeze)
            contentDescription = stringResource(id = R.string.content_description_lemon_squeeze)
            text = stringResource(id = R.string.lemon_squeeze)
            action = {
                if (randomCount == 1) {
                    randomCount = (2..4).random()
                    currentStep = 3
                } else {
                    randomCount--
                }
            }
        }
        3 -> {
            painter = painterResource(id = R.drawable.lemon_drink)
            contentDescription = stringResource(id = R.string.content_description_lemon_drink)
            text = stringResource(id = R.string.lemon_drink)
            action = { currentStep = 4 }
        }
        else -> {
            painter = painterResource(id = R.drawable.lemon_restart)
            contentDescription = stringResource(id = R.string.content_description_lemon_restart)
            text = stringResource(id = R.string.lemon_restart)
            action = { currentStep = 1 }
        }
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Image(
                painter = painter,
                contentDescription = contentDescription,
                modifier = Modifier
                    .size(200.dp)
                    .clip(shape = RoundedCornerShape(40.dp))
                    .background(
                        color = Color(0xFFD2E7DB),
                        //color = Color(red = 105, green = 205, blue = 216),
                        shape = RoundedCornerShape(40.dp)
                    )
                    .clickable(onClick = action)
            )
            Spacer(modifier = Modifier.padding(16.dp))
            Text(
                text= text,
                fontSize = 18.sp
            )
        }
    }
}