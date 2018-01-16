package vone.meihuayishu.com.UI.Frg;

import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import vone.meihuayishu.com.BaseFragment;
import vone.meihuayishu.com.R;

public class NumChooseFragment extends BaseFragment {


    private TextInputEditText tiet_1;
    private TextInputEditText tiet_2;

    public NumChooseFragment() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_num_choose, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {

        tiet_1 = (TextInputEditText) view.findViewById(R.id.tiet_1);
        tiet_2 = (TextInputEditText) view.findViewById(R.id.tiet_2);
    }

}
