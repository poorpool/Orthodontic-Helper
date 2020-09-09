package net.yxchen.orthodontichelper

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.core.view.GravityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import net.yxchen.orthodontichelper.adapter.BraceAdapter
import net.yxchen.orthodontichelper.database.MyDatabaseHelper
import net.yxchen.orthodontichelper.pojo.Brace
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {
    private val dbHelper = MyDatabaseHelper(this, "OrthodonticHelper.db", 1)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        supportActionBar?.let {
            it.setDisplayHomeAsUpEnabled(true)
            it.setHomeAsUpIndicator(R.drawable.ic_menu)
        }

        dbHelper.writableDatabase

        initBracesMenu()










        /*
        initBraces()
        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
        val adapter = BraceAdapter(braceList)
        recyclerView.adapter = adapter*/
    }

    private fun initBracesMenu() {

        val braceList = dbHelper.queryAllBraces(dbHelper.writableDatabase)

        Toast.makeText(this, braceList.toString(), Toast.LENGTH_LONG).show()
        val menu = navView.menu

        for (i in braceList.size - 1 downTo 0) {
            menu.add(i, i, i, braceList[i].name)
        }
    }

/*
    private fun initBraces() {
        repeat(5) {
            braceList.add(Brace("第一副", Date(), false))
        }
    }*/

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.add_item -> Toast.makeText(this, "Add item", Toast.LENGTH_SHORT).show()
            android.R.id.home -> drawerLayout.openDrawer(GravityCompat.START)
        }
        return true
    }
}