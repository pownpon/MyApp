package android.pownpon.app.base

import android.content.Context
import android.view.LayoutInflater
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

abstract class BaseRecyclerViewTopAdapter<T, VB : ViewBinding, VH : BaseRecyclerViewHolder<VB>>(
    protected val context: Context
) : RecyclerView.Adapter<VH>() {

    protected val inflater: LayoutInflater = LayoutInflater.from(context)
    protected val dataList: MutableList<T> = mutableListOf()

    open fun refreshData(data: Iterable<T>) {
        dataList.clear()
        dataList.addAll(data)
        this@BaseRecyclerViewTopAdapter.notifyDataSetChanged()
    }

    fun loadMoreData(data: Iterable<T>) {
        dataList.addAll(data)
        this@BaseRecyclerViewTopAdapter.notifyDataSetChanged()
    }

    fun insertData(position: Int, data: Collection<T>) {
        dataList.addAll(position, data)
        this@BaseRecyclerViewTopAdapter.notifyItemRangeInserted(position, data.size)
    }

    fun insertData(position: Int, data: T) {
        dataList.add(position, data)
        this@BaseRecyclerViewTopAdapter.notifyItemRangeInserted(position, 1)
    }
}