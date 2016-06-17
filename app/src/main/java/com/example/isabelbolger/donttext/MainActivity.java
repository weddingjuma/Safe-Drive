package com.example.isabelbolger.donttext;

        import android.app.Activity;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.Switch;
        import android.widget.TextView;
        import android.widget.CompoundButton;
        import android.widget.CompoundButton.OnCheckedChangeListener;

public class IBeaconBootstrap extends MainActivity implements BootstrapNotifier {
//Starts the app when detecting beacon
    private RegionBootstrap regionBootstrap;

    @Override
    public void onCreate() {

        super.onCreate();

        android.util.Log.d("IBeaconBootstrap", "App started up");

        // wake up the app when any beacon is seen (you can specify specific id
        // filers in the parameters below)

        Region region = new Region("MyRegion", null, null, null);
        regionBootstrap = new RegionBootstrap(this, region);

        // This is for Apple compatible iBeacons
        BeaconManager.getInstanceForApplication(this).getBeaconParsers().add(new BeaconParser().setBeaconLayout("m:0-3=4c000215,i:4-19,i:20-21,i:22-23,p:24-24"));
    }

    @Override
    public void didDetermineStateForRegion(int state, Region region) {

        Log.d("Boostrap didDetermineStateForRegion", "Region " + region.toString());
    }

    @Override
    public void didEnterRegion(Region region) {

        Log.d("Boostrap didEnterRegion", "Got a didEnterRegion call");

        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        this.startActivity(intent);
    }

    @Override
    public void didExitRegion(Region region) {

        Log.d("Boostrap didExitRegion", "Got a didExitRegion call");
    }
}

//when the switch is on, airplane mode on. if off, then no airplane mode

public class MainActivity extends Activity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    Switch swi = (Switch) findViewById(R.id.switch);
    swi.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
    {
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) //Line A
        {

        }
    });

    public void onClick(View view)
    {
        boolean isOn = ((Switch) view).isChecked();

        if (isOn)
        {
            //code for airplane mode on
            // read the airplane mode setting
            boolean isEnabled = Settings.System.getInt(
                    getContentResolver(),
                    Settings.System.AIRPLANE_MODE_ON, 0) == 1;

            // toggle airplane mode
            Settings.System.putInt(
                    getContentResolver(),
                    Settings.System.AIRPLANE_MODE_ON, isEnabled ? 0 : 1);

            // Post an intent to reload
            Intent intent = new Intent(Intent.ACTION_AIRPLANE_MODE_CHANGED);
            intent.putExtra("state", !isEnabled);
            sendBroadcast(intent);
        }
    }

}

