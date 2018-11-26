package com.example.ixuba.chessgame.Data

import com.example.ixuba.chessgame.R

class Piece(val side: Side, val type: PieceType, var position: Position) {

    val resource: Int
        get() {
            return when(this.side) {
                Side.white -> when (this.type) {
                    PieceType.king -> R.drawable.white_king
                    PieceType.queen -> R.drawable.white_queen
                    PieceType.bishop -> R.drawable.white_bishop
                    PieceType.knight -> R.drawable.white_knight
                    PieceType.rook -> R.drawable.white_rook
                    PieceType.pawn -> R.drawable.white_pawn
                }

                Side.black -> when (this.type) {
                    PieceType.king -> R.drawable.black_king
                    PieceType.queen -> R.drawable.black_queen
                    PieceType.bishop -> R.drawable.black_bishop
                    PieceType.knight -> R.drawable.black_knight
                    PieceType.rook -> R.drawable.black_rook
                    PieceType.pawn -> R.drawable.black_pawn
                }
            }
    }

    val linesThisCanReach: ArrayList<Line>
        get() {
            return getLinesThisCouldReach(this.type)
        }

    val linesPawnCouldReachThis: ArrayList<Line>
        get() {
            return Piece(this.side.opposite, this.type, this.position).getPawnLines()
        }

    override fun equals(other: Any?): Boolean {
        return other is Piece && this.side == other.side && this.type == other.type && this.position == other.position
    }


    fun getLinesThisCouldReach(pieceType: PieceType) : ArrayList<Line> {
        return when(pieceType) {
            PieceType.king -> getKingLines()
            PieceType.queen -> getQueenLines()
            PieceType.rook -> getRookLines()
            PieceType.knight -> getKnightLines()
            PieceType.bishop -> getBishopLines()
            PieceType.pawn -> getPawnLines()
        }
    }


    private fun getKingLines() : ArrayList<Line> {

        val result: ArrayList<Line> = ArrayList<Line>()
        for(i in -1..1) {
            for(j in -1..1) {
                if((i != 0 || j !=0 ) && ((position.x + i) in 0..7) && ((position.y + j) in 0..7)) {
                    val line: Line = Line()
                    line.add(Position(position.x + i, position.y + j))
                    result.add(line)
                }
            }
        }

        return result
    }

    private fun getQueenLines() : ArrayList<Line> {
        val result: ArrayList<Line> = ArrayList<Line>()
        result.add(position.getLine(Direction.N))
        result.add(position.getLine(Direction.S))
        result.add(position.getLine(Direction.E))
        result.add(position.getLine(Direction.W))
        result.add(position.getLine(Direction.NE))
        result.add(position.getLine(Direction.SW))
        result.add(position.getLine(Direction.NW))
        result.add(position.getLine(Direction.SE))

        return result
    }

    private fun getRookLines() : ArrayList<Line> {
        val result: ArrayList<Line> = ArrayList<Line>()
        result.add(position.getLine(Direction.N))
        result.add(position.getLine(Direction.S))
        result.add(position.getLine(Direction.E))
        result.add(position.getLine(Direction.W))

        return result
    }

    private fun getKnightLines() : ArrayList<Line> {
        val result: ArrayList<Line> = ArrayList<Line>()
        for(i in -2..2) {
            for(j in -2..2) {
                if((i != j) && (-i != j) && (i != 0) && (j != 0)
                    && (position.x + i) in 0..7 && (position.y + j) in 0..7)
                {
                    val line: Line = Line()
                    line.add(Position(position.x + i, position.y + j))
                    result.add(line)
                }
            }
        }

        return result
    }

    private fun getBishopLines() : ArrayList<Line> {
        val result: ArrayList<Line> = ArrayList<Line>()
        result.add(position.getLine(Direction.NE))
        result.add(position.getLine(Direction.SW))
        result.add(position.getLine(Direction.NW))
        result.add(position.getLine(Direction.SE))

        return result
    }

    private fun getPawnLines() : ArrayList<Line> {

        val result: ArrayList<Line> = ArrayList<Line>()
        var line: Line
        when(side) {
            Side.white -> {
                if((position.x + 1) in 0..7 && (position.y + 1) in 0..7) {
                    line = Line()
                    line.add(Position(position.x + 1, position.y + 1))
                    result.add(line)
                }
                if((position.x - 1) in 0..7 && (position.y + 1) in 0..7) {
                    line = Line()
                    line.add(Position(position.x - 1, position.y + 1))
                    result.add(line)

                }
                if((position.x) in 0..7 && (position.y + 1) in 0..7) {
                    line = Line()
                    line.add(Position(position.x, position.y + 1))
                    result.add(line)
                }
            }
            Side.black -> {
                if((position.x + 1) in 0..7 && (position.y - 1) in 0..7) {
                    line = Line()
                    line.add(Position(position.x + 1, position.y - 1))
                    result.add(line)

                }
                if((position.x - 1) in 0..7 && (position.y - 1) in 0..7) {
                    line = Line()
                    line.add(Position(position.x - 1, position.y - 1))
                    result.add(line)

                }
                if((position.x) in 0..7 && (position.y - 1) in 0..7) {
                    line = Line()
                    line.add(Position(position.x, position.y - 1))
                    result.add(line)

                }
            }
        }

        return result
    }
}


fun getDefaultPieces(): ArrayList<Piece> {
    var result: ArrayList<Piece> = ArrayList<Piece>()

    result.add(
        Piece(
            Side.white,
            PieceType.rook,
            Position('a', '1')
        )
    )
    result.add(
        Piece(
            Side.white,
            PieceType.knight,
            Position('b', '1')
        )
    )
    result.add(
        Piece(
            Side.white,
            PieceType.bishop,
            Position('c', '1')
        )
    )
    result.add(
        Piece(
            Side.white,
            PieceType.queen,
            Position('d', '1')
        )
    )
    result.add(
        Piece(
            Side.white,
            PieceType.king,
            Position('e', '1')
        )
    )
    result.add(
        Piece(
            Side.white,
            PieceType.bishop,
            Position('f', '1')
        )
    )
    result.add(
        Piece(
            Side.white,
            PieceType.knight,
            Position('g', '1')
        )
    )
    result.add(
        Piece(
            Side.white,
            PieceType.rook,
            Position('h', '1')
        )
    )

    result.add(
        Piece(
            Side.black,
            PieceType.rook,
            Position('a', '8')
        )
    )
    result.add(
        Piece(
            Side.black,
            PieceType.knight,
            Position('b', '8')
        )
    )
    result.add(
        Piece(
            Side.black,
            PieceType.bishop,
            Position('c', '8')
        )
    )
    result.add(
        Piece(
            Side.black,
            PieceType.queen,
            Position('d', '8')
        )
    )
    result.add(
        Piece(
            Side.black,
            PieceType.king,
            Position('e', '8')
        )
    )
    result.add(
        Piece(
            Side.black,
            PieceType.bishop,
            Position('f', '8')
        )
    )
    result.add(
        Piece(
            Side.black,
            PieceType.knight,
            Position('g', '8')
        )
    )
    result.add(
        Piece(
            Side.black,
            PieceType.rook,
            Position('h', '8')
        )
    )

    for(letter in 'a'..'h') {
        result.add(
            Piece(
                Side.white,
                PieceType.pawn,
                Position(letter, '2')
            )
        )
        result.add(
            Piece(
                Side.black,
                PieceType.pawn,
                Position(letter, '7')
            )
        )
    }

    return result
}