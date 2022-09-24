package com.example.semana5_2_sqlite_jueves

import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    lateinit var dbHelper:OpenHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val txtNom:EditText=findViewById(R.id.txtNom)
        val txtEdad:EditText=findViewById(R.id.txtEdad)
        val txtCorreo:EditText=findViewById(R.id.txtCorreo)

        val btnGrabar:Button=findViewById(R.id.btngrabar)
        val btnListar:Button=findViewById(R.id.btnListar)

        val txtRes:TextView=findViewById(R.id.txtRes)

        btnGrabar.setOnClickListener(){

            dbHelper= OpenHelper(this)

            dbHelper.nuevoUser(txtNom.text.toString(),
                                txtEdad.text.toString().toInt(),
                                txtCorreo.text.toString())

            txtEdad.text.clear()
            txtCorreo.text.clear()
            txtNom.text.clear()

        }

        btnListar.setOnClickListener(){

            txtRes.text=""
            dbHelper= OpenHelper(this)

            val db:SQLiteDatabase=dbHelper.readableDatabase
            val cursor=db.rawQuery("select * from users",null)

            if(cursor.moveToFirst()){
                do{
                    txtRes.append("Codigo: "+cursor.getInt(0).toString()+"\n"+
                            "Nombre: "+cursor.getString(1).toString()+"\n"+
                            "Edad: "+cursor.getInt(2).toString()+"\n"+
                            "Correo: "+cursor.getString(3).toString()+"\n\n")
                }while(cursor.moveToNext())
            }

        }

    }
}