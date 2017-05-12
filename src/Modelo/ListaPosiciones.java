package Modelo;

import java.util.ArrayList;
import java.util.Iterator;

public  class ListaPosiciones {
    /**
     * atributos
     */
    public ArrayList<Posicion> posiciones;

    /**
     * devuelve un iterador de posiciones
     * @return
     */
    private Iterator<Posicion> getIterador() {
        return posiciones.iterator();
    }

    /**
     * constructora de listas de posiciones sin arggs
     */
    public ListaPosiciones(){
        posiciones=new ArrayList<>();
    }
    /**
     * constructora de listas de posiciones con una longitud limitada (para barcos) por defecto a(-1,-1)
     */
    public ListaPosiciones(String pTipo){
        posiciones=new ArrayList<>();
        for(int i=0;i<Battleship.getMyBattleship().getLength(pTipo);i++){
            Posicion pPos=new Posicion(-1,-1);
            pPos.setState(new SNormal());
            posiciones.add(pPos);
        }
    }

    /**
     * añade una nueva posicion
     * @param pPos
     */
    public void anadir(Posicion pPos){
        posiciones.add(pPos);
    }

    /**
     * hunde la posicion especificada
     */
    public void hundir(){
       Iterator<Posicion>it=getIterador();
       while(it.hasNext()){
           Posicion act=it.next();
           act.tocar();
        }
    }

    /**
     * impacta la posicion especificada
     * @param pPos
     */
    public void recibirDanios(Posicion pPos) {
        Iterator<Posicion>it=getIterador();
        boolean tocado=false;
        while(it.hasNext() && !tocado){
            Posicion act=it.next();
            if(act.equals(pPos)) {
                act.tocar();
                tocado=true;
            }
        }
    }

    /**
     * devuelve true si la lista contiene posiciones que aun no estan inicializadas
     * @return
     */
    public boolean noInicializado(){
        Iterator<Posicion>it=getIterador();
        boolean noinicializado=true;
        while(it.hasNext() && noinicializado){
            Posicion act=it.next();
            if(act.getX()!= -1 && act.getY() != -1) {
                noinicializado=false;
            }
        }
        return  noinicializado;
    }

    /**
     * método que devuelve true si la posicion dada pertenece ala lista de posiciones
     * @param pPos
     * @return
     */
    public boolean pretenecenAreaAccion(Posicion pPos){
        Iterator<Posicion> it=getIterador();
        boolean pertenece=false;
        while(it.hasNext() && !pertenece){
            Posicion act=it.next();
            pertenece=pPos.equals(act);
        }
        return pertenece;
    }

    /**
     * devuelve la longitud de la lista de posiciones
     * @return
     */
    public int size(){
        return posiciones.size();
    }

    /**
     * devolvera true si la lista de posiciones es colindante a la lista de posiciones deducibel de los parametros
     * @param pPivote
     * @param pDireccion
     * @param pLength
     * @return
     */
    public boolean esColindante(int[] pPivote, String pDireccion, int pLength){

        Iterator<Posicion>it=getIterador();
        boolean colindante=false;
        while(it.hasNext() && !colindante){
            Posicion act=it.next();
            int pX = pPivote[0];
            int pY = pPivote[1];
            int x = act.getX();
            int y = act.getY();
            int indexPos = 0;
            while (indexPos != pLength && !colindante) {
                colindante =colindante || (pX == x && pY == y);
                colindante =colindante || (pX + 1 == x && pY == y);
                colindante =colindante || (pX - 1 == x && pY == y);
                colindante =colindante || (pX == x && pY - 1 == y);
                colindante =colindante || (pX == x && pY + 1 == y);
                if (pDireccion.equals("V")) {
                    ++pX;
                } else {
                    ++pY;
                }
                indexPos++;
            }
        }
        return colindante;
    }

    /**
     * reparar la posicion especificada
     * @param pPos
     */
    public void reparar(Posicion pPos){
        Iterator<Posicion>it=getIterador();
        boolean reparado=false;
        while(it.hasNext() && !reparado){
            Posicion act=it.next();
            if(act.equals(pPos)) {
                act.reparar();
                reparado=true;
            }
        }
    }

    /**
     * devolvera true si todas sus posiciones estan tocadas
     * @return
     */
    public boolean estaHundido(){
        Iterator<Posicion>it=getIterador();
        boolean hundido=true;
        while(it.hasNext() && hundido){
            Posicion act=it.next();
            if(act.getEstado() instanceof SNormal){
                hundido=false;
            }
        }
        return hundido;
    }

    /**
     * devuelve el estado de la posicion especificada como parametro
     * @param pPos
     * @return
     */
    public String estadoPos(Posicion pPos){
        Iterator<Posicion>it=getIterador();
        boolean enc=false;
        State estado=null;
        while(it.hasNext() && !enc){
            Posicion act=it.next();
            if(act.equals(pPos)){
                enc=true;
                estado=act.getEstado();
                if(estado instanceof STocado){
                    return "tocado";
                }else{
                    return  "normal";
                }

            }
        }
        return null;
    }

    /**
     * devuelve la primera posicion cuyo estado sea normal
     * @return
     */
    public Posicion contieneNormal(){
        for(Posicion p:posiciones){
            String estado=Tablero.getMiTablero().estadoCampoContrario(p);
            if(estado!=null && estado.equals("normal")){
                return p;
            }
        }
        return null;
    }

    /**
     * devuelve la primera posicion cuyo estado sea normal
     * @return
     */
    public Posicion contieneTocadoNoHundido(){
        for(Posicion p:posiciones){
            String estado=Tablero.getMiTablero().estadoCampoContrario(p);
            if(estado!=null && estado.equals("tocado") && !Tablero.getMiTablero().barcoHundido(p)){
                return p;
            }
        }
        return null;
    }

    /**
     * inicializa la lista de posiciones con las posiciones deducibles de los parametros dados
     * @param pivote
     * @param direccion
     */
    public void inicializar (int[] pivote ,String direccion ) {
        int x = pivote[0], i = 0,y = pivote[1];
        if (direccion.equals("H")) {
            while (i < posiciones.size()) {
                int[]tmp=new int[2];
                tmp[0] = x;
                tmp[1] = y;
                posiciones.get(i).setPosicion(tmp);
                y++;
                i++;

            }

        } else {
            while (i <  posiciones.size()) {
                int[]tmp=new int[2];
                tmp[0] = x;
                tmp[1] = y;
                posiciones.get(i).setPosicion(tmp);
                x++;
                i++;

            }
        }
    }

    /**
     * devuelve true si la lista contiene la posicion dada como parametro
     * @param pos
     * @return
     */
    public boolean contiene(Posicion pos){
        Iterator<Posicion> it=getIterador();
        boolean enc=false;
        while(it.hasNext()&& !enc){
            Posicion act=it.next();
            enc= act.equals(pos);
        }
        return enc;
    }
}
