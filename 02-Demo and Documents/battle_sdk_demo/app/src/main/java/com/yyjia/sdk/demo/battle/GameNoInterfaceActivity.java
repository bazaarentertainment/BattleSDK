package com.yyjia.sdk.demo.battle;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.yyjia.sdk.center.GMcenter;
import com.yyjia.sdk.data.Information;
import com.yyjia.sdk.listener.ExitSDKListener;
import com.yyjia.sdk.listener.InitListener;
import com.yyjia.sdk.listener.LoginListener;
import com.yyjia.sdk.listener.NoticeGameListener;
import com.yyjia.sdk.util.Utils;

import java.util.Random;

/**
 * 游戏没有自己的界面
 */
public class GameNoInterfaceActivity extends Activity implements View.OnClickListener {

    private Activity mActivity;
    private GMcenter mCenter = null;
    private Button btnLogin, btnSelectGameWay, btnCreateRoom, btnJoinRoom, btnSubmitGameResult, btnExitGame;

    private int gameId = 12;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_no_interface);
        initView();

        mActivity = this;

        if (mCenter == null) {
            mCenter = GMcenter.getInstance(mActivity);
            mCenter.setGameId(71);
            // 此方法选接
            mCenter.setQrCodeExtraInfo("Extra Info");
            // 登录登出监听器
            mCenter.setLoginListener(new LoginListener() {
                // 登录监听方法
                @Override
                public void loginSuccessed(String code) {
                    if (code == Information.LOGIN_SUSECCEDS) {
                        // SDK登录成功 游戏(服务端)请求 登录验证
                        ToastUtil.showShortToast(mActivity, "LOGIN SUCCESS");
                    } else {
                        ToastUtil.showShortToast(mActivity, "LOGIN FAIL");
                    }
                }

                // 登出监听方法
                public void logoutSuccessed(String code) {
                    if (code == Information.LOGOUT_SUSECCED) {
                        // 账号 退出 游戏需要重启到 登录界面
                        ToastUtil.showShortToast(mActivity, "EXIT SUCCESS");
                    } else {
                        ToastUtil.showShortToast(mActivity, "EXIT FAIL");
                    }
                }

                @Override
                public void logcancelSuccessed(String code) {
                    if (code == Information.LOGCANCEL_SUSECCED) {
                        ToastUtil.showShortToast(mActivity, "CANCEL LOGIN");
                    }
                }
            });

            mCenter.setInitListener(new InitListener() {

                @Override
                public void initSuccessed(String code) {
                    // 监听SDK初始化 如果初始化成功 可以选择适合时间登陆
                    if (code == Information.INITSUCCESSEDS) {
                        mCenter.checkLogin();
                    } else {
                        ToastUtil.showShortToast(mActivity, "INIT FAIL");
                    }
                }
            });

            mCenter.setExitSDKListener(new ExitSDKListener() {
                @Override
                public void onSuccess() {
                    mCenter = null;
                    System.exit(0);
                }

                @Override
                public void onFailure() {
                    ToastUtil.showShortToast(mActivity, "Exit Failure");
                }
            });

            mCenter.setNoticeGameListener(new NoticeGameListener() {

                // 通知游戏开始
                @Override
                public void startGame(int gameId) {
                    ToastUtil.showShortToast(mActivity, "Start Game");
                }

                // 通知游戏结束
                @Override
                public void gameOver(int gameId) {
                    ToastUtil.showShortToast(mActivity, "Game Over");
                }

                // 单人模式（试玩）
                @Override
                public void startSingleGame(int gameId) {

                }
            });

        }
        Utils.E("onCreate");
        mCenter.onCreate(mActivity);
    }

    private void initView() {
        btnLogin = (Button) findViewById(R.id.btn_login);
        btnSelectGameWay = (Button) findViewById(R.id.btn_select_game_way);
        btnCreateRoom = (Button) findViewById(R.id.btn_create_room);
        btnJoinRoom = (Button) findViewById(R.id.btn_join_room);
        btnSubmitGameResult = (Button) findViewById(R.id.btn_submit_game_result);
        btnExitGame = (Button) findViewById(R.id.btn_exit_game);
        btnLogin.setOnClickListener(this);
        btnSelectGameWay.setOnClickListener(this);
        btnCreateRoom.setOnClickListener(this);
        btnJoinRoom.setOnClickListener(this);
        btnSubmitGameResult.setOnClickListener(this);
        btnExitGame.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                mCenter.checkLogin();
                break;
            case R.id.btn_select_game_way:
                mCenter.selectGameWay();
                break;
            case R.id.btn_create_room:
                mCenter.showPayPriceDialog(gameId, true);
                break;
            case R.id.btn_join_room:
                mCenter.showPayPriceDialog(gameId, false);
                break;
            case R.id.btn_submit_game_result:
                Random random = new Random();
                mCenter.submitGameResult(random.nextInt(100), false);
                break;
            case R.id.btn_exit_game:
                mCenter.exitGame();
                break;
            case R.id.btn_exit_app:
                mCenter.exitSDK();
                break;
        }
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {

    }

    @Override
    protected void onPause() {
        super.onPause();
        Utils.E("onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Utils.E("onStop");
    }

    @Override
    protected void onDestroy() {
        mCenter.onDestroy();
        Utils.E("onDestroy");
        super.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Utils.E("onResume");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Utils.E("onStart");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Utils.E("onRestart");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }
}
