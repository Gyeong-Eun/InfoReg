package com.example.inforeg

import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import com.example.inforeg.DBManager

class PersonnelReg : AppCompatActivity() {

    lateinit var dbManager: DBManager
    lateinit var sqlitedb : SQLiteDatabase

    lateinit var btnRegister : Button
    lateinit var edtName : EditText
    lateinit var edtPlace : EditText
    lateinit var edtStart : EditText
    lateinit var edtDate : EditText
    lateinit var edtTime : EditText
    lateinit var edtContent : EditText
    lateinit var edtLink : EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_personnel_reg)

        btnRegister = findViewById(R.id.btnRegister)
        edtName = findViewById(R.id.edtName)
        edtPlace = findViewById(R.id.edtPlace)
        edtStart = findViewById(R.id.edtStart)
        edtDate = findViewById(R.id.edtDate)
        edtTime = findViewById(R.id.edtTime)
        edtContent = findViewById(R.id.edtContent)
        edtLink = findViewById(R.id.edtLink)

        dbManager = DBManager(this, "guruDB", null, 1)

        btnRegister.setOnClickListener {
            var str_name: String = edtName.text.toString()
            var str_place: String = edtPlace.text.toString()
            var str_start: String = edtStart.text.toString()
            var str_date: String = edtDate.text.toString()
            var str_time: String = edtTime.text.toString()
            var str_content: String = edtContent.text.toString()
            var str_link: String = edtLink.text.toString()


            sqlitedb = dbManager.writableDatabase
            sqlitedb.execSQL("INSERT INTO guru VALUES ('" +str_name+"', '"+str_place+"', "+str_start+", '" +str_date+"', '" +str_time+"', '" +str_content+"', '" +str_link+"')")
            sqlitedb.close()

            val intent = Intent(this, PersonnelList::class.java)
            intent.putExtra("intent_name", str_name)
            startActivity(intent)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_personnel_reg, menu)
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
                val  intent = Intent(this, PersonnelList::class.java)
                startActivity(intent)
                return true
            }
        }

        return super.onOptionsItemSelected(item)
    }
}
