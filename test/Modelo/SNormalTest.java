package Modelo;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SNormalTest {
    SNormal normal;

    @BeforeMethod
    public void init(){
        normal=new SNormal();
    }

    @Test
    public void testConstructora() throws Exception {
        Assert.assertNotNull(normal);
    }

    @Test
    public void testCambiosEstado() throws Exception {
      Assert.assertTrue(normal.reparar() instanceof  SNormal);
      Assert.assertTrue(normal.tocar() instanceof  STocado);
    }

    /**
     * los método relacionados con varias clases los testeamos
     * de manera manual aqui solo aparecen test de métodos finales
     * que devuelven un valor
     */
}
