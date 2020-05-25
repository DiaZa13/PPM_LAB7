package com.example.ppm_4.editGuest

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.example.ppm_4.R
import com.example.ppm_4.database.GuestDatabase
import com.example.ppm_4.databinding.FragmentEditguestBinding



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


        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.lifecycleOwner = this

        val application = requireNotNull(this.activity).application
        val dataSource = GuestDatabase.getInstance(application).GuestDatabaseDao
       // val editGuestFragmentArgs by navArgs<editGuestFragmentArgs>()

        //viewModelFactory = EditGuestViewModelFactory(dataSource, editGuestFragmentArgs.Id)
       // viewModel = ViewModelProvider(this, viewModelFactory).get(EditGuestViewModel::class.java)

        //binding.viewModel = viewModel

    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.delete_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.delete) {
            viewModel.deleteGuest()
            activity?.onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }


}
