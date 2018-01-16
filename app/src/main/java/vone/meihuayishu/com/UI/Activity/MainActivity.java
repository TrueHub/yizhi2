package vone.meihuayishu.com.UI.Activity;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.util.Pair;
import android.transition.Explode;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

import vone.meihuayishu.com.BaseActivity;
import vone.meihuayishu.com.R;
import vone.meihuayishu.com.VIEW.drawales.CircleImgDrawable;

import static android.support.v4.app.ActivityOptionsCompat.makeSceneTransitionAnimation;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    private static final String TAG = "MSL MainActivity";
    private Button btn_time;
    private Button btn_yao;
    private Button btn_num;
    private Button btn_bazi;
    private FloatingActionButton float_note;
    public static final String CHOOSE_FLAG = "1c0b9fa83b220de5";
    public static final String CHOOSE_COLOR = "2bc210943e1bc88a";
    public static final String CHOOSE_BTN_COLOR = "b06c19fac9b6d824";
    public static final int FLAG_TIME = (byte) 0xaf;
    public static final int FLAG_NUM = (byte) 0xae;
    public static final int FLAG_YAO = (byte) 0xad;
    public static final int FLAG_BAZI = (byte) 0xac;
    private ImageView view;
    private int[] colors;
    private int i;
    private boolean floatingActionBack = false;
    private int windowWidth;
    private TextView tv_note_bazi;
    private TextView tv_note_gua;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WindowManager.LayoutParams localLayoutParams = getWindow().getAttributes();
        localLayoutParams.flags = (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | localLayoutParams.flags);
        setContentView(R.layout.activity_main);
        //此activity进入
        getWindow().setEnterTransition(new Explode().setDuration(500));
        //此activity退出
        getWindow().setExitTransition(new Explode().setDuration(500));
        initView();
    }

    private void initView() {
        btn_time = (Button) findViewById(R.id.btn_time);
        btn_yao = (Button) findViewById(R.id.btn_yao);
        btn_num = (Button) findViewById(R.id.btn_num);
        btn_bazi = (Button) findViewById(R.id.btn_bazi);
        float_note = (FloatingActionButton) findViewById(R.id.float_note);

        btn_time.setOnClickListener(this);
        btn_yao.setOnClickListener(this);
        btn_num.setOnClickListener(this);
        btn_bazi.setOnClickListener(this);
        float_note.setOnClickListener(this);
        view = (ImageView) findViewById(R.id.view);
        view.setOnClickListener(this);

        windowWidth = MainActivity.this.getWindowManager().getDefaultDisplay().getWidth();
        tv_note_bazi = (TextView) findViewById(R.id.tv_note_bazi);
        tv_note_bazi.setOnClickListener(this);
        tv_note_gua = (TextView) findViewById(R.id.tv_note_gua);
        tv_note_gua.setOnClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: ");
    }

    @Override
    protected void onStart() {
        super.onStart();
        int oldColor = view.getDrawingCacheBackgroundColor();
        i = new Random().nextInt(4);
        colors = new int[]{R.color.colorGreen, R.color.colorBlue, R.color.colorIndigo, R.color.colorRed};
        float_note.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(colors[i])));
        CircleImgDrawable.setCircleColorImgDrawable(MainActivity.this, view, colors[i]);
        Log.i(TAG, "onStart: ");
    }


    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, ChooseActivity.class);
        Bundle options;
        Pair<View, String> shareElement_1 = new Pair<>(v, getString(R.string.shareElement_frg));
        Pair<View, String> shareElement_2;
        View v2;
        switch (v.getId()) {
            case R.id.btn_time:
                intent.putExtra(CHOOSE_FLAG, FLAG_TIME);
                intent.putExtra(CHOOSE_COLOR, getResources().getColor(R.color.colorIndigo));
                intent.putExtra(CHOOSE_BTN_COLOR, getResources().getColor(R.color.colorRed));
                v2 = btn_bazi;
                shareElement_2 = Pair.create(v2, getString(R.string.shareElement_btn));
                options = makeSceneTransitionAnimation(this, shareElement_1, shareElement_2).toBundle();
                startActivity(intent, options);
                break;
            case R.id.btn_yao:
                intent.putExtra(CHOOSE_FLAG, FLAG_YAO);
                intent.putExtra(CHOOSE_COLOR, getResources().getColor(R.color.colorBlue));
                intent.putExtra(CHOOSE_BTN_COLOR, getResources().getColor(R.color.colorGreen));
                v2 = btn_num;
                shareElement_2 = Pair.create(v2, getString(R.string.shareElement_btn));
                options = makeSceneTransitionAnimation(this, shareElement_1, shareElement_2).toBundle();
                startActivity(intent, options);
                break;
            case R.id.btn_num:
                intent.putExtra(CHOOSE_FLAG, FLAG_NUM);
                intent.putExtra(CHOOSE_COLOR, getResources().getColor(R.color.colorGreen));
                intent.putExtra(CHOOSE_BTN_COLOR, getResources().getColor(R.color.colorBlue));
                v2 = btn_yao;
                shareElement_2 = Pair.create(v2, getString(R.string.shareElement_btn));
                options = makeSceneTransitionAnimation(this, shareElement_1, shareElement_2).toBundle();
                startActivity(intent, options);
                break;
            case R.id.btn_bazi:
                intent.putExtra(CHOOSE_FLAG, FLAG_BAZI);
                intent.putExtra(CHOOSE_COLOR, getResources().getColor(R.color.colorRed));
                intent.putExtra(CHOOSE_BTN_COLOR, getResources().getColor(R.color.colorIndigo));
                v2 = btn_time;
                shareElement_2 = Pair.create(v2, getString(R.string.shareElement_btn));
                options = makeSceneTransitionAnimation(this, shareElement_1, shareElement_2).toBundle();
                startActivity(intent, options);
                break;
            case R.id.float_note:
                AnimatorSet set = new AnimatorSet();
                int width = getResources().getDisplayMetrics().widthPixels;
                int[] location = new int[2];
                v.getLocationOnScreen(location);
                ObjectAnimator o_tranX = ObjectAnimator.ofFloat(v, "translationX", (width - v.getWidth()) / 2 - location[0]);
                set.playTogether(o_tranX);
                if (floatingActionBack) {
                    set.setDuration(100 * 2).setStartDelay(100 * 3);
                    set.start();
                } else {
                    set.setDuration(100 * 2).start();
                }
                new Thread(runnable).start();
                break;
            case R.id.tv_note_bazi:
                Log.d(TAG, "onClick: bazi note" );
                break;
            case R.id.tv_note_gua:
                Log.d(TAG, "onClick: gua note" );
                break;
        }
    }

    private Runnable runnable = new Runnable() {

        @Override
        public void run() {
            if (!floatingActionBack) {
                try {
                    Thread.sleep(100 * 2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            int w = view.getWidth();
            while (true) {
                if (floatingActionBack) {
                    w -= 5;
                } else {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            view.setVisibility(View.VISIBLE);
                        }
                    });
                    w += 5;
                }
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                final int finalW = w;
                final int finalH = w;
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(view.getWidth(), view.getHeight());
                        params.width = finalW;
                        params.height = finalH;
                        params.gravity = Gravity.CENTER;
                        view.setLayoutParams(params);
                        CircleImgDrawable.setCircleColorImgDrawable(MainActivity.this, view, colors[i]);
                    }
                });

                if (w > windowWidth || w < 0) {
                    floatingActionBack = !floatingActionBack;
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if (!floatingActionBack) view.setVisibility(View.GONE);
                        }
                    });
                    break;
                }
            }
            runOnUiThread(new Runnable() {
                @Override
                public void run() {

                    if (floatingActionBack) {
                        tv_note_bazi.setClickable(true);
                        tv_note_gua.setClickable(true);
                    } else {
                        tv_note_bazi.setClickable(false);
                        tv_note_gua.setClickable(false);
                    }
                }
            });

        }
    };
}
