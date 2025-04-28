package com.nickwyrwas.projecttwo_eventtrackingapp

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import android.util.Log

class EditEventActivity : AppCompatActivity() {

    private var eventId: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_event)

        val eventNameEditText = findViewById<EditText>(R.id.eventNameEditText)
        val eventDateEditText = findViewById<EditText>(R.id.eventDateEditText)
        val saveUpdatedEventButton = findViewById<Button>(R.id.saveUpdatedEventButton)

        // Retrieve the event details passed from DataDisplayActivity
        eventId = intent.getIntExtra("eventId", -1)
        val eventName = intent.getStringExtra("eventName")
        val eventDate = intent.getStringExtra("eventDate")

        // Pre-fill the EditText fields with the current event details
        eventNameEditText.setText(eventName)
        eventDateEditText.setText(eventDate)

        saveUpdatedEventButton.setOnClickListener {
            // Get updated data from the user
            val updatedEventName = eventNameEditText.text.toString()
            val updatedEventDate = eventDateEditText.text.toString()

            if (updatedEventName.isNotEmpty() && updatedEventDate.isNotEmpty()) {
                val dbHelper = DatabaseHelper(this)
                val success = dbHelper.updateEvent(eventId, updatedEventName, updatedEventDate)

                Log.d("EditEventActivity", "Updating Event - ID: $eventId, Name: $updatedEventName, Date: $updatedEventDate")

                if (success) {
                    Toast.makeText(this, "Event updated successfully", Toast.LENGTH_SHORT).show()
                    finish()  // Close the activity and return to DataDisplayActivity
                } else {
                    Toast.makeText(this, "Failed to update event", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Please enter both event name and date", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
