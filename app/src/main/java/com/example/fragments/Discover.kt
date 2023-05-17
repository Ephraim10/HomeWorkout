package com.example.fragments

import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import java.util.concurrent.TimeUnit


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Discover.newInstance] factory method to
 * create an instance of this fragment.
 */
class Discover : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var button1: Button
    private lateinit var button2: Button
    private lateinit var button: LinearLayout
    private lateinit var upgradePrompt: LinearLayout
    private lateinit var countDownTimer: CountDownTimer
    private var timerRunning: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        // Initialize the views
        val view = inflater.inflate(R.layout.fragment_discover, container, false)

        // Initialize the views
        button1 = view.findViewById(R.id.startyoga1)
        button2 = view.findViewById(R.id.startyoga2)
        button = view.findViewById(R.id.food2)
        upgradePrompt = view.findViewById(R.id.premium_upgrade_prompt) // Set click listeners on the buttons
        button1.setOnClickListener {
            upgradePrompt.visibility = View.VISIBLE
            button1.text = "Please upgrade to premium"
        }
        button.setOnClickListener {
            upgradePrompt.visibility = View.VISIBLE
            button1.text = "Please upgrade to premium"
        }
        button2.setOnClickListener {
            upgradePrompt.visibility = View.VISIBLE
            button2.text = "Please upgrade to premium"
        }
        upgradePrompt.setOnClickListener {
            upgradePrompt.visibility = View.GONE
        }

        // Initialize the count down timer with 15 seconds and an interval of 1 second
        countDownTimer = object : CountDownTimer(15_000, 1_000) {
            override fun onTick(millisUntilFinished: Long) {
                // Format the time and update the button
            }

            override fun onFinish() {
                // Reset the timer and the button when the timer finishes
                timerRunning = false
                hideUpgradePrompt()
            }
        }


        return view
    }
    private fun hideUpgradePrompt() {
        // Delay the change to hide the upgrade prompt for 1 second
        Handler().postDelayed({
            upgradePrompt.visibility = View.GONE
        }, 1_000)
    }
    private fun getTimeString(millis: Long): String {
        val minutes = TimeUnit.MILLISECONDS.toMinutes(millis)
        val seconds = TimeUnit.MILLISECONDS.toSeconds(millis) -
                TimeUnit.MINUTES.toSeconds(minutes)
        return "%02d:%02d".format(minutes, seconds)
    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment Discover.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Discover().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}