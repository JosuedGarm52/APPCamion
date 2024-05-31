package com.example.camionapi.views

import android.content.Intent
import android.os.Bundle
import android.view.View
//import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.camionapi.databinding.ActivityMainBinding
import android.widget.Toast
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.camionapi.R
import com.example.camionapi.application.CamionApplication
import com.example.camionapi.models.random.Cuenta
import com.example.camionapi.utils.MensajeRapido
import com.example.camionapi.utils.MyAppConfig
import com.example.camionapi.viewModel.MainActivityViewModel
import com.example.camionapi.viewModel.MainActivityViewModelFactory
import com.example.camionapi.viewModel.SecondFragmentViewModel
import com.example.camionapi.viewModel.SecondFragmentViewModelFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var viewModel: MainActivityViewModel

    //private lateinit var txtUsername : EditText
    //private lateinit var txtPassword : EditText
    //private lateinit var btnLogin : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        // Obtén la instancia del repository desde la aplicación
        val combinedRepository = (application as CamionApplication).combinedRepository

        // Inicializa el ViewModel usando el ViewModelProvider
        viewModel = ViewModelProvider(this, MainActivityViewModelFactory(combinedRepository))
            .get(MainActivityViewModel::class.java)


        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        viewModel.isRegisterLinkClicked.observe(this, Observer { isClicked ->
            if (isClicked) {
                //Toast.makeText(this, "¡Te diriges al registro!", Toast.LENGTH_SHORT).show()
                // Aquí puedes añadir lógica adicional como navegar a una nueva actividad

                // Llamar a la función isConnected para verificar la conexión
                viewModel.isConnected()

                val intent = Intent(this, PortalActivity::class.java)
                startActivity(intent)
            }
        })

        binding.btnLogin.setOnClickListener(View.OnClickListener {
            val user = binding.edtUsername.text.toString()
            val contra = binding.edtPassword.text.toString()
            if (user.isNullOrEmpty() && contra.isNullOrEmpty()){
                Toast.makeText(this, "Los campos estan vacios", Toast.LENGTH_SHORT).show()
            }else{
                lifecycleScope.launch {
                    val resultado = viewModel.loggin(Cuenta(user, contra))
                    if(resultado !=null){
                        withContext(Dispatchers.Main) {
                            Toast.makeText(this@MainActivity, resultado.message, Toast.LENGTH_SHORT).show()
                        }
                        if(resultado.message == "Inicio de sesión exitoso"){
                            // Navegar a PortalActivity
                            val intent = Intent(this@MainActivity, PortalActivity::class.java)
                            startActivity(intent)
                        }
                    }
                }
            }
        })
    }
}