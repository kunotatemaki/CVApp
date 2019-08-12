package com.raul.androidapps.cvapp.ui.expertise

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.raul.androidapps.cvapp.R
import com.raul.androidapps.cvapp.databinding.CVAppBindingComponent
import com.raul.androidapps.cvapp.databinding.ExpertiseDetailItemBinding
import com.raul.androidapps.cvapp.databinding.ExpertiseItemBinding
import com.raul.androidapps.cvapp.persistence.relations.CompanyWithAllInfo


class ExpertiseAdapter constructor(
    private val bindingComponent: CVAppBindingComponent
) :
    RecyclerView.Adapter<ExpertiseAdapter.ExpertiseViewHolder>() {

    private var items: List<CompanyWithAllInfo> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExpertiseViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<ExpertiseItemBinding>(
            inflater, R.layout.expertise_item, parent,
            false, bindingComponent
        )

        return ExpertiseViewHolder(binding = binding)

    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ExpertiseViewHolder, position: Int) {
        holder.bind(items[position], bindingComponent)
    }

    fun updateItems(items: List<CompanyWithAllInfo>) {
        this.items = items
        notifyDataSetChanged()
    }

    class ExpertiseViewHolder constructor(
        private val binding: ExpertiseItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(company: CompanyWithAllInfo, bindingComponent: CVAppBindingComponent) {
            binding.company = company
            val inflater = LayoutInflater.from(itemView.context)
            binding.tasksList.apply {
                removeAllViews()
                company.tasks.forEach {
                    val detailsBinding: ExpertiseDetailItemBinding = DataBindingUtil.inflate(
                        inflater,
                        R.layout.expertise_detail_item,
                        this,
                        false,
                        bindingComponent
                    )
                    detailsBinding.detail = it.task
                    detailsBinding.executePendingBindings()
                    this.addView(detailsBinding.root)
                }
                if(company.tasks.isEmpty()){
                    binding.tasksTitle.visibility = View.GONE
                    binding.tasksList.visibility = View.GONE
                }else{
                    binding.tasksTitle.visibility = View.VISIBLE
                    binding.tasksList.visibility = View.VISIBLE
                }
            }
            binding.achievementsList.apply {
                removeAllViews()
                company.achievements.forEach {
                    val detailsBinding: ExpertiseDetailItemBinding = DataBindingUtil.inflate(
                        inflater,
                        R.layout.expertise_detail_item,
                        this,
                        false,
                        bindingComponent
                    )
                    detailsBinding.detail = it.achievement
                    detailsBinding.executePendingBindings()
                    this.addView(detailsBinding.root)
                }
                if(company.achievements.isEmpty()){
                    binding.achievementsList.visibility = View.GONE
                    binding.achievementsTitle.visibility = View.GONE
                }else{
                    binding.achievementsList.visibility = View.VISIBLE
                    binding.achievementsTitle.visibility = View.VISIBLE
                }
            }
            binding.executePendingBindings()
        }
    }
}