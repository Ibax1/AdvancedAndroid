package com.example.ixuba.chessgame

import android.graphics.Canvas

class GamePresenter(var gameView: GameView) {

    var gameData: GameData = GameData()

    fun gameStarted() {
        gameView.showGame(GameShowData(this.gameData, Side.white))
    }

    fun onGameSurfaceActionDown(position: Position) {

    }

    fun onGameSurfaceActionUp(position: Position) {

    }


}