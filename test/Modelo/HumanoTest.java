package Modelo;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class HumanoTest {
    private Humano tmp;

    @BeforeMethod
    public void init() throws Exception {
        tmp=new Humano("edgar");
        Battleship.getMyBattleship().pruebas();
    }

    @Test
    public void testConstructora() throws Exception {
        Assert.assertNotNull(tmp);
    }

    @Test
    public void testGetNombre() throws Exception {
       Assert.assertTrue(tmp.getNombre().equals("edgar"));
    }

    @Test
    public void testQuedanBarcos() throws Exception {
        Assert.assertFalse(tmp.quedanBarcos());
    }

    /**
     * los método relacionados con varias clases los testeamos
     * de manera manual aqui solo aparecen test de métodos finales
     * que devuelven un valor
     */
}
