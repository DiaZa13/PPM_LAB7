package com.example.ppm_4.newguest

import android.os.Bundle
import android.view.*
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.ppm_4.R
import com.example.ppm_4.database.GuestDatabase
import com.example.ppm_4.database.GuestDatabaseDao
import com.example.ppm_4.database.Role
import com.example.ppm_4.databinding.FragmentNewguestBinding
import com.example.ppm_4.register.RegisterFragmentViewModel
import kotlinx.android.synthetic.main.fragment_newguest.*

/**
 * A simple [Fragment] subclass.
 */
class newguestFragment : Fragment() {

    private lateinit var viewModel: NewguestFragmentViewModel
    private lateinit var viewModelFactory:NewguestFragmentViewModelFactory
    private lateinit var  binding: FragmentNewguestBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_newguest, container, false)

        binding.setLifecycleOwner(this)

        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val application = requireNotNull(this.activity).application
        val dataSource = GuestDatabase.getInstance(application).GuestDatabaseDao
        viewModelFactory = NewguestFragmentViewModelFactory(dataSource)
        viewModel = ViewModelProvider(this, viewModelFactory).get(NewguestFragmentViewModel::class.java)
        // TODO: Use the ViewModel
        binding.viewModel = viewModel

        val roles = arrayListOf<Role>()
        val rolesArray = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, roles)
        binding.roleSpinner.adapter = rolesArray

        viewModel.roles.observe(viewLifecycleOwner, Observer {
            roles.clear()
            roles.addAll(it)
            if (it.isNotEmpty()) binding.roleSpinner.setSelection(0)
            rolesArray.notifyDataSetChanged()
        })


    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.newguest_menu, menu)
    }


    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.save -> {
            val name = txtName.getText().toString()
            val phone = txtPhone.getText().toString()
            val email = txtEmail.getText().toString()
            if(name == "" || phone == ""|| email == ""){
                Toast.makeText(activity, "Llene todos los campos proporcionados", Toast.LENGTH_SHORT).show()
            }else{
            // User chose the "Settings" item, show the app settings UI...
            viewModel.insertGuest(binding.roleSpinner.selectedItem)
            view?.findNavController()?.navigate(R.id.action_newguestFragment_to_guestsFragment2)}

            true
        }
        R.id.close -> {
            // User chose the "Settings" item, show the app settings UI...
                view?.findNavController()?.navigate(R.id.action_newguestFragment_to_guestsFragment2)
            true
        }

        else -> {
            // If we got here, the user's action was not recognized.
            // Invoke the superclass to handle it.
            super.onOptionsItemSelected(item)
        }
    }
}




