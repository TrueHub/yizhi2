package vone.meihuayishu.com.UI.Frg;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Arrays;
import java.util.List;

import vone.meihuayishu.com.R;
import vone.meihuayishu.com.VIEW.weidget.loopview.LoopView;

/**
 * A simple {@link Fragment} subclass.
 */
public class YaoChooseFragment extends Fragment {

    private LoopView loop_up;
    private LoopView loop_down;
    private LoopView loop_yao;

    public YaoChooseFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_yao_choose, container, false);
        initView(view);
        initData();
        return view;
    }

    private void initView(View view) {

        loop_up = (LoopView) view.findViewById(R.id.loop_up);
        loop_down = (LoopView) view.findViewById(R.id.loop_down);
        loop_yao = (LoopView) view.findViewById(R.id.loop_yao);

    }

    private void initData(){
        List<String> baguaList = Arrays.asList(getResources().getStringArray(R.array.bagua));
        List<String> yaoList = Arrays.asList(getResources().getStringArray(R.array.dongyao));

        loop_up.setItems(baguaList);
        loop_down.setItems(baguaList);
        loop_yao.setItems(yaoList);

    }

}
