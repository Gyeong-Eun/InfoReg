package com.example.inforeg

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.view.Menu
import android.view.MenuItem
import androidx.core.content.ContextCompat
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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
                R.id.third -> {
                    val intent = Intent(this,PersonnelSet::class.java)
                    startActivity(intent)
                }
            }
            true
        }
        bnv_main.selectedItemId = R.id.first

    }
}
