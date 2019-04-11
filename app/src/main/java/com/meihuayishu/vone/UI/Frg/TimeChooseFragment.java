package com.meihuayishu.vone.UI.Frg;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.TimePicker;

import com.meihuayishu.vone.BaseFragment;
import com.meihuayishu.vone.EVENT_BEANS.Act_2_Frg;
import com.meihuayishu.vone.GLOBAL.GuaBean;
import com.meihuayishu.vone.R;
import com.meihuayishu.vone.UI.Activity.GuaActivity;
import com.meihuayishu.vone.UTILS.NumPickerUtil;
import com.meihuayishu.vone.UTILS.RxBus;
import com.meihuayishu.vone.UTILS.bazi.TianGanDiZhi;

import io.reactivex.functions.Consumer;

public class TimeChooseFragment extends BaseFragment {

    private DatePicker date_picker;
    private TimePicker time_picker;
    private GuaBean guaBean;
    private static final String TAG = "MSL TimeChooseFragment";
    private boolean isAttach;

    public TimeChooseFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_date_picker, container, false);
        initView(view);
        //默认初始上卦下挂动爻
        guaBean = new GuaBean(1, 1, 1);
        getDate();
        return view;
    }

    private void initView(View view) {
        date_picker = view.findViewById(R.id.date_picker);
        time_picker = view.findViewById(R.id.time_picker);
        time_picker.setIs24HourView(true);
        NumPickerUtil.resizePikcer(date_picker, this.getContext());// 调整datepicker大小
        NumPickerUtil.resizePikcer(time_picker, this.getContext());// 调整timepicker大小
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        isAttach = true;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        isAttach = false;
    }

    private void getDate() {


        RxBus.getDefault().subscribe(Act_2_Frg.class, new Consumer<Act_2_Frg>() {

            @Override
            public void accept(Act_2_Frg act_2_frg) throws Exception {
                if (!isAttach) return;
                if (act_2_frg != Act_2_Frg.Calc_Time) {
                    return;
                }
                String date = String.valueOf(date_picker.getYear()) +
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

                String mDate = date.split(" ")[0], mTime = date.split(" ")[1];
                String[] mDates = mDate.split("-");
                String[] mTimes = mTime.split(":");
                int month = Integer.valueOf(mDates[1]);
                int day = Integer.valueOf(mDates[2]);
                int hour = Integer.valueOf(mTimes[0]);

                String siZhu = TianGanDiZhi.exchangeGanZhi(date);
                int diZhiIndex = TianGanDiZhi.DIZHI_STR.indexOf(siZhu.toCharArray()[1]);

                Log.e("MSL", "onActivityResult: " + siZhu + "," + siZhu.toCharArray()[1] + "," + diZhiIndex);
                final GuaBean guaBean = new GuaBean();
                guaBean.setShang((diZhiIndex + month + day) % 8);
                guaBean.setXia((diZhiIndex + month + day + hour) % 8);
                guaBean.setDong((diZhiIndex + month + day + hour) % 6);
                Log.d(TAG, "accept: ");
                Intent baziIntent = new Intent(TimeChooseFragment.this.getContext(), GuaActivity.class);
                baziIntent.putExtra("from", 1);
                Bundle bundle = new Bundle();
                bundle.putParcelable("gua", guaBean);
                baziIntent.putExtras(bundle);
                if (getActivity() != null) {
                    getActivity().startActivity(baziIntent);
                    TimeChooseFragment.super.onDestroyView();
                }
            }
        });

    }
}
