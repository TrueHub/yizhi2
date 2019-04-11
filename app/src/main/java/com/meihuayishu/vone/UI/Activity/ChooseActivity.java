package com.meihuayishu.vone.UI.Activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.FragmentTransaction;
import android.transition.Explode;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.meihuayishu.vone.BaseActivity;
import com.meihuayishu.vone.EVENT_BEANS.Act_2_Frg;
import com.meihuayishu.vone.R;
import com.meihuayishu.vone.UI.Frg.BaziChooseFragment;
import com.meihuayishu.vone.UI.Frg.NumChooseFragment;
import com.meihuayishu.vone.UI.Frg.TimeChooseFragment;
import com.meihuayishu.vone.UI.Frg.YaoChooseFragment;
import com.meihuayishu.vone.UTILS.RxBus;

import static com.meihuayishu.vone.UI.Activity.MainActivity.CHOOSE_BTN_COLOR;
import static com.meihuayishu.vone.UI.Activity.MainActivity.CHOOSE_COLOR;
import static com.meihuayishu.vone.UI.Activity.MainActivity.CHOOSE_FLAG;
import static com.meihuayishu.vone.UI.Activity.MainActivity.FLAG_BAZI;
import static com.meihuayishu.vone.UI.Activity.MainActivity.FLAG_NUM;
import static com.meihuayishu.vone.UI.Activity.MainActivity.FLAG_TIME;
import static com.meihuayishu.vone.UI.Activity.MainActivity.FLAG_YAO;

public class ChooseActivity extends BaseActivity implements View.OnClickListener {

    private FrameLayout frameLayout;
    private ConstraintLayout root_view;
    private LinearLayout ll;
    private Button btn_calculate;
    private ImageView iv_back;
    private TextView tv_title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose);
        //此activity进入
        getWindow().setEnterTransition(new Explode().setDuration(500));
        //此activity退出
        getWindow().setExitTransition(new Explode().setDuration(500));
        Intent intent = getIntent();
        initView(intent);

    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        btn_calculate.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onPause() {
        super.onPause();
        btn_calculate.setVisibility(View.GONE);
    }

    private void initView(Intent intent) {
        frameLayout = (FrameLayout) findViewById(R.id.frameLayout);
        root_view = (ConstraintLayout) findViewById(R.id.root_view);
        root_view.setOnClickListener(this);
        btn_calculate = (Button) findViewById(R.id.btn_calculate);
        btn_calculate.setOnClickListener(this);
        iv_back = (ImageView) findViewById(R.id.iv_back);
        iv_back.setOnClickListener(this);
        tv_title = (TextView) findViewById(R.id.tv_title);

        int activityFlag = getIntent().getIntExtra(CHOOSE_FLAG, -1);
        int shareEleColor = getIntent().getIntExtra(CHOOSE_COLOR, Color.TRANSPARENT);
        int btnColor = getIntent().getIntExtra(CHOOSE_BTN_COLOR, Color.TRANSPARENT);
        root_view.setBackgroundColor(shareEleColor);
        btn_calculate.setBackgroundColor(btnColor);
        Window window = getWindow();
        // 有些情况下需要先清除透明flag
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(shareEleColor);
        btn_calculate.setTextColor(getResources().getColor(R.color.colorWhite));
//        float w = getWindowManager().getDefaultDisplay().getWidth() * 0.8f;
//        float h = getWindowManager().getDefaultDisplay().getHeight() *0.08f;
//        btn_calculate.setWidth((int) w);
//        btn_calculate.setHeight((int) h);
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        switch (activityFlag) {
            case FLAG_TIME:
                tv_title.setText("以时间起卦");
                btn_calculate.setText("开始排卦");
                ft.replace(R.id.frameLayout, new TimeChooseFragment());
                break;
            case FLAG_YAO:
                tv_title.setText("成卦排盘");
                btn_calculate.setText("开始排卦");
                ft.replace(R.id.frameLayout, new YaoChooseFragment());
                break;
            case FLAG_NUM:
                tv_title.setText("以数字起卦");
                ft.replace(R.id.frameLayout, new NumChooseFragment());
                btn_calculate.setText("开始排卦");
                break;
            case FLAG_BAZI:
                tv_title.setText("先天八字排盘");
                ft.replace(R.id.frameLayout, new BaziChooseFragment());
                btn_calculate.setText("八字排盘");
                break;
        }
        ft.commit();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_calculate:
                int activityFlag = getIntent().getIntExtra(CHOOSE_FLAG, -1);
                switch (activityFlag) {
                    case FLAG_TIME:
                        RxBus.getDefault().send(Act_2_Frg.Calc_Time);
                        break;
                    case FLAG_YAO:
                        RxBus.getDefault().send(Act_2_Frg.Calc_Yao);
                        break;
                    case FLAG_NUM:
                        RxBus.getDefault().send(Act_2_Frg.Calc_Num);
                        break;
                    case FLAG_BAZI:
                        RxBus.getDefault().send(Act_2_Frg.Calc_Bazi);
                        break;
                }
                frameLayout = null;
                finish();
                break;
            case R.id.iv_back:
                super.onBackPressed();
                break;
        }
    }
}
