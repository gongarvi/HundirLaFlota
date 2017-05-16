package Modelo;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class STocadoTest {
    STocado tocado;

    @BeforeMethod
    public void init(){
        tocado=new STocado();
    }

    @Test
    public void testConstructora() throws Exception {
        Assert.assertNotNull(tocado);
    }

    @Test
    public void testCambiosEstado() throws Exception {
        Assert.assertTrue(tocado.reparar() instanceof  SNormal);
        Assert.assertTrue(tocado.tocar() instanceof  STocado);
    }

    /**
     * los método relacionados con varias clases los testeamos
     * de manera manual aqui solo aparecen test de métodos finales
     * que devuelven un valor
     */
}
