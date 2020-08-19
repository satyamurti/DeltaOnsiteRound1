package com.mrspd.deltaonsiteround1

import android.content.Context
import android.graphics.*
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.LiveData
import com.mrspd.deltaonsiteround1.MainActivity.Companion.pathh


class Fragment1 : Fragment() {
    lateinit var pathsInterface: PathsInterface
    private  var  mPath: Path = Path()
    private lateinit var mPaint: Paint
    lateinit var count: LiveData<Path>

    companion object {
        const val TOUCH_TOLERANCE = 4f
        fun newInstance(): Fragment1 {
            return Fragment1()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val rootView: View =
            inflater.inflate(R.layout.fragment1, container, false)
        val relativeLayout =
            rootView.findViewById<View>(R.id.rect) as RelativeLayout
        relativeLayout.addView(DrawingView(activity))

        return rootView;
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val mainActivity = MainActivity
        mPaint = Paint()
        mPaint.isAntiAlias = true
        mPaint.isDither = true
        mPaint.color = Color.GREEN
        mPaint.style = Paint.Style.STROKE
        mPaint.strokeJoin = Paint.Join.ROUND
        mPaint.strokeCap = Paint.Cap.ROUND
        mPaint.strokeWidth = 12f


    }
    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is PathsInterface) {
            pathsInterface = context as PathsInterface
        } else {
            throw RuntimeException(
                context.toString()
                        + " must implement FragmentAListener"
            )
        }
    }
    inner class DrawingView(context: FragmentActivity?) : View(context) {
        val mainActivity = MainActivity
        private lateinit var mBitmap: Bitmap
        private lateinit var mCanvas: Canvas
        private var mPath: Path = Path()
        private val mBitmapPaint: Paint
        private val circlePaint: Paint
        private val circlePath: Path
        override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
            super.onSizeChanged(w, h, oldw, oldh)
            mBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888)
            mCanvas = Canvas(mBitmap)
        }

        override fun onDraw(canvas: Canvas) {
            super.onDraw(canvas)
            pathh.value = mPath //livedata waala
//            pathLiveData.value = mPath
            canvas.drawBitmap(mBitmap, 0F, 0F, mBitmapPaint)
            canvas.drawPath(mPath, mPaint)
            canvas.drawPath(circlePath, circlePaint)

        }

        private var mX = 0f
        private var mY = 0f
         fun touch_start(x: Float, y: Float) {

            mPath.moveTo(x, y)
            mX = x
            mY = y
        }

        private fun touch_move(x: Float, y: Float) {
            val dx = Math.abs(x - mX)
            val dy = Math.abs(y - mY)
            if (dx >= TOUCH_TOLERANCE || dy >= TOUCH_TOLERANCE) {
                mPath.quadTo(mX, mY, (x + mX) / 2, (y + mY) / 2)
                mX = x
                mY = y
                circlePath.reset()
                circlePath.addCircle(mX, mY, 30F, Path.Direction.CW)
                pathsInterface.drawPaths(mPath)
            }
        }


        private fun touch_up() {
            mPath.lineTo(mX, mY)
            circlePath.reset()
            mCanvas.drawPath(mPath, mPaint)
        }

        override fun onTouchEvent(event: MotionEvent): Boolean {
            val x = event.x
            val y = event.y
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    touch_start(x, y)
                    invalidate()
                }
                MotionEvent.ACTION_MOVE -> {

                    touch_move(x, y)
                    invalidate()
                }
                MotionEvent.ACTION_UP -> {
                    touch_up()
                    invalidate()
                }
            }
            return true
        }

        init {
            mPaint = Paint()
            if (pathh.value == null){
                pathh.value = mPath
            }
            else{
                mPath = pathh.value!!
            }
            mBitmapPaint = Paint(Paint.DITHER_FLAG)
            circlePaint = Paint()
            circlePath = Path()
            circlePaint.isAntiAlias = true
            circlePaint.color = Color.BLUE
            circlePaint.style = Paint.Style.STROKE
            circlePaint.strokeJoin = Paint.Join.MITER
            circlePaint.strokeWidth = 4f
        }


    }

}