package com.gocart;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class OneTimeSetupActivity extends Activity {
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.onetimesetupview);
        
        Context mAppContext = this.getApplicationContext();
        
        TelephonyManager tMgr =(TelephonyManager)mAppContext.getSystemService(Context.TELEPHONY_SERVICE);
        String mPhoneNumber = tMgr.getLine1Number();
        final EditText phoneTextField = (EditText) findViewById(R.id.phoneNumberTextField);
        phoneTextField.setText(mPhoneNumber);
       
        final Button submitAcctInfoBtn = (Button) findViewById(R.id.submitAccountInfoButton);
        submitAcctInfoBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });
	}
}
