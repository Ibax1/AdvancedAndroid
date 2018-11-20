package com.example.ixuba.chessgame

import android.graphics.Canvas

class GamePresenter(var gameView: GameView) {


    fun gameStarted() {
        val gameData: GameData = GameData()
        gameView.showGame(gameData)
    }

    fun stepMade(gameData: GameData) {

    }
}