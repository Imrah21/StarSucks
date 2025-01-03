package com.StarSucks

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.StarSucks.databinding.ActivityOrderHistoryBinding

class OrderHistoryActivity : AppCompatActivity() {
    val database = Firebase.database

    val starSucksRef = Firebase.database("https://starsucks-ecd9a-default-rtdb.europe-west1.firebasedatabase.app").getReference("orders")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityOrderHistoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Read from the database
        starSucksRef.addValueEventListener(object: ValueEventListener {

            override fun onDataChange(snapshot: DataSnapshot) {
                var list = mutableListOf<Order>()

                // iterate over the children in the list
                for (pulledOrder in snapshot.children) {
                    val order : Order? = pulledOrder.getValue(Order::class.java)
                    if (order != null) {
                        list.add(order)
                    }
                }

                // create the adapter to display the items
                var orderAdapter = ArrayAdapter(this@OrderHistoryActivity,
                    android.R.layout.simple_list_item_1, list)
                binding.lstvOrderHistory.adapter = orderAdapter
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@OrderHistoryActivity,
                    "Error reading from database", Toast.LENGTH_SHORT).show()
            }
        })
    }
}