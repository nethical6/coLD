package nethical.cold.utils

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Point
import android.graphics.drawable.ColorDrawable
import android.view.View

class DragShadowBuilder(v: View)  : View.DragShadowBuilder(v){
    private val shadow = ColorDrawable(Color.LTGRAY)
    override fun onProvideShadowMetrics(size: Point, touch: Point) {

        // Set the width of the shadow to half the width of the original View.
        val width: Int = view.width / 2

        // Set the height of the shadow to half the height of the original View.
        val height: Int = view.height / 2

        // The drag shadow is a ColorDrawable. This sets its dimensions to be the
        // same as the Canvas that the system provides. As a result, the drag shadow
        // fills the Canvas.
        shadow.setBounds(0, 0, width, height)

        // Set the size parameter's width and height values. These get back to
        // the system through the size parameter.
        size.set(width, height)

        // Set the touch point's position to be in the middle of the drag shadow.
        touch.set(width / 2, height / 2)
    }
    override fun onDrawShadow(canvas: Canvas) {

        // Draw the ColorDrawable on the Canvas passed in from the system.
        shadow.draw(canvas)
    }


}