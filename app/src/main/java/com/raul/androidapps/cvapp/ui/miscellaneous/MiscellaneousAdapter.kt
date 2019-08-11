package com.raul.androidapps.cvapp.ui.miscellaneous

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.raul.androidapps.cvapp.R
import com.raul.androidapps.cvapp.databinding.CVAppBindingComponent
import com.raul.androidapps.cvapp.databinding.MiscellaneousDetailItemBinding
import com.raul.androidapps.cvapp.databinding.MiscellaneousItemBinding
import com.raul.androidapps.cvapp.persistence.relations.MiscellaneousWithAllInfo


class MiscellaneousAdapter constructor(
    private val bindingComponent: CVAppBindingComponent
) :
    RecyclerView.Adapter<MiscellaneousAdapter.MiscellaneousViewHolder>() {

    private var items: List<MiscellaneousWithAllInfo> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MiscellaneousViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<MiscellaneousItemBinding>(
            inflater, R.layout.miscellaneous_item, parent,
            false, bindingComponent
        )

        return MiscellaneousViewHolder(binding = binding)

    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: MiscellaneousViewHolder, position: Int) {
        holder.bind(items[position], bindingComponent)
    }

    fun updateItems(items: List<MiscellaneousWithAllInfo>) {
        this.items = items
        notifyDataSetChanged()
    }

    class MiscellaneousViewHolder constructor(
        private val binding: MiscellaneousItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(miscellaneous: MiscellaneousWithAllInfo, bindingComponent: CVAppBindingComponent) {
            binding.miscellaneousWithAllInfo = miscellaneous
            binding.miscellaneousDetails.apply {
                removeAllViews()
                val inflater = LayoutInflater.from(itemView.context)
                miscellaneous.values.forEach {
                    val detailsBinding: MiscellaneousDetailItemBinding = DataBindingUtil.inflate(
                        inflater,
                        R.layout.miscellaneous_detail_item,
                        this,
                        false,
                        bindingComponent
                    )
                    detailsBinding.detail = it.value
                    detailsBinding.executePendingBindings()
                    this.addView(detailsBinding.root)
                }
            }
            binding.executePendingBindings()
        }
    }
}