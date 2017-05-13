package Modelo;

import org.testng.Assert;
import org.testng.annotations.Test;

public class BarcoFactoryTest {

    @Test
    public void testGetBarcoFactory() throws Exception {
        Assert.assertNotNull(BarcoFactory.getBarcoFactory());
    }

    @Test
    public void testCrearBarco() throws Exception {
        Assert.assertNotNull(BarcoFactory.getBarcoFactory().crearBarco("fragata"));
        Assert.assertTrue(BarcoFactory.getBarcoFactory().crearBarco("fragata") instanceof  Fragata);
        Assert.assertNotNull(BarcoFactory.getBarcoFactory().crearBarco("destructor"));
        Assert.assertTrue(BarcoFactory.getBarcoFactory().crearBarco("destructor") instanceof  Destructor);
        Assert.assertNotNull(BarcoFactory.getBarcoFactory().crearBarco("submarino"));
        Assert.assertTrue(BarcoFactory.getBarcoFactory().crearBarco("submarino") instanceof  Submarino);
        Assert.assertNotNull(BarcoFactory.getBarcoFactory().crearBarco("portaaviones"));
        Assert.assertTrue(BarcoFactory.getBarcoFactory().crearBarco("portaaviones") instanceof  Portaaviones);
    }


}
