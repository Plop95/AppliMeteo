package fr.abdelli.modele;

import java.util.ArrayList;

public class WeatherCities {

	private double message;
	private String cod;
	private String calctime;
	private int cnt;
	private ArrayList<City> list;
	
	public double getMessage() {
		return message;
	}
	public void setMessage(double message) {
		this.message = message;
	}
	public String getCod() {
		return cod;
	}
	public void setCod(String cod) {
		this.cod = cod;
	}
	public String getCalctime() {
		return calctime;
	}
	public void setCalctime(String calctime) {
		this.calctime = calctime;
	}
	public int getCnt() {
		return cnt;
	}
	public void setCnt(int cnt) {
		this.cnt = cnt;
	}
	public ArrayList<City> getList() {
		return list;
	}
	public void setList(ArrayList<City> list) {
		this.list = list;
	}
}
