package Vista;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import Controlador.ControladorTablero;

public class TableroVista extends JFrame implements Observer {

    /**
     * atributos
     */
    private static TableroVista vista;
    private JPanel ventana;
    private JPanel opciones;
    private JPanel east;
    private JPanel middle;
    private JPanel imput;
    private JLabel dinero;
    private JLabel bombas;
    private JLabel misiles;
    private JLabel misilesNS;
    private JLabel misilesEO;
    private JLabel misilesBOOM;
    private JLabel bombasAlmacen;
    private JLabel misilesAlmacen;
    private JLabel misilesNSAlmacen;
    private JLabel misilesEOAlmacen;
    private JLabel misilesBoomAlmacen;
    private JLabel precioBomba;
    private JLabel precioMisil;
    private JLabel precioMisilNS;
    private JLabel precioMisilesEO;
    private JLabel precioMisilesBoom;
    private JLabel precioEscudo;
    private JLabel precioReparacion;
    private JLabel usosRadar;
    private JLabel nombre;
    private boolean skipHints;
    private String armaSeleccionada;
    private String orientacionleccionada;
    private String barcoSeleccionado;
    private int maxFila;
    private int maxCol;
    private ArrayList<JButton> casillasMiddle;//campo Jugador
    private ArrayList<JButton> casillasEast;//campo Contrincante
    private JFrame popUpAct;


    /**
     * devuelve el tipo de barco sleccionado
     * @return
     */
    public String getTipoBarco(){return barcoSeleccionado;}

    /**
     * ddevuelve la orientacion seleccionada
     * @return
     */
    public String getOrientacion(){return orientacionleccionada;}

    /**
     * este método actualiza toda la información del jugador
     */
    private void actualizarJugador(){
        ControladorTablero.getController().mostrarFlotaJugador();
        ControladorTablero.getController().actualizarOpciones();
    }

    /**
     * este método cierrra el popUp que este abierto actualmente
     */
    public void disposePopUp(){popUpAct.dispose();}

    /**
     * lanza up popUp con la información especificada por el parámetro
     * @param pInfo
     */
    public void popUpInformacion(String pInfo){
        popUpAct =new JFrame();
        popUpAct.setTitle("información");
        popUpAct.setBounds(300,300,1000,100);
        popUpAct.add(new JLabel(pInfo), BorderLayout.CENTER);
        JButton btn=new JButton("entendido");
        btn.addActionListener(ControladorTablero.getController());
        popUpAct.add(btn, BorderLayout.EAST);
        popUpAct.setVisible(true);
    }

    /**
     * lanza up popUp de victoria / derrota
     * @param pInfo
     */
    public void popUpVictoriaDerrota(String pInfo){
        popUpAct =new JFrame();
        popUpAct.setTitle(pInfo);
        popUpAct.setBounds(300,300,1000,100);
        if(pInfo.equals("victoria")) {
            popUpAct.add(new JLabel("felicidades !! almirante " + ControladorTablero.getController().getNombreJugador()), BorderLayout.CENTER);
        }else{
            popUpAct.add(new JLabel("otra vez será , reinicia y dale una tunda almirante"  + ControladorTablero.getController().getNombreJugador()), BorderLayout.CENTER);
        }
        JPanel east=new JPanel();
        JButton btnR=new JButton("reiniciar");
        JButton btnS=new JButton("salir");
        btnR.addActionListener(ControladorTablero.getController());
        btnS.addActionListener(ControladorTablero.getController());
        popUpAct.add(east,BorderLayout.EAST);
        east.add(btnR,BorderLayout.WEST);
        east.add(btnS,BorderLayout.EAST);
        popUpAct.setVisible(true);
    }

    /**
     * lanza up popUp con la error especificado por el parámetro
     * @param pTipoFallo
     */
    public void popUpError(String pTipoFallo){
        popUpAct =new JFrame();
        popUpAct.setTitle("error");
        popUpAct.setBounds(400,300,300,100);
        popUpAct.add(new JLabel(pTipoFallo), BorderLayout.CENTER);
        JButton btn=new JButton("entendido");
        btn.addActionListener(ControladorTablero.getController());
        popUpAct.add(btn, BorderLayout.EAST);
        popUpAct.setVisible(true);
    }

    /**
     * lanza la información referente a la primera fase RADAR
     */
    public void faseRadar(){
        if(!skipHints){
            popUpInformacion("selecciona una posicion del campo enemigo para colocar un radar , recuerda");
        }
    }

    /**
     * lanza la información referente a la segunda fase COMPRAR
     */
    public void faseComprarYEScudo(){
        if(!skipHints){
            popUpInformacion("en esta fase puedes comprar lo que desees ,tanto armas como escudos ,abajo a la izquierda encontraras el boton de comprar y para los escudos selecciona un barco");
        }
    }

    /**
     * lanza la información referente a la tercera fase DISPARAR
     */
    public void faseDisparo(){
        if(!skipHints){
            popUpInformacion("en esta fase debes seleccionar una posición para disparar con el arma que selecciones");
        }
        //seleccion de arma
    }
    /**
     * lanza la información referente a la cuarta fase TURNO IA
     */
    public void faseTurnoIA(){
        if(!skipHints){
            popUpInformacion("la IA jugara su turno ahora");
        }

    }

    /**
     * lanza la información referente a la quita fase REPARAR
     */
    public void faseReparar(){
        if(!skipHints){
            popUpInformacion("en esta fase puedes reparar un barco aliado o saltar la fase");
        }

    }

    /**
     * activa o desactiva el booleano que controla la ayuda
     */
    public void skipHints(){
        skipHints=!skipHints;
    }

    /**
     * lanza una ventana para recoger los datos de colocacion de barco (por defecto: fragata ,
     */
    public void recogerInfo(){
        popUpAct =new JFrame();
        popUpAct.setTitle("colocacion de barco");
        popUpAct.setBounds(150,150,1000,100);
        ButtonGroup selectorBarco=new ButtonGroup();
        ButtonGroup selectorOrientacion=new ButtonGroup();
        JRadioButton fragata=new JRadioButton("fragata");
        fragata.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                barcoSeleccionado="fragata";
            }
        });
        JRadioButton destructor=new JRadioButton("destructor");
        destructor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                barcoSeleccionado="destructor";
            }
        });
        JRadioButton submarino=new JRadioButton("submarino");
        submarino.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                barcoSeleccionado="submarino";
            }
        });
        JRadioButton portaaviones=new JRadioButton("portaaviones");
        portaaviones.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                barcoSeleccionado="portaaviones";
            }
        });
        selectorBarco.add(fragata);
        selectorBarco.add(destructor);
        selectorBarco.add(submarino);
        selectorBarco.add(portaaviones);
        JRadioButton vertical=new JRadioButton("vertical");
        vertical.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                orientacionleccionada="V";
            }
        });
        JRadioButton horizontal=new JRadioButton("horizontal");
        horizontal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                orientacionleccionada="H";
            }
        });
        selectorOrientacion.add(vertical);
        selectorOrientacion.add(horizontal);
        JButton btn=new JButton("añadirInfo");
        btn.addActionListener(ControladorTablero.getController());
        JPanel centro=new JPanel();
        centro.setLayout(new GridLayout(2, 4, 0, 0));
        centro.add(fragata);
        centro.add(destructor);
        centro.add(submarino);
        centro.add(portaaviones);
        centro.add(new Label(""));
        centro.add(vertical);
        centro.add(horizontal);
        popUpAct.add(new JLabel("selecciona tipo de barco y orientacion, recuerda \n"+ControladorTablero.getController().cuantosBarcosQuedan()),BorderLayout.NORTH);
        popUpAct.add(centro, BorderLayout.CENTER);
        popUpAct.add(btn, BorderLayout.EAST);
        popUpAct.setVisible(true);
    }

    /**
     * lanza la información referente a la fase  COLOCAR BARCOS
     */
    public void faseColocacion(){
        if(!skipHints){
            popUpInformacion("en esta fase debes seleccionar: tipo de barco, posiciones y orientacion de los barcos");
        }
    }

    /**
     * lanza la información referente a la DERROTA
     */
    public void faseDerrota(){
            popUpVictoriaDerrota("derrota");
    }

    /**
     * lanza la información referente a la VICTORIA
     */
    public void faseVictoria(){
        popUpVictoriaDerrota("victoria");
    }

    /**
     * devuelve el arma seleccionada por el usuario
     * @return
     */
    public String getSeleccion(){return  armaSeleccionada;}

    /**
     * actualiza el boton del campo aliado especificado como parametro al estado dado tambien como parametro
     * @param boton
     * @param pEstado
     */
    public void actualizarCampoAliado(int boton,String pEstado){

        if(pEstado.equals( "normal" )){
            casillasMiddle.get( boton ).setBackground(Color.black);
        }else if(pEstado.equals( "tocado" )){
            casillasMiddle.get( boton ).setBackground(Color.red);
        }else if(pEstado.equals( "escudo" )) {
            casillasMiddle.get( boton ).setBackground(Color.CYAN);
        }else {
            casillasMiddle.get( boton ).setBackground(Color.blue);
        }
    }

    /**
     * reinicia la Mae vista
     */
    public void reiniciarMae(){vista=null;dispose();}

    /**
     * actualiza el boton del campo enemigo especificado como parametro al estado dado tambien como parametro
     * @param boton
     * @param pEstado
     */
    public void actualizarCampoEnemigo(int boton,String pEstado){

            if (pEstado.equals("normal")) {
                casillasEast.get(boton).setBackground(Color.black);
            }else if(pEstado.equals( "escudo" )){
                casillasEast.get( boton ).setBackground(Color.CYAN);
            }else if (pEstado.equals("tocado")) {
                casillasEast.get(boton).setBackground(Color.red);
            } else if (pEstado.equals("agua")) {
                casillasEast.get(boton).setBackground(Color.blue);
            }
    }

    /**
     * actualiza la opcion deseada con el texto dado
     * @param pOpcion
     * @param pTexto
     */
    public void actualizarOpciones(String pOpcion,String pTexto){
        if(pOpcion.equals("dinero")) {
            dinero.setText(pTexto);
        }else  if(pOpcion.equals("bombas")) {
            bombas.setText(pTexto);
        }else  if(pOpcion.equals("misiles")) {
            misiles.setText(pTexto);
        }else  if(pOpcion.equals("misilesNS")) {
            misilesNS.setText(pTexto);
        }else  if(pOpcion.equals("misilesEO")) {
            misilesEO.setText(pTexto);
        }else  if(pOpcion.equals("misilesBoom")) {
            misilesBOOM.setText(pTexto);
        }else  if(pOpcion.equals("bombasAlmacen")) {
            bombasAlmacen.setText(pTexto);
        }else  if(pOpcion.equals("misilesAlmacen")) {
            misilesAlmacen.setText(pTexto);
        }else  if(pOpcion.equals("misilesNSAlmacen")) {
            misilesNSAlmacen.setText(pTexto);
        }else  if(pOpcion.equals("misilesEOAlmacen")) {
            misilesEOAlmacen.setText(pTexto);
        }else  if(pOpcion.equals("misilesBoomAlmacen")) {
            misilesBoomAlmacen.setText(pTexto);
        }else  if(pOpcion.equals("precioBomba")) {
            precioBomba.setText(pTexto);
        }else  if(pOpcion.equals("precioMisil")) {
            precioMisil.setText(pTexto);
        }else  if(pOpcion.equals("precioMisilNS")) {
            precioMisilNS.setText(pTexto);
        }else  if(pOpcion.equals("precioMisilesEO")) {
            precioMisilesEO.setText(pTexto);
        }else  if(pOpcion.equals("precioMisilesBoom")) {
            precioMisilesBoom.setText(pTexto);
        }else  if(pOpcion.equals("precioReparacion")) {
            precioReparacion.setText(pTexto);
        }else  if(pOpcion.equals("precioEscudo")) {
            precioEscudo.setText(pTexto);
        }else  if(pOpcion.equals("usosRadar")) {
            usosRadar.setText(pTexto);
        }else  if(pOpcion.equals("nombre")) {
            nombre.setText(pTexto);
        }
    }

    /**
     * devuelve la unica instancia de TAbleroVista
     * @return
     */
    public static TableroVista getTablero(){

        if(vista==null){
            vista=new TableroVista();
        }
        return vista;
    }

    /**
     * contructora privada de tablero vista (patron Singleton)
     */
    private TableroVista(){}

    /**
     * inicializa todos los componentes de la ventana Ppal
     * @param pFilas
     * @param pColumnas
     */
    public void  inicializarVista(int pFilas, int pColumnas) {
        skipHints=false;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("HundiLaFlota");
        setBounds(10, 50, 1360, 450);
        middle = new JPanel();
        east= new JPanel();
        ventana = new JPanel();
        opciones = new JPanel();
        imput=new JPanel();
        ButtonGroup selectorArma=new ButtonGroup();
        JRadioButton bomba=new JRadioButton("bomba");
        bomba.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                armaSeleccionada="bomba";
            }
        });
        selectorArma.add(bomba);
        JRadioButton misil=new JRadioButton("misil");
        misil.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                armaSeleccionada="misil";
            }
        });
        selectorArma.add(misil);
        JRadioButton misilNS=new JRadioButton("misilNS");
        misilNS.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                armaSeleccionada="misilNS";
            }
        });
        selectorArma.add(misilNS);
        JRadioButton misilEO=new JRadioButton("misilEO");
        misilEO.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                armaSeleccionada="misilEO";
            }
        });
        selectorArma.add(misilEO);
        JRadioButton misilBOOM=new JRadioButton("misilBoom");
        misilBOOM.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                armaSeleccionada="misilBoom";
            }
        });
        selectorArma.add(misilBOOM);
        imput.add(bomba);
        imput.add(misil);
        imput.add(misilNS);
        imput.add(misilEO);
        imput.add(misilBOOM);
        JButton comprar=new JButton( );
        imput.add( comprar );
        comprar.setSize( 20, 20);
        comprar.setVisible( true );
        comprar.setText("comprar");

        //añadir controlador del tablero a los botones de campo jugador
        comprar.addActionListener(ControladorTablero.getController());


        JButton saltarFase=new JButton( );
        imput.add( saltarFase );
        saltarFase.setSize( 20, 20);
        saltarFase.setVisible( true );
        saltarFase.setText("saltarFase");

        //añadir controlador del tablero a los botones de campo jugador
        saltarFase.addActionListener(ControladorTablero.getController());


        JButton deshabilitarAyuda=new JButton( );
        imput.add( deshabilitarAyuda );
        deshabilitarAyuda.setSize( 20, 20);
        deshabilitarAyuda.setVisible( true );
        deshabilitarAyuda.setText("deshabilitarAyuda");

        //añadir controlador del tablero a los botones de campo jugador
        deshabilitarAyuda.addActionListener(ControladorTablero.getController());

        ventana.setLayout(new BorderLayout(0, 0));
        ventana.add(opciones, BorderLayout.WEST);
        ventana.add(middle, BorderLayout.CENTER);
        ventana.add(east, BorderLayout.EAST);
        ventana.add(imput, BorderLayout.SOUTH);

        ventana.setBackground(Color.GRAY);
        ventana.setBorder(new EmptyBorder(5, 5, 5, 5));
        add( ventana );

        middle.setBackground(Color.GRAY);
        middle.setBorder(new EmptyBorder(5, 5, 5, 5));
        middle.setLayout(new GridLayout(pFilas, pColumnas, 0, 0));

        east.setBackground(Color.GRAY);
        east.setBorder(new EmptyBorder(5, 5, 5, 5));
        east.setLayout(new GridLayout(pFilas, pColumnas, 0, 0));

        opciones.setBackground(Color.GRAY);
        opciones.setBorder(new EmptyBorder(5, 5, 5, 5));
        opciones.setLayout(new GridLayout(11, 4, 0, 0));

        //inicializa columnas y filas
        maxCol=pColumnas;
        maxFila=pFilas;
        casillasMiddle =new ArrayList<JButton>(  );
        casillasEast = new ArrayList<JButton>(  );

        //inicializa botones
        for (int i = 0;i<maxCol*maxFila; i++) {
            JButton jButton=new JButton( );
            jButton.setBackground(Color.blue);
            casillasMiddle.add( jButton );
            jButton.setSize( 20, 20);
            jButton.setVisible( true );
            middle.add (jButton);

            //añadir controlador del tablero a los botones de campo jugador
            jButton.addActionListener(ControladorTablero.getController());


            JButton jButton1=new JButton( );
            jButton1.setBackground(Color.white);
            casillasEast.add( jButton1 );
            jButton1.setSize( 20, 20);
            jButton1.setVisible( true );
            east.add( jButton1 );

            //añadir controlador del tablero a los botones de campo ordenador
            jButton1.addActionListener(ControladorTablero.getController());

        }
        opciones.add( new Label( "nombre") );
        opciones.add( nombre=new JLabel( "0" ));
        opciones.add( new JLabel("") );
        opciones.add( new JLabel( "" ));
        opciones.add( new Label( "INFORMACIÖN GENERAL") );
        opciones.add( new JLabel( "" ));
        opciones.add( new JLabel("") );
        opciones.add( new JLabel( "" ));
        opciones.add( new Label( "Dinero") );
        opciones.add( dinero=new JLabel( "0" ));
        opciones.add( new JLabel("usos radar") );
        opciones.add( usosRadar=new JLabel( "0" ));
        opciones.add( new JLabel( "Precio Escudo: ") );
        opciones.add( precioEscudo=new JLabel( "0" ));
        opciones.add( new JLabel("Precio Reparacion: ") );
        opciones.add( precioReparacion=new JLabel( "0" ));
        opciones.add( new Label( "INFORMACIÓN ARMAS") );
        opciones.add( new JLabel( "" ));
        opciones.add( new JLabel("") );
        opciones.add( new JLabel( "" ));
        opciones.add( new JLabel("") );
        opciones.add( new JLabel( "mis armas" ));
        opciones.add( new JLabel("almacén") );
        opciones.add( new JLabel( "precio" ));
        opciones.add( new Label( "bombas") );
        opciones.add( bombas=new JLabel( "0" ) );
        opciones.add( bombasAlmacen=new JLabel( "0" ) );
        opciones.add( precioBomba=new JLabel( "0" ) );
        opciones.add( new Label( "misiles") );
        opciones.add( misiles=new JLabel( "0" ) );;
        opciones.add( misilesAlmacen=new JLabel( "0" ) );
        opciones.add( precioMisil=new JLabel( "0" ) );
        opciones.add( new Label( "misilesNS") );
        opciones.add( misilesNS=new JLabel( "0" ) );
        opciones.add( misilesNSAlmacen=new JLabel( "0" ) );
        opciones.add( precioMisilNS=new JLabel( "0" ) );
        opciones.add( new Label( "misilesEO") );
        opciones.add( misilesEO=new JLabel( "0" ) );
        opciones.add( misilesEOAlmacen=new JLabel( "0" ) );
        opciones.add( precioMisilesEO=new JLabel( "0" ) );
        opciones.add( new Label( "misilesBOOM") );
        opciones.add( misilesBOOM=new JLabel( "0" ));
        opciones.add( misilesBoomAlmacen=new JLabel( "0" ) );
        opciones.add( precioMisilesBoom=new JLabel( "0" ) );

        setVisible( true );
        ControladorTablero.getController().actualizarOpciones();
    }

    /**
     * devuelve el indice del boton independientemente de campo donde se encuentre
     * @param pBoton
     * @return
     */
    public int getIndex(JButton pBoton){
        if(casillasMiddle.contains(pBoton)){
            return casillasMiddle.indexOf(pBoton);
        }else{
            return casillasEast.indexOf(pBoton);
        }
    }

    /**
     * devuelve un String que identifica el campo donde se encuentra del boton dado como parametro
     * @param pBoton
     * @return
     */
    public String tableroDe(JButton pBoton){
        if(casillasMiddle.contains(pBoton)){
            return "jugador";
        }else{
            return "ordenador";
        }
    }

    /**
     * metodo redefinido del observer que actualiza toda la informacion referente al jugador humano
     * @param o
     * @param arg
     */
    @Override
    public void update(Observable o, Object arg) {
        actualizarJugador();
    }
}