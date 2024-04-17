package belajar.unit.test

class Calculator {

    fun divide(first: Int, second: Int): Int {
        if (second == 0) {
            throw IllegalArgumentException("can not divide by zero")
        } else {
            return first / second
        }

    }

    fun add (first: Int, second: Int): Int {
        return first + second
    }

}