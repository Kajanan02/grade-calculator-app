package com.example.gradecalculater

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.compose.ui.tooling.preview.Preview
import com.example.gradecalculater.ui.theme.GradeCalculaterTheme

class MainActivity : ComponentActivity() {
    private lateinit var editTextMarks: EditText
    private lateinit var buttonCalculate: Button
    private lateinit var buttonReset: Button
    private lateinit var textViewResult: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editTextMarks = findViewById(R.id.editTextMarks)
        buttonCalculate = findViewById(R.id.buttonCalculate)
        buttonReset = findViewById(R.id.buttonReset)
        textViewResult = findViewById(R.id.textViewResult)

        buttonCalculate.setOnClickListener {
            calculateGrade()
        }

        buttonReset.setOnClickListener {
            resetFields()
        }
    }


    private fun calculateGrade() {
        val marksText = editTextMarks.text.toString()
        if (marksText.isBlank()) {
            textViewResult.setText(R.string.enter_marks_error)
            textViewResult.setTextColor(Color.RED)
            return
        }

        val marks = marksText.toInt()
        if (marks < 0 || marks > 100) {
            textViewResult.setText(R.string.marks_range_error)
            textViewResult.setTextColor(Color.RED)
            return
        }

        val grade: String = calculateGradeFromMarks(marks)
        textViewResult.text = getString(R.string.grade_result, grade)
        if(grade.contains("Fail"))
            textViewResult.setTextColor(Color.RED)
        else
            textViewResult.setTextColor(Color.BLACK)
    }

    private fun calculateGradeFromMarks(marks: Int): String {
        return when {
            marks >= 75 -> "A Excellent"
            marks >= 65 -> "B Very Good"
            marks >= 55 -> "C Good"
            marks >= 40 -> "D Pass"
            else -> "F Fail"
        }
    }
    private fun resetFields() {
        editTextMarks.text.clear()
        textViewResult.text = ""
    }
}



@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    GradeCalculaterTheme {
        Greeting("Android")
    }
}