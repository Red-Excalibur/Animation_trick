package com.example.project0003ori



import android.os.Bundle

import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColor
import androidx.compose.animation.core.*
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha

import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            Row(
                modifier = Modifier.fillMaxSize().background(Color.Black),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.Top

            ) {
                var isAnimation2Visible by remember {
                    mutableStateOf(false)
                }
                MyAnimation()
                MyAnimation1()

                Column() {
                    //here

                    if (isAnimation2Visible){ MyAnimation2()}


                    Button(onClick = {
                        isAnimation2Visible =!isAnimation2Visible
                    }) {
                        Text(text = "Go 2")
                    }
                }
            }


        }
    }




}

class States {
    companion object {
        enum class MyStates { start,end}
    }
}
class States1 {
    companion object {
        enum class MyStates1 { start1,end1}
    }
}
@Composable
fun MyAnimation(){
    var state by remember {
        mutableStateOf(States.Companion.MyStates.start)
    }
    val position :Dp by animateDpAsState(
        targetValue = if (state == States.Companion.MyStates.start)
            0.dp
        else
            300.dp
        ,
        animationSpec = spring(
            dampingRatio = 0.2f
        )
    )


    Column(
        modifier = Modifier,
    ) {

        Box(
            modifier = Modifier
                .fillMaxSize(0.3f)
                .absoluteOffset(y = position)
        ){
            Box(

                modifier = Modifier
                    .background(Color.Red)
                    .fillMaxSize()
            ) {

            }
        }

        Button(onClick = { if (state == States.Companion.MyStates.start) {
            state =States.Companion.MyStates.end
        }
        else {
            state = States.Companion.MyStates.start
        }


        }) {
            Text(text = "Go")
        }
    }

}

/////
@Composable
fun MyAnimation1(){
    var state by remember {
        mutableStateOf(States1.Companion.MyStates1.start1)
    }
    val position :Dp by animateDpAsState(
        targetValue = if (state == States1.Companion.MyStates1.start1)
            0.dp
        else
            300.dp
        ,
        animationSpec = tween(2000)
    )


    Column(
        modifier = Modifier,
    ) {

        Box(
            modifier = Modifier
                .fillMaxSize(0.3f)
                .absoluteOffset(y = position)
        ){
            Box(

                modifier = Modifier
                    .background(Color.Blue)
                    .fillMaxSize()
            ) {

            }
        }

        Button(onClick = { if (state == States1.Companion.MyStates1.start1) {
            state =States1.Companion.MyStates1.end1
        }
        else {
            state = States1.Companion.MyStates1.start1
        }


        }) {
            Text(text = "Go 1")
        }
    }

}

@Composable
fun MyAnimation2 (){

    val infiniteTransition = rememberInfiniteTransition()

    val size by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 0.5f,
        animationSpec = infiniteRepeatable(
            animation = tween(2000) ,
            repeatMode = RepeatMode.Reverse
        )
    )
    val color by infiniteTransition.animateColor(
        initialValue = Color.Red,
        targetValue = Color.Yellow,
        animationSpec = infiniteRepeatable(
            animation = tween(1000) ,
            repeatMode = RepeatMode.Reverse
        )
    )

    Box( Modifier) {
        Box(
            modifier = Modifier
                .fillMaxSize(size)
                .background(color)
        ) {

        }
    }
}

