package com.example.tictactoe

import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.rounded.AddCircle
import androidx.compose.material.icons.rounded.Close
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

typealias NowCellState = MutableStateFlow<CellState>

class PlayerViewModel : ViewModel() {
    val _nowCellState: MutableStateFlow<CellState> = MutableStateFlow(CellState.EMPTY)
    val nowCellState: StateFlow<CellState>
        get() = _nowCellState.asStateFlow()

    val nowPlayer: MutableStateFlow<Player> = MutableStateFlow(Player.ONE)

    // Each parameter of `boardState
    // 0: No one selected
    // 1: Player1 has selected
    // 2: Player2 is selecting
    private val _boardState: MutableStateFlow<List<MutableList<StateFlow<CellState>>>> = MutableStateFlow(
        listOf(
            mutableListOf(nowCellState, nowCellState, nowCellState),
            mutableListOf(nowCellState, nowCellState, nowCellState),
            mutableListOf(nowCellState, nowCellState, nowCellState)
        )

    )

    val boardState: StateFlow<List<MutableList<StateFlow<CellState>>>>
        get() = _boardState.asStateFlow()

    fun onCellClicked(row: Int, col: Int) {
        when (nowPlayer.value) {
            Player.ONE -> {
                _nowCellState.value = CellState.CIRCLE
                _boardState.value[row][col] = nowCellState
                nowPlayer.value = Player.TWO
            }
            Player.TWO -> {
                _nowCellState.value = CellState.CROSS
                _boardState.value[row][col] = nowCellState
                nowPlayer.value = Player.ONE
            }
        }
    }

}