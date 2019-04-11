package com.meihuayishu.vone.MODUL;

import android.Manifest;
import android.app.Activity;
import android.widget.ScrollView;

import com.meihuayishu.vone.UTILS.RequestPermissionUtils;
import com.meihuayishu.vone.UTILS.ScreenShot;

import cn.sharesdk.onekeyshare.OnekeyShare;

/**
 * Created by Dell on 2017-3-30.
 *
 */
public class ShareThePageImp implements ShareThePageItfc {
    private String[] permissions;

    @Override
    public void sharePageToAllPlatform(final Activity activity, String str) {

        permissions = new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.READ_PHONE_STATE,
                Manifest.permission.ACCESS_COARSE_LOCATION};

        if (0 != RequestPermissionUtils.isPermissionComplete(activity, permissions).size()) {
            RequestPermissionUtils.requestPermission(activity, permissions,"分享需要以下权限");
            return;
        }

        OnekeyShare oks = new OnekeyShare();
        final String fileName = activity.getCacheDir().toString() + "screenshot"+ System.currentTimeMillis() + ".PNG";
//                Log.i("MSL", "onLongClick: " + ResultActivity.this.getCacheDir().toString());

        new Thread(new Runnable() {
            @Override
            public void run() {
                ScreenShot.shootLoacleView(activity, fileName);

            }
        }).start();

        oks.disableSSOWhenAuthorize();

        //权限检查与请求

        oks.setTitle("刚得一卦");

        oks.setText(str);

        oks.setImagePath(fileName);

        oks.setShareContentCustomizeCallback(new ShareContentCustomizeImp(activity));

        oks.show(activity);

    }

    @Override
    public void shareScrollViewToAllPlatform(Activity activity, final ScrollView scrollView, final int bgColor, String str) {

        permissions = new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.READ_PHONE_STATE,
                Manifest.permission.ACCESS_COARSE_LOCATION};

        if (0 != RequestPermissionUtils.isPermissionComplete(activity, permissions).size()) {
            RequestPermissionUtils.requestPermission(activity, permissions,"分享动作需要获取以下权限");
            return;
        }

        OnekeyShare oks = new OnekeyShare();
        final String fileName = activity.getCacheDir().toString() + "screenshot"+ System.currentTimeMillis() + ".PNG";
//                Log.i("MSL", "onLongClick: " + ResultActivity.this.getCacheDir().toString());

        new Thread(new Runnable() {
            @Override
            public void run() {
                ScreenShot.shootScrollView(scrollView,bgColor, fileName);

            }
        }).start();

        oks.disableSSOWhenAuthorize();

        //权限检查与请求

        oks.setTitle("");

        oks.setText(str);

        oks.setImagePath(fileName);

        oks.setShareContentCustomizeCallback(new ShareContentCustomizeImp(activity));

        oks.show(activity);

    }

    @Override
    public void shareScrollViewToAllPlatform(Activity activity, final ScrollView scrollView, String str) {

        permissions = new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.READ_PHONE_STATE,
                Manifest.permission.ACCESS_COARSE_LOCATION};

        if (0 != RequestPermissionUtils.isPermissionComplete(activity, permissions).size()) {
            RequestPermissionUtils.requestPermission(activity, permissions,"分享动作需要获取以下权限");
            return;
        }

        OnekeyShare oks = new OnekeyShare();
        final String fileName = activity.getCacheDir().toString() + "screenshot"+ System.currentTimeMillis() + ".PNG";
//                Log.i("MSL", "onLongClick: " + ResultActivity.this.getCacheDir().toString());

        new Thread(new Runnable() {
            @Override
            public void run() {
                ScreenShot.shootScrollView(scrollView, fileName);

            }
        }).start();

        oks.disableSSOWhenAuthorize();

        //权限检查与请求

        oks.setTitle("");

        oks.setText(str);

        oks.setImagePath(fileName);

        oks.setShareContentCustomizeCallback(new ShareContentCustomizeImp(activity));

        oks.show(activity);

    }
}
