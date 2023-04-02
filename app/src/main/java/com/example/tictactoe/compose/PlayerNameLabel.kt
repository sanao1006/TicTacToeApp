package com.example.tictactoe.compose

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.example.tictactoe.data.Player

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
