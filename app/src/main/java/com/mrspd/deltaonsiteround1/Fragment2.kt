package com.mrspd.deltaonsiteround1

import android.content.Context
import android.graphics.*
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.mrspd.deltaonsiteround1.MainActivity.Companion.pathh


class Fragment2 : Fragment(), PathsInterface {
        var flag = true
    private lateinit var mPaint: Paint
    private var mPath: Path = Path()
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
            inflater.inflate(R.layout.fragment2, container, false)
        val relativeLayout =
            rootView.findViewById<View>(R.id.rect) as RelativeLayout
        relativeLayout.addView(DrawingView(activity as MainActivity))

        return rootView;
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mPaint = Paint()
        mPaint.isAntiAlias = true
        mPaint.isDither = true
        mPaint.color = Color.GREEN
        mPaint.style = Paint.Style.STROKE
        mPaint.strokeJoin = Paint.Join.ROUND
        mPaint.strokeCap = Paint.Cap.ROUND
        mPaint.strokeWidth = 12f
        count = MutableLiveData<Path>()

        count.observe(this, Observer {
            Log.d("gghh", "changes observed")
            mPath = it
            DrawingView(context as MainActivity).postInvalidate()
        })

//        btReset2.setOnClickListener {
//            pathh.reset()
//            DrawingView(activity).touch_start(0F,0F)
//            flag = false
//        }
    }

    inner class DrawingView(context: Context?) : View(context), PathsInterface {
        //        override fun drawPaths(path: Path) {
//            Log.d("gghh", "BOOOOMM")
//            mPath = path
//            invalidate()
//        }
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
            //   redrawpaths()
            canvas.drawBitmap(mBitmap, 0F, 0F, mBitmapPaint)
            canvas.drawPath(mPath, mPaint)
            canvas.drawPath(circlePath, circlePaint)
//            if (!flag){
//                canvas.drawColor(Color.WHITE)
//                flag = true
//                pathh.reset()
//            }
        }

        private var mX = 0f
        private var mY = 0f
        fun touch_start(x: Float, y: Float) {
            if (!flag) {
                mPath.reset()
                flag = true
            }
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
            }
        }

        private fun touch_up() {
            mPath.lineTo(mX, mY)
            circlePath.reset()
            mCanvas.drawPath(mPath, mPaint)


        }

        fun redrawpaths() {
            pathh.observe(viewLifecycleOwner, Observer {
                Log.d("gghh", "changes observed")
                mPath = it
            })

            invalidate()
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
//            mPath = pathh.value!!
            mBitmapPaint = Paint(Paint.DITHER_FLAG)
            circlePaint = Paint()
            circlePath = Path()

            circlePaint.isAntiAlias = true
            circlePaint.color = Color.BLUE
            circlePaint.style = Paint.Style.STROKE
            circlePaint.strokeJoin = Paint.Join.MITER
            circlePaint.strokeWidth = 4f
        }

        override fun drawPaths(path: Path) {
            mPath = path
            Log.d("gghh", "Boom1")
            invalidate()
        }


    }

    override fun drawPaths(path: Path) {
        mPath = path
        Log.d("gghh", "Boom1")

        DrawingView(context).invalidate()
    }

}