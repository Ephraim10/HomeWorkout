package exercises

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.TextView
import com.example.fragments.MainActivity2
import com.example.fragments.R

class chairActivity : AppCompatActivity() {
    private var buttonvalue: String? = null
    private lateinit var startBtn: Button
    private lateinit var mtextview: TextView
    private var MTimerunning = false
    private var MTimeLeftinmills: Long = 0

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chair)
        buttonvalue = intent.getStringExtra("value")
        val intvalue = buttonvalue?.toInt()
        startBtn = findViewById(R.id.startbutton)
        mtextview = findViewById(R.id.time)

        startBtn.setOnClickListener {
            if (MTimerunning) {
                stoptimer()
            } else {
                startTimer()
            }
        }
    }
    private fun stoptimer() {
        countDownTimer.cancel()
        MTimerunning = false
        startBtn.text = "START"
    }

    private fun startTimer() {
        val value1 = mtextview.text.toString()
        val num1 = value1.substring(0, 2)
        val num2 = value1.substring(3, 5)

        val number = Integer.valueOf(num1) * 60 + Integer.valueOf(num2)
        MTimeLeftinmills = number.toLong() * 1000L // Use toLong() to convert Int to Long


        countDownTimer = object : CountDownTimer(MTimeLeftinmills, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                MTimeLeftinmills = millisUntilFinished
                updateTimer()
            }

            override fun onFinish() {
                val newvalue = buttonvalue?.toInt()?.plus(1) ?: 2
                if (newvalue <= 7) {
                    val intent = Intent(this@chairActivity, MainActivity2::class.java).apply {
                        addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                        putExtra("value", newvalue.toString())
                    }
                    startActivity(intent)
                } else {
                    val intent = Intent(this@chairActivity, MainActivity2::class.java).apply {
                        addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                        putExtra("value", "1")
                    }
                    startActivity(intent)
                }
            }
        }.start()

        startBtn.text = "Pause"
        MTimerunning = true
    }

    private fun updateTimer() {
        val minutes = (MTimeLeftinmills / 60000).toInt()
        var seconds = (MTimeLeftinmills % 60000 / 1000).toInt()
        var timeLeftText = ""
        if (minutes < 10) timeLeftText = "0"
        timeLeftText += "$minutes:"
        if (seconds < 10) timeLeftText += "0"
        timeLeftText += seconds
        mtextview.text = timeLeftText
    }

    override fun onBackPressed() {
        val intent = Intent(this@chairActivity, MainActivity2::class.java)
        startActivity(intent)
        finish()


    }
    private lateinit var countDownTimer: CountDownTimer
}
