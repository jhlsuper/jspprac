package com.example.jspprac

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main)
        val queue = Volley.newRequestQueue(this)

        loginBtn.setOnClickListener {

            val inputLogin = userId.text.toString()
            val inputPassword = userPwd.text.toString()
            val url = "http://13.125.243.95:8080/Sign.jsp?userId=$inputLogin&userPassword=$inputPassword"

            val stringRequest = StringRequest(
                Request.Method.GET, url,
                { response ->
                    response.trim { it <= ' ' }
                    val details = response.split(",").toTypedArray()
                    if (response == "error") {

                        Toast.makeText(this, "Invalid email or password", Toast.LENGTH_SHORT).show()
                    } else {

                        Toast.makeText(this, "Login successful", Toast.LENGTH_SHORT).show()

                        SharedPrefManager.getInstance(this).resgisterCurrentUser(details[0], details[1])
                        startActivity(Intent(this, MainActivity::class.java))
                        finish()
                    }
                },
                { })
            queue.add(stringRequest)
        }
    }
}
