package com.nickwyrwas.projecttwo_eventtrackingapp

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "eventTracker.db"
        private const val DATABASE_VERSION = 1

        private const val TABLE_USER = "User"
        private const val COLUMN_USER_ID = "id"
        private const val COLUMN_USERNAME = "username"
        private const val COLUMN_PASSWORD = "password"

        private const val TABLE_EVENT = "Event"
        private const val COLUMN_EVENT_ID = "id"
        private const val COLUMN_EVENT_NAME = "name"
        private const val COLUMN_EVENT_DATE = "date"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createUserTable = ("CREATE TABLE $TABLE_USER (" +
                "$COLUMN_USER_ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "$COLUMN_USERNAME TEXT," +
                "$COLUMN_PASSWORD TEXT)")
        db?.execSQL(createUserTable)

        val createEventTable = ("CREATE TABLE $TABLE_EVENT (" +
                "$COLUMN_EVENT_ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "$COLUMN_EVENT_NAME TEXT," +
                "$COLUMN_EVENT_DATE TEXT)")
        db?.execSQL(createEventTable)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_USER")
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_EVENT")
        onCreate(db)
    }

    fun addUser(username: String, password: String): Boolean {
        val db = writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_USERNAME, username)
            put(COLUMN_PASSWORD, password)
        }
        val result = db.insert(TABLE_USER, null, values)
        return result != -1L
    }

    fun checkUser(username: String, password: String): Boolean {
        val db = readableDatabase
        val cursor = db.rawQuery(
            "SELECT * FROM $TABLE_USER WHERE $COLUMN_USERNAME = ? AND $COLUMN_PASSWORD = ?",
            arrayOf(username, password)
        )
        val exists = cursor.count > 0
        cursor.close()
        return exists
    }

    fun addEvent(name: String, date: String): Boolean {
        val db = writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_EVENT_NAME, name)
            put(COLUMN_EVENT_DATE, date)
        }
        try {
            val result = db.insert(TABLE_EVENT, null, values)
            if (result == -1L) {
                Log.e("DatabaseHelper", "Failed to insert event")
                return false
            }
            Log.d("DatabaseHelper", "Event added successfully: $name, $date")
            return true
        } catch (e: Exception) {
            Log.e("DatabaseHelper", "Error adding event: ${e.message}")
            return false
        }
    }

    fun getAllEvents(): ArrayList<Event> {
        val eventList = ArrayList<Event>()
        val db = readableDatabase
        val cursor = db.rawQuery("SELECT * FROM $TABLE_EVENT", null)
        if (cursor.moveToFirst()) {
            do {
                val event = Event(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2)
                )
                eventList.add(event)
            } while (cursor.moveToNext())
        }
        cursor.close()
        return eventList
    }

    fun updateEvent(id: Int, name: String, date: String): Boolean {
        val db = writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_EVENT_NAME, name)
            put(COLUMN_EVENT_DATE, date)
        }
        val result = db.update(TABLE_EVENT, values, "$COLUMN_EVENT_ID = ?", arrayOf(id.toString()))
        return result > 0
    }

    fun deleteEvent(id: Int): Boolean {
        val db = writableDatabase
        val result = db.delete(TABLE_EVENT, "$COLUMN_EVENT_ID = ?", arrayOf(id.toString()))
        return result > 0
    }
}
