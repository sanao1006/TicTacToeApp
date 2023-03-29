package com.example.tictactoe

import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.rounded.AddCircle
import androidx.compose.material.icons.rounded.Close
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.lifecycle.ViewModel

class PlayerViewModel : ViewModel() {
    val nowPlayer: MutableState<Player> = mutableStateOf(Player.ONE)

    // Each parameter of `boardState
    // 0: No one selected
    // 1: Player1 has selected
    // 2: Player2 is selecting
    val boardState: MutableState<List<MutableList<Int>>> = mutableStateOf(
        listOf(
            mutableListOf(0, 0, 0),
            mutableListOf(0, 0, 0),
            mutableListOf(0, 0, 0)
        )
    )

    fun onCellClicked(row: Int, col: Int) {
        when (nowPlayer.value) {
            Player.ONE -> {
                boardState.value[row][col] == 1
                nowPlayer.value = Player.TWO
            }
            Player.TWO -> {
                boardState.value[row][col] == 2
                nowPlayer.value = Player.ONE
            }
        }
    }

    fun putIcon(row: Int, col: Int): ImageVector {
        return if (boardState.value[row][col] == 1) {
            Icons.Filled.CheckCircle
        } else {
            Icons.Rounded.Close
        }

    }
}