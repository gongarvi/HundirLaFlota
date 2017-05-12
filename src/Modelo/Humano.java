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
             int length = getTipoBarco();
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

                String estado=Tablero.getMiTablero().estadoCampoAliado(new Posicion(i, j));
                if(estado!=null){
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
     * método que decide el tipo de barco y devuelve su longitud (Gui interna)
     * @return
     */
    private int getTipoBarco(){
        String tipo ;
        System.out.println("introduce tipo barco:\n fragata \n destructor \n portaaviones \n submarino");
        tipo=Battleship.getMyBattleship().imputString();
        if(tipo.equals("fragata")){
            return Battleship.getMyBattleship().getLength("fragata");
        }else  if(tipo.equals("submarino")){
            return Battleship.getMyBattleship().getLength("submarino");
        }else  if(tipo.equals("destructor")){
            return Battleship.getMyBattleship().getLength("destructor");
        }else  if(tipo.equals("portaaviones")){
            return Battleship.getMyBattleship().getLength("portaaviones");
        }else{
            System.out.println("incorrecto");
            return getTipoBarco();
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
                "\n precio misilBoom "+ Battleship.getMyBattleship().getPrecioMisilBOOM());
        arma =Battleship.getMyBattleship().imputString();
        comprarArma(arma);
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
        }else{
            disparar();
        }
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
           comprar();
           disparar();
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