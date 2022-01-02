package nethical.cold.widgets.viewgroup

import android.content.ClipData
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.view.DragEvent
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import nethical.cold.Project
import nethical.cold.utils.AddView
import nethical.cold.utils.CreateShadow
import nethical.cold.widgets.WidgetUtils

class LinearLayoutItem(context: Context,holder : ViewGroup, index:Int, project: Project) : BaseViewGroup.DragListener {

    val temp_linear  = LinearLayout(context)
    val _context = context
    var shadow: View
    init {
        temp_linear.layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.MATCH_PARENT)
        temp_linear.setPadding(8,8,8,8)
        temp_linear.setBackgroundColor(Color.BLUE)

        val border = GradientDrawable()
        border.setColor(Color.TRANSPARENT)
        border.setStroke(2,Color.LTGRAY)
        temp_linear.background = border

        val shadowcls = CreateShadow()
        shadow = shadowcls.create_shadow(context)

        holder.addView(temp_linear, index)

        val baseViewGroup = BaseViewGroup(context, project)
        baseViewGroup.CreateDragListener(temp_linear,holder,this)
        baseViewGroup.CreateDragger(temp_linear,holder,"Linear Layout")
    }


}