package cinemathreads;

/**
 *
 * @author Ola
 */
import java.util.Scanner;

public class Bilet {
    	private int nrBiletu;
	private Boolean zarezerwowany;
        private String nazwisko;
	
	public Bilet(int nrBiletu, Boolean zarezerwowany) {
		
		this.nrBiletu = nrBiletu;
		this.zarezerwowany = zarezerwowany;
                nazwisko=null;
	}
	public Bilet(int nrBiletu, Boolean zarezerwowany, String nazwisko) {
		
		this.nrBiletu = nrBiletu;
		this.zarezerwowany = zarezerwowany;
                this.nazwisko=nazwisko;
	}        
	
	public int getNrBiletu() {
		return nrBiletu;
	}
	public void setNrBiletu(int nrBiletu) {
		this.nrBiletu = nrBiletu;
	}
	public Boolean getZarezerwowany() {
		return zarezerwowany;
	}
	public void setZarezerwowany(Boolean zarezerwowany) {
		this.zarezerwowany = zarezerwowany;
	}
        public void setNazwisko(String nazwisko){
            this.nazwisko=nazwisko;
        }
        public String getNazwisko(){
            return nazwisko;
        }
        
        synchronized int zarezerwuj(String nazwa){

            if(zarezerwowany){
                return 1;
            }
            else{
                zarezerwowany=true;
                nazwisko=nazwa;
                System.out.println("Metoda zarezerwuj(): Bilet nr "+nrBiletu+" zarezerwowany na nazwisko: "+nazwisko );            
            }
            return 0; 
	
	}
	synchronized String odwolaj(){
            if(!zarezerwowany)
                    return "Bilet nr "+nrBiletu+" nie jest zarezerwowany";
            else{
            zarezerwowany=false;
            System.out.println("Rezerwacja na bilet nr "+nrBiletu+" została anulowana");
            //nazwisko=null;          
            }
            return "Rezerwacja na bilet nr "+nrBiletu+" została anulowana";
        }
}
