package com.example.hoangtu.ailatrieuphu;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;


public class MainGameActivity extends Activity implements View.OnClickListener {

    private static final int ANSWER_A = 1;
    private static final int ANSWER_B = 2;
    private static final int ANSWER_C = 3;
    private static final int ANSWER_D = 4;

    private static final int CHANGE_QUESTION = 100;
    private static final int TIME_PLAY_EACH_QUESTION = 34;
    private static final int TIME_REMAIN_EACH_QUESTION = 101;
    private static final int WAIT_RIGHT_ANSWER = 102;
    private static final int CORRECT_ANSWER = 103;
    private static final int BACK_TO_NORMAL = 104;
    private static final int CREATE_QUESTION = 105;
    private static final int WRONG_ANSWER = 106;
    private static final int MONEY_YES = 888;
    private static final int MONEY_NO = 999;
    private static final int DUNG_CUOC_CHOI_TAI_DAY = 8888;
    public static final String KEY_NUMBER_QUESTION = "KEY_NUMBER_QUESTION";
    public static final String KEY_MONEY = "KEY_MONEY";
    private static final int REQUEST_CODE_PLUS = 9999;
    private static final int CHANGE_ACTIVITY = 6666;
    private DBManager database;
    private TextView mTxtQuestionNumber, mTxtQuestion, mTxtTime, mTxtAnswerA, mTxtAnswerB, mTxtAnswerC, mTxtAnswerD, mTxtA, mTxtB, mTxtC, mTxtD, mTxtMoney;
    private Button mBtnStopGame, mBtnSwitchQuestion, mBtn5050, mBtnAskAudience, mBtnCallHelper;
    private ImageView mIVAnswerA, mIVAnswerB, mIVAnswerC, mIVAnswerD;
    private int level = 0;
    private int myAnswer;
    private int kiemTra = 0;
    private boolean useHelper5050 = false;
    private boolean isRunning = true;
    private int time;
    private int trueCase;
    private String question, caseA, caseB, caseC, caseD;
    private MyDialogChangeQuestion myDialogChangeQuestion;
    private MyDialogMoneyShow myDialogMoneyShow;
    private MyDialogExitGame myDialogExitGame;
    private MyDialogWrong myDialogWrong;
    private MyDialogEndTime myDialogEndTime;
    private MyDialogCallHelper myDialogCallHelper;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            switch (msg.what) {
                case CHANGE_QUESTION:

                    if (useHelper5050) {
                        visiableAnswer();
                    }
                    mTxtQuestion.setText(question);
                    mTxtAnswerA.setText(caseA);
                    mTxtAnswerB.setText(caseB);
                    mTxtAnswerC.setText(caseC);
                    mTxtAnswerD.setText(caseD);
                    mBtnSwitchQuestion.setBackgroundResource(R.drawable.atp__activity_player_button_image_help_change_question_x);
                    mBtnSwitchQuestion.setEnabled(false);
                    break;
                case TIME_REMAIN_EACH_QUESTION:
                    mTxtTime.setText(msg.arg1 + "");
                    if (msg.arg1 == 0) {
                        Handler handlerShow = new Handler();
                        handlerShow.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                myDialogEndTime.show();
                            }
                        }, 4000);
                        postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                String numberQuestion = level + "";
                                Intent intent = new Intent();
                                intent.setClass(MainGameActivity.this, FinishActivity.class);
                                intent.putExtra(KEY_NUMBER_QUESTION, numberQuestion);
                               // intent.putExtra(KEY_MONEY, mTxtMoney.getText().toString());

                                startActivityForResult(intent, REQUEST_CODE_PLUS);
                            }
                        }, 7000);
                    }
                    break;
                case WAIT_RIGHT_ANSWER:
                    changeColorWait();
                    break;
                case CORRECT_ANSWER:
                    changeColorRight();
                    if (msg.arg1 == MONEY_YES) {
                        //mTxtMoney.setText(getMoneyLevel());
                    }
                    break;
                case BACK_TO_NORMAL:
                    changeColorNormal();
                    break;
                case CREATE_QUESTION:
                    myDialogMoneyShow.setBackgroundTableRow(level + 1);
                    myDialogMoneyShow.show();
                    postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            myDialogMoneyShow.dismiss();
                        }
                    }, 4000);
                    createQuestion();
                    break;
                case WRONG_ANSWER:
                    changeColorWrong();
                    Handler handlerShow = new Handler();
                    handlerShow.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            myDialogWrong.show();
                        }
                    }, 4000);
                    postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            String numberQuestion = level + "";
                            Intent intent = new Intent();
                            intent.setClass(MainGameActivity.this, FinishActivity.class);
                            intent.putExtra(KEY_NUMBER_QUESTION, numberQuestion);
                            //intent.putExtra(KEY_MONEY, mTxtMoney.getText().toString());

                            startActivityForResult(intent, REQUEST_CODE_PLUS);
                        }
                    }, 7000);
                    break;
                case DUNG_CUOC_CHOI_TAI_DAY:
                    if (msg.arg1 == MyDialogExitGame.DUNG_CUOC_CHOI) {
                        String numberQuestion = level + "";
                        Intent intent = new Intent();
                        intent.setClass(MainGameActivity.this, FinishActivity.class);
                        intent.putExtra(KEY_NUMBER_QUESTION, numberQuestion);
                        //intent.putExtra(KEY_MONEY, mTxtMoney.getText().toString());

                        startActivityForResult(intent, REQUEST_CODE_PLUS);
                    }
                    break;
                default:
                    break;
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_game);
        initView();
        initDatabase();
        myDialogMoneyShow.show();
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                myDialogMoneyShow.dismiss();
            }
        }, 4000);
        createQuestion();
        initThread();
    }

    private void initThread() {
        Thread thread = new Thread(runnable2);
        thread.start();
    }

    private void createQuestion() {
        isRunning = true;

        if (useHelper5050) {
            visiableAnswer();
        }
        time = TIME_PLAY_EACH_QUESTION;
        trueCase = database.getArrQ().get(level).getTrueCase();
        mTxtQuestion.setText(database.getArrQ().get(level).getQuestion() + "");
        mTxtQuestionNumber.setText("Câu " + database.getArrQ().get(level).getLevel() + "");
        mTxtAnswerA.setText(database.getArrQ().get(level).getCaseA() + "");
        mTxtAnswerB.setText(database.getArrQ().get(level).getCaseB() + "");
        mTxtAnswerC.setText(database.getArrQ().get(level).getCaseC() + "");
        mTxtAnswerD.setText(database.getArrQ().get(level).getCaseD() + "");
        resetSetOnClickButton();
        if (useHelper5050 == true) {
            mBtn5050.setOnClickListener(null);
        }
    }

    private void initDatabase() {
        database = new DBManager(this);
        database.get15Questions();

    }

    private void initView() {
        mTxtQuestionNumber = (TextView) findViewById(R.id.txt_question_number);
        mTxtQuestion = (TextView) findViewById(R.id.txt_question);
        mTxtTime = (TextView) findViewById(R.id.txt_time);

        mTxtA = (TextView) findViewById(R.id.txv_A);
        mTxtB = (TextView) findViewById(R.id.txv_B);
        mTxtC = (TextView) findViewById(R.id.txv_C);
        mTxtD = (TextView) findViewById(R.id.txv_D);

        myDialogCallHelper = new MyDialogCallHelper(this);
        myDialogEndTime = new MyDialogEndTime(this);
        myDialogWrong = new MyDialogWrong(this);
        myDialogMoneyShow = new MyDialogMoneyShow(this);
        myDialogMoneyShow.getWindow().getAttributes().windowAnimations = R.style.DialogAnim;

        mTxtMoney = (TextView) findViewById(R.id.txt_money);
        mTxtAnswerA = (TextView) findViewById(R.id.txt_answer_a);
        mTxtAnswerB = (TextView) findViewById(R.id.txt_answer_b);
        mTxtAnswerC = (TextView) findViewById(R.id.txt_answer_c);
        mTxtAnswerD = (TextView) findViewById(R.id.txt_answer_d);
        mIVAnswerA = (ImageView) findViewById(R.id.iv_answer_a);
        mIVAnswerB = (ImageView) findViewById(R.id.iv_answer_b);
        mIVAnswerC = (ImageView) findViewById(R.id.iv_answer_c);
        mIVAnswerD = (ImageView) findViewById(R.id.iv_answer_d);
        mBtnStopGame = (Button) findViewById(R.id.btn_stop_game);
        mBtnSwitchQuestion = (Button) findViewById(R.id.btn_switch_question);
        mBtn5050 = (Button) findViewById(R.id.btn_5050);
        mBtnAskAudience = (Button) findViewById(R.id.btn_ask_audience);
        mBtnCallHelper = (Button) findViewById(R.id.btn_call_helper);

        resetSetOnClickButton();
    }


    @Override
    protected void onDestroy() {
        database.closeDB();
        super.onDestroy();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_answer_a:
                myAnswer = ANSWER_A;
                mIVAnswerB.setOnClickListener(null);
                mIVAnswerC.setOnClickListener(null);
                mIVAnswerD.setOnClickListener(null);
                mBtnAskAudience.setOnClickListener(null);
                mBtnCallHelper.setOnClickListener(null);
                mBtnSwitchQuestion.setOnClickListener(null);
                mBtn5050.setOnClickListener(null);
                mBtnStopGame.setOnClickListener(null);
                changeIVColorToWaitRightAnswer();
                break;
            case R.id.iv_answer_b:
                myAnswer = ANSWER_B;
                mIVAnswerA.setOnClickListener(null);
                mIVAnswerC.setOnClickListener(null);
                mIVAnswerD.setOnClickListener(null);
                mBtnAskAudience.setOnClickListener(null);
                mBtnCallHelper.setOnClickListener(null);
                mBtnSwitchQuestion.setOnClickListener(null);
                mBtn5050.setOnClickListener(null);
                mBtnStopGame.setOnClickListener(null);
                changeIVColorToWaitRightAnswer();
                break;
            case R.id.iv_answer_c:
                myAnswer = ANSWER_C;
                mIVAnswerB.setOnClickListener(null);
                mIVAnswerA.setOnClickListener(null);
                mIVAnswerD.setOnClickListener(null);
                mBtnAskAudience.setOnClickListener(null);
                mBtnCallHelper.setOnClickListener(null);
                mBtnSwitchQuestion.setOnClickListener(null);
                mBtn5050.setOnClickListener(null);
                mBtnStopGame.setOnClickListener(null);
                changeIVColorToWaitRightAnswer();
                break;
            case R.id.iv_answer_d:
                myAnswer = ANSWER_D;
                mIVAnswerB.setOnClickListener(null);
                mIVAnswerC.setOnClickListener(null);
                mIVAnswerA.setOnClickListener(null);
                mBtnAskAudience.setOnClickListener(null);
                mBtnCallHelper.setOnClickListener(null);
                mBtnSwitchQuestion.setOnClickListener(null);
                mBtn5050.setOnClickListener(null);
                mBtnStopGame.setOnClickListener(null);
                changeIVColorToWaitRightAnswer();
                break;
            case R.id.btn_stop_game:
                stopGame();
                break;
            case R.id.btn_5050:
                delete2AnswersWrong();
                break;
            case R.id.btn_switch_question:
                showChangeQuestionDialog();
                break;
            case R.id.btn_call_helper:
                showDialogCallHelper();
                break;
            default:
                break;
        }
    }

    private void changeIVColorToWaitRightAnswer() {
        isRunning = false;
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                int count = 0;
                Message message = new Message();
                message.what = WAIT_RIGHT_ANSWER;
                message.setTarget(handler);
                message.sendToTarget();
                while (count <= 3) {
                    count++;
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                checkAnswer();
            }
        });
        thread.start();

    }

    private void changeColorWait() {
        AnimationDrawable animationDrawable;
        switch (myAnswer) {
            case ANSWER_A:
                mIVAnswerA.setImageResource(R.drawable.animation_list_bg_button_select);
                animationDrawable = (AnimationDrawable) mIVAnswerA.getDrawable();
                animationDrawable.start();
                break;
            case ANSWER_B:
                mIVAnswerB.setImageResource(R.drawable.animation_list_bg_button_select);
                animationDrawable = (AnimationDrawable) mIVAnswerB.getDrawable();
                animationDrawable.start();
                break;
            case ANSWER_C:
                mIVAnswerC.setImageResource(R.drawable.animation_list_bg_button_select);
                animationDrawable = (AnimationDrawable) mIVAnswerC.getDrawable();
                animationDrawable.start();
                break;
            case ANSWER_D:
                mIVAnswerD.setImageResource(R.drawable.animation_list_bg_button_select);
                animationDrawable = (AnimationDrawable) mIVAnswerD.getDrawable();
                animationDrawable.start();
                break;
            default:
                break;
        }
    }

    private void changeColorRight() {
        AnimationDrawable animationDrawable;
        switch (trueCase) {
            case ANSWER_A:
                mIVAnswerA.setImageResource(R.drawable.animation_list_bg_button_correct);
                animationDrawable = (AnimationDrawable) mIVAnswerA.getDrawable();
                animationDrawable.start();
                break;
            case ANSWER_B:
                mIVAnswerB.setImageResource(R.drawable.animation_list_bg_button_correct);
                animationDrawable = (AnimationDrawable) mIVAnswerB.getDrawable();
                animationDrawable.start();
                break;
            case ANSWER_C:
                mIVAnswerC.setImageResource(R.drawable.animation_list_bg_button_correct);
                animationDrawable = (AnimationDrawable) mIVAnswerC.getDrawable();
                animationDrawable.start();
                break;
            case ANSWER_D:
                mIVAnswerD.setImageResource(R.drawable.animation_list_bg_button_correct);
                animationDrawable = (AnimationDrawable) mIVAnswerD.getDrawable();
                animationDrawable.start();
                break;
            default:
                break;
        }
    }

    private void changeColorWrong() {
        AnimationDrawable animationDrawable;
        switch (myAnswer) {
            case ANSWER_A:
                mIVAnswerA.setImageResource(R.drawable.animation_list_bg_button_wrong);
                animationDrawable = (AnimationDrawable) mIVAnswerA.getDrawable();
                animationDrawable.start();
                break;
            case ANSWER_B:
                mIVAnswerB.setImageResource(R.drawable.animation_list_bg_button_wrong);
                animationDrawable = (AnimationDrawable) mIVAnswerB.getDrawable();
                animationDrawable.start();
                break;
            case ANSWER_C:
                mIVAnswerC.setImageResource(R.drawable.animation_list_bg_button_wrong);
                animationDrawable = (AnimationDrawable) mIVAnswerC.getDrawable();
                animationDrawable.start();
                break;
            case ANSWER_D:
                mIVAnswerD.setImageResource(R.drawable.animation_list_bg_button_wrong);
                animationDrawable = (AnimationDrawable) mIVAnswerD.getDrawable();
                animationDrawable.start();
                break;
            default:
                break;
        }
    }

    private void showDialogCallHelper() {
        myDialogCallHelper = new MyDialogCallHelper(this);
        mBtnCallHelper.setBackgroundResource(R.drawable.atp__activity_player_button_image_help_call_x);
        mBtnCallHelper.setOnClickListener(null);
        String dapAn = "";
        if (trueCase == 1) {
            dapAn = "A";
        }
        if (trueCase == 2) {
            dapAn = "B";
        }
        if (trueCase == 3) {
            dapAn = "C";
        }
        if (trueCase == 4) {
            dapAn = "D";
        }
        myDialogCallHelper.setCauTraLoi("Phương án: " + dapAn);
        myDialogCallHelper.setOwnerActivity(this);
        myDialogCallHelper.show();

    }

    public void showChangeQuestionDialog() {
        myDialogChangeQuestion = new MyDialogChangeQuestion(this);
        myDialogChangeQuestion.setOwnerActivity(this);
        myDialogChangeQuestion.show();
        createThreadToListenChangeQuestion();
    }

    private void createThreadToListenChangeQuestion() {
        Thread thread = new Thread(runnable);
        thread.start();
    }

    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            //isRunning = true;
            while (isRunning) {
                if (myDialogChangeQuestion.getIsChange() == MyDialogChangeQuestion.YES) {
                    isRunning = false;
                    myDialogChangeQuestion.setIsChange(0);
                    changeQuestion();
                    //isRunning = true;
                } else {
                    if (myDialogChangeQuestion.getIsChange() == MyDialogChangeQuestion.NO) {
                        isRunning = true;
                        myDialogChangeQuestion.setIsChange(0);
                    }
                }

            }
        }
    };

    private Runnable runnable2 = new Runnable() {
        @Override
        public void run() {
            while (isRunning && time > -1) {
                time--;
                Message message = new Message();
                message.what = TIME_REMAIN_EACH_QUESTION;
                //message.what = CREATE_QUESTION;
                message.arg1 = time;
                message.setTarget(handler);
                message.sendToTarget();
                if (time == 0) {
                    isRunning = false;
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    };

    private void changeQuestion() {
        //database.openDB();
        /*if (useHelper5050) {
            visiableAnswer();
            useHelper5050 = false;
        }*/
        isRunning = true;
        //SQLiteDatabase sql;
        int numberTable = level + 1;
        String sqlChangeQuestion = "select * from Question" + numberTable + " order by random() limit 1";
        String changeQuestion = "Select * from Question" + numberTable;
        Cursor cursor = database.getmSqliteDB().rawQuery(sqlChangeQuestion, null);
        //if(cursor!=null) {
        cursor.moveToFirst();
        int quesionIndex = cursor.getColumnIndex("Question");
        int caseAIndex = cursor.getColumnIndex("CaseA");
        int caseBIndex = cursor.getColumnIndex("CaseB");
        int caseCIndex = cursor.getColumnIndex("CaseC");
        int caseDIndex = cursor.getColumnIndex("CaseD");
        int trueCaseIndex = cursor.getColumnIndex("TrueCase");

        //while (cursor.isAfterLast() == false) {

        question = cursor.getString(quesionIndex);
        caseA = cursor.getString(caseAIndex);
        caseB = cursor.getString(caseBIndex);
        caseC = cursor.getString(caseCIndex);
        caseD = cursor.getString(caseDIndex);
        trueCase = Integer.parseInt(cursor.getString(trueCaseIndex));
        //cursor.moveToNext();
        //}
        //}
        Message message = new Message();
        message.what = CHANGE_QUESTION;
        message.setTarget(handler);
        message.sendToTarget();

        time = TIME_PLAY_EACH_QUESTION;
        Message message1 = new Message();
        message1.what = TIME_REMAIN_EACH_QUESTION;
        message1.arg1 = time;
        message1.setTarget(handler);
        message1.sendToTarget();
    }


    public void delete2AnswersWrong() {
        useHelper5050 = true;
        Random random = new Random();
        mBtn5050.setBackgroundResource(R.drawable.atp__activity_player_button_image_help_5050_x);
        trueCase = database.getArrQ().get(level).getTrueCase();
        int wrongAnswerOne, wrongAnswerTwo;
        wrongAnswerOne = random.nextInt(4) + 1;
        wrongAnswerTwo = random.nextInt(4) + 1;
        while (wrongAnswerOne == trueCase) {
            wrongAnswerOne = random.nextInt(4) + 1;
        }
        while (wrongAnswerTwo == trueCase || wrongAnswerTwo == wrongAnswerOne) {
            wrongAnswerTwo = random.nextInt(4) + 1;
        }

        switch (wrongAnswerOne) {
            case ANSWER_A:
                mIVAnswerA.setVisibility(View.INVISIBLE);
                mTxtAnswerA.setVisibility(View.INVISIBLE);
                mTxtA.setVisibility(View.INVISIBLE);
                break;
            case 2:
                mIVAnswerB.setVisibility(View.INVISIBLE);
                mTxtAnswerB.setVisibility(View.INVISIBLE);
                mTxtB.setVisibility(View.INVISIBLE);
                break;
            case 3:
                mIVAnswerC.setVisibility(View.INVISIBLE);
                mTxtAnswerC.setVisibility(View.INVISIBLE);
                mTxtC.setVisibility(View.INVISIBLE);
                break;
            case 4:
                mIVAnswerD.setVisibility(View.INVISIBLE);
                mTxtAnswerD.setVisibility(View.INVISIBLE);
                mTxtD.setVisibility(View.INVISIBLE);
                break;
            default:
                break;
        }

        switch (wrongAnswerTwo) {
            case 1:
                mIVAnswerA.setVisibility(View.INVISIBLE);
                mTxtAnswerA.setVisibility(View.INVISIBLE);
                mTxtA.setVisibility(View.INVISIBLE);
                break;
            case 2:
                mIVAnswerB.setVisibility(View.INVISIBLE);
                mTxtAnswerB.setVisibility(View.INVISIBLE);
                mTxtB.setVisibility(View.INVISIBLE);
                break;
            case 3:
                mIVAnswerC.setVisibility(View.INVISIBLE);
                mTxtAnswerC.setVisibility(View.INVISIBLE);
                mTxtC.setVisibility(View.INVISIBLE);
                break;
            case 4:
                mIVAnswerD.setVisibility(View.INVISIBLE);
                mTxtAnswerD.setVisibility(View.INVISIBLE);
                mTxtD.setVisibility(View.INVISIBLE);
                break;
            default:
                break;
        }

    }

    public void stopGame() {
        myDialogExitGame = new MyDialogExitGame(this);
        myDialogExitGame.setOwnerActivity(this);
        myDialogExitGame.show();
        kiemTraStopGame();
    }

    private void kiemTraStopGame() {
        Thread threadStop = new Thread(runnableStop);
        threadStop.start();
    }

    private Runnable runnableStop = new Runnable() {
        @Override
        public void run() {
            while (isRunning) {
                if (myDialogExitGame.getIsExit() == MyDialogExitGame.DUNG_CUOC_CHOI) {
                    isRunning = false;
                    Message message = new Message();
                    message.what = DUNG_CUOC_CHOI_TAI_DAY;
                    message.arg1 = myDialogExitGame.getIsExit();
                    message.setTarget(handler);
                    message.sendToTarget();
                    myDialogExitGame.setIsExit(0);
                }
            }
        }
    };

    public void checkAnswer() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                if (myAnswer == trueCase) {
                    int count = 0;
                    kiemTra = MONEY_YES;
                    Message message = new Message();
                    message.what = CORRECT_ANSWER;
                    message.arg1 = kiemTra;
                    message.setTarget(handler);
                    message.sendToTarget();
                    while (count <= 3) {
                        count++;
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    if (level < 14) {
                        Message message2 = new Message();
                        message2.what = BACK_TO_NORMAL;
                        message2.setTarget(handler);
                        message2.sendToTarget();
                        level++;

                        Message message1 = new Message();
                        message1.what = CREATE_QUESTION;
                        message1.setTarget(handler);
                        message1.sendToTarget();
                        time = TIME_PLAY_EACH_QUESTION;
                        isRunning = true;
                        while (isRunning && time > -1) {
                            time--;
                            Message message3 = new Message();
                            message3.what = TIME_REMAIN_EACH_QUESTION;
                            message3.arg1 = time;
                            message3.setTarget(handler);
                            message3.sendToTarget();
                            if (time == 0) {
                                isRunning = false;
                            }
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                    if (level == 14) {
//                Toast.makeText(MainGameActivity.this, "You are winner! ", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    kiemTra = MONEY_NO;

                    Message message = new Message();
                    message.what = WRONG_ANSWER;
                    message.setTarget(handler);
                    message.sendToTarget();

                    Message message1 = new Message();
                    message1.what = CORRECT_ANSWER;
                    message1.arg1 = kiemTra;
                    message1.setTarget(handler);
                    message1.sendToTarget();
                }
            }
        });
        thread.start();

    }

    private void changeColorNormal() {
        switch (myAnswer) {
            case ANSWER_A:
                mIVAnswerA.setImageResource(R.drawable.atp__activity_player_layout_play_answer_background_normal);
                break;
            case ANSWER_B:
                mIVAnswerB.setImageResource(R.drawable.atp__activity_player_layout_play_answer_background_normal);
                break;
            case ANSWER_C:
                mIVAnswerC.setImageResource(R.drawable.atp__activity_player_layout_play_answer_background_normal);
                break;
            case ANSWER_D:
                mIVAnswerD.setImageResource(R.drawable.atp__activity_player_layout_play_answer_background_normal);
                break;
            default:
                break;
        }
    }

    private void visiableAnswer() {
        mIVAnswerA.setVisibility(View.VISIBLE);
        mTxtAnswerA.setVisibility(View.VISIBLE);
        mTxtA.setVisibility(View.VISIBLE);

        mIVAnswerB.setVisibility(View.VISIBLE);
        mTxtAnswerB.setVisibility(View.VISIBLE);
        mTxtB.setVisibility(View.VISIBLE);

        mIVAnswerC.setVisibility(View.VISIBLE);
        mTxtAnswerC.setVisibility(View.VISIBLE);
        mTxtC.setVisibility(View.VISIBLE);

        mIVAnswerD.setVisibility(View.VISIBLE);
        mTxtAnswerD.setVisibility(View.VISIBLE);
        mTxtD.setVisibility(View.VISIBLE);
    }

    private String getMoneyLevel() {
        String[] arrMoney = new String[]{
                "200,000", "400,000", "600,000", "1,000,000", "2,000,000", "3,000,000",
                "6,000,000", "10,000,000", "14,000,000", "22,000,000", "30,000,000",
                "40,000,000", "60,000,000", "85,000,000", "150,000,000"
        };
        return arrMoney[level];
    }

    private void resetSetOnClickButton() {
        mIVAnswerA.setOnClickListener(this);
        mIVAnswerB.setOnClickListener(this);
        mIVAnswerC.setOnClickListener(this);
        mIVAnswerD.setOnClickListener(this);
        mBtnAskAudience.setOnClickListener(this);
        mBtnCallHelper.setOnClickListener(this);
        mBtnSwitchQuestion.setOnClickListener(this);
        mBtn5050.setOnClickListener(this);
        mBtnStopGame.setOnClickListener(this);
    }
}

