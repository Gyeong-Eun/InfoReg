package com.example.inforeg

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import com.google.android.material.bottomnavigation.BottomNavigationView

class PersonnelSet : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_personnel_set)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_personnel_list, menu)
        val bnv_main = findViewById<BottomNavigationView>(R.id.bnv_main)
        bnv_main.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.first -> {
                    val intent = Intent(this, PersonnelList::class.java)
                    startActivity(intent)
                }
                R.id.second -> {
                    val intent = Intent(this, PersonnelReg::class.java)
                    startActivity(intent)
                }
            }
            true
        }
        return true
    }
}