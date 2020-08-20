package com.mrspd.deltaonsiteround1

import android.content.Intent
import android.graphics.Color
import android.graphics.Path
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import com.mrspd.deltaonsiteround1.DrawingView.Companion.paint


class MainActivity : AppCompatActivity() , Fragment1.Fragment1Listener, Fragment2.Fragment2Listener {

    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

//    fun Reset(view: View) {
//        val intent = intent
//        overridePendingTransition(0, 0)
//        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
//        finish()
//        overridePendingTransition(0, 0)
//        startActivity(intent)
//    }

    override fun sendPath1(path: Path?) {
        Fragment2.updatePath2(path);
    }

    override fun sendPath2(path: Path?) {
        Fragment1.updatePath1(path);
    }

    fun Red(view: View) {
        paint.setColor(Color.RED)
    }
    fun Black(view: View) {
        paint.setColor(Color.BLACK)

    }
    fun Erase(view: View) {
        val intent = intent
        overridePendingTransition(0, 0)
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
        finish()
        overridePendingTransition(0, 0)
        startActivity(intent)

    }

}