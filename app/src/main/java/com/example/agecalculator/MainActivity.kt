package com.example.agecalculator

import android.app.DatePickerDialog
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var selectDateTV: TextView
    private lateinit var convertIntoTime: TextView
    private lateinit var calculateBtn: Button
    private lateinit var dateToSdf: Date

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        selectDateTV = findViewById(R.id.dobTv)
        convertIntoTime = findViewById(R.id.timeTV)
        calculateBtn = findViewById(R.id.calculateBtn)

        selectDateTV.setOnClickListener {
            pickUpDate(it)
        }

        calculateBtn.setOnClickListener {
            convertDateToTime()
        }

    }


    private fun pickUpDate(view: View) {
        val myCalendar = Calendar.getInstance()
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MONTH)
        val day = myCalendar.get(Calendar.DAY_OF_MONTH)


        //open the calendar
        val dpd = DatePickerDialog(
            this,
            DatePickerDialog.OnDateSetListener { view, selectedYear, selectedMonth, selectedDayOfMonth ->

                val selectedDate = "$selectedDayOfMonth/${selectedMonth + 1}/$selectedYear"
                selectDateTV.text = selectedDate

                val sdf = SimpleDateFormat("dd/MM/yyy", Locale.ENGLISH)
                dateToSdf = sdf.parse(selectedDate) as Date


            },
            year, month, day
        )

        //setting the max date in Calendar
        dpd.datePicker.maxDate = Date().time - 86400000
        dpd.show()

    }


    private fun convertDateToTime() {
        val sdf = SimpleDateFormat("dd/MM/yyy", Locale.ENGLISH)

        //convert the selected date into Minutes
        val selectedDateIntoMin = dateToSdf.time / 60000

        //select the current date
        val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))

        //convert the current date into minute
        val currentDateIntoMinute = currentDate!!.time / 60000

        //subtract from current date to selectDate
        val differenceTime = currentDateIntoMinute - selectedDateIntoMin

        convertIntoTime.text = differenceTime.toString()
        convertIntoTime.setBackgroundColor(Color.GRAY)
    }
}