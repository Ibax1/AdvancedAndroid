package com.example.ixuba.chessgame.Game.GamePresenters

import android.util.Log
import com.example.ixuba.chessgame.Data.GameData
import com.example.ixuba.chessgame.Data.GameShowData
import com.example.ixuba.chessgame.Data.Position
import com.example.ixuba.chessgame.Data.Side
import com.example.ixuba.chessgame.Game.GameContract

open class GamePresenter(var gameContract: GameContract) {

    var gameData: GameData = GameData()
    var positionOnDown: Position? = null

    fun gameStarted() {
        showGame()
    }

    fun onGameSurfaceActionDown(position: Position) {
        positionOnDown = position
        showGameWithPossibleMoves(position)
    }

    fun onGameSurfaceActionUp(position: Position) {
        if(gameData.proceedOnMoveIfPossible(positionOnDown!!, position)) {
            if(gameData.isCheckMated(gameData.currentSide)) {
                showGameEnd()
            }
        }
        showGame()
    }

    private fun showGame() {
        gameContract.showGame(
            GameShowData(
                this.gameData
            )
        )
    }

    private fun showGameWithPossibleMoves(position: Position) {
        gameContract.showGame(
            GameShowData(
                this.gameData,
                this.gameData.getTrulyPossibleMoves(position)))
    }

    private fun showGameEnd() {
        Log.d("myTag", "End")
    }

}