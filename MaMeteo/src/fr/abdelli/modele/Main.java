package fr.abdelli.modele;

import android.os.Parcel;
import android.os.Parcelable;

public class Main implements Parcelable{

	private double temp;
	private double pressure;
	private double humidity;
	private double temp_min;
	private double temp_max;
	
	public double getTemp() {
		return temp;
	}
	public void setTemp(double temp) {
		this.temp = temp;
	}
	public double getPressure() {
		return pressure;
	}
	public void setPressure(double pressure) {
		this.pressure = pressure;
	}
	public double getHumidity() {
		return humidity;
	}
	public void setHumidity(double humidity) {
		this.humidity = humidity;
	}
	public double getTemp_min() {
		return temp_min;
	}
	public void setTemp_min(double temp_min) {
		this.temp_min = temp_min;
	}
	public double getTemp_max() {
		return temp_max;
	}
	public void setTemp_max(double temp_max) {
		this.temp_max = temp_max;
	}
	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public void writeToParcel(Parcel dest, int flags) {
		// TODO Auto-generated method stub
		dest.writeDouble(temp);
		dest.writeDouble(pressure);
		dest.writeDouble(humidity);
		dest.writeDouble(temp_max);
		dest.writeDouble(temp_min);
	}
	
	public static final Parcelable.Creator<Main> CREATOR = new Parcelable.Creator<Main>(){
		
		@Override
		public Main createFromParcel(Parcel source){
			return new Main(source);
		}

		@Override
		public Main[] newArray(int size){
			return new Main[size];
		}
	};

	public Main(Parcel in) {
		this.temp = in.readDouble();
		this.pressure = in.readDouble();
		this.humidity = in.readDouble();
		this.temp_max = in.readDouble();
		this.temp_min = in.readDouble();
	}
	
}
