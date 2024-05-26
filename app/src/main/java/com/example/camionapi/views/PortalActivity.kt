package com.example.camionapi.views

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.camionapi.R
import com.example.camionapi.databinding.ActivityPortalBinding

class PortalActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityPortalBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityPortalBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        val navController = findNavController(R.id.nav_host_fragment_content_portal)
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)

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
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_portal)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }
}