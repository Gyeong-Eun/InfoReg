package com.example.inforeg

import android.annotation.SuppressLint
import android.content.Intent
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import com.example.inforeg.DBManager

class PersonnelInfo : AppCompatActivity() {

    lateinit var dbManager: DBManager
    lateinit var sqlitedb: SQLiteDatabase

    lateinit var tvName: TextView
    lateinit var tvPlace: TextView
    lateinit var tvStart: TextView
    lateinit var tvTime: TextView
    lateinit var tvContent: TextView


    lateinit var str_name: String
    lateinit var str_place: String
    lateinit var str_start: String
    lateinit var str_time: String
    lateinit var str_content: String
    lateinit var btnBack : ImageButton


    @SuppressLint("Range")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_personnel_info)

        btnBack = findViewById(R.id.btnBack)

        tvName = findViewById(R.id.edtName)
        tvPlace = findViewById(R.id.edtPlace)
        tvStart = findViewById(R.id.edtStart)
        tvTime = findViewById(R.id.edtTime)
        tvContent = findViewById(R.id.edtContent)

        val intent = intent
        str_name = intent.getStringExtra("intent_name").toString()

        dbManager = DBManager(this, "guruDB", null, 1)
        sqlitedb = dbManager.readableDatabase

        var cursor:Cursor
        cursor = sqlitedb.rawQuery("SELECT * FROM guru WHERE name = '"+str_name+"';", null)

        if(cursor.moveToNext()) {
            str_place = cursor.getString((cursor.getColumnIndex("place"))).toString()
            str_start = cursor.getString((cursor.getColumnIndex("start"))).toString()
            str_time = cursor.getString((cursor.getColumnIndex("time"))).toString()
            str_content = cursor.getString((cursor.getColumnIndex("content"))).toString()
        }

        btnBack.setOnClickListener {
            val intent = Intent(this, PersonnelList::class.java)
            startActivity(intent)
        }

        cursor.close()
        sqlitedb.close()
        dbManager.close()

        tvName.text = str_name
        tvPlace.text = str_place
        tvStart.text = str_start
        tvTime.text = str_time
        tvContent.text = str_content + "\n "
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_personnel_info, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item?.itemId) {
            R.id.action_home -> {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                return true
            }

            R.id.action_list -> {
                val intent = Intent(this, PersonnelList::class.java)
                startActivity(intent)
                return true
            }
            R.id.action_reg -> {
                val  intent = Intent(this, PersonnelReg::class.java)
                startActivity(intent)
                return true
            }
            R.id.action_remove -> {

                dbManager = DBManager(this, "personnelDB", null, 1)
                sqlitedb = dbManager.readableDatabase

                sqlitedb.execSQL("DELETE FROM guru WHERE name = '"+str_name + "';")
                sqlitedb.close()
                dbManager.close()

                val intent = Intent(this, PersonnelList::class.java)
                startActivity(intent)
                return true
            }
        }

        return super.onOptionsItemSelected(item)
    }
}