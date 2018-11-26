package com.example.ixuba.chessgame.Game.GameSurface

import android.content.Context
import android.graphics.Canvas
import android.support.graphics.drawable.VectorDrawableCompat
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View
import com.example.ixuba.chessgame.Data.Direction
import com.example.ixuba.chessgame.Data.GameShowData
import com.example.ixuba.chessgame.Data.Position
import com.example.ixuba.chessgame.R

class GameSurface(context: Context, attributeSet: AttributeSet) : View( context, attributeSet) {

    private var listener: OnGameSurfaceEventListener? = null
    //private var pieces: ArrayList<Piece> = ArrayList<Piece>()
    private var gameShowData: GameShowData? = null

    fun setOnGameSurfaceEventListener(listener: OnGameSurfaceEventListener) {
        this.listener = listener
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {

        if(event == null) {
            return true
        }

        val position: Position =
            Position(width / 8, event.x, event.y)

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
        var vectorBoard = VectorDrawableCompat.create(getContext().getResources(),
            R.drawable.chess_board, null);
        vectorBoard?.setBounds(0, 0, width, width)
        vectorBoard?.draw(canvas)
    }

    fun drawPrevStepMark(canvas: Canvas?) {
        if(gameShowData?.gameData?.story != null && gameShowData?.gameData?.story!!.size > 0) {
            drawVectorAtPosition(canvas, gameShowData?.gameData?.story?.last()!!.from,
                R.drawable.prev_step_from
            )
            drawVectorAtPosition(canvas, gameShowData?.gameData?.story?.last()!!.to,
                R.drawable.prev_step_to
            )
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
        //drawVectorAtPosition(canvas, Position(3,3), R.drawable.possible_step)
    }

    fun drawVectorAtPosition(canvas: Canvas?, position: Position, resource: Int) {
        var vectorPiece: VectorDrawableCompat? = VectorDrawableCompat.create(getContext().getResources(),
            resource, null);
        var dim: Int = width/8
        vectorPiece?.setBounds(position.getSide(dim, Direction.W)  , position.getSide(dim, Direction.N),
            position.getSide(dim, Direction.E), position.getSide(dim, Direction.S))

        vectorPiece?.draw(canvas)
    }

}