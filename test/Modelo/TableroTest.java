package Modelo;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TableroTest {

    @BeforeMethod
    public void init(){
        Battleship.getMyBattleship().pruebas();
    }

    @Test
    public void testAccionesDeTablero() throws Exception {
        Posicion normalIA=null;
        boolean posResuelta=false;
        int i=0;
        int j=0;
        if(Battleship.getMyBattleship().turnoAct()==0) {
            Battleship.getMyBattleship().cambiarTurno();
        }
        while(i<Battleship.getMyBattleship().maxFila()&&!posResuelta){
            while (j<Battleship.getMyBattleship().maxCol()&&!posResuelta){
                Posicion act=new Posicion(i,j);
                String estadoAliado=Tablero.getMiTablero().estadoCampoAliado(act);
                if(estadoAliado!=null && estadoAliado.equals("normal")){
                    normalIA=act;
                    posResuelta=true;
                }else{
                    j++;
                }
            }
            j=0;
            i++;
        }
        Assert.assertTrue(Tablero.getMiTablero().estadoCampoAliado(normalIA).equals("normal"));

        Battleship.getMyBattleship().cambiarTurno();//humano

        Assert.assertTrue(Tablero.getMiTablero().estadoCampoContrario(normalIA).equals("normal"));
        Tablero.getMiTablero().impactar(normalIA);
       Assert.assertTrue(Tablero.getMiTablero().estadoCampoContrario(normalIA).equals("tocado"));

        Battleship.getMyBattleship().cambiarTurno();//ia

        Assert.assertTrue(Tablero.getMiTablero().estadoCampoAliado(normalIA).equals("tocado"));
        Tablero.getMiTablero().reparar(normalIA);
        Assert.assertTrue(Tablero.getMiTablero().estadoCampoAliado(normalIA).equals("normal"));
        Tablero.getMiTablero().setEscudo(normalIA);
        Assert.assertTrue(Tablero.getMiTablero().escudoAliado(normalIA));

        Battleship.getMyBattleship().cambiarTurno();//humano

        Assert.assertTrue(Tablero.getMiTablero().escudoEnemigo(normalIA));
        Tablero.getMiTablero().hundir(normalIA);
        Assert.assertFalse(Tablero.getMiTablero().escudoEnemigo(normalIA));

        Battleship.getMyBattleship().cambiarTurno();//ia

        Assert.assertFalse(Tablero.getMiTablero().escudoAliado(normalIA));

        Battleship.getMyBattleship().cambiarTurno();//humano

        Tablero.getMiTablero().hundir(normalIA);
        Tablero.getMiTablero().barcoHundidoEnemigo(normalIA);

        Battleship.getMyBattleship().cambiarTurno();//ia

        Tablero.getMiTablero().barcoHundidoAliado(normalIA);
    }

    @Test
    public void testConstructora() throws Exception {
        Assert.assertNotNull(Tablero.getMiTablero());
    }

    /**
     * los método relacionados con varias clases los testeamos
     * de manera manual aqui solo aparecen test de métodos finales
     * que devuelven un valor
     */
}
