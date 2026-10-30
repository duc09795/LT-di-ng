package com.example.charityapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.charityapp.databinding.ItemCampaignBinding
import com.example.charityapp.models.Campaign
import java.text.NumberFormat
import java.util.Locale

class CampaignAdapter(
    private var list: List<Campaign>,
    private val onItemClick: (Campaign) -> Unit
) : RecyclerView.Adapter<CampaignAdapter.CampaignViewHolder>() {

    inner class CampaignViewHolder(val binding: ItemCampaignBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CampaignViewHolder {
        val binding = ItemCampaignBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return CampaignViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CampaignViewHolder, position: Int) {
        val item = list[position]
        val formatter = NumberFormat.getCurrencyInstance(Locale("vi", "VN"))

        holder.binding.apply {
            tvTitle.text = item.title
            tvRaisedAmount.text = "Đã đạt: ${formatter.format(item.currentAmount)}"
            tvTargetAmount.text = "Mục tiêu: ${formatter.format(item.targetAmount)}"

            val percent = if (item.targetAmount > 0) {
                ((item.currentAmount / item.targetAmount) * 100).toInt()
            } else 0
            progressBar.progress = percent.coerceAtMost(100)

            Glide.with(root.context)
                .load(item.imageUrl)
                .centerCrop()
                .into(imgCampaign)

            root.setOnClickListener { onItemClick(item) }
        }
    }

    override fun getItemCount(): Int = list.size

    fun updateData(newList: List<Campaign>) {
        list = newList
        notifyDataSetChanged()
    }
}