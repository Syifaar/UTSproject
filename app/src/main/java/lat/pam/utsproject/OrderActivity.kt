package lat.pam.utsproject

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class OrderActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_order)


        // Mendapatkan referensi elemen UI
        val foodNameTextView = findViewById<TextView>(R.id.etFoodName)
        val servingsEditText = findViewById<EditText>(R.id.etServings)
        val nameEditText = findViewById<EditText>(R.id.etName)
        val notesEditText = findViewById<EditText>(R.id.etNotes)
        val btnOrder = findViewById<Button>(R.id.btnOrder)

        // Nonaktifkan tombol "Place Order" ketika belum terisi input order
        btnOrder.isEnabled = false

        val foodName = intent.getStringExtra("foodName")
        foodNameTextView.text = foodName

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Fungsi untuk memeriksa jika semua input telah diisi
        val checkInputs = {
            val isFilled = foodNameTextView.text.isNotEmpty() &&
                    servingsEditText.text.isNotEmpty() &&
                    nameEditText.text.isNotEmpty() &&
                    notesEditText.text.isNotEmpty()
            btnOrder.isEnabled = isFilled
        }

        // TextWatcher untuk memantau perubahan teks di setiap EditText
        val textWatcher = object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                checkInputs()
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        }

        // Menambahkan TextWatcher ke setiap EditText
        servingsEditText.addTextChangedListener(textWatcher)
        nameEditText.addTextChangedListener(textWatcher)
        notesEditText.addTextChangedListener(textWatcher)


        btnOrder.setOnClickListener {
            // Mengambil input pengguna
            val servings = servingsEditText.text.toString()
            val name = nameEditText.text.toString()
            val notes = notesEditText.text.toString()

            // Intent untuk pindah ke ConfirmationActivity
            val intent = Intent(this, ConfirmationActivity::class.java).apply {
                putExtra("foodName", foodName)
                putExtra("servings", servings)
                putExtra("name", name)
                putExtra("notes", notes)
            }
            startActivity(intent)
        }

    }
}


