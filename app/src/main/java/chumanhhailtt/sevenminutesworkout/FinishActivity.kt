package chumanhhailtt.sevenminutesworkout

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class FinishActivity : AppCompatActivity() {

    var btnFinish: Button? = null

    var triumphSound: MediaPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_finish)

        triumphSound = MediaPlayer.create(this, R.raw.triumph)
        triumphSound!!.start()

        btnFinish = findViewById(R.id.btn_finish)
        btnFinish!!.setOnClickListener {
            finish()
        }
    }

    override fun onDestroy() {
        triumphSound?.stop()
        super.onDestroy()
    }
}