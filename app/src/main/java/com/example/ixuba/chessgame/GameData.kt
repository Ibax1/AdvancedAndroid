package com.example.ixuba.chessgame

class GameData(var story: ArrayList<Pair<Position, Position>> = ArrayList<Pair<Position, Position>>(),
               var pieces: ArrayList<ChessPiece> = getDefaultPieces()) {


    fun isTherePieceAtPosition(position: Position) : Boolean {
        for(piece in this.pieces) {
            if(piece.position.equals(position)) {
                return true
            }
        }

        return false
    }

    fun getPieceAtPosition(position: Position): ChessPiece? {
        for(piece in this.pieces) {
            if(piece.position.equals(position)) {
                return piece
            }
        }

        return null
    }

    fun checkIfPositionIsAttacked(position: Position) : Boolean {
        TODO()
    }

    fun givePossibleMovesForStep(step: Pair<Position, Position>) : ArrayList<Position>? {
        TODO()
    }

    fun doStep(step: Pair<Position, Position>) {
        TODO()
    }

    fun undoStep() {
        TODO()
    }
}