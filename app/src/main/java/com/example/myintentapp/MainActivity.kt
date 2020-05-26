package com.example.myintentapp

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var tvResult: TextView
    companion object {
        private const val REQUEST_CODE = 100
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnMoveActivity: Button = findViewById(R.id.btn_move_activity)
        val btnMoveWithDataActivity: Button = findViewById(R.id.btn_move_activity_data)
        val btnMoveWithObject: Button = findViewById(R.id.btn_move_activity_object)
        val btnDialPhone: Button = findViewById(R.id.btn_dial_number)
        val btnMoveForResult: Button = findViewById(R.id.btn_move_for_result)
        tvResult = findViewById(R.id.tv_result)

        btnMoveActivity.setOnClickListener (this)
        btnMoveWithDataActivity.setOnClickListener(this)
        btnMoveWithObject.setOnClickListener(this)
        btnDialPhone.setOnClickListener(this)
        btnMoveForResult.setOnClickListener(this)

    }

    override fun onClick(v: View?) {
        if (v != null) {
            when (v.id){
                R.id.btn_move_activity -> {
                    val intent = Intent(this@MainActivity, MoveActivity::class.java)
                    startActivity(intent)
                }
                R.id.btn_move_activity_data -> {
                    val intent = Intent(this@MainActivity, MoveWithDataActivity::class.java)
                    intent.putExtra(MoveWithDataActivity.EXTRA_NAME, "DicodingAcademy Boy")
                    intent.putExtra(MoveWithDataActivity.EXTRA_AGE, 5)
                    startActivity(intent)
                }
                R.id.btn_move_activity_object -> {
                    val person = Person(
                        "DicodingAcademy",
                        5,
                        "academy@dicoding.com",
                        "Bandung"
                    )

                    val intent = Intent(this@MainActivity, MoveWithObjectActivity::class.java)
                    intent.putExtra(MoveWithObjectActivity.EXTRA_PERSON, person)
                    startActivity(intent)
                }
                R.id.btn_dial_number -> {
                    val phoneNumber = "081210841382"
                    val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:$phoneNumber"))
                    startActivity(intent)
                }
                R.id.btn_move_for_result -> {
                    val intent = Intent(this@MainActivity, MoveForResultActivity::class.java)
                    startActivityForResult(intent, REQUEST_CODE)
                }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == REQUEST_CODE){
            if(resultCode == MoveForResultActivity.RESULT_CODE){
                val selectedValue = data?.getIntExtra(MoveForResultActivity.EXTRA_SELECTED_VALUE,0)
                tvResult.text = "Hasil : $selectedValue"
            }
        }
    }
}
