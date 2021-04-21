package chumanhhailtt.sevenminutesworkout

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper(context: Context) : SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION) {

    companion object {
        val DB_NAME = "HISTORY"
        val DB_VERSION = 1
        val TABLE_HISTORY = "history"
        val KEY_DATE = "date"
        val KEY_PROGRESS_PERCENT = "progress_percent"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val sqlScript = "CREATE TABLE " + TABLE_HISTORY + "(" +
                KEY_DATE + " TEXT NOT NULL," +
                KEY_PROGRESS_PERCENT + " INTEGER NOT NULL)"
        db?.execSQL(sqlScript)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS " + TABLE_HISTORY)
        onCreate(db)
    }

    fun addDate(hist: History): Long {
        val db = writableDatabase
        val cv = ContentValues()

        cv.put(KEY_DATE, hist.date)
        cv.put(KEY_PROGRESS_PERCENT, hist.progressPercent)
        val result = db.insert(TABLE_HISTORY, null, cv)
        db.close()

        return result
    }

    fun getAllDates(): ArrayList<History> {
        val db = readableDatabase
        val cursor = db.rawQuery("SELECT * FROM " + TABLE_HISTORY, null)
        val histories = ArrayList<History>()
        var date: String
        var hist: Int

        cursor.moveToFirst()
        while(cursor.isAfterLast() == false) {
            date = cursor.getString(cursor.getColumnIndex(KEY_DATE))
            hist = cursor.getInt((cursor.getColumnIndex(KEY_PROGRESS_PERCENT)))
            histories.add(History(date, hist))
            cursor.moveToNext()
        }
        cursor.close()
        db.close()

        return histories
    }
}