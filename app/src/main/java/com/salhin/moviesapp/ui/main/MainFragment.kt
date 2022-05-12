package com.salhin.moviesapp.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.salhin.moviesapp.databinding.MainFragmentBinding

class MainFragment : Fragment() {


    private var _binding: MainFragmentBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: MainViewModel
    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        _binding = MainFragmentBinding.inflate(inflater, container, false)
        val root: View = binding.root
        navController = NavHostFragment.findNavController(this)

        return root
    }
}