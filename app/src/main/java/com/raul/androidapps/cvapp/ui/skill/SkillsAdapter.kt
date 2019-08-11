package com.raul.androidapps.cvapp.ui.skill

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.raul.androidapps.cvapp.R
import com.raul.androidapps.cvapp.databinding.CVAppBindingComponent
import com.raul.androidapps.cvapp.databinding.SkillItemBinding


class SkillsAdapter constructor(
    private val bindingComponent: CVAppBindingComponent
) :
    RecyclerView.Adapter<SkillsAdapter.SkillViewHolder>() {

    private var items: List<String> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SkillViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<SkillItemBinding>(
            inflater, R.layout.skill_item, parent,
            false, bindingComponent
        )

        return SkillViewHolder(binding = binding)

    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: SkillViewHolder, position: Int) {
        holder.bind(items[position])
    }

    fun updateItems(items: List<String>) {
        this.items = items
        notifyDataSetChanged()
    }

    class SkillViewHolder constructor(
        private val binding: SkillItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(skill: String) {
            binding.skill = skill
            binding.executePendingBindings()
        }
    }
}