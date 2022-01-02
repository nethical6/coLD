package nethical.cold.widgets.attributes

import android.content.Context
import android.view.View

class ViewAttributes(_context: Context) {
var context = _context
    fun set_padding(view: View,left : Int,top : Int,right : Int,bottom : Int,){
        view.setPadding(left,top, right, bottom)
    }


}