package by.itacademy.korobchits.dz5

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.PointF
import android.graphics.Rect
import android.graphics.RectF
import android.os.Build
import android.util.AttributeSet
import android.util.Log
import android.view.View
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import by.itacademy.korobchits.R
import java.util.Random

class MyDiagramView : View {

    private val linePaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val arcPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val pointPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val textNumberPaint = Paint(Paint.ANTI_ALIAS_FLAG)

    private val rnd = Random()

    private var listPairsNumberAndDegree = mutableListOf<Pair<Int, Float>>()
    private var sumNumbers = 0
    private var centerX = 0f
    private var centerY = 0f
    private var radius = 0f
    private var rect = RectF()

    var arrayNumbers: List<Int> = emptyList()
        set(value) {
            field = value
            invalidate()
        }

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) : super(
        context,
        attrs,
        defStyleAttr,
        defStyleRes
    )

    init {
        linePaint.color = ContextCompat.getColor(context, R.color.dz5Line)
        linePaint.strokeWidth = getResources().getDimensionPixelSize(R.dimen.dz5line).toFloat()

        pointPaint.color = ContextCompat.getColor(context, R.color.dz5Line)
        pointPaint.style = Paint.Style.FILL
        pointPaint.strokeWidth = getResources().getDimensionPixelSize(R.dimen.dz5line).toFloat()

        textNumberPaint.color = ContextCompat.getColor(context, R.color.dz5Line)
        textNumberPaint.textSize = getResources().getDimensionPixelSize(R.dimen.dz5TextNumber).toFloat()

        arcPaint.style = Paint.Style.FILL
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        val modeWidth = MeasureSpec.getMode(widthMeasureSpec)
        val modeHeight = MeasureSpec.getMode(heightMeasureSpec)

        val sizeWidth = MeasureSpec.getSize(widthMeasureSpec)
        val sizeHeight = MeasureSpec.getSize(heightMeasureSpec)

        var customSizeHeight = 0
        var customSizeWidth = 0

        when (modeWidth) {
            MeasureSpec.AT_MOST -> {
                customSizeWidth = sizeWidth
                customSizeHeight = sizeWidth
            }
            MeasureSpec.EXACTLY -> {
                customSizeWidth = sizeWidth
                customSizeHeight = sizeHeight
            }
            MeasureSpec.UNSPECIFIED -> {
                Log.e("AAA", "UNSPECIFIED")
            }
        }

        when (modeHeight) {
            MeasureSpec.AT_MOST -> {
                customSizeWidth = sizeWidth
                customSizeHeight = sizeWidth
            }
            MeasureSpec.EXACTLY -> {
                customSizeWidth = sizeWidth
                customSizeHeight = sizeHeight
            }
            MeasureSpec.UNSPECIFIED -> {
                Log.e("AAA", "UNSPECIFIED")
            }
        }

        setMeasuredDimension(customSizeWidth, customSizeHeight)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        invalidate()
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas ?: return

        drawDiagram(canvas)
    }

    private fun drawDiagram(canvas: Canvas) {

        calculateRect()
        calculateDegrees()

        var startAngle = 0f
        for (pair in listPairsNumberAndDegree) {

            arcPaint.setARGB(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256))

            canvas.save()
            canvas.rotate(startAngle + pair.second / 2 + 90f, centerX, centerY)

            var number = pair.first.toString()
            var degree = pair.second
            var rectForDrawNumber = Rect()
            textNumberPaint.getTextBounds(number, 0, number.length, rectForDrawNumber)
            var textHeight = rectForDrawNumber.height()

            canvas.drawLine(centerX, centerY - radius, centerX, (height / 2f - radius) / 1.5f, linePaint)
            canvas.drawCircle(centerX, (height / 2f - radius) / 1.5f, textHeight / 3f, pointPaint)

            canvas.rotate(-(startAngle + degree / 2f + 90f), centerX, (height / 2f - radius) / 1.5f)
            if (startAngle + degree / 2f < 90f || startAngle + degree / 2f > 270f)
                canvas.drawText(
                    number,
                    centerX + textHeight / 1.5f,
                    (height / 2f - radius) / 1.5f + textHeight / 2f,
                    textNumberPaint
                )
            else
                canvas.drawText(
                    number,
                    centerX - textNumberPaint.measureText(number) - textHeight / 1.5f,
                    (height / 2f - radius) / 1.5f + textHeight / 2f,
                    textNumberPaint
                )

            canvas.restore()
            canvas.drawArc(rect, startAngle, degree, true, arcPaint)

            startAngle += degree
        }
    }

    private fun calculateDegrees() {
        sumNumbers = arrayNumbers.sum()
        listPairsNumberAndDegree = mutableListOf()
        for (i in arrayNumbers)
            listPairsNumberAndDegree.add(i to i * 360f / sumNumbers)
    }

    private fun calculateRect() {
        centerX = width / 2f
        centerY = height / 2f
        var centerOfCanvas = PointF(centerX, centerY)
        var rectSide = (Math.min(width, height) * 0.6f)
        radius = rectSide / 2f
        rect.set(
            centerOfCanvas.x - radius,
            centerOfCanvas.y - radius,
            centerOfCanvas.x + radius,
            centerOfCanvas.y + radius
        )
    }
}
