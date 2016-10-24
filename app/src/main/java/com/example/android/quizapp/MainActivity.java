package com.example.android.quizapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * Submits the quiz and creates a toast message with the score
     */
    public void submitQuiz(View view) {
        String finalScore = calculateScore();
        buildToast(finalScore);
    }

    /**
     * Get current responses from question 1
     *
     * @return float score up to 2 points (1/4 point each checkbox)
     */
    private float scoreQuestion1() {
        float score = 2;
        CheckBox choice1 = (CheckBox) findViewById(R.id.question_1_choice_1);
        boolean choice1Checked = choice1.isChecked();
        if (choice1Checked) {
            score -= 0.5;
        }

        CheckBox choice2 = (CheckBox) findViewById(R.id.question_1_choice_2);
        boolean choice2Checked = choice2.isChecked();
        if (choice2Checked) {
            score -= 0.5;
        }

        CheckBox choice3 = (CheckBox) findViewById(R.id.question_1_choice_3);
        boolean choice3Checked = choice3.isChecked();
        if (!choice3Checked) {
            score -= 0.5;
        }

        CheckBox choice4 = (CheckBox) findViewById(R.id.question_1_choice_4);
        boolean choice4Checked = choice4.isChecked();
        if (!choice4Checked) {
            score -= 0.5;
        }

        return score;
    }

    /**
     * Get current responses from question 2
     *
     * @return float score up to 2 points
     */
    private float scoreQuestion2() {
        float score = 2;
        CheckBox choice1 = (CheckBox) findViewById(R.id.question_2_choice_1);
        boolean choice1Checked = choice1.isChecked();
        if (!choice1Checked) {
            score -= 0.5;
        }

        CheckBox choice2 = (CheckBox) findViewById(R.id.question_2_choice_2);
        boolean choice2Checked = choice2.isChecked();
        if (choice2Checked) {
            score -= 0.5;
        }

        CheckBox choice3 = (CheckBox) findViewById(R.id.question_2_choice_3);
        boolean choice3Checked = choice3.isChecked();
        if (choice3Checked) {
            score -= 0.5;
        }

        CheckBox choice4 = (CheckBox) findViewById(R.id.question_2_choice_4);
        boolean choice4Checked = choice4.isChecked();
        if (!choice4Checked) {
            score -= 0.5;
        }

        return score;
    }

    /**
     * Get score from radio button question
     *
     * @param answerButtonId int for button view id
     * @return either 1 if correct or zero if incorrect
     */
    private int scoreRadioButtonQuestion(int answerButtonId) {
        RadioButton correctChoice = (RadioButton) findViewById(answerButtonId);
        boolean isCorrect = correctChoice.isChecked();
        if (isCorrect) {
            return 1;
        } else {
            return 0;
        }
    }

    /**
     * Get Score from fill in the blank questions
     *
     * @param editTextId    int of R.id
     * @param correctAnswer string to check against
     * @return either a 2 if correct or zero if incorrect
     */
    private int getFillInBlankScore(int editTextId, String correctAnswer) {
        EditText userInput = (EditText) findViewById(editTextId);
        String answer = userInput.getText().toString();

        if (answer.length() > 0 && answer.trim().toLowerCase().equals(correctAnswer)) {
            return 2;
        } else {
            return 0;
        }
    }

    /**
     * Calculates the score out of 8 for all questions
     *
     * @return number string with the total score
     */
    private String calculateScore() {
        float totalScore = 0;
        totalScore = totalScore + scoreQuestion1();
        totalScore = totalScore + scoreQuestion2();
        totalScore = totalScore + scoreRadioButtonQuestion(R.id.question_3_choice_2);
        totalScore = totalScore + scoreRadioButtonQuestion(R.id.question_4_choice_1);

        // Get answer 5 from strings file
        String answerQuestion5 = String.valueOf(getText(R.string.question_5_answer));
        totalScore = totalScore + getFillInBlankScore(R.id.question_5_edit_text, answerQuestion5);

        // Get answer 6 from strings file
        String answerQuestion6 = String.valueOf(getText(R.string.question_6_answer));
        totalScore = totalScore + getFillInBlankScore(R.id.question_6_edit_text, answerQuestion6);

        // Learned about formatting digit syntax from user rodion
        // http://stackoverflow.com/questions/4553633/java-float-formatting-depends-on-locale
        // Converts to no decimal point if the decimal is a zero
        String formattedScore;
        if (totalScore == Math.floor(totalScore)) {
            formattedScore = String.format("%.0f", totalScore);
        } else {
            formattedScore = String.format("%.1f", totalScore);
        }

        return formattedScore;
    }

    /**
     * Creates result Toast message
     *
     * @param finalScore string to be output as finalScore/10
     */
    private void buildToast(String finalScore) {
        String message = getString(R.string.result_message, finalScore);
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
}
