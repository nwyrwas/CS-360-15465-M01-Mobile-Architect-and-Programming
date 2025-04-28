package com.nickwyrwas.projecttwo_eventtrackingapp

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class AddEventActivity : AppCompatActivity() {

    private lateinit var eventNameInput: EditText
    private lateinit var eventDateInput: EditText
    private lateinit var addEventButton: Button
    private lateinit var db: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_event)

        eventNameInput = findViewById(R.id.eventNameInput)
        eventDateInput = findViewById(R.id.eventDateInput)
        addEventButton = findViewById(R.id.addEventButton)

        db = DatabaseHelper(this)

        addEventButton.setOnClickListener {
            val eventName = eventNameInput.text.toString()
            val eventDate = eventDateInput.text.toString()

            Log.d("AddEventActivity", "Event Name: $eventName, Event Date: $eventDate")

            // Validate the input
            if (eventName.isEmpty() || eventDate.isEmpty()) {
                Toast.makeText(this, "Please fill in both fields", Toast.LENGTH_SHORT).show()
            } else {
                val success = db.addEvent(eventName, eventDate)

                // Display appropriate toast message
                if (success) {
                    Toast.makeText(this, "Event added successfully", Toast.LENGTH_SHORT).show()
                    finish() // Close the activity after adding the event
                } else {
                    Toast.makeText(this, "Failed to add event", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}
