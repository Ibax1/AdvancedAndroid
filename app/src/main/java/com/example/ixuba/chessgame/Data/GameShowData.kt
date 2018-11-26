package com.example.ixuba.chessgame.Data

class GameShowData(var gameData: GameData,
                   var possibleSteps: ArrayList<Position> = ArrayList<Position>(),
                   var flipped: Boolean = false)