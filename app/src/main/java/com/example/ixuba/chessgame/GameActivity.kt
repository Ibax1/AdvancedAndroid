package com.example.ixuba.chessgame

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_game.*


class GameActivity : AppCompatActivity(), GameView {

    val presenter: GamePresenter = GamePresenter(this)
    var gameData: GameData? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)
        presenter.gameStarted()
    }

    override fun showGame(gameData: GameData) {
        this.gameData = gameData
        gameSurface.drawGame(this.gameData!!)
    }


}
