package Modelo;

import org.testng.Assert;
import org.testng.annotations.Test;

public class ArmaFactoryTest {

    @Test
    public void testGetArmaFactory() throws Exception {
        Assert.assertNotNull(ArmaFactory.getArmaFactory());
    }

    @Test
    public void testCrearArma() throws Exception {
        Assert.assertNotNull(ArmaFactory.getArmaFactory().crearArma("bomba"));
        Assert.assertTrue(ArmaFactory.getArmaFactory().crearArma("bomba") instanceof  Bomba);
        Assert.assertNotNull(ArmaFactory.getArmaFactory().crearArma("misil"));
        Assert.assertTrue(ArmaFactory.getArmaFactory().crearArma("misil") instanceof  Misil);
        Assert.assertNotNull(ArmaFactory.getArmaFactory().crearArma("misilEO"));
        Assert.assertTrue(ArmaFactory.getArmaFactory().crearArma("misilEO") instanceof  MisilEO);
        Assert.assertNotNull(ArmaFactory.getArmaFactory().crearArma("misilNS"));
        Assert.assertTrue(ArmaFactory.getArmaFactory().crearArma("misilNS") instanceof  MisilNS);
        Assert.assertNotNull(ArmaFactory.getArmaFactory().crearArma("misilBoom"));
        Assert.assertTrue(ArmaFactory.getArmaFactory().crearArma("misilBoom") instanceof  MisilBoom);
    }


    /**
     * los método relacionados con varias clases los testeamos
     * de manera manual aqui solo aparecen test de métodos finales
     * que devuelven un valor
     */
}
