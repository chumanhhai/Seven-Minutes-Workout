package chumanhhailtt.sevenminutesworkout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.*
import kotlin.collections.ArrayList

class HistoryActivity : AppCompatActivity() {

    var toolbarHistoryActivity: Toolbar? = null
    var rvHistory: RecyclerView? = null
    var tvNoHistory: TextView? = null

    var dbHelper = DBHelper(this)
    var historiesList: ArrayList<History>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)

        // set action bar
        toolbarHistoryActivity = findViewById(R.id.toolbar_history_activity)
        setSupportActionBar(toolbarHistoryActivity)
        supportActionBar!!.setTitle("History")
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        toolbarHistoryActivity!!.setNavigationOnClickListener {
            onBackPressed()
        }

        // get view
        rvHistory = findViewById(R.id.rv_history)
        tvNoHistory = findViewById(R.id.tv_no_history)

        // get all histories
        historiesList = dbHelper.getAllDates()
        historiesList!!.reverse()

        if(historiesList!!.size == 0 ) {
            showNoHistories()
        } else
            showHistories()
    }

    private fun showNoHistories() {
        rvHistory!!.visibility = View.GONE
        tvNoHistory!!.visibility = View.VISIBLE
    }

    private fun showHistories() {
        // set view
        rvHistory!!.visibility = View.VISIBLE
        tvNoHistory!!.visibility = View.GONE

        rvHistory!!.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        val adapter = HistoryAdapter(this, historiesList!!)
        rvHistory!!.adapter = adapter
    }
}