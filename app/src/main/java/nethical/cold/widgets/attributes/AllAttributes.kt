package nethical.cold.widgets.attributes

import android.content.Context
import android.view.View
import android.widget.TextView
import androidx.core.view.marginTop

class AllAttributes {
    open val SET_PADDING = 0

    open val SET_MARGIN_TOP = 1
    open val SET_MARGIN_BOTTOM = 2
    open val SET_MARGIN_LEFT = 3
    open val SET_MARGIN_RIGHT = 4

    open val SET_ID = 2
    open val SET_GRAVITY = 3
    open val SET_LAYOUT_GRAVITY = 4
    open val SET_ON_CLICL = 5

    open val SET_TEXT = 6
    open val SET_TEXT_COLOR = 7


    fun update(type : Int,view: View, context: Context, value : String){
        when (type){
            SET_PADDING -> {
                val v = value.toInt()
                view.setPadding(v,v,v,v)
            }
            SET_TEXT -> {
                if (view is TextView){
                    view.text = value
                }
            }


        }
    }
}