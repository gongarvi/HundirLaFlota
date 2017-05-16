package Modelo;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class EscudoTest {
    private  Escudo tmp;

    @BeforeMethod
    public void init() throws Exception {
        tmp=new Escudo();

    }

    @Test
    public void testConstructora() throws Exception {
        Assert.assertNotNull(tmp);
    }

    @Test
    public void testRecibirImpactosYGetImpactosRestantes() throws Exception {
        Assert.assertTrue(tmp.getImpactosRestantes()==2);
        tmp.recibirImpacto();
        Assert.assertTrue(tmp.getImpactosRestantes()==1);
        Assert.assertTrue(tmp.recibirImpacto());
    }

    @Test
    public void testDestruir() throws Exception {
        Assert.assertTrue(tmp.getImpactosRestantes()==2);
        tmp.destruir();
        Assert.assertTrue(tmp.getImpactosRestantes()==0);
    }

    /**
     * los método relacionados con varias clases los testeamos
     * de manera manual aqui solo aparecen test de métodos finales
     * que devuelven un valor
     */
}
