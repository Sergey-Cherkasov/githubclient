package br.svcdev.githubclient.view.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.svcdev.githubclient.R
import br.svcdev.githubclient.presenter.IUsersListPresenter
import br.svcdev.githubclient.view.interfaces.IUserItemView

class UsersRVAdapter(val presenter: IUsersListPresenter) :
        RecyclerView.Adapter<UsersRVAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            ViewHolder(LayoutInflater.from(parent.context)
                    .inflate(R.layout.element_item_user, parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.pos = position
        holder.itemView.setOnClickListener { presenter.itemClickListener?.invoke(holder) }
        presenter.bindView(holder)
    }

    override fun getItemCount(): Int = presenter.getCount()

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
            IUserItemView {

        var textView: TextView = itemView.findViewById(R.id.tv_login)

        override fun setLogin(text: String) {
            textView.text = text
        }

        override var pos: Int = -1

    }

}