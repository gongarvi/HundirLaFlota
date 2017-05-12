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

                String estado=Tablero.getMiTablero().estadoCampoAliado(new Posicion(i, j));
                if(estado!=null){
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
        usarRadar();
        comprar();
        disparar();
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
        int i=1,j=1;
        while(!resueltaPos && i<Battleship.getMyBattleship().maxFila()-1){
            while(!resueltaPos && j<Battleship.getMyBattleship().maxCol()-1){
                Posicion act =new Posicion(i,j);
                if(!revisadas.contiene(act) && !revisadasColindantes(act)){
                    //si la posicion no se encuentra entre las revisadas
                    tmp=act;
                    resueltaPos=true;
                }else{
                    j++;
                }
            }
            j=1;
            i++;
        }
        return  tmp;
    }

    /**
     * devuelve true si alguna casilla colindante se encuentra revisada
     * @param pPos
     * @return
     */
    private boolean revisadasColindantes(Posicion pPos){
       Collection<Posicion> tmp=pPos.colindantes();
       for(Posicion p:tmp){
           if(revisadas.contiene(p)){
               return true;
           }
       }

       return false;
    }

    /**
     * resuleve la siguiente posicion en la que la IA disparara
     * @return
     */
    public Posicion resolverSigPosDisparar(){
        //si la posicion se encuentra entre las revisadas
        Posicion tmp=revisadas.contieneNormal();
        if(tmp==null){
            tmp=revisadas.contieneTocadoNoHundido();
            if(tmp==null){
            boolean resueltaPos=false;
            int i=0,j=0;
            while(!resueltaPos && i<Battleship.getMyBattleship().maxFila()){
                while(!resueltaPos && j<Battleship.getMyBattleship().maxCol()){
                    Posicion act =new Posicion(i,j);
                   if(!revisadas.contiene(act)){
                       //si la posicion no se encuentra entre las revisadas
                       tmp=act;
                       resueltaPos=true;
                   }else{
                       j++;
                   }
                }
                j=0;
                i++;
            }
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
        if(estadoTablero!=null && existeArma("misil")){
            return "misil";
        }else{
            int maxCol=Battleship.getMyBattleship().maxCol();
            int maxFila=Battleship.getMyBattleship().maxFila();
            if(pPos.getX()<((maxFila/2)+1) && pPos.getX()>((maxFila/2)-1) && pPos.getY()<((maxCol/2)+1) && pPos.getY()>((maxCol/2)-1) && existeArma("misilBoom")){
                return "misilBoom";
            }else if( existeArma("mmisilEO")){
                return "misilEO";
            }else if( existeArma("misilNS")){
                return "misilNS";
            }else {
                return "bomba";
            }
        }
    }
    /**
     * metodo para utilizar el radar
     */
    @Override
    public void usarRadar() {
        Posicion pos=resolverSigPosRadar();
        setPosicionRadar(pos);
    }

    /**
     * método para comprar
     */
    @Override
    public void comprar() {
        comprarArma("bomba");
        Posicion tmp=revisadas.contieneNormal();
        if(tmp==null){
            tmp=revisadas.contieneTocadoNoHundido();
            if(tmp==null){
                boolean resueltaPos=false;
                int i=0,j=0;
                int maxCol=Battleship.getMyBattleship().maxCol();
                int maxFila=Battleship.getMyBattleship().maxFila();
                while(!resueltaPos && i<maxFila){
                    while(!resueltaPos && j<maxCol){
                        Posicion act =new Posicion(i,j);
                        if(!revisadas.contiene(act)){
                            if(act.getX()<((maxFila/2)+1) && act.getX()>((maxFila/2)-1) && act.getY()<((maxCol/2)+1) && act.getY()>((maxCol/2)-1) ){
                                comprarArma("misilBoom");
                            }else {
                                int select=(int)(Math.random()*3.0);
                                if(select==0){
                                    comprarArma("misilNS");
                                }if(select==1){
                                    comprarArma("bomba");
                                }if(select==2){
                                    comprarArma("misilEO");
                                }
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
                comprarArma("misil");
            }
        }else{
            comprarArma("misil");
        }
    }

    /**
     * método para disparar
     */
    @Override
    public void disparar() {
        Posicion pos=resolverSigPosDisparar();
        String arma=resolverArmaDisparar(pos);
        this.dispararArma(arma,pos);
    }

}