package com.example.ppm_4.editRole

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment

import com.example.ppm_4.R
import com.example.ppm_4.databinding.FragmentEditroleBinding

class editRoleFragment : Fragment() {

    companion object {
        fun newInstance() = editRoleFragment()
    }

    private lateinit var viewModel: EditRoleViewModel
    private lateinit var binding: FragmentEditroleBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_editrole, container, false)
        binding.setLifecycleOwner(this)

        setHasOptionsMenu(true)
        return binding.root
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(EditRoleViewModel::class.java)
        // TODO: Use the ViewModel
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.delete_menu, menu)
    }

}
