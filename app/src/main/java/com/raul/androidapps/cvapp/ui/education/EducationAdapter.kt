package com.raul.androidapps.cvapp.ui.education

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.raul.androidapps.cvapp.R
import com.raul.androidapps.cvapp.databinding.CVAppBindingComponent
import com.raul.androidapps.cvapp.databinding.EducationItemBinding
import com.raul.androidapps.cvapp.databinding.SkillItemBinding


class EducationAdapter constructor(
    private val bindingComponent: CVAppBindingComponent
) :
    RecyclerView.Adapter<EducationAdapter.EducationViewHolder>() {

    private var items: List<String> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EducationViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<EducationItemBinding>(
            inflater, R.layout.education_item, parent,
            false, bindingComponent
        )

        return EducationViewHolder(binding = binding)

    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: EducationViewHolder, position: Int) {
        holder.bind(items[position])
    }

    fun updateItems(items: List<String>) {
        this.items = items
        notifyDataSetChanged()
    }

    class EducationViewHolder constructor(
        private val binding: EducationItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(education: String) {
            binding.education = education
            binding.executePendingBindings()
        }
    }
}