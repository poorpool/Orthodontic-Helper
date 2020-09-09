package net.yxchen.orthodontichelper.pojo

import java.util.*
import kotlin.collections.ArrayList

class Brace(val name: String, val startDate: Date, var ended: Boolean) { // 牙套=
    var id: Int = 1
    var endDate: Date = Date()
    var timeList: List<Pair<Date, Date> > = ArrayList()
}