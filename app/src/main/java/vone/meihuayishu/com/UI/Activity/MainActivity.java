package vone.meihuayishu.com.UI.Activity;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.util.Pair;
import android.transition.Explode;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import java.util.Random;

import vone.meihuayishu.com.BaseActivity;
import vone.meihuayishu.com.R;

import static android.support.v4.app.ActivityOptionsCompat.makeSceneTransitionAnimation;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    private Button btn_time;
    private Button btn_yao;
    private Button btn_num;
    private Button btn_bazi;
    private FloatingActionButton float_note;
    public static final String CHOOSE_FLAG = "ininininini";
    public static final String CHOOSE_COLOR = "cocococococ";
    public static final String CHOOSE_BTN_COLOR = "btnbtnbtnbtn";
    public static final int FLAG_TIME = (byte) 0xaf;
    public static final int FLAG_NUM = (byte) 0xae;
    public static final int FLAG_YAO = (byte) 0xad;
    public static final int FLAG_BAZI = (byte) 0xac;

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
    }

    @Override
    protected void onResume() {
        super.onResume();
        int i = new Random().nextInt(4);
        int[] colors = new int[]{R.color.colorGreen, R.color.colorBlue, R.color.colorIndigo, R.color.colorRed};
        float_note.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(colors[i])));
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
                options = makeSceneTransitionAnimation(this, shareElement_1,shareElement_2).toBundle();
                startActivity(intent, options);
                break;
            case R.id.btn_yao:
                intent.putExtra(CHOOSE_FLAG, FLAG_YAO);
                intent.putExtra(CHOOSE_COLOR, getResources().getColor(R.color.colorBlue));
                intent.putExtra(CHOOSE_BTN_COLOR, getResources().getColor(R.color.colorGreen));
                v2 = btn_num;
                shareElement_2 = Pair.create(v2, getString(R.string.shareElement_btn));
                options = makeSceneTransitionAnimation(this, shareElement_1,shareElement_2).toBundle();
                startActivity(intent, options);
                break;
            case R.id.btn_num:
                intent.putExtra(CHOOSE_FLAG, FLAG_NUM);
                intent.putExtra(CHOOSE_COLOR, getResources().getColor(R.color.colorGreen));
                intent.putExtra(CHOOSE_BTN_COLOR, getResources().getColor(R.color.colorBlue));
                v2 = btn_yao;
                shareElement_2 = Pair.create(v2, getString(R.string.shareElement_btn));
                options = makeSceneTransitionAnimation(this, shareElement_1,shareElement_2).toBundle();
                startActivity(intent, options);
                break;
            case R.id.btn_bazi:
                intent.putExtra(CHOOSE_FLAG, FLAG_BAZI);
                intent.putExtra(CHOOSE_COLOR, getResources().getColor(R.color.colorRed));
                intent.putExtra(CHOOSE_BTN_COLOR, getResources().getColor(R.color.colorIndigo));
                v2 = btn_time;
                shareElement_2 = Pair.create(v2, getString(R.string.shareElement_btn));
                options = makeSceneTransitionAnimation(this, shareElement_1,shareElement_2).toBundle();
                startActivity(intent, options);
                break;
            case R.id.float_note:


                break;
        }
    }
}
