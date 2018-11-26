package com.example.ixuba.chessgame.Data

enum class Side() {
    white, black;

    val opposite: Side
        get() {
            if(this == white) {
                return black
            } else {
                return white
            }
        }
}