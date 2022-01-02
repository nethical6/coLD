package nethical.cold

import android.content.ClipData
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.os.Build
import android.os.Bundle
import android.view.DragEvent
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import nethical.cold.adapters.AttributeAdapter
import nethical.cold.adapters.PaletteAdapter
import nethical.cold.adapters.Attribute
import nethical.cold.utils.AddView
import java.io.StringWriter
import javax.xml.transform.TransformerFactory
import javax.xml.transform.dom.DOMSource
import javax.xml.transform.stream.StreamResult

class MainActivity : AppCompatActivity() {
    private lateinit var shadow: View
    private lateinit var attributeTitle : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // TODO : replace deprecated method
        window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_main)


        val viewsList = listOf("Linear Layout","Scroll View","TextView", "Button","CheckBox","ProgressBar","Custom View")


        attributeTitle = findViewById(R.id.viewname)
        val viewsRecycler = findViewById<RecyclerView>(R.id.views_recycler)
        val previewLayout = findViewById<LinearLayout>(R.id.layout_preview)
        val attrRecycler = findViewById<RecyclerView>(R.id.attr_list)
        val previewParent = findViewById<LinearLayout>(R.id.preview_parent)
        val designerPreferences = findViewById<ImageView>(R.id.designer_pref)
        val btnViewSource = findViewById<ImageView>(R.id.viewsource)

        val border = GradientDrawable()
        border.setColor(Color.TRANSPARENT)
        border.setStroke(2, Color.LTGRAY)
        previewParent.background = border



        shadow = createShadow()

        viewsRecycler.adapter = PaletteAdapter(viewsList)
        viewsRecycler.layoutManager = LinearLayoutManager(this)

        val attributesList = mutableListOf<Attribute>()
        attributesList.add(Attribute("padding", 1, previewLayout, this))
        attrRecycler.adapter = AttributeAdapter(attributesList)
        attrRecycler.layoutManager = LinearLayoutManager(this)


        val project = Project()
        project.context = this
        project.attrRecycler = attrRecycler
        project.gendoc()

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {

            previewLayout.setOnDragListener{ _,e ->

                when (e.action){
                    DragEvent.ACTION_DRAG_ENTERED -> {
                        previewLayout.addView(shadow)
                        true
                    }

                    DragEvent.ACTION_DRAG_EXITED -> {
                        previewLayout.removeView(shadow)
                        true
                    }

                    DragEvent.ACTION_DROP -> {
                        val item: ClipData.Item = e.clipData.getItemAt(0)
                        previewLayout.removeView(shadow)
                        AddView(item.text.toString(),previewLayout,applicationContext,project)
                        true
                    }
                    DragEvent.ACTION_DRAG_STARTED -> true
                    DragEvent.ACTION_DRAG_ENDED -> true
                    else -> false
                }

            }
        }

        designerPreferences.setOnClickListener{
            val popupMenu = PopupMenu(applicationContext,designerPreferences)
            popupMenu.inflate(R.menu.popup_designer)
            popupMenu.setOnMenuItemClickListener {
                val set = ConstraintSet()
                val f = findViewById<ConstraintLayout>(R.id.frameLayout)
                set.clone(f)
                set.setDimensionRatio(previewParent.id,"16:9")
                set.applyTo(f)
                true
            }
            popupMenu.show()



        }

        btnViewSource.setOnClickListener{
           val s : DOMSource = DOMSource(project.doc)
            val writer = StringWriter()
            val result = StreamResult(writer)
            val tf = TransformerFactory.newInstance()
            val transformer = tf.newTransformer()
            transformer.transform(s,result)

            Toast.makeText(this,writer.toString(),Toast.LENGTH_SHORT).show()
        }



    }

    private fun createShadow(): View {
        val shadow = View(this)
        shadow.setBackgroundColor(0x52000000)
        shadow.layoutParams = ViewGroup.LayoutParams(50,50)
        return shadow

    }
//    attributes: List<String>, view: View? = null, viewGroup: ViewGroup? = null,




}