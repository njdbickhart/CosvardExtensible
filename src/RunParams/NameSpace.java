/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RunParams;

/**
 *
 * @author bickhart
 */
public abstract class NameSpace {
    public final String ID;
    public final String LIB;
    public final String SAMPLE;
    
    public NameSpace(String ID, String LIB, String SAMPLE){
        this.ID = ID;
        this.LIB = LIB;
        this.SAMPLE = SAMPLE;
    }
}
