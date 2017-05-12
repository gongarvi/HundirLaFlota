package Modelo;

import Controlador.ControladorTablero;

public class Radar {
    /**
     * atributos del radar
     */
    private int numUsos = Battleship.getMyBattleship().getMaxUsosRadar();

    /**
     * constructora de radar
     */
    public Radar() {

    }

    /**
     * devuelve true si los valores (i,j) pertenecen a las casillas al rededor de pPos
     *
     * @param pI
     * @param pJ
     * @param pPos
     * @return
     */
    private boolean pretenecenAreaAccion(int pI, int pJ, Posicion pPos) {
        return (pI == pPos.getX() - 1 || pI == pPos.getX() || pI == pPos.getX() + 1) && (pJ == pPos.getY() - 1 || pJ == pPos.getY() || pJ == pPos.getY() + 1);
    }

    public void colocarRadar(Posicion pPos){
        if(Battleship.getMyBattleship().turnoAct()==0){
            mostrarRadarJugador(pPos);
        }else{
            registrarRadarIA(pPos);
        }
    }

    public void mostrarRadarJugador(Posicion pPos) {
        if (numUsos > 0) {
            if (Battleship.getMyBattleship().getTipoVista().equals("consola")) {
                System.out.println("TableroVista Ordenador:\n");
            }
            numUsos -= 1;
            for (int i = 0; i < Battleship.getMyBattleship().maxFila(); i++) {
                String tablero = "";
                for (int j = 0; j < Battleship.getMyBattleship().maxCol(); j++) {
                    if (pretenecenAreaAccion(i, j, pPos) || Tablero.getMiTablero().barcoHundido(new Posicion(i, j))) {
                        String estado = Tablero.getMiTablero().estadoCampoContrario(new Posicion(i, j));
                        if (estado != null) {
                            if (estado.equals("normal")) {
                                if (Battleship.getMyBattleship().getTipoVista().equals("consola")) {
                                    tablero += " B ";
                                } else {
                                    ControladorTablero.getController().setBotonEnemigo(i, j, "normal");
                                }
                            } else if (estado.equals("tocado")) {
                                if (Battleship.getMyBattleship().getTipoVista().equals("consola")) {
                                    tablero += " T ";
                                } else {
                                    ControladorTablero.getController().setBotonEnemigo(i, j, "tocado");
                                }
                            }
                        } else {
                            if (Battleship.getMyBattleship().getTipoVista().equals("consola")) {
                                tablero += " A ";
                            } else {
                                ControladorTablero.getController().setBotonEnemigo(i, j, "agua");
                            }
                        }
                    } else {
                        if (Battleship.getMyBattleship().getTipoVista().equals("consola")) {
                            tablero += " ? ";
                        }
                    }

                }
                if (Battleship.getMyBattleship().getTipoVista().equals("consola")) {
                    System.out.println(tablero);
                }
            }

        } else {
            if (Battleship.getMyBattleship().getTipoVista().equals("consola")) {
                System.out.println("fallo faltan usos");
            } else {
                ControladorTablero.getController().error("falta de usos");
            }
        }
    }

    public void registrarRadarIA(Posicion pPos) {
        if (numUsos > 0) {
            numUsos -= 1;
            for (int i = 0; i < Battleship.getMyBattleship().maxFila(); i++) {
                for (int j = 0; j < Battleship.getMyBattleship().maxCol(); j++) {
                    Posicion aRegistrar = new Posicion(i, j);
                    if (pretenecenAreaAccion(i, j, pPos) ) {
                        Battleship.getMyBattleship().addRevisadaIA(aRegistrar);
                    }

                }

            }
        }

    }
}
