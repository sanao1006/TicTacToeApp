package com.example.tictactoe

import android.annotation.SuppressLint
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.rounded.Close
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun Board(viewModel: PlayerViewModel = viewModel()) {
    val boardState by viewModel.boardState.collectAsState()

    Column {
        boardState.forEachIndexed { rIndex, row ->
            Row {
                row.forEachIndexed { cIndex, cell ->
                    Box(modifier = Modifier
                        .clickable {
//                        セルの状態書き換え
//                        プレイヤーの変更
                            if (!viewModel.isCellChosen(rIndex, cIndex)) {
                                viewModel.onCellClicked(rIndex, cIndex)
                            } else{
//                                TODO 警告を出す
                            }
                        }
                        .width(80.dp)
                        .height(80.dp)
                        .border(width = 1.dp, color = Color.Black)
                    ) {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            when (cell.value) {
//                          セルの状態に応じてアイコンを表示
                                CellState.CIRCLE -> {
                                    Icon(Icons.Default.CheckCircle, contentDescription = null)
                                }
                                CellState.CROSS -> {
                                    Icon(Icons.Default.Close, contentDescription = null)
                                }
                                CellState.EMPTY -> {}
                            }

                        }
                    }
                }
            }
        }
    }
}
