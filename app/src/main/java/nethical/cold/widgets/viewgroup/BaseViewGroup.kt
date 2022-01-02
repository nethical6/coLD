package nethical.cold.widgets.viewgroup

import android.content.ClipData
import android.content.ClipDescription
import android.content.Context
import android.os.Build
import android.view.DragEvent
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import nethical.cold.Project
import nethical.cold.utils.AddView
import nethical.cold.utils.CreateShadow
import nethical.cold.utils.DragShadowBuilder

class BaseViewGroup(_context:Context, _project: Project) {
    lateinit var shadow : View
    var context = _context
    private var isFocoused = false
    val project = _project

    init {
        val shadowcls = CreateShadow()
        shadow = shadowcls.create_shadow(context)
    }

    fun CreateDragListener(view: ViewGroup, holder : ViewGroup, action: DragListener ){
        view.setOnDragListener{ b,e ->
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

    fun CreateDragger(view: ViewGroup, holder : ViewGroup, Type : String = "Linear Layout"){
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


    interface DragListener{
        fun OnDragEnterd(e : DragEvent, view: ViewGroup, holder : ViewGroup, context: Context, shadow: View){
            view.addView(shadow)
        }
        fun OnDragExited(e : DragEvent, view: ViewGroup, holder : ViewGroup, context: Context, shadow: View){
            view.removeView(shadow)
        }
        fun OnDropped(e : DragEvent, view: ViewGroup, holder : ViewGroup, context: Context, shadow: View, project: Project){
            val item: ClipData.Item = e.clipData.getItemAt(0)
            view.removeView(shadow)
            AddView(item.text.toString(),view,context,project)
        }

    }
}