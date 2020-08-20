package com.mrspd.deltaonsiteround1

import android.content.Intent
import android.graphics.Color
import android.graphics.Path
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import com.mrspd.deltaonsiteround1.DrawingView.Companion.ColorOfPath
import com.mrspd.deltaonsiteround1.DrawingView.Companion.eraserOn
import com.mrspd.deltaonsiteround1.DrawingView.Companion.paint
import com.mrspd.deltaonsiteround1.DrawingView.Companion.path
import kotlinx.android.synthetic.main.activity_main.*


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

    override fun sendPath1(pathh: Path?) {


        Fragment2.updatePath2(pathh);
    }

    override fun sendPath2(pathh: Path?) {


        Fragment1.updatePath1(pathh);
    }

    fun Red(view: View) {
        path = Path()
        ColorOfPath = 2
        paint.color = Color.RED
        eraserOn = false
    }
    fun Black(view: View) {
        path = Path()
        ColorOfPath = 1
        paint.setColor(Color.BLACK)
        eraserOn = false

    }
    fun Erase(view: View) {
//        val intent = intent
//        overridePendingTransition(0, 0)
//        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
//        finish()
//        overridePendingTransition(0, 0)
//        startActivity(intent)
        eraserOn = true
    }

}