package io.rajeshp.adaptiveapp.ui.common.extensions

import android.os.Bundle
import android.view.Menu
import androidx.annotation.IdRes
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.ui.NavigationUI
import com.ismaeldivita.chipnavigation.ChipNavigationBar
import java.lang.ref.WeakReference

fun ChipNavigationBar.setupWithNavController(
    menu: Menu,
    navController: NavController
) {
    setOnItemSelectedListener {
        NavigationUI.onNavDestinationSelected(menu.findItem(it), navController)
    }

    val weakReference = WeakReference(this)

    navController.addOnDestinationChangedListener(object : NavController.OnDestinationChangedListener {
        override fun onDestinationChanged(
            controller: NavController,
            destination: NavDestination,
            arguments: Bundle?
        ) {
            val view = weakReference.get()

            if (view == null) {
                navController.removeOnDestinationChangedListener(this)
                return
            }

            for (i in 0 until menu.size()) {
                val menuItem = menu.getItem(i)
                val id = menuItem.itemId
                if (matchDestination(destination, id)) {
                    menuItem.isChecked = true
                    setItemSelected(id)
                }
            }
        }

        fun matchDestination(
            destination: NavDestination,
            @IdRes destId: Int
        ): Boolean {
            var currentDestination: NavDestination? = destination
            while (currentDestination!!.id != destId && currentDestination.parent != null) {
                currentDestination = currentDestination.parent
            }
            return currentDestination.id == destId
        }
    })
}