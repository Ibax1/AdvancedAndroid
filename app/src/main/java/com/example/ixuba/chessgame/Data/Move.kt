package com.example.ixuba.chessgame.Data

// This class is only used for story. In other places only class Position is used to represent possible steps for
// certain piece
class Move(val from: Position, val to: Position, var deletedPiece: Piece? = null)