package chumanhhailtt.sevenminutesworkout

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class HistoryAdapter(val context: Context, val historiesList: ArrayList<History>) : RecyclerView.Adapter<HistoryAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val pbHistoryPercent = itemView.findViewById<ProgressBar>(R.id.pb_history_percent)
        val tvHistoryPercent = itemView.findViewById<TextView>(R.id.tv_history_percent)
        val tvHistoryDate = itemView.findViewById<TextView>(R.id.tv_history_date)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_history, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.pbHistoryPercent!!.progress = historiesList.get(position).progressPercent
        holder.tvHistoryPercent!!.text = historiesList.get(position).progressPercent.toString()
        holder.tvHistoryDate!!.text = historiesList.get(position).date
    }

    override fun getItemCount(): Int {
        return historiesList.size
    }
}