package Modelo;
import Controlador.ControladorTablero;

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
    }

    /**
     * reinicia el almacen
     *
     */
    public void reiniciarAlmacen() {
        this.inicializarAlmacen();
    }

    /**
     * inicializar el almacen con el número de armas especificadas en Battleship
     *
     */
    public void inicializarAlmacen() {
        while (cuantasBombasHay() < Battleship.getMyBattleship().getnBombas()) {
            existencias.añadirArma("bomba");
        }

        while (cuantosMisilesHay()< Battleship.getMyBattleship().getnMisiles()) {
            existencias.añadirArma("misil");
        }

        while (cuantosMisilesNSHay() <Battleship.getMyBattleship().getnMisilesNS()) {
            existencias.añadirArma("misilNS");
        }

        while (cuantosMisilesBoomHay() <Battleship.getMyBattleship().getnMisilesBoom()) {
            existencias.añadirArma("misilBoom");
        }

        while (cuantosMisilesEOHay() <Battleship.getMyBattleship().getnMisilesEO()) {
           existencias.añadirArma("misilEO");
        }


    }

    /**
     * setea las opciones con el precio de cada arma
     */
    public void precioArmas(){
        if(Battleship.getMyBattleship().getTipoVista().equals("consola")) {
            System.out.println(
                    "bombas: " +Battleship.getMyBattleship(). getPrecioBomba() +
                            "\nmisil: " +Battleship.getMyBattleship(). getPrecioMisil() +
                            "\nmisilBoom: " + Battleship.getMyBattleship().getPrecioMisilBOOM()+
                            "\nmisilNS: " +Battleship.getMyBattleship(). getPrecioMisilNS()+
                            "\nmisilEO: " +Battleship.getMyBattleship(). getPrecioMisilEO()
            );
        }else{
            ControladorTablero.getController().setOpcion("precioBomba", String.valueOf(Battleship.getMyBattleship().getPrecioBomba() ));
            ControladorTablero.getController().setOpcion("precioMisil",String.valueOf( Battleship.getMyBattleship().getPrecioMisil()));
            ControladorTablero.getController().setOpcion("precioMisilNS",String.valueOf( Battleship.getMyBattleship(). getPrecioMisilNS()));
            ControladorTablero.getController().setOpcion("precioMisilesEO",String.valueOf(Battleship.getMyBattleship().getPrecioMisilEO()));
            ControladorTablero.getController().setOpcion("precioMisilesBoom",String.valueOf(Battleship.getMyBattleship().getPrecioMisilBOOM()));
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
     * método que devuelve el número de misilesBoom que quedan en el almacen
     */
    public int cuantosMisilesBoomHay(){
       return existencias.getSize("misilBoom");
    }

    /**
     * método que devuelve el número de misilesEO que quedan en el almacen
     */
    public int cuantosMisilesEOHay(){
       return existencias.getSize("misilEO");
    }

    /**
     * método que devuelve el número de misilesNS que quedan en el almacen
     */
    public int cuantosMisilesNSHay(){
        return existencias.getSize("misilNS");
    }

    /**
     * método que devuelve el número de misiles que quedan en el almacen
     */
    public int cuantosMisilesHay(){
       return existencias.getSize("misil");
    }

    /**
     * método que devuelve el número de bombas que quedan en el almacen
     */
    public int cuantasBombasHay(){
       return existencias.getSize("bomba");
    }
    /**
     * método que muestra el numero de armas que quedan de cada tipo (GUI interna/externa)
     */
    public void cuantasArmasHay(){
        if(Battleship.getMyBattleship().getTipoVista().equals("consola")) {
            System.out.println(
                    "bombas: " + existencias.getSize("bomba") +
                            "\nmisil: " + existencias.getSize("misil") +
                            "\nmisilBoom: " + existencias.getSize("misilBoom") +
                            "\nmisilNS: " + existencias.getSize("misilNS") +
                            "\nmisilEO: " + existencias.getSize("misilEO")
            );
        }else{
            ControladorTablero.getController().setOpcion("bombasAlmacen", String.valueOf(cuantasBombasHay()));
            ControladorTablero.getController().setOpcion("misilesAlmacen",String.valueOf( cuantosMisilesHay()));
            ControladorTablero.getController().setOpcion("misilesNSAlmacen",String.valueOf(cuantosMisilesNSHay()));
            ControladorTablero.getController().setOpcion("misilesEOAlmacen",String.valueOf(cuantosMisilesEOHay()));
            ControladorTablero.getController().setOpcion("misilesBoomAlmacen",String.valueOf(cuantosMisilesBoomHay()));
        }
    }
    /**
     * método que comprueba si todavia quedan armas de el tipo especificado
     * @param pArma
     * @return
     */
    public boolean existeArma(String pArma) {
            return existencias.consultarArma(pArma) ;
    }
}


