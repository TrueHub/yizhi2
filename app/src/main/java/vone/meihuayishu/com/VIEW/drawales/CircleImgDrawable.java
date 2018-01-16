package vone.meihuayishu.com.VIEW.drawales;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.widget.ImageView;

public class CircleImgDrawable extends Drawable {

    public static boolean setCircleImgDrawable(ImageView view, int ResId) {
        Bitmap bitmap = BitmapFactory.decodeResource(view.getResources(),
                ResId);
        view.setImageDrawable(new CircleImgDrawable(bitmap));
        return true;
    }

    public static boolean setCircleImgDrawable(ImageView view, Bitmap bitmap) {
        view.setImageDrawable(new CircleImgDrawable(bitmap));
        return true;
    }

    public static boolean setCircleColorImgDrawable(Context context , ImageView view, int color) {

        Bitmap bitmap = Bitmap.createBitmap(10, 10,
                Bitmap.Config.ARGB_8888);
        bitmap.eraseColor( ContextCompat.getColor(context,color));//填充颜色
        view.setImageDrawable(new CircleImgDrawable(bitmap));
        return true;
    }

    private Paint mPaint;
    private int mWidth;
    private Bitmap mBitmap;

    public CircleImgDrawable(Bitmap bitmap) {
        mBitmap = bitmap;
        BitmapShader bitmapShader = new BitmapShader(bitmap, Shader.TileMode.CLAMP,
                Shader.TileMode.CLAMP);
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setShader(bitmapShader);
        mWidth = Math.min(mBitmap.getWidth(), mBitmap.getHeight());
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawCircle(mWidth / 2, mWidth / 2, mWidth / 2, mPaint);
    }

    @Override
    public int getIntrinsicWidth() {
        return mWidth;
    }

    @Override
    public int getIntrinsicHeight() {
        return mWidth;
    }

    @Override
    public void setAlpha(int alpha) {
        mPaint.setAlpha(alpha);
    }

    @Override
    public void setColorFilter(ColorFilter cf) {
        mPaint.setColorFilter(cf);
    }

    @Override
    public int getOpacity() {
        return PixelFormat.TRANSLUCENT;
    }

}  