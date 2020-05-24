package com.example.ppm_4.register

import android.os.Bundle
import com.example.ppm_4.R
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.lifecycle.Observer
import com.example.ppm_4.database.GuestDatabase
import com.example.ppm_4.databinding.FragmentRegisterBinding


/**
 * A simple [Fragment] subclass.
 */
class registerFragment : Fragment() {

    companion object{
        fun newInstance() = registerFragment()
    }

    private lateinit var viewModel: RegisterFragmentViewModel
    private lateinit var viewModelFactory: RegisterFragmentViewModelFactory
    private lateinit var binding:FragmentRegisterBinding
    private var tRegistered = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_register, container, false)

        setHasOptionsMenu(true)
        return binding.root
    }


    //ViewModel
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.lifecycleOwner = this
        val application = requireNotNull(this.activity).application
        val dataSource = GuestDatabase.getInstance(application).GuestDatabaseDao
        viewModelFactory = RegisterFragmentViewModelFactory(dataSource)
        viewModel = ViewModelProvider(this, viewModelFactory).get(RegisterFragmentViewModel::class.java)
        binding.viewModel = viewModel

        viewModel.registerComplete.observe(viewLifecycleOwner, Observer {
            if (it) {
                view?.findNavController()?.navigate(R.id.action_registerFragment_to_resultsFragment)
                viewModel.finishRegister()
            }
        })

        viewModel.guests.observe(viewLifecycleOwner, Observer {
            viewModel.initialize(it)
            (activity as AppCompatActivity).supportActionBar?.title = "Registrando (" + viewModel.guestIndex + "/ " + viewModel.totalGuests +")"
        })



    }

    //ActionBar
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.action_bar, menu)
    }


    //SelectedButtonActionBar
    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.registered -> {
            // User chose the "Settings" item, show the app settings UI...
            viewModel.updateRegisteredCurrentGuest()
            true
        }
        R.id.Notregistered -> {
            // User chose the "Settings" item, show the app settings UI...
            viewModel.updateNotRegisteredCurrentGuest()

            true
        }

        else -> {
            // If we got here, the user's action was not recognized.
            // Invoke the superclass to handle it.
            super.onOptionsItemSelected(item)
        }
    }




}
