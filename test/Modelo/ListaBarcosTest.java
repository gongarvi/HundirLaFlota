package Modelo;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ListaBarcosTest {

    private  ListaBarcos lb;

    @BeforeMethod
    public void init() throws Exception {

        Battleship.getMyBattleship().pruebas();
       lb=new ListaBarcos();
       lb.inicializarAuto("destructor");
       lb.inicializarAuto("submarino");
       lb.inicializarAuto("portaaviones");
       lb.inicializarAuto("fragata");
    }

    @Test
    public void testConstructora() throws Exception {
        Assert.assertNotNull(lb);
    }

    @Test
    public void testFuncionesListaBarcos() throws Exception {
        Posicion normal=null;
        boolean posResuelta=false;
        int i=0;
        int j=0;
        while(i<Battleship.getMyBattleship().maxFila()&&!posResuelta){
            while (j<Battleship.getMyBattleship().maxCol()&&!posResuelta){
                Posicion act=new Posicion(i,j);
                String estadoAliado=lb.estadoPos(act);
                if(estadoAliado!=null && estadoAliado.equals("normal")){
                    normal=act;
                    posResuelta=true;
                }else{
                    j++;
                }
            }
            j=0;
            i++;
        }
        lb.impactar(normal);
        Assert.assertTrue(lb.estadoPos(normal).equals("tocado"));
        lb.reparar(normal);
        Assert.assertTrue(lb.estadoPos(normal).equals("normal"));
        Assert.assertFalse(lb.tieneEscudo(normal));
        Assert.assertFalse(lb.barcoHundido(normal));
        lb.setEscudo(normal);
        Assert.assertTrue(lb.tieneEscudo(normal));
        lb.hundir(normal);
        Assert.assertFalse(lb.tieneEscudo(normal));
        lb.hundir(normal);
        Assert.assertTrue(lb.barcoHundido(normal));
        Assert.assertFalse(lb.flotaHundida());
    }

    /**
     * los método relacionados con varias clases los testeamos
     * de manera manual aqui solo aparecen test de métodos finales
     * que devuelven un valor
     */
}
