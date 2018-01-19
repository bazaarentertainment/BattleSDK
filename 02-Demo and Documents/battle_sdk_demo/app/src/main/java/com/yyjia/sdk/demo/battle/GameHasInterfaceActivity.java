package com.yyjia.sdk.demo.battle;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.yyjia.sdk.center.GMcenter;
import com.yyjia.sdk.data.Information;
import com.yyjia.sdk.listener.CreateRoomCostListener;
import com.yyjia.sdk.listener.CreateRoomListener;
import com.yyjia.sdk.listener.ExitSDKListener;
import com.yyjia.sdk.listener.InitListener;
import com.yyjia.sdk.listener.JoinRoomCostListener;
import com.yyjia.sdk.listener.JoinRoomListener;
import com.yyjia.sdk.listener.JoinRoomUserList;
import com.yyjia.sdk.listener.LoginListener;
import com.yyjia.sdk.listener.NoticeGameListener;
import com.yyjia.sdk.listener.ScanQrCodeListener;
import com.yyjia.sdk.listener.SubmitGameResultListener;
import com.yyjia.sdk.util.Utils;

import java.util.Random;

/**
 * 游戏有自己的界面
 */
public class GameHasInterfaceActivity extends Activity implements View.OnClickListener {

    private Activity mActivity;
    private GMcenter mCenter = null;
    private Button btnLogin, btnGetCreateRoomCost, btnGetJoinRoomCost, btnCreateRoom, btnScanQrCode,
            btnStartGame, btnSubmitGameResult, btnLogout, btnExitGame;

    private int gameId = 12;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_have_interface);
        mActivity = this;
        initView();

        if (mCenter == null) {
            mCenter = GMcenter.getInstance(mActivity);
            // 是否自定义登录界面
            mCenter.setCustomLoginInterface(true);
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

            /// 创建房间监听
            mCenter.setCreateRoomListener(new CreateRoomListener() {
                @Override
                public void onSuccess(int gameId, String result) {
                    ToastUtil.showShortToast(mActivity, "Create Room Success");
                }

                @Override
                public void onFailure(int gameId, String errMsg) {
                    ToastUtil.showShortToast(mActivity, errMsg);
                }
            });

            // 获取创建房间所需支付的金额
            mCenter.setCreateRoomCostListener(new CreateRoomCostListener() {
                @Override
                public void onSuccess(int gameId, String money) {
                    Utils.E("money = " + money);
                }

                @Override
                public void onFailure(int gameId, String errMsg) {

                }
            });

            // 加入房间所需支付金额的监听
            mCenter.setJoinRoomCostListener(new JoinRoomCostListener() {
                @Override
                public void onSuccess(int gameId, String money) {

                }

                @Override
                public void onFailure(int gameId, String errMsg) {

                }
            });

            // 加入房间的监听
            mCenter.setJoinRoomListener(new JoinRoomListener() {
                @Override
                public void onSuccess(int gameId, String result) {
                    Utils.E("JoinRoomListener onSuccess = " + result);
                    ToastUtil.showShortToast(mActivity, "Join Room Success");
                }

                @Override
                public void onFailure(int gameId, String errMsg) {
                    ToastUtil.showShortToast(mActivity, errMsg);
                }
            });

            mCenter.setSubmitGameResultListener(new SubmitGameResultListener() {
                @Override
                public void showGameResult(int gameId, String result) {
                    Utils.E("showGameResult = " + result);
                }
            });

            mCenter.setJoinRoomUserListListener(new JoinRoomUserList() {
                @Override
                public void result(int gameId, String result) {
                    Utils.E("setJoinRoomUserListListener result =" + result);
                }
            });

            mCenter.setScanQrCodeListener(new ScanQrCodeListener() {
                @Override
                public void showResult(String result) {
                    Utils.E("result = " + result);
                    // 返回二维码扫描数据，需游戏自己解析数据
                    int gameId = 12;
                    String roomId = result;
                    mCenter.joinRoom(mActivity, gameId, roomId);
                }
            });

            mCenter.setExitSDKListener(new ExitSDKListener() {
                @Override
                public void onSuccess() {
                    mCenter = null;
                    finish();
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

                @Override
                public void startSingleGame(int gameId) {
                    ToastUtil.showShortToast(mActivity, "Single Mode");
                }
            });
        }
        Utils.E("onCreate");
        mCenter.onCreate(mActivity);
    }

    private void initView() {
        btnLogin = (Button) findViewById(R.id.btn_login);
        btnGetCreateRoomCost = (Button) findViewById(R.id.btn_get_create_room_cost);
        btnGetJoinRoomCost = (Button) findViewById(R.id.btn_get_join_room_cost);
        btnCreateRoom = (Button) findViewById(R.id.btn_create_room);
        btnScanQrCode = (Button) findViewById(R.id.btn_scan_qr_code);
        btnSubmitGameResult = (Button) findViewById(R.id.btn_submit_game_result);
        btnExitGame = (Button) findViewById(R.id.btn_exit_game);
        btnLogout = (Button) findViewById(R.id.btn_logout);
        btnStartGame = (Button) findViewById(R.id.btn_start_game);
        btnLogin.setOnClickListener(this);
        btnGetCreateRoomCost.setOnClickListener(this);
        btnGetJoinRoomCost.setOnClickListener(this);
        btnCreateRoom.setOnClickListener(this);
        btnScanQrCode.setOnClickListener(this);
        btnSubmitGameResult.setOnClickListener(this);
        btnExitGame.setOnClickListener(this);
        btnLogout.setOnClickListener(this);
        btnStartGame.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                mCenter.login("userName", "password");
                break;
            case R.id.btn_get_create_room_cost:
                mCenter.getCreateRoomCost(mActivity, gameId);
                break;
            case R.id.btn_get_join_room_cost:
                mCenter.getJoinRoomCost(mActivity, gameId);
                break;
            case R.id.btn_create_room:
                mCenter.createRoom(mActivity, gameId);
                break;
            case R.id.btn_scan_qr_code:
                mCenter.scanQrCode(mActivity);
                break;
            case R.id.btn_submit_game_result:
                Random random = new Random();
                mCenter.submitGameResult(random.nextInt(100), true);
                break;
            case R.id.btn_logout:
                mCenter.logout();
                break;
            case R.id.btn_exit_game:
                mCenter.exitGame();
                break;
            case R.id.btn_start_game:
                mCenter.noticeSdkStartGame();
                break;
            default:
                break;
        }
    }

    @Override
    public void finish() {
        super.finish();
        Utils.E("onFinish");
        System.exit(0);
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
