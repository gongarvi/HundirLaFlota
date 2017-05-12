package Modelo;

import Controlador.ControladorTablero;

public abstract class Arma {
    /**
     * atributos arma
     */
    private int precio;

    /**
     * contructora de arma
     * @param pPrecio
     */
    public Arma(int pPrecio){
        precio=pPrecio;
    }

    /**
     * método que devuelve el precio del arma
     * @return
     */
    public int getPrecio(){
      return  precio;
    }

    /**
     * metodo que muestra las casillas afectadas por el tipo de disparo (GUI interna /externa)
     * @param pListaPos
     */
    public void mostrarDisparoJugador(ListaPosiciones pListaPos){
        if(Battleship.getMyBattleship().getTipoVista().equals("consola")) {
            System.out.println("TableroVista Ordenador:\n");
        }
        for(int i=0;i<Battleship.getMyBattleship().maxFila();i++) {

            String tablero ="";
            for (int j = 0; j < Battleship.getMyBattleship().maxCol(); j++) {
                Posicion tmp =new Posicion(i,j);
                if ((pListaPos.pretenecenAreaAccion(tmp))|| Tablero.getMiTablero().barcoHundido(tmp)) {
                    String estado = Tablero.getMiTablero().estadoCampoContrario(tmp);
                    if (estado != null) {
                    if (estado .equals("normal")) {
                        if(Battleship.getMyBattleship().getTipoVista().equals("consola")) {
                            tablero += " B ";
                        }else{
                            ControladorTablero.getController().setBotonEnemigo(i,j,"normal");
                        }
                    } else if (estado .equals("tocado")) {
                        if (Battleship.getMyBattleship().getTipoVista().equals("consola")) {

                            tablero += " T ";
                        } else {
                            ControladorTablero.getController().setBotonEnemigo(i, j, "tocado");
                        }
                    }
                    } else {
                        if(Battleship.getMyBattleship().getTipoVista().equals("consola")) {
                            tablero += " A ";
                        }else{
                            ControladorTablero.getController().setBotonEnemigo(i,j,"agua");
                        }
                    }
                }
                else {
                    if(Battleship.getMyBattleship().getTipoVista().equals("consola")) {
                        tablero += " ? ";
                    }
                }
            }
            if(Battleship.getMyBattleship().getTipoVista().equals("consola")) {
                System.out.println(tablero);
            }
        }
    }

    /**
     * metodo que registra una posicion avistada para la IA
     * @param pListaPos
     */
    public void registrarDisparoIA(ListaPosiciones pListaPos){
        for(int i=0;i<Battleship.getMyBattleship().maxFila();i++) {
            for (int j = 0; j < Battleship.getMyBattleship().maxCol(); j++) {
                Posicion tmp =new Posicion(i,j);
                if ((pListaPos.pretenecenAreaAccion(tmp))) {
                   Battleship.getMyBattleship().addRevisadaIA(tmp);
                }
            }

        }
    }


    /**
     * método abstrcto disparar redefinido en clases inferiores
     * @param pPos
     */
    public abstract void disparar(Posicion pPos);
}
