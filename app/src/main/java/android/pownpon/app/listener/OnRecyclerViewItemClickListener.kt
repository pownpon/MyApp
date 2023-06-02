package android.pownpon.app.listener

import android.content.Context
import android.pownpon.app.global.showLog
import android.view.GestureDetector
import android.view.MotionEvent
import androidx.recyclerview.widget.RecyclerView

class OnRecyclerViewItemClickListener(
    context: Context,
    private val onLongClick: ((position: Int) -> Unit)?,
    private val onClick: ((position: Int) -> Unit)?
) : RecyclerView.OnItemTouchListener {

    private val mGestureDetector: GestureDetector
    private var mRecyclerView: RecyclerView? = null

    init {
        mGestureDetector =
            GestureDetector(context, object : GestureDetector.SimpleOnGestureListener() {
                override fun onSingleTapUp(e: MotionEvent): Boolean {
                    showLog(this@OnRecyclerViewItemClickListener, "00000")
                    onClick?.let { it(getPosition(e)) }
                    return true
                }

                override fun onLongPress(e: MotionEvent) {
                    showLog(this@OnRecyclerViewItemClickListener, "11111")
                    onLongClick?.let { it(getPosition(e)) }
                }
            })
    }

    private fun getPosition(e: MotionEvent): Int {
        val itemView = mRecyclerView?.findChildViewUnder(e.x, e.y)
        val position = itemView?.let { mRecyclerView?.getChildAdapterPosition(it) }
        return position ?: 0
    }

    override fun onInterceptTouchEvent(rv: RecyclerView, e: MotionEvent): Boolean {
        return true
    }

    override fun onTouchEvent(rv: RecyclerView, e: MotionEvent) {
        mRecyclerView = rv
        mGestureDetector.onTouchEvent(e)
    }

    override fun onRequestDisallowInterceptTouchEvent(disallowIntercept: Boolean) {

    }
}