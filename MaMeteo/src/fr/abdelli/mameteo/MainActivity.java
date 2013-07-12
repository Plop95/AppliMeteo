package fr.abdelli.mameteo;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageButton;
import fr.abdelli.modele.City;
import fr.abdelli.modele.WeatherCities;
import fr.abdelli.ws.WSWeatherCities;

public class MainActivity extends Activity implements LocationListener,OnClickListener{

	private LocationManager lm;
	private double latitude;
	private double longitude;
	private double altitude;
	private double accuracy;

	private WeatherCities msg;
	private String reponse;
	
	private EditText edit;
	private ImageButton imgButton;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		
		lm = (LocationManager) this.getSystemService(LOCATION_SERVICE);
		if (lm.isProviderEnabled(LocationManager.GPS_PROVIDER))
			lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000*60*5, 0,  this);
		lm.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1000*60*5, 0,  this);
		
		findViewById(R.id.button1).setOnClickListener(this);
		edit = (EditText) findViewById(R.id.editText1);
		
		imgButton = (ImageButton) findViewById(R.id.imageButton1);
		imgButton.setOnClickListener(this);
		registerForContextMenu(imgButton);
	}

	@Override
	public void onLocationChanged(Location location) {
		// TODO Auto-generated method stub
		latitude = location.getLatitude();
		longitude = location.getLongitude();
		altitude = location.getAltitude();
		accuracy = location.getAccuracy();
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
			int i=0;
			for (City city : msg.getList()) {
				menu.add(Menu.NONE, i, Menu.NONE, city.getName());
				i++;
			}
		}
				
		super.onCreateContextMenu(menu, v, menuInfo);
	}
	
	@Override
	public boolean onContextItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		
	    int index = item.getItemId();
	    
		Intent i = new Intent(this, TemperatureResultatActivity.class);
		i.putExtra("city", msg.getList().get(index).getName());
		i.putExtra("main", msg.getList().get(index).getMain());
		i.putExtra("wind", msg.getList().get(index).getWind());
		i.putExtra("weather", msg.getList().get(index).getWeathers().get(0));

		startActivity(i);
		
		return super.onContextItemSelected(item);
	}
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.button1:
			if(edit.getText().toString().isEmpty()){
				AlertDialog.Builder adb = new AlertDialog.Builder(this);
				adb.setTitle("Information");
				adb.setMessage("Veuillez tapez une ville s'il vous plait");
				adb.setPositiveButton("OK", null);
				adb.show();
			}else{
				Intent i = new Intent(this, TemperatureResultatActivity.class);
				i.putExtra("city", edit.getText().toString());
				startActivity(i);
			}
			break;
		case R.id.imageButton1:
			WSWeatherCities wsweathercities = new WSWeatherCities(this);
			wsweathercities.execute("http://api.openweathermap.org/data/2.1/find/city?lat="+latitude+"&lon="+longitude+"&cnt=10");
			break;
		default:
			break;
		}
	}

	public WeatherCities getMsg() {
		return msg;
	}

	public void setMsg(WeatherCities msg) {
		this.msg = msg;
	}
}
