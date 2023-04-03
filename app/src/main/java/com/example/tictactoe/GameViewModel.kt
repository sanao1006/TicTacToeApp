package com.example.tictactoe

import androidx.lifecycle.ViewModel
import com.example.tictactoe.data.CellState
import com.example.tictactoe.data.Player
import com.example.tictactoe.data.WinState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class PlayerViewModel : ViewModel() {
    val nowCellState: MutableStateFlow<CellState> = MutableStateFlow(CellState.EMPTY)
    val _nowPlayer: MutableStateFlow<Player> = MutableStateFlow(Player.ONE)
    val nowPlayer: StateFlow<Player> = _nowPlayer

    val _isChosen: MutableStateFlow<List<MutableList<Boolean>>> =
        MutableStateFlow(List(3) { MutableList(3) { false } })
    val isChosen: StateFlow<List<MutableList<Boolean>>> = _isChosen

    val _winState: MutableStateFlow<WinState> = MutableStateFlow(WinState.Draw)
    val winState: StateFlow<WinState> = _winState

    fun isCellChosen(row: Int, col: Int): Boolean {
        return _isChosen.value[row][col]
    }

    // Each parameter of `boardState
    // 0: No one selected
    // 1: Player1 has selected
    // 2: Player2 is selecting
    private val _boardState: MutableStateFlow<List<MutableList<MutableStateFlow<CellState>>>> =
        MutableStateFlow(
            List(3) { MutableList(3) { MutableStateFlow(CellState.EMPTY) } }
        )

    val boardState: StateFlow<List<List<StateFlow<CellState>>>>
        get() = _boardState.asStateFlow()

    fun onCellClicked(row: Int, col: Int) {
        _isChosen.value[row][col] = true
        val newState = when (nowPlayer.value) {
            Player.ONE -> CellState.CIRCLE
            Player.TWO -> CellState.CROSS
        }
        nowCellState.value = newState
        _nowPlayer.value = when (nowPlayer.value) {
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
        checkBattleState()

    }

    fun checkBattleState(){
        if(_boardState.value.any { col -> col.all { cellItem -> cellItem.value == CellState.CIRCLE } }){ _winState.value = WinState.Player1Win}
        if(transpose(_boardState.value).any { col -> col.all { cellItem -> cellItem.value == CellState.CIRCLE   } }){ _winState.value = WinState.Player1Win  }
        if(_boardState.value.any { col -> col.all { cellItem -> cellItem.value == CellState.CROSS } }){ _winState.value = WinState.Player2Win}
        if(transpose(_boardState.value).any { col -> col.all { cellItem -> cellItem.value == CellState.CROSS   } }){ _winState.value = WinState.Player2Win  }
    }

}

fun <T> transpose(list: List<List<T>>): List<List<T>> =
    list.first().mapIndexed { index, _ ->
        list.map { row -> row[index] }
    }