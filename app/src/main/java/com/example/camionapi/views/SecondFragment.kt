package com.example.camionapi.views

import android.app.AlertDialog
import android.os.Bundle
import android.text.Editable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.annotation.Dimension
import androidx.fragment.app.viewModels
import com.example.camionapi.R
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.camionapi.application.CamionApplication
import com.example.camionapi.databinding.FragmentSecondBinding
import com.example.camionapi.models.camion.Camion
import com.example.camionapi.models.camion.CamionItem
import com.example.camionapi.viewModel.FirstFragmentViewModel
import com.example.camionapi.viewModel.FirstFragmentViewModelFactory
import com.example.camionapi.viewModel.SecondFragmentViewModel
import com.example.camionapi.viewModel.SecondFragmentViewModelFactory
import com.google.android.material.snackbar.Snackbar

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    /*
    val combinedRepository = (requireActivity().application as CamionApplication).combinedRepository

    val secondFragmentViewModel: SecondFragmentViewModel by viewModels {
        SecondFragmentViewModelFactory(combinedRepository)
    }
    */


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        /*
        val args: SecondFragmentArgs by navArgs()

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

        //binding.edtID.isEnabled = true
        binding.btnBorrar.isEnabled = true
        binding.btnBorrar.visibility = View.VISIBLE

        if(args.ID != 0){
            binding.btnGuardar.text = "Editar"
            secondFragmentViewModel.getCamionById(args.ID).observe(viewLifecycleOwner) { Camion ->
                if (Camion != null) {
                    //binding.edtID.text = Editable.Factory.getInstance().newEditable(Camion.ID.toString())
                    //binding.edtID.isEnabled = false
                    binding.edtColor.text = Editable.Factory.getInstance().newEditable(Camion.color)
                    binding.edtMatricula.text = Editable.Factory.getInstance().newEditable(Camion.matricula)
                    binding.edtConductor.text = Editable.Factory.getInstance().newEditable(Camion.conductor)
                    binding.edtYear.text = Editable.Factory.getInstance().newEditable(Camion.yearOperative.toString())
                    binding.edtMarca.text = Editable.Factory.getInstance().newEditable(Camion.marca)
                    binding.edtModelo.text = Editable.Factory.getInstance().newEditable(Camion.modelo)
                    val tipo = Camion.tipo
                    val spinnerPosition = when (tipo) {
                        "Pequeño" -> 0
                        "Mediano" -> 1
                        "Grande" -> 2
                        "Muy Grande" -> 3
                        else -> 0 // Valor predeterminado si no coincide con ninguno de los anteriores
                    }
                    spinner.setSelection(spinnerPosition)
                    binding.edtTipo.text = Editable.Factory.getInstance().newEditable(Camion.tipo)
                }
            }
        }else{
            //añade
            binding.btnBorrar.isEnabled = false
            binding.btnBorrar.visibility = View.INVISIBLE

        }
        // Add options menu to the toolbar
        binding.btnGuardar.setOnClickListener {
            //val ID = binding.edtID.text.toString()
            val color = binding.edtColor.text.toString()
            val matricula = binding.edtMatricula.text.toString()
            val nombre = binding.edtConductor.text.toString()
            //val IDS = ID.toIntOrNull()
            val year = binding.edtYear.text.toString()
            val years = year.toIntOrNull()
            val selectedDimension = spinner.selectedItem as String
            val marca = binding.edtMarca.text.toString()
            val modelo = binding.edtModelo.text.toString()
            val tipo = binding.edtTipo.text.toString()

            if ( //IDS != null &&
                years != null
                && !color.isNullOrEmpty()
                && !matricula.isNullOrEmpty()
                && !nombre.isNullOrEmpty()
                && !selectedDimension.isNullOrEmpty()
                && !marca.isNullOrEmpty()
                && !modelo.isNullOrEmpty()
                && !tipo.isNullOrEmpty()) {
                val Camionx = CamionItem(0,color, nombre, selectedDimension, marca, matricula, modelo, tipo, years )
                if(args.ID != 0){
                    secondFragmentViewModel.updateCamion(Camionx)
                    Snackbar.make(requireView(), "Se actualizó el camión", Snackbar.LENGTH_SHORT).show()
                }else{
                    secondFragmentViewModel.insertCamion(Camionx)
                    Snackbar.make(requireView(), "Se añadio un camion", Snackbar.LENGTH_SHORT).show()
                }
                findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
            } else {
                Snackbar.make(requireView(), "Los campos no están llenados correctamente", Snackbar.LENGTH_SHORT).show()
            }
        }
        binding.btnBorrar.setOnClickListener {
            if(args.ID != 0){
                val builder = AlertDialog.Builder(requireContext())
                builder.setTitle("Confirmación")
                builder.setMessage("¿Estás seguro de que deseas eliminar este camion?")

                // Configurar el botón de confirmación
                builder.setPositiveButton("Sí") { _, _ ->
                    secondFragmentViewModel.deleteCamionById(args.ID)

                    Snackbar.make(view, "Camion: '${args.ID}' eliminada", Snackbar.LENGTH_SHORT).show()
                    findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
                }

                // Configurar el botón de cancelación
                builder.setNegativeButton("No") { _, _ ->
                    Snackbar.make(view, "Eliminación cancelada", Snackbar.LENGTH_SHORT).show()
                }

                // Mostrar el cuadro de diálogo
                builder.show()
            }
        }

         */
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}