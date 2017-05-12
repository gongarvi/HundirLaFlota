package Modelo;


public class MisilEO extends Arma{

    /**
     * constructora de misil EO
     * @param pPrecio
     */
    public MisilEO(int pPrecio) {
        super(pPrecio);
    }

    /**
     * método disparar heredado y redefinido
     * @param pPos
     */
    @Override
    public void disparar(Posicion pPos) {
        int[] pivote=new int[2];
        ListaPosiciones tmp=new ListaPosiciones();
        for(int dx=0;dx<Battleship.getMyBattleship().maxCol();dx++) {
            pivote[0]=dx;
            pivote[1]=pPos.getY();
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
