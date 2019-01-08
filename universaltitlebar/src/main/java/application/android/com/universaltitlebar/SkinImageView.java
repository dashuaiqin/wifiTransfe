package application.android.com.universaltitlebar;

import android.content.Context;
import android.graphics.Matrix;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;

import application.android.com.universaltitlebar.util.ScreenUtil;

public class SkinImageView extends AppCompatImageView {
    private int imgWidth;
    private int imgHeight;
    private Context context;
    private Matrix matrix;
    public static final int TYPE_START_CROP = 1;
    public static final int TYPE_END_CROP = 0;
    private int cropType = 0;
    private Drawable skinDrawable;

    public SkinImageView(Context context) {
        super(context);
        this.context = context;
        this.init();
    }

    public SkinImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        this.init();
    }

    public SkinImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        this.init();
    }

    private void init() {
        this.setScaleType(ScaleType.MATRIX);
    }
//
//    //高度=title栏高度+状态栏高度
//    @Override
//    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
//        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
//            imgHeight = ScreenUtil.dp2px(context, 44);
//        }else{
//            imgHeight = ScreenUtil.dp2px(context, 44) + ScreenUtil.getStatusHeight(context);
//        }
//        setMeasuredDimension(MeasureSpec.getSize(widthMeasureSpec), imgHeight);
//    }

    protected boolean setFrame(int l, int t, int r, int b) {
        this.imgWidth = r - l;
        this.imgHeight = b - t;
        this.matrix = null;
        this.calculationMatrix(this.skinDrawable);
        return super.setFrame(l, t, r, b);
    }

    public void setImageDrawable(@Nullable Drawable drawable) {
        this.calculationMatrix(drawable);
        super.setImageDrawable(drawable);
    }

    public void calculationMatrix(Drawable drawable) {
        if (drawable != null) {
            if (this.matrix == null) {
                this.skinDrawable = drawable;
                int dwidth = drawable.getIntrinsicWidth();
                int dheight = drawable.getIntrinsicHeight();
                if (dwidth <= 0 || dheight <= 0 || this.imgWidth <= 0) {
                    super.setImageDrawable(drawable);
                    return;
                }

                this.matrix = new Matrix();
                float scale = (float) this.imgWidth / (float) dwidth;
                float dy = (float) this.imgHeight - (float) dheight * scale;
                this.matrix.setScale(scale, scale);
                if (this.cropType != 1) {
                    float dx = 0.0F;
                    if (dy > 0.0F) {
                        scale = (float) this.imgHeight / (float) dheight;
                        dy = 0.0F;
                        dx = (float) this.imgWidth - (float) dwidth * scale;
                    }

                    this.matrix.setScale(scale, scale);
                    this.matrix.postTranslate((float) Math.round(dx), (float) Math.round(dy));
                }

                this.setImageMatrix(this.matrix);
            } else {
                this.setImageMatrix(this.matrix);
            }

        }
    }

    public void setCropType(int type) {
        this.cropType = type;
        this.matrix = null;
    }
}
