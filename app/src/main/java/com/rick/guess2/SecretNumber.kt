package com.rick.guess2

import java.util.Random

class SecretNumber {
    var secret : Int = Random().nextInt(10)+1
    var count : Int = 0

    fun validate(number : Int) : Int{
        count++
        return number - secret
    }

    fun reset(){
        secret = Random().nextInt(10) + 1
        count = 0
    }
}

fun main() {
    val secretNumber = SecretNumber()
    println(secretNumber.secret)
//    println(secretNumber.validate(2))
    println("${secretNumber.validate(2)}, count:${secretNumber.count}")
}