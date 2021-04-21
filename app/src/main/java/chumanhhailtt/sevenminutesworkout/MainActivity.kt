package chumanhhailtt.sevenminutesworkout

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageButton
import android.widget.Toast
import android.widget.Toolbar
import com.google.android.material.button.MaterialButton

class MainActivity : AppCompatActivity() {

    var btnStart: MaterialButton? = null
    var btnIBM: MaterialButton? = null
    var btnHistory: ImageButton? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnStart = findViewById(R.id.btnStart)
        btnIBM = findViewById(R.id.btn_IBM)
        btnHistory = findViewById(R.id.btn_history)

        btnStart!!.setOnClickListener {
            val intent = Intent(this, ExerciseActivity::class.java)
            startActivity(intent)
        }
        btnIBM!!.setOnClickListener {
            val intent = Intent(this, IBMActivity::class.java)
            startActivity(intent)
        }
        btnHistory!!.setOnClickListener {
            val intent = Intent(this, HistoryActivity::class.java)
            startActivity(intent)
        }
    }
}