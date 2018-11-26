package com.example.ixuba.chessgame.Game.GameSurface

import com.example.ixuba.chessgame.Data.Position

interface OnGameSurfaceEventListener {

    fun onGameSurfaceActionDown(position: Position)
    fun onGameSurfaceActionUp(position: Position)
}