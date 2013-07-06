package fr.abdelli.mameteo;

import android.app.Activity;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageButton;

import com.google.gson.Gson;

import fr.abdelli.modele.City;
import fr.abdelli.modele.WeatherCities;

public class MainActivity extends Activity implements LocationListener,OnClickListener{

	private LocationManager lm;
	private double latitude;
	private double longitude;
	private double altitude;
	private double accuracy;

	private WeatherCities msg;
	private String reponse;
	
	private EditText edit;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	
		//faudra trouver une autre technique pour afficher la liste des villes ici apparait au long press
		ImageButton imgButton = (ImageButton) findViewById(R.id.imageButton1);
		imgButton.setOnClickListener(this);
		registerForContextMenu(imgButton);
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		
		lm = (LocationManager) this.getSystemService(LOCATION_SERVICE);
		if (lm.isProviderEnabled(LocationManager.GPS_PROVIDER))
			lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 10000, 0,  this);
		lm.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 10000, 0,  this);
		
		findViewById(R.id.button1).setOnClickListener(this);
		edit = (EditText) findViewById(R.id.editText1);
	}

	@Override
	public void onLocationChanged(Location location) {
		// TODO Auto-generated method stub
		latitude = location.getLatitude();
		longitude = location.getLongitude();
		altitude = location.getAltitude();
		accuracy = location.getAccuracy();

		//Juste pour tester que ça fonctionne l'url ici sert à récupérer les villes les plus proches avec les températures en fct de la géoloc
				new Thread(new Runnable() {
					
					@Override
					public void run() {
						// TODO Auto-generated method stub
						Log.v("BLABLA", ""+latitude+" "+longitude);
						String data = Outils.getJSON("http://api.openweathermap.org/data/2.1/find/city?lat="+latitude+"&lon="+longitude+"&cnt=10");
						Gson gson = new Gson();
						
						//On enregistre le résultat du webservice dans la classe WeatherCities
						msg = gson.fromJson(data, WeatherCities.class);
						
					}
				}).start();
	}

	@Override
	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub
		switch (status) {
		case LocationProvider.OUT_OF_SERVICE:
			break;
		case LocationProvider.TEMPORARILY_UNAVAILABLE:
			break;
		case LocationProvider.AVAILABLE:
			break;
		}
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		lm.removeUpdates(this);

	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		// TODO Auto-generated method stub
		
		if(msg!=null){
			for (City city : msg.getList()) {
				menu.add(city.getName());
			}
		}
				
		super.onCreateContextMenu(menu, v, menuInfo);
	}
	
	@Override
	public boolean onContextItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		
		edit.setText(item.getTitle().toString());
		return super.onContextItemSelected(item);
	}
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.button1:
			Intent i = new Intent(this, TemperatureResultatActivity.class);
			startActivity(i);
			break;

		default:
			break;
		}
	}
}
