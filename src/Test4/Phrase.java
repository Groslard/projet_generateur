package Test4; 


public class Phrase { 

	public Phrase(){
 	 this.mamot= new Mot();

 	 this.numero=10;

	} 

	private Mot mamot; 
	private int numero; 

	public void setMaMot(Mot mamot){
		this.mamot=mamot;
	}

	public Mot getMaMot(){
		return mamot;
	}

	public void setNumero(int numero){
		this.numero=numero;
	}

	public int getNumero(){
		return numero;
	}


 }