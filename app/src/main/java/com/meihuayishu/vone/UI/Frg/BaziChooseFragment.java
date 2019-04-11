package com.meihuayishu.vone.UI.Frg;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;

import com.meihuayishu.vone.BaseFragment;
import com.meihuayishu.vone.EVENT_BEANS.Act_2_Frg;
import com.meihuayishu.vone.R;
import com.meihuayishu.vone.UI.Activity.BaziActivity;
import com.meihuayishu.vone.UTILS.NumPickerUtil;
import com.meihuayishu.vone.UTILS.RxBus;
import com.meihuayishu.vone.UTILS.bazi.Lvhehun;
import com.meihuayishu.vone.UTILS.bazi.PaipanClass;

import java.util.Calendar;

import io.reactivex.functions.Consumer;

public class BaziChooseFragment extends BaseFragment {

    private static final String TAG = "MSL BaziChooseFragment";
    private DatePicker date_picker;
    private TimePicker time_picker;
    private TextView tv_date_lunar;
    private String sex = "男";
    private Switch sexSwitch;
    private Context context;
    private boolean isAttach;

    public BaziChooseFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bazi_choose, container, false);
        initView(view);
        getDate();
        getSex();
        submit();
        Log.i(TAG, "onCreateView: ");
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
        isAttach = true;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        this.context = null;
        isAttach = false;
    }

    private void initView(View view) {
        date_picker = (DatePicker) view.findViewById(R.id.date_picker);
        time_picker = (TimePicker) view.findViewById(R.id.time_picker);
        tv_date_lunar = (TextView) view.findViewById(R.id.tv_date_lunar);
        time_picker.setIs24HourView(true);
        NumPickerUtil.resizePikcer(date_picker, this.getContext());// 调整datepicker大小
        NumPickerUtil.resizePikcer(time_picker, this.getContext());// 调整timepicker大小
        sexSwitch = (Switch) view.findViewById(R.id.switch1);

        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        date_picker.init(year, month, day, new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker datePicker, int i, int i1, int i2) {
                Log.w(TAG, "onDateChanged: " + getDate());
            }
        });

        time_picker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker timePicker, int i, int i1) {
                Log.w(TAG, "onDateChanged: " + getDate());
            }
        });

    }

    private String getDate() {
        String str = String.valueOf(date_picker.getYear()) +
                "-" +
                ((date_picker.getMonth() + 1) < 10 ? "0" + (date_picker.getMonth() + 1)
                        : (date_picker.getMonth() + 1)) +
                "-" +
                ((date_picker.getDayOfMonth() < 10) ? "0" + date_picker.getDayOfMonth()
                        : date_picker.getDayOfMonth()) +
                " " +
                ((time_picker.getCurrentHour() < 10) ? "0" + time_picker.getCurrentHour()
                        : time_picker.getCurrentHour()) +
                ":" + ((time_picker.getCurrentMinute() < 10) ? "0" + time_picker.getCurrentMinute()
                : time_picker.getCurrentMinute());
        showLunar(str);
        return str;
    }

    private void getSex() {
        sexSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    sex = "女";
                } else {
                    sex = "男";
                }
//                Log.e("MSL", "onCheckedChanged: " + sex);
            }
        });
    }

    private void submit() {
        RxBus.getDefault().subscribe(Act_2_Frg.class, new Consumer<Act_2_Frg>() {

            @Override
            public void accept(Act_2_Frg act_2_frg) throws Exception {
                if (!isAttach) return;
                switch (act_2_frg) {
                    case Calc_Bazi:
                        Log.d(TAG, "accept: ");
                        String date = getDate();
                        Intent baziIntent = new Intent(context, BaziActivity.class);
                        baziIntent.putExtra("date",date).putExtra("sex",sex);
                        context.startActivity(baziIntent);
                        BaziChooseFragment.super.onDestroyView();
                        break;
                }
            }
        });
    }

    private void showLunar(String date) {
        Lvhehun.sex a ;
        if (sex.equals("男")) a = Lvhehun.sex.man;
        else a = Lvhehun.sex.woman;
        PaipanClass paipan = new PaipanClass(date,a);
        StringBuilder bazi = new StringBuilder();
        for (int i = 0 , len = paipan.bazi.length; i < len; i++) {
            bazi.append(paipan.bazi[i]);
        }
        String lunar = new PaipanClass(date, Lvhehun.sex.man).getLunarDate() + bazi.substring(7) + "时";
        tv_date_lunar.setText(lunar + "[" + bazi.toString() + "]");
    }
}
