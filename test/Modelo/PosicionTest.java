package Modelo;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Created by Edgar on 16/05/2017.
 */
public class PosicionTest {
    Posicion pos;

    @BeforeMethod
    public void init(){
        pos=new Posicion(1,1);
        pos.setState(new SNormal());
    }

    @Test
    public void testConstructora() throws Exception {
        Assert.assertNotNull(pos);
    }

    @Test
    public void testEstadoYCambiosDeEstado() throws Exception {
        Assert.assertTrue (pos.getEstado() instanceof SNormal);
        pos.reparar();
        Assert.assertTrue (pos.getEstado() instanceof SNormal);
        pos.tocar();
        Assert.assertTrue (pos.getEstado() instanceof STocado);
        pos.tocar();
        Assert.assertTrue (pos.getEstado() instanceof STocado);
        pos.reparar();
        Assert.assertTrue (pos.getEstado() instanceof SNormal);
    }

    @Test
    public void testGetXYGetY() throws Exception {
        Assert.assertTrue (pos.getX()==1);
        Assert.assertTrue (pos.getY()==1);
        int[] pivote=new int[2];
        pivote[0]= 0;
        pivote[1]=0;
        pos.setPosicion(pivote);
        Assert.assertTrue (pos.getX()==0);
        Assert.assertTrue (pos.getY()==0);
    }

    @Test
    public void testEquals() throws Exception {
       Assert.assertTrue(pos.equals(new Posicion(1,1)));
    }

    /**
     * los método relacionados con varias clases los testeamos
     * de manera manual aqui solo aparecen test de métodos finales
     * que devuelven un valor
     */
}
