package Modelo;


public class MisilEO extends Arma{

    /**
     * constructora de misil EO
     */
    public MisilEO() {

    }

    /**
     * método disparar heredado y redefinido
     * @param pPos
     */
    @Override
    public void disparar(Posicion pPos) {
        int[] pivote=new int[2];
        ListaPosiciones tmp=new ListaPosiciones();
        for(int dy=0;dy<Battleship.getMyBattleship().maxCol();dy++) {
            pivote[0]= pPos.getX();
            pivote[1]= dy;
            pPos.setPosicion(pivote);
            tmp.anadir(new Posicion(pivote[0],pivote[1]));
            Tablero.getMiTablero().hundir(pPos);
        }
        if(Battleship.getMyBattleship().turnoAct()==0){
            mostrarDisparoJugador(tmp);
        }else{
            registrarDisparoIA(tmp);
        }

    }
}
