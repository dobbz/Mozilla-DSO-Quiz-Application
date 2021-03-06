package com.mozilla.hackathon.kiboko.activities;

import android.support.v7.app.AppCompatActivity;

import com.mozilla.hackathon.kiboko.App;
import com.mozilla.hackathon.kiboko.events.ApplicationStateChanged;
import com.mozilla.hackathon.kiboko.services.DataBootstrapService;
import com.squareup.otto.Bus;

/**
 * Created by Brian Mwadime on 06/06/2016.
 */
public class DSOActivity extends AppCompatActivity {

    Bus bus = App.getBus();

    @Override
    protected void onResume()
    {
        DataBootstrapService.startDataBootstrapIfNecessary(this);
        bus.register(this);

        App.getBus().post(new ApplicationStateChanged(true));
        super.onResume();
        System.out.println("Resume");
    }

    @Override
    protected void onPause()
    {
        App.getBus().post(new ApplicationStateChanged(false));
        bus.unregister(this);
        super.onPause();
        System.out.println("Pause");
    }
}
