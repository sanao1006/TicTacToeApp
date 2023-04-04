package com.example.tictactoe.compose

import androidx.compose.foundation.layout.*
import androidx.compose.material.AlertDialog
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.tictactoe.GameViewModel
import com.example.tictactoe.data.WinState
import androidx.lifecycle.viewmodel.compose.viewModel
@Composable
fun WinPlayerDialog(
    winState: WinState,
    viewModel: GameViewModel = viewModel()
) {
    AlertDialog(
        onDismissRequest = { },
        title = {Text("Winner...")},
        text = {
            Text(
                text = when (winState) {
                    WinState.Player1Win -> "Player1 !!!"
                    WinState.Player2Win -> "Player2 !!!"
                    WinState.Draw -> "DRAW !!!"
                }
            )
        },
        buttons = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(all = 12.dp),
                horizontalArrangement = Arrangement.End
            ) {
                TextButton(onClick = { viewModel.resetBoard()  }) {
                    Text(text = "もう一回遊ぶ")
                }
            }
        }
    )


}


@Preview
@Composable
fun Pre(){
    WinPlayerDialog(winState = WinState.Player2Win)
}
