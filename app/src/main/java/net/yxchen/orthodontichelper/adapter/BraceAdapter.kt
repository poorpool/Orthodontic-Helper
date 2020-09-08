package net.yxchen.orthodontichelper.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.brace_menu_item.view.*
import net.yxchen.orthodontichelper.R
import net.yxchen.orthodontichelper.pojo.Brace

class BraceAdapter(val braceList: List<Brace>) : RecyclerView.Adapter<BraceAdapter.ViewHolder>() {
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val braceName: TextView = view.findViewById(R.id.braceName)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.brace_menu_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = braceList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val brace = braceList[position]
        holder.braceName.text = brace.name
    }
}