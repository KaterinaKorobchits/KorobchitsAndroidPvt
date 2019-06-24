package by.itacademy.korobchits.dz4

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.graphics.Rect
import android.os.Build
import android.util.AttributeSet
import android.view.View
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import by.itacademy.korobchits.R
import java.util.Calendar

class MyClockView : View {

    private val linePaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val lineBoldPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val clockNumbersPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val arrowClockHandPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val lineClockHandPaint = Paint(Paint.ANTI_ALIAS_FLAG)

    private val calendar = Calendar.getInstance()
    private var pathClockHand = Path()

    private var centerX: Float = 0f
    private var centerY: Float = 0f
    private var radius: Float = 0f
    private var startY: Float = 0f
    private var endY: Float = 0f
    private var heightArrow: Float = 0f
    private val clockNumbers = arrayOf(12, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11)
    private val coordinateXYDrawNumbers = mutableMapOf<Int, Pair<Float, Float>>()

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
        linePaint.color = ContextCompat.getColor(context, R.color.dz4Clock)
        linePaint.strokeWidth = getResources().getDimensionPixelSize(R.dimen.dz4line).toFloat()

        lineBoldPaint.color = ContextCompat.getColor(context, R.color.dz4Clock)
        lineBoldPaint.strokeWidth = getResources().getDimensionPixelSize(R.dimen.dz4lineBold).toFloat()

        clockNumbersPaint.color = ContextCompat.getColor(context, R.color.dz4NumberClock)
        clockNumbersPaint.style = Paint.Style.STROKE
        clockNumbersPaint.textSize = getResources().getDimensionPixelSize(R.dimen.dz4ClockNumber).toFloat()

        arrowClockHandPaint.color = ContextCompat.getColor(context, R.color.dz4NumberClock)

        lineClockHandPaint.color = ContextCompat.getColor(context, R.color.dz4NumberClock)
        lineClockHandPaint.strokeWidth = getResources().getDimensionPixelSize(R.dimen.dz4lineClockHand).toFloat()
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        centerX = width / 2f
        centerY = height / 2f
        radius = Math.min(width, height) / 2f * 0.95f
        startY = centerY - radius * 0.9f
        endY = centerY - radius

        val radiusForNumbers = Math.min(width, height) / 2f * 0.68f
        var rectForDrawNumber = Rect()
        for (number in clockNumbers) {
            val numberStr = number.toString()
            clockNumbersPaint.getTextBounds(numberStr, 0, numberStr.length, rectForDrawNumber)
            coordinateXYDrawNumbers.put(
                number,
                width / 2f - clockNumbersPaint.measureText(numberStr) / 2f + Math.sin(Math.PI / 6 * number).toFloat() * radiusForNumbers
                        to height / 2f + rectForDrawNumber.height() / 2f - Math.cos(Math.PI / 6 * number).toFloat() * radiusForNumbers
            )
        }
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas ?: return
        canvas.save()

        for (i in 0 until 60) {
            if (i % 5 == 0)
                canvas.drawLine(centerX, startY, centerX, endY, lineBoldPaint)
            else
                canvas.drawLine(centerX, startY, centerX, endY, linePaint)
            canvas.rotate(6f, centerX, centerY)
        }

        for (number in clockNumbers) {
            val textX = coordinateXYDrawNumbers[number]?.first ?: return
            val textY = coordinateXYDrawNumbers[number]?.second ?: return
            canvas.drawText(number.toString(), textX, textY, clockNumbersPaint)
        }

        val hour = if (calendar.get(Calendar.HOUR_OF_DAY) < 12) calendar.get(Calendar.HOUR_OF_DAY)
        else calendar.get(Calendar.HOUR_OF_DAY) - 12
        val minute = calendar.get(Calendar.MINUTE)

        canvas.rotate(hour * 30f + minute / 2f, centerX, centerY)
        drawClockHand(canvas, 0.2f)
        canvas.restore()
        canvas.rotate(minute * 6f, centerX, centerY)
        drawClockHand(canvas, 0.5f)
    }

    private fun drawClockHand(canvas: Canvas, heightInPercentRadius: Float) {
        canvas.drawLine(centerX, centerY * 1.1f, centerX, centerY - radius * heightInPercentRadius, lineClockHandPaint)
        heightArrow = radius / 4
        pathClockHand = Path()
        pathClockHand.moveTo(centerX, centerY - radius * heightInPercentRadius - heightArrow)
        pathClockHand.lineTo(centerX * 1.05f, (centerY - radius * heightInPercentRadius))
        pathClockHand.lineTo(centerX * 0.95f, (centerY - radius * heightInPercentRadius))
        pathClockHand.close()
        canvas.drawPath(pathClockHand, arrowClockHandPaint)
    }
}