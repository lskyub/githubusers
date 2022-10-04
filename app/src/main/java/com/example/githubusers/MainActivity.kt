package com.example.githubusers

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.annotation.IdRes
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.navigation.Navigator
import androidx.navigation.fragment.NavHostFragment
import com.example.githubusers.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private lateinit var navController: NavController

    val viewModel: MainActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        binding.lifecycleOwner = this

        setContentView(binding.root)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController

        viewModel.moveValue.observe(this) {
            if (it == R.id.btn_api) {
                navigateSafe(R.id.action_navigation_local_to_navigation_main)
            } else if (it == R.id.btn_local) {
                navigateSafe(R.id.action_navigation_main_to_navigation_local)
            }
        }
    }

    fun navigateSafe(
        @IdRes resId: Int,
        args: Bundle? = null,
        navOptions: NavOptions? = null,
        navExtras: Navigator.Extras? = null
    ) {
        val action =
            navController.currentDestination?.getAction(resId) ?: navController.graph.getAction(
                resId
            )
        if (action != null && navController.currentDestination?.id != action.destinationId) {
            navController.navigate(resId, args, navOptions, navExtras)
        }
    }
}