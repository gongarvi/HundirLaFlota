package Modelo;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ArmaTest {

    /**
     * test de todas las constructoras de Arma
     */
    Arma tmp1;
    Arma tmp2;
    Arma tmp3;
    Arma tmp4;
    Arma tmp5;

    @BeforeMethod
    public void init() {
        tmp1 = new Bomba();
        tmp2 = new Misil();
        tmp3 = new MisilEO();
        tmp4 = new MisilNS();
        tmp5 = new MisilBoom();
    }

    @Test
    public void testCostructoras() throws Exception {
        Assert.assertNotNull(tmp1);
        Assert.assertNotNull(tmp2);
        Assert.assertNotNull(tmp3);
        Assert.assertNotNull(tmp4);
        Assert.assertNotNull(tmp5);
    }

}
