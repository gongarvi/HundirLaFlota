package Modelo;

import Controlador.ControladorTablero;

import java.util.Collection;

public class IA extends Jugador {

    /**
     * atributos
     */
    ListaPosiciones revisadas;

    /**
     * constructora de IA
     */
    public IA() {
        super();
        revisadas=new ListaPosiciones();
    }

    /**
     * método para reiniciar las posiciones revisadas de la IA
     */
    public void reiniciarRevisadas() {
        revisadas=new ListaPosiciones();
    }

    /**
     * metodo que muestra el Tablero del ordenador
     */
    public void mostrarFlotaOrdenador(){
        if(Battleship.getMyBattleship().getTipoVista().equals("consola")) {
            System.out.println("<implementado para pruebas>:\n");
            System.out.println("TableroVista ordenador:\n");
        }
        for(int i=0;i<Battleship.getMyBattleship().maxFila();i++) {
            String tablero ="";
            for (int j = 0; j < Battleship.getMyBattleship().maxCol(); j++) {
                Posicion act =new Posicion(i,j);
                boolean esc =Tablero.getMiTablero().escudoAliado(act);
                String estado=Tablero.getMiTablero().estadoCampoAliado(act);
                if(estado!=null && esc) {
                    if (estado.equals("normal")) {
                        if (Battleship.getMyBattleship().getTipoVista().equals("consola")) {
                            tablero += " BE ";
                        } else {
                            ControladorTablero.getController().setBotonEnemigo(i, j, "escudo");
                        }
                    } else if (estado.equals("tocado")) {
                        if (Battleship.getMyBattleship().getTipoVista().equals("consola")) {
                            tablero += " TE ";
                        } else {
                            ControladorTablero.getController().setBotonEnemigo(i, j, "escudo");
                        }
                    }
                }else if(estado!=null && !esc){
                if (estado.equals("normal")) {
                    if(Battleship.getMyBattleship().getTipoVista().equals("consola")) {
                        tablero += " B ";
                    }else{
                        ControladorTablero.getController().setBotonEnemigo(i,j,"normal");
                    }
                } else if (estado.equals("tocado")) {
                    if (Battleship.getMyBattleship().getTipoVista().equals("consola")) {
                        tablero += " T ";
                    } else {
                        ControladorTablero.getController().setBotonEnemigo(i, j, "tocado");
                    }
                }
                }else{
                    if(Battleship.getMyBattleship().getTipoVista().equals("consola")) {
                        tablero += " A ";
                    }else{
                        ControladorTablero.getController().setBotonEnemigo(i,j,"agua");
                    }
                }
            }
            if(Battleship.getMyBattleship().getTipoVista().equals("consola")) {
                System.out.println(tablero);
            }
        }
    }

    /**
     * se registra una posicion como revisada
     * @param pPos
     */
    public void addPosRevisada(Posicion pPos){revisadas.anadir(pPos);}

    /**
     * método jugar turno de la IA
     */
    public void jugarTurno() {
        reparar();
        usarRadar();
        comprar();
        colocarEscudos();
        disparar();
        /**
         * descomentar la linea de abajo para ver el campo enemigo en el campo enemigo
         */
       // mostrarFlotaOrdenador(); //dscomentar para ver cada movimiento del ordenador en el campo enemigo
        /**
         * descomentar la linea de arriba para ver el campo enemigo enel campo enemigo
         */
    }

    /**
     * metodo reparar de la IA
     */
    public void reparar(){
        Posicion pos=posDañadaAliada();
        if(getDinero()>Battleship.getMyBattleship().getPrecioReparacion() && pos !=null){
            repararBarco(pos);
        }
    }

    /**
     * resuleve la siguiente posicion que reparará la IA
     * @return
     */
    public Posicion posDañadaAliada(){
        Posicion tmp=null;
        boolean resueltaPos=false;
        int i=0,j=0;
        while(!resueltaPos && i<Battleship.getMyBattleship().maxFila()){
            while(!resueltaPos && j<Battleship.getMyBattleship().maxCol()){
                Posicion act =new Posicion(i,j);
                String estado=Tablero.getMiTablero().estadoCampoAliado(act);
                if(estado!=null && estado.equals("tocado") && !Tablero.getMiTablero().barcoHundidoAliado(act)){
                    resueltaPos=true;
                    tmp=act;
                }else{
                    j++;
                }
            }
            j=0;
            i++;
        }
        return  tmp;
    }

    /**
     * metodo resolver escudo  de la IA
     */
    public void colocarEscudos(){
        Posicion pos=posAliada();
        if(getDinero()>Battleship.getMyBattleship().getPrecioEscudo() && pos!=null){
            setEscudo(pos);
        }
    }

    /**
     * resuleve la siguiente posicion que reparará la IA
     * @return
     */
    public Posicion posAliada(){
        Posicion tmp=null;
        boolean resueltaPos=false;
        int i=0,j=0;
        while(!resueltaPos && i<Battleship.getMyBattleship().maxFila()){
            while(!resueltaPos && j<Battleship.getMyBattleship().maxCol()){
                Posicion act =new Posicion(i,j);
                String estado=Tablero.getMiTablero().estadoCampoAliado(act);
                if(estado!=null && estado.equals("normal")&& !Tablero.getMiTablero().escudoAliado(act)){
                    resueltaPos=true;
                    tmp=act;
                }else{
                    j++;
                }
            }
            j=0;
            i++;
        }
        return  tmp;
    }


    /**
     * metodo para inicializar los portasviones (pruebas)
     */
    public void colocarSoloPortaaviones() {
        Tablero.getMiTablero().inicializarAutoOrdenador("portaaviones");
    }

    /**
     * metodo para inicializar los barcos en las coordenadas deseadas
     */
	@Override
	public void colocarBarcos() {
            Tablero.getMiTablero().inicializarAutoOrdenador("fragata");
            Tablero.getMiTablero().inicializarAutoOrdenador("destructor");
            Tablero.getMiTablero().inicializarAutoOrdenador("submarino");
            Tablero.getMiTablero().inicializarAutoOrdenador("portaaviones");
    }

    /**
     * resuleve la siguiente posicion en la que la IA colocara el radar
     * @return
     */
    public Posicion resolverSigPosRadar(){
        //si la posicion se encuentra entre las revisadas
        Posicion tmp=null;
        boolean resueltaPos=false;
        int i = Battleship.getMyBattleship().maxFila()-2, j = Battleship.getMyBattleship().maxCol()-2;
        while (!resueltaPos && i>0) {
            while (!resueltaPos && j >0) {
                Posicion act = new Posicion(i, j);
                if (!revisadas.contieneColindantes(act)) {
                    //si la posicion no se encuentra entre las revisadas
                    tmp = act;
                    resueltaPos = true;
                } else {
                    j--;
                }
            }
            j = Battleship.getMyBattleship().maxCol()-2;
            i--;
        }
        return  tmp;
    }

    /**
     * resuleve la siguiente posicion en la que la IA disparara
     * @return
     */
    public Posicion resolverSigPosDisparar(){
        //si la posicion se encuentra entre las revisadas
        revisadas.revisarColindantesTocado();
        Posicion tmp=revisadas.contieneNormal();
        if(tmp==null) {
            boolean resueltaPos = false;
            int i = 0, j = 0;
            while (!resueltaPos && i < Battleship.getMyBattleship().maxFila()) {
                while (!resueltaPos && j < Battleship.getMyBattleship().maxCol()) {
                    Posicion act = new Posicion(i, j);
                    if (!revisadas.contiene(act)) {
                        //si la posicion no se encuentra entre las revisadas
                        tmp = act;
                        resueltaPos = true;
                    } else {
                        j++;
                    }
                }
                j = 0;
                i++;
            }
        }
        return  tmp;
    }

    /**
     * resuelve el arma con que atacar en funcion de la casilla a la que se ataque
     * @param pPos
     * @return
     */
    private String resolverArmaDisparar(Posicion pPos){
        String estadoTablero=Tablero.getMiTablero().estadoCampoContrario(pPos);
        if(estadoTablero!=null &&estadoTablero.equals("normal") &&existeArma("misil")){
            return "misil";
        }else{
            if(existeArma("misilBoom")){
                return "misilBoom";
            }else if( existeArma("misilEO")){
                return "misilEO";
            }else if( existeArma("misilNS")){
                return "misilNS";
            }else if( existeArma("bomba")) {
                return "bomba";
            }
        }
        return null;
    }
    /**
     * metodo para utilizar el radar
     */
    @Override
    public void usarRadar() {
        if (usosRadar() != 0){
            Posicion pos = resolverSigPosRadar();
            setPosicionRadar(pos);
      }
    }

    /**
     * método para comprar
     */
    @Override
    public void comprar() {
        if(Almacen.getMiAlmacen().existeArma("bomba")) {
            comprarArma("bomba");
        }
        Posicion tmp=revisadas.contieneNormal();
        if(tmp==null){
                boolean resueltaPos=false;
                int i=0,j=0;
                int maxCol=Battleship.getMyBattleship().maxCol();
                int maxFila=Battleship.getMyBattleship().maxFila();
                while(!resueltaPos && i<maxFila){
                    while(!resueltaPos && j<maxCol){
                        Posicion act =new Posicion(i,j);
                        if(!revisadas.contiene(act)){
                            if(revisadas.aptoMisilBoom(act) && getDinero()>=Battleship.getMyBattleship().getPrecioArma("misilBoom") && Almacen.getMiAlmacen().existeArma("misilBoom")){
                                comprarArma("misilBoom");
                            }else if(revisadas.aptoMisilNS(act) && getDinero()>=Battleship.getMyBattleship().getPrecioArma("misilNS")&& Almacen.getMiAlmacen().existeArma("misilNS")){
                                comprarArma("misilNS");
                            }else if(revisadas.aptoMisilEO(act) && getDinero()>=Battleship.getMyBattleship().getPrecioArma("misilEO")&& Almacen.getMiAlmacen().existeArma("misilEO")){
                                comprarArma("misilEO");
                            }

                            resueltaPos=true;
                        }else{
                            j++;
                        }
                    }
                    j=0;
                    i++;
                }
            }else{
                if((getDinero()>=Battleship.getMyBattleship().getPrecioArma("misil"))&& Almacen.getMiAlmacen().existeArma("misil")) {
                    comprarArma("misil");
                }
            }

    }

    /**
     * método para disparar
     */
    @Override
    public void disparar() {
        Posicion pos=resolverSigPosDisparar();
        String arma = resolverArmaDisparar(pos);
        if(pos!=null && arma!=null) {
            this.dispararArma(arma, pos);
        }
    }

}