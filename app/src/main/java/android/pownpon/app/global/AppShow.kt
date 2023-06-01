package android.pownpon.app.global

import android.content.Context
import android.pownpon.app.applaunch.AppMy
import android.util.Log
import android.widget.Toast

/**
 * 打印log日志
 */
fun showLog(logObj: Any, msg: String) {
    if (deBug && !msg.isNullOrEmpty()) {
        val objName = logObj::class.java.simpleName
        Log.e(objName, msg)
    }
}

private var toast: Toast? = null

/**
 * 显示提示信息
 */
fun showToast(msg: String) {
    if (null == toast) {
        toast = Toast.makeText(AppMy.getApp(), msg, Toast.LENGTH_SHORT)
    } else {
        toast!!.setText(msg)
    }
    toast?.show()
}

/**
 * 显示提示信息
 */
fun showToast(context: Context, msg: String) {
    Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
}