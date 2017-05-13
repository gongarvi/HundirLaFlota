package Modelo;

import java.util.HashMap;
import java.util.Stack;

public class ListaArmas {

    /**
     * coleccion que guarda las armas
     */
    HashMap<String, Stack<Arma>> ls;

    public ListaArmas() {
        ls = new HashMap<>();
        ls.put("bomba", new Stack<>());
        ls.put("misil", new Stack<>());
        ls.put("misilNS", new Stack<>());
        ls.put("misilBoom", new Stack<>());
        ls.put("misilEO", new Stack<>());
    }

    /**
     * metodo que devuelve true si el arma especifica existe y false si no existe
     * @param pArma
     * @return
     */
    public boolean consultarArma(String pArma) {
        return !ls.get(pArma).isEmpty();
    }


    /**
     * metodo que devuelve el número de armas que quedan para el tipo especificado
     * @param pArma
     * @return
     */
    public int getSize(String pArma) {
        return ls.get(pArma).size();
    }

    /**
     * metodo que añade un arma al tipo de arma especificado
     * @param pTipo
     */
    public void añadirArma(String pTipo) {
            ls.get(pTipo).push(ArmaFactory.getArmaFactory().crearArma(pTipo));
    }

    /**
     * eliminar el arma especifica de la coleccion y la devuelve
     * @param pTipo
     * @return
     */
    public Arma removeArma(String pTipo) {
        if (consultarArma(pTipo)){
            return ls.get(pTipo).pop();
        }else{
            return null;
        }
    }

}
