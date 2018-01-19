package com.yyjia.sdk.demo.battle;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity implements View.OnClickListener {

    private Button btnGameNoInterface, btnGameHasInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        btnGameNoInterface = (Button) findViewById(R.id.btn_game_no_interface);
        btnGameHasInterface = (Button) findViewById(R.id.btn_game_has_interface);

        btnGameNoInterface.setOnClickListener(this);
        btnGameHasInterface.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_game_no_interface:
                startActivity(new Intent(this, GameNoInterfaceActivity.class));
                break;
            case R.id.btn_game_has_interface:
                startActivity(new Intent(this, GameHasInterfaceActivity.class));
                break;
            default:
                break;
        }
    }
}
