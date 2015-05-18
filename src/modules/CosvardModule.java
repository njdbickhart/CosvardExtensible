/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modules;

import RunParams.NameSpace;
import RunParams.components.ExecPaths;
import engine.Command;
import logger.LogFile;

/**
 *
 * @author bickhart
 */
public abstract class CosvardModule {
    protected final NameSpace[] input;
    protected NameSpace output;
    protected final ExecPaths paths;
    protected final LogFile logger;
    
    public CosvardModule(ExecPaths paths, LogFile logger, NameSpace input){
        this.paths = paths;
        this.logger = logger;
        this.input = new NameSpace[]{input};
    }
    
    public CosvardModule(ExecPaths paths, LogFile logger, NameSpace[] input){
        this.paths = paths;
        this.logger = logger;
        this.input = input;
    }
    
    public abstract Command CreateCMD();
    
    public abstract NameSpace GetOutput();
}
