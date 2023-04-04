package com.example.tictactoe

import androidx.lifecycle.ViewModel
import com.example.tictactoe.data.CellState
import com.example.tictactoe.data.GameState
import com.example.tictactoe.data.Player
import com.example.tictactoe.data.WinState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class GameViewModel : ViewModel() {
    val nowCellState: MutableStateFlow<CellState> = MutableStateFlow(CellState.EMPTY)
    val _nowPlayer: MutableStateFlow<Player> = MutableStateFlow(Player.ONE)
    val nowPlayer: StateFlow<Player> = _nowPlayer

    val _gameTurnCount: MutableStateFlow<Int> = MutableStateFlow(1)
    val gameTurnCount: StateFlow<Int> = _gameTurnCount

    val _gameState: MutableStateFlow<GameState> = MutableStateFlow(GameState.IN_PROGRESS)
    val gameState: StateFlow<GameState> = _gameState

    val _isChosen: MutableStateFlow<List<MutableList<Boolean>>> =
        MutableStateFlow(List(3) { MutableList(3) { false } })

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
        _gameTurnCount.value = _gameTurnCount.value + 1
        checkBattleState()

    }

    fun checkBattleState() {
        boardCheck()
        if (_gameTurnCount.value == 10) {
            _gameState.value = GameState.FINISH
        }

    }

    fun boardCheck() {
        val winningConditions = listOf(
            listOf(0 to 0, 0 to 1, 0 to 2),
            listOf(1 to 0, 1 to 1, 1 to 2),
            listOf(2 to 0, 2 to 1, 2 to 2),
            listOf(0 to 0, 1 to 0, 2 to 0),
            listOf(0 to 1, 1 to 1, 2 to 1),
            listOf(0 to 2, 1 to 2, 2 to 2),
            listOf(0 to 0, 1 to 1, 2 to 2),
            listOf(0 to 2, 1 to 1, 2 to 0),
        )

        val winningPlayer = when {
            winningConditions.any { condition ->
                condition.all { (x, y) -> _boardState.value[x][y].value == CellState.CIRCLE }
            } -> WinState.Player1Win
            winningConditions.any { condition ->
                condition.all { (x, y) -> _boardState.value[x][y].value == CellState.CROSS }
            } -> WinState.Player2Win
            else -> null
        }

        if (winningPlayer != null) {
            _winState.value = winningPlayer
            _gameState.value = GameState.FINISH
        }
    }

    fun resetBoard(){
        _boardState.value.forEach { row -> row.forEach { cell -> cell.value = CellState.EMPTY } }
        _isChosen.value.forEach { row -> row.fill(false)}
        _winState.value = WinState.Draw
        _gameState.value = GameState.IN_PROGRESS
        _gameTurnCount.value = 1
        _nowPlayer.value = Player.ONE
    }

}

fun <T> transpose(list: List<List<T>>): List<List<T>> =
    list.first().mapIndexed { index, _ ->
        list.map { row -> row[index] }
    }