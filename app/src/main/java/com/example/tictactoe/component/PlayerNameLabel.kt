package com.example.tictactoe.component

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.example.tictactoe.Player

@Composable
fun PlayerNameLabel(nowPlayer: Player) {
    Text(
        text = "プレイヤー ${
            when (nowPlayer) {
                Player.ONE -> {
                    "1"
                }
                Player.TWO -> {
                    "2"
                }
            }
        } の番です"
    )
}
