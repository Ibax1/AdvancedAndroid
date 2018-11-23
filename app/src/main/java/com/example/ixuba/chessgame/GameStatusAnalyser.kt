//package com.example.ixuba.chessgame
//
//import java.lang.Exception
//
//class GameStatusAnalyser {
//
//    private val occupations: Array2D<PositionOccupation> = Array2D<PositionOccupation>(8, 8, PositionOccupation.empty)
//    private val attackStatus: Array2D<PositionAttackStatus> = Array2D<PositionAttackStatus>(8, 8, PositionAttackStatus.notUnderAttack)
//
//    constructor(gameData: GameData) {
//
//    }
//
//    enum class PositionOccupation() {empty, notEmpty}
//    enum class PositionAttackStatus() {notUnderAttack, underAttack}
//
//    class  Array2D<T>(x: Int, y: Int) {
//        var array: ArrayList<T> = ArrayList<T>(x * y)
//        var w = x
//        var l = y
//
//        constructor(x: Int, y: Int, value: T) : this(x, y) {
//            for(i in 0..array.size) {
//                array[i] = value
//            }
//        }
//
//        operator fun get(x: Int, y: Int): T {
//            if(x !in 0..(w-1) || y !in 0..(l-1)) {
//                throw Exception("Array2D, get(x: Int, y: Int): arguments(s) is/are out of range")
//            }
//            return array[w * y + x]
//        }
//
//        operator fun set(x: Int, y: Int, value: T) {
//            if(x !in 0..(w-1) || y !in 0..(l-1)) {
//                throw Exception("Array2D, set(x: Int, y: Int): argument(s) is/are out of range")
//            }
//            array[w * y + x] = value
//        }
//    }
//}