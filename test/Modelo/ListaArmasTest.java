package Modelo;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ListaArmasTest {

    ListaArmas tmp;

    @BeforeMethod
    public void init() {
       tmp=new ListaArmas();
    }

    @Test
    public void testGetMiAlmacen() throws Exception {
        Assert.assertNotNull(tmp);
    }

    @Test
    public void testAñadirArmaYGetSize() throws Exception {
        tmp.añadirArma("bomba");
        tmp.añadirArma("misil");
        tmp.añadirArma("misilNS");
        tmp.añadirArma("misilEO");
        tmp.añadirArma("misilBoom");
        Assert.assertEquals(1,tmp.getSize("bomba"));
        Assert.assertEquals(1,tmp.getSize("misil"));
        Assert.assertEquals(1,tmp.getSize("misilNS"));
        Assert.assertEquals(1,tmp.getSize("misilEO"));
        Assert.assertEquals(1,tmp.getSize("misilBoom"));
    }

    @Test
    public void testRemoveArmaYGetSize() throws Exception {
        tmp.removeArma("bomba");
        tmp.removeArma("misil");
        tmp.removeArma("misilNS");
        tmp.removeArma("misilEO");
        tmp.removeArma("misilBoom");
        Assert.assertEquals(0,tmp.getSize("bomba"));
        Assert.assertEquals(0,tmp.getSize("misil"));
        Assert.assertEquals(0,tmp.getSize("misilNS"));
        Assert.assertEquals(0,tmp.getSize("misilEO"));
        Assert.assertEquals(0,tmp.getSize("misilBoom"));

        tmp.añadirArma("bomba");
        tmp.añadirArma("misil");
        tmp.añadirArma("misilNS");
        tmp.añadirArma("misilEO");
        tmp.añadirArma("misilBoom");
        tmp.añadirArma("bomba");
        tmp.añadirArma("misil");
        tmp.añadirArma("misilNS");
        tmp.añadirArma("misilEO");
        tmp.añadirArma("misilBoom");
        tmp.removeArma("bomba");
        tmp.removeArma("misil");
        tmp.removeArma("misilNS");
        tmp.removeArma("misilEO");
        tmp.removeArma("misilBoom");
        Assert.assertEquals(1,tmp.getSize("bomba"));
        Assert.assertEquals(1,tmp.getSize("misil"));
        Assert.assertEquals(1,tmp.getSize("misilNS"));
        Assert.assertEquals(1,tmp.getSize("misilEO"));
        Assert.assertEquals(1,tmp.getSize("misilBoom"));
    }

    @Test
    public void testConsultaArma() throws Exception {
        tmp.añadirArma("bomba");
        tmp.añadirArma("misil");
        tmp.añadirArma("misilNS");
        tmp.añadirArma("misilEO");
        tmp.añadirArma("misilBoom");
        Assert.assertTrue(tmp.consultarArma("bomba"));
        Assert.assertTrue(tmp.consultarArma("misil"));
        Assert.assertTrue(tmp.consultarArma("misilNS"));
        Assert.assertTrue(tmp.consultarArma("misilEO"));
        Assert.assertTrue(tmp.consultarArma("misilBoom"));
    }

}
