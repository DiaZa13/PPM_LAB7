package com.example.ppm_4.guests

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.ppm_4.R
import com.example.ppm_4.database.GuestDatabase
import com.example.ppm_4.databinding.FragmentGuestsBinding

/**
 * A simple [Fragment] subclass.
 */
class guestsFragment : Fragment() {

    private lateinit var viewModel: GuestsFragmentViewModel
    private lateinit var viewModelFactory: GuestsFragmentViewModelFactory
    private lateinit var  binding: FragmentGuestsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_guests, container, false)
        binding.setLifecycleOwner(this)
        binding.btnNewguest.setOnClickListener{
            view?.findNavController()?.navigate(R.id.action_guestsFragment2_to_newguestFragment)
        }

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.lifecycleOwner = this
        val application = requireNotNull(this.activity).application
        val dataSource = GuestDatabase.getInstance(application).GuestDatabaseDao
        viewModelFactory = GuestsFragmentViewModelFactory(dataSource)
        viewModel = ViewModelProvider(this, viewModelFactory).get(GuestsFragmentViewModel::class.java)
        // TODO: Use the ViewModel
        binding.viewModel = viewModel

    }


}

