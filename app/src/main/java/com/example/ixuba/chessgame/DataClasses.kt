package com.example.ixuba.chessgame

enum class PieceType() {king, queen, rook, knight, bishop, pawn}

enum class Side() {white, black}

class GameData(var story: ArrayList<String> = ArrayList<String>(), var pieces: ArrayList<ChessPiece> = getDefaultPieces())

