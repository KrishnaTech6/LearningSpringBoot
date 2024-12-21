package com.example.demo


data class What(val hell: String , var balance: Double)
fun main(){

    data class User(val name: String, var age: Int, val what: What)

    val user1 = User("hehe", 22, What("12",12.3))
    val user2 = user1.copy()

    user2.what.balance = 123.3

    println(user1.what) // Output: [123 Street, 456 Avenue]
    println(user2.what) // Output: [123 Street, 456 Avenue]
}