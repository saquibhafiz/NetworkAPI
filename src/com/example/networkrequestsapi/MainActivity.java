package com.example.networkrequestsapi;

import java.io.InputStream;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.widget.TextView;

import com.example.networkrequestsapi.NetworkService.NetworkResponseListener;

public class MainActivity extends Activity {

	private boolean mIsBound = false;
    protected NetworkService mBoundService;
    TextView tv;

	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        tv = (TextView) findViewById(R.id.textView);
        
        doBindService();
    }
    
    private ServiceConnection mConnection = new ServiceConnection() {
        public void onServiceConnected(ComponentName className, IBinder service) {
            mBoundService = ((NetworkService.NetworkBinder)service).getService();
            
            getFromLink("http://www.google.ca");
            getFromLink("http://www.facebook.com");
            getFromLink("http://www.youtube.com");
            getFromLink("http://www.allkpop.com");
            getFromLink("http://www.ssdfsdfasdfsdfsdf.com");
            getFromLink("http://www.reddit.com");
            getFromLink("http://i.imgur.com/maVfxwi.gif");
            
        }

		public void getFromLink(final String url) {
			mBoundService.get(url, new NetworkResponseListener() {
				
				@Override
				public void onSuccess(InputStream data) {
					try {
						final String dataString = NetworkService.inputStreamToString(data);
						Log.d("MainActiivty", dataString);
						runOnUiThread(new Runnable() {
							
							@Override
							public void run() {
								tv.setText(url + " worked.\n" + dataString);
							}
						});
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				
				@Override
				public void onError(final Exception error) {
					runOnUiThread(new Runnable() {
						
						@Override
						public void run() {
							tv.setText(url + " failed.\n" + error.getMessage());
						}
					});
				}
			});
		}

        public void onServiceDisconnected(ComponentName className) {
            mBoundService = null;
        }
    };

    void doBindService() {
        bindService(new Intent(MainActivity.this, NetworkService.class), mConnection, Context.BIND_AUTO_CREATE);
        mIsBound = true;
    }

    void doUnbindService() {
        if (mIsBound) {
            unbindService(mConnection);
            mIsBound = false;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        doUnbindService();
    }
    
}
