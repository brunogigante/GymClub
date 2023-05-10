package localhost.cm.gymclub.ui.auth.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import localhost.cm.gymclub.databinding.ActivityLoginBinding
import localhost.cm.gymclub.shared.AuthSharedPref
import localhost.cm.gymclub.ui.MainActivity
import localhost.cm.gymclub.ui.auth.register.RegisterActivity


@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private val viewModel: LoginViewModel by viewModels()
    private lateinit var authSharedPref: AuthSharedPref


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)

        authSharedPref = AuthSharedPref(this)

        // verify if the user is logged in
        if (authSharedPref.jwt != null) {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        viewModel.shouldClose.observe(this) {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        viewModel.toasts.observe(this) {
            Toast.makeText(this, it, Toast.LENGTH_LONG).show()
        }

        binding.logIn.setOnClickListener {
            val email = binding.editTextTextEmailAddress.text.toString()
            val password = binding.editTextTextPassword.text.toString()

            viewModel.login(email, password)
        }

        binding.registerTextView.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

        setContentView(binding.root)
    }
}