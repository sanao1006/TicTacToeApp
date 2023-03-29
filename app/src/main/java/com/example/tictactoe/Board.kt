package com.example.tictactoe

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun Board() {
    Column {
        for (i in 0 until 3) {
            Row {
                for (j in 0 until 3) {
                    Box(
                        modifier = Modifier
                            .width(80.dp)
                            .height(80.dp)
                            .border(1.dp, Color.Black)
                    ) {
                        Button(
                            onClick = { /*TODO*/ },
                            modifier = Modifier.fillMaxSize(),
                            colors = ButtonDefaults.buttonColors(
                                backgroundColor = Color.Cyan
                            )
                        ) {

                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun BoardPreview(){
    Board()
}