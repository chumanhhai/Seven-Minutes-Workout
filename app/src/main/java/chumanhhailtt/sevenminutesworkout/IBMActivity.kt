package chumanhhailtt.sevenminutesworkout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import java.lang.Exception

class IBMActivity : AppCompatActivity() {

    var toolbarIBMActivity: Toolbar? = null
    var etWeight: EditText? = null
    var etHeight: EditText? = null
    var tvIBMScore: TextView? = null
    var tvIBMType: TextView? = null
    var btnIBMCaculate: Button? = null
    var llIBMInput: LinearLayout? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_i_b_m)

        //setup action bar
        toolbarIBMActivity = findViewById(R.id.toolbar_ibm_activity)
        setSupportActionBar(toolbarIBMActivity)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        toolbarIBMActivity!!.setNavigationOnClickListener {
            onBackPressed()
        }

        // get view
        etWeight = findViewById(R.id.et_weight)
        etHeight = findViewById(R.id.et_height)
        tvIBMScore = findViewById(R.id.tv_ibm_score)
        tvIBMType = findViewById(R.id.tv_ibm_type)
        btnIBMCaculate = findViewById(R.id.btn_ibm_calculate)
        llIBMInput = findViewById(R.id.ll_ibm_input)

        btnIBMCaculate!!.setOnClickListener {
            if(etHeight!!.text.isNullOrEmpty() || etWeight!!.text.isNullOrEmpty()) {
                Toast.makeText(this, "Please enter full information!", Toast.LENGTH_SHORT).show()
            } else {
                val height: Float = etHeight!!.text.toString().toFloat() / 100
                val weight: Float = etWeight!!.text.toString().toFloat()
                val score = weight / (height*height)
                tvIBMScore!!.text = score.toString()
                if(score < 16) {
                    tvIBMType!!.text = "Severe Thinness"
                    tvIBMType!!.setTextColor(ContextCompat.getColor(this, R.color.yellow))
                } else if(score < 17) {
                    tvIBMType!!.text = "Moderate Thinness"
                    tvIBMType!!.setTextColor(ContextCompat.getColor(this, R.color.yellow))
                } else if(score < 18.5) {
                    tvIBMType!!.text = "Mild Thinness"
                    tvIBMType!!.setTextColor(ContextCompat.getColor(this, R.color.yellow))
                } else if(score < 25) {
                    tvIBMType!!.text = "Normal"
                    tvIBMType!!.setTextColor(ContextCompat.getColor(this, R.color.green))
                } else if(score < 30) {
                    tvIBMType!!.text = "Overweight"
                    tvIBMType!!.setTextColor(ContextCompat.getColor(this, R.color.red))
                } else {
                    tvIBMType!!.text = "Obese (Dit me beo vai lon)"
                    tvIBMType!!.setTextColor(ContextCompat.getColor(this, R.color.red))
                }
                llIBMInput!!.visibility = View.VISIBLE
            }
        }
    }
}