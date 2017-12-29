package vone.meihuayishu.com.UI.Frg;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import vone.meihuayishu.com.R;
import vone.meihuayishu.com.UTILS.NumPickerUtil;

public class BaziChooseFragment extends Fragment {


    private DatePicker date_picker;
    private TimePicker time_picker;
    private TextView tv_date_lunar;

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
        return view;
    }

    private void initView(View view) {
        date_picker = (DatePicker) view.findViewById(R.id.date_picker);
        time_picker = (TimePicker) view.findViewById(R.id.time_picker);
        tv_date_lunar = (TextView) view.findViewById(R.id.tv_date_lunar);
        time_picker.setIs24HourView(true);
        NumPickerUtil.resizePikcer(date_picker,this.getContext());// 调整datepicker大小
        NumPickerUtil.resizePikcer(time_picker,this.getContext());// 调整timepicker大小
    }
}