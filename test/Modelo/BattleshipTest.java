package Modelo;

import org.testng.Assert;
import org.testng.annotations.Test;

public class BattleshipTest {

    @Test
    public void testGetBattleship() throws Exception {
        Assert.assertNotNull(Battleship.getMyBattleship());
    }

    @Test
    public void testGeters() throws Exception {
        Assert.assertEquals(Battleship.getMyBattleship().turnoAct(),1);
        Assert.assertEquals(Battleship.getMyBattleship().getDineroInicial(),1000);
        Assert.assertEquals(Battleship.getMyBattleship().getFaseCompraYEscudo(),2);
        Assert.assertEquals(Battleship.getMyBattleship().getFaseDisparo(),3);
        Assert.assertEquals(Battleship.getMyBattleship().getFaseInicial(),0);
        Assert.assertEquals(Battleship.getMyBattleship().getFaseRadar(),1);
        Assert.assertEquals(Battleship.getMyBattleship().getFaseTurnoIA(),4);
        Assert.assertEquals(Battleship.getMyBattleship().getFaseReparacion(),5);
        Assert.assertEquals(Battleship.getMyBattleship().getFaseAct(),0);
        Assert.assertEquals(Battleship.getMyBattleship().getLength("fragata"),1);
        Assert.assertEquals(Battleship.getMyBattleship().getLength("destructor"),2);
        Assert.assertEquals(Battleship.getMyBattleship().getLength("submarino"),3);
        Assert.assertEquals(Battleship.getMyBattleship().getLength("portaaviones"),4);
        Assert.assertEquals(Battleship.getMyBattleship().getMax("fragata"),4);
        Assert.assertEquals(Battleship.getMyBattleship().getMax("destructor"),3);
        Assert.assertEquals(Battleship.getMyBattleship().getMax("submarino"),2);
        Assert.assertEquals(Battleship.getMyBattleship().getMax("portaaviones"),1);
        Assert.assertEquals(Battleship.getMyBattleship().getPrecioBomba(),0);
        Assert.assertEquals(Battleship.getMyBattleship().getPrecioEscudo(),75);
        Assert.assertEquals(Battleship.getMyBattleship().getPrecioMisil(),25);
        Assert.assertEquals(Battleship.getMyBattleship().getPrecioMisilBOOM(),200);
        Assert.assertEquals(Battleship.getMyBattleship().getPrecioMisilNS(),100);
        Assert.assertEquals(Battleship.getMyBattleship().getPrecioMisilEO(),100);
        Assert.assertEquals(Battleship.getMyBattleship().getPrecioReparacion(),50);
        Assert.assertEquals(Battleship.getMyBattleship().maxFila(),10);
        Assert.assertEquals(Battleship.getMyBattleship().maxCol(),10);
        Assert.assertEquals(Battleship.getMyBattleship().getMaxUsosRadar(),3);
    }

    @Test
    public void testAddVistaGetVista() throws Exception {
        Battleship.getMyBattleship().addTipoVista("consola");
        Assert.assertEquals(Battleship.getMyBattleship().getTipoVista(),"consola");
    }

    @Test
    public void testCambiarTurno() throws Exception {
        Battleship.getMyBattleship().cambiarTurno();
        Assert.assertEquals(Battleship.getMyBattleship().turnoAct(),1);
    }


    /**
     * los método relacionados con varias clases los testeamos
     * de manera manual aqui solo aparecen test de métodos finales
     * que devuelven un valor
     */
}
