package com.example.ppm_4.roles

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.ppm_4.database.Role
import com.example.ppm_4.databinding.RoleItemsViewBinding

class roleAdapter(val listener: RoleListener): ListAdapter<Role, roleAdapter.viewHolder>(RoleDiffCallback()){


    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(listener, item)

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): roleAdapter.viewHolder {
        return viewHolder.from(parent)
    }


    class viewHolder private constructor(val binding: RoleItemsViewBinding):
        RecyclerView.ViewHolder(binding.root){

        fun bind(clickListener: RoleListener, item : Role){
            binding.role = item
            binding.clickListener = clickListener
            binding.executePendingBindings()
        }

        companion object{
            fun from(parent: ViewGroup) : viewHolder{
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = RoleItemsViewBinding.inflate(layoutInflater,parent,false)

                return viewHolder(binding)
            }
        }

    }

    class RoleDiffCallback: DiffUtil.ItemCallback<Role>(){
        override fun areItemsTheSame(oldItem: Role, newItem: Role): Boolean {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            return oldItem.Id == newItem.Id
        }

        override fun areContentsTheSame(oldItem: Role, newItem: Role): Boolean {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            return oldItem == newItem
        }

    }

}

class RoleListener(val clickListener: (Id: Int) -> Unit){
    fun onClick(role: Role) = clickListener(role.Id)
}