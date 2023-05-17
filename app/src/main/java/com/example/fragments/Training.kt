package com.example.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import exercises.BowActivity
import exercises.BridgeActivity
import exercises.ChildActivity
import exercises.CoblerActivity
import exercises.CowActivity
import exercises.CrunchActivity
import exercises.LegActivity
import exercises.PausejiActivity
import exercises.PlankActivity
import exercises.PlayjiActivity
import exercises.RotActivity
import exercises.SitActivity
import exercises.TwistActivity
import exercises.WindActivity
import exercises.chairActivity

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Training.newInstance] factory method to
 * create an instance of this fragment.
 */
class Training : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

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
        val rootView = inflater.inflate(R.layout.fragment_training, container, false)

        // Find the linear layouts you want to bind and set a click listener
        val linearLayout1: LinearLayout = rootView.findViewById(R.id.bow_pose)
        linearLayout1.setOnClickListener {
            val intent = Intent(activity, BowActivity::class.java)
            startActivity(intent)
        }
        val linearLayout2: LinearLayout = rootView.findViewById(R.id.bridge_pose)
        linearLayout2.setOnClickListener {
            val intent = Intent(activity, BridgeActivity::class.java)
            startActivity(intent)
        }
        val linearLayout: LinearLayout = rootView.findViewById(R.id.chair_pose)
        linearLayout.setOnClickListener {
            val intent = Intent(activity, chairActivity::class.java)
            startActivity(intent)
        }
        val linearLayout3: LinearLayout = rootView.findViewById(R.id.child_pose)
        linearLayout3.setOnClickListener {
            val intent = Intent(activity, ChildActivity::class.java)
            startActivity(intent)
        }
        val linearLayout4: LinearLayout = rootView.findViewById(R.id.cobbler_pose)
        linearLayout4.setOnClickListener {
            val intent = Intent(activity, CoblerActivity::class.java)
            startActivity(intent)
        }
        val linearLayout5: LinearLayout = rootView.findViewById(R.id.cow_pose)
        linearLayout5.setOnClickListener {
            val intent = Intent(activity, CowActivity::class.java)
            startActivity(intent)
        }
        val linearLayout6: LinearLayout = rootView.findViewById(R.id.playji_pose)
        linearLayout6.setOnClickListener {
            val intent = Intent(activity, PlayjiActivity::class.java)
            startActivity(intent)
        }
        val linearLayout7: LinearLayout = rootView.findViewById(R.id.pauseji_pose)
        linearLayout7.setOnClickListener {
            val intent = Intent(activity, PausejiActivity::class.java)
            startActivity(intent)
        }
        val linearLayout8: LinearLayout = rootView.findViewById(R.id.plank_pose)
        linearLayout8.setOnClickListener {
            val intent = Intent(activity, PlankActivity::class.java)
            startActivity(intent)
        }
        val linearLayout9: LinearLayout = rootView.findViewById(R.id.crunches_pose)
        linearLayout9.setOnClickListener {
            val intent = Intent(activity, CrunchActivity::class.java)
            startActivity(intent)
        }
        val linearLayout10: LinearLayout = rootView.findViewById(R.id.situp_pose)
        linearLayout10.setOnClickListener {
            val intent = Intent(activity, SitActivity::class.java)
            startActivity(intent)
        }
        val linearLayout11: LinearLayout = rootView.findViewById(R.id.rotation_pose)
        linearLayout11.setOnClickListener {
            val intent = Intent(activity, RotActivity::class.java)
            startActivity(intent)
        }
        val linearLayout12: LinearLayout = rootView.findViewById(R.id.twist_pose)
        linearLayout12.setOnClickListener {
            val intent = Intent(activity, TwistActivity::class.java)
            startActivity(intent)
        }
        val linearLayout14: LinearLayout = rootView.findViewById(R.id.windmill_pose)
        linearLayout14.setOnClickListener {
            val intent = Intent(activity, WindActivity::class.java)
            startActivity(intent)
        }
        val linearLayout15: LinearLayout = rootView.findViewById(R.id.legup_pose)
        linearLayout15.setOnClickListener {
            val intent = Intent(activity, LegActivity::class.java)
            startActivity(intent)
        }
        return rootView
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment Training.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Training().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}