package com.example.ixuba.chessgame

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.support.graphics.drawable.VectorDrawableCompat
import android.support.v4.content.ContextCompat
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View

class GameSurface(context: Context, attributeSet: AttributeSet) : View( context, attributeSet) {

    private val white: Paint = Paint().apply {
        color = ContextCompat.getColor(context, R.color.white)
    }
    private val black: Paint = Paint().apply {
        color = ContextCompat.getColor(context, R.color.black)
    }
    private val red: Paint = Paint().apply {
        color = Color.RED
    }

    private var xCoordinate = -1f
    private var yCoordinate = -1f

    private var pieces: ArrayList<ChessPiece> = ArrayList<ChessPiece>()


    override fun onTouchEvent(event: MotionEvent?): Boolean {
        if(event?.action == MotionEvent.ACTION_UP) {
            xCoordinate = event.x
            yCoordinate = event.y
            invalidate()
        }
        return true
    }

    fun drawGame(gameData: GameData) {
        pieces = gameData.pieces
        invalidate()
        Log.d("tagor", "drawGame")
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        drawBoard(canvas)
        drawPrevStepMark(canvas)
        drawPossibleSteps(canvas)
        drawPieces(canvas)
    }

    fun drawBoard(canvas: Canvas?, flipped: Boolean = false) {
        var vectorBoard = VectorDrawableCompat.create(getContext().getResources(), R.drawable.chess_board, null);
        vectorBoard?.setBounds(0, 0, width, width)
        vectorBoard?.draw(canvas)
    }

    fun drawPrevStepMark(canvas: Canvas?) {

    }

    fun drawPossibleSteps(canvas: Canvas?) {

    }

    fun drawPieces(canvas: Canvas?) {
        for(piece in pieces) {
            drawPiece(canvas, piece)
        }
    }

    fun drawPiece(canvas: Canvas?, piece:ChessPiece) {
        Log.d("tagor", "drawPiece")
        var vectorPiece: VectorDrawableCompat? = VectorDrawableCompat.create(getContext().getResources(),
                piece.resource, null);
        var dim: Int = width/8

        vectorPiece?.setBounds(piece.position.getLeft(dim)  , piece.position.getTop(dim),
            piece.position.getRight(dim), piece.position.getBottom(dim))

        vectorPiece?.draw(canvas)
    }



}