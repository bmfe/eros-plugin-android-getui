package com.eros.erosplugingt.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.eros.erosplugingt.model.NotificationBean;
import com.eros.framework.adapter.router.RouterTracker;
import com.eros.framework.constant.Constant;
import com.eros.framework.constant.WXEventCenter;
import com.eros.framework.manager.ManagerFactory;
import com.eros.framework.manager.impl.ParseManager;
import com.eros.framework.manager.impl.dispatcher.DispatchEventManager;
import com.eros.framework.model.BaseEventBean;
import com.taobao.weex.WXSDKInstance;

import java.io.Serializable;

/**
 * Created by Carry on 2017/11/16.
 */

public class ResultActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        if (intent == null || intent.getStringExtra("type") == null) return;
        String type = intent.getStringExtra("type");
        switch (type) {
            case Constant.Action.ACTION_NOTIFICATION:
                //点击通知
                Serializable serializable = intent.getSerializableExtra(Constant.Notification
                        .TAG_NOTIFICATION);
                if (serializable instanceof NotificationBean) {
                    NotificationBean bean = (NotificationBean) serializable;
                    //发送事件
                    Activity activity = RouterTracker.peekActivity();
                    BaseEventBean eventBean = new BaseEventBean();
                    eventBean.context = activity;
                    eventBean.type = WXEventCenter.EVENT_PUSHMANAGER;
                    eventBean.clazzName = "com.benmu.framework.event.GlobalEvent";
                    bean.trigger = true;
                    ParseManager param = ManagerFactory.getManagerService(ParseManager.class);
                    String json = param.toJsonString(bean);
                    eventBean.param = json;
                    ManagerFactory.getManagerService(DispatchEventManager.class).getBus().post
                            (eventBean);
                    finish();

                }
                break;
        }
    }
}
