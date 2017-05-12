package Modelo;

public abstract class Barco {
    /**
     * atributos de barco
     */
    private ListaPosiciones partesBarco;
    private Escudo escudo;
    private boolean hundido;

    /**
     * constructora de barco
     */
    public Barco(String pTipo) {
        escudo = null;
        hundido = false;
        partesBarco=new ListaPosiciones(pTipo);
    }

    /**
     * método para comprobar si un barco esta hundido
     * @return
     */
    public boolean hundido(){return hundido;}

    /**
     *
     * @param pPosiciones
     */
    public void setPosiciones(ListaPosiciones pPosiciones){
    	partesBarco=pPosiciones;
    }

    /**
     * comprueba si el barco es colindante a la lista de posiciones que se deduce mediante los parametros dados
     * @param pPivote
     * @param direccion
     * @param length
     * @return
     */
    public boolean escolindante(int[] pPivote, String direccion, int length){
        return partesBarco.esColindante(pPivote,direccion,length);
    }

    /**
     * comprueba si la longitud del barco coincide con la dada
     * @param length
     * @return
     */
    public boolean esBarcoCorrecto(int length)
    {
        return  partesBarco.size()==length ;
    }

    /**
     * comprueba si  las posiciones del barco estan inicializadas
     * @return
     */
    public boolean noInicializado(){
        return partesBarco.noInicializado();
    }

    /**
     * recupera el estado de una posicion concreta del barco
     * @param pPos
     * @return
     */
    public String estadoPos(Posicion pPos){
        return partesBarco.estadoPos(pPos);
    }

    /**
     * repara la posicion seleccionada del barco
     * @param pPos
     */
    public void reparar(Posicion pPos){
        partesBarco.reparar(pPos);
    }

    /**
     * devuelve true si entre las partes del barco se encuentra la posicion dada como parametro
     * @param pos
     * @return
     */
    public boolean contiene(Posicion pos){
       return  partesBarco.contiene(pos);
    }

    /**
     * inicializa las posiciones del barco
     * @param pivote
     * @param direccion
     */
    public void inicializar (int[] pivote ,String direccion ) {
       partesBarco.inicializar(pivote,direccion);
    }

    /**
     * metodo que settea un escudo al barco
     * @param esc
     */
    public void setEscudo(Escudo esc) {
        this.escudo = esc;
    }

    /**
     * metodo que hunde el barco
     */
    public void hundir(){
        if(escudo!=null){
            escudo.destruir();
            escudo=null;
        }else {
            partesBarco.hundir();
            hundido=true;
        }
    }

    /**
     *
     * @param pPos
     */
    public void recibirDanios(Posicion pPos) {
        if(escudo!=null){
            if(escudo.recibirImpacto()){
                escudo=null;
            }
        }else {
            partesBarco.recibirDanios(pPos);
            hundido=partesBarco.estaHundido();
        }
    }

    /**
     * método qe
     * @return
     */
    public boolean tieneEscudo(){
        return  escudo!=null;
    }
}