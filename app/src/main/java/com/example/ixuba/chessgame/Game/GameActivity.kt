package com.example.ixuba.chessgame.Game

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.ixuba.chessgame.*
import com.example.ixuba.chessgame.Data.*
import com.example.ixuba.chessgame.Game.GamePresenters.GamePresenter
import com.example.ixuba.chessgame.Game.GameSurface.OnGameSurfaceEventListener
import kotlinx.android.synthetic.main.activity_game.*


class GameActivity : AppCompatActivity(), GameContract,
    OnGameSurfaceEventListener {

    val presenter: GamePresenter =
        GamePresenter(this)
    var gameShowData: GameShowData? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        gameSurface.setOnGameSurfaceEventListener(this)
        presenter.gameStarted()
    }

    override fun showGame(gameShowData: GameShowData) {
        this.gameShowData = gameShowData
        gameSurface.drawGame(this.gameShowData!!)
    }

    override fun onGameSurfaceActionDown(position: Position) {
        presenter.onGameSurfaceActionDown(position)
    }

    override fun onGameSurfaceActionUp(position: Position) {
        presenter.onGameSurfaceActionUp(position)
    }
}
