package service;

import org.junit.Assert;
import org.junit.Test;

public class ServicioATest {
    @Test
    public void testSumar(){
        int a = 2;
        int b = 2;
        ServicioA servicioA = new ServicioAImpl();
        Assert.assertEquals(4, servicioA.sumar(a,b));
    }
}
