package Modelo;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class RadarTest {
    Radar radar;

    @BeforeMethod
    public void init(){
        radar=new Radar();
        Battleship.getMyBattleship().pruebas();
    }

    @Test
    public void testConstructora() throws Exception {
        Assert.assertNotNull(radar);
    }

    @Test
    public void testUsosRadarYusarlo() throws Exception {
      Assert.assertTrue(radar.usosRaar()==3);
      radar.colocarRadar(new Posicion(1,1));
      radar.colocarRadar(new Posicion(4,4));
      radar.colocarRadar(new Posicion(8,8));
      Assert.assertTrue(radar.usosRaar()==0);
    }

    /**
     * los método relacionados con varias clases los testeamos
     * de manera manual aqui solo aparecen test de métodos finales
     * que devuelven un valor
     */
}
