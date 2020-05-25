package com.example.ppm_4.newrole

import android.os.Bundle
import android.view.*
import android.widget.SeekBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController

import com.example.ppm_4.R
import com.example.ppm_4.database.GuestDatabase
import com.example.ppm_4.databinding.NewroleFragmentBinding
import com.example.ppm_4.newguest.NewguestFragmentViewModel
import com.example.ppm_4.newguest.NewguestFragmentViewModelFactory
import kotlinx.android.synthetic.main.newrole_fragment.txtName
import kotlinx.android.synthetic.main.newrole_fragment.*

class newroleFragment : Fragment() {

    private lateinit var viewModel: NewroleViewModel
    private lateinit var viewModelFactory: NewroleViewModelFactory
    private lateinit var  binding: NewroleFragmentBinding
    var contador: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.newrole_fragment, container, false)
        //contador = binding.sbOrden.getProgress()
        binding.sbOrden.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                binding.txtOrder.text = progress.toString()
            }
            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }
            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }

        })
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val application = requireNotNull(this.activity).application
        val dataSource = GuestDatabase.getInstance(application).GuestDatabaseDao
        viewModelFactory = NewroleViewModelFactory(dataSource)
        viewModel = ViewModelProvider(this, viewModelFactory).get(NewroleViewModel::class.java)
        // TODO: Use the ViewModel
        binding.viewModel = viewModel

    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.newguest_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.save -> {
            val name = txtName.getText().toString()
            val description = txtDescription.getText().toString()
            if(name == "" || description == ""){
                Toast.makeText(activity, "Llene todos los campos proporcionados", Toast.LENGTH_SHORT).show()
            }else{
                // User chose the "Settings" item, show the app settings UI...
                val index: Int = txtIcon.getText().toString().toInt()
                viewModel.insertRole(index)
                view?.findNavController()?.navigate(R.id.action_newroleFragment2_to_rolesFragment2)}

            true
        }
        R.id.close -> {
            // User chose the "Settings" item, show the app settings UI...
            view?.findNavController()?.navigate(R.id.action_newroleFragment2_to_rolesFragment2)
            true
        }

        else -> {
            // If we got here, the user's action was not recognized.
            // Invoke the superclass to handle it.
            super.onOptionsItemSelected(item)
        }
    }
}
