package nethical.cold.adapters

import android.content.ClipData
import android.content.ClipDescription
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import nethical.cold.R
import nethical.cold.utils.DragShadowBuilder

class PaletteAdapter(val views: List<String>):  RecyclerView.Adapter<PaletteAdapter.ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.palette_custom,parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.view_title.text = views[position]
        val tag = views[position]
        holder.linear_holder.setOnLongClickListener { v ->
            val item = ClipData.Item(tag)
            val dragData = ClipData(
                v.tag as? CharSequence,
                arrayOf(ClipDescription.MIMETYPE_TEXT_PLAIN),
                item)
            val myShadow = DragShadowBuilder(holder.linear_holder)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                v.startDragAndDrop(dragData,  // The data to be dragged
                    myShadow,  // The drag shadow builder
                    null,      // No need to use local data
                    0          // Flags (not currently used, set to 0)
                )
            }
            true
        }
    }

    override fun getItemCount(): Int {
        return views.size
    }
    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val linear_holder = itemView.findViewById<LinearLayout>(R.id.linear_holdr)
        val view_title = itemView.findViewById<TextView>(R.id.view_title)
        val view_icon = itemView.findViewById<ImageView>(R.id.view_icon)

    }

}


