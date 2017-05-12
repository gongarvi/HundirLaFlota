package Modelo;


public class MisilBoom extends Arma {
    /**
     * constructora de misil Boom
     * @param pPrecio
     */
    public MisilBoom(int pPrecio) {
        super(pPrecio);
    }

    /**
     * método disparar heredado y redefinido
     * @param pPos
     */
    @Override
    public void disparar(Posicion pPos) {
        int x=pPos.getX();
        int y=pPos.getY();
        int[] pivote=new int[2];
        ListaPosiciones tmp=new ListaPosiciones();
        for(int dx=0;dx<Battleship.getMyBattleship().maxFila();dx++) {
            pivote[0]= dx;
            pivote[1]= y;
            pPos.setPosicion(pivote);
            tmp.anadir(new Posicion(pivote[0],pivote[1]));
            Tablero.getMiTablero().hundir(pPos);
        }
        for(int dy=0;dy<Battleship.getMyBattleship().maxCol();dy++) {
            pivote[0]= x;
            pivote[1]= dy;
            pPos.setPosicion(pivote);
            tmp.anadir(new Posicion(pivote[0],pivote[1]));
            Tablero.getMiTablero().hundir(pPos);

        }
        if(Battleship.getMyBattleship().turnoAct()==0){
            System.out.println("muestra");
            mostrarDisparoJugador(tmp);
        }else{
            registrarDisparoIA(tmp);
        }
    }
}
