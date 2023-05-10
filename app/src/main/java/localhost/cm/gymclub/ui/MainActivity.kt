package localhost.cm.gymclub.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint
import localhost.cm.gymclub.R
import localhost.cm.gymclub.shared.AuthSharedPref
import localhost.cm.gymclub.ui.auth.login.LoginActivity


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private var navController: NavController? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // bottom navigation setup
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment)
        navController = navHostFragment?.findNavController()
        navController?.let {
            findViewById<BottomNavigationView>(R.id.nav_view).setupWithNavController(
                it
            )

            setupActionBarWithNavController(it)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController?.navigateUp() ?: super.onSupportNavigateUp()
    }
}