package com.example.ixuba.chessgame.Data

import kotlin.collections.ArrayList

class GameData(var story: ArrayList<Move> = ArrayList<Move>(),
               var pieces: ArrayList<Piece> = getDefaultPieces()
) {

    val currentSide: Side
        get() {
            if(story.size % 2 == 0) {
                return Side.white
            } else {
                return Side.black
            }
        }

    fun copy() : GameData {
        return GameData(this.story, this.pieces)
    }

    fun isTherePieceAt(position: Position) : Boolean {
        for(piece in this.pieces) {
            if(piece.position.equals(position)) {
                return true
            }
        }

        return false
    }

    fun getPiece(position: Position): Piece? {
        for(piece in this.pieces) {
            if(piece.position.equals(position)) {
                return piece
            }
        }

        return null
    }

    fun getPiece(type: PieceType, side: Side) : Piece? {
        for(piece in this.pieces) {
            if(piece.type == type && piece.side == side) {
                return piece
            }
        }

        return null
    }


    fun isCheckMated(side: Side) : Boolean {
        val trulyPossibleMoves: ArrayList<Position> = ArrayList<Position>()
        val pieces: ArrayList<Piece> = ArrayList<Piece>()
        pieces.addAll(this.pieces)
        for(piece in pieces) {
            if(piece.side == side) {
                trulyPossibleMoves.addAll(getTrulyPossibleMoves(piece))
            }
        }

        return trulyPossibleMoves.isEmpty()
    }

    fun isChecked(side: Side) : Boolean {
        return isAttacked(getPiece(PieceType.king, side)!!)
    }



    fun moveThroughHistory() {
        pieces = getDefaultPieces()
        for(move in story) {
            makeMove(move)
        }
    }

    fun proceedOnMoveIfPossible(from: Position, to: Position) : Boolean {

        if(isMovePossible(from, to)){
            makeMove(getPiece(from)!!, to)
            return true
        }
        return false
    }

    fun isMovePossible(from: Position, to: Position) : Boolean{
        val piece = getPiece(from)
        if(piece == null) {
            return false
        }
        val trulyPossibleMoves = getTrulyPossibleMoves(piece)
        if (trulyPossibleMoves.contains(to)) {
            return true
        }
        return false
    }

    private fun makeMove(piece: Piece, to: Position) {
        story.add(Move(piece.position, to, deletePiece(to)))
        movePiece(piece, to)
    }

    private fun makeMove(move: Move) {
        val piece = getPiece(move.from)
        if(piece != null) {
            makeMove(piece, move.to)
        }
    }

    private fun undoMove() {
        if(story.isEmpty()) {
            return
        }

        val move = story.last()
        story.removeAt(story.lastIndex)

        movePiece(getPiece(move.to)!!, move.from)
        if(move.deletedPiece != null) {
            pieces.add(move.deletedPiece!!)
        }

    }

    private fun movePiece( piece: Piece, to: Position) {
        val index = pieces.indexOf(piece)
        pieces[index].position = to
    }

    private fun deletePiece(position: Position) : Piece?  {
        val pieceToDelete = getPiece(position)
        if(pieceToDelete != null) {
            pieces.remove(pieceToDelete)
        }

        return pieceToDelete
    }


    fun getTrulyPossibleMoves(position: Position) : ArrayList<Position> {
        val piece: Piece? = getPiece(position)
        if(piece != null) {
          return getTrulyPossibleMoves(piece)
        }
        return ArrayList<Position>()
    }

    fun getTrulyPossibleMoves(piece: Piece) : ArrayList<Position> {
        if(piece.side != currentSide) {
            return ArrayList<Position>()
        }

        val result: ArrayList<Position> = ArrayList<Position>()
        val potentialMoves = getPossibleMoves(piece)
        val gameDataTemporary: GameData = this.copy()
        for(move in potentialMoves) {
            gameDataTemporary.makeMove(piece, move)
            if(! gameDataTemporary.isChecked(piece.side)) {
                result.add(move)
            }
            gameDataTemporary.undoMove()
        }

        return result
    }

    fun getPossibleMoves(position: Position) : ArrayList<Position> {
        val piece: Piece? = getPiece(position)
        if (piece != null) {
            return getPossibleMoves(piece)
        }
        return ArrayList<Position>()
    }

    fun getPossibleMoves(piece: Piece) : ArrayList<Position> {
        if(piece.type == PieceType.pawn) {
            return getPawnPossibleMoves(piece)
        } else {
            return getNonPawnPossibleMoves(piece)
        }
    }

    private fun getPawnPossibleMoves(piece: Piece) : ArrayList<Position> {
        val result: ArrayList<Position> = ArrayList<Position>()

        for(line in piece.linesThisCanReach) {
            val potentialPosition: Position = line.positions.first()
            val pieceAtPotentialPosition: Piece? = getPiece(potentialPosition)

            if(potentialPosition.x == piece.position.x) {
                // If position in front of pawn than it can move there
                if(pieceAtPotentialPosition == null) {
                    result.add(potentialPosition)
                }
            } else {
                // If position in fork of pawn is enemy piece then pawn can take it
                if(pieceAtPotentialPosition != null && pieceAtPotentialPosition.side != piece.side) {
                    result.add(potentialPosition)
                }
            }
        }

        return result
    }

    private fun getNonPawnPossibleMoves(piece: Piece) : ArrayList<Position> {
        val result: ArrayList<Position> = ArrayList<Position>()

        for(line in piece.linesThisCanReach) {
            val cleanLine: Line = cleanLineOfPossibleMoves(line, piece.side)
            result.addAll(cleanLine.positions)
        }

        return result
    }

    private fun cleanLineOfPossibleMoves(line: Line, side: Side) : Line {
        val result: Line = Line()
        for(position in line.positions) {
            val piece: Piece? = getPiece(position)
            if(piece == null) {
                result.add(position)
                continue
            }
            if(piece.side != side) {
                result.add(position)
            }

            break
        }

        return result
    }


    fun isAttacked(piece: Piece) : Boolean {
        return isAttacked(piece, PieceType.king)
                || isAttacked(piece, PieceType.queen)
                || isAttacked(piece, PieceType.rook)
                || isAttacked(piece, PieceType.knight)
                || isAttacked(piece, PieceType.bishop)
                || isAttacked(piece, PieceType.pawn)
    }

    private fun isAttacked(piece: Piece, attackingPieceType: PieceType) : Boolean {

        return if(attackingPieceType == PieceType.pawn) {
            isAttackedByPawn(piece)
        } else {
            isThereAttackViaLines(piece.getLinesThisCouldReach(attackingPieceType), piece.side, attackingPieceType)
        }
    }

    private fun isThereAttackViaLine(line: Line, defendingSide: Side, attackingPieceType: PieceType) : Boolean {

        for(position in line.positions) {
            val piece = getPiece(position)
            if(piece == null) {
                continue
            }
            if(piece.side == defendingSide) {
                break
            }
            if(piece.type == attackingPieceType) {
                return true
            }
            break
        }
        return false
    }

    private fun isThereAttackViaLines(lines: ArrayList<Line>, defendingSide: Side, attackingPieceType: PieceType) : Boolean {
        for(line in lines) {
            if(isThereAttackViaLine(line, defendingSide, attackingPieceType)) {
                return true
            }
        }

        return false
    }

    private fun isAttackedByPawn(piece: Piece): Boolean {
        for(line in piece.linesPawnCouldReachThis) {
            val potentialPosition: Position = line.positions.first()
            if(potentialPosition.x == piece.position.x ) {
                continue
            }

            val pieceAtPotentialPosition: Piece? = getPiece(potentialPosition)
            if(pieceAtPotentialPosition != null && pieceAtPotentialPosition.type == PieceType.pawn) {
                return true
            }
        }

        return false
    }

}