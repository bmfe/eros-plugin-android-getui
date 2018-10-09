package com.benmu.erosplugingt.module;

import com.alibaba.weex.plugin.annotation.WeexModule;
import com.eros.erosplugingt.manager.GetuiManager;
import com.eros.framework.constant.WXEventCenter;
import com.eros.framework.manager.ManagerFactory;
import com.eros.framework.manager.impl.dispatcher.DispatchEventManager;
import com.eros.framework.model.WeexEventBean;
import com.taobao.weex.annotation.JSMethod;
import com.taobao.weex.bridge.JSCallback;
import com.taobao.weex.common.WXModule;

import java.util.Map;

/**
 * Created by liuyuanxiao on 18/4/10.
 */

@WeexModule(name = "bmPush", lazyLoad = true)
public class WXGTModule extends WXModule {
    @JSMethod
    public void initPush(String parms) {
        GetuiManager.pushInit(mWXSDKInstance.getContext());
    }

    /**
     * 获取个推的Cid
     */
    @JSMethod
    public void getCid(JSCallback callback) {
        WeexEventBean weexEventBean = new WeexEventBean();
        weexEventBean.setKey(WXEventCenter.EVENT_GETCID);
        weexEventBean.setContext(mWXSDKInstance.getContext());
        weexEventBean.setJscallback(callback);
        ManagerFactory.getManagerService(DispatchEventManager.class).getBus().post(weexEventBean);
    }

}
