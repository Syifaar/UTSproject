package lat.pam.utsproject

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.content.Intent
import android.content.SharedPreferences
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class RegisterActivity : AppCompatActivity() {
    private lateinit var sharedPreferences: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        // Inisialisasi SharedPreferences
        sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE)

        // Inisialisasi elemen UI
        val fullNameEditText = findViewById<EditText>(R.id.etFullName)
        val usernameEditText = findViewById<EditText>(R.id.etRegisterUsername)
        val passwordEditText = findViewById<EditText>(R.id.etRegisterPassword)
        val registerButton = findViewById<Button>(R.id.btnRegister)

        // Tombol Register
        registerButton.setOnClickListener {
            val fullName = fullNameEditText.text.toString()
            val username = usernameEditText.text.toString()
            val password = passwordEditText.text.toString()

            if (username.isNotEmpty() && password.isNotEmpty() && fullName.isNotEmpty()) {
                // Simpan data akun ke SharedPreferences
                val editor = sharedPreferences.edit()
                editor.putString("username", username)
                editor.putString("password", password)
                editor.apply()

                Toast.makeText(this, "Akun berhasil dibuat! Silakan login.", Toast.LENGTH_SHORT).show()

                // Kembali ke halaman login
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(this, "Harap isi semua kolom", Toast.LENGTH_SHORT).show()
            }
        }
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}