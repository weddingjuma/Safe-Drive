package com.example.isabelbolger.donttext;

public class MainActivity extends Activity {

    import android.app.Activity;
    import android.os.Bundle;
    import android.view.Menu;
    import android.widget.CompoundButton;
    import android.widget.CompoundButton.OnCheckedChangeListener;
    import android.widget.Switch;
    import android.widget.Toast;

    public class MainActivity extends Activity {

        private Switch mySwitch;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            mySwitch = (Switch) findViewById(R.id.mySwitch);

// set the switch to ON
            mySwitch.setChecked(true);
// attach a listener to check for changes in state
            mySwitch.setOnCheckedChangeListener(new OnCheckedChangeListener() {

                @Override
                public void onCheckedChanged(CompoundButton buttonView,
                                             boolean isChecked) {

                    if (isChecked) {

                        Toast.makeText(getApplicationContext(), “The switch is ON”,
                        Toast.LENGTH_SHORT).show();

                    } else {

                        Toast.makeText(getApplicationContext(),
                                “The switch is OFF”,Toast.LENGTH_SHORT).show();
                    }

                }
            });

        }

        @Override
        public boolean onCreateOptionsMenu(Menu menu) {
// Inflate the menu; this adds items to the action bar if it is present.
            getMenuInflater().inflate(R.menu.main, menu);
            return true;
        }

    }