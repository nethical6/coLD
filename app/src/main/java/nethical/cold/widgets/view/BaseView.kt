package nethical.cold.widgets.view

import android.content.ClipData
import android.content.ClipDescription
import android.content.Context
import android.graphics.Color
import android.opengl.Visibility
import android.os.Build
import android.view.DragEvent
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import nethical.cold.Project
import nethical.cold.utils.AddView
import nethical.cold.utils.CreateShadow
import nethical.cold.utils.DragShadowBuilder

class BaseView(_context:Context,  _project: Project) {
    var shadow : View
    var context = _context
    val project = _project

    init {
        val shadowcls = CreateShadow()
        shadow = shadowcls.create_shadow(context)
    }

    fun CreateDragListener(view: View, holder : ViewGroup,action: DragListener ){
        view.setOnDragListener{ _,e ->
            when (e.action){
                DragEvent.ACTION_DRAG_ENTERED -> {
                    action.OnDragEnterd(e,view,holder,context, shadow)
                    true
                }
                DragEvent.ACTION_DRAG_EXITED -> {
                    action.OnDragExited(e,view,holder,context, shadow)
                    true
                }
                DragEvent.ACTION_DROP -> {
                    action.OnDropped(e,view,holder,context, shadow, project)
                    true
                }
                else -> true
            }
        }
    }

    fun CreateDragger(view: View, holder : ViewGroup, Type : String = "Button"){
        view.setOnLongClickListener{v ->view.visibility = View.GONE
            val item = ClipData.Item(Type)
            val dragData = ClipData(
                v.tag as? CharSequence,
                arrayOf(ClipDescription.MIMETYPE_TEXT_PLAIN),
                item)
            val myShadow = DragShadowBuilder(view)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                v.startDragAndDrop(dragData,  // The data to be dragged
                    myShadow,  // The drag shadow builder
                    null,      // No need to use local data
                    0          // Flags (not currently used, set to 0)
                )
            }
            holder.removeView(view)
            true
        }
    }

    fun createAttributeseter(viewName : String,view: View){
        view.setOnClickListener {

        }
    }

    fun resize(view:View){
        val width : Int = (99).toInt()
        val height : Int = (54).toInt()
        view.layoutParams = ViewGroup.LayoutParams(
            width,height)
        if (view is TextView){
            view.textSize = 8F
        }
    }


    interface DragListener{
        fun OnDragEnterd( e : DragEvent,view: View, holder : ViewGroup, context:Context, shadow:View ){
            val index = holder.indexOfChild(view)
            holder.addView(shadow,index)
        }
        fun OnDragExited( e : DragEvent,view: View, holder : ViewGroup, context:Context, shadow:View ){
            holder.removeView(shadow)
        }
        fun OnDropped( e : DragEvent,view: View, holder : ViewGroup, context:Context, shadow:View, project: Project){
            val item: ClipData.Item = e.clipData.getItemAt(0)
            val index = holder.indexOfChild(shadow)
            holder.removeView(shadow)
            AddView(item.text.toString(),holder,context,project, index)
        }

    }
}






//interface BaseView {
//    var view: View
//    var holder: ViewGroup
//    var context: Context
//
//    var shadow : View
//
//    fun initView(){
////        view.setOnDragListener{ b , e ->
////            when (e.action){
////                DragEvent.ACTION_DRAG_ENTERED -> {
////                    DragEntered()
////                }
////                DragEvent.ACTION_DRAG_EXITED -> {
////                    DragExited()
////                }
////                DragEvent.ACTION_DROP -> {
////                    drop(e)
////                }
////            }
////            true
////        }
//    }
//
//    fun DragEntered() : Boolean{
//        val index = holder.indexOfChild(view) - 1
//        holder.addView(shadow,index)
//        Toast.makeText(context,"drag Enterd", Toast.LENGTH_SHORT)
//        return true
//    }
//
//    fun DragExited() : Boolean{
//        holder.removeView(shadow)
//        return true
//    }
//    fun drop(e: DragEvent) {
//        Toast.makeText(context,"dropped", Toast.LENGTH_SHORT)
//        val item: ClipData.Item = e.clipData.getItemAt(0)
//        val index = holder.indexOfChild(view) - 1
//        holder.removeView(shadow)
//        AddView(item.text.toString(),holder,context, index)
//    }


//}