package com.bagasbest.fundamental2.academy.ui.reader.list

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.bagasbest.fundamental2.R
import com.bagasbest.fundamental2.academy.data.ModuleEntity
import com.bagasbest.fundamental2.academy.ui.reader.CourseReaderActivity
import com.bagasbest.fundamental2.academy.ui.reader.CourseReaderCallback
import com.bagasbest.fundamental2.academy.ui.reader.CourseReaderViewModel
import com.bagasbest.fundamental2.academy.utils.DataDummy
import com.bagasbest.fundamental2.academy.viewmodel.ViewModelFactory
import com.bagasbest.fundamental2.databinding.FragmentModuleListBinding


class ModuleListFragment : Fragment(), MyAdapterClickListener {

    companion object {
        val TAG: String = ModuleListFragment::class.java.simpleName
        fun newInstance() : ModuleListFragment = ModuleListFragment()
    }

    private lateinit var binding: FragmentModuleListBinding
    private lateinit var adapter: ModuleListAdapter
    private lateinit var courseReaderCallback: CourseReaderCallback
    private lateinit var viewModel: CourseReaderViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
       binding = FragmentModuleListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //Jika Anda ganti requireActivity() dengan this, maka Fragment tidak akan mengambil ViewModel dari Activity tetapi akan membuat ViewModel baru.
        val factory = ViewModelFactory.getInstance(requireContext())
        viewModel = ViewModelProvider(requireActivity(), factory)[CourseReaderViewModel::class.java]
        adapter = ModuleListAdapter(this)
        populateRecyclerView(viewModel.getModules())
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        courseReaderCallback = context as CourseReaderActivity
    }

    override fun onItemClicked(adapterPosition: Int, moduleId: String) {
        courseReaderCallback.moveTo(adapterPosition, moduleId)
        viewModel.setSelectedModule(moduleId)
    }

    private fun populateRecyclerView(modules: List<ModuleEntity>) {
        with(binding) {
            moduleListProgressBar.visibility = View.GONE
            adapter.setModules(modules)
            rvModule.layoutManager = LinearLayoutManager(context)
            rvModule.setHasFixedSize(true)
            rvModule.adapter = adapter
            val dividerItemDecoration = DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL)
            rvModule.addItemDecoration(dividerItemDecoration)
        }
    }




}