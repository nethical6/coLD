package nethical.cold.widgets.view

import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import nethical.cold.Project
import nethical.cold.adapters.AttributeAdapter
import nethical.cold.adapters.Attribute
import org.w3c.dom.Document
import org.w3c.dom.Element

// TODO : Add functionality to save properties
class ButtonItem(holder : ViewGroup, index : Int, project: Project ) : BaseView.DragListener {
    val context = project.context
    private val tempButton  = Button(context)
    init {
        val baseView = BaseView(context,project)
        tempButton.text = "Button"
        tempButton.setPadding(8,8,8,8)
        tempButton.layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT)
        baseView.resize(tempButton)
        holder.addView(tempButton,index)
//        Toast.makeText(context,tempButton.,Toast.LENGTH_SHORT).show()
        createButton(project.rootElement,project.doc)


        baseView.CreateDragListener(tempButton,holder,this)
        baseView.CreateDragger(tempButton,holder,"Button")

        val attributesList = mutableListOf<Attribute>()
        attributesList.add(Attribute("Padding", 1, tempButton, context))
        attributesList.add(Attribute("Text", 6, tempButton, context))

        val attr_recycler = project.attrRecycler

        tempButton.setOnClickListener {
            attr_recycler.adapter = AttributeAdapter(attributesList)
            attr_recycler.layoutManager = LinearLayoutManager(context)
        }


    }

    fun createButton(element: Element, doc: Document){
        val nubBtn: Element = doc.createElement("Button")
        nubBtn.setAttribute("android:layout-width","match_parent")
        nubBtn.setAttribute("android:layout-height","match_parent")
        nubBtn.setAttribute("android:text","Button")
        nubBtn.setAttribute("android:padding","8dp")

        element.appendChild(nubBtn)

    }
}