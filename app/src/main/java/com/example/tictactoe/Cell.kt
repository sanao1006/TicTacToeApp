package com.example.tictactoe

data class Cell(
    val row: Int,
    val col: Int,
    val cellState: CellState = CellState.EMPTY
)
