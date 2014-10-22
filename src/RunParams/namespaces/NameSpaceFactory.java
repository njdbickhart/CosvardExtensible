/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RunParams.namespaces;

import RunParams.NameSpace;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.logging.Logger;

/**
 *
 * @author bickhart
 */
public class NameSpaceFactory {
    public ArrayList<NameSpace> getInitialNameSpace(String inputFile, Logger logfile, Logger console){
        ArrayList<NameSpace> holder = new ArrayList<>();
        try(BufferedReader input = Files.newBufferedReader(Paths.get(inputFile), Charset.defaultCharset())){
            
        }catch(IOException ex){
            
        }
        return holder;
    }
}
