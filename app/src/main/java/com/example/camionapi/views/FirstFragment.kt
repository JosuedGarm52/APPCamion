package com.example.camionapi.views

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.camionapi.R
import com.example.camionapi.adapter.CamionAdapter
import com.example.camionapi.application.CamionApplication
import com.example.camionapi.databinding.FragmentFirstBinding
import com.example.camionapi.models.camion.CamionItem
import com.example.camionapi.repository.CombinedCamionRepository
import com.example.camionapi.viewModel.FirstFragmentViewModel
import com.example.camionapi.viewModel.FirstFragmentViewModelFactory


/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!


    val firstFragmentViewModel: FirstFragmentViewModel by viewModels {
        FirstFragmentViewModelFactory((requireActivity().application as CamionApplication).combinedRepository)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = CamionAdapter { camion ->
            onItemClick(camion)
        }

        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())

        try{
            firstFragmentViewModel.camionKardex.observe(viewLifecycleOwner,
                Observer { camiones ->
                    camiones?.let {
                        adapter.submitList(it)
                    }
                })
        }catch(e: Exception){
            Log.e("FirstFragment", "Error al observar camionKardex: ${e.message}", e)
        }


    }

    private fun onItemClick(it: CamionItem) {
        Log.d("FirstFragment", "onItem clic")
        Toast.makeText(requireContext(), "Clic al ${it.ID} matricula: ${it.matricula}", Toast.LENGTH_SHORT).show()

        val action = FirstFragmentDirections.actionFirstFragmentToSecondFragment(it.ID)
        findNavController().navigate(action)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

