package Modelo;

public class Misil extends Arma {

    /**
     * constructora de misil
     * @param pPrecio
     */
    public Misil(int pPrecio) {
        super(pPrecio);
    }

    /**
     * método disparar heredado y redefinido
     * @param pPos
     */
    @Override
    public void disparar(Posicion pPos) {
        Tablero.getMiTablero().hundir(pPos);
        ListaPosiciones tmp=new ListaPosiciones();
        tmp.anadir(pPos);
        if(Battleship.getMyBattleship().turnoAct()==0){
            mostrarDisparoJugador(tmp);
        }else{
            registrarDisparoIA(tmp);
        }
    }
}