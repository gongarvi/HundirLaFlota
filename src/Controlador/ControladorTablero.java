package Controlador;

import Modelo.Battleship;
import Vista.TableroVista;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;

public class ControladorTablero extends Observable implements ActionListener {
    /**
     * atributos del controlador
     */
    private static ControladorTablero controller;
    private  int posAct;

    /**
     * constructora del controlador de tablero
     */
    private ControladorTablero() {
        addObserver(TableroVista.getTablero());
    }

    /**
     * recuperar la instancia única del controlador
     * @return
     */
    public static ControladorTablero getController() {

        if(controller==null){
            controller=new ControladorTablero();
        }
        return controller;
    }

    /**
     * método para realizar una petición de actualizacion de opciones (Modelo)
     */
    public void actualizarOpciones(){Battleship.getMyBattleship().mostrarOpciones();}

    /**
     * metodo para inicializar la GUI externa (Vista)
     */
    public void inicializarGui(){TableroVista.getTablero().inicializarVista(maxFila(),maxColumna());}

    /**
     * método para generar una peticion de actualizacion del campo aliado (Modelo)
     */
    public void mostrarFlotaJugador(){Battleship.getMyBattleship().mostrarCampoAliado();}

    /**
     * método que actualiza en la vista la opcion deseada con el texto correspondiente (Vista)
     * @param pOpcion
     * @param pTexto
     */
    public void setOpcion(String pOpcion,String pTexto){TableroVista.getTablero().actualizarOpciones(pOpcion,pTexto);}

    /**
     * método que indexa la fase para mostrar una ventana de información u otra (Vista)
     * @param pFase
     */
    public void fase(int pFase){
        if(pFase==Battleship.getMyBattleship().getFaseRadar()){
            TableroVista.getTablero().faseRadar();
        }else if(pFase==Battleship.getMyBattleship().getFaseCompraYEscudo()){
            TableroVista.getTablero().faseComprarYEScudo();
        }else if(pFase==Battleship.getMyBattleship().getFaseDisparo()){
            TableroVista.getTablero().faseDisparo();
        }else if(pFase==Battleship.getMyBattleship().getFaseReparacion()){
            TableroVista.getTablero().faseReparar();
        }else  if(pFase==Battleship.getMyBattleship().getFaseInicial()){
            TableroVista.getTablero().faseColocacion();
        }else  if(pFase==Battleship.getMyBattleship().getFaseTurnoIA()){
            TableroVista.getTablero().faseTurnoIA();
        }
    }

    /**
     * devuelve la seleccion de arma (Vista)
     * @return
     */
    public String getImputSelect(){return TableroVista.getTablero().getSeleccion();}

    /**
     * devuelve la seleccion de tipo de barco (Vista)
     * @return
     */
    public String getTipoBarco(){return TableroVista.getTablero().getTipoBarco();}

    /**
     * lanza una ventana que pedira los datos necesarios para colocar un barco (Vista)
     */
    private void pedirDatos(){TableroVista.getTablero().recogerInfo();}

    /**
     * devuelve la orientacion seleccionada (Vista)
     * @return
     */
    public String getOrientacion(){return TableroVista.getTablero().getOrientacion();}

    /**
     * devuelve el String con la cantidad de barcos que falta por colocar  (Modelo)
     * @return
     */
    public String cuantosBarcosQuedan(){
        return Battleship.getMyBattleship().cuantosBarcosQuedan();
    }

    /**
     * lanza una ventana de error con el mensaje especificado eludible (Vista)
     * @param pError
     */
    public void error(String pError){TableroVista.getTablero().popUpError(pError);}

    /**
     * lanza un mensaje de informacion que no se puede eludir (Vista)
     */
    public void recuerda(){TableroVista.getTablero().popUpInformacion("recuerda que si quieres recibir ayuda tendras que habilitar los comentarios");}

    /**
     * settea el boton aliado seleccionado (x,y) al estado de dicha casilla en el modelo  (Vista)
     * @param pX
     * @param pY
     * @param pEstado
     */
    public void setBotonAliado(int pX,int pY,String pEstado){TableroVista.getTablero().actualizarCampoAliado(convertirABoton(pX,pY),pEstado);}


    /**
     * settea el boton enemigo seleccionado (x,y) al estado de dicha casilla en el modelo  (Vista)
     * @param pX
     * @param pY
     * @param pEstado
     */
    public void setBotonEnemigo(int pX,int pY,String pEstado){TableroVista.getTablero().actualizarCampoEnemigo(convertirABoton(pX,pY),pEstado);}

    /**
     * devuelve el numero máximo de fias del tablero (Modelo)
     * @return
     */
    public int maxFila(){return Battleship.getMyBattleship().maxFila();}

    /**
     * devuelve el numero máximo de columnas del tablero (Modelo)
     * @return
     */
    public int maxColumna(){return Battleship.getMyBattleship().maxCol();}

    /**
     * método que dada una coordenada (x,y)  devuelve el index del boton al que se refieren
     * @param pX
     * @param pY
     * @return
     */
    private int convertirABoton(int pX,int pY){return (pX*10)+pY;}

    /**
     * método que notifica a los observer que ha ocurrido un cambio en el modelo
     */
    public void changed(){
        setChanged();
        notifyObservers();
        clearChanged();
    }

    /**
     * gestor de eventos asociados a la vista (Modelo - Vista)
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {

        //recoger posicion del boton que lo llamo...

        AbstractButton btnSeleccionado= (AbstractButton)e.getSource();
        if(btnSeleccionado.getText().equals("entendido")) {
            TableroVista.getTablero().disposePopUp();
        }else if(btnSeleccionado.getText().equals("saltarFase")) {
            Battleship.getMyBattleship().saltarFase();
        }else if(btnSeleccionado.getText().equals("comprar")) {
            Battleship.getMyBattleship().comprar();
        }else if(btnSeleccionado.getText().equals("deshabilitarAyuda")) {
            TableroVista.getTablero().skipHints();
        }else if(btnSeleccionado.getText().equals("añadirInfo")) {
            TableroVista.getTablero().disposePopUp();
            Battleship.getMyBattleship().gestionarCampoAliado(posAct);
        }else {
            int clickada = TableroVista.getTablero().getIndex((JButton) btnSeleccionado);
            String tablero = TableroVista.getTablero().tableroDe((JButton) e.getSource());
            if (tablero.equals("jugador")) {
                //set Escudo o reparar
                if(Battleship.getMyBattleship().getFaseAct()==0){
                    posAct=clickada;
                    ControladorTablero.getController().pedirDatos();
                }else{
                    Battleship.getMyBattleship().gestionarCampoAliado(clickada);
                }
            } else {
                //set Disparar o colocarRadar
                Battleship.getMyBattleship().gestionarCampoEnemigo(clickada);
            }
        }
    }
}