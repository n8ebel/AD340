package com.goobar.io.ad340.details

import android.os.Bundle
import android.view.*
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import androidx.navigation.navGraphViewModels
import coil.api.load
import com.goobar.io.ad340.*
import com.goobar.io.ad340.databinding.FragmentForecastDetailsBinding
import java.text.SimpleDateFormat
import java.util.*

class ForecastDetailsFragment : Fragment() {

    private val args: ForecastDetailsFragmentArgs by navArgs()
    private val viewModel: ForecastDetailsViewModel by viewModels()

    private var _binding: FragmentForecastDetailsBinding? = null
    // This property only valid between onCreateView and onDestroyView
    private val binding get() = _binding!!

    private lateinit var tempDisplaySettingManager: TempDisplaySettingManager

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentForecastDetailsBinding.inflate(inflater, container, false)
        tempDisplaySettingManager = TempDisplaySettingManager(requireContext())
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewStateObserver = Observer<ForecastDetailsViewState> { viewState ->
            binding.tempText.text = formatTempForDisplay(viewState.temp, tempDisplaySettingManager.getTempDisplaySetting())
            binding.descriptionText.text = viewState.description
            binding.dateText.text = viewState.date
            binding.forecastIcon.load(viewState.iconUrl)
        }
        viewModel.viewState.observe(viewLifecycleOwner, viewStateObserver)
        viewModel.processArgs(args)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
