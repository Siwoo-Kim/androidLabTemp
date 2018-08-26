package android.siwoo.com.androidlabtemp.soundvibrator;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.siwoo.com.androidlabtemp.R;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.*;

import java.util.Arrays;
import java.util.Calendar;

public class LabFour extends AppCompatActivity implements View.OnClickListener{
    Button alertBtn;
    Button listBtn;
    Button progressBtn;
    Button dateBtn;
    Button timeBtn;
    Button customDialogBtn;

    AlertDialog customDialog;
    AlertDialog listDialog;
    AlertDialog alertDialog;

    DialogInterface.OnClickListener dialogListener = (DialogInterface dialog, int which) -> {
        if(dialog == customDialog && which == DialogInterface.BUTTON_POSITIVE) {
            showToast("Custom Dialog 확인 Clicked");
        } else if (dialog == listDialog) {
            String[] data = getResources().getStringArray(R.array.dialog_array);
            showToast(data[which] + " 선택! ");
        } else if (dialog == alertDialog && which == DialogInterface.BUTTON_POSITIVE) {
            showToast("alert dialog ok click...");
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab_four2);

        alertBtn = findViewById(R.id.alert_btn);
        listBtn = findViewById(R.id.list_btn);
        progressBtn = findViewById(R.id.progress_btn);
        dateBtn = findViewById(R.id.date_btn);
        timeBtn = findViewById(R.id.time_btn);
        customDialogBtn = findViewById(R.id.custom_btn);

        for(Button button: Arrays.asList(alertBtn, listBtn, progressBtn, dateBtn, timeBtn, customDialogBtn)) {
            button.setOnClickListener(this);
        }
    }

    private void showToast(String message) {
        Toast toast = Toast.makeText(this, message, Toast.LENGTH_SHORT);
        toast.show();
    }


    @Override
    public void onClick(View view) {
        if(view == alertBtn) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setIcon(android.R.drawable.ic_dialog_alert);
            builder.setSingleChoiceItems(R.array.dialog_array, 0, dialogListener);

            builder.setPositiveButton("확인", null);
            builder.setNegativeButton("취소", null);
            listDialog = builder.create();
            listDialog.show();
        } else if (view == progressBtn) {
            ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setIcon(android.R.drawable.ic_dialog_alert);
            progressDialog.setTitle("Wait..");
            progressDialog.setMessage("서버 연동중입니다. 잠시만 기다리세요.");

            progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            progressDialog.setIndeterminate(true);
            progressDialog.show();
        }else if(view == dateBtn) {
            Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DATE);
            DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    showToast(year +":" + (month+1) +":"+dayOfMonth);
                }
            }, year, month, day);
            datePickerDialog.show();
        }else if(timeBtn == view) {
            Calendar calendar = Calendar.getInstance();
            int hour = calendar.get(Calendar.HOUR_OF_DAY);
            int minute = calendar.get(Calendar.MINUTE);

            TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                    showToast(hourOfDay + ":" + minute);
                }
            }, hour, minute, false);
            timePickerDialog.show();
        }else if(view == customDialogBtn) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
            View _view = inflater.inflate(R.layout.dialog_layout, null);
            builder.setView(_view);

            builder.setPositiveButton("확인", dialogListener);
            builder.setNegativeButton("취소", null);

            customDialog = builder.create();
            customDialog.show();
        }
    }
}
