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

class RecyclerViewAdapter(private val listener: OnItemClickListener) :
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

    inner class MyViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView), View.OnClickListener {

            init {
                itemView.setOnClickListener(this)
            }

        private val name: TextView = itemView.findViewById(R.id.tvTitle)
        private val starNumber: TextView = itemView.findViewById(R.id.tvStarNumber)
        private val forkNumber: TextView = itemView.findViewById(R.id.tvForkNumber)
        private val description: TextView = itemView.findViewById(R.id.tvDescription)
        fun bind(item: Item?) {
            if (item != null) {
                name.text = item.name
                starNumber.text = item.stargazersCount.toString()
                forkNumber.text = item.forksCount.toString()
                description.text = item.description as CharSequence
            }
        }

        override fun onClick(p0: View?) {
            listener.onItemClick(adapterPosition,getItem(adapterPosition))
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

    interface OnItemClickListener {
        fun onItemClick(position: Int, item: Item?)
    }
}