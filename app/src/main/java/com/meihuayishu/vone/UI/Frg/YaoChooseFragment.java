package com.meihuayishu.vone.UI.Frg;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.meihuayishu.vone.EVENT_BEANS.Act_2_Frg;
import com.meihuayishu.vone.GLOBAL.GuaBean;
import com.meihuayishu.vone.R;
import com.meihuayishu.vone.UI.Activity.GuaActivity;
import com.meihuayishu.vone.UTILS.RxBus;
import com.meihuayishu.vone.VIEW.weidget.loopview.LoopView;

import java.util.Arrays;
import java.util.List;

import io.reactivex.functions.Consumer;

/**
 * A simple {@link Fragment} subclass.
 */
public class YaoChooseFragment extends Fragment {

    private LoopView loop_up;
    private LoopView loop_down;
    private LoopView loop_yao;
    private boolean isAttach;

    public YaoChooseFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_yao_choose, container, false);
        initView(view);

        initData();

        getData();

        return view;
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

    private void getData() {
        //默认初始上卦下挂动爻

        RxBus.getDefault().subscribe(Act_2_Frg.class, new Consumer<Act_2_Frg>() {

            @Override
            public void accept(Act_2_Frg act_2_frg) throws Exception {
                if (!isAttach) return;
                if (act_2_frg != Act_2_Frg.Calc_Yao) {
                    return;
                }
                GuaBean guaBean = new GuaBean(1, 1, 1);
                guaBean.setShang(loop_up.getSelectedItem() + 1);

                guaBean.setXia(loop_down.getSelectedItem() + 1);

                guaBean.setDong(loop_yao.getSelectedItem() + 1);

                Intent intent = new Intent(YaoChooseFragment.this.getActivity(), GuaActivity.class);

                Bundle bundle = new Bundle();
                bundle.putParcelable("gua", guaBean);
                intent.putExtras(bundle);
                intent.putExtra("from", 2);
                startActivity(intent);
            }
        });
    }

    private void initView(View view) {

        loop_up = (LoopView) view.findViewById(R.id.loop_up);
        loop_down = (LoopView) view.findViewById(R.id.loop_down);
        loop_yao = (LoopView) view.findViewById(R.id.loop_yao);

    }

    private void initData() {
        List<String> baguaList = Arrays.asList(getResources().getStringArray(R.array.bagua));
        List<String> yaoList = Arrays.asList(getResources().getStringArray(R.array.dongyao));

        loop_up.setItems(baguaList);
        loop_down.setItems(baguaList);
        loop_yao.setItems(yaoList);

    }

}
