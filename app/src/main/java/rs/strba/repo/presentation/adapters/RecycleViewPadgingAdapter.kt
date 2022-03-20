package rs.strba.repo.presentation.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import rs.strba.repo.R
import rs.strba.repo.data.model.Item

class RecyclerViewAdapter :
    PagingDataAdapter<Item, RecyclerViewAdapter.MyViewHolder>(DiffUtilCallBack()) {
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyViewHolder {
        val inflater =
            LayoutInflater.from(parent.context).inflate(R.layout.item_repo, parent, false)
        return MyViewHolder(inflater)
    }

    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val name: TextView = view.findViewById(R.id.tvTitle)
        private val starNumber: TextView = view.findViewById(R.id.tvStarNumber)
        private val forkNumber: TextView = view.findViewById(R.id.tvForkNumber)
        private val description: TextView = view.findViewById(R.id.tvDescription)
        fun bind(item: Item?) {
            if (item != null) {
                name.text = item.name
                starNumber.text = item.stargazersCount.toString()
                forkNumber.text = item.forksCount.toString()
                description.text = item.description as CharSequence
            }
        }
    }

    class DiffUtilCallBack : DiffUtil.ItemCallback<Item>() {
        override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean {
            return oldItem.name == newItem.name && oldItem.createdAt == newItem.createdAt
        }

    }
}