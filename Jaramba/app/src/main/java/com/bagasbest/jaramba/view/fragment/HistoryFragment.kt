package com.bagasbest.jaramba.view.fragment

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bagasbest.jaramba.databinding.FragmentHistoryBinding
import com.bagasbest.jaramba.viewmodel.adapter.HistoryAdapter
import com.bagasbest.jaramba.viewmodel.utils.DatePickerFragment
import com.bagasbest.jaramba.viewmodel.viewmodel.HistoryViewModel
import com.google.firebase.auth.FirebaseAuth
import java.text.SimpleDateFormat
import java.util.*

class HistoryFragment : Fragment(), DatePickerDialog.OnDateSetListener {

  private var binding : FragmentHistoryBinding? = null
  private lateinit var viewModel: HistoryViewModel
  private lateinit var adapter: HistoryAdapter

  override fun onResume() {
    super.onResume()
    initRecyclerView()
    initViewModel("all")
  }

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {

    binding = FragmentHistoryBinding.inflate(layoutInflater, container, false)

    return binding!!.root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    binding?.historyDate?.setOnClickListener {
      val datePickerFragment = DatePickerFragment()
      datePickerFragment.setTargetFragment(this, 0)
      fragmentManager.let { it1 ->
        if (it1 != null) {
          datePickerFragment.show(it1, "DatePicker")
        }
      }
    }

    binding?.historyDateAll?.setOnClickListener {
      initRecyclerView()
      initViewModel("all")
    }

  }

  private fun initRecyclerView() {
    val layoutManager = LinearLayoutManager(activity)
    layoutManager.reverseLayout = true
    layoutManager.stackFromEnd = true
    binding?.rvHistory?.layoutManager = layoutManager
    adapter = HistoryAdapter()
    binding?.rvHistory?.adapter = adapter
  }

  private fun initViewModel(date: String) {
    // AMBIL SEMUA HISTORY BERDASARKAN CUSTOMER UID
    val uid = FirebaseAuth.getInstance().currentUser?.uid
    viewModel = activity?.let { ViewModelProvider(it, ViewModelProvider.NewInstanceFactory()) }!!.get(HistoryViewModel::class.java)



    binding?.progressBar?.visibility = View.VISIBLE
    if (uid != null && date == "all") {
      viewModel.setHistory(uid, requireActivity())
    }
    else if(uid != null && date != "all") {
      viewModel.setHistoryByDate(uid, requireActivity(), date)
    }


    viewModel.getHistoryMutableLiveData().observe(viewLifecycleOwner, {  historyList ->
      if(historyList.size > 0) {
        binding?.noData?.visibility = View.GONE
        adapter.setData(historyList)
      } else {
        binding?.noData?.visibility = View.VISIBLE
      }
      binding?.progressBar?.visibility = View.GONE
    })
  }

  override fun onDestroy() {
    super.onDestroy()
    binding = null
  }

  override fun onDateSet(p0: DatePicker?, year: Int, mon: Int, day: Int) {
    val calendar = Calendar.getInstance()
    calendar[Calendar.YEAR] = year
    calendar[Calendar.MONTH] = mon
    calendar[Calendar.DAY_OF_MONTH] = day

    val dateFormat = SimpleDateFormat("dd MMMM yyyy", Locale.getDefault())

    //set untuk TextView
    binding?.historyDate?.text = dateFormat.format(calendar.time)
    initRecyclerView()
    initViewModel(dateFormat.format(calendar.time))
  }
}