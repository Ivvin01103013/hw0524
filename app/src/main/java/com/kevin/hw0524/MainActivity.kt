package com.kevin.hw0524

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    var db = Firebase.firestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var edtDrink:EditText = findViewById(R.id.edtDrink)
        var edtSize:EditText = findViewById(R.id.edtSize)
        var edtPrice:EditText = findViewById(R.id.edtPrice)
        var btnDelete:Button = findViewById(R.id.btnDelete)

        var btnUpdate: Button = findViewById(R.id.btnUpdate)
        btnUpdate.setOnClickListener({
            val Drinks = hashMapOf(
                "Name" to edtDrink.text.toString(),
                "Size" to edtSize.text.toString(),
                "Price" to edtPrice.text.toString().toInt()
            )
            db.collection("White")
                .add(Drinks)
                .addOnSuccessListener { documentReference ->
                    Toast.makeText(
                        this, "新增/異動資料成功",
                        Toast.LENGTH_LONG
                    ).show()
                }
                .addOnFailureListener { e ->
                    Toast.makeText(
                        this, "新增/異動資料失敗：" + e.toString(),
                        Toast.LENGTH_LONG
                    ).show()
                }
        })
        btnDelete.setOnClickListener({
            db.collection("White")
                .document(edtDrink.text.toString())
                .delete()
            Toast.makeText(this,"删除资料",Toast.LENGTH_LONG).show()
        })
    }
}