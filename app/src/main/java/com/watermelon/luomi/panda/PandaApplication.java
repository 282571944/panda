package com.watermelon.luomi.panda;

import android.app.Application;

import com.watermelon.luomi.panda.greendao.DaoMaster;
import com.watermelon.luomi.panda.greendao.DaoSession;
import com.watermelon.luomi.panda.net.APIService;
import com.watermelon.luomi.panda.utils.DeviceTool;

/**
 * application init
 * 1.database
 * 2.api service
 * Created by luomi on 2016-09-09.
 */
public class PandaApplication extends Application {
    DaoSession daoSession;
    @Override
    public void onCreate() {
        super.onCreate();

//        daoSession = new DaoMaster(new DaoMaster.DevOpenHelper(this, "panda.db").getEncryptedWritableDb(DeviceTool.getDeviceUni(this))).newSession();
        APIService.initInstance(getResources().getString(R.string.app_url)).getInstance();
    }

    public DaoSession getDaoSession() {
        return daoSession;
    }
}
