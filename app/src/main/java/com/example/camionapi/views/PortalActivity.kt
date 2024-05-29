package com.example.camionapi.views

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.camionapi.R
import com.example.camionapi.application.CamionApplication
import com.example.camionapi.databinding.ActivityPortalBinding
import com.example.camionapi.utils.MyAppConfig
import com.example.camionapi.viewModel.FirstFragmentViewModel
import com.example.camionapi.viewModel.FirstFragmentViewModelFactory
import com.example.camionapi.viewModel.MainActivityViewModel
import com.example.camionapi.viewModel.MainActivityViewModelFactory
import com.example.camionapi.viewModel.PortalActivityViewModel
import com.example.camionapi.viewModel.PortalActivityViewModelFactory

class PortalActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityPortalBinding

    private lateinit var portalActivityViewModel: PortalActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityPortalBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        val navController = findNavController(R.id.nav_host_fragment_content_portal)
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)

        //viewModel
        val combinedRepository = (application as CamionApplication).combinedRepository
        val camionDatabase = (application as CamionApplication).database
        val context = this
        portalActivityViewModel = ViewModelProvider(this, PortalActivityViewModelFactory(combinedRepository, camionDatabase, context))
            .get(PortalActivityViewModel::class.java)

        // Observa cambios en el NavController
        navController.addOnDestinationChangedListener { controller, destination, arguments ->
            // Aquí puedes manejar los cambios en el fragmento actual
            when (destination.id) {
                R.id.FirstFragment -> {
                    // Manejar cambios específicos para el FirstFragment
                    portalActivityViewModel.isConnected()
                }
                R.id.SecondFragment -> {
                    // Manejar cambios específicos para el SecondFragment
                    portalActivityViewModel.isConnected()
                }
                // Agrega más casos según sea necesario para otros fragmentos
            }
        }

        binding.fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null)
                .setAnchorView(R.id.fab).show()
        }

        binding.btnAdd.setOnClickListener {view ->
            val currentDestinationId = findNavController(R.id.nav_host_fragment_content_portal).currentDestination?.id

            // Verifica el fragmento actual y realiza la acción correspondiente
            when (currentDestinationId) {
                R.id.FirstFragment -> {
                    findNavController(R.id.nav_host_fragment_content_portal).navigate(R.id.action_FirstFragment_to_SecondFragment)

                }
                R.id.SecondFragment -> {
                    findNavController(R.id.nav_host_fragment_content_portal).navigate(R.id.action_SecondFragment_to_FirstFragment)

                }
                else -> {
                    Snackbar.make(view, "Pulsa otro boton", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show()
                }
            }
        }
        // Observa cambios en isConect y actualiza la imagen
        observeConnectionStatus()


    }
    private fun observeConnectionStatus() {
        MyAppConfig.isConect.observe(this, Observer { connected ->
            updateConnectionStatus(connected)
        })
    }

    private fun updateConnectionStatus(isConnected: Boolean) {
        val imageView = binding.imgSignal
        if (isConnected) {
            imageView.setImageResource(R.drawable.signal) // Imagen para conexión exitosa
        } else {
            imageView.setImageResource(R.drawable.bad_signal) // Imagen para conexión fallida
        }
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_refresh -> {
                // Lógica para refrescar
                portalActivityViewModel.isConnected()
                true
            }
            R.id.action_delete_local_db -> {
                // Lógica para borrar la base de datos local
                portalActivityViewModel.deleteDatabase()
                true
            }
            R.id.action_settings -> {
                // Lógica para abrir la pantalla de ajustes
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_toolbar, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_portal)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }
}