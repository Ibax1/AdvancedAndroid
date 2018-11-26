package com.example.ixuba.chessgame.Main

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.ixuba.chessgame.Game.GameActivity
import com.example.ixuba.chessgame.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MainView {

    val presenter: MainPresenter = MainPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        playButton.setOnClickListener { startNewGame()}
    }

    override fun startNewGame() {
        presenter.startNewGame()
        val intent = Intent(this, GameActivity::class.java).apply{}
        startActivity(intent)
    }
}
