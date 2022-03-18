package rs.strba.repo.presentation.adapters

import android.content.ClipData
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import rs.strba.repo.R
import rs.strba.repo.data.model.Item


class RepoAdapter(var repos: List<Item>) : RecyclerView.Adapter<RepoAdapter.RepoViewHolder>() {

    inner class RepoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_repo, parent, false)
        return RepoViewHolder(view)
    }

    override fun onBindViewHolder(holder: RepoViewHolder, position: Int) {
        holder.itemView.findViewById<TextView>(R.id.tvTitle).text =
            repos[position].fullName
    }

    override fun getItemCount(): Int {
        return repos.size
    }
}