package com.example.simplequiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class QuizActivity extends AppCompatActivity {

    String [] question = {
            "Kadar air dalam sisa makanan diatur oleh",
            "Enzim yang dihasilkan oleh pankreas yaitu",
            "Air liur yang dihasilkan oleh kelenjar ludah memiliki fungsi seperti berikut, kecuali",
            "Protein di dalam tubuh akan segera dirombak dan digunakan tubuh dalam bentuk",
            "Zat makanan yang dibutuhkan dalam pembentukan tulang yaitu"
    };

    String [][] answer = {
            {"Usus halus", "Poros usus", "Usus besar", "Usus dua belas jari"},
            {"ripsin, amilase, dan lipase", "Pepsin, tripsin, dan renin", "Tripsin, renin, dan ptialin", "Tripsin, renin, erepsin"},
            {"Mencegah mulut dari kekeringan", "Membasahi makanan", "Membunuh mikroorganisme", "Memberi suasana basa dalam mulut"},
            {"Glukosa", "Pepton", "Asam amino", "Polipeptida"},
            {"Kalsium dan vitamin C", "Serat dan vitamin D", "Kalsium dan vitamin D", "Serat dan zat besi"}
    };

    String [] trueAnswer = {
            "Usus besar",
            "ripsin, amilase, dan lipase",
            "Memberi suasana basa dalam mulut",
            "Asam amino",
            "Kalsium dan vitamin D",
    };

    int enu         = 0;
    int len         = question.length;
    int point       = 0;

    TextView txtQuestionPre;
    RadioGroup radiosAnswerPre;
    Button butSubmitAnswerPre;
    Intent intent;

    RadioButton selectedAnswer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        txtQuestionPre = (TextView) findViewById(R.id.txtQuestion);
        radiosAnswerPre = (RadioGroup) findViewById(R.id.radiosAnswer);
        butSubmitAnswerPre = (Button) findViewById(R.id.butSubmitAnsPre);

        txtQuestionPre.setText(question[0]);
        for (int i = 0; i < radiosAnswerPre.getChildCount(); i++) {
            ((RadioButton) radiosAnswerPre.getChildAt(i)).setText(String.valueOf(answer[0][i]));
        }

        butSubmitAnswerPre.setOnClickListener(v -> {
            next();
        });
    }

    public void next(){
        if (enu < len-1){
            int selectedAnswerId = radiosAnswerPre.getCheckedRadioButtonId();
            if (selectedAnswerId != -1) {
                selectedAnswer = findViewById(selectedAnswerId);
                String selectedRbText = selectedAnswer.getText().toString();

                if (selectedRbText.equals(trueAnswer[enu])){
                    point++;
                }

                enu++;
                txtQuestionPre.setText(question[enu]);
                for (int i = 0; i < radiosAnswerPre.getChildCount(); i++) {
                    ((RadioButton) radiosAnswerPre.getChildAt(i)).setText(String.valueOf(answer[enu][i]));
                }

            } else {
                Toast.makeText(QuizActivity.this, "Silakan pilih jawaban", Toast.LENGTH_SHORT).show();
            }
        } else {
            intent = new Intent(QuizActivity.this, ResultActivity.class);
            intent.putExtra("point", String.valueOf(point+1));
            startActivity(intent);
        }
    }
}