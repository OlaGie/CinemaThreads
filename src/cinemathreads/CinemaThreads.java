
package cinemathreads;

/**
 *
 * @author Ola
 */
import java.util.*;
import java.util.concurrent.*;

public class CinemaThreads {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException {
        
        List<Bilet> bilety=new ArrayList<>();
        int biletyIlosc=3;
        int watkiIlosc=10;
        
        for(int i=0;i<biletyIlosc;i++){
            bilety.add(new Bilet(i,false));
        }
        
        //List<Callable<Integer>> zadanie = new ArrayList<Callable<Integer>>();
        ExecutorService exec = Executors.newFixedThreadPool(watkiIlosc);
        List<FutureTaskR> wyniki = new ArrayList<>();
        
        for(int i=0;i<watkiIlosc;i++){
            wyniki.add(new FutureTaskR(new Rezerwacja(bilety,i, "Nazwisko: "+i)));
        }
        
        for(FutureTaskR r:wyniki){
            exec.execute(r);
        }
        exec.shutdown();

        for(Bilet b:bilety){
            System.out.println("Bilet nr: "+b.getNrBiletu()+" zostal zarezerwowany na nazwisko: "+b.getNazwisko());
        }
        
    }
    
}
