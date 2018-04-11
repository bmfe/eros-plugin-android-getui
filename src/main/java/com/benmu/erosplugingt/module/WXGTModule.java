package com.benmu.erosplugingt.module;

import com.alibaba.weex.plugin.annotation.WeexModule;
import com.benmu.erosplugingt.manager.GetuiManager;
import com.taobao.weex.annotation.JSMethod;
import com.taobao.weex.common.WXModule;

/**
 * Created by liuyuanxiao on 18/4/10.
 */

@WeexModule(name = "gtmodule", lazyLoad = true)
public class WXGTModule extends WXModule {
    @JSMethod
    public void initGeTui() {
        GetuiManager.pushInit(mWXSDKInstance.getContext());
    }
}
