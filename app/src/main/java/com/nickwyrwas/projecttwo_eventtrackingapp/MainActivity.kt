package com.nickwyrwas.projecttwo_eventtrackingapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val viewEventsButton = findViewById<Button>(R.id.viewEventsButton)
        val smsPermissionButton = findViewById<Button>(R.id.smsPermissionButton)
        val addEventButton = findViewById<Button>(R.id.addEventButton)

        viewEventsButton.setOnClickListener {
            // Open DataDisplayActivity to view the events
            startActivity(Intent(this, DataDisplayActivity::class.java))
        }

        smsPermissionButton.setOnClickListener {
            // Open SMSPermissionActivity for handling SMS permissions
            startActivity(Intent(this, SMSPermissionActivity::class.java))
        }

        addEventButton.setOnClickListener {
            // Open AddEventActivity to add a new event
            val intent = Intent(this, AddEventActivity::class.java)
            startActivity(intent)
        }
    }

    // Method to update an event
    private fun updateEvent(eventId: Int, newName: String, newDate: String) {
        val dbHelper = DatabaseHelper(this)
        val success = dbHelper.updateEvent(eventId, newName, newDate)
        if (success) {
            // Update successful, show a message or update the UI accordingly
            Toast.makeText(this, "Event updated successfully", Toast.LENGTH_SHORT).show()
        } else {
            // Handle failure (e.g., show a toast message)
            Toast.makeText(this, "Failed to update event", Toast.LENGTH_SHORT).show()
        }
    }

    // Method to delete an event
    private fun deleteEvent(eventId: Int) {
        val dbHelper = DatabaseHelper(this)
        val success = dbHelper.deleteEvent(eventId)
        if (success) {
            // Deletion successful, show a message or update the UI accordingly
            Toast.makeText(this, "Event deleted successfully", Toast.LENGTH_SHORT).show()
        } else {
            // Handle failure (e.g., show a toast message)
            Toast.makeText(this, "Failed to delete event", Toast.LENGTH_SHORT).show()
        }
    }
}
