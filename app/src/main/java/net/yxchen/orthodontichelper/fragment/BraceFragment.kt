package net.yxchen.orthodontichelper.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.brace_fragment.*
import net.yxchen.orthodontichelper.R
import net.yxchen.orthodontichelper.pojo.Brace
import java.text.SimpleDateFormat

class BraceFragment : Fragment() {
    private val dateFormatter = SimpleDateFormat("yyyy-MM-dd")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.brace_fragment, container, false)
    }

    fun refresh(brace: Brace) {
        braceName.text = brace.name
        braceStartDate.text = dateFormatter.format(brace.startDate)
    }
}