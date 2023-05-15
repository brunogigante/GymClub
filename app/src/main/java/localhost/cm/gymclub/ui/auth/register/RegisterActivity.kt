package localhost.cm.gymclub.ui.auth.register

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.navigation.findNavController
import dagger.hilt.android.AndroidEntryPoint
import localhost.cm.gymclub.R
import localhost.cm.gymclub.databinding.ActivityRegisterBinding
import localhost.cm.gymclub.ui.auth.login.LoginActivity


@AndroidEntryPoint
class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    private val viewModel: RegisterViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)

        binding.signUpButton?.setOnClickListener {
            val firstName = binding.editTextFirstName?.text.toString()
            val lastName = binding.editTextLastName?.text.toString()
            val email = binding.editTextEmailAddressRegister?.text.toString()
            val password = binding.editTextPasswordRegister?.text.toString()
            val confirmPassword = binding.editTextConfirmPasswordRegister?.text.toString()

            if (password != confirmPassword) {
                Toast.makeText(
                    this,
                    getString(R.string.different_passwords),
                    Toast.LENGTH_LONG
                ).show()
                return@setOnClickListener
            }
            viewModel.register(firstName, lastName, email, password)

            Toast.makeText(
                this,
                getString(R.string.user_registered),
                Toast.LENGTH_LONG).show()

            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        binding.textSignIN.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        setContentView(binding.root)
    }
}