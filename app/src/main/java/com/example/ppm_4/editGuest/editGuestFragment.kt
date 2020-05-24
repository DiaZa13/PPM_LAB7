package com.example.ppm_4.editGuest

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.ppm_4.R
import com.example.ppm_4.databinding.FragmentEditguestBinding
import com.example.ppm_4.databinding.FragmentGuestsBinding


class editGuestFragment : Fragment() {

    companion object {
        fun newInstance() = editGuestFragment()
    }

    private lateinit var viewModel: EditGuestViewModel
    private lateinit var binding: FragmentEditguestBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_editguest, container, false)
        binding.setLifecycleOwner(this)

        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(EditGuestViewModel::class.java)
        // TODO: Use the ViewModel
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.delete_menu, menu)
    }


}
