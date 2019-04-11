package com.meihuayishu.vone.VIEW.weidget.loopview;

import android.os.Handler;
import android.os.Message;

final class MessageHandler extends Handler {
    static final int WHAT_INVALIDATE_LOOP_VIEW = 1000;
    static final int WHAT_SMOOTH_SCROLL = 2000;
    static final int WHAT_ITEM_SELECTED = 3000;

    private final LoopView loopview;

    MessageHandler(LoopView loopview) {
        this.loopview = loopview;
    }

    @Override
    public final void handleMessage(Message msg) {
        switch (msg.what) {
            case WHAT_INVALIDATE_LOOP_VIEW:
                loopview.onItemSelected();
                loopview.invalidate();
                break;

            case WHAT_SMOOTH_SCROLL:
                loopview.onItemSelected();
                loopview.smoothScroll(LoopView.ACTION.FLING);
                break;

            case WHAT_ITEM_SELECTED:
                loopview.onItemSelected();
                break;
        }
    }

}