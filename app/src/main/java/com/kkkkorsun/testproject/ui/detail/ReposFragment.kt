package com.kkkkorsun.testproject.ui.detail

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.kkkkorsun.testproject.R
import com.kkkkorsun.testproject.databinding.FragmentReposBinding

class ReposFragment: Fragment(R.layout.fragment_repos) {

    private var _binding: FragmentReposBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentReposBinding.bind(view)
    }
}