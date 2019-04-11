package com.meihuayishu.vone.UI.Frg;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.meihuayishu.vone.BaseFragment;
import com.meihuayishu.vone.EVENT_BEANS.Act_2_Frg;
import com.meihuayishu.vone.GLOBAL.GuaBean;
import com.meihuayishu.vone.R;
import com.meihuayishu.vone.UI.Activity.GuaActivity;
import com.meihuayishu.vone.UTILS.RxBus;

import io.reactivex.functions.Consumer;

public class NumChooseFragment extends BaseFragment {


    private TextInputEditText tiet_1;
    private TextInputEditText tiet_2;
    private boolean isAttach;

    public NumChooseFragment() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        submit();

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

    private void submit() {
        RxBus.getDefault().subscribe(Act_2_Frg.class,new Consumer<Act_2_Frg>(){
            @Override
            public void accept(Act_2_Frg act2Frg) throws Exception {

                if (!isAttach) return;
                if (act2Frg != Act_2_Frg.Calc_Num) {
                    return;
                }

                int edit1String = -1, edit2String = -1;

                if ((tiet_1.getText().toString().trim().isEmpty())) {
                    Toast.makeText(NumChooseFragment.this.getActivity(), "第一个数不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                edit1String = Integer.valueOf(tiet_1.getText().toString().trim());

                if (tiet_2.getText().toString().trim().isEmpty()) {
                    Toast.makeText(NumChooseFragment.this.getActivity(), "第二个数也不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                edit2String = Integer.valueOf(tiet_2.getText().toString().trim());

                if (-1 == edit1String || -1 == edit2String) {
                    return;
                }

                GuaBean guaBean = new GuaBean();
                guaBean.setShang(edit1String % 8);
                guaBean.setXia(edit2String % 8);
                guaBean.setDong((edit1String + edit2String) % 6);

                if (guaBean.getShang() == 0 ) {
                    guaBean.setShang(8);
                }
                if (guaBean.getXia() == 0) {
                    guaBean.setXia(8);
                }
                if (0 == guaBean.getDong()) {
                    guaBean.setDong(6);
                }
                Bundle bundle = new Bundle();
                bundle.putParcelable("gua", guaBean);
                Intent intent = new Intent(NumChooseFragment.this.getActivity(), GuaActivity.class);
                intent.putExtra("from",3);
                intent.putExtras(bundle);
                NumChooseFragment.this.getActivity().startActivity(intent);
                NumChooseFragment.super.onDestroy();
            }
        });
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
