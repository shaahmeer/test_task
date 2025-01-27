package com.example.testtask.adatper


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.testtask.data.Job
import com.example.testtask.databinding.ItemJobsBinding
import kotlin.reflect.KFunction1

class FavJobAdapter(
    var jobs: List<Job>,
    private val onFavoriteClick: (Int) -> Unit,
    private val onApplyClick: (Int) -> Unit,
    private val onRemoveFavoriteClick: (Int) -> Unit
) : RecyclerView.Adapter<FavJobAdapter.JobViewHolder>() {

    inner class JobViewHolder(private val binding: ItemJobsBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(job: Job) {
            binding.tvViewersCount.text = job.viewersCount
            binding.tvJobTitle.text = job.jobTitle
            binding.tvLocation.text = job.location
            binding.tvCompanyName.text = job.companyName
            binding.tvExperience.text = job.experience
            binding.tvPublishDate.text = job.publishDate

            binding.btnFavorite.setColorFilter(
                if (job.isFavorite) android.graphics.Color.RED else android.graphics.Color.GRAY
            )

            binding.btnFavorite.setOnClickListener {
                onRemoveFavoriteClick(adapterPosition) // This is for removing from favorites
            }
            binding.btnApply.setOnClickListener { onApplyClick(adapterPosition) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JobViewHolder {
        val binding = ItemJobsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return JobViewHolder(binding)
    }

    override fun onBindViewHolder(holder: JobViewHolder, position: Int) {
        holder.bind(jobs[position])
    }

    override fun getItemCount(): Int = jobs.size

    fun updateData(newJobs: List<Job>) {
        jobs = newJobs
        notifyDataSetChanged()
    }
}
