package com.example.ppm_4.guests


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.ppm_4.R
import com.example.ppm_4.database.GuestandRole
import com.example.ppm_4.databinding.GuestItemViewBinding

class guestsAdapter(val listener: GuestandRoleListener): ListAdapter<GuestandRole, guestsAdapter.viewHolder>(GuestandRoleDiffCallback()){


    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(listener, item)

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): guestsAdapter.viewHolder {
        return viewHolder.from(parent)
    }


    class viewHolder private constructor(val binding: GuestItemViewBinding):
        RecyclerView.ViewHolder(binding.root){

        fun bind(clickListener: GuestandRoleListener, item : GuestandRole){
            binding.guest = item
            binding.clickListener = clickListener
            binding.executePendingBindings()
        }

        companion object{
            fun from(parent: ViewGroup) : viewHolder{
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = GuestItemViewBinding.inflate(layoutInflater,parent,false)

                return viewHolder(binding)
            }
        }

    }

    class GuestandRoleDiffCallback: DiffUtil.ItemCallback<GuestandRole>(){
        override fun areItemsTheSame(oldItem: GuestandRole, newItem: GuestandRole): Boolean {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            return oldItem.guest.Id == newItem.guest.Id
        }

        override fun areContentsTheSame(oldItem: GuestandRole, newItem: GuestandRole): Boolean {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            return oldItem.guest == newItem.guest
        }

    }

}

class GuestandRoleListener(val clickListener: (Id: Int) -> Unit){
    fun onClick(guest: GuestandRole) = clickListener(guest.guest.Id)
}