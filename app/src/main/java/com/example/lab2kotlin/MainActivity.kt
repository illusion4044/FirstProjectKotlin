package com.example.lab2kotlin

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    data class MenuItem(val name: String, val price: Int)

    private val menuItems = listOf(
        MenuItem("Піца", 150),
        MenuItem("Бургер", 120),
        MenuItem("Салат", 90),
        MenuItem("Суші", 200),
        MenuItem("Десерт", 80)
    )

    private var total = 0

     fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val menuListView = findViewById<ListView>(R.id.menuListView)
        val totalText = findViewById<TextView>(R.id.totalText)


        val menuNames = menuItems.map { "${it.name} - ${it.price} грн" }


        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, menuNames)
        menuListView.adapter = adapter

        // Обробка кліків по елементах меню
        menuListView.setOnItemClickListener { _, _, position, _ ->
            val selectedItem = menuItems[position]
            total += selectedItem.price

            val discount = when {
                total >= 500 -> 0.15
                total >= 200 -> 0.10
                else -> 0.0
            }

            val discountedTotal = total - (total * discount).toInt()

            totalText.text = "Сума: $total грн\nЗнижка: ${(discount * 100).toInt()}%\nДо оплати: $discountedTotal грн"
            Toast.makeText(this, "Додано: ${selectedItem.name} - ${selectedItem.price} грн", Toast.LENGTH_SHORT).show()
        }
    }
}
