package com.example.ixuba.chessgame

enum class PieceType() {king, queen, rook, knight, bishop, pawn}

enum class Side() {white, black}

//enum class GameMode() {oneVsOneLocal, Online}

class GameShowData(var gameData: GameData,
                   var mySide: Side,
                   var possibleSteps: ArrayList<Position> = ArrayList<Position>(),
                   var flipped: Boolean = false)