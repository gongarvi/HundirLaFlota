package Modelo;

import Controlador.ControladorTablero;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class ListaBarcos {
    /**
     *atributos
     */
    private ArrayList<Barco> lb;

    /**
     *constructora de listaBarcos se contruye e inicializa
     */
    public ListaBarcos() {
        lb = new ArrayList<>();
        inicializar();
    }

    /**
     * método que devuelve el iterador correspondiente a la lista de barcos
     * @return
     */
    private Iterator<Barco> getIterator() {
        return lb.iterator();
    }

    /**
     * devuelve true si el barco que contiene la posicion dada como parameto esta hundido
     * @param pPos
     * @return
     */
    public boolean barcoHundido(Posicion pPos){
        Barco tmp=buscarPorPos(pPos);
        if(tmp==null){
            return false;
        }else{
            return tmp.hundido();
        }
    }

    /**
     * devuelve el String con el numero de barcos que quedan con inicializar
     * @return
     */
    public String cuantosBarcosQuedan(){
       Iterator<Barco> barcoIterator = getIterator();
       int f=0,d=0,p=0,s=0;
       while(barcoIterator.hasNext()){
           Barco tmp=barcoIterator.next();

           if(tmp.noInicializado()){
               if(tmp instanceof Fragata){
                    f+=1;
               }else if(tmp instanceof Destructor){
                    d+=1;
               }else if(tmp instanceof Submarino){
                    s+=1;
               }else if(tmp instanceof Portaaviones){
                    p+=1;
               }

           }
       }
       return("quedan:\n fragatas  "+f+"\n destructores:  "+d+"\n submarinos:  "+s+"\n portaaviones:  "+p);

    }

    /**
     * método para inicializar automaticamente una flota
     * @param tipo
     */
    public void inicializarAuto(String tipo) {
        int pX ;
        int pY ;
        String direccion = "H";
        Boolean valido;
        int nBarcos = Battleship.getMyBattleship().getMax(tipo);
        Random aleatorio = new Random();
        while( nBarcos != 0) {
            pX = aleatorio.nextInt(Battleship.getMyBattleship().maxFila());
            pY = aleatorio.nextInt(Battleship.getMyBattleship().maxCol());
            if(direccion.equals("H")) {
                direccion = "V";
            }else{
                direccion = "H";
            }
            int[]pivote=new int[2];
            pivote[0]=pX;
            pivote[1]=pY;
            valido = comprobarCoordenadas( pivote, direccion, Battleship.getMyBattleship().getLength(tipo) );
            if (valido) {
                inicializar(pivote,direccion,Battleship.getMyBattleship().getLength(tipo));
                nBarcos--;
            }
        }
    }

    /**
     * inicializa un barco de la longitud especificada con las posiciones deducibles de los parametros dados
     * @param pPivote
     * @param direccion
     * @param length
     */
    public void inicializar(int[] pPivote,String direccion,int length){

        boolean insertado=false;

        if(comprobarCoordenadas(pPivote,direccion,length)){
            Iterator<Barco> barcos=getIterator();
            while(barcos.hasNext() && !insertado){
                Barco actB=barcos.next();
                if( actB.esBarcoCorrecto(length)&& actB.noInicializado()) {
                    actB.inicializar(pPivote,direccion);
                    insertado=true;
                }
            }
        }
       if(!insertado){
            if(Battleship.getMyBattleship().getTipoVista().equals("consola")) {
                System.out.println("fallo al coloocar el barco");
            }else {
                ControladorTablero.getController().error("fallo al coloocar el barco");
            }
       }
    }

    /**
     * comprueba la validez de las posiciones deducibles de los parametros
     * @param pPivote
     * @param direccion
     * @param length
     * @return
     */
    private boolean comprobarCoordenadas(int[] pPivote,String direccion,int length){
        Iterator<Barco> barcos=getIterator();
        boolean valido;
        if(direccion.equals("V")){
            valido=(pPivote[0]+(length-1)<Battleship.getMyBattleship().maxFila()) &&(pPivote[0]>=0);
        }else{
            valido=(pPivote[1]+(length-1)<Battleship.getMyBattleship().maxCol())&& (pPivote[1]>=0);
        }
        while(barcos.hasNext() && valido ){
                  Barco actB=barcos.next();
                  if( actB.escolindante(pPivote,direccion,length) ) {
                      valido = false;
                  }
        }
    return  valido;
    }

    /**
     * método para añadir un barco a la lista
     * @param pBarco
     */
    private void anadir(Barco pBarco) {
        lb.add(pBarco);
    }

    /**
     * método para reiniciar la flota
     */
    public void reiniciar(){
        lb=new ArrayList<>();
        inicializar();
    }

    /**
     * método para reparar la posicion dada como parametro
     * @param pPosicion
     */
    public void reparar( Posicion pPosicion){
        Barco aImpactar=buscarPorPos(pPosicion);
        if(aImpactar!=null){
            aImpactar.reparar(pPosicion);
        }else{
            System.out.println("fallaste al elegir barco");
            Battleship.getMyBattleship().cancelarCompra(Battleship.getMyBattleship().getPrecioReparacion());
        }
    }

    /**
     * metodo para impactar la posicion dada como parametro
     * @param pPosicion
     */
    public void impactar( Posicion pPosicion){
        Barco aImpactar=buscarPorPos(pPosicion);
        if(aImpactar!=null){
            aImpactar.recibirDanios(pPosicion);

        }else  if(Battleship.getMyBattleship().turnoAct()==0) {
            if (Battleship.getMyBattleship().getTipoVista().equals("consola")) {
                System.out.println("fallo Aguaaaa....");
            }
        }
    }

    /**
     * devuelve true si el barco que contiene la posicion tiene escudo
     * @param pPos
     * @return
     */
    public boolean tieneEscudo(Posicion pPos){
        Barco aImpactar=buscarPorPos(pPos);
        if(aImpactar!=null){
            return aImpactar.tieneEscudo();
        }else {
            return false;
        }
    }
    /**
     * devuelve el estado de una posicion dada como parametro
     * @param pPos
     * @return
     */
    public  String estadoPos(Posicion pPos) {
       Barco act= buscarPorPos(pPos);
       if(act!=null){
          return act.estadoPos(pPos);
       }
       return null ;
    }

    /**
     * devuelve true si todos los barcos de la flota estan hundidos
     * @return
     */
    public boolean flotaHundida() {
        for(Barco b:lb){
            if(!b.hundido()){
                return false;
            }
        }
        return true;
    }

    /**
     * hunde la posicion dada como parametro
     * @param pPosicion
     */
    public void hundir( Posicion pPosicion){

        Barco aImpactar=buscarPorPos(pPosicion);
        if(aImpactar!=null){
            aImpactar.hundir();
        }else if(Battleship.getMyBattleship().turnoAct()==0) {
            if (Battleship.getMyBattleship().getTipoVista().equals("consola")) {
                System.out.println("fallo Aguaaaa....");
            }
        }
    }

    /**
     * settea el escudo al barco que contenga la posicion
     * @param pPosicion
     */
    public void setEscudo( Posicion pPosicion){

        Barco aImpactar=buscarPorPos(pPosicion);
        if(aImpactar!=null && !aImpactar.tieneEscudo()){
            aImpactar.setEscudo();
        }else if(Battleship.getMyBattleship().turnoAct()==0) {
            if (Battleship.getMyBattleship().getTipoVista().equals("consola")) {
                System.out.println("has fallado o tenia escudo");
                Battleship.getMyBattleship().cancelarCompra(Battleship.getMyBattleship().getPrecioEscudo());
            } else {
                ControladorTablero.getController().error("Has fallado o ya tenia escudo");
                Battleship.getMyBattleship().cancelarCompra(Battleship.getMyBattleship().getPrecioEscudo());
            }
        }
    }

    /**
     * devuelve el barco que contenga la posicion
     * @param pos
     * @return
     */
    private Barco buscarPorPos(Posicion pos) {
        boolean enc = false;
        Iterator<Barco> itr = this.getIterator();
        Barco b = null;
        while (itr.hasNext() && !enc) {
            b = itr.next();
            if (b.contiene(pos)) {
                enc = true;
            }
        }
        if(!enc){
            b=null;
        }
        return b;
    }

    /**
     *inicializa la llista de barcos con el número de barcos totales para cada flota, por defecto sus posiciones seran todas (-1,-1)
     */
    private void inicializar() {

            for (int i = 0; i < Battleship.getMyBattleship().getMax("portaaviones"); i++) {
                anadir(BarcoFactory.getBarcoFactory().crearBarco("portaaviones"));
            }

            for (int i = 0; i < Battleship.getMyBattleship().getMax("submarino"); i++) {
                anadir(BarcoFactory.getBarcoFactory().crearBarco("submarino"));
            }

            for (int i = 0; i < Battleship.getMyBattleship().getMax("destructor"); i++) {
                anadir(BarcoFactory.getBarcoFactory().crearBarco("destructor"));
            }

            for (int i = 0; i < Battleship.getMyBattleship().getMax("fragata"); i++) {
                anadir(BarcoFactory.getBarcoFactory().crearBarco("fragata"));
            }

    }
}
