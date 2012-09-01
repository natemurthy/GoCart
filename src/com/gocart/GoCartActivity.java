package com.gocart;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class GoCartActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
                
        final Button button1 = (Button) findViewById(R.id.findStoreButton);
        button1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
            }
        });
        
        final Button button2 = (Button) findViewById(R.id.checkIntoStoreButton);
        button2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
            }
        });
        
        oneTimeAccountSetup();
    }
    
    public void oneTimeAccountSetup() {
    	SharedPreferences prefs = getSharedPreferences("mypreferences", MODE_PRIVATE);
    	boolean isFirstUsage = prefs.getBoolean("first_usage", true);

    	if(isFirstUsage) {
    	    // show login screen
    		Intent firstTimeInstall = new Intent(this, OneTimeSetupActivity.class);
    		startActivity(firstTimeInstall);

    	    // save preferences
    	    Editor editor = prefs.edit();
    	    editor.putBoolean("first_usage", false);
    	    editor.commit();
    	}
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
    	switch(item.getItemId()) {
    		case R.id.item1: {
    			Intent mapCall = new Intent(android.content.Intent.ACTION_VIEW, Uri.parse("google.navigation:q=95054"));
    			startActivity(mapCall);
    			break;
    		}
    		
    		case R.id.item3: {
    			Intent zx_call = new Intent("com.google.zxing.client.android.SCAN");
        		zx_call.putExtra("SCAN_MODE", "PRODUCT_MODE");
        		startActivityForResult(zx_call,0);
        		break;
    		}
    		
    		case R.id.item5: {
    			Intent setupCall = new Intent(this, OneTimeSetupActivity.class);
    			startActivity(setupCall);
    			break;
    		}
    	}
    	return true;
    }
    
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (requestCode == 0) {
            if (resultCode == RESULT_OK) {
                String contents = intent.getStringExtra("SCAN_RESULT");
                //String format = intent.getStringExtra("SCAN_RESULT_FORMAT");
                // Handle successful scan
                Intent result = new Intent(this.getApplicationContext(),ScanResultActivity.class);
                result.putExtra("contents",contents);
                startActivity(result);
                finish();
            } else {
                throw new RuntimeException("Barcode scanner didn't return successfully");
            }
        }
    }
}