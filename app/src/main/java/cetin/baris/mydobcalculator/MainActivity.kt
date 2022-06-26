package cetin.baris.mydobcalculator

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.text.SimpleDateFormat
import java.time.Year
import java.util.*

class MainActivity : AppCompatActivity() {
    private var tvSelectedDate : TextView? = null
    private var tvDifference : TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnDatePicker: Button = findViewById(R.id.btnDatePicker)

        btnDatePicker.setOnClickListener {
            clickDateBtn()
        }

    }

    fun clickDateBtn() {
        tvSelectedDate = findViewById(R.id.dateView)
        val myCalendar = Calendar.getInstance()
        tvDifference = findViewById(R.id.tvDifference)
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MONTH)
        val dayOfMonth = myCalendar.get(Calendar.DAY_OF_MONTH)

        val dpd = DatePickerDialog(this,
        DatePickerDialog.OnDateSetListener{view,selectedYear,selectedMonth,selectedDay ->
            val selectedDate = "$selectedDay/${selectedMonth+1}/$selectedYear"
            tvSelectedDate?.text = selectedDate
            val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
            val theDate = sdf.parse(selectedDate)
            val selectedDateinMunites = theDate.time / 60000
            val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))
            val currentDateinMunites = currentDate.time / 60000

            val differenceInMunites = currentDateinMunites - selectedDateinMunites
            tvDifference?.text = differenceInMunites.toString()
        },
            year,
            month,
            dayOfMonth
            )
        dpd.datePicker.maxDate = System.currentTimeMillis() - 86400000

        dpd.show()


    }

}