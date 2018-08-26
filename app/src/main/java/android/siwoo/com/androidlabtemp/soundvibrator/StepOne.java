package android.siwoo.com.androidlabtemp.soundvibrator;

import android.app.*;
import android.content.DialogInterface;
import android.siwoo.com.androidlabtemp.R;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

public class StepOne extends AppCompatActivity implements View.OnClickListener{
    Button alertButton;
    Button listButton;
    Button progressButton;
    Button dateButton;
    Button timeButton;
    Button customButton;
    AlertDialog customDialog;
    AlertDialog listDialog;
    AlertDialog alertDialog;

    List<String> data;
    List<Button> buttons = new ArrayList<>();
    DialogInterface.OnClickListener clickListener = (dialog, which) -> {
        if(dialog == customDialog && which == Dialog.BUTTON_POSITIVE) {
            toasting("Custom Dialog is clicked");
        }else if(dialog == listDialog) {
            toasting(data.get(which) + " is selected");
        }else if(dialog == alertDialog && which == AlertDialog.BUTTON_POSITIVE) {
            toasting("alert dialog POSITIVE Button clicked");
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step_one);

        data = Arrays.asList(getResources().getStringArray(R.array.data_array));
        alertButton = findViewById(R.id.alert_btn);
        buttons.add(alertButton);
        listButton = findViewById(R.id.list_btn);
        buttons.add(listButton);
        progressButton = findViewById(R.id.progress_btn);
        buttons.add(progressButton);
        dateButton = findViewById(R.id.date_btn);
        buttons.add(dateButton);
        timeButton = findViewById(R.id.time_btn);
        buttons.add(timeButton);
        customButton = findViewById(R.id.custom_btn);
        buttons.add(customButton);

        for(Button button: buttons) {
            button.setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View view) {
        if(view == alertButton) {
            if(alertDialog == null) {
                alertDialog = new AlertDialog.Builder(this)
                        .setTitle("Notice")
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setMessage("Do you want to exit?")
                        .setPositiveButton("OK", clickListener)
                        .setNegativeButton("NO", null)
                        .create();
            }
            alertDialog.show();
        }else if(view == listButton) {
            if(listDialog == null) {
                listDialog = new AlertDialog.Builder(this)
                        .setTitle("")
                        .setItems(data.toArray(new String[]{}), clickListener)
                        .setPositiveButton("OK", null)
                        .setNegativeButton("NO", null)
                        .create();
            }
            listDialog.show();
        }else if(view == progressButton) {
            ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setTitle("Wait");
            progressDialog.setIcon(android.R.drawable.ic_dialog_alert);
            progressDialog.setMessage("Connecting to server, please wait.");
            progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            progressDialog.setIndeterminate(true);
            progressDialog.show();
        }else if(view == dateButton) {
            Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH) + 1;
            int date = calendar.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog.OnDateSetListener listener = (_dialog, _year, _month, _dayOfMonth) -> {
                toasting(_year + ":" + _month+":" + _dayOfMonth);
            };
            DatePickerDialog dialog = new DatePickerDialog(this, listener, year, month, date);
            dialog.show();
        }else if(view == timeButton) {
            Calendar calendar = Calendar.getInstance();
            int hour = calendar.get(Calendar.HOUR_OF_DAY);
            int minutes = calendar.get(Calendar.MINUTE);

            TimePickerDialog.OnTimeSetListener listener = (_dialog, _hourOfDay, _minute) -> {
                toasting(_hourOfDay + ":" + _minute);
            };
            TimePickerDialog dialog = new TimePickerDialog(this, listener, hour, minutes, false);
            dialog.show();
        }else if (view == customButton) {
            if(customDialog == null) {
                LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
                customDialog = new AlertDialog.Builder(this)
                        .setView(inflater.inflate(R.layout.dialog_layout2, null))
                        .setPositiveButton("OK", clickListener)
                        .setNegativeButton("NO", null)
                        .create();
            }

            customDialog.show();
        }
    }

    public void toasting(String message) {
        Toast toast = Toast.makeText(this, message, Toast.LENGTH_LONG);
        toast.show();
    }

}
