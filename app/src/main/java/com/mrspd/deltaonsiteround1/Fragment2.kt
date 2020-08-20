package com.mrspd.deltaonsiteround1

import android.content.Context
import android.graphics.Path
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import androidx.fragment.app.Fragment
import com.mrspd.deltaonsiteround1.DrawingView.Companion.path

class Fragment2 : Fragment() {
    var drawView: RelativeLayout? = null
    var listener: Fragment2Listener? = null

    interface Fragment2Listener {
        fun sendPath2(path: Path?)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView: View =
            inflater.inflate(R.layout.fragment2, container, false)
        drawView = rootView.findViewById(R.id.rect)
        return rootView
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)
        drawingView = DrawingView(this.context)
        drawView!!.addView(drawingView)
        listener!!.sendPath2(path)

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is Fragment1.Fragment1Listener) {
            listener = context as Fragment2Listener
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

    }

    companion object {
        var drawingView: DrawingView? = null
        fun newInstance(): Fragment2 {
            return Fragment2()
        }

        fun updatePath2(pathh: Path?) {
            path = pathh!!
        }
    }
}