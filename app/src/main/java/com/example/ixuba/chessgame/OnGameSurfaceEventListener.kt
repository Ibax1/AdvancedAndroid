package com.example.ixuba.chessgame

interface OnGameSurfaceEventListener {

    fun onGameSurfaceActionDown(position: Position)
    fun onGameSurfaceActionUp(position: Position)
}