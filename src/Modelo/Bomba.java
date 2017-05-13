package Modelo;


public class Bomba extends Arma {


    /**
     * constructora de bomba
     */
    public Bomba() {

    }

    /**
     * método disparar heredado y redefinido
     * @param pPos
     */
    @Override
    public void disparar(Posicion pPos) {
        Tablero.getMiTablero().impactar(pPos);
        ListaPosiciones tmp=new ListaPosiciones();
        tmp.anadir(pPos);
        if(Battleship.getMyBattleship().turnoAct()==0){
            mostrarDisparoJugador(tmp);
        }else{
            registrarDisparoIA(tmp);
        }
    }


}