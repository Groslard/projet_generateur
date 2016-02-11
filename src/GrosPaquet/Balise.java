package GrosPaquet;

import java.util.ArrayList;

public class Balise { 

	private String monstring; 
	private ArrayList<ArrayList<Coucou>> mescoucou; 

	public Balise(){} 

	public void setMonString(String monstring){
		this.monstring=monstring;
	}

	public String getMonString(){
		return monstring;
	}

	public void setMesCoucou(ArrayList<ArrayList<Coucou>> mescoucou){
		this.mescoucou=mescoucou;
	}

	public ArrayList<ArrayList<Coucou>> getMesCoucou(){
		return mescoucou;
	}


 }