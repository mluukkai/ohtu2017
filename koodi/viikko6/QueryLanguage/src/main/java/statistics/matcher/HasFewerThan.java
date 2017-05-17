/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package statistics.matcher;

import java.lang.reflect.Method;
import statistics.Player;

/**
 *
 * @author jarkko
 */
public class HasFewerThan implements Matcher{
    private int num;
    private String field;
   
    public HasFewerThan(int num, String field){
        this.num = num;
        this.field = "get" 
                + Character.toUpperCase(field.charAt(0)) 
                + field.substring(1, field.length());
    }

    @Override
    public boolean matches(Player p) {
        
        try {                                    
            Method method = p.getClass().getMethod(field);
            int playersValue = (Integer)method.invoke(p);
            return playersValue < num;
            
        } catch (Exception ex) {
            System.out.println(ex);
            throw new IllegalStateException(
                    "Player does not have field "
                    + field.substring(3, field.length()).toLowerCase());
        }   
    }
    
}
