package com.example.baseballgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    int [] comNumbers = new int[3];
    int inputTextCount = 0;

    TextView[] inputTextView = new TextView[3];
    Button[] numButton = new Button[10];
    int hitCount =1;

    ImageButton backSpaceButton;
    ImageButton hitButton;
    TextView resultTextView;
    ScrollView scrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        comNumbers = getComNumbers();

        for (int i = 0; i <inputTextView.length ; i++) {
                inputTextView[i] = findViewById(R.id.input_text_view_0 +i);
        }

        for (int i = 0; i < numButton.length; i++) {
         numButton[i] = findViewById(R.id.num_button_0 +i);
        }

        backSpaceButton =findViewById(R.id.back_space_button);
        hitButton = findViewById(R.id.hit_button);
        resultTextView = findViewById(R.id.result_text_view);
        scrollView = findViewById(R.id.scroll_view);

        for(Button getNumButton : numButton){
            getNumButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(inputTextCount <3) {
                        Button button = findViewById(v.getId());
                        String getButtonNumber = button.getText().toString();
                        inputTextView[inputTextCount].setText(getButtonNumber);
                        button.setEnabled(false);
                        inputTextCount++;
                    }
                    else{
                        Toast.makeText(MainActivity.this, "체크 버튼을 눌러 실행하세요", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

        backSpaceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(inputTextCount<=0){
                    Toast.makeText(MainActivity.this, "더이상 지울 숫자가 없습니다.", Toast.LENGTH_SHORT).show();
                }
                else {
                    int buttonEnableNum = Integer.parseInt(inputTextView[inputTextCount-1].getText().toString());
                    numButton[buttonEnableNum].setEnabled(true);
                    inputTextView[inputTextCount - 1].setText("");
                    inputTextCount--;
                }
            }
        });

        hitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(inputTextCount<3){
                    Toast.makeText(MainActivity.this, "숫자 3개를 입력해주세요.", Toast.LENGTH_SHORT).show();
                }
                else{

                        int[] userNumbers = new int[3];


                        for (int i = 0; i < userNumbers.length; i++) {
                            userNumbers[i] = Integer.parseInt(inputTextView[i].getText().toString());
                        }

                        int[] countCheck = new int[2];

                    countCheck = getCountCheck(comNumbers, userNumbers);

                    String resultCount;

                    if(countCheck[0] ==3){
                        resultCount =   getResultCount(countCheck,userNumbers,hitCount)+" 아웃!";

                        Intent intent = new Intent(MainActivity.this,Congratulation_Activity.class);
                        startActivity(intent);

                        hitCount =1;
                    }
                        else{
                        resultCount = getResultCount(countCheck,userNumbers,hitCount);
                        hitCount++;
                        }
                    if(hitCount==1) {
                        resultTextView.setText(resultCount+"\n");
                    }
                    else{
                        resultTextView.append(resultCount+"\n");
                    }

                    scrollView.fullScroll(View.FOCUS_DOWN);
                    setClear(); //초기상태로 복귀
                }
            }
        });

    }

    private String getResultCount(int[] countCheck, int[] userNumbers,int hitCount) {
        String resultCount = hitCount+ "회차  ["+userNumbers[0]+" "+userNumbers[1]+ " "+userNumbers[2] +
                "] S : "+countCheck[0]+ " B : " +countCheck[1];
        return resultCount;
    }

    private void setClear() {
        inputTextCount = 0;
        for (int i = 0; i <inputTextView.length ; i++) {
            inputTextView[i].setText("");
        }
        for(Button button : numButton){
            button.setEnabled(true);
        }
    }

    private int[] getCountCheck(int[] comNumbers, int[] userNumbers) {

        int[] setCount = new int[2];
        for (int i = 0; i <comNumbers.length ; i++) {
            for (int j = 0; j <userNumbers.length ; j++) {
                if(comNumbers[i]== userNumbers[j] && i==j)
                {
                    setCount[0]++;      //strike
                }
                else if(comNumbers[i]==userNumbers[j] && i!=j)
                {
                   setCount[1]++;       //ball
                }
            }
        }
        return setCount;
    }

    public int[] getComNumbers(){
        int[]setCumNumber = new int[3];

        for (int i = 0; i <setCumNumber.length ; i++) {
            //랜덤숫자 배정
            setCumNumber[i] = new Random().nextInt(10);
            for(int j=0; j<i; j++) {
                if(setCumNumber[i] == setCumNumber[j]){
                    i--;
                    break;
                }
            }
        }
        Log.e("CumNumber","ComNumber"+setCumNumber[0]+"/"+setCumNumber[1]+"/"+setCumNumber[2]);
        return setCumNumber;
    }


}
