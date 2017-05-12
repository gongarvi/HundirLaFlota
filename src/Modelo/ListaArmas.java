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
        return ls.get(pArma).peek()!=null;
    }

    /**
     * metodo que devuelve el precio del arma especificada
     * @param pArma
     * @return
     */
    public int getPrecioArma(String pArma) {
        return ls.get(pArma).peek().getPrecio();
    }

    /**
     * metodo que devuelve el número de armas que quedan para el tipo especificado
     * @param pArma
     * @return
     */
    public int getSize(String pArma) {
        System.out.println(pArma);
        System.out.println(ls.get(pArma).size());
        return ls.get(pArma).size();
    }

    /**
     * metodo que añade un arma al tipo de arma especificado
     * @param pTipo
     * @param pArma
     */
    public void añadirArma(String pTipo,Arma pArma) {
            ls.get(pTipo).push(pArma);
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
