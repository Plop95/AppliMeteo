package fr.abdelli.modele;

import java.util.ArrayList;

public class City {

	private int id;
	private String name;
	private Main main;
	private Wind wind;
	private ArrayList<Weather> weather;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Main getMain() {
		return main;
	}
	public void setMain(Main main) {
		this.main = main;
	}
	public Wind getWind() {
		return wind;
	}
	public void setWind(Wind wind) {
		this.wind = wind;
	}
	public ArrayList<Weather> getWeathers() {
		return weather;
	}
	public void setWeathers(ArrayList<Weather> weather) {
		this.weather = weather;
	}
}
