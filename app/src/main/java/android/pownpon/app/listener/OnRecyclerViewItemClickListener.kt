package android.pownpon.app.listener

import android.content.Context
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
    private var mDisallowIntercept = false

    init {
        mGestureDetector =
            GestureDetector(context, object : GestureDetector.SimpleOnGestureListener() {
                override fun onSingleTapUp(e: MotionEvent): Boolean {
                    onClick?.let { it(getPosition(e)) }
                    return true
                }

                override fun onLongPress(e: MotionEvent) {
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
        if (mDisallowIntercept) {
            return false
        }
        val itemView = rv.findChildViewUnder(e.x, e.y)
        return true != itemView?.dispatchTouchEvent(e)
    }

    override fun onTouchEvent(rv: RecyclerView, e: MotionEvent) {
        mRecyclerView = rv
        mGestureDetector.onTouchEvent(e)
    }

    override fun onRequestDisallowInterceptTouchEvent(disallowIntercept: Boolean) {
        mDisallowIntercept = disallowIntercept
    }
}