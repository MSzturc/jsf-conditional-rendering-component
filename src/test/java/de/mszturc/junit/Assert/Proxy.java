package de.mszturc.junit.Assert;

import org.junit.Assert;
import static org.junit.Assert.assertTrue;

/**
 * Author: MSzturc
 * Date:   31.07.2014 
 */
public class Proxy {

    public static void assertProxyInsanceOf(Object candidate, Class<?> clazz){
        
        if(!(candidate.getClass() == clazz)){
            if(candidate.getClass().getName().toLowerCase().contains("proxy")){
                assertTrue(candidate.getClass().getSuperclass() == clazz);
            }
            else {
                Assert.fail();
            }
        }
    }
}
