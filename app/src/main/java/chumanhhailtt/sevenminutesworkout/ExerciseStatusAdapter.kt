package chumanhhailtt.sevenminutesworkout

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView

class ExerciseStatusAdapter(val context: Context, val itemsList: ArrayList<Exercise>): RecyclerView.Adapter<ExerciseStatusAdapter.ViewHolder>() {
    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val tvExerciseLevel = itemView.findViewById<TextView>(R.id.tv_exercise_level)
        val llExerciseStatusItem = itemView.findViewById<LinearLayout>(R.id.ll_exercise_status_item)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_status, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tvExerciseLevel.text = (position + 1).toString()
        val exercise = itemsList.get(position)
        when(exercise.status) {
            Exercise.DONE -> {
                holder.llExerciseStatusItem.background = ContextCompat.getDrawable(context, R.drawable.bg_item_done_status)
                holder.tvExerciseLevel.setTextColor(ContextCompat.getColor(context, R.color.white))
            }
            Exercise.DOING -> {
                holder.llExerciseStatusItem.background = ContextCompat.getDrawable(context, R.drawable.bg_item_doing_status)
                holder.tvExerciseLevel.setTextColor(ContextCompat.getColor(context, R.color.black))
            }
            Exercise.NOT_YET -> {
                holder.llExerciseStatusItem.background = ContextCompat.getDrawable(context, R.drawable.bg_item_not_yet_status)
                holder.tvExerciseLevel.setTextColor(ContextCompat.getColor(context, R.color.black))
            }
        }
    }

    override fun getItemCount(): Int {
        return itemsList.size
    }
}