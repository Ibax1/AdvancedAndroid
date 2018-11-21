package com.example.ixuba.chessgame

import android.util.Log

class Position(var x: Int, var y: Int) {

    init{
        if(x !in 0..7 || y !in 0..7){
            throw Exception("Argument(s) for Position object is/are not in right range")
        }
        Log.d("positionObjectTag", "Position object created with x = ${x}, y = ${y}")
    }

    constructor(xChar: Char, yChar: Char) : this(xChar.toInt() - 97, yChar.toInt() - 49)

    constructor(xyString: String) : this(xyString[0], xyString[1])

    constructor(dim: Int, xFloat: Float, yFloat: Float) : this(7 - (xFloat / dim.toFloat()).toInt(),
        7 - (yFloat / dim.toFloat()).toInt())

    override fun toString(): String {
        return (x + 97).toChar().toString() + (y + 49).toChar()
    }

    fun getLeft(dim: Int) : Int {
        return x * dim
    }

    fun getRight(dim: Int) : Int {
        return (x + 1) * dim
    }

    fun getTop(dim: Int) : Int {
        return (7 - y) * dim
    }

    fun getBottom(dim: Int) : Int {
        return (7 - (y - 1)) * dim
    }
}