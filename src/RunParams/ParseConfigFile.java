/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RunParams;

import java.io.BufferedReader;
import java.io.File;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 *
 * @author bickhart
 */
public class ParseConfigFile {
    private final Set<String> reqPaths = new HashSet<>();
    private final Map<String, String> paths = new ConcurrentHashMap<>();
    
    public ParseConfigFile(String ... prognames){
        reqPaths.addAll(Arrays.asList(prognames));
    }
    
    public void parse(String file, Logger console){
        try(BufferedReader input = Files.newBufferedReader(Paths.get(file), Charset.defaultCharset())){
            String line;
            while((line = input.readLine()) != null){
                line = line.trim();
                String[] segs = line.split("=");
                if(segs.length != 2)
                    throw new Exception("Config file error: Unset path variable for: " + line);
                paths.put(segs[0], segs[1]);
            }
        }catch(Exception ex){
            console.log(Level.SEVERE, ex.getMessage(), ex);
        }
        
        List<String> empty = this.reqPaths.stream()
                .filter(s -> !paths.containsKey(s))
                .collect(Collectors.toList());
        
        empty.stream()
                .forEach(s -> {
                    if(Stream.of(System.getenv("PATH").split(Pattern.quote(File.pathSeparator)))
                        .map(Paths::get)
                        .anyMatch(path -> Files.exists(path.resolve(s)))){
                        
                        // This is a stream method that gets the actual file path
                        Path loc = Stream.of(System.getenv("PATH").split(Pattern.quote(File.pathSeparator)))
                                .map(Paths::get)
                                .filter(path -> Files.exists(path.resolve(s)))
                                .findFirst().get();
                        paths.put(s, loc.toString());
                    }else{
                        console.log(Level.WARNING, "Config file warning: Could not find executable: " + s + " in System Path!");
                    }
                });
        
        console.log(Level.FINE, "Loaded Configuration file and executable paths");
    }
    
    public Path GetExecutablePath(String exe, Logger console){
        try{
            if(!ExecutableExists(exe))
                throw new Exception("Could not find executable: " + exe + " in system path!");
            if(!this.paths.containsKey(exe))
                throw new Exception("Configuration file does not contain a path for program: " + exe);
        }catch(Exception ex){
            console.log(Level.SEVERE, ex.getMessage(), ex);
        }
        
        return Paths.get(this.paths.get(exe));
    }
    
    private boolean ExecutableExists(String exe){
        if(!this.paths.containsKey(exe))
            return false;
        else{
            return Files.exists(Paths.get(this.paths.get(exe)).resolve(exe));
        }
    }
}
