package com.example.pw1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private val expenseManager = ExpenseManager()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Получаем ссылки на элементы интерфейса
        val amountInput = findViewById<EditText>(R.id.amountInput)
        val categoryInput = findViewById<EditText>(R.id.categoryInput)
        val dateInput = findViewById<EditText>(R.id.dateInput)
        val addExpenseButton = findViewById<Button>(R.id.addExpenseButton)
        val showExpensesButton = findViewById<Button>(R.id.showExpensesButton)
        val showCategoryTotalButton = findViewById<Button>(R.id.showCategoryTotalButton)
        val outputTextView = findViewById<TextView>(R.id.outputTextView)

        // Добавление начальных данных
        addInitialExpenses()

        // Добавление нового расхода через интерфейс
        addExpenseButton.setOnClickListener {
            val amount = amountInput.text.toString().toDoubleOrNull()
            val category = categoryInput.text.toString()
            val date = dateInput.text.toString()

            if (amount != null && category.isNotEmpty() && date.isNotEmpty()) {
                val expense = Expense(amount, category, date)
                expenseManager.addExpense(expense)
                Toast.makeText(this, "Расход добавлен!", Toast.LENGTH_SHORT).show()

                // Очищаем поля
                amountInput.text.clear()
                categoryInput.text.clear()
                dateInput.text.clear()
            } else {
                Toast.makeText(this,
                    "Пожалуйста, заполните все поля", Toast.LENGTH_SHORT).show()
            }
        }

        // Вывод всех расходов
        showExpensesButton.setOnClickListener {
            val allExpensesText = expenseManager.getAllExpensesAsText()
            outputTextView.text = allExpensesText
        }

        // Вывод общей суммы по категориям
        showCategoryTotalButton.setOnClickListener {
            val totalByCategoryText = expenseManager.getTotalByCategoryAsText()
            outputTextView.text = totalByCategoryText
        }
    }

    // Метод для добавления начальных данных
    private fun addInitialExpenses() {
        val initialExpenses = listOf(
            Expense(500.0, "Продукты", "01.09.2024"),
            Expense(1200.0, "Одежда", "03.09.2024"),
            Expense(300.0, "Транспорт", "02.09.2024"),
            Expense(800.0, "Развлечения", "05.09.2024"),
            Expense(1500.0, "Аренда", "01.09.2024")
        )

        for (expense in initialExpenses) {
            expenseManager.addExpense(expense)
        }
    }
}
