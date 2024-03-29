package by.itacademy.korobchits.dz2

import android.graphics.Bitmap
import android.graphics.BitmapShader
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Shader
import android.os.Build
import com.squareup.picasso.Transformation

class CircleTransformation : Transformation {
    override fun transform(source: Bitmap?): Bitmap? {
        if (source == null) {
            return source
        }

        var bitmap: Bitmap

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O && source.config == Bitmap.Config.HARDWARE) {
            val softwareCopy = source.copy(Bitmap.Config.ARGB_8888, true)
            if (softwareCopy == null) {
                return source
            } else {
                bitmap = softwareCopy
                source.recycle()
            }
        } else {
            bitmap = source
        }

        var size = bitmap.width
        if (size != bitmap.height) {
            var sizeX = size
            var sizeY = bitmap.height
            size = Math.min(sizeY, sizeX)
            sizeX = (sizeX - size) / 2
            sizeY = (sizeY - size) / 2

            val squareSource = Bitmap.createBitmap(bitmap, sizeX, sizeY, size, size)
            bitmap.recycle()
            bitmap = squareSource
        }

        val circleBitmap = Bitmap.createBitmap(size, size, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(circleBitmap)
        val paint = Paint()
        val shader = BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP)

        paint.shader = shader
        paint.isAntiAlias = true
        val centerAndRadius = size / 2f
        canvas.drawCircle(centerAndRadius, centerAndRadius, centerAndRadius, paint)

        bitmap.recycle()
        return circleBitmap
    }

    override fun key(): String {
        return "circleTransformation()"
    }
}