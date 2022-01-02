package nethical.cold.widgets.view

import android.content.Context
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import nethical.cold.Project
import nethical.cold.widgets.WidgetUtils

class TextViewItem(context: Context, holder : ViewGroup, index : Int, project: Project) : BaseView.DragListener {
    val temp_text_view  = TextView(context)
    init {
        val baseView = BaseView(context,project)
        baseView.resize(temp_text_view)
        temp_text_view.text = "TextView"
        temp_text_view.setPadding(8,8,8,8)
        temp_text_view.layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT)
        holder.addView(temp_text_view,index)
        baseView.CreateDragListener(temp_text_view,holder,this)
        baseView.CreateDragger(temp_text_view,holder,"TextView")

    }
}