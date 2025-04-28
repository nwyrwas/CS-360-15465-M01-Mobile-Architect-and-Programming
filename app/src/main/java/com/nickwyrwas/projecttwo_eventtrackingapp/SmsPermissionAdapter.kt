package com.nickwyrwas.projecttwo_eventtrackingapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class SmsPermissionAdapter(private val permissionList: List<String>) : RecyclerView.Adapter<SmsPermissionAdapter.SmsPermissionViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SmsPermissionViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_sms_permission, parent, false)
        return SmsPermissionViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: SmsPermissionViewHolder, position: Int) {
        holder.bind(permissionList[position])
    }

    override fun getItemCount(): Int = permissionList.size

    class SmsPermissionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val permissionTextView: TextView = itemView.findViewById(R.id.permissionTextView)

        fun bind(permission: String) {
            permissionTextView.text = permission
        }
    }
}
