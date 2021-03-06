package fr.abdelli.modele;

import android.os.Parcel;
import android.os.Parcelable;

public class Weather implements Parcelable{

	private int id;
	private String main;
	private String description;
	private String icon;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getMain() {
		return main;
	}
	public void setMain(String main) {
		this.main = main;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public void writeToParcel(Parcel dest, int flags) {
		// TODO Auto-generated method stub
		dest.writeInt(id);
		dest.writeString(main);
		dest.writeString(description);
		dest.writeString(icon);
	}
	
	public static final Parcelable.Creator<Weather> CREATOR = new Parcelable.Creator<Weather>(){
		
		@Override
		public Weather createFromParcel(Parcel source){
			return new Weather(source);
		}

		@Override
		public Weather[] newArray(int size){
			return new Weather[size];
		}
	};

	public Weather(Parcel in) {
		this.id= in.readInt();
		this.main = in.readString();
		this.description = in.readString();
		this.icon = in.readString();

	}
}
