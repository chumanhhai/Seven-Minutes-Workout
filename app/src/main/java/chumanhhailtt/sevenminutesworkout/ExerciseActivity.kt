package chumanhhailtt.sevenminutesworkout

import android.app.Dialog
import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.speech.tts.TextToSpeech
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.text.SimpleDateFormat
import java.util.*

class ExerciseActivity : AppCompatActivity(), TextToSpeech.OnInitListener, ExerciseStatusAdapter.JumpInToExercise {

    var toolbarExeciseActivity: Toolbar? = null
    var pbCountdown: ProgressBar? = null
    var tvCountdownNumber: TextView? = null
    var tvCountdownTitle: TextView?= null
    var ivExercise: ImageView? = null
    var llGetReady: LinearLayout? = null
    var llDoingExercise: LinearLayout? = null
    var tvExerciseName: TextView? = null
    var tvNextExercise: TextView? = null
    var rvExerciseStatus: RecyclerView? = null

    var rest = true
    var exerciseLevel = 0
    var exerciseCompleted = BooleanArray(Constants.exercises.size, { i -> false })
    var countdownTimer: CountDownTimer? = null
    var textToSpeech: TextToSpeech? = null
    var endingSound: MediaPlayer? = null
    var exerciseAdapter: ExerciseStatusAdapter? = null
    var dbHelper: DBHelper? = null

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
            showWarningDialog()
        }

        // set all exercises to be NOT_YET
        for(ex: Exercise in Constants.exercises)
            ex.status = Exercise.NOT_YET

        // get view
        ivExercise = findViewById(R.id.iv_exercise)
        llGetReady = findViewById(R.id.ll_get_ready)
        llDoingExercise = findViewById(R.id.ll_doing_exercise)
        tvExerciseName = findViewById(R.id.tv_exercise_name)
        tvNextExercise = findViewById(R.id.tv_next_exercise)
        rvExerciseStatus = findViewById(R.id.rv_exercise_status)

        // get dbhelper
        dbHelper = DBHelper(this)

        // set up
        textToSpeech = TextToSpeech(this, this)
        endingSound = MediaPlayer.create(this, R.raw.press_start)
        setUpExerciseStatusRecyclerView()

        // init
        executeRest()
    }

    fun setUpExerciseStatusRecyclerView() {
        rvExerciseStatus!!.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        exerciseAdapter = ExerciseStatusAdapter(this, Constants.exercises)
        rvExerciseStatus!!.adapter = exerciseAdapter
    }

    fun countdown(timeInSec: Int) {
        pbCountdown!!.max = timeInSec
        pbCountdown!!.progress = timeInSec
        tvCountdownNumber!!.text = timeInSec.toString()

        countdownTimer = object : CountDownTimer((timeInSec*1000).toLong(), 1000) {
            override fun onTick(millisUntilFinished: Long) {
                val timeInSecond = (millisUntilFinished/1000).toInt()
                pbCountdown!!.setProgress(timeInSecond, true)
                tvCountdownNumber!!.text = timeInSecond.toString()
            }
            override fun onFinish() {
                if(rest) {
                    rest = false
                    executeDoExcercise()
                } else {
                    // update exercise status
                    Constants.exercises.get(exerciseLevel).status = Exercise.DONE
                    exerciseAdapter!!.notifyDataSetChanged()

                    exerciseCompleted[exerciseLevel] = true
                    exerciseLevel++
                    if(exerciseLevel < Constants.exercises.size) {
                        rest = true
                        executeRest()
                    } else {
                        val intent = Intent(this@ExerciseActivity, FinishActivity::class.java)
                        startActivity(intent)
                        finish()
                    }
                }
            }
        }.start()
    }

    fun getNumberOfExerciseCompleted(): Int {
        var num = 0

        for(ex: Boolean in exerciseCompleted)
            if(ex == true)
                num++

        return num
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

        // speak
        speakOut(exercise.name)
    }

    fun executeRest() {
        // set visibility
        llGetReady!!.visibility = View.VISIBLE
        llDoingExercise!!.visibility = View.GONE
        tvNextExercise!!.text = Constants.exercises.get(exerciseLevel).name

        // set view
        pbCountdown = llGetReady!!.findViewById(R.id.pb_countdown)
        tvCountdownNumber = llGetReady!!.findViewById(R.id.tv_countdown_number)
        tvCountdownTitle = llGetReady!!.findViewById(R.id.tv_countdown_title)

        // update exercise status
        Constants.exercises.get(exerciseLevel).status = Exercise.DOING
        exerciseAdapter!!.notifyDataSetChanged()

        tvCountdownTitle!!.text = COUNTDOWN_READY_TITLE
        countdown(COUNTDOWN_READY_NUM)

        // transition sound
        endingSound!!.start()

    }

    override fun onDestroy() {
        // release resources
        countdownTimer?.cancel()
        textToSpeech?.shutdown()
        endingSound?.stop()

        // save history
        val progressPercent = 100*getNumberOfExerciseCompleted() / Constants.exercises.size
        val date = SimpleDateFormat("dd/MMM/yyyy hh:mm:ss", Locale.getDefault()).format(Date(System.currentTimeMillis()))
        dbHelper!!.addDate(History(date, progressPercent))

        super.onDestroy()
    }

    override fun onInit(status: Int) {
        if(status == TextToSpeech.SUCCESS) {
            val result = textToSpeech!!.setLanguage(Locale.US)
            if(result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Toast.makeText(this, "US Language is not supported!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun speakOut(text: String) {
        textToSpeech!!.speak(text, TextToSpeech.QUEUE_FLUSH, null, "")
    }

    fun showWarningDialog() {
        val dialog = Dialog(this)
        dialog.setContentView(R.layout.dialog_warning_eixit)
        val btnYes = dialog.findViewById<Button>(R.id.btn_yes)
        btnYes.setOnClickListener {
            finish()
            dialog.dismiss()
        }
        val btnCancel = dialog.findViewById<Button>(R.id.btn_cancel)
        btnCancel.setOnClickListener {
            dialog.dismiss()
        }
        dialog.show()
    }

    override fun jumpIntoExerciseAction(idx: Int) {
        // set not yet state for current level
        Constants.exercises.get(exerciseLevel).status = Exercise.NOT_YET
        exerciseAdapter!!.notifyDataSetChanged()

        // cancel current countdown timer
        countdownTimer!!.cancel()

        // execute jumped level
        rest = true
        exerciseLevel = idx
        executeRest()
    }
}