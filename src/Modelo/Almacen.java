package Modelo;

public class Almacen {

    /**
     * varibles Almacen
     * @param args
     */
    private static Almacen miAlmacen;
    private ListaArmas existencias;

    /**
     * constructora privada para aplicar el patrón Singleton
     */
    private Almacen() {
            existencias = new ListaArmas();
             inicializarAlmacen();
            System.out.println("inicializa almacen");
    }

    /**
     * inicializar el almacen con el número de armas especificadas en Battleship
     *
     */
    public void inicializarAlmacen() {
        while (existencias.getSize("bomba") < Battleship.getMyBattleship().getnBombas()) {
            existencias.añadirArma("bomba",ArmaFactory.getArmaFactory().crearArma("bomba"));
        }

        while (existencias.getSize("misil")< Battleship.getMyBattleship().getnMisiles()) {
            existencias.añadirArma("misil",ArmaFactory.getArmaFactory().crearArma("misil"));
        }

        while (existencias.getSize("misilNS") <Battleship.getMyBattleship().getnMisilesNS()) {
            existencias.añadirArma("misilNS",ArmaFactory.getArmaFactory().crearArma("misilNS"));
        }

        while (existencias.getSize("misilBoom") <Battleship.getMyBattleship().getnMisilesBoom()) {
            existencias.añadirArma("misilBoom",ArmaFactory.getArmaFactory().crearArma("misilBoom"));
        }

        while (existencias.getSize("misilEO") <Battleship.getMyBattleship().getnMisilesEO()) {
           existencias.añadirArma("misilEO",ArmaFactory.getArmaFactory().crearArma("misilEO"));
        }


    }

    /**
     * getter de la única instancia de almacen
     * @return
     */
    public static Almacen getMiAlmacen() {
            if (miAlmacen == null) {
                miAlmacen = new Almacen();
            }
            return miAlmacen;
        }

    /**
     * método que devuelve el arma que se desea comprar
     * @param pArma
     * @return
     */
    public Arma comprarArma(String pArma) {
        if (existeArma(pArma)) {
            return this.existencias.removeArma(pArma);
        }

        return null;
    }

    /**
     * método que comprueba si todavia quedan armas de el tipo especificado
     * @param pArma
     * @return
     */
    private boolean existeArma(String pArma) {
            return existencias.consultarArma(pArma) ;

        }

    /**
     * devuelve el precio del arma especificada
     * @param pArma
     * @return
     */
    public int getPrecioArma(String pArma) {
            return this.existencias.getPrecioArma(pArma);
        }

}


