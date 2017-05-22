package Modelo;

import Controlador.ControladorTablero;

public abstract class Jugador {
    /**
     * atributos del jugador
     */
    private int dinero ;
    private ListaArmas lArmas;
    private Radar radar;

    /**
     * contructora de jugador, inicializa sus atributos
     */
    public Jugador() {
        Tablero.getMiTablero();
        dinero=Battleship.getMyBattleship().getDineroInicial();
        lArmas = new ListaArmas();
        radar = new Radar();
    }

    /**
     * método para reiniciar la lista de armas del jugador
     */
    public void vaciarListaArmas(){
        lArmas=new ListaArmas();
    }

    /**
     * método para reiniciar el radar del jugador
     */
    public void reiniciarRadar(){
        radar=new Radar();
    }

    /**
     * método que devuelve el dinero del jugador
     * @return
     */
    public int getDinero() {
        return dinero;
    }

    /**
     * método que devuelve true si existe un arma y false en caso contrario
     * @param pTipo
     * @return
     */
    public boolean existeArma(String pTipo){
        return  lArmas.getSize(pTipo)>0;
    }

    /**
     * método que muestra cuanto dinero queda (GUI interna/externa)
     */
    public void cuantoDineroHay(){
        if(Battleship.getMyBattleship().getTipoVista().equals("consola")) {
            System.out.println(
                    "dinero: " + getDinero()
            );
        }else{
            ControladorTablero.getController().setOpcion("dinero", String.valueOf( getDinero() ));

        }
    }

    /**
     * método que muestra el numero de armas que quedan de cada tipo (GUI interna/externa)
     */
    public void cuantasArmasHay(){
        if(Battleship.getMyBattleship().getTipoVista().equals("consola")) {
            System.out.println(
                    "bombas: " + lArmas.getSize("bomba") +
                            "\nmisil: " + lArmas.getSize("misil") +
                            "\nmisilBoom: " + lArmas.getSize("misilBoom") +
                            "\nmisilNS: " + lArmas.getSize("misilNS") +
                            "\nmisilEO: " + lArmas.getSize("misilEO")
            );
        }else{
            ControladorTablero.getController().setOpcion("bombas", String.valueOf(lArmas.getSize("bomba")));
            ControladorTablero.getController().setOpcion("misiles",String.valueOf( lArmas.getSize("misil")));
            ControladorTablero.getController().setOpcion("misilesNS",String.valueOf( lArmas.getSize("misilNS")));
            ControladorTablero.getController().setOpcion("misilesEO",String.valueOf(lArmas.getSize("misilEO")));
            ControladorTablero.getController().setOpcion("misilesBoom",String.valueOf(lArmas.getSize("misilBoom")));
        }
    }

    /**
     * método que decide si es posible decrementar el dinero dado como parametro
     * @param pCantidad
     * @return
     */
    public boolean decrementarDinero(int pCantidad) {
        if (dinero >= pCantidad) {
            dinero -= pCantidad;
            return true;
        }
        return false;
    }

    /**
     * método que compra un arma
     * @param pArma
     */
    public void comprarArma(String pArma) {
        if (decrementarDinero(Battleship.getMyBattleship().getPrecioArma(pArma))) {
            Arma aComprar=Almacen.getMiAlmacen().comprarArma(pArma);
            if(aComprar!=null) {
                lArmas.añadirArma(pArma);
            }else{
                if (Battleship.getMyBattleship().getTipoVista().equals("consola")){
                    System.out.println("No quedan existencias en el almacen");
                    Battleship.getMyBattleship().cancelarCompra(Battleship.getMyBattleship().getPrecioArma(pArma));
                }else{
                    ControladorTablero.getController().error("No quedan existencias en el almacen");
                    Battleship.getMyBattleship().cancelarCompra(Battleship.getMyBattleship().getPrecioArma(pArma));
                }
            }
        }else if (Battleship.getMyBattleship().getTipoVista().equals("consola")){
            System.out.println("No tienes dinero suficiente");
        }else{
            ControladorTablero.getController().error("No tienes dinero suficiente");
        }
    }
    /**
     * método abstracto colocarBarcos redefinido en clases  especificas
     */
    public abstract void colocarBarcos();

    /**
     * método que decide si el jugador que tiene el turno ha ganado la partida
     * @return
     */
    public boolean haGanado() {
        return Tablero.getMiTablero().haGanado();
    }
    /**
     * método abstracto usarRadar redefinido en clases  especificas
     */
    public abstract void usarRadar();
    /**
     * método abstracto comprar redefinido en clases  especificas
     */
    public abstract void comprar();

    /**
     * método abstracto disparar redefinido en clases  especificas
     */
    public abstract void disparar();

    /**
     * método que repara un barco seleccionado
     * @param pPos
     */
    public void repararBarco(Posicion pPos) {
        if (decrementarDinero(Battleship.getMyBattleship().getPrecioEscudo())) {
        Tablero.getMiTablero().reparar(pPos);
        }else if (Battleship.getMyBattleship().getTipoVista().equals("consola")){
            System.out.println("No tienes dinero suficiente");
        }else{
            ControladorTablero.getController().error("No tienes dinero suficiente");
        }
    }
    /**
     * método abstracto jugar turno redefinido en clases  especificas
     */
    public abstract void jugarTurno();

    /**
     * método que devuelve el numero de usos del radar
     * @return
     */
    public int usosRadar() {
      return radar.usosRaar();
    }

    /**
     * método que dispara el arma seleccionada en la posicion dada
     * @param pArma
     * @param pPos
     */
    public void dispararArma(String pArma, Posicion pPos) {
        Arma aDisparar=lArmas.removeArma(pArma);
        if(aDisparar!=null){
            aDisparar.disparar(pPos);
        }else if (Battleship.getMyBattleship().getTipoVista().equals("consola")){
            System.out.println("No tienes armas de ese tipo");
        }else{
            ControladorTablero.getController().error("No tienes armas de ese tipo");
        }
    }

    /**
     * método que muestra la posicion  dada como parametro
     * @param pPos
     */
    public void setPosicionRadar(Posicion pPos) {
        radar.colocarRadar( pPos);
    }

    /**
     * método que settea un escudo en la posicion seleccionada
     * @param pPosicion
     */
    public void setEscudo( Posicion pPosicion){
        if (decrementarDinero(Battleship.getMyBattleship().getPrecioEscudo())) {
             Tablero.getMiTablero().setEscudo(pPosicion);
        }else if (Battleship.getMyBattleship().getTipoVista().equals("consola")){
            System.out.println("No tienes dinero suficiente");
        }else{
            ControladorTablero.getController().error("No tienes dinero suficiente");
        }
    }
}