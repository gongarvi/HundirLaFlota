package Modelo;


import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ListaPosicionesTest {
    private  ListaPosiciones LP;
    private  ListaPosiciones lf;
    private  ListaPosiciones ld;
    private  ListaPosiciones ls;
    private  ListaPosiciones lp;

    @BeforeMethod
    public void init() throws Exception {
        LP=new ListaPosiciones();
        lp=new ListaPosiciones("portaaviones");
        lf=new ListaPosiciones("fragata");
        ld=new ListaPosiciones("destructor");
        ls=new ListaPosiciones("submarino");
    }

    @Test
    public void testConstructora() throws Exception {
        Assert.assertNotNull(LP);
        Assert.assertNotNull(lp);
        Assert.assertNotNull(lf);
        Assert.assertNotNull(ld);
        Assert.assertNotNull(ls);
    }

    @Test
    public void testFuncionesListaPosiciones() throws Exception {
        Assert.assertTrue(LP.contieneNormal()==null);
        Assert.assertTrue(lp.contieneNormal()!=null);
        Assert.assertTrue(lf.contieneNormal()!=null);
        Assert.assertTrue(ld.contieneNormal()!=null);
        Assert.assertTrue(ls.contieneNormal()!=null);
        Assert.assertTrue(lp.size()==4);
        Assert.assertTrue(lf.size()==1);
        Assert.assertTrue(ld.size()==2);
        Assert.assertTrue(ls.size()==3);
        Assert.assertTrue(lp.noInicializado());
        Assert.assertTrue(lf.noInicializado());
        Assert.assertTrue(ld.noInicializado());
        Assert.assertTrue(ls.noInicializado());
        int[]pivote=new int[2];
        pivote[0]=0;
        pivote[1]=0;
        lp.inicializar(pivote,"H");
        lf.inicializar(pivote,"H");
        ld.inicializar(pivote,"H");
        ls.inicializar(pivote,"H");
        Assert.assertFalse(lp.noInicializado());
        Assert.assertFalse(lf.noInicializado());
        Assert.assertFalse(ld.noInicializado());
        Assert.assertFalse(ls.noInicializado());
        lp.recibirDanios(new Posicion(0,2));
        Assert.assertTrue( lp.estadoPos(new Posicion(0,2)).equals("tocado"));
        lp.reparar(new Posicion(0,2));
        Assert.assertTrue( lp.estadoPos(new Posicion(0,2)).equals("normal"));
        lp.hundir();
        Assert.assertTrue(lp.contieneTocadoNoHundido()==null);
        Assert.assertTrue(lp.estaHundido());
        Assert.assertTrue(lp.contiene(new Posicion(0,0)));
        Assert.assertFalse(lp.contiene(new Posicion(-1,-1)));
    }


    /**
     * los método relacionados con varias clases los testeamos
     * de manera manual aqui solo aparecen test de métodos finales
     * que devuelven un valor
     */
}
