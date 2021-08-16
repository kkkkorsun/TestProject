package com.kkkkorsun.testproject.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.kkkkorsun.testproject.R
import com.kkkkorsun.testproject.databinding.ActivityDetailUserBinding
import com.kkkkorsun.testproject.model.RepositoryModel

class DetailUserActivity : AppCompatActivity() {

    companion object{
        const val EXTRA_USERNAME = "extra_username"    }

    private lateinit var binding: ActivityDetailUserBinding
    private lateinit var viewModel: DetailUserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailUserBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val recyclerView: RecyclerView = binding.rvRepos
        recyclerView.layoutManager = LinearLayoutManager(this)

        val username = intent.getStringExtra(EXTRA_USERNAME)

        viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(DetailUserViewModel::class.java)

        if (username != null) {
            viewModel.setUserDetail(username)
        }
        if (username != null) {
            viewModel.setUserRepos(username)
        }
        viewModel.getUserDetail().observe(this, {
            if (it != null){
                binding.apply {
                    tvName.text = it.name
                    tvUsername.text = it.login
                    Glide.with(this@DetailUserActivity)
                        .load(it.avatar_url)
                        .transition(DrawableTransitionOptions.withCrossFade())
                        .centerCrop()
                        .into(ivProfile)
                }
            }
        })
        viewModel.getUserRepos().observe(this, {
            if (it != null){
                binding.apply {
                    tvName.text = it[1].name.toString()
                    recyclerView.adapter = RecyclerAdapter(it)
                }
            }
        })
    }

    private inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private lateinit var repos: RepositoryModel
        private val repoName: TextView = itemView.findViewById(R.id.repos_name)
        private val repoDesc: TextView = itemView.findViewById(R.id.repos_desc)
        private val repoForks: TextView = itemView.findViewById(R.id.repos_forks)

        fun bind(repos: RepositoryModel) {
            this.repos = repos
            repoName.text = repos.name
            repoDesc.text = repos.description
            repoForks.text = repos.forks_count.toString()
        }
    }

    private inner class RecyclerAdapter(private var repos: List<RepositoryModel>) :
        RecyclerView.Adapter<MyViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
            val itemView =
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_repos, parent, false)
            return MyViewHolder(itemView)
        }

        override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
            val repos = repos[position]
            holder.bind(repos)
        }

        override fun getItemCount() = repos.size
    }
}