package com.example.ixuba.chessgame

enum class PieceType() {king, queen, rook, knight, bishop, pawn}

enum class Side() {white, black}

class Position(var position: Pair<Char, Char>) {
    val x: Int
    get() = position.first.toInt() - 97


    val y: Int
    get() = position.second.toInt() - 48
}

class ChessPiece(val side: Side, val type: PieceType, var position: Position) {

    val resource: Int = when(this.side) {
        Side.white -> when(this.type) {
            PieceType.king -> 0
            PieceType.queen -> 0
            PieceType.bishop -> 0
            PieceType.knight -> 0
            PieceType.rook -> 0
            PieceType.pawn -> 0
        }

        Side.black -> when(this.type) {
            PieceType.king -> 0
            PieceType.queen -> 0
            PieceType.bishop -> 0
            PieceType.knight -> 0
            PieceType.rook -> 0
            PieceType.pawn -> 0
        }
    }

}

class GameData(var story: ArrayList<String> = ArrayList<String>(), var pieces: ArrayList<ChessPiece> = getDefaultPieces())

fun getDefaultPieces(): ArrayList<ChessPiece> {
    var result: ArrayList<ChessPiece> = ArrayList<ChessPiece>()

    result.add(ChessPiece(Side.white, PieceType.rook, Position(Pair('a', '1'))))
    result.add(ChessPiece(Side.white, PieceType.knight, Position(Pair('b', '1'))))
    result.add(ChessPiece(Side.white, PieceType.bishop, Position(Pair('c', '1'))))
    result.add(ChessPiece(Side.white, PieceType.queen, Position(Pair('d', '1'))))
    result.add(ChessPiece(Side.white, PieceType.king, Position(Pair('e', '1'))))
    result.add(ChessPiece(Side.white, PieceType.bishop, Position(Pair('f', '1'))))
    result.add(ChessPiece(Side.white, PieceType.knight, Position(Pair('g', '1'))))
    result.add(ChessPiece(Side.white, PieceType.rook, Position(Pair('h', '1'))))

    result.add(ChessPiece(Side.black, PieceType.rook, Position(Pair('a', '8'))))
    result.add(ChessPiece(Side.black, PieceType.knight, Position(Pair('b', '8'))))
    result.add(ChessPiece(Side.black, PieceType.bishop, Position(Pair('c', '8'))))
    result.add(ChessPiece(Side.black, PieceType.queen, Position(Pair('d', '8'))))
    result.add(ChessPiece(Side.black, PieceType.king, Position(Pair('e', '8'))))
    result.add(ChessPiece(Side.black, PieceType.bishop, Position(Pair('f', '8'))))
    result.add(ChessPiece(Side.black, PieceType.knight, Position(Pair('g', '8'))))
    result.add(ChessPiece(Side.black, PieceType.rook, Position(Pair('h', '8'))))

    for(letter in 'a'..'h') {
        result.add(ChessPiece(Side.white, PieceType.pawn, Position(Pair(letter, '2'))))
        result.add(ChessPiece(Side.black, PieceType.pawn, Position(Pair(letter, '7'))))
    }

    return result
}