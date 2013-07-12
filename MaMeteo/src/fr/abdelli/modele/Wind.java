package fr.abdelli.modele;

import android.os.Parcel;
import android.os.Parcelable;

public class Wind implements Parcelable{

	private double speed;
	private int deg;
	private double gust;
	
	public double getSpeed() {
		return speed;
	}
	public void setSpeed(double speed) {
		this.speed = speed;
	}
	public int getDeg() {
		return deg;
	}
	public void setDeg(int deg) {
		this.deg = deg;
	}
	public double getGust() {
		return gust;
	}
	public void setGust(double gust) {
		this.gust = gust;
	}
	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public void writeToParcel(Parcel dest, int flags) {
		// TODO Auto-generated method stub
		dest.writeDouble(speed);
		dest.writeInt(deg);
		dest.writeDouble(gust);
	}
	
	public static final Parcelable.Creator<Wind> CREATOR = new Parcelable.Creator<Wind>(){
		
		@Override
		public Wind createFromParcel(Parcel source){
			return new Wind(source);
		}

		@Override
		public Wind[] newArray(int size){
			return new Wind[size];
		}
	};

	public Wind(Parcel in) {
		this.speed= in.readDouble();
		this.deg = in.readInt();
		this.gust = in.readDouble();
	}
}
