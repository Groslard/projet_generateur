package GrosPaquet; 

public class Balise { 

	private String monstring; 
	private Arraylist<Arraylist<Coucou>> mescoucou; 

	public Balise(){} 

	public void setMonString(String monstring){
		this.monstring=monstring;
	}

	public String getMonString(){
		return monstring;
	}

	public void setMesCoucou(Arraylist<Arraylist<Coucou>> mescoucou){
		this.mescoucou=mescoucou;
	}

	public Arraylist<Arraylist<Coucou>> getMesCoucou(){
		return mescoucou;
	}


 }