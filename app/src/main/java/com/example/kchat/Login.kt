package com.example.kchat

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class Login : AppCompatActivity() {

    // Set up the buttons and EditTexts for initialization
    private lateinit var editEmail: EditText
    private lateinit var editPassword: EditText
    private lateinit var btnLogin: Button
    private lateinit var btnSignUp: Button

    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        print("Working 3")

        supportActionBar?.hide()

        mAuth = FirebaseAuth.getInstance()

        print("Working 4")

        // Initialize the buttons and EditTexts
        editEmail = findViewById(R.id.editEmail)
        editPassword = findViewById(R.id.editPassword)
        btnLogin = findViewById(R.id.btnLogin)
        btnSignUp = findViewById(R.id.btnSignUp)

        // When we press the SignUp button, we will go to the SignUp activity, so we set a click listener and send an intent which goes from
        // {this} activity to the SignUp activity
        //Then we start the activity with the intent inside it
        btnSignUp.setOnClickListener {
            val intent = Intent(this@Login, SignUp::class.java)
            print("Working 5")
            startActivity(intent)
            print("Working 6")
        }

        btnLogin.setOnClickListener {
            val email = editEmail.text.toString()
            val password = editPassword.text.toString()
            print("Working 7")
            login(email, password)
        }
    }

    private fun login(email: String, password: String) {
        // Logic for logging in user
        mAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Code for logging in user
                    val intent = Intent(this@Login, MainActivity::class.java)
                    finish()
                    startActivity(intent)
                } else {
                    Toast.makeText(this@Login, "User does not exist!", Toast.LENGTH_SHORT).show()
                }
            }
    }
}