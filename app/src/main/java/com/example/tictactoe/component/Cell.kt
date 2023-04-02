package com.example.tictactoe.component

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.tictactoe.CellState
import kotlinx.coroutines.flow.MutableStateFlow

@Composable
fun Cell(
    cell: MutableStateFlow<CellState>,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
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