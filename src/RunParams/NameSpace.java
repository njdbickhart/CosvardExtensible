/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RunParams;

import java.util.ArrayList;

/**
 *
 * @author bickhart
 */
public abstract class NameSpace {
    public final String ID;
    public final String LIB;
    public final String SAMPLE;
    public final ArrayList<String> INFO;
    
    public NameSpace(String[] a){
        this.ID = a[0];
        this.LIB = a[1];
        this.SAMPLE = a[2];
        this.INFO = new ArrayList<>();
        for(int x = 3; x < a.length; x++){
            this.INFO.add(a[x]);
        }
    }
}
