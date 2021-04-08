package chumanhhailtt.sevenminutesworkout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import java.lang.Exception

class IBMActivity : AppCompatActivity() {

    var toolbarIBMActivity: Toolbar? = null
    var etMetricWeight: EditText? = null
    var etMetricHeight: EditText? = null
    var etUsWeight: EditText? = null
    var etUsHeight: EditText? = null
    var tvIBMScore: TextView? = null
    var tvIBMType: TextView? = null
    var btnIBMCaculate: Button? = null
    var llIBMResult: LinearLayout? = null
    var llMetricUnit: LinearLayout? = null
    var llUsUnit: LinearLayout? = null
    var rgUnit: RadioGroup? = null

    var metricUnit = true

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
        etMetricWeight = findViewById(R.id.et_metric_weight)
        etMetricHeight = findViewById(R.id.et_metric_height)
        etUsWeight = findViewById(R.id.et_us_weight)
        etUsHeight = findViewById(R.id.et_us_height)
        tvIBMScore = findViewById(R.id.tv_ibm_score)
        tvIBMType = findViewById(R.id.tv_ibm_type)
        btnIBMCaculate = findViewById(R.id.btn_ibm_calculate)
        llIBMResult = findViewById(R.id.ll_ibm_result)
        llMetricUnit = findViewById(R.id.llMetricUnit)
        llUsUnit = findViewById(R.id.llUsUnit)
        rgUnit = findViewById(R.id.rg_unit)

        btnIBMCaculate!!.setOnClickListener {
            if(metricUnit) {
                if(etMetricWeight!!.text.isNullOrBlank() || etMetricHeight!!.text.isNullOrBlank()) {
                    Toast.makeText(this, "Please enter full information!", Toast.LENGTH_SHORT).show()
                } else {
                    val height: Float = etMetricHeight!!.text.toString().toFloat() / 100
                    val weight: Float = etMetricWeight!!.text.toString().toFloat()
                    val score = 703*weight / (height*height)
                    setIBMResult(score)
                }
            } else {
                if(etUsWeight!!.text.isNullOrBlank() || etUsHeight!!.text.isNullOrBlank()) {
                    Toast.makeText(this, "Please enter full information!", Toast.LENGTH_SHORT).show()
                } else {
                    val height: Float = etUsHeight!!.text.toString().toFloat() / 100
                    val weight: Float = etUsWeight!!.text.toString().toFloat()
                    val score = weight / (height*height)
                    setIBMResult(score)
                }
            }
        }

        rgUnit!!.setOnCheckedChangeListener { group, checkedId ->
            if(checkedId == R.id.rb_metric) {
                llMetricUnit!!.visibility = View.VISIBLE
                llUsUnit!!.visibility = View.GONE
                llIBMResult!!.visibility = View.INVISIBLE
                etMetricHeight!!.text.clear()
                etMetricWeight!!.text.clear()
                metricUnit = true
            } else if(checkedId == R.id.rb_us){
                llUsUnit!!.visibility = View.VISIBLE
                llMetricUnit!!.visibility = View.GONE
                llIBMResult!!.visibility = View.INVISIBLE
                etUsWeight!!.text.clear()
                etUsHeight!!.text.clear()
                metricUnit = false
            }
        }
    }

    private fun setIBMResult(score: Float) {
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
        llIBMResult!!.visibility = View.VISIBLE
    }
}