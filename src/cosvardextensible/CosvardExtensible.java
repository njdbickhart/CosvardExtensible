/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cosvardextensible;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;
import logger.MainPipelineFormatter;

/**
 *
 * @author bickhart
 */
public class CosvardExtensible {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
    }
    
    private static Logger CreateMainAppLogger(String logOut){
        Logger log = Logger.getLogger(CosvardExtensible.class.getName());
        LogManager.getLogManager().reset();
        try {
            FileHandler fh = new FileHandler("logOut");
            fh.setFormatter(new MainPipelineFormatter());
            log.addHandler(fh);            
        } catch (IOException | SecurityException ex) {
            Logger.getLogger(CosvardExtensible.class.getName()).log(Level.SEVERE, null, ex);
        }
        return log;
    }
    
    private static Logger CreateMainConsoleLogger(){
        Logger log = Logger.getLogger(CosvardExtensible.class.getName());
        
        return log;
    }
}
