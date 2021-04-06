package chumanhhailtt.sevenminutesworkout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.*
import androidx.appcompat.widget.Toolbar

class ExerciseActivity : AppCompatActivity() {

    var toolbarExeciseActivity: Toolbar? = null
    var pbCountdown: ProgressBar? = null
    var tvCountdownNumber: TextView? = null
    var tvCountdownTitle: TextView?= null
    var ivExercise: ImageView? = null
    var llGetReady: LinearLayout? = null
    var llDoingExercise: LinearLayout? = null
    var counter: CountDownTimer? = null
    var tvExerciseName : TextView? = null

    var rest = true
    var exerciseLevel = 0

    val COUNTDOWN_READY_NUM = 10
    val COUNTDOWN_READY_TITLE = "GET READY FOR"
    val COUNTDOWN_DOING_EXCERCISE_TITLE = "TIME LEFT"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exercise)

        // set back button enabled
        toolbarExeciseActivity = findViewById(R.id.toolbar_exercise_activity)
        setSupportActionBar(toolbarExeciseActivity)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toolbarExeciseActivity!!.setNavigationOnClickListener {
            onBackPressed()
        }

        // get view
        ivExercise = findViewById(R.id.iv_exercise)
        llGetReady = findViewById(R.id.ll_get_ready)
        llDoingExercise = findViewById(R.id.ll_doing_exercise)
        tvExerciseName = findViewById(R.id.tv_exercise_name)

        // setup
        executeRest()
    }

    fun countdown(timeInSec: Int) {
        pbCountdown!!.max = timeInSec
        pbCountdown!!.progress = timeInSec
        tvCountdownNumber!!.text = timeInSec.toString()

        counter = object : CountDownTimer((timeInSec*1000).toLong(), 1000) {
            override fun onTick(millisUntilFinished: Long) {
                val timeInSecond = (millisUntilFinished/1000).toInt()
                pbCountdown!!.setProgress(timeInSecond, true)
                tvCountdownNumber!!.text = timeInSecond.toString()
            }
            override fun onFinish() {
                if(rest) {
                    exerciseLevel++
                    if(exerciseLevel < Constants.exercises.size) {
                        rest = false
                        executeDoExcercise()
                    }
                } else {
                    rest = true
                    executeRest()
                }
            }
        }.start()
    }

    fun executeDoExcercise() {
        // set visibility
        llGetReady!!.visibility = View.GONE
        llDoingExercise!!.visibility = View.VISIBLE

        // set view
        pbCountdown = llDoingExercise!!.findViewById(R.id.pb_countdown)
        tvCountdownNumber = llDoingExercise!!.findViewById(R.id.tv_countdown_number)
        tvCountdownTitle = llDoingExercise!!.findViewById(R.id.tv_countdown_title)

        val exercise = Constants.exercises.get(exerciseLevel)
        ivExercise!!.setImageResource(exercise.image)
        tvCountdownTitle!!.text = COUNTDOWN_DOING_EXCERCISE_TITLE
        tvExerciseName!!.text = exercise.name
        countdown(exercise.length)
    }

    fun executeRest() {
        // set visibility
        llGetReady!!.visibility = View.VISIBLE
        llDoingExercise!!.visibility = View.GONE

        // set view
        pbCountdown = llGetReady!!.findViewById(R.id.pb_countdown)
        tvCountdownNumber = llGetReady!!.findViewById(R.id.tv_countdown_number)
        tvCountdownTitle = llGetReady!!.findViewById(R.id.tv_countdown_title)

        tvCountdownTitle!!.text = COUNTDOWN_READY_TITLE
        countdown(COUNTDOWN_READY_NUM)
    }
}