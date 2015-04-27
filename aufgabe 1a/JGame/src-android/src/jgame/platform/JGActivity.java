package jgame.platform;
import android.app.*;
import android.os.Bundle;
import android.content.res.AssetManager;

import android.hardware.*;
import android.view.*;
import android.graphics.*;
import android.graphics.drawable.Drawable;

import android.content.Intent;
import android.content.DialogInterface;
import android.net.Uri;



public class JGActivity extends Activity {

	JGEngine eng;

	class UrlInvoker implements Runnable {
		String url;
		public UrlInvoker(String url) {
			this.url = url;
		}
		public void run() {
			// XXX currently resets the application state
			Intent i = new Intent(Intent.ACTION_VIEW);
			//i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			i.setData(Uri.parse(url));
			startActivity(i);
		}
	}


	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		eng = JGEngine.current_engine;
		eng.initActivity(this);
	}

	@Override
	protected void onResume() {
		// Ideally a game should implement onResume() and onPause()
		// to take appropriate action when the activity looses focus
		super.onResume();
		eng.start();
		eng.sensormanager.registerListener(eng.canvas, eng.accelerometer,
			SensorManager.SENSOR_DELAY_FASTEST);

	}

	@Override
	protected void onPause() {
		// Ideally a game should implement onResume() and onPause()
		// to take appropriate action when the activity looses focus
		super.onPause();
		eng.stop();
		eng.sensormanager.unregisterListener(eng.canvas);
	}

	protected Dialog onCreateDialog(int id) {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setMessage("Enable sound?");
		//builder.setCancelable(false);
		builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int id) {
				if (eng!=null && eng instanceof StdGame) {
					((StdGame)eng).audioenabled=true;
				}
			}
		});
		builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int id) {
				if (eng!=null && eng instanceof StdGame) {
					((StdGame)eng).audioenabled=false;
				}
				//dialog.cancel();
			}
		});
		AlertDialog alert = builder.create();
		return alert;
	}

	static final int MAINGROUP = Menu.FIRST;

	static final int RESUME = Menu.FIRST;
	static final int QUITGAME   = Menu.FIRST+1;

	// standard menu
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		menu.add(Menu.NONE, RESUME, Menu.NONE, "Resume");
		menu.add(Menu.NONE, QUITGAME, Menu.NONE, "Quit To Menu");
		//MenuInflater inflater = getMenuInflater();
		//inflater.inflate(R.menu.menu, menu);
		return true;
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		eng.start();
		switch (item.getItemId()) {
			case RESUME:
				// nothing extra needs to be done
			break;
			case QUITGAME:
				// XXX StdGame specific
				// we should have a quit signal in the api or something
				if (eng instanceof StdGame) {
					((StdGame)eng).gameOver();
				}
				//finish();
			break;
		}
		return true;
	}
	@Override
	public boolean onMenuOpened(int featureId, Menu menu) {
		eng.stop();
		return true;
	}
	@Override
	public void onOptionsMenuClosed (Menu menu) {
		eng.start();
	}
}

