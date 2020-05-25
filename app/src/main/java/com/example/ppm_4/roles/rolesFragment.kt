package com.example.ppm_4.roles

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.ppm_4.R
import com.example.ppm_4.database.GuestDatabase
import com.example.ppm_4.databinding.FragmentRolesBinding

class rolesFragment : Fragment() {

    companion object {
        fun newInstance() = rolesFragment()
    }

    private lateinit var viewModel: RolesViewModel
    private lateinit var viewModelFactory: RolesViewModelFactory
    private lateinit var  binding: FragmentRolesBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View?{
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_roles, container, false)
        binding.setLifecycleOwner(this)
        binding.btnNewRol.setOnClickListener{
            view?.findNavController()?.navigate(R.id.action_rolesFragment2_to_newroleFragment2)
        }

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.lifecycleOwner = this
        val application = requireNotNull(this.activity).application
        val dataSource = GuestDatabase.getInstance(application).GuestDatabaseDao
        viewModelFactory = RolesViewModelFactory(dataSource)
        viewModel = ViewModelProvider(this, viewModelFactory).get(RolesViewModel::class.java)
        // TODO: Use the ViewModel
        binding.viewModel = viewModel

        viewModel.roleClicked.observe(viewLifecycleOwner, Observer {
            if(it != null){
                view?.findNavController()?.navigate(R.id.action_rolesFragment2_to_editRoleFragment)
            }
        })

        val adapter = roleAdapter(RoleListener {
            viewModel.onRoleClicked(it)
        })

        binding.roleList.adapter = adapter;

        viewModel.roles.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.submitList(it)
            }
        })
    }

}
