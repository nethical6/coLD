package nethical.cold.utils

import android.content.Context
import android.view.ViewGroup
import nethical.cold.Project
import nethical.cold.widgets.view.ButtonItem
import nethical.cold.widgets.view.TextViewItem
import nethical.cold.widgets.viewgroup.LinearLayoutItem

class AddView(type: String, holder: ViewGroup, context: Context, project: Project, index: Int = holder.childCount) {
    init {
        when (type){
            "Linear Layout" -> {
                LinearLayoutItem(context,holder, index, project)
            }

            "Button" -> {
                ButtonItem(holder, index, project)
            }

            "TextView" -> {
                TextViewItem(context, holder, index, project)
            }
        }
    }
}