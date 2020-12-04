package br.svcdev.githubclient.view.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.svcdev.githubclient.R
import br.svcdev.githubclient.view.interfaces.IRepoItemView
import br.svcdev.githubclient.presenter.IReposListPresenter

class ReposRVAdapter(val presenter: IReposListPresenter) :
        RecyclerView.Adapter<ReposRVAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), IRepoItemView {

        var repoName: TextView = itemView.findViewById(R.id.tv_repo_name)
        var repoDescription: TextView = itemView.findViewById(R.id.tv_repo_description)

        override fun setRepoName(text: String?) {
            repoName.text = text
        }

        override fun setRepoDescription(text: String?) {
            repoDescription.text = text
        }

        override var pos = -1

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            ViewHolder(LayoutInflater.from(parent.context)
                    .inflate(R.layout.element_item_repos, parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.pos = position
        holder.itemView.setOnClickListener { presenter.itemClickListener?.invoke(holder)
        }
        presenter.bindView(holder)
    }

    override fun getItemCount() = presenter.getCount()

}