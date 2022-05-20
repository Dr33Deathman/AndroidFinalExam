package kz.kamran.androidfinalexam.presentation.home_page

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import kz.kamran.androidfinalexam.R
import kz.kamran.androidfinalexam.databinding.FragmentHomeBinding
import kz.kamran.androidfinalexam.presentation.home_page.rv.CountryAdapter
import kz.kamran.androidfinalexam.presentation.home_page.state.HomeState

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? =null
    private val binding get() = _binding!!

    private val viewModel: HomeViewModel by viewModels()
    private val adapter:CountryAdapter by lazy {
        CountryAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onDestroy() {
        viewModel.cancel()
        super.onDestroy()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRv()
        setListeners()
        setObservers()
    }

    private fun initRv() {
        binding.recyclerViewCountries.adapter = adapter
        adapter.onItemClick = {
            findNavController().navigate(
                HomeFragmentDirections.actionHomeFragmentToCountryFragment(it)
            )
        }
    }

    private fun setObservers() {
        viewModel.homeState.observe(viewLifecycleOwner){
            when(it){
                is HomeState.Loading -> {
                    binding.root.isRefreshing = true
                }
                is HomeState.Error ->{
                    binding.root.isRefreshing = false
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_LONG).show()
                }
                is HomeState.Success ->{
                    binding.root.isRefreshing = false
                    Log.d("COUNTEIREREIRE", it.countries.toString())
                    adapter.countries = it.countries
                }
                else ->{
                    binding.root.isRefreshing = false
                }
            }
        }
    }

    private fun setListeners(){
        binding.root.setOnRefreshListener {
            viewModel.getList()
        }
    }


}