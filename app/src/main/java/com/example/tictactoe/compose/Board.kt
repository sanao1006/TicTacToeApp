package com.example.tictactoe

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.tictactoe.compose.Cell
import com.example.tictactoe.compose.PlayerNameLabel
import com.example.tictactoe.data.GameState

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun Board(viewModel: PlayerViewModel = viewModel()) {
    val boardState by viewModel.boardState.collectAsState()
    val nowPlayer by viewModel.nowPlayer.collectAsState()
    val context = LocalContext.current
    val winState by viewModel.winState.collectAsState()
    val gameState by viewModel.gameState.collectAsState()

    Column {
        boardState.forEachIndexed { rIndex, row ->
            Row {
                row.forEachIndexed { cIndex, cell ->
                    Cell(
                        cell = cell,
                        modifier = Modifier
                            .clickable(
                                enabled = when (gameState) {
                                    GameState.IN_PROGRESS -> {
                                        true
                                    }
                                    GameState.FINISH -> {
                                        false
                                    }
                                }
                            ) {
//                        セルの状態書き換え
//                        プレイヤーの変更
                                if (!viewModel.isCellChosen(rIndex, cIndex)) {
                                    viewModel.onCellClicked(rIndex, cIndex)
                                } else {
                                    Toast
                                        .makeText(context, "選択できません", Toast.LENGTH_SHORT)
                                        .show()
                                }
                            }

                    )
                }
            }
        }
        if (gameState == GameState.FINISH) {

            Text(text = winState.toString())
        }

        PlayerNameLabel(nowPlayer = nowPlayer)
    }
}
