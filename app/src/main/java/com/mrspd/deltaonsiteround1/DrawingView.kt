package com.mrspd.deltaonsiteround1

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.view.MotionEvent
import android.view.View


class DrawingView(context: Context?) : View(context) {
    var mX = 50f
    var mY = 50f
    private val circlePaint: Paint
    private val circlePath: Path
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawPath(path, paint)
        canvas.drawPath(circlePath, circlePaint)
        postInvalidate()
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        super.onTouchEvent(event)
        mX = event.x
        mY = event.y
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                path.moveTo(mX, mY)
            }
            MotionEvent.ACTION_MOVE -> {
                circlePath.reset()
                circlePath.addCircle(mX, mY, 30F, Path.Direction.CW)
                path.lineTo(mX, mY)
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
        var path: Path = Path()


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