package com.example.characterviewer.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.characterviewer.databinding.FragmentSearchDetailsBinding
import com.example.characterviewer.MainActivityViewModel
import org.koin.androidx.viewmodel.ext.android.activityViewModel

class SearchDetails : Fragment() {

    private val viewModel: MainActivityViewModel by activityViewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = FragmentSearchDetailsBinding.inflate(inflater, container, false)
        view.viewModel = viewModel
        view.lifecycleOwner = this
        return view.root
    }
}