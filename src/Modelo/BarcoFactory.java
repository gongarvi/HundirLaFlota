package Modelo;

public class BarcoFactory {
    /**
     * atributos de la factoria de barcos
     */
    private static BarcoFactory miBarcoFactory;

    /**
     * contructora privada de la factoria de barcos (patron Singleton)
     */
    private BarcoFactory() {
    }

    /**
     * devuelve la única instancia de la factoria de barcos
     * @return
     */
    public static BarcoFactory getBarcoFactory() {
        if (miBarcoFactory == null) {
            miBarcoFactory = new BarcoFactory();
        }
        return miBarcoFactory;
    }

    /**
     * método que generea barcos en funcion del tipo especificado
     * @param pTipoBarco
     * @return
     */
    public Barco crearBarco(String pTipoBarco) {

        Barco b = null;
        if (pTipoBarco.equalsIgnoreCase("portaaviones")) {
            b = new Portaaviones();
        } else if (pTipoBarco.equalsIgnoreCase("submarino")) {
            b = new Submarino();
        } else if (pTipoBarco.equalsIgnoreCase("destructor")) {
            b = new Destructor();
        } else if (pTipoBarco.equalsIgnoreCase("fragata")) {
            b = new Fragata();
        }
        return b;
    }
}