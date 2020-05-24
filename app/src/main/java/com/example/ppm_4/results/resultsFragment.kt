package com.example.ppm_4.results

import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.ppm_4.R
import com.example.ppm_4.database.GuestDatabase
import com.example.ppm_4.databinding.FragmentResultsBinding
//import com.example.ppm_4.databinding.FragmentResultsBindingImpl


/**
 * A simple [Fragment] subclass.
 */
class resultsFragment : Fragment() {

    private lateinit var viewModel: ResultsFragmentViewModel
    private lateinit var viewModelFactory: ResultsFragmentViewModelFactory
    private lateinit var binding : FragmentResultsBinding
    var tRegistered : Int? = 0
    var tGuests:Int? = 0
    var msg:String? = " "

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_results, container, false)

        binding.btnReload.setOnClickListener{
            view?.findNavController()?.navigate(R.id.action_resultsFragment_to_registerFragment)

        }

        binding.btnGuests.setOnClickListener{
            Toast.makeText(activity, viewModel.guestText.value, Toast.LENGTH_SHORT).show()
        }

        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.lifecycleOwner = this
        val application = requireNotNull(this.activity).application
        val dataSource = GuestDatabase.getInstance(application).GuestDatabaseDao
        viewModelFactory = ResultsFragmentViewModelFactory(dataSource)
        viewModel = ViewModelProvider(this, viewModelFactory).get(ResultsFragmentViewModel::class.java)
        binding.viewModel = viewModel
        //binding.lifecycleOwner = viewLifecycleOwner
        viewModel.tGuests.observe(viewLifecycleOwner, Observer { tGuest ->
            binding.txtTotal.text = "Invitados: " + tGuest.toString()
        })
        viewModel.tRegistered.observe(viewLifecycleOwner, Observer { tRegister ->
            binding.txtTRegistrados.text = "Registrados: " + tRegister.toString()
        })

    }



    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.share_menu, menu)
    }

        override fun onOptionsItemSelected(item: MenuItem): Boolean {
            super.onOptionsItemSelected(item)

            if(item.itemId == R.id.share){
                val intent = Intent()
                intent.action=Intent.ACTION_SEND
                intent.putExtra(Intent.EXTRA_TEXT, msg)
                intent.type="text/plain"
                startActivity(Intent.createChooser(intent, "Share to:"))
            }
            return NavigationUI.onNavDestinationSelected(item, view!!.findNavController())
        }


}

