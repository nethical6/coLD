package nethical.cold.utils

import android.content.Context

import android.view.View
import android.view.ViewGroup

class CreateShadow {

    fun create_shadow(context: Context?,color: Int = 0x52000000 ): View {
        var shadow : View = View(context)
        shadow.setBackgroundColor(color)
        shadow.layoutParams = ViewGroup.LayoutParams(50,50)
        return shadow

    }


}