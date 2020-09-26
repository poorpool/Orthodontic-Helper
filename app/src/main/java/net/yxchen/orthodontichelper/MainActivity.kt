package net.yxchen.orthodontichelper

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import kotlinx.android.synthetic.main.activity_main.*
import net.yxchen.orthodontichelper.database.MyDatabaseHelper
import net.yxchen.orthodontichelper.fragment.AddBraceDialogFragment
import net.yxchen.orthodontichelper.fragment.BraceFragment
import net.yxchen.orthodontichelper.pojo.Brace
import java.lang.Exception


class MainActivity : AppCompatActivity() {
    private val dbHelper = MyDatabaseHelper(this, "OrthodonticHelper.db", 1)

    private lateinit var braceList : List<Brace>

    private lateinit var fragment : BraceFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        supportActionBar?.let {
            it.setDisplayHomeAsUpEnabled(true)
            it.setHomeAsUpIndicator(R.drawable.ic_menu)
        }

        dbHelper.writableDatabase

        initBracesMenuAndFragment()

        /*
        initBraces()
        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
        val adapter = BraceAdapter(braceList)
        recyclerView.adapter = adapter*/
    }

    private fun refreshBracesMenu() {
        braceList = dbHelper.queryAllBraces(dbHelper.writableDatabase)

        braceList = braceList.reversed()

//        Toast.makeText(this, braceList.toString(), Toast.LENGTH_LONG).show()
        val menu = navView.menu

        menu.clear()

        for (i in braceList.indices) {
            menu.add(i, i, i, braceList[i].name)
        }
        navView.setNavigationItemSelectedListener {
            fragment.refresh(braceList[it.order])
            drawerLayout.closeDrawer(navView)
            return@setNavigationItemSelectedListener true
        }
    }

    fun initBracesMenuAndFragment() {
        refreshBracesMenu()
        fragment = braceFragment as BraceFragment
        fragment.refresh(braceList.first())
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
            R.id.add_item -> {
                try {
                    val dialogFragment = AddBraceDialogFragment(dbHelper, this)
                    dialogFragment.show(this.supportFragmentManager, "addBrace")
                } catch (e : Exception) {
                    Toast.makeText(this, e.toString(), Toast.LENGTH_LONG).show()
                }
            }
            android.R.id.home -> drawerLayout.openDrawer(GravityCompat.START)
        }
        return true
    }


}