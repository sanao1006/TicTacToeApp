package com.example.tictactoe

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class PlayerViewModel : ViewModel() {
    val nowCellState: MutableStateFlow<CellState> = MutableStateFlow(CellState.EMPTY)
    val nowPlayer: MutableStateFlow<Player> = MutableStateFlow(Player.ONE)

    val _isChosen: MutableStateFlow<List<MutableList<Boolean>>> =
        MutableStateFlow(List(3) { MutableList(3) { false } })
    val isChosen: StateFlow<List<MutableList<Boolean>>> = _isChosen

    fun isCellChosen(row: Int, col: Int): Boolean {
        return _isChosen.value[row][col]
    }

    // Each parameter of `boardState
    // 0: No one selected
    // 1: Player1 has selected
    // 2: Player2 is selecting
    private val _boardState: MutableStateFlow<List<MutableList<MutableStateFlow<CellState>>>> =
        MutableStateFlow(
            listOf(
                mutableListOf(
                    MutableStateFlow(CellState.EMPTY),
                    MutableStateFlow(CellState.EMPTY),
                    MutableStateFlow(CellState.EMPTY)
                ),
                mutableListOf(
                    MutableStateFlow(CellState.EMPTY),
                    MutableStateFlow(CellState.EMPTY),
                    MutableStateFlow(CellState.EMPTY)
                ),
                mutableListOf(
                    MutableStateFlow(CellState.EMPTY),
                    MutableStateFlow(CellState.EMPTY),
                    MutableStateFlow(CellState.EMPTY)
                )
            )

        )

    val boardState: StateFlow<List<List<MutableStateFlow<CellState>>>>
        get() = _boardState.asStateFlow()

    fun onCellClicked(row: Int, col: Int) {
            _isChosen.value[row][col] = true
            val newState = when (nowPlayer.value) {
                Player.ONE -> CellState.CIRCLE
                Player.TWO -> CellState.CROSS
            }
            nowCellState.value = newState
            nowPlayer.value = when (nowPlayer.value) {
                Player.ONE -> Player.TWO
                Player.TWO -> Player.ONE
            }
            _boardState.value = _boardState.value.mapIndexed { rIndex, rowList ->
                rowList.mapIndexed { cIndex, cell ->
                    if (rIndex == row && cIndex == col) {
                        MutableStateFlow(newState)
                    } else {
                        cell
                    }
                }.toMutableList()
        }

    }

}

