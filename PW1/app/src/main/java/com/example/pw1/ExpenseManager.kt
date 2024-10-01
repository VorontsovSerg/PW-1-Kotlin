package com.example.pw1

class ExpenseManager {
    private val expenseList = mutableListOf<Expense>()

    // Добавление расхода
    fun addExpense(expense: Expense) {
        expenseList.add(expense)
    }

    // Вывод всех расходов
    fun printAllExpenses() {
        if (expenseList.isEmpty()) println("Расходы не найдены.")
        else {
            for (expense in expenseList) {
                expense.printExpenseDetails()
            }
        }
    }

    // Подсчет общей суммы по каждой категории
    fun calculateTotalByCategory() {
        val categoryMap = mutableMapOf<String, Double>()

        for (expense in expenseList) {
            val currentTotal = categoryMap[expense.category] ?: 0.0
            categoryMap[expense.category] = currentTotal + expense.amount
        }

        // Вывод суммы по категориям
        for ((category, total) in categoryMap) {
            println("Категория: $category, Всего: $total руб.")
        }
    }

    fun getTotalByCategoryAsText(): String {
        if (expenseList.isEmpty()) {
            return "Расходов пока нет."
        }

        val categoryMap = mutableMapOf<String, Double>()
        for (expense in expenseList) {
            val currentTotal = categoryMap[expense.category] ?: 0.0
            categoryMap[expense.category] = currentTotal + expense.amount
        }

        val sb = StringBuilder()
        for ((category, total) in categoryMap) {
            sb.append("Категория: $category, Общая сумма: $total руб\n")
        }
        return sb.toString()
    }

    fun getAllExpensesAsText(): String {
        if (expenseList.isEmpty()) {
            return "Расходов пока нет."
        }

        val sb = StringBuilder()
        for (expense in expenseList) {
            sb.append("Расход: ${expense.amount} руб, Категория: ${expense.category}, Дата: ${expense.date}\n")
        }
        return sb.toString()
    }
}
