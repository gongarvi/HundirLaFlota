package Modelo;

import Controlador.ControladorTablero;

public class Humano extends Jugador {

    /**
     * variable que guarda el nombre del jugador humano
     */
    private String nombre;


    /**
     * contructora de humano , se actualiza el campo nombre con el especifico y se llama a la clase jugador que inicializara sus atributos
     * @param pNombre
     */
    public Humano(String pNombre) {
        super();
        nombre = pNombre;
    }

    /**
     * metodo para colocar los barcos automaticamente (funciona raro)
     */
    public void colocarBarcosAuto() {
        Tablero.getMiTablero().inicializarAutoHumano("fragata");
        Tablero.getMiTablero().inicializarAutoHumano("destructor");
        Tablero.getMiTablero().inicializarAutoHumano("submarino");
        Tablero.getMiTablero().inicializarAutoHumano("portaaviones");

    }

    /**
     * metodo colocar barcos que utiliza la GUI interna(consola)
     */
    @Override
    public void colocarBarcos() {

         System.out.println("inicializar flota Humano");
         while (quedanBarcos()) {
             System.out.println(cuantosBarcosQuedan());
             mostrarFlotaJugador();
             int[] pPivote = Battleship.getMyBattleship().getEjes();
             String direccion =Battleship.getMyBattleship(). getDireccion();
             int length=-1;
             while(length!=-1) {
                 String tipo = Battleship.getMyBattleship().imputString();
                 length = Battleship.getMyBattleship().getLength(tipo);
                 if(length==-1){
                     System.out.println("inserta bien el tipo de barco");
                 }
             }
             Tablero.getMiTablero().inicializar(pPivote, direccion, length);
         }

         System.out.println("tablero humano inicializar flota jugador");

    }

    /**
     * adaptacion del método interno al controlador
     * @param pPivote
     * @param direccion
     * @param length
     */
    public void colocarUnBarco(int[] pPivote , String direccion ,int length ) {
            Tablero.getMiTablero().inicializar(pPivote, direccion, length);
    }

    /**
     * devuelve un String con el número de barcos que quedan por colocar de cada tipo
     * @return
     */
    public String cuantosBarcosQuedan(){
       return Tablero.getMiTablero().cuantosBarcosQuedan();
    }

    /**
     * método que muestra el campo aliado , discrimina si lo debe hacer mediante la Gui interna u otra externa
     */
    public void mostrarFlotaJugador(){
        if(Battleship.getMyBattleship().getTipoVista().equals("consola")) {
            System.out.println("TableroVista Jugador:\n");
        }
        for(int i=0;i<Battleship.getMyBattleship().maxFila();i++) {

            String tablero ="";
            for (int j = 0; j < Battleship.getMyBattleship().maxCol(); j++) {
                Posicion act=new Posicion(i, j);
                boolean esc =Tablero.getMiTablero().escudoAliado(act);
                String estado=Tablero.getMiTablero().estadoCampoAliado(act);
                if(estado!=null && esc) {
                    if (estado.equals("normal")) {
                        if (Battleship.getMyBattleship().getTipoVista().equals("consola")) {
                            tablero += " BE ";
                        } else {
                            ControladorTablero.getController().setBotonAliado(i, j, "escudo");
                        }
                    } else if (estado.equals("tocado")) {
                        if (Battleship.getMyBattleship().getTipoVista().equals("consola")) {
                            tablero += " TE ";
                        } else {
                            ControladorTablero.getController().setBotonAliado(i, j, "escudo");
                        }
                    }
                }else if(estado!=null && ! esc){
                    if (estado.equals("normal")) {
                        if(Battleship.getMyBattleship().getTipoVista().equals("consola")) {
                           tablero += " B ";
                          }else{
                           ControladorTablero.getController().setBotonAliado(i,j,"normal");
                     }
                     } else if (estado.equals("tocado")) {
                         if (Battleship.getMyBattleship().getTipoVista().equals("consola")) {
                              tablero += " T ";
                          } else {
                                ControladorTablero.getController().setBotonAliado(i, j, "tocado");
                    }
                }
                }else{
                    if(Battleship.getMyBattleship().getTipoVista().equals("consola")) {
                        tablero += " A ";
                    }else{
                        ControladorTablero.getController().setBotonAliado(i,j,"agua");
                    }
                }
            }
            if(Battleship.getMyBattleship().getTipoVista().equals("consola")) {
                System.out.println(tablero);
            }
        }
    }


    /**
     * método para utilizar radar (GUI interna)
     */
    public void  usarRadar(){
        System.out.println("introduce posicion para colocar radar\n");
        int[] posicion=Battleship.getMyBattleship().getEjes();
        Posicion tmp=new Posicion (posicion[0],posicion[1]);
        setPosicionRadar(tmp);
        Battleship.getMyBattleship().saltarFase();
    }

    /**
     * método para comprar (GUI interna)
     */
    public void  comprar(){
        String arma;
        System.out.println("tu dinero:"+getDinero());
        System.out.println("selecciones un tipo de arma para comprar:" +
                "\n precio bomba " + Battleship.getMyBattleship().getPrecioBomba() +
                "\n precio misil " + Battleship.getMyBattleship().getPrecioMisil() +
                "\n precio misilEO " + Battleship.getMyBattleship().getPrecioMisilEO() +
                "\n precio misilNS " + Battleship.getMyBattleship().getPrecioMisilNS() +
                "\n precio misilBoom "+ Battleship.getMyBattleship().getPrecioMisilBOOM() +
                "\n precio escudo "+ Battleship.getMyBattleship().getPrecioEscudo() );
        System.out.println("para continuearescribe : saltar fase ");
        arma =Battleship.getMyBattleship().imputString();
        if(arma.equals("escudo")){
            mostrarFlotaJugador();
            int[]pivote=Battleship.getMyBattleship().getEjes();
            setEscudo(new Posicion(pivote[0],pivote[1]));
        }else  if(arma.equals("saltar fase")){
            Battleship.getMyBattleship().saltarFase();
        }else{
            comprarArma(arma);
        }
    }

    /**
     * método para disparar (GUI interna)
     */
    public void  disparar(){
        String arma;
        System.out.println("tu dinero:"+getDinero());
        System.out.println("selecciones un tipo de arma para disparar ");
        this.cuantasArmasHay();
        arma =Battleship.getMyBattleship().imputString();
        System.out.println("introduce posicion para disparar\n");
        int[] posicion=Battleship.getMyBattleship().getEjes();
        Posicion tmp=new Posicion (posicion[0],posicion[1]);
        if(existeArma(arma)) {
            dispararArma(arma, tmp);
            Battleship.getMyBattleship().saltarFase();
        }else{
            disparar();
        }
    }


    /**
     * método para disparar (GUI interna)
     */
    public void  reparar(){
        mostrarFlotaJugador();
        int[] pivote;
        System.out.println("selecciones una posicion para reparar ");
        pivote =Battleship.getMyBattleship().getEjes();
        Posicion tmp=new Posicion (pivote[0],pivote[1]);
        repararBarco(tmp);
    }

    /**
     * método que devuelve el nombre del jugador humano
     * @return
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * método para jugar turno por consola (GUI interna)
     */
    public void jugarTurno(){
       if(Battleship.getMyBattleship().getTipoVista().equals("consola")) {
           mostrarFlotaJugador();
           usarRadar();
           System.out.println(Battleship.getMyBattleship().getFaseAct());
           while(Battleship.getMyBattleship().getFaseAct()==Battleship.getMyBattleship().getFaseCompraYEscudo()) {
               comprar();
           }
           disparar();
           reparar();
       }
    }

    /**
     * metodo que devuelve true si posiciones sin inicializar en algun barco
     * @return
     */
    public boolean quedanBarcos() {
        if (null != Tablero.getMiTablero().estadoCampoAliado(new Posicion(-1, -1))) {
            return true;
        }
        return false;
    }

}