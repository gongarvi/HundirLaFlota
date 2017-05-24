package Modelo;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class IATest {
    private  IA tmp;

    @BeforeMethod
    public void init() throws Exception {
        tmp=new IA();
        Battleship.getMyBattleship().pruebas();
    }

    @Test
    public void testConstructora() throws Exception {
        Assert.assertNotNull(tmp);
    }

    @Test
    public void testMetodosResolucionMovimientos() throws Exception {
        Posicion normalIA=null;
        Posicion normalHumano = null;
        boolean posResuelta=false;
        int i=0;
        int j=0;
        Assert.assertTrue(tmp.resolverSigPosRadar().equals(new Posicion(8,8)));
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
        Assert.assertTrue(tmp.posAliada().equals(normalIA));
        Battleship.getMyBattleship().cambiarTurno();
        Tablero.getMiTablero().impactar(normalIA);
        posResuelta=false;
         i=0;
         j=0;
        while(i<Battleship.getMyBattleship().maxFila()&&!posResuelta){
            while (j<Battleship.getMyBattleship().maxCol()&&!posResuelta){
                Posicion act=new Posicion(i,j);
                String estadoEnemigo=Tablero.getMiTablero().estadoCampoContrario(act);
                if(estadoEnemigo!=null && estadoEnemigo.equals("normal")){
                    normalHumano=act;
                    posResuelta=true;
                }else{
                    j++;
                }
            }
            j=0;
            i++;
        }
        tmp.addPosRevisada(normalHumano);
        Assert.assertTrue(tmp.resolverSigPosDisparar().equals(normalHumano));
        tmp.comprar();
        Assert.assertTrue(tmp.existeArma("misil"));

    }


    /**
     * los método relacionados con varias clases los testeamos
     * de manera manual aqui solo aparecen test de métodos finales
     * que devuelven un valor
     */
}
