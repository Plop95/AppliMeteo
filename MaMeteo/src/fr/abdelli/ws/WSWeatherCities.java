package fr.abdelli.ws;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;

import fr.abdelli.mameteo.MainActivity;
import fr.abdelli.mameteo.Outils;
import fr.abdelli.mameteo.R;
import fr.abdelli.mameteo.TemperatureResultatActivity;
import fr.abdelli.modele.WeatherCities;

public class WSWeatherCities extends AsyncTask<String, String, WeatherCities>{

	private Activity activity;
	private ProgressDialog dialog;
	
	public WSWeatherCities(Activity a){
		activity=a;
	}
	
	@Override
	protected void onPreExecute() {
		// TODO Auto-generated method stub
		dialog = new ProgressDialog(activity);
		dialog.setTitle("Chargement...");
		dialog.show();
		super.onPreExecute();
	}
	
	@Override
	protected WeatherCities doInBackground(String... params) {
		// TODO Auto-generated method stub
		
		String data = Outils.getJSON(params[0]);
		Gson gson = new Gson();
		//On enregistre le résultat du webservice dans la classe WeatherCities
		try {
			WeatherCities wc = gson.fromJson(data, WeatherCities.class);
			
			return wc;
		} catch (Exception e) {
			// TODO: handle exception
			Log.v("Exception", ""+e);
			return null;
		}
		
		
	}
	
	@Override
	protected void onPostExecute(WeatherCities result) {
		// TODO Auto-generated method stub

		super.onPostExecute(result);
		dialog.dismiss();

		if(result!=null){
			if(activity instanceof MainActivity){
				((MainActivity)activity).setMsg(result);
				((MainActivity)activity).openContextMenu(activity.findViewById(R.id.imageButton1));
			}
			if(activity instanceof TemperatureResultatActivity){
				((TemperatureResultatActivity)activity).setMsg(result);
				
				if (((TemperatureResultatActivity)activity).isCurrentTemp()) {
					((TemperatureResultatActivity)activity).afficheCurrentTemp();
				}else{
					((TemperatureResultatActivity)activity).addMoreTemp();
				}
			}
		}else{
			AlertDialog.Builder adb = new AlertDialog.Builder(activity);
			adb.setTitle("Information");
			adb.setMessage("Une erreur est survenue");
			adb.setPositiveButton("OK", new OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					activity.finish();
				}
			});
			adb.show();
		}
	}
}
