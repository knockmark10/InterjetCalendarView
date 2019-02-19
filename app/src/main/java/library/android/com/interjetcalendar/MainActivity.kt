package library.android.com.interjetcalendar

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        construct()
    }

    private fun construct() {
        this.calendar
            .setHeaderColor(R.color.colorAccent)
            .setDayColor(R.color.colorPrimary)
            .setWeekIndicatorColor(R.color.colorPrimaryDark)
            .setDaySelectedColor(R.color.colorSelected)
            .setRangeColor(R.color.colorRange)
            .create(Calendar.getInstance())

        this.calendar.setOnDaySelectedListener {
            Log.d("12", it.dayToDisplay)
        }

    }
}
