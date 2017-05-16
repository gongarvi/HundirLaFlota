package Modelo;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AlmacenTest {

    @Test
    public void testGetMiAlmacen() throws Exception {
        Assert.assertNotNull(Almacen.getMiAlmacen());
    }

    @Test
    public void testComprarArma() throws Exception {
        Assert.assertNotNull(Almacen.getMiAlmacen().comprarArma("bomba"));
        Assert.assertTrue(Almacen.getMiAlmacen().comprarArma("bomba") instanceof  Bomba);
        Assert.assertNotNull(Almacen.getMiAlmacen().comprarArma("misil"));
        Assert.assertTrue(Almacen.getMiAlmacen().comprarArma("misil") instanceof  Misil);
        Assert.assertNotNull(Almacen.getMiAlmacen().comprarArma("misilEO"));
        Assert.assertTrue(Almacen.getMiAlmacen().comprarArma("misilEO") instanceof  MisilEO);
        Assert.assertNotNull(Almacen.getMiAlmacen().comprarArma("misilNS"));
        Assert.assertTrue(Almacen.getMiAlmacen().comprarArma("misilNS") instanceof  MisilNS);
        Assert.assertNotNull(Almacen.getMiAlmacen().comprarArma("misilBoom"));
        Assert.assertTrue(Almacen.getMiAlmacen().comprarArma("misilBoom") instanceof  MisilBoom);
    }

    @Test
    public void testExisteArma() throws Exception {
        Assert.assertTrue(Almacen.getMiAlmacen().existeArma("bomba"));
        Assert.assertTrue(Almacen.getMiAlmacen().existeArma("misil") );
        Assert.assertTrue(Almacen.getMiAlmacen().existeArma("misilEO"));
        Assert.assertTrue(Almacen.getMiAlmacen().existeArma("misilNS"));
        Assert.assertTrue(Almacen.getMiAlmacen().existeArma("misilBoom"));
    }

    @Test
    public void testGetPrecioArma() throws Exception {
        Assert.assertEquals(Battleship.getMyBattleship().getPrecioArma("bomba"), Battleship.getMyBattleship().getPrecioBomba());
        Assert.assertEquals(Battleship.getMyBattleship().getPrecioArma("misil"), Battleship.getMyBattleship().getPrecioMisil());
        Assert.assertEquals(Battleship.getMyBattleship().getPrecioArma("misilNS"), Battleship.getMyBattleship().getPrecioMisilNS());
        Assert.assertEquals(Battleship.getMyBattleship().getPrecioArma("misilEO"), Battleship.getMyBattleship().getPrecioMisilEO());
        Assert.assertEquals(Battleship.getMyBattleship().getPrecioArma("misilBoom"), Battleship.getMyBattleship().getPrecioMisilBOOM());
    }

    @Test
    public void TestcuantosMisilesBoomHay(){
        Almacen.getMiAlmacen().inicializarAlmacen();
        Assert.assertTrue( Almacen.getMiAlmacen().cuantosMisilesBoomHay()==Battleship.getMyBattleship().getnMisilesBoom());
    }

    @Test
    public void cuantosMisilesEOHay(){
        Almacen.getMiAlmacen().inicializarAlmacen();
        Assert.assertTrue( Almacen.getMiAlmacen().cuantosMisilesEOHay()==Battleship.getMyBattleship().getnMisilesEO());
    }

    @Test
    public void cuantosMisilesNSHay(){
        Almacen.getMiAlmacen().inicializarAlmacen();
        Assert.assertTrue( Almacen.getMiAlmacen().cuantosMisilesNSHay()==Battleship.getMyBattleship().getnMisilesNS());
    }

    @Test
    public void cuantosMisilesHay(){
        Assert.assertTrue( Almacen.getMiAlmacen().cuantosMisilesHay()==Battleship.getMyBattleship().getnMisiles());
    }

    @Test
    public void cuantasBombasHay() {
        Assert.assertTrue( Almacen.getMiAlmacen().cuantasBombasHay()==Battleship.getMyBattleship().getnBombas());
    }


    /**
     * los método relacionados con varias clases los testeamos
     * de manera manual aqui solo aparecen test de métodos finales
     * que devuelven un valor
     */
}
