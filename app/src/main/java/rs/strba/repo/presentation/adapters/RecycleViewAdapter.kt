package rs.strba.repo.presentation.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import rs.strba.repo.R
import rs.strba.repo.data.model.Item


class RecycleViewAdapter : RecyclerView.Adapter<RecycleViewAdapter.RepoViewHolder>() {

    private val myRepoData = mutableListOf<Item>()

    fun submitList(newData: List<Item>) {
        myRepoData.clear()
        myRepoData.addAll(newData)
        notifyDataSetChanged()
    }

    inner class RepoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_repo, parent, false)
        return RepoViewHolder(view)
    }

    override fun onBindViewHolder(holder: RepoViewHolder, position: Int) {
        holder.itemView.findViewById<TextView>(R.id.tvTitle).text =
            myRepoData[position].fullName
        holder.itemView.findViewById<TextView>(R.id.tvForkNumber).text =
            myRepoData[position].forksCount.toString()
        holder.itemView.findViewById<TextView>(R.id.tvStarNumber).text =
            myRepoData[position].stargazersCount.toString()
        holder.itemView.findViewById<TextView>(R.id.tvDescription).text =
            myRepoData[position].description as CharSequence?
    }

    override fun getItemCount(): Int {
        return myRepoData.size
    }
}