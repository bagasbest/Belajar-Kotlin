package com.bagasbest.fundamental2.academy.ui.reader.list

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.bagasbest.fundamental2.academy.data.source.local.entity.ModuleEntity
import com.bagasbest.fundamental2.academy.ui.reader.CourseReaderActivity
import com.bagasbest.fundamental2.academy.ui.reader.CourseReaderCallback
import com.bagasbest.fundamental2.academy.ui.reader.CourseReaderViewModel
import com.bagasbest.fundamental2.academy.viewmodel.ViewModelFactory
import com.bagasbest.fundamental2.academy.vo.Status
import com.bagasbest.fundamental2.databinding.FragmentModuleListBinding


class ModuleListFragment : Fragment(), MyAdapterClickListener {

    companion object {
        val TAG: String = ModuleListFragment::class.java.simpleName
        fun newInstance(): ModuleListFragment = ModuleListFragment()
    }

    private var _fragmentModuleListBinding: FragmentModuleListBinding? = null
    private val binding get() = _fragmentModuleListBinding

    private lateinit var adapter: ModuleListAdapter
    private lateinit var courseReaderCallback: CourseReaderCallback
    private lateinit var viewModel: CourseReaderViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        _fragmentModuleListBinding = FragmentModuleListBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val factory = ViewModelFactory.getInstance(requireActivity())
        viewModel = ViewModelProvider(requireActivity(), factory)[CourseReaderViewModel::class.java]
        adapter = ModuleListAdapter(this)

        viewModel.modules.observe(viewLifecycleOwner, { moduleEntities ->
            if (moduleEntities != null) {
                when (moduleEntities.status) {
                    Status.LOADING -> binding?.progressBar?.visibility = View.VISIBLE
                    Status.SUCCESS -> {
                        binding?.progressBar?.visibility = View.GONE
                        populateRecyclerView(moduleEntities.data as List<ModuleEntity>)
                    }
                    Status.ERROR -> {
                        binding?.progressBar?.visibility = View.GONE
                        Toast.makeText(context, "Terjadi kesalahan", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        })
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
        binding?.progressBar?.visibility = View.GONE
        adapter.setModules(modules)
        binding?.rvModule?.layoutManager = LinearLayoutManager(context)
        binding?.rvModule?.setHasFixedSize(true)
        binding?.rvModule?.adapter = adapter
        val dividerItemDecoration = DividerItemDecoration(binding?.rvModule?.context, DividerItemDecoration.VERTICAL)
        binding?.rvModule?.addItemDecoration(dividerItemDecoration)
    }
}