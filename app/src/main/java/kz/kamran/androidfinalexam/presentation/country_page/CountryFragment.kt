package kz.kamran.androidfinalexam.presentation.country_page

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.processor.internal.definecomponent.codegen._dagger_hilt_android_internal_builders_ActivityComponentBuilder
import kz.kamran.androidfinalexam.R
import kz.kamran.androidfinalexam.data.model.Report
import kz.kamran.androidfinalexam.databinding.FragmentCountryBinding
import kz.kamran.androidfinalexam.presentation.country_page.state.CountryState
import kz.kamran.androidfinalexam.presentation.home_page.state.HomeState

@AndroidEntryPoint
class CountryFragment : Fragment() {

    private var _binding:FragmentCountryBinding? = null
    private val binding get() = _binding!!

    private val viewModel: CountryViewModel by viewModels()

    private val args : CountryFragmentArgs by navArgs()
    private val slug : String by lazy {
        args.slug
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCountryBinding.inflate(inflater, container, false)
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

        setListeners()
        setObservers()

        loadData()
    }

    private fun setListeners() {
        binding.root.setOnRefreshListener {
            viewModel.getReports(slug)
        }
        binding.imageViewBack.setOnClickListener {
            findNavController().navigateUp()
        }
    }

    private fun setObservers() {
        viewModel.countryState.observe(viewLifecycleOwner){
            when(it){
                is CountryState.Loading -> {
                    binding.root.isRefreshing = true
                }
                is CountryState.Error ->{
                    binding.root.isRefreshing = false
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_LONG).show()
                }
                is CountryState.Success ->{
                    binding.root.isRefreshing = false
                    setData(it.report)
                }
                else -> {
                    binding.root.isRefreshing = false
                }
            }
        }
    }

    private fun setData(report: Report) {
        with(binding){
            textViewActive.text = "Active ${report.active}"
            textViewCountry.text = report.country
            textViewConfirmed.text = "Confirmed ${report.confirmed}"
            textViewDate.text = report.date.toString()
            textViewDeaths.text = "Deaths ${report.deaths}"
            textViewRecovered.text = "Recovered ${report.recovered}"
        }
    }

    private fun loadData() {
        viewModel.getReports(slug)
    }

}