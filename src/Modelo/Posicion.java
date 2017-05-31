package Modelo;

import Controlador.ControladorTablero;

import java.util.ArrayList;
import java.util.Collection;

public class Posicion {

    /**
     * atributos
     */
    private State estado;
    private int[] posicion;

    /**
     * constructora de posicion
     * @param x
     * @param y
     */
    public Posicion(int x, int y) {
        posicion = new int[2];
        posicion[0] = x;
        posicion[1] = y;
    }

    /**
     * tocar la posición
     */
    public void tocar(){
       setState(estado.tocar());
    }

    /**
     * reparar posicion
     */
    public void reparar(){
        if(estado instanceof STocado) {
            setState(estado.reparar());
        }else if(Battleship.getMyBattleship().turnoAct()==0) {
            if (Battleship.getMyBattleship().getTipoVista().equals("consola")) {
                System.out.println("no está tocado");
                Battleship.getMyBattleship().cancelarCompra(Battleship.getMyBattleship().getPrecioEscudo());
            } else {
                ControladorTablero.getController().error("no está tocado");
                Battleship.getMyBattleship().cancelarCompra(Battleship.getMyBattleship().getPrecioEscudo());
            }
        }
    }

    /**
     * setear nuevo estado al dado como parametro
     * @param pEstado
     */
    public void setState(State pEstado) {
        this.estado = pEstado;
    }

    /**
     * recoger estado de la posicion
     * @return
     */
    public State getEstado() {
        return this.estado;
    }

    /**
     * devuelve la lista de posiciones colindantes a esta posicion
     * @return
     */
    public Collection<Posicion> colindantes(){
        int posX=posicion[0];
        int posY=posicion[1];
        Collection<Posicion> tmp=new ArrayList<>();
        for(int i=posX-1;i<posX+2;i++){
            for(int j=posY-1;j<posY+2;j++){
                tmp.add(new Posicion(i,j));
            }
        }
        return tmp;
    }

    /**
     * devuelve el valor x de la posicion
     * @return
     */
    public int getX() {
        return posicion[0];
    }

    /**
     * devuelve el valor y de la posicion
     * @return
     */
    public int getY() {
        return posicion[1];
    }

    /**
     * setea la posicion con los valores dados como parametro
     * @param posicion
     */
    public void setPosicion(int[] posicion) {
        this.posicion = posicion;
    }

    /**
     * devuelve true si esta y la posicion dada como parametro son iguales
     * @param other
     * @return
     */
    public boolean equals(Posicion other){
        return posicion[0]==other.getX()&&posicion[1]==other.getY();

    }
}