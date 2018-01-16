package vone.meihuayishu.com.UI.Activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.transition.Explode;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import vone.meihuayishu.com.BaseActivity;
import vone.meihuayishu.com.R;
import vone.meihuayishu.com.UI.Frg.BaziChooseFragment;
import vone.meihuayishu.com.UI.Frg.NumChooseFragment;
import vone.meihuayishu.com.UI.Frg.TimeChooseFragment;
import vone.meihuayishu.com.UI.Frg.YaoChooseFragment;

import static vone.meihuayishu.com.UI.Activity.MainActivity.CHOOSE_BTN_COLOR;
import static vone.meihuayishu.com.UI.Activity.MainActivity.CHOOSE_COLOR;
import static vone.meihuayishu.com.UI.Activity.MainActivity.CHOOSE_FLAG;
import static vone.meihuayishu.com.UI.Activity.MainActivity.FLAG_BAZI;
import static vone.meihuayishu.com.UI.Activity.MainActivity.FLAG_NUM;
import static vone.meihuayishu.com.UI.Activity.MainActivity.FLAG_TIME;
import static vone.meihuayishu.com.UI.Activity.MainActivity.FLAG_YAO;

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
        btn_calculate.setBackgroundColor(btnColor);
        root_view.setBackgroundColor(shareEleColor);
        btn_calculate.setTextColor(getResources().getColor(R.color.colorWhite));
//        float w = getWindowManager().getDefaultDisplay().getWidth() * 0.8f;
//        float h = getWindowManager().getDefaultDisplay().getHeight() *0.08f;
//        btn_calculate.setWidth((int) w);
//        btn_calculate.setHeight((int) h);
        switch (activityFlag) {
            case FLAG_TIME:
                tv_title.setText("以时间起卦");
                getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, new TimeChooseFragment()).commit();
                btn_calculate.setText("开始排卦");
                break;
            case FLAG_YAO:
                tv_title.setText("成卦排盘");
                btn_calculate.setText("开始排卦");
                getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, new YaoChooseFragment()).commit();
                break;
            case FLAG_NUM:
                tv_title.setText("以数字起卦");
                btn_calculate.setText("开始排卦");
                getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, new NumChooseFragment()).commit();
                break;
            case FLAG_BAZI:
                tv_title.setText("先天八字排盘");
                btn_calculate.setText("八字排盘");
                getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, new BaziChooseFragment()).commit();
                break;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_calculate:

                break;
            case R.id.iv_back:
                super.onBackPressed();
                break;
        }
    }

}
