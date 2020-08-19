package com.mrspd.deltaonsiteround1

import android.content.Context
import android.content.Intent
import android.graphics.Path
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavController
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() , PathsInterface {

    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//
//        //Getting the Navigation Controller
//        navController = Navigation.findNavController(this, R.id.fragment)
//
//        //Setting the navigation controller to Bottom Nav
//        bottomNav.setupWithNavController(navController)
//
//
//        //Setting up the action bar
//        NavigationUI.setupActionBarWithNavController(this, navController)

    }
companion object{
// var pathh  = MutableLiveData<Path>()
}

    fun Reset(view: View) {
        val intent = intent
        overridePendingTransition(0, 0)
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
        finish()
//        pathh = MutableLiveData()
        overridePendingTransition(0, 0)
        startActivity(intent)
    }



    override  fun drawPaths(path: Path , context: Context) {
        Log.d("gghh", " out for delivery to fragment2")

//        var fragment :Fragment2 = supportFragmentManager.findFragmentById(R.id.fragment2) as Fragment2
        Fragment2().drawPaths(path , this)
}
    //Setting Up the back button
//    override fun onSupportNavigateUp(): Boolean {
//        return NavigationUI.navigateUp(navController, null)
//    }
}