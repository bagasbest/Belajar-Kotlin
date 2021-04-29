package com.bagasbest.fundamental2.academy.ui.reader.content

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.bagasbest.fundamental2.academy.data.ModuleEntity
import com.bagasbest.fundamental2.academy.ui.reader.CourseReaderViewModel
import com.bagasbest.fundamental2.academy.viewmodel.ViewModelFactory
import com.bagasbest.fundamental2.databinding.FragmentModuleContentBinding


class ModuleContentFragment : Fragment() {

    companion object {
        val TAG: String = ModuleContentFragment::class.java.simpleName
        fun newInstance(): ModuleContentFragment = ModuleContentFragment()
    }

    private lateinit var binding: FragmentModuleContentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentModuleContentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            // Jika Anda ganti requireActivity() dengan this, maka Fragment tidak akan mengambil ViewModel dari Activity tetapi akan membuat ViewModel baru.
            val factory = ViewModelFactory.getInstance(requireContext())
            val viewModel =
                ViewModelProvider(requireActivity(), factory)[CourseReaderViewModel::class.java]
            val module = viewModel.getSelectedModule()
            populateWebView(module)
        }
    }

    private fun populateWebView(content: ModuleEntity) {
        binding.webView.loadData(content.contentEntity?.content ?: "", "text/html", "UTF-8")
    }

}