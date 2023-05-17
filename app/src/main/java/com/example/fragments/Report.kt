package com.example.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.Toast
import org.json.JSONException
import org.json.JSONObject
import java.io.File
import java.io.FileWriter
import java.io.IOException

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Report.newInstance] factory method to
 * create an instance of this fragment.
 */
class Report : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var reportTitleEditText: EditText
    private lateinit var trainingFeedbackEditText: EditText
    private lateinit var goalsEditText: EditText
    private lateinit var reportListView: ListView

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
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_report, container, false)

        // Get references to the report input fields
        reportTitleEditText = rootView.findViewById(R.id.reportTitle)
        trainingFeedbackEditText = rootView.findViewById(R.id.trainingFeedback)
        goalsEditText = rootView.findViewById(R.id.goals)

        // Set a click listener for the save button
        val saveReportButton = rootView.findViewById<Button>(R.id.saveReportButton)
        saveReportButton.setOnClickListener {
            saveReport()
        }
        // Set up the list view for displaying saved reports
        reportListView = rootView.findViewById(R.id.reportListView)
        val reports = loadReports()
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, reports)
        reportListView.adapter = adapter

        // Set a click listener for the list view items
        reportListView.setOnItemClickListener { parent, view, position, id ->
            val reportTitle = parent.getItemAtPosition(position) as String
            val report = loadReport(reportTitle)
            reportTitleEditText.setText(report.optString("title"))
            trainingFeedbackEditText.setText(report.optString("feedback"))
            goalsEditText.setText(report.optString("goals"))
        }


        return rootView
    }
    private fun saveReport() {
        // Get the user's report inputs
        val reportTitle = reportTitleEditText.text.toString().trim()
        val trainingFeedback = trainingFeedbackEditText.text.toString().trim()
        val goals = goalsEditText.text.toString().trim()

        // Create a JSONObject to store the report data
        val reportData = JSONObject()
        try { reportData.put("title", reportTitle)
            reportData.put("feedback", trainingFeedback)
            reportData.put("goals", goals)
        } catch (e: JSONException) {
            e.printStackTrace()
        }

        // Save the report to a file
        val reportsDirectory = File(requireActivity().filesDir, "reports")
        if (!reportsDirectory.exists()) {
            reportsDirectory.mkdirs()
        }
        val reportFile = File(reportsDirectory, "$reportTitle.json")
        try {
            val writer = FileWriter(reportFile)
            writer.write(reportData.toString())
            writer.close()
            Toast.makeText(requireContext(), R.string.report_saved_message, Toast.LENGTH_SHORT).show()

            // Add the report to the list view
            val adapter = reportListView.adapter as ArrayAdapter<String>
            adapter.add(reportTitle)
        } catch (e: IOException) {
            e.printStackTrace()
            Toast.makeText(requireContext(), R.string.report_saved_error_message, Toast.LENGTH_SHORT).show()
        }
    }
    private fun loadReports(): List<String> {
        val reportsDirectory = File(requireActivity().filesDir, "reports")
        if (reportsDirectory.exists()) {
            return reportsDirectory.listFiles()
                .filter { it.isFile }
                .map { it.name.replace(".json", "") }
        }

        return emptyList()
    }

    private fun loadReport(reportTitle: String): JSONObject {
        val reportsDirectory = File(requireActivity().filesDir, "reports")
        val reportFile = File(reportsDirectory, "$reportTitle.json")
        return if (reportFile.exists()) {
            try {
                JSONObject(reportFile.readText())
            } catch (e: JSONException) {
                e.printStackTrace()
                JSONObject()
            }
        } else {
            JSONObject()
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment Report.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Report().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}