package localhost.cm.gymclub.ui.auth

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import localhost.cm.gymclub.R
import localhost.cm.gymclub.shared.AuthSharedPref
import localhost.cm.gymclub.ui.auth.login.LoginActivity



class MainActivity : AppCompatActivity() {
    private lateinit var authSharedPref: AuthSharedPref
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        authSharedPref = AuthSharedPref(this)

        // verify if the user is logged in
        if (authSharedPref.jwt == null) {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        // bottom navigation setup
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment)
        val navController = navHostFragment?.findNavController()
        navController?.let {
            findViewById<BottomNavigationView>(R.id.nav_view).setupWithNavController(
                it
            )
        }
    }
}