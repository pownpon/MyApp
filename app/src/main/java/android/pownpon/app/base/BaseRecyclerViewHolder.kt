package android.pownpon.app.base

import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

class BaseRecyclerViewHolder<V : ViewBinding>(val binding: V) : RecyclerView.ViewHolder(binding.root) {
}