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

    private var listener:OnGameSurfaceEventListener? = null
    //private var pieces: ArrayList<ChessPiece> = ArrayList<ChessPiece>()
    private var gameShowData: GameShowData? = null

    fun setOnGameSurfaceEventListener(listener: OnGameSurfaceEventListener) {
        this.listener = listener
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {

        if(event == null) {
            return true
        }

        val position: Position = Position(width / 8, event.x, event.y)

        when(event.action) {
            MotionEvent.ACTION_DOWN -> {
                Log.d("onTouchTag", "User started to touch board - DOWN: ${position.toString()}")
                listener?.onGameSurfaceActionDown(position)
            }

            MotionEvent.ACTION_MOVE -> {

            }

            MotionEvent.ACTION_UP -> {
                Log.d("onTouchTag", "User stopped to touch board - UP: ${position.toString()}")
                listener?.onGameSurfaceActionUp(position)
            }
        }

        return true
    }

    fun drawGame(gameShowData: GameShowData) {
        this.gameShowData = gameShowData
        invalidate()
        Log.d("tagor", "drawGame")
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        drawBoard(canvas)
        drawPrevStepMark(canvas)
        drawPieces(canvas)
        drawPossibleSteps(canvas)
    }

    fun drawBoard(canvas: Canvas?, flipped: Boolean = false) {
        var vectorBoard = VectorDrawableCompat.create(getContext().getResources(), R.drawable.chess_board, null);
        vectorBoard?.setBounds(0, 0, width, width)
        vectorBoard?.draw(canvas)
    }

    fun drawPrevStepMark(canvas: Canvas?) {
        if(gameShowData?.gameData?.story != null && gameShowData?.gameData?.story!!.size > 0) {
            drawVectorAtPosition(canvas, gameShowData?.gameData?.story?.last()!!.first, R.drawable.prev_step_from)
            drawVectorAtPosition(canvas, gameShowData?.gameData?.story?.last()!!.second, R.drawable.prev_step_to)
        }
    }

    fun drawPieces(canvas: Canvas?) {
        for (piece in gameShowData?.gameData!!.pieces) {
            drawVectorAtPosition(canvas, piece.position, piece.resource)
        }
    }

    fun drawPossibleSteps(canvas: Canvas?) {
        for(posStep in gameShowData!!.possibleSteps) {
            drawVectorAtPosition(canvas, posStep, R.drawable.possible_step)
        }
    }

    fun drawVectorAtPosition(canvas: Canvas?, position: Position, resource: Int) {
        var vectorPiece: VectorDrawableCompat? = VectorDrawableCompat.create(getContext().getResources(),
            resource, null);
        var dim: Int = width/8
        vectorPiece?.setBounds(position.getLeft(dim)  , position.getTop(dim),
            position.getRight(dim), position.getBottom(dim))

        vectorPiece?.draw(canvas)
    }

}