package fr.abdelli.modele;

import java.util.ArrayList;

public class City {

	private int id;
	private int dt;
	private String name;
	private double distance;
	private Coordonnee coord;
	private Main main;
	private Wind wind;
	private ArrayList<Weather> weathers;
	private Cloud cloud;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getDt() {
		return dt;
	}
	public void setDt(int dt) {
		this.dt = dt;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getDistance() {
		return distance;
	}
	public void setDistance(double distance) {
		this.distance = distance;
	}
	public Coordonnee getCoord() {
		return coord;
	}
	public void setCoord(Coordonnee coord) {
		this.coord = coord;
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
		return weathers;
	}
	public void setWeathers(ArrayList<Weather> weathers) {
		this.weathers = weathers;
	}
	public Cloud getCloud() {
		return cloud;
	}
	public void setCloud(Cloud cloud) {
		this.cloud = cloud;
	}
}
