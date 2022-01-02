package nethical.cold.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import nethical.cold.R
import nethical.cold.widgets.attributes.AllAttributes

class AttributeAdapter(private val attr_list: List<Attribute>):  RecyclerView.Adapter<AttributeAdapter.ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.attributes_custom,parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.attrTitle.text = attr_list[position].name
        holder.attrValue.setOnEditorActionListener{ _, actionId, _ ->
            return@setOnEditorActionListener when (actionId) {
                EditorInfo.IME_ACTION_DONE -> {
                    val m = AllAttributes()
                    m.update(attr_list[position].attrnum,attr_list[position].view,attr_list[position].context,holder.attrValue.text.toString())
                    true
                }
                else -> false
            }
        }
        holder.attrTitle.setOnClickListener{
            val m = AllAttributes()
            m.update(m.SET_PADDING,attr_list[position].view,attr_list[position].context,"20")
        }
    }

    override fun getItemCount(): Int {
        return attr_list.size
    }
    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val attrTitle: TextView = itemView.findViewById(R.id.attr_name)
        val attrValue: EditText = itemView.findViewById(R.id.attr_value)

    }


}


