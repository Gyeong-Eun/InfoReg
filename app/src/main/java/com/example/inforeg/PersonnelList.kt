package com.example.inforeg

import android.annotation.SuppressLint
import android.content.Intent
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.LinearLayout
import android.widget.TextView
import com.example.inforeg.DBManager
import com.google.android.material.bottomnavigation.BottomNavigationView

class PersonnelList : AppCompatActivity() {

    lateinit var dbManager : DBManager
    lateinit var sqlitedb : SQLiteDatabase
    lateinit var layout: LinearLayout

    @SuppressLint("Range")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_personnel_list)

        dbManager = DBManager(this, "guruDB", null, 1)
        sqlitedb = dbManager.readableDatabase

        layout = findViewById(R.id.personnel)

        var cursor : Cursor
        cursor = sqlitedb.rawQuery("SELECT * FROM guru", null)

        var num: Int =0
        if (cursor.moveToFirst()) {
          do {
              var str_name = cursor.getString((cursor.getColumnIndex("name"))).toString()
              var str_place = cursor.getString((cursor.getColumnIndex("place"))).toString()
              var str_start = cursor.getString((cursor.getColumnIndex("start"))).toString()
              var str_time = cursor.getString((cursor.getColumnIndex("time"))).toString()
              var str_content = cursor.getString((cursor.getColumnIndex("content"))).toString()


              var layout_item:LinearLayout = LinearLayout(this)
              layout_item.orientation = LinearLayout.VERTICAL
              layout_item.id = num

              var tvName: TextView = TextView(this)
              tvName.text = str_name
              tvName.textSize = 30f
              tvName.setBackgroundColor(Color.LTGRAY)
              layout_item.addView(tvName)

              var tvPlace: TextView = TextView(this)
              tvPlace.text = str_place
              layout_item.addView(tvPlace)

              var tvStart: TextView = TextView(this)
              tvStart.text = str_start
              layout_item.addView(tvStart)

              var tvTime: TextView = TextView(this)
              tvTime.text = str_time
              layout_item.addView(tvTime)

              var tvContent: TextView = TextView(this)
              tvContent.text = str_content
              layout_item.addView(tvContent)

              layout_item.setOnClickListener {
                  val intent = Intent(this, PersonnelInfo::class.java )
                  intent.putExtra("intent_name", str_name)
                  startActivity(intent)
              }

              layout.addView(layout_item)
              num++;
          }  while (cursor.moveToNext())
        }

        cursor.close()
        sqlitedb.close()
        dbManager.close()

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
                R.id.third -> {
                    val intent = Intent(this, PersonnelSet::class.java)
                    startActivity(intent)
                }
            }
            true
        }
        return true
    }



    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item?.itemId) {
            R.id.action_home -> {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                return true
            }
            R.id.action_reg -> {
                val  intent = Intent(this, PersonnelReg::class.java)
                startActivity(intent)
                return true
            }
        }

        return super.onOptionsItemSelected(item)
    }
}