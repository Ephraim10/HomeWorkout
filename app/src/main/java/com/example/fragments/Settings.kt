package com.example.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.CheckBox
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Spinner
import android.widget.Toast

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Settings.newInstance] factory method to
 * create an instance of this fragment.
 */
class Settings : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var genderGroup: RadioGroup
    private lateinit var maleButton: RadioButton
    private lateinit var femaleButton: RadioButton
    private lateinit var otherButton: RadioButton
    private lateinit var goalSpinner: Spinner
    private lateinit var notificationCheckbox: CheckBox
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
        val view = inflater.inflate(R.layout.fragment_settings, container, false)

        // Initialize the views
        genderGroup = view.findViewById(R.id.gender_group)
        maleButton = view.findViewById(R.id.male_button)
        femaleButton = view.findViewById(R.id.female_button)
        otherButton = view.findViewById(R.id.other_button)
        goalSpinner = view.findViewById(R.id.goal_spinner)
        notificationCheckbox = view.findViewById(R.id.notification_checkbox)
        // Set the workout goals array as the data source for the Spinner
        val goalsAdapter = ArrayAdapter.createFromResource(requireContext(), R.array.workout_goals, android.R.layout.simple_spinner_item)
        goalsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        goalSpinner.adapter = goalsAdapter

        // Set the selection state and the click listener for the notification CheckBox
        notificationCheckbox.isChecked = true
        notificationCheckbox.setOnClickListener {
            if (notificationCheckbox.isChecked) {
                Toast.makeText(requireContext(), "Workout reminders enabled", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(requireContext(), "Workout reminders disabled", Toast.LENGTH_SHORT).show()
            }
        }

        return view
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Set the title and the selection states for the gender RadioButtons
        requireActivity().title = getString(R.string.settings_title)
        maleButton.isChecked = true
    }
    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment Settings.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Settings().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}