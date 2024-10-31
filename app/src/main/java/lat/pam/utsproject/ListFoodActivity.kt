package lat.pam.utsproject

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ListFoodActivity : AppCompatActivity() {
    private lateinit var foodrecyclerView: RecyclerView
    private lateinit var drinkrecyclerView: RecyclerView
    private lateinit var foodadapter: FoodAdapter
    private lateinit var drinkadapter: FoodAdapter
    private lateinit var foodList: List<Food>
    private lateinit var drinkList: List<Food>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_list_food)


        foodrecyclerView = findViewById(R.id.foodrecyclerView)
        drinkrecyclerView = findViewById(R.id.drinkrecyclerView)

        foodrecyclerView.layoutManager = LinearLayoutManager(this)
        drinkrecyclerView.layoutManager = LinearLayoutManager(this)

        // Menyiapkan data makanan
        foodList = listOf(
            Food("Batagor", "Batagor asli enak dari Bandung", R.drawable.batagor),
            Food("Black Salad", "Salad segar yang dibuat secara langsung", R.drawable.black_salad),
            Food("Cheesecake", "chessecake enak sedunia", R.drawable.cheesecake),
            Food("Cireng", "Cireng Endul", R.drawable.cireng),
            Food("donut","Donut manis penggugah rasa",R.drawable.donut),
        )

        drinkList = listOf(
            Food("Cappuccino", "Kopi cappuccino asli yang dibuat dari Kopi Arabica", R.drawable.cappuchino),
            Food("Cendol", "Cendol segar dengan citarasa endul", R.drawable.cendol),
            Food("Es Buah", "Es Buah menyegarkan dahaga", R.drawable.esbuah),
            Food("Strawberry Mocktail", "Buah Strawberry asli segar dipetik langsung dari perkebunan ciwidey", R.drawable.strawmocktail),
            Food("Blue Ocean", "Minuman segar Best Seller", R.drawable.blueocean),
        )

        foodadapter = FoodAdapter(foodList) { selectedFood ->
            val  intent = Intent(this, OrderActivity::class.java)
            intent.putExtra("foodName", selectedFood.name)
            startActivity(intent)
        }
        foodrecyclerView.adapter = foodadapter

        // Menyiapkan adapter untuk minuman
        drinkadapter = FoodAdapter(drinkList) { selectedFood ->
            val intent = Intent(this, OrderActivity::class.java)
            intent.putExtra("foodName", selectedFood.name)
            startActivity(intent)
        }
        drinkrecyclerView.adapter = drinkadapter


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}