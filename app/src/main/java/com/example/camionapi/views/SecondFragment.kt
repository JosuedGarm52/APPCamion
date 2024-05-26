package com.example.camionapi.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner
import com.example.camionapi.R
import androidx.navigation.fragment.findNavController
import com.example.camionapi.databinding.FragmentSecondBinding

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Obtener el Spinner desde el layout
        val spinner: Spinner = view.findViewById(R.id.spinner)

        // Crear un ArrayAdapter usando el string array y un layout predeterminado del spinner
        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.spinner_items,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Especificar el layout que se usará cuando aparezcan las opciones del spinner
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Aplicar el adaptador al spinner
            spinner.adapter = adapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}