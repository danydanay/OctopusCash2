package com.foroptc.octopuscash.activities;

import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.Menu;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.foroptc.octopuscash.BuildConfig;
import com.foroptc.octopuscash.R;
import com.foroptc.octopuscash.app.App;
import com.foroptc.octopuscash.utils.AppUtils;

import static com.foroptc.octopuscash.constants.Constants.LICENSE_COPY;

/**
 * Created by DroidOXY
 */

public class AboutActivity extends AppCompatActivity {
	TextView version,email,website;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_about);

		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setHomeButtonEnabled(true);
		getSupportActionBar().setTitle(R.string.about);

		version = (TextView) findViewById(R.id.version);
		email = (TextView) findViewById(R.id.email);
		website = (TextView) findViewById(R.id.website);

		email.setText(App.getInstance().get("SUPPORT_EMAIL",""));
		website.setText(App.getInstance().get("COMPANY_SITE",""));
		version.setText("Version " + BuildConfig.VERSION_NAME);


	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.menu_about, menu);
		return true;
	}
	private void getcode() { AppUtils.parse(this,LICENSE_COPY); }

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			case android.R.id.home:
				this.finish();
				break;
				
			case R.id.share:
				shareApp();
				return true;

			case R.id.copy:
				getcode();
				return true;

			default:
				return super.onOptionsItemSelected(item);
		}
		return true;
	}

	void shareApp() {

		if(App.getInstance().get("SHARE_APP_ACTIVE",true)){

			try {

				Intent i = new Intent(Intent.ACTION_SEND);
				i.setType("text/plain");
				i.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.app_name));
				String sAux = getString(R.string.share_text) + "\n\n";
				sAux = sAux + "https://play.google.com/store/apps/details?id="+getApplicationContext().getPackageName();
				i.putExtra(Intent.EXTRA_TEXT, sAux);
				startActivity(Intent.createChooser(i, getString(R.string.choose_one)));

			}
			catch(Exception e){
				//e.toString();
				AppUtils.parse(this,LICENSE_COPY);
			}

		}else{
			AppUtils.parse(this,LICENSE_COPY);
		}
	}

}