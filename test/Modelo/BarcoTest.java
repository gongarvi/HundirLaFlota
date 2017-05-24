package Modelo;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class BarcoTest {

    /**
     * test de todas las constructoras de Barco
     */
    Barco tmp1;
    Barco tmp2;
    Barco tmp3;
    Barco tmp4;

    @BeforeMethod
    public void init() {
        tmp1 = new Fragata();
        tmp2 = new Destructor();
        tmp3 = new Submarino();
        tmp4 = new Portaaviones();
    }

    @Test
    public void testCostructoras() throws Exception {
        Assert.assertNotNull(tmp1);
        Assert.assertNotNull(tmp2);
        Assert.assertNotNull(tmp3);
        Assert.assertNotNull(tmp4);
    }

    @Test
    public void testHundido() throws Exception {
        Assert.assertFalse(tmp1.hundido());
        Assert.assertFalse(tmp2.hundido());
        Assert.assertFalse(tmp3.hundido());
        Assert.assertFalse(tmp4.hundido());
    }

    @Test
    public void testEsColindante() throws Exception {
        int []prueba1=new int[2];
        prueba1[0]=1;
        prueba1[1]=1;
        int []prueba2=new int[2];
        prueba2[0]=-1;
        prueba2[1]=-1;
        int []prueba3=new int[2];
        prueba3[0]=11;
        prueba3[1]=11;
        int []prueba4=new int[2];
        prueba4[0]=-1;
        prueba4[1]=0;
        Assert.assertFalse(tmp1.escolindante(prueba1,"H",1));
        Assert.assertTrue(tmp2.escolindante(prueba2,"V",1));
        Assert.assertFalse(tmp3.escolindante(prueba3,"H",1));
        Assert.assertTrue(tmp4.escolindante(prueba4,"V",1));
    }

    @Test
    public void testEsBarcoCorrecto() throws Exception {
        Assert.assertFalse(tmp1.esBarcoCorrecto(2));
        Assert.assertTrue(tmp2.esBarcoCorrecto(2));
        Assert.assertFalse(tmp3.esBarcoCorrecto(4));
        Assert.assertTrue(tmp4.esBarcoCorrecto(4));
        Assert.assertTrue(tmp1.esBarcoCorrecto(1));
        Assert.assertFalse(tmp2.esBarcoCorrecto(1));
        Assert.assertTrue(tmp3.esBarcoCorrecto(3));
        Assert.assertFalse(tmp4.esBarcoCorrecto(3));
    }


    @Test
    public void testNoInicializadoYInicializar() throws Exception {
        Assert.assertTrue(tmp1.noInicializado());
        Assert.assertTrue(tmp2.noInicializado());
        Assert.assertTrue(tmp3.noInicializado());
        Assert.assertTrue(tmp4.noInicializado());
        int []prueba1=new int[2];
        prueba1[0]=1;
        prueba1[1]=1;
        tmp1.inicializar(prueba1,"H");
        tmp2.inicializar(prueba1,"V");
        tmp3.inicializar(prueba1,"H");
        tmp4.inicializar(prueba1,"V");
        Assert.assertFalse(tmp1.noInicializado());
        Assert.assertFalse(tmp2.noInicializado());
        Assert.assertFalse(tmp3.noInicializado());
        Assert.assertFalse(tmp4.noInicializado());
    }

    @Test
    public void testEstadoPosRepararTocarSetEstadoPos() throws Exception {

        Assert.assertEquals(tmp2.estadoPos(new Posicion(-1,-1)),"normal");
        Assert.assertEquals(tmp3.estadoPos(new Posicion(-1,-1)),"normal");
        Assert.assertEquals(tmp4.estadoPos(new Posicion(-1,-1)),"normal");

        tmp2.recibirDanios(new Posicion(-1,-1));
        tmp3.recibirDanios(new Posicion(-1,-1));
        tmp4.recibirDanios(new Posicion(-1,-1));

        Assert.assertEquals(tmp2.estadoPos(new Posicion(-1,-1)),"tocado");
        Assert.assertEquals(tmp3.estadoPos(new Posicion(-1,-1)),"tocado");
        Assert.assertEquals(tmp4.estadoPos(new Posicion(-1,-1)),"tocado");

        tmp2.reparar(new Posicion(-1,-1));
        tmp3.reparar(new Posicion(-1,-1));
        tmp4.reparar(new Posicion(-1,-1));

        Assert.assertEquals(tmp2.estadoPos(new Posicion(-1,-1)),"normal");
        Assert.assertEquals(tmp3.estadoPos(new Posicion(-1,-1)),"normal");
        Assert.assertEquals(tmp4.estadoPos(new Posicion(-1,-1)),"normal");
    }

    @Test
    public void testContiene() throws Exception {
        Assert.assertTrue(tmp1.contiene(new Posicion(-1,-1)));
        Assert.assertTrue(tmp2.contiene(new Posicion(-1,-1)));
        Assert.assertTrue(tmp3.contiene(new Posicion(-1,-1)));
        Assert.assertTrue(tmp4.contiene(new Posicion(-1,-1)));
        Assert.assertFalse(tmp1.contiene(new Posicion(0,0)));
        Assert.assertFalse(tmp2.contiene(new Posicion(0,0)));
        Assert.assertFalse(tmp3.contiene(new Posicion(0,0)));
        Assert.assertFalse(tmp4.contiene(new Posicion(0,0)));
    }

    @Test
    public void testHundirSetEscudoTieneEscudo() throws Exception {
        tmp1.setEscudo();
        tmp2.setEscudo();
        tmp3.setEscudo();
        tmp4.setEscudo();
        Assert.assertTrue(tmp1.tieneEscudo());
        Assert.assertTrue(tmp2.tieneEscudo());
        Assert.assertTrue(tmp3.tieneEscudo());
        Assert.assertTrue(tmp4.tieneEscudo());
        tmp1.hundir();
        tmp2.hundir();
        tmp3.hundir();
        tmp4.hundir();
        Assert.assertFalse(tmp1.hundido());
        Assert.assertFalse(tmp2.hundido());
        Assert.assertFalse(tmp3.hundido());
        Assert.assertFalse(tmp4.hundido());
        tmp1.hundir();
        tmp2.hundir();
        tmp3.hundir();
        tmp4.hundir();
        Assert.assertTrue(tmp1.hundido());
        Assert.assertTrue(tmp2.hundido());
        Assert.assertTrue(tmp3.hundido());
        Assert.assertTrue(tmp4.hundido());
        Assert.assertEquals(tmp1.estadoPos(new Posicion(-1,-1)),"tocado");
        Assert.assertEquals(tmp2.estadoPos(new Posicion(-1,-1)),"tocado");
        Assert.assertEquals(tmp3.estadoPos(new Posicion(-1,-1)),"tocado");
        Assert.assertEquals(tmp4.estadoPos(new Posicion(-1,-1)),"tocado");
    }


    /**
     * los método relacionados con varias clases los testeamos
     * de manera manual aqui solo aparecen test de métodos finales
     * que devuelven un valor
     */
}
