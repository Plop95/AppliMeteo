package fr.abdelli.mameteo;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import fr.abdelli.modele.City;
import fr.abdelli.modele.Main;
import fr.abdelli.modele.Weather;
import fr.abdelli.modele.WeatherCities;
import fr.abdelli.modele.Wind;
import fr.abdelli.ws.WSWeatherCities;

public class TemperatureResultatActivity extends Activity implements OnClickListener{

	private String city;
	private WeatherCities msg;
	private boolean isCurrentTemp;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.temperature_resultat);
	}
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		city = getIntent().getExtras().getString("city");

		((TextView)findViewById(R.id.textViewTitre)).setText(city+" en ce moment :");

		if(getIntent().getExtras().size()>1){
			Main main = getIntent().getExtras().getParcelable("main");
			Weather weather = getIntent().getExtras().getParcelable("weather");
			Wind wind = getIntent().getExtras().getParcelable("wind");
			
			((TextView)findViewById(R.id.textViewMain)).setText(weather.getMain());
			int temp = (int) (main.getTemp()-273.15);
			((TextView)findViewById(R.id.textViewTemperature)).setText(""+temp+"¡C");
			((TextView)findViewById(R.id.textViewHumidite)).setText(""+main.getHumidity()+"%");
			((TextView)findViewById(R.id.textViewVent)).setText(""+wind.getSpeed()+" mps");
			
			GetIconImageTask task = new GetIconImageTask();
			task.setImgView(((ImageView)findViewById(R.id.imageView1)));
			task.execute("http://openweathermap.org/img/w/"+weather.getIcon());
		}else{
			isCurrentTemp=true;
			Log.v("BLABLA", "http://api.openweathermap.org/data/2.1/find/name?q="+city+"&units=metric");
			WSWeatherCities wsweathercities = new WSWeatherCities(this);
			wsweathercities.execute("http://api.openweathermap.org/data/2.1/find/name?q="+city+"&units=metric");
		}
		
		
		findViewById(R.id.button1).setOnClickListener(this);
		findViewById(R.id.button2).setOnClickListener(this);

	}
	
	public WeatherCities getMsg() {
		return msg;
	}

	public void setMsg(WeatherCities msg) {
		this.msg = msg;
	}
	
	public void addMoreTemp(){
		City c = msg.getList().get(7);
		GetIconImageTask task = new GetIconImageTask();
		task.setImgView(((ImageView)findViewById(R.id.ImageView4)));
		task.execute("http://openweathermap.org/img/w/"+c.getWeathers().get(0).getIcon());
		((TextView)findViewById(R.id.textViewMain4)).setText(c.getWeathers().get(0).getMain());
		int temp = (int) (c.getMain().getTemp()-273.15);
		((TextView)findViewById(R.id.textViewTemperature4)).setText(""+temp+"¡C");

		
		City c2 = msg.getList().get(14);
		GetIconImageTask task2 = new GetIconImageTask();
		task2.setImgView(((ImageView)findViewById(R.id.ImageView3)));
		task2.execute("http://openweathermap.org/img/w/"+c2.getWeathers().get(0).getIcon());
		((TextView)findViewById(R.id.textViewMain3)).setText(c2.getWeathers().get(0).getMain());
		int temp2 = (int) (c2.getMain().getTemp()-273.15);
		((TextView)findViewById(R.id.textViewTemperature3)).setText(""+temp2+"¡C");

		City c3 = msg.getList().get(21);
		GetIconImageTask task3 = new GetIconImageTask();
		task3.setImgView(((ImageView)findViewById(R.id.ImageView2)));
		task3.execute("http://openweathermap.org/img/w/"+c3.getWeathers().get(0).getIcon());
		((TextView)findViewById(R.id.textViewMain2)).setText(c3.getWeathers().get(0).getMain());
		int temp3 = (int) (c3.getMain().getTemp()-273.15);
		((TextView)findViewById(R.id.textViewTemperature2)).setText(""+temp3+"¡C");

	}
	
	public void afficheCurrentTemp(){
		isCurrentTemp=false;
		
		City c = msg.getList().get(0);
		((TextView)findViewById(R.id.textViewMain)).setText(c.getWeathers().get(0).getMain());
		((TextView)findViewById(R.id.textViewTemperature)).setText(""+c.getMain().getTemp()+"¡C");
		((TextView)findViewById(R.id.textViewHumidite)).setText(""+c.getMain().getHumidity()+"%");
		((TextView)findViewById(R.id.textViewVent)).setText(""+c.getWind().getSpeed()+" mps");
		
		GetIconImageTask task = new GetIconImageTask();
		task.setImgView(((ImageView)findViewById(R.id.imageView1)));
		task.execute("http://openweathermap.org/img/w/"+c.getWeathers().get(0).getIcon());
	}
	
	class GetIconImageTask extends AsyncTask<String, Integer, Bitmap>{
		private ImageView imgViewRef;
		@Override
		protected Bitmap doInBackground(String... params) {
			// TODO Auto-generated method stub
			URL imageURL=null;
			
			try {
				imageURL = new URL(params[0]);
				return BitmapFactory.decodeStream(imageURL.openConnection().getInputStream());

			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}
		
		@Override
		protected void onPostExecute(Bitmap result) {
			// TODO Auto-generated method stub
			imgViewRef.setImageBitmap(result);

			super.onPostExecute(result);
		}

		public ImageView getImgView() {
			return imgViewRef;
		}

		public void setImgView(ImageView imgViewRef) {
			this.imgViewRef = imgViewRef;
		}
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.button1:
			findViewById(R.id.relativeLay).setVisibility(View.VISIBLE);
			findViewById(R.id.textViewTitreTemp).setVisibility(View.VISIBLE);
			findViewById(R.id.button1).setVisibility(View.GONE);

			WSWeatherCities wsweathercities = new WSWeatherCities(this);
			wsweathercities.execute("http://api.openweathermap.org/data/2.1/forecast/city?q="+city);
			
			break;
		case R.id.button2:
			finish();
			break;

		default:
			break;
		}
	}
	
	
	public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
                finish();
                return false;
        }
        return super.onKeyDown(keyCode, event);
	}

	public boolean isCurrentTemp() {
		return isCurrentTemp;
	}

	public void setCurrentTemp(boolean isCurrentTemp) {
		this.isCurrentTemp = isCurrentTemp;
	}

}
