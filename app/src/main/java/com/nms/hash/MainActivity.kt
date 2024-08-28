package com.nms.hash

import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException

class MainActivity : AppCompatActivity() {

    private lateinit var editTextInput: EditText
    private lateinit var buttonHash: Button
    private lateinit var textViewHash: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editTextInput = findViewById(R.id.editTextInput)
        buttonHash = findViewById(R.id.buttonHash)
        textViewHash = findViewById(R.id.textViewHash)

        buttonHash.setOnClickListener {
            val input = editTextInput.text.toString()
            if (input.isNotEmpty()) {
                val hash = hashInput(input)
                textViewHash.text = hash
            } else {
                textViewHash.text = "Please enter text."
            }
        }
    }

    private fun hashInput(input: String): String {
        return try {
            val digest = MessageDigest.getInstance("SHA-256")
            val hashBytes = digest.digest(input.toByteArray())
            hashBytes.joinToString("") { "%02x".format(it) }
        } catch (e: NoSuchAlgorithmException) {
            e.printStackTrace()
            "Hashing error"
        }
    }
}
