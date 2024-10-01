package com.example.pw1

class Expense(val amount: Double, val category: String, val date: String) {

    fun printExpenseDetails() {
        println("Расход: $amount руб., Категория: $category, Дата: $date")
    }
}
