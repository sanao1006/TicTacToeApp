package com.example.tictactoe

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
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
}