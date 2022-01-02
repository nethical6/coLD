package nethical.cold

import android.content.Context
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import org.w3c.dom.Document
import org.w3c.dom.Element
import javax.xml.parsers.DocumentBuilder
import javax.xml.parsers.DocumentBuilderFactory

class Project {
    lateinit var attrRecycler: RecyclerView
    lateinit var attrViewName: TextView
    lateinit var context: Context

    lateinit var doc: Document
    lateinit var rootElement : Element

    lateinit var indexMap: MutableMap<Int,Element>

    fun gendoc(){
        val docFactory: DocumentBuilderFactory = DocumentBuilderFactory.newInstance()
        val docBuilder: DocumentBuilder = docFactory.newDocumentBuilder()
        doc = docBuilder.newDocument()
        rootElement = doc.createElement("LinearLayout")
        rootElement.setAttribute("android:layout-width","match_parent")
        rootElement.setAttribute("android:layout-height","match_parent")
        doc.appendChild(rootElement)

    }

    // This function adds the viewgroup to the viewgroup index list
    fun addToIndexMap(key: Int,element: Element){
        indexMap[key] = element
    }


}