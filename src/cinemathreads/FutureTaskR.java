/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cinemathreads;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ola
 */
public class FutureTaskR<T> extends FutureTask<T>{

    public FutureTaskR(Callable clbl) {
        super(clbl);
    }
    public void done() {
        if(isCancelled()){
            System.out.println("Cancelled");
        }
        else{
            try {
                System.out.println("Zakończony wątek: "+Thread.currentThread().getName()+" "+get());
                //get();
                //System.out.println(":done(): "getName());
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            } catch (ExecutionException ex) {
                ex.printStackTrace();
            }
        }

    }
}
