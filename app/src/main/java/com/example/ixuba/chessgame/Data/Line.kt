package com.example.ixuba.chessgame.Data

class Line(var positions: ArrayList<Position>) {

    constructor() : this(ArrayList<Position>())

    fun add(position: Position) {
        this.positions.add(position)
    }

    override fun toString(): String {
        var result = ""
        for(position in positions) {
            result += position.toString() + "; "
        }
        return result
    }
}