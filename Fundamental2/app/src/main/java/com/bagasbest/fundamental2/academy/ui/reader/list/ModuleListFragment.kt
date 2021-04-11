package com.bagasbest.fundamental2.academy.ui.reader.list

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.bagasbest.fundamental2.R
import com.bagasbest.fundamental2.academy.data.ModuleEntity
import com.bagasbest.fundamental2.academy.ui.reader.CourseReaderActivity
import com.bagasbest.fundamental2.academy.ui.reader.CourseReaderCallback
import com.bagasbest.fundamental2.academy.utils.DataDummy
import com.bagasbest.fundamental2.databinding.FragmentModuleListBinding


class ModuleListFragment : Fragment(), MyAdapterClickListener {

    companion object {
        val TAG: String = ModuleListFragment::class.java.simpleName
        fun newInstance() : ModuleListFragment = ModuleListFragment()
    }

    private lateinit var binding: FragmentModuleListBinding
    private lateinit var adapter: ModuleListAdapter
    private lateinit var courseReaderCallback: CourseReaderCallback

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
        adapter = ModuleListAdapter(this)
        populateRecyclerView(DataDummy.generateDummyModules("a14"))
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        courseReaderCallback = context as CourseReaderActivity
    }

    override fun onItemClicked(adapterPosition: Int, moduleId: String) {
        courseReaderCallback.moveTo(adapterPosition, moduleId)
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