package com.example.tictactoe

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.tictactoe.component.Cell
import com.example.tictactoe.component.PlayerNameLabel

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun Board(viewModel: PlayerViewModel = viewModel()) {
    val boardState by viewModel.boardState.collectAsState()
    val nowPlayer by viewModel.nowPlayer.collectAsState()
    val context = LocalContext.current

    Column {
        boardState.forEachIndexed { rIndex, row ->
            Row {
                row.forEachIndexed { cIndex, cell ->
                    Cell(
                        cell = cell,
                        modifier = Modifier
                            .clickable {
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
                            .width(80.dp)
                            .height(80.dp)
                    )
                }
            }
        }
        
        PlayerNameLabel(nowPlayer = nowPlayer)
    }
}
