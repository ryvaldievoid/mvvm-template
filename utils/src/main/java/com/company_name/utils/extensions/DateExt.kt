package com.company_name.utils.extensions

import android.app.DatePickerDialog
import android.content.Context
import android.widget.DatePicker
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import java.util.Calendar.*
import kotlin.properties.Delegates

val currentCalendar by lazy { Calendar.getInstance() }
var isDateAssigned by Delegates.observable(false) { property, oldValue, newValue -> }
var dateStr = ""

fun dateFormatter(format: String = "yyyy-MM-dd", isLocale: Boolean = true) =
        SimpleDateFormat(
                format,
                Locale(if (isLocale) "com" else "en") // TODO: support any other locale?
        )

fun Calendar.getDate(year: Int, month: Int, dayOfMonth: Int): Date =
        with(this) {
            set(year, month, dayOfMonth)
            time
        }

fun Calendar.getCalendarPeriod(alreadySelected: Boolean, state: Int): Int = with(this) {
    if (alreadySelected) get(state) else currentCalendar.get(state)
}

fun Calendar.getCalendarPeriod(str: String?, state: Int): Int = with(this) {
    if (str.isNullOrBlank()) get(state) else str.getCalendarFromString().get(state)
}

@Throws(ParseException::class)
fun String.getCalendarFromString(): Calendar = currentCalendar.apply {
    time = dateFormatter().parse(this@getCalendarFromString)
}

fun Context.showDate(
        /*add new param to fit our use case*/
        todo: (String) -> String
) {
    Calendar.getInstance().apply {
        add(YEAR, -20)
        val dialog by lazy {
            DatePickerDialog(
                    this@showDate,
                    // Using lambda for cleaner approach
                    { _: DatePicker, year: Int, month: Int, dayOfMonth: Int ->
                        isDateAssigned = true
                        dateStr = todo(
                                dateFormatter().format(getDate(year, month, dayOfMonth))
                        )
                    },
                    getCalendarPeriod(dateStr, YEAR),
                    getCalendarPeriod(dateStr, MONTH),
                    getCalendarPeriod(dateStr, DAY_OF_MONTH)
            ).apply {
                // TODO: Set minDate as well?
                datePicker.maxDate =
                        /*if (isAboveHoneyComb) DateTime().minusYears(19).millis
                        else */timeInMillis
            }
        }

        dialog.show()
    }
}

val Long.unixTime: Long
    get() = this / 1000L