package com.example.ixuba.chessgame

class ChessPiece(val side: Side, val type: PieceType, var position: Position) {

    val resource: Int = when(this.side) {
        Side.white -> when(this.type) {
            PieceType.king -> R.drawable.white_king
            PieceType.queen -> R.drawable.white_queen
            PieceType.bishop -> R.drawable.white_bishop
            PieceType.knight -> R.drawable.white_knight
            PieceType.rook -> R.drawable.white_rook
            PieceType.pawn -> R.drawable.white_pawn
        }

        Side.black -> when(this.type) {
            PieceType.king -> R.drawable.black_king
            PieceType.queen -> R.drawable.black_queen
            PieceType.bishop -> R.drawable.black_bishop
            PieceType.knight -> R.drawable.black_knight
            PieceType.rook -> R.drawable.black_rook
            PieceType.pawn -> R.drawable.black_pawn
        }
    }
}

fun getDefaultPieces(): ArrayList<ChessPiece> {
    var result: ArrayList<ChessPiece> = ArrayList<ChessPiece>()

    result.add(ChessPiece(Side.white, PieceType.rook, Position('a', '1')))
    result.add(ChessPiece(Side.white, PieceType.knight, Position('b', '1')))
    result.add(ChessPiece(Side.white, PieceType.bishop, Position('c', '1')))
    result.add(ChessPiece(Side.white, PieceType.queen, Position('d', '1')))
    result.add(ChessPiece(Side.white, PieceType.king, Position('e', '1')))
    result.add(ChessPiece(Side.white, PieceType.bishop, Position('f', '1')))
    result.add(ChessPiece(Side.white, PieceType.knight, Position('g', '1')))
    result.add(ChessPiece(Side.white, PieceType.rook, Position('h', '1')))

    result.add(ChessPiece(Side.black, PieceType.rook, Position('a', '8')))
    result.add(ChessPiece(Side.black, PieceType.knight, Position('b', '8')))
    result.add(ChessPiece(Side.black, PieceType.bishop, Position('c', '8')))
    result.add(ChessPiece(Side.black, PieceType.queen, Position('d', '8')))
    result.add(ChessPiece(Side.black, PieceType.king, Position('e', '8')))
    result.add(ChessPiece(Side.black, PieceType.bishop, Position('f', '8')))
    result.add(ChessPiece(Side.black, PieceType.knight, Position('g', '8')))
    result.add(ChessPiece(Side.black, PieceType.rook, Position('h', '8')))

    for(letter in 'a'..'h') {
        result.add(ChessPiece(Side.white, PieceType.pawn, Position(letter, '2')))
        result.add(ChessPiece(Side.black, PieceType.pawn, Position(letter, '7')))
    }

    return result
}