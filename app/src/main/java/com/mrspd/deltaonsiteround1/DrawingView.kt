package com.mrspd.deltaonsiteround1

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.view.MotionEvent
import android.view.View
import androidx.core.util.Pair


class DrawingView(context: Context?) : View(context) {
    var mX = 50f
    var mY = 50f
    private val circlePaint: Paint
    private val circlePath: Path
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        for (pair in allPairs!!) {
            assert(pair.first != null)
            assert(pair.second != null)
            canvas.drawPath(pair.first!!, pair.second!!)
        }
        //canvas.drawPath(path, paint)
        canvas.drawPath(circlePath, circlePaint)
        canvas.save()
        invalidate()
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        super.onTouchEvent(event)
        mX = event.x
        mY = event.y
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
//                path.moveTo(mX, mY)
                if (!eraserOn)
                    allPairs!!.add(getDrawingPair(mX, mY))
                else
                    allPairs!!.add(getEraserPair(mX, mY))


            }
            MotionEvent.ACTION_MOVE -> {
                circlePath.reset()
                circlePath.addCircle(mX, mY, 30F, Path.Direction.CW)
//                path.lineTo(mX, mY)
                allPairs!![allPairs!!.size - 1].first!!.lineTo(mX, mY)
            }
            MotionEvent.ACTION_UP -> {

                return false
            }
        }
        invalidate()
        return true
    }

    companion object {
        var paint: Paint = Paint()
        lateinit var path: Path
        var ColorOfPath = 0
        var eraserOn = false
        var allPairs: ArrayList<Pair<Path, Paint>>? = ArrayList()


    }

    private fun getDrawingPair(
        x: Float,
        y: Float
    ): Pair<Path, Paint> {
        var painttt: Paint = Paint()
        val pathh: Path = Path()
        if (ColorOfPath == 1) {
            painttt.isAntiAlias = true
            painttt.isDither = true
            painttt.color = Color.BLACK
            painttt.style = Paint.Style.STROKE
            painttt.strokeJoin = Paint.Join.ROUND
            painttt.strokeCap = Paint.Cap.ROUND
            painttt.strokeWidth = 12f
        } else if (ColorOfPath == 2) {
            painttt.isAntiAlias = true
            painttt.isDither = true
            painttt.color = Color.RED
            painttt.style = Paint.Style.STROKE
            painttt.strokeJoin = Paint.Join.ROUND
            painttt.strokeCap = Paint.Cap.ROUND
            painttt.strokeWidth = 12f
        } else {
            painttt.isAntiAlias = true
            painttt.isDither = true
            painttt.color = Color.GREEN
            painttt.style = Paint.Style.STROKE
            painttt.strokeJoin = Paint.Join.ROUND
            painttt.strokeCap = Paint.Cap.ROUND
            painttt.strokeWidth = 12f
        }
        pathh.moveTo(x, y)
        return Pair(pathh, painttt)

    }

    private fun getEraserPair(
        x: Float,
        y: Float
    ): Pair<Path, Paint> {
        val eraserPath: Path
        val eraserPaint: Paint
        eraserPath = Path()
        eraserPath.moveTo(x, y)
        eraserPaint = Paint()
        eraserPaint.color = Color.WHITE
        eraserPaint.style = Paint.Style.STROKE
        eraserPaint.strokeCap = Paint.Cap.ROUND
        eraserPaint.strokeJoin = Paint.Join.ROUND
        eraserPaint.isAntiAlias = true
        eraserPaint.strokeWidth = 30f
        return Pair(
            eraserPath,
            eraserPaint
        )
    }

    init {
        paint = Paint()
        path = Path()
        paint.isAntiAlias = true
        paint.isDither = true
        paint.color = Color.GREEN
        paint.style = Paint.Style.STROKE
        paint.strokeJoin = Paint.Join.ROUND
        paint.strokeCap = Paint.Cap.ROUND
        paint.strokeWidth = 12f

        circlePaint = Paint()
        circlePath = Path()
        circlePaint.isAntiAlias = true
        circlePaint.color = Color.BLUE
        circlePaint.style = Paint.Style.STROKE
        circlePaint.strokeJoin = Paint.Join.MITER
        circlePaint.strokeWidth = 4f
    }
}