package com.example.ixuba.chessgame.Game

import com.example.ixuba.chessgame.Data.GameShowData

interface GameContract {
    fun showGame(showData: GameShowData)
}