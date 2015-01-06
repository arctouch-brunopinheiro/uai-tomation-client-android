package uaitomation.arctouch.com.uaitomation;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import com.android.volley.Response;


public class HomeControlActivity extends ActionBarActivity {

    EditText serverAddress;
    Switch airConditionerStateSwitch;
    Button airConditionerWarmButton;
    Button airConditionerColdButton;
    private ArduinoService arduinoService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_control);

        serverAddress = (EditText) findViewById(R.id.serverAddress);
        airConditionerStateSwitch = (Switch) findViewById(R.id.airConditionerStateSwitch);
        airConditionerWarmButton = (Button) findViewById(R.id.airConditionerWarmButton);
        airConditionerColdButton = (Button) findViewById(R.id.airConditionerColdButton);
    }

    @Override
    protected void onStart() {
        super.onStart();
        arduinoService = ArduinoService.instance(this, this.serverAddress.getText().toString());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home_control, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void airConditionerWarmButtonClick(View view) {
        showDialog("Air conditioner warm button clicked");
        arduinoService.setAirConditionerWarm(new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                // HANDLE CALLBACK
            }
        });
    }

    public void airConditionerFreezeButtonClick(View view) {
        showDialog("Air conditioner cold button clicked");
        arduinoService.setAirConditionerCold(new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                // HANDLE CALLBACK
            }
        });
    }

    public void airConditionerStateSwitchClick(View view) {
        showDialog("Air conditioner state switch clicked");
        arduinoService.setAirConditionerState(airConditionerStateSwitch.isChecked(), new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                // HANDLE CALLBACK
            }
        });
    }

    private void showDialog(String message) {
        new AlertDialog.Builder(this)
                .setMessage(message)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // continue with delete
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }
}
