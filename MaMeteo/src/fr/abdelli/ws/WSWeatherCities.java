package fr.abdelli.ws;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;

import com.google.gson.Gson;

import fr.abdelli.mameteo.MainActivity;
import fr.abdelli.mameteo.Outils;
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
		dialog.setTitle("Chargement des températures des villes aux alentours");
		dialog.show();
		super.onPreExecute();
	}
	
	@Override
	protected WeatherCities doInBackground(String... params) {
		// TODO Auto-generated method stub
		
		String data = Outils.getJSON(params[0]);
		Gson gson = new Gson();
		//On enregistre le résultat du webservice dans la classe WeatherCities
		WeatherCities wc = gson.fromJson(data, WeatherCities.class);
		return wc;
	}
	
	@Override
	protected void onPostExecute(WeatherCities result) {
		// TODO Auto-generated method stub

		super.onPostExecute(result);
		if(activity instanceof MainActivity){
			((MainActivity)activity).setMsg(result);
		}
		
		dialog.dismiss();
	}
}
