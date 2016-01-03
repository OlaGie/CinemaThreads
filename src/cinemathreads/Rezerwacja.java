package cinemathreads;

import java.util.List;
import java.util.Random;
import java.util.concurrent.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ola
 */
public class Rezerwacja implements Callable<String>{
    
    List<Bilet> bilety;
    String nazwisko;
    int nrWatku;
    
    Rezerwacja(List<Bilet> bilety, int nrWatku, String nazwisko){
        this.bilety=bilety;
        this.nrWatku=nrWatku;
        this.nazwisko=nazwisko;
    }
        public Bilet wolnyBilet(List<Bilet> bilety){
            for(Bilet b:bilety){
                if(b.getZarezerwowany()==false)
                    return b;
            }
            return null;
        }

        public synchronized void zarezerwuj(Bilet bilet){

                    bilet.setNazwisko(nazwisko);
                    bilet.setZarezerwowany(true); 
                    //System.out.println("Return: Watek: "+Thread.currentThread().getName()+ " zarezerwowal bilet nr "+bilet.getNrBiletu() + " na nazwisko "+nazwisko);
                    return ;
                }   
        public synchronized void odrezerwuj(Bilet bilet){
            //System.out.println("Oczekiwanie na wolny bilet "+ bilet.getNrBiletu()+" w funkcji odrezerwuj()");
                
                    bilet.setNazwisko(null);
                    bilet.setZarezerwowany(false); 
                    //System.out.println("Odrezerwuj: Watek: "+Thread.currentThread().getName()+ " odrezerwowal bilet nr "+bilet.getNrBiletu()) ;
                
        }   

    @Override
    public String call() throws Exception {

            Random generator = new Random();
            int nrBiletu=generator.nextInt(3);
            Bilet bilet=bilety.get(nrBiletu);
            //System.out.println("nrBiletu"+nrBiletu+"Zarezerwowany: "+bilet.getZarezerwowany());
                if(bilet.getZarezerwowany() ==false){
                    zarezerwuj(bilet);
                    return "Watek: "+Thread.currentThread().getName()+ " zarezerwowal bilet nr "+nrBiletu + " na nazwisko "+nazwisko;                
                }

                else{
                    System.out.println("Wątek nr "+Thread.currentThread().getName()+ "oczekuje na wolny bilet nr"+ bilet.getNrBiletu());
                    Thread.sleep(2000);
                    if(bilet.getZarezerwowany() ==false){
                        zarezerwuj(bilet);
                        return "Watek: "+Thread.currentThread().getName()+ " zarezerwowal bilet nr "+nrBiletu + " na nazwisko "+nazwisko;                
                    }
                    odrezerwuj(bilet);
                    //System.out.println("Watek: "+Thread.currentThread().getName()+ " odrezerwowal bilet nr "+nrBiletu) ;
                    return "Return Watek: "+Thread.currentThread().getName()+ " odrezerwowal bilet nr "+nrBiletu ;
                    //Thread.sleep(1000);
                }
            //odrezerwuj(bilet);
                            
                

    }
}
