package Modelo;

import Controlador.ControladorTablero;

import java.util.Scanner;

public class Battleship {
    /**
     * atributos del Battleship
     */
    private static Battleship myBattleship;
    private Humano humano;
    private IA ia;
    private int turno;
    private String tipoVista;
    private int fase;
    private boolean acabado;

    /**
     * variables globales modelo:
     */
    private int precioBomba=0;
    private int precioMisil=25;
    private int precioMisilEO=100;
    private int precioMisilNS=100;
    private int precioMisilBOOM=200;
    private int dineroInicial=1000;
    private int precioReparacion=50;
    private int maxFila=10;
    private int maxColumna=10;
    private int precioEscudo=75;
    private int LengthF=1;
    private int LengthD=2;
    private int LengthS=3;
    private int LengthP=4;
    private int nBombas=100;
    private int nMisiles=20;
    private int nMisiliesEO=20;
    private int nMisilesNS=20;
    private int nMisilesBoom=20;
    private int maxParaF=4;
    private int maxParaD=3;
    private int maxParaS=2;
    private int maxParaP=1;
    private int maxUsosRadar=3;

    /**
     * fases del juego:
     */
    private int faseInicializacion=0;
    private int faseRadar=1;
    private int faseCompraYEscudo=2;
    private int faseDisparo=3;
    private int faseTurnoIA=4;
    private int faseReparacion=5;
    private int faseVictoria=6;
    private int faseDerrota=7;

    /**
     * contructora privada para la clase Battleship (patrón Singleton)
     */
    private Battleship() {
        //0 es turno humano
        fase=0;
        turno=0;
        acabado=false;
    }
    /**
     *inicializa el Battleship para ejecutar pruebas
     * @return
     */
    public void pruebas(){
        Tablero.getMiTablero();
        tipoVista="consola";
        inicializarJugadores("edgar");
        humano.colocarBarcosAuto();
        ia.colocarBarcos();
    }

    /**
     * main del juego
     * @param args
     */
    public static void main(String[] args){
        Battleship.getMyBattleship().jugar();
    }

    /**
     *devuelve el valor de la fase inicial
     * @return
     */
    public int getFaseInicial(){return faseInicializacion;}

    /**
     *devuelve el valor de la fase radar
     * @return
     */
    public int getFaseRadar(){return faseRadar;}

    /**
     *devuelve el valor de la fase Derrota
     * @return
     */
    public int getFaseDerrota(){return faseDerrota;}

    /**
     *devuelve el valor de la fase Victoria
     * @return
     */
    public int getFaseVictoria(){return faseVictoria;}

    /**
     *devuelve el valor de la fase Comprar o colocar escudo
     * @return
     */
    public int getFaseCompraYEscudo(){return faseCompraYEscudo;}

    /**
     *devuelve el valor de la fase disparo
     * @return
     */
    public int getFaseDisparo(){return faseDisparo;}

    /**
     *devuelve el valor de la fase turno IA
     * @return
     */
    public int getFaseTurnoIA(){return faseTurnoIA;}

    /**
     *devuelve el valor de la fase reparacion
     * @return
     */
    public int getFaseReparacion(){return faseReparacion;}

    /**
     * devuelve el precio del arma especificada
     * @param pArma
     * @return
     */
    public int getPrecioArma(String pArma) {
        if(pArma.equals("bomba")){
            return getPrecioBomba();
        }else if(pArma.equals("misil")){
            return getPrecioMisil();
        }else if(pArma.equals("misilEO")){
            return getPrecioMisilEO();
        }else if(pArma.equals("misilNS")){
            return getPrecioMisilNS();
        }else if(pArma.equals("misilBoom")){
            return getPrecioMisilBOOM();
        }else{
            return 0;
        }
    }

    /**
     * método que devuelve el numero máximo de filas (global)
     * @return
     */
    public int maxFila(){
        return maxFila;
    }

    /**
     * añade el tipo de vista que se usara en la APP
     * @param pTipoVista
     */
    public void addTipoVista(String pTipoVista){tipoVista=pTipoVista;}

    /**
     * método que devuelve el numero máximo de columnas (global)
     * @return
     */
    public int getMaxUsosRadar(){
        return maxUsosRadar;
    }


    /**
     * método que devuelve el numero máximo de columnas (global)
     * @return
     */
    public int maxCol(){
        return maxColumna;
    }

    /**
     * método que devuelve el precio de un escudo (global)
     * @return
     */
    public int getPrecioEscudo(){return precioEscudo;}

    /**
     * devuelve el numero maximo de fragatas
     * @return
     */
    private int getMaxParaF(){return maxParaF;}

    /**
     * devuelve el numero maximo de destructores
     * @return
     */
    private int getMaxParaD(){return maxParaD;}

    /**
     * devuelve el numero maximo para submarinos
     * @return
     */
    private int getMaxParaS(){return maxParaS;}

    /**
     * devuelve el numero maximo para portaaviones
     * @return
     */
    private int getMaxParaP(){return maxParaP;}
    /**
     * método que devuelve el precio de una bomba (global)
     * @return
     */
    public int getPrecioBomba(){return precioBomba;}

    /**
     * método que devuelve el precio de un misil (global)
     * @return
     */
    public int getPrecioMisil(){return precioMisil;}

    /**
     * método que devuelve el precio de una misilEO (global)
     * @return
     */
    public int getPrecioMisilEO(){return precioMisilEO;}

    /**
     * método que devuelve el precio de un misilNS(global)
     * @return
     */
    public int getPrecioMisilNS(){return precioMisilNS;}

    /**
     * método que devuelve el numero total de bombas (global)
     * @return
     */
    public int getnBombas(){return nBombas;}


    /**
     * método que devuelve el numero total de misiles (global)
     * @return
     */
    public int getnMisiles(){return nMisiles;}


    /**
     * método que devuelve el numero total de misilesEO (global)
     * @return
     */
    public int getnMisilesEO(){return nMisiliesEO;}


    /**
     * método que devuelve el numero total de misilesNS (global)
     * @return
     */
    public int getnMisilesNS(){return nMisilesNS;}

    /**
     * devuelve el numero máximo de barcos para el tipo especifico
     * @param pTipo
     * @return
     */
    public int getMax(String pTipo){
        if(pTipo.equals("fragata")){
            return getMaxParaF();
        }else  if(pTipo.equals("submarino")){
            return getMaxParaS();
        }else  if(pTipo.equals("destructor")){
            return getMaxParaD();
        }else  if(pTipo.equals("portaaviones")){
            return getMaxParaP();
        }else{
            return -1;
        }
    }

    /**
     * método que devuelve el numero total de misiles Boom (global)
     * @return
     */
    public int getnMisilesBoom(){return nMisilesBoom;}

    /**
     * método que devuelve el precio de un misilBoom (global)
     * @return
     */
    public int getPrecioMisilBOOM(){return precioMisilBOOM;}

    /**
     * metodo que devuelve el dinero inicial de cada jugador
     */
    public int getDineroInicial(){return dineroInicial;}

    /**
     * método que devuelve el precio de reparacion para los barcos (global)
     * @return
     */
    public int getPrecioReparacion(){return precioReparacion;}

    /**
     * metodo que devuelve el String con el numero de barcos que queda para cada tipo
     * @return
     */
    public String cuantosBarcosQuedan(){
        return humano.cuantosBarcosQuedan();
    }

    /**
     * getter de la única instancia de la clase Battleship
     * @return
     */
    public static Battleship getMyBattleship() {
        if (myBattleship == null) {
            myBattleship = new Battleship();
        }
        return myBattleship;
    }

    /**
     * metodo que devuelve el turno actual del juego
     * @return
     */
    public int turnoAct(){return turno;}

    /**
     * metodo que devuelve el tipo de vista que se esta utilizando en el juego
     * @return
     */
    public String getTipoVista() {
        return tipoVista;
    }

    /**
     * metodo que inicializa el jugador humano y la IA
     * @param pNombre
     */
    private void inicializarJugadores(String pNombre) {
        humano = new Humano(pNombre);
        ia = new IA();
    }

    /**
     * método que coloca los barcos en el juego (GUI interna)
     */
    private void colocarFlotas() {
       humano.colocarBarcosAuto();
       cambiarTurno();
       ia.colocarBarcos();
       cambiarTurno();
    }

    /**
     * método que permite recoger un valor por consola (Gui interna)
     * @return
     */
    public String imputString(){
        Scanner imput=new Scanner( System.in );
        String result=imput.nextLine();
        return result ;
    }

    /**
     * método que permite recoger un valor entero por consola (Gui interna)
     * @return
     */
    public int imputInt(){
        Scanner imput=new Scanner( System.in );
        int result;
        try{
             result = Integer.parseInt(imput.nextLine());
             return result;
        }catch(Exception e){
            System.out.println("introduce un numero por favor");
            return  imputInt();
        }
    }

    /**
     * metodo que consulta al usuario la orientacion que desea para el barco a colocar (Gui interna)
     * @return
     */
    public String getDireccion(){
        String direccion;
        System.out.println("introduce direccion\n H \n V");
        direccion=imputString();
        if (!direccion.equals("H")&& !direccion.equals("V") ){
            getDireccion();
        }
        return direccion;
    }

    /**
     * metodo que devuelve un par de enteros (Gui interna)
     * @return
     */
    public int[] getEjes(){
        int[] pivote=new int[2];
        System.out.println("introduce eje x\n");
        pivote[0]=imputInt();
        System.out.println("introduce eje y\n");
        pivote[1]=imputInt();


        return pivote;
    }

    /**
     * metodo que inicializa la GUI (GUI externa)
     */
    private void inicializarGui(){ControladorTablero.getController().inicializarGui();}

    /**
     *reinicia las Mae de la vista para reiniciar
     */
    private void reiniciarMaes(){
        myBattleship=null;
        Almacen.getMiAlmacen().reiniciarMae();
        Tablero.getMiTablero().reiniciarMae();
        inicializarGui();
        ControladorTablero.getController().reiniciarVista();
    }

    /**
     * metodo jugar que decide la vista del juego e inicia la interaccion con el usuario en funcion de dicha seleccion ("consola" = GUI interna , "gui" = GUI externa)
     */
    public void jugar() {
        //lanzar Ventana Bienvenida y recoger nombre y tipo de vista
        System.out.println("Bienvenido: ¿quieres usar consola? (y/n)");
        String seleccion = imputString();
        String tipoVista;
        if(seleccion.equals("y")||seleccion.equals("Y")) {
             tipoVista="consola";
        }else{
             tipoVista="gui";
        }
        if(acabado){
            reiniciarMaes();
        }
        Battleship.getMyBattleship().addTipoVista(tipoVista);
        System.out.println("¿como te llamas ?");
        String pNombre = imputString();
        inicializarJugadores(pNombre);//inicializa todos sus atributos
        if(tipoVista.equals("consola")) {
            colocarFlotas();//abre el gestor de colocacion de flotas
            while (!acabado) {
                fase=faseRadar;
                humano.jugarTurno();
                acabado = humano.haGanado();
                if (acabado) {
                    System.out.println("has ganado " + humano.getNombre() + " felicidades!!!!!!!");
                }
            }
        }else{
            inicializarGui();
            cambiarTurno();
            ia.colocarBarcos();
            cambiarTurno();
            //iniciar el peloteo
            ControladorTablero.getController().fase(0);
        }
    }

    /**
     * método para cambiar el turno actual
     */
    public void cambiarTurno() {
        if(turno==0){
            turno=1;
        }else{
            turno=0;
        }
    }

    /**
     * método que pide el refresco del campo aliado (GUI externa)
     */
    public void mostrarCampoAliado(){
        humano.mostrarFlotaJugador();
    }

    /**
     * método que pide el refresco de las posiciones (GUI externa)
     */
    public void mostrarOpciones(){
        ControladorTablero.getController().setOpcion("turno",String.valueOf(turnoAct()));
        ControladorTablero.getController().setOpcion("precioEscudo",String.valueOf(getPrecioEscudo()));
        ControladorTablero.getController().setOpcion("precioReparacion",String.valueOf(getPrecioReparacion()));
        ControladorTablero.getController().setOpcion("usosRadar",String.valueOf(humano.usosRadar()));
        ControladorTablero.getController().setOpcion("nombre",String.valueOf(humano.getNombre()));
        humano.cuantoDineroHay();
        humano.cuantasArmasHay();
        Almacen.getMiAlmacen().cuantasArmasHay();
        Almacen.getMiAlmacen().precioArmas();
    }

    /**
     * método que gestiona un suceso en el campo aliado (GUI externa)
     * @param pBtnSeleccionado
     */
    public void gestionarCampoAliado(int pBtnSeleccionado){

        Posicion pPos=convertirAPosicion(pBtnSeleccionado);
        if (fase==faseCompraYEscudo) {
            humano.setEscudo(pPos);
            //avisar que puede comprar mas hasta que pase fase
            ControladorTablero.getController().fase(fase);
            ControladorTablero.getController().changed();
        }else if (fase==faseReparacion) {
            humano.repararBarco(pPos);
            //avisar siguiente fase
            fase=1;
            ControladorTablero.getController().fase(fase);
            ControladorTablero.getController().changed();
        }else if (fase==faseInicializacion) {
            //humano.colocarBarcosAuto(); //descomentar para colocar los barcos automaticamente
            /**
             * comentar debajo(para auto)
             **/
            int[] pivote=new int[2];
            pivote[0]=pPos.getX();
            pivote[1]=pPos.getY();
            humano.colocarUnBarco(pivote,ControladorTablero.getController().getOrientacion(),getLength(ControladorTablero.getController().getTipoBarco()));
            /**
             * comentar encima (para auto)
             */
            ControladorTablero.getController().changed();
            System.out.println(humano.quedanBarcos());
            if(!humano.quedanBarcos()){
                //avisar siguiente fase
                fase=faseRadar;
                ControladorTablero.getController().fase(fase);
            }else{
                //quedan n barcos
            }
        }else{
           ControladorTablero.getController().error("en esta fase no se puede hacer eso");
           ControladorTablero.getController().recuerda();
           ControladorTablero.getController().fase(fase);
        }
    }

    /**
     * método que devuelve la longitud del tipo especificado
     * @param pTipo
     * @return
     */
    public int getLength(String pTipo){
        if(pTipo.equals("fragata")){
            return LengthF;
        }else  if(pTipo.equals("submarino")){
            return LengthS;
        }else  if(pTipo.equals("destructor")){
            return LengthD;
        }else  if(pTipo.equals("portaaviones")){
            return LengthP;
        }else{
            return -1;
        }

    }

    /**
     * método que devuelve el valor de la fase actual (GUI externa)
     * @return
     */
    public int getFaseAct( ){
       return fase;
    }

    /**
     * método que gestiona un suceso en el campo enemigo (GUI externa)
     * @param pBtnSeleccionado
     */
    public void gestionarCampoEnemigo(int pBtnSeleccionado){
        Posicion pPos=convertirAPosicion(pBtnSeleccionado);
        if(fase==faseDisparo) {
            String arma=ControladorTablero.getController().getImputSelect();
            System.out.println(arma);
            if(humano.existeArma(arma)) {
                humano.dispararArma(arma, pPos);
                ControladorTablero.getController().changed();
                if(Tablero.getMiTablero().haGanado()){
                    acabado =true;
                    fase=faseVictoria;
                    ControladorTablero.getController().fase(fase);
                }
                //hasta aqui bien
                fase = faseTurnoIA;
                //turno de la IA
                cambiarTurno();
                ia.jugarTurno();
                if(Tablero.getMiTablero().haGanado()){
                    acabado =true;
                    fase=faseDerrota;
                    ControladorTablero.getController().fase(fase);
                }
                cambiarTurno();

                ControladorTablero.getController().changed();
                //avisar siguiente fase
                fase = faseReparacion;
                ControladorTablero.getController().fase(fase);
            }else{
                ControladorTablero.getController().error("no quedan armas");
            }
        }else  if (fase==faseRadar) {
            humano.setPosicionRadar(pPos);
            ControladorTablero.getController().changed();
            //avisar siguiente fase
            fase=faseCompraYEscudo;
            ControladorTablero.getController().fase(fase);
        }else{
            //error
            ControladorTablero.getController().error("en esta fase no se puede hacer eso");
            ControladorTablero.getController().recuerda();
            ControladorTablero.getController().fase(fase);
        }
    }


    /**
     * devuelve el nombre del jugador humano  (
     * @return
     */
    public String getNombreJugador(){return humano.getNombre();}

    /**
     * método que permite saltar una fase sin realizar na accin (GUI externa)
     */
    public void saltarFase(){
        if(fase==faseInicializacion || fase==faseDerrota || fase==faseVictoria){
            if(tipoVista.equals("consola")){
                System.out.println("no puedes saltarte esta fase");
            }else {
                ControladorTablero.getController().error("no puedes saltarte esta fase");
            }
        }else  if(fase==faseDisparo){
            cambiarTurno();
            ia.jugarTurno();
            if(Tablero.getMiTablero().haGanado()){
                acabado =true;
                fase=faseDerrota;
                if(tipoVista.equals("consola")){
                    System.out.println("lo siento has perdido ,vuelve a intentarlo");
                }else {
                    ControladorTablero.getController().fase(fase);
                }
            }
            cambiarTurno();
            if(!tipoVista.equals("consola")){
                ControladorTablero.getController().changed();
            }
            fase = faseReparacion;
            if(tipoVista.equals("consola")){
                System.out.println("repara en esta fase");
            }else {
                ControladorTablero.getController().fase(fase);
            }
        }else if (fase ==faseRadar){
            //avisar cambio de fase
            fase=faseCompraYEscudo;
            if(tipoVista.equals("consola")){
                System.out.println("en esta fase puedes comprar armas y escudos , tantos como desees");
            }else {
                ControladorTablero.getController().fase(fase);
            }
        } else if (fase ==faseCompraYEscudo){
            //avisar cambio de fase
            fase=faseDisparo;
            if(tipoVista.equals("consola")){
                System.out.println("en esta fase puedes disparar el arma que desees a la posicion que introduzcas");
            }else {
                ControladorTablero.getController().fase(fase);
            }
        }else if (fase ==faseReparacion){
            //avisar cambio de fase
            fase=faseRadar;
            if(tipoVista.equals("consola")){
                System.out.println("coloca radar en esta fase");
            }else {
                ControladorTablero.getController().fase(fase);
            }
        }
    }

    /**
     * método que gestiona la compra de armas (GUI externa)
     */
    public void comprar(){
        if (fase==faseCompraYEscudo) {
            if(Almacen.getMiAlmacen().existeArma(ControladorTablero.getController().getImputSelect())){
                humano.comprarArma(ControladorTablero.getController().getImputSelect());
                ControladorTablero.getController().changed();
            }else{
                if(getTipoVista().equals("consola")){
                    System.out.println("no quedan "+ControladorTablero.getController().getImputSelect()+ " en el almacen");
                }else{
                    ControladorTablero.getController().error("no quedan "+ControladorTablero.getController().getImputSelect()+ " en el almacen");
                }
            }
            //avisar que puede comprar mas hasta que pase fase
        }else{
            ControladorTablero.getController().error("en esta fase no se puede hacer eso");
            ControladorTablero.getController().recuerda();
            ControladorTablero.getController().fase(fase);
        }
    }

    /**
     * cancela una compra cuando se produce un error
     * @param pCantidad
     */
    public void cancelarCompra(int pCantidad){
        humano.decrementarDinero(-pCantidad);
    }

    /**
     * método capaz de resolver una posicion desde un entero dado (GUI externa)
     * la logica del index debe seguir una coleccion , EJ : 10 -> (1,0)
     * @param pIndexBoton
     * @return
     */
    private Posicion convertirAPosicion(int pIndexBoton){return new Posicion(pIndexBoton/10,(pIndexBoton%10));}

    /**
     * método que añade una posicion a las revisadas de la ia
     * @param pPos
     */
    public void addRevisadaIA(Posicion pPos){ia.addPosRevisada(pPos);}

}