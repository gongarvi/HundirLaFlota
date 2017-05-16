package Modelo;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ListaArmasTest {
    private  ListaArmas la;

    @BeforeMethod
    public void init() throws Exception {
        la=new ListaArmas();
    }

    @Test
    public void testConstructora() throws Exception {
        Assert.assertNotNull(la);
    }

    @Test
    public void testFuncionesListaArmas() throws Exception {
       Assert.assertFalse(la.consultarArma("bomba"));
       Assert.assertFalse( la.consultarArma("misil"));
       Assert.assertFalse( la.consultarArma("misilEO"));
       Assert.assertFalse( la.consultarArma("misilNS"));
       Assert.assertFalse( la.consultarArma("misilBoom"));
       la.añadirArma("bomba");
       la.añadirArma("misil");
       la.añadirArma("misilEO");
       la.añadirArma("misilNS");
       la.añadirArma("misilBoom");
       Assert.assertTrue(la.consultarArma("bomba"));
       Assert.assertTrue( la.consultarArma("misil"));
       Assert.assertTrue( la.consultarArma("misilEO"));
       Assert.assertTrue( la.consultarArma("misilNS"));
       Assert.assertTrue( la.consultarArma("misilBoom"));
       Assert.assertTrue(la.getSize("bomba")==1);
       Assert.assertTrue( la.getSize("misil")==1);
       Assert.assertTrue( la.getSize("misilEO")==1);
       Assert.assertTrue( la.getSize("misilNS")==1);
       Assert.assertTrue( la.getSize("misilBoom")==1);
       la.removeArma("bomba");
       la.removeArma("misil");
       la.removeArma("misilEO");
       la.removeArma("misilNS");
       la.removeArma("misilBoom");
       Assert.assertFalse(la.consultarArma("bomba"));
       Assert.assertFalse( la.consultarArma("misil"));
       Assert.assertFalse( la.consultarArma("misilEO"));
       Assert.assertFalse( la.consultarArma("misilNS"));
       Assert.assertFalse( la.consultarArma("misilBoom"));
    }


    /**
     * los método relacionados con varias clases los testeamos
     * de manera manual aqui solo aparecen test de métodos finales
     * que devuelven un valor
     */
}
