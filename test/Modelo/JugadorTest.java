package Modelo;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.swing.*;

/**
 * Created by Edgar on 15/05/2017.
 */
public class JugadorTest {
    private Jugador tmp;

    @BeforeMethod
    public void init() throws Exception {
        tmp=new Humano("edgar");
        Battleship.getMyBattleship().masPruebas();
    }

    @Test
    public void testConstructora() throws Exception {
        Assert.assertNotNull(tmp);
    }

    @Test
    public void testGetNombre() throws Exception {
        Assert.assertTrue(tmp.getDinero()==1000);
    }

    @Test
    public void testTodoRelacionadoConArmas() throws Exception {
        Assert.assertFalse(tmp.existeArma("bomba"));
        Assert.assertFalse(tmp.existeArma("misil"));
        Assert.assertFalse(tmp.existeArma("misilEO"));
        Assert.assertFalse(tmp.existeArma("misilNS"));
        Assert.assertFalse(tmp.existeArma("misilBoom"));
        tmp.comprarArma("bomba");
        Assert.assertTrue(tmp.getDinero()==Battleship.getMyBattleship().getDineroInicial()-Battleship.getMyBattleship().getPrecioBomba());
        tmp.comprarArma("misil");
        Assert.assertTrue(tmp.getDinero()==Battleship.getMyBattleship().getDineroInicial()-Battleship.getMyBattleship().getPrecioMisil()-Battleship.getMyBattleship().getPrecioBomba());
        tmp.comprarArma("misilEO");
        Assert.assertTrue(tmp.getDinero()==Battleship.getMyBattleship().getDineroInicial()-Battleship.getMyBattleship().getPrecioMisilEO()-Battleship.getMyBattleship().getPrecioMisil()-Battleship.getMyBattleship().getPrecioBomba());
        tmp.comprarArma("misilNS");
        Assert.assertTrue(tmp.getDinero()==Battleship.getMyBattleship().getDineroInicial()-Battleship.getMyBattleship().getPrecioMisilNS()-Battleship.getMyBattleship().getPrecioMisilEO()-Battleship.getMyBattleship().getPrecioMisil()-Battleship.getMyBattleship().getPrecioBomba());
        tmp.comprarArma("misilBoom");
        Assert.assertTrue(tmp.getDinero()==Battleship.getMyBattleship().getDineroInicial()-Battleship.getMyBattleship().getPrecioMisilBOOM()-Battleship.getMyBattleship().getPrecioMisilNS()-Battleship.getMyBattleship().getPrecioMisilEO()-Battleship.getMyBattleship().getPrecioMisil()-Battleship.getMyBattleship().getPrecioBomba());
        Assert.assertTrue(tmp.existeArma("bomba"));
        Assert.assertTrue(tmp.existeArma("misil"));
        Assert.assertTrue(tmp.existeArma("misilEO"));
        Assert.assertTrue(tmp.existeArma("misilNS"));
        Assert.assertTrue(tmp.existeArma("misilBoom"));
        tmp.dispararArma("bomba",new Posicion(0,0));
        tmp.dispararArma("misil",new Posicion(0,0));
        tmp.dispararArma("misilEO",new Posicion(0,0));
        tmp.dispararArma("misilNS",new Posicion(0,0));
        tmp.dispararArma("misilBoom",new Posicion(0,0));
        Assert.assertFalse(tmp.existeArma("bomba"));
        Assert.assertFalse(tmp.existeArma("misil"));
        Assert.assertFalse(tmp.existeArma("misilEO"));
        Assert.assertFalse(tmp.existeArma("misilNS"));
        Assert.assertFalse(tmp.existeArma("misilBoom"));
    }

    @Test
    public void testUsosRadar() throws Exception {
        Assert.assertTrue(tmp.usosRadar()==Battleship.getMyBattleship().getMaxUsosRadar());
    }

    @Test
    public void testHaGanado() throws Exception {
        Assert.assertFalse(tmp.haGanado());
        for(int i=0;i<Battleship.getMyBattleship().maxFila();i++){
            for(int j=0;j<Battleship.getMyBattleship().maxCol();j++){
                new Bomba().disparar(new Posicion(i,j));
            }
        }
        Assert.assertTrue(tmp.haGanado());
    }

    /**
     * los método relacionados con varias clases los testeamos
     * de manera manual aqui solo aparecen test de métodos finales
     * que devuelven un valor
     */
}
