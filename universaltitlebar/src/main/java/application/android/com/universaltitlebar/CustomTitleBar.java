package application.android.com.universaltitlebar;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.FloatRange;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import application.android.com.universaltitlebar.util.ScreenUtil;


public class CustomTitleBar extends RelativeLayout {

    private LayoutInflater mLayoutInflater;
    private Context mContext;

    private SkinImageView ivBg;//背景img
    private RelativeLayout mBack;//返回监听

    private TextView mTitleTxt;//标题文本

    private TextView mBackTv;//标题栏左边文字
    private ImageView mBackImg;//标题栏左边返回按钮img
    private ImageView mRightImg;//标题栏右边 右侧图片按钮
    private ImageView mRightImgLeft;//标题栏右边 左侧图片按钮 （如果页面右侧只有一个图片按钮 则用此控件）
    private TextView mRightTxt;//标题栏右边文本
    private RelativeLayout mRightLayout;//标题栏右边 文本监听
    private LinearLayout mRightLinearLayout; //标题栏右边 图片按钮布局
    private ImageView mRightSearchFileImg;//手动搜索按钮
    private InterfaceSkinChange mSkinChange;

    private View mDivider;//标题栏下方灰线

    private LinearLayout mCutLayout;//标题栏右侧图文布局（先图后文）
    private ImageView mCutImg;
    private TextView mCutTxt;
    private RelativeLayout mCustomTitleBarLayout;

    private float mChildAlpha = 1;

    public CustomTitleBar(Context context) {
        super(context);
        this.mContext = context;
        initialize();
    }

    public CustomTitleBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        initialize();
    }

    private void initialize() {
        mLayoutInflater = LayoutInflater.from(mContext);
        removeAllViews();
        setLayout();
        initView();

        ScreenUtil.setTitleMarginStatusHeight(mContext,mCustomTitleBarLayout);
        setIvBgHeight();
    }

    private void setIvBgHeight() {
        int height = ScreenUtil.dp2px(mContext, 44);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            height = ScreenUtil.getStatusHeight(mContext) + ScreenUtil.dp2px(mContext, 44);
        }
        RelativeLayout.LayoutParams layoutParams = new LayoutParams(LayoutParams.MATCH_PARENT, height);
        ivBg.setLayoutParams(layoutParams);
    }

    protected void setLayout() {
        RelativeLayout mLayout = (RelativeLayout) mLayoutInflater.inflate(R.layout.custom_titlebar_view, null);
        addView(mLayout);
    }

    protected void initView() {
        mBackImg = (ImageView) findViewById(R.id.custom_title_back_img);
        mBackTv = (TextView) findViewById(R.id.custom_title_back_tv);
        ivBg = (SkinImageView) findViewById(R.id.iv_bg);
        mBack = (RelativeLayout) findViewById(R.id.custom_title_back);
        mTitleTxt = (TextView) findViewById(R.id.custom_title_txt);
        mRightImg = (ImageView) findViewById(R.id.custom_title_right_img);
        mRightTxt = (TextView) findViewById(R.id.custom_title_right_txt);
        mRightLayout = (RelativeLayout) findViewById(R.id.custom_title_right_rl);
        mRightImgLeft = (ImageView) findViewById(R.id.custom_title_right_imgleft);
        mRightLinearLayout = (LinearLayout) findViewById(R.id.custom_title_right_ll);
        mRightSearchFileImg = (ImageView) findViewById(R.id.custom_title_right_search_file);
        mDivider = findViewById(R.id.custom_bar_divider);

        mCutLayout = (LinearLayout) findViewById(R.id.custom_title_cut);
        mCutImg = (ImageView) findViewById(R.id.custom_title_cut_img);
        mCutTxt = (TextView) findViewById(R.id.custom_title_cut_txt);
        mCustomTitleBarLayout = (RelativeLayout) findViewById(R.id.custom_titlebar_context);
    }

    /**
     * 设置标题栏灰线是否显示
     * 默认隐藏
     *
     * @param bool
     */
    public void setmDividerVisibility(boolean bool) {
        if (bool) {
            mDivider.setVisibility(View.VISIBLE);
        } else {
            mDivider.setVisibility(View.GONE);
        }
    }

    public void setChildAlpha(@FloatRange(from = 0.0, to = 1.0) float alpha) {
        if (mChildAlpha != alpha) {
            mChildAlpha = alpha;
            mTitleTxt.setAlpha(mChildAlpha);
            mBackImg.setAlpha(mChildAlpha);
            mBackTv.setAlpha(mChildAlpha);
            mRightLinearLayout.setAlpha(mChildAlpha);
        }
    }

    /**
     * 设置灰线颜色
     *
     * @param resId
     */
    public void setmDividerBackground(int resId) {
        mDivider.setBackgroundResource(resId);
    }

    /**
     * 设置灰线颜色
     *
     * @param color
     */
    public void setmDividerBackgroundColor(int color) {
        mDivider.setBackgroundColor(color);
    }

    /**
     * 设置标题栏右侧，剪裁布局显隐
     *
     * @param bool
     */
    public void setCutLayoutVisibility(boolean bool) {
        if (bool) {
            mCutLayout.setVisibility(View.VISIBLE);
        } else {
            mCutLayout.setVisibility(View.GONE);
        }
    }

    /**
     * 设置剪裁图标src
     *
     * @param resId
     */
    public void setCutImgSrc(int resId) {
        mCutImg.setImageResource(resId);
        if (mSkinChange != null) {
            mSkinChange.loadIcon(mCutImg, resId);
        }
    }

    /**
     * 设置剪裁 文本 颜色
     *
     * @param color
     */
    public void setmCutTxtColor(int color) {
        mCutTxt.setTextColor(color);
    }

    /**
     * 设置剪裁 文本 内容
     *
     * @param str
     */
    public void setmCutTxt(CharSequence str) {
        if (!TextUtils.isEmpty(str)) {
            mCutTxt.setText(str);
        }
    }

    /**
     * 设置剪裁按钮监听
     *
     * @param onClick
     */
    public void setCutLayoutListener(View.OnClickListener onClick) {
        mCutLayout.setOnClickListener(onClick);
    }

    /**
     * 设置标题栏右侧  手动扫描按钮显隐
     *
     * @param bool
     */
    public void setmRightSearchFileImgVisibility(boolean bool) {
        if (bool) {
            mRightSearchFileImg.setVisibility(View.VISIBLE);
            if (mRightLayout.getVisibility() != View.VISIBLE) {
                mRightLayout.setVisibility(View.VISIBLE);
            }
        } else {
            mRightSearchFileImg.setVisibility(View.GONE);
        }
    }

    /**
     * 设置手动扫描按钮 src
     *
     * @param resId
     */
    public void setmRightSearchFileImgSrc(int resId) {
        mRightSearchFileImg.setImageResource(resId);
        if (mSkinChange != null) {
            mSkinChange.loadIcon(mRightSearchFileImg, resId);
        }
    }

    /***
     * 设置返回键 监听
     * R.id.custom_title_back
     * @param onClickListener
     */
    public void setBackActionOnClickListener(View.OnClickListener onClickListener) {
        mBack.setOnClickListener(onClickListener);
    }

    /***
     * 设置返回按钮  图片
     * @param resId
     */
    public void setBackImgSrc(int resId) {
        setBackImageVisibility(true);
        mBackImg.setImageResource(resId);
        if (mSkinChange != null) {
            mSkinChange.loadIcon(mBackImg, resId);
        }
    }

    public void setBackImageResource(int resId) {
        setBackImageVisibility(true);
        mBackImg.setImageResource(resId);
    }

    /**
     * 设置返回按钮 背景
     *
     * @param resId
     */
    public void setBackImgBackground(int resId) {
        setBackImageVisibility(true);
        mBackImg.setBackgroundResource(resId);

    }

    /**
     * 设置返回按钮drawable
     *
     * @param drawable
     */
    public void setBackImgDrawable(Drawable drawable) {
        setBackImageVisibility(true);
        mBackImg.setImageDrawable(drawable);
    }

    public void setBackImgVistibility(boolean bool) {
        mBackTv.setVisibility(View.GONE);
        if (!bool) {
            mBack.setVisibility(View.INVISIBLE);
            mBackImg.setVisibility(View.INVISIBLE);
        } else {
            mBack.setVisibility(View.VISIBLE);
            mBackImg.setVisibility(View.VISIBLE);
        }
    }

    /**
     * 设置标题 文本
     *
     * @param str
     */
    public void setTitleTxt(CharSequence str) {
        if (!TextUtils.isEmpty(str)) {
            mTitleTxt.setText(str);
        }
    }

    /**
     * 设置左侧返回位置的文字
     */
    public void setLeftTvTxt(CharSequence str) {
        setBackImageVisibility(false);
        mBackTv.setText(str);
    }

    /**
     * 标题栏左侧文字显示则图片隐藏，文字隐藏则图片显示
     */
    private void setBackImageVisibility(boolean visibility) {
        mBackTv.setVisibility(visibility ? GONE : VISIBLE);
        mBackImg.setVisibility(visibility ? VISIBLE : GONE);
    }

    /**
     * 根据ID 设置标题文本
     *
     * @param resId
     */
    public void setmTitleTxtId(int resId) {
        mTitleTxt.setText(resId);
    }

    /**
     * 设置标题文本 颜色
     *
     * @param color
     */
    public void setTitleTxtColor(int color) {
        mTitleTxt.setTextColor(color);
    }

    /**
     * 设置标题栏 背景颜色
     *
     * @param resId
     */
    public void setTitleBarBackGroudColor(int resId) {
        getChildAt(0).setBackgroundColor(resId);
    }

    /**
     * 设置标题栏 背景颜色
     *
     * @param drawable
     */
    public void setTitleBarBackGroud(Drawable drawable) {
        getChildAt(0).setBackgroundDrawable(drawable);
    }

    /**
     * 设置标题栏 背景图片
     *
     * @param drawable
     */
    public void setTitleBarBackImg(Drawable drawable) {
        ivBg.setImageDrawable(drawable);
    }

    /**
     * 设置标题栏 背景透明度
     *
     * @param alpha 透明度
     */
    public void setTitleBarBackgroundAlpha(@FloatRange(from = 0.0, to = 1.0) float alpha) {
        if (getChildAt(0).getBackground() != null) {
            getChildAt(0).getBackground().setAlpha((int) (alpha * 0xff));
        }
    }

    /**
     * 设置标题栏 右边文字 是否显示
     * 默认不显示
     *
     * @param bool
     */
    public void setRightTxtVisibility(boolean bool) {
        if (bool) {
            mRightLayout.setVisibility(View.VISIBLE);
            mRightTxt.setVisibility(View.VISIBLE);
        } else {
            mRightLayout.setVisibility(View.GONE);
            mRightTxt.setVisibility(View.GONE);
        }
    }

    /**
     * 设置右边文本 内容
     *
     * @param str
     */
    public void setRightTxt(CharSequence str) {
        if (!TextUtils.isEmpty(str)) {
            mRightTxt.setText(str);
        }
    }

    /**
     * 设置右边文本 颜色
     *
     * @param color
     */
    public void setRightTxtColor(int color) {
        mRightTxt.setTextColor(color);
    }

    /**
     * 设置右边文本 点击事件
     * R.id.custom_title_right_rl
     *
     * @param click
     */
    public void setRightTxtOnClickListener(View.OnClickListener click) {
        //mRightTxt.setOnClickListener(click);
        mRightLayout.setOnClickListener(click);
    }

    public void release() {
        mContext = null;
    }

    /**
     * 设置标题栏右边 右侧图片按钮是否显示
     * 默认不显示
     *
     * @param bool
     */
    public void setRightImgVisibility(boolean bool) {
        if (bool) {
            if (mRightLinearLayout.getVisibility() != View.VISIBLE) {
                mRightLinearLayout.setVisibility(View.VISIBLE);
            }
            mRightImg.setVisibility(View.VISIBLE);
        } else {
            mRightImg.setVisibility(View.GONE);
            if (mRightImgLeft.getVisibility() == View.GONE) {
                mRightLinearLayout.setVisibility(View.GONE);
            }
        }

    }

    /**
     * 设置标题栏右边 右侧img 图片
     *
     * @param resId
     */
    public void setRightImgSrc(int resId) {
        mRightImg.setImageResource(resId);
        if (mSkinChange != null) {
            mSkinChange.loadIcon(mRightImg, resId);
        }
    }

    /**
     * 设置标题栏右边 右侧img background
     *
     * @param resId
     */
    public void setRightImgBackground(int resId) {
        mRightImg.setBackgroundResource(resId);
    }

    /**
     * 设置标题栏右边 右侧img drawable
     *
     * @param drawable
     */
    public void setRightImgDrawable(Drawable drawable) {
        mRightImg.setImageDrawable(drawable);
    }

    /**
     * 设置标题栏右边  右侧图片按钮监听
     * R.id.custom_title_right_img
     *
     * @param click
     */
    public void setRightImgOnClickListener(View.OnClickListener click) {
        mRightImg.setOnClickListener(click);
    }

    /**
     * 设置标题栏右边  左侧图片按钮是否显示
     * 默认不显示
     *
     * @param bool
     */
    public void setRightImgLeftVisibility(boolean bool) {
        if (bool) {
            mRightLinearLayout.setVisibility(View.VISIBLE);
            mRightImgLeft.setVisibility(View.VISIBLE);
        } else {
            mRightLinearLayout.setVisibility(View.GONE);
            mRightImgLeft.setVisibility(View.GONE);
        }
    }

    /**
     * 设置标题栏右边 左侧图片按钮src
     *
     * @param resId
     */
    public void setRightImgLeftSrc(int resId) {
        mRightImgLeft.setImageResource(resId);
        if (mSkinChange != null) {
            mSkinChange.loadIcon(mRightImgLeft, resId);
        }
    }

    /**
     * 设置标题栏右边 左侧图片background
     *
     * @param resId
     */
    public void setRightImgLeftBackground(int resId) {
        mRightImgLeft.setBackgroundResource(resId);
    }

    /**
     * 设置标题栏右边 左侧图片 drawable
     *
     * @param drawzble
     */
    public void setRightImgLeftDrawzble(Drawable drawzble) {
        mRightImgLeft.setImageDrawable(drawzble);
    }

    /**
     * 设置标题栏右边 左侧图片 监听
     * R.id.custom_title_right_imgleft
     *
     * @param click
     */
    public void setRightImgLeftOnClickListener(View.OnClickListener click) {
        mRightImgLeft.setOnClickListener(click);
    }

    /**
     * 设置标题栏右边 文字按钮是否可点击
     *
     * @param bool
     */
    public void setmRightTxtIsClickable(boolean bool) {
        //mRightTxt.setClickable(bool);
        mRightLayout.setClickable(bool);
    }

    public void setSkinChange(InterfaceSkinChange mSkinChange) {
        this.mSkinChange = mSkinChange;
    }

    public interface InterfaceSkinChange {
        void loadIcon(ImageView iv, int resId);
    }

}