package net.yxchen.orthodontichelper.database

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast
import net.yxchen.orthodontichelper.pojo.Brace
import java.text.SimpleDateFormat
import java.util.*

class MyDatabaseHelper(val context: Context, name: String, version: Int) : SQLiteOpenHelper(context, name, null, version) {
    private val createBrace = "create table Brace (\n" +
            "    id integer primary key autoincrement,\n" +
            "    name text,\n" +
            "    start_date text,\n" +
            "    ended integer,\n" +
            "    end_date text\n" +
            ")"

    private val createBraceAndStop = "create table BraceAndStop (\n" +
            "    id integer primary key autoincrement,\n" +
            "    brace_id integer,\n" +
            "    start_date text,\n" +
            "    end_date text,\n" +
            "    ended integer\n" +
            ")"

    private val dateFormatter = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")

    fun insertBrace(db: SQLiteDatabase, name: String) {
        val nowDate = Date()
        val values = ContentValues().apply {
            put("name", name)
            put("start_date", dateFormatter.format(nowDate))
            put("ended", 0)
            put("end_date", dateFormatter.format(nowDate))
        }
        db.insert("Brace", null, values)
    }

    fun queryAllBraces(db: SQLiteDatabase): List<Brace> {
        val cursor = db.query("Brace", null, null, null, null, null, null, null)
        val list = ArrayList<Brace>()
        if (cursor.moveToFirst()) {
            do {
                val brace = Brace(cursor.getString(cursor.getColumnIndex("name")),
                    dateFormatter.parse(cursor.getString(cursor.getColumnIndex("start_date"))),
                    cursor.getInt(cursor.getColumnIndex("ended")) > 0)
                brace.endDate = dateFormatter.parse(cursor.getString(cursor.getColumnIndex("end_date")))
                brace.id = cursor.getInt(cursor.getColumnIndex("id"))
                list.add(brace)
            } while (cursor.moveToNext())
        }
        cursor.close()
        return list
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(createBrace)
        db.execSQL(createBraceAndStop)
        insertBrace(db, "第一副牙套")
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
    }

}