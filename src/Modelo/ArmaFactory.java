package Modelo;

public class ArmaFactory {
    /**
     * atributos de la factoria de armas
     */
    private static ArmaFactory miArmaFactory;

    /**
     * constructora privada de la factoria de armas
     */
    private ArmaFactory() {
    }

    /**
     * método que devuelve la única instancia de  la factoria
     * @return
     */
    public static ArmaFactory getArmaFactory() {
        if (miArmaFactory == null) {
            miArmaFactory = new ArmaFactory();
        }
        return miArmaFactory;
    }

    /**
     * método para crear armas
     * @param pTipoArma
     * @return
     */
    public Arma crearArma(String pTipoArma) {
        Arma a = null;
        if (pTipoArma.equalsIgnoreCase("bomba")) {
            a = new Bomba();
        } else if (pTipoArma.equalsIgnoreCase("misil")) {
            a = new Misil();
        } else if (pTipoArma.equalsIgnoreCase("misilEO")) {
            a = new MisilEO();
        } else if (pTipoArma.equalsIgnoreCase("misilNS")) {
            a =  new MisilNS();
        } else if (pTipoArma.equalsIgnoreCase("misilBoom")) {
            a = new MisilBoom();
        }
        return a;
    }

}