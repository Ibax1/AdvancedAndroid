package com.example.ixuba.chessgame.Data

import android.util.Log

class Position(var x: Int, var y: Int) {

    init{
        //Log.d("positionObjectTag", "Position object created with x = ${x}, y = ${y}")
        if(x !in 0..7 || y !in 0..7){
            throw Exception("Class Position, constructor(Int, Int): arguments are not in appropriate range") as Throwable
        }
    }

    constructor(xChar: Char, yChar: Char) : this(xChar.toInt() - 97, yChar.toInt() - 49)

    constructor(xyString: String) : this(xyString[0], xyString[1])

    constructor(dim: Int, xFloat: Float, yFloat: Float) : this( 0,0){
        x = (xFloat / dim.toFloat()).toInt()
        y = 7 - (yFloat / dim.toFloat()).toInt()
        if(x < 0) x = 0
        if(y < 0) y = 0
        if(x > 7) y = 7
        if(y > 7) y = 7
    }


    override fun toString(): String {
        return (x + 97).toChar().toString() + (y + 49).toChar()
    }

    override fun equals(other: Any?): Boolean {
        if(other is Position && this.x == other.x && this.y == other.y) {
            return true
        }

        return false
    }

    // This method is used to get parameters(left, top, right, bottom) to draw vector image.
    fun getSide(dim: Int, direction: Direction) : Int {
        when(direction) {
            Direction.N -> {
                return (7 - y) * dim
            }
            Direction.S -> {
                return (7 - (y - 1)) * dim
            }
            Direction.E -> {
                return (x + 1) * dim
            }
            Direction.W -> {
                return x * dim
            }
            else -> {
                throw Exception("Class Position, getSide(Int, Direction): inappropiete Direction")
            }
        }
    }

    // Returns line of positions from this position to edge of board, in one of 4 orthogonal and 4 diagonal directions
    fun getLine(direction: Direction) : Line {
        val result: ArrayList<Position> = ArrayList<Position>()
        when(direction) {
            Direction.N -> {
                for(i in (this.y + 1)..7) {
                    result.add(Position(this.x, i))
                }
            }
            Direction.S -> {
                for(i in (this.y - 1) downTo 0) {
                    result.add(Position(this.x, i))
                }
            }
            Direction.E -> {
                for(i in (this.x + 1)..7) {
                    result.add(Position(i, this.y))
                }
            }
            Direction.W -> {
                for(i in (this.x - 1) downTo 0) {
                    result.add(Position(i, this.y))
                }
            }
            Direction.NE -> {
                for(i in 1..minOf(7 - this.x, 7 - this.y)) {
                    result.add(Position(this.x + i, this.y + i))
                }
            }
            Direction.SW -> {
                for(i in 1..minOf(this.x, this.y)) {
                    result.add(Position(this.x - i, this.y - i))
                }
            }
            Direction.NW -> {
                for(i in 1..minOf(this.x, 7 - this.y)) {
                    result.add(Position(this.x - i, this.y + i))
                }
            }
            Direction.SE -> {
                for(i in 1..minOf(7 - this.x, this.y)) {
                    result.add(Position(this.x + i, this.y - i))
                }
            }
        }

        return Line(result)
    }
}