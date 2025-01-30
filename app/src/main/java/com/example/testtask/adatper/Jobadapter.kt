package com.example.testtask.adatper

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.testtask.Jobdetails
import com.example.testtask.data.Job
import com.example.testtask.databinding.ItemJobsBinding

class Jobadapter(
    var jobs: List<Job>,
    private val onFavoriteClick: (Int) -> Unit,
    kFunction1: (Int) -> Unit,

    ) : RecyclerView.Adapter<Jobadapter.JobViewHolder>() {

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
                onFavoriteClick(adapterPosition)
            }

            binding.btnApply.setOnClickListener {
                val intent = Intent(itemView.context, Jobdetails::class.java).apply {
                    putExtra("jobTitle", job.jobTitle)
                    putExtra("location", job.location)
                    putExtra("companyName", job.companyName)
                    putExtra("experience", job.experience)
                    putExtra("publishDate", job.publishDate)
                    putExtra("viewersCount", job.viewersCount)
                    putExtra("isFavorite", job.isFavorite)
                    putExtra("salary", job.salary)
                    putExtra("schedules", ArrayList(job.schedules)) // Pass list as ArrayList
                    putExtra("appliedNumber", job.appliedNumber) // Handle null value in Jobdetails
                    putExtra("description", job.description)
                    putExtra("responsibilities", job.responsibilities) // Ensure this is a String
                    putExtra("questions", ArrayList(job.questions)) // Pass list as ArrayList
                }
                itemView.context.startActivity(intent)
            }

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
