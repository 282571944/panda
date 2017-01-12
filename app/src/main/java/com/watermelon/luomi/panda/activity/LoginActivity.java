package com.watermelon.luomi.panda.activity;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.watermelon.luomi.panda.R;
import com.watermelon.luomi.panda.utils.CommonUtils;

public class LoginActivity extends BaseActivity {
    enum UIType {
        LOGIN, REGISTER, FOUND
    }

    int ui = UIType.LOGIN.ordinal();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void login(View view) {

    }

    public void register(View view) {
        setContentView(R.layout.activity_register);
        ui = UIType.REGISTER.ordinal();
        CommonUtils.setToolbar(this,R.string.tab7_name);
    }

    public void foundPassword(View view) {
        setContentView(R.layout.activity_password);
        ui = UIType.FOUND.ordinal();
        CommonUtils.setToolbar(this,R.string.tab8_name);
    }

    @Override
    public void onBackPressed() {
        if (ui == UIType.LOGIN.ordinal()) {
            super.onBackPressed();
        } else if (ui == UIType.REGISTER.ordinal()) {
            setContentView(R.layout.activity_login);
            ui = UIType.LOGIN.ordinal();
        } else if (ui == UIType.FOUND.ordinal()) {
            setContentView(R.layout.activity_login);
            ui = UIType.LOGIN.ordinal();
        } else {

        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
}
