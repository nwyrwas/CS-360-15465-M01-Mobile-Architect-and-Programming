package com.nickwyrwas.projecttwo_eventtrackingapp

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class DataDisplayActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_data_display)

        val eventListView = findViewById<ListView>(R.id.eventListView)
        val dbHelper = DatabaseHelper(this)

        // Fetching events from the database
        val events = dbHelper.getAllEvents()

        // Ensure the list is not empty
        if (events.isNotEmpty()) {
            // Creating an adapter to display event names and dates
            val eventNames = events.map { "${it.eventName} - ${it.eventDate}" }
            val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, eventNames)
            eventListView.adapter = adapter
        } else {
            Toast.makeText(this, "No events found", Toast.LENGTH_SHORT).show()
        }

        // Handling update button click
        val updateEventButton = findViewById<Button>(R.id.updateEventButton)
        updateEventButton.setOnClickListener {
            if (events.isNotEmpty()) {
                val firstEvent = events[0] // Example: updating the first event
                val updated = dbHelper.updateEvent(firstEvent.id, "Updated Event", "2025-05-01")
                if (updated) {
                    Toast.makeText(this, "Event updated successfully", Toast.LENGTH_SHORT).show()
                    // Refresh the event list after updating
                    val updatedEvents = dbHelper.getAllEvents()
                    val updatedEventNames = updatedEvents.map { "${it.eventName} - ${it.eventDate}" }
                    eventListView.adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, updatedEventNames)
                } else {
                    Toast.makeText(this, "Event update failed", Toast.LENGTH_SHORT).show()
                }
            }
        }

        // Handling delete button click
        val deleteEventButton = findViewById<Button>(R.id.deleteEventButton)
        deleteEventButton.setOnClickListener {
            if (events.isNotEmpty()) {
                val firstEvent = events[0] // Example: deleting the first event
                val deleted = dbHelper.deleteEvent(firstEvent.id)
                if (deleted) {
                    Toast.makeText(this, "Event deleted successfully", Toast.LENGTH_SHORT).show()
                    // Refresh the event list after deletion
                    val updatedEvents = dbHelper.getAllEvents()
                    val updatedEventNames = updatedEvents.map { "${it.eventName} - ${it.eventDate}" }
                    eventListView.adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, updatedEventNames)
                } else {
                    Toast.makeText(this, "Event deletion failed", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}
