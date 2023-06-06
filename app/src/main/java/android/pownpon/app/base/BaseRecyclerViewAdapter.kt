package android.pownpon.app.base

import android.content.Context
import androidx.viewbinding.ViewBinding

abstract class BaseRecyclerViewAdapter<T, VB : ViewBinding>(context: Context) :
    BaseRecyclerViewTopAdapter<T, VB, BaseRecyclerViewHolder<VB>>(context) {

    final override fun getItemCount(): Int = dataList.size

    final override fun onBindViewHolder(holder: BaseRecyclerViewHolder<VB>, position: Int) {
        onBind(holder.binding, position, dataList[position])
    }

    abstract fun onBind(binding:VB, position: Int, item: T)
}