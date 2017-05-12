package Modelo;

public class Tablero {
    /**
     * atributos
     */
    private ListaBarcos flotaHumano;
    private ListaBarcos flotaOrdenador;
    private static Tablero miTablero;

    /**
     * constructora
     */
    public Tablero() {
        System.out.println("inicializamos flotas");
        flotaHumano=new ListaBarcos();
        flotaOrdenador=new ListaBarcos();
    }

    /**
     * método para acceder a la instancia uica de tablero
     * @return
     */
    public static Tablero getMiTablero(){
        if(miTablero==null){
            miTablero = new Tablero();
        }
        return miTablero;
    }

    /**
     * método para inicializar la flota de jugador automaticamente
     * @param pTipo
     */
    public void inicializarAutoHumano(String pTipo){
        flotaHumano.inicializarAuto(pTipo);
    }

    /**
     * método para inicializar la flota de jugador automaticamente
     * @param pTipo
     */
    public void inicializarAutoOrdenador(String pTipo){
        flotaOrdenador.inicializarAuto(pTipo);
    }

    /**
     * método que devuelve el String con el numero de barcos que quedan por inicializarse en el jugador
     * @return
     */
    public String cuantosBarcosQuedan(){

        if(Battleship.getMyBattleship().turnoAct()==0){
           return flotaHumano.cuantosBarcosQuedan( );
        }else{
           return flotaOrdenador.cuantosBarcosQuedan( );
        }
    }

    /**
     * devuelve true si el barco  que contiene la posicion esta hundido
     * @param pPos
     * @return
     */
    public boolean barcoHundido(Posicion pPos){
        if(Battleship.getMyBattleship().turnoAct()==0){
            return  flotaOrdenador.barcoHundido(pPos);
        }else{
            return  flotaHumano.barcoHundido(pPos);
        }

    }

    /**
     * devolvera el resultado de comprobar si la flota contraria esta hundida
     * @return
     */
    public boolean haGanado() {
        if(Battleship.getMyBattleship().turnoAct()==1){
            return  flotaHumano.haGanado() ;
        }else{
            return  flotaOrdenador.haGanado() ;
        }
    }

    /**
     * devuelve el estado de la posicion aliada
     * @param pPos
     * @return
     */
    public  String estadoCampoAliado(Posicion pPos){
        if(Battleship.getMyBattleship().turnoAct()==0){
            return  flotaHumano.estadoPos(pPos);
        }else{
            return  flotaOrdenador.estadoPos(pPos);
        }
    }

    /**
     * devuelve el estado de la posicion enemiga
     * @param pPos
     * @return
     */
    public  String estadoCampoContrario(Posicion pPos){
        if(Battleship.getMyBattleship().turnoAct()==0){
            return  flotaOrdenador.estadoPos(pPos);
        }else{
            return  flotaHumano.estadoPos(pPos);
        }
    }

    /**
     * inicializa el primer barco que cumpla las espectativas de longitud siempre que las posiciones deducibles  sean validas
     * @param pPivote
     * @param direccion
     * @param length
     */
    public void inicializar(int[] pPivote,String direccion,int length){
        if(Battleship.getMyBattleship().turnoAct()==0){
            flotaHumano.inicializar( pPivote,direccion,length);
        }else{

            flotaOrdenador.inicializar( pPivote,direccion,length);
        }
    }

    /**
     * repara la posicion especificada
     * @param pPosicion
     */
    public void reparar( Posicion pPosicion){

        if(Battleship.getMyBattleship().turnoAct()==0){
            flotaHumano.reparar( pPosicion);
        }else{
            flotaOrdenador.reparar( pPosicion);
        }
    }

    /**
     * setea el escudo al barco que contenga la posicion especificada
     * @param pPosicion
     */
    public void setEscudo( Posicion pPosicion){

        if(Battleship.getMyBattleship().turnoAct()==0){
            flotaHumano.setEscudo( pPosicion);
        }else{
            flotaOrdenador.setEscudo( pPosicion);
        }
    }

    /**
     * impacta en la posicion especificada
     * @param pPosicion
     */
    public void impactar( Posicion pPosicion){

        if(Battleship.getMyBattleship().turnoAct()==1){
            flotaHumano.impactar( pPosicion);
        }else{
            flotaOrdenador.impactar( pPosicion);
        }
    }

    /**
     * hunde el barco que contenga la posicion
     * @param pPosicion
     */
    public void hundir( Posicion pPosicion){

        if(Battleship.getMyBattleship().turnoAct()==1){
            flotaHumano.hundir( pPosicion);
        }else{
            flotaOrdenador.hundir( pPosicion);
        }
    }

}