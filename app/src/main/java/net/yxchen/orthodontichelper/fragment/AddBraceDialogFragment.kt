package net.yxchen.orthodontichelper.fragment

import android.app.Activity
import android.database.sqlite.SQLiteOpenHelper
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.*
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import net.yxchen.orthodontichelper.MainActivity
import net.yxchen.orthodontichelper.R
import net.yxchen.orthodontichelper.database.MyDatabaseHelper


class AddBraceDialogFragment(private val dbHelper: MyDatabaseHelper, private val activity: MainActivity) : DialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.add_brace_fragment, container, false)
        val button = view.findViewById<Button>(R.id.addBrace)
        val braceName = view.findViewById<EditText>(R.id.braceName)
        button.setOnClickListener {
            dbHelper.insertBrace(dbHelper.writableDatabase, braceName.text.toString())
            activity.initBracesMenuAndFragment()
            this.dismiss()
        }
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        isCancelable = true
        val window = dialog!!.window
        window?.setGravity(Gravity.BOTTOM)
//        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        val params: WindowManager.LayoutParams? = window?.attributes
        params?.gravity = Gravity.CENTER
        // 使用ViewGroup.LayoutParams，以便Dialog 宽度充满整个屏幕
        // 使用ViewGroup.LayoutParams，以便Dialog 宽度充满整个屏幕
        params?.width = ViewGroup.LayoutParams.MATCH_PARENT
        params?.height = ViewGroup.LayoutParams.WRAP_CONTENT
        window?.attributes = params
    }
}