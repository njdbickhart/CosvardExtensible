/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package engine;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.Callable;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author desktop
 */
public class Command implements Callable<String>{
    
    
    
    protected Thread StreamDepleter(Process p){
        return new Thread(() ->{
            try(final BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()))){
            
                String line;
                try {
                    while((line = in.readLine()) != null){
                        //out.write(line);
                        //out.newLine();
                    }
                } catch (IOException ex) {            
                    Logger.getLogger(Command.class.getName()).log(Level.SEVERE, null, ex);
                }
                //out.close();
            }catch(IOException ex){
                ex.printStackTrace();
            }
            
        });
    }
    
    protected Thread StreamLogger(Process p, Logger log){
        return new Thread(() ->{
            try(final BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()))){
            
                String line;
                try {
                    while((line = in.readLine()) != null){
                        log.log(Level.FINEST, line);
                    }
                } catch (IOException ex) {            
                    log.log(Level.SEVERE, null, ex);
                }
                //out.close();
            }catch(IOException ex){
                log.log(Level.SEVERE, null, ex);
            }
            
        });
    }

    @Override
    public String call() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
