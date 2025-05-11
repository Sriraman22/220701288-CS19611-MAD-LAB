package com.example.ex_3
import android.content.Context
import android.graphics.*
import android.util.*
import android.view.MotionEvent
import android.view.View

class TouchScreen(context: Context, attributeSet: AttributeSet) : View(context, attributeSet) {
    var paint = Paint()
    var path = Path()
    var flag = 0
    var x1 = 0f
    var y1 = 0f
    var x2 = 0f
    var y2 = 0f

    init {
        paint.color = Color.RED
        paint.isAntiAlias = true
        paint.strokeJoin = Paint.Join.ROUND
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = 5f
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawPath(path, paint)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        if (flag == 2) {
            val x = event.x
            val y = event.y
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    path.moveTo(x, y)
                }
                MotionEvent.ACTION_MOVE -> {
                    path.lineTo(x, y)
                }
                MotionEvent.ACTION_UP -> {
                    return false
                }
                else -> return false
            }
        }

        if (flag == 0 || flag == 1) {
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    x1 = event.x
                    y1 = event.y
                    return true
                }
                MotionEvent.ACTION_MOVE -> return false
                MotionEvent.ACTION_UP -> {
                    x2 = event.x
                    y2 = event.y
                    val rectF = RectF(x1, y1, x2, y2)
                    if (flag == 0) {
                        path.addOval(rectF, Path.Direction.CCW)
                    } else {
                        path.addRect(rectF, Path.Direction.CCW)
                    }
                    invalidate()
                    return true
                }
            }
        }
        return true
    }

    fun setDraw(f: Int) {
        flag = f
    }

    fun startDrawing() {
        path.rewind()
        invalidate()
    }
}
