package com.example.kchat

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class SignUp : AppCompatActivity() {

    private lateinit var editEmail: EditText
    private lateinit var editName: EditText
    private lateinit var editPassword: EditText
    private lateinit var btnSignUp: Button
    private lateinit var mDbRef: DatabaseReference

    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d("myTag", "This is my message");
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        Log.d("myTag", "This is my message2");

        supportActionBar?.hide()

        mAuth = FirebaseAuth.getInstance()

        Log.d("myTag", "This is my message3");


        editEmail = findViewById(R.id.editEmail)
        editName = findViewById(R.id.editName)
        editPassword = findViewById(R.id.editPassword)
        btnSignUp = findViewById(R.id.btnSignUp)

        btnSignUp.setOnClickListener {
            val name = editName.text.toString()
            val email = editEmail.text.toString()
            val password = editPassword.text.toString()
            Log.d("myTag", "This is my message4");
//            val name = editName.text.toString()

            signUp(name, email, password)
        }
    }

    private fun signUp(name:String, email: String, password: String) {
        Log.d("myTag", "This is my message5");
        // Logic of creating user
        mAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Add user to the database
                    addUserToDatabase(name, email, mAuth.currentUser?.uid!!)
                    // Code for jumping to home
                    val intent = Intent(this@SignUp, MainActivity::class.java)
                    finish()
                    startActivity(intent)
                } else {
                    Log.e("Firebase Auth", "Sign-in failed", task.exception)
                    Toast.makeText(
                        this@SignUp, task.exception!!.message.toString(),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
    }

    private fun addUserToDatabase(name: String, email: String, uid: String) {
        mDbRef = FirebaseDatabase.getInstance().getReference()
        mDbRef.child("user").child(uid).setValue(User(name, email, uid))
    }
}