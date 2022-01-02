package nethical.cold.widgets

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import nethical.cold.R

class WidgetUtils(_view : View, _holder : ViewGroup, _context : Context) : View.OnLongClickListener {
    var context : Context = _context
    var view:View = _view
    var holder: ViewGroup = _holder

    init {
        view.setOnLongClickListener(this)
    }


    override fun onLongClick(v: View?): Boolean {
        val popupMenu: PopupMenu = PopupMenu(context,v )
        popupMenu.menuInflater.inflate(R.menu.popup_designer, popupMenu.menu)
        popupMenu.setOnMenuItemClickListener { i ->

            true
        }
        popupMenu.show()

        return true
    }
}