package Modelo;

import Controlador.ControladorTablero;

import java.util.Collection;

public class Radar {
    /**
     * atributos del radar
     */
    private int numUsos ;

    /**
     * constructora de radar
     */
    public Radar() {numUsos = Battleship.getMyBattleship().getMaxUsosRadar();}

    /**
     * devuelve el número de usos restantes del radar
     * @return
     */
    public int usosRaar(){
        return  numUsos;
    }

    /**
     * devuelve true si la posición pAct pertenece a las casillas al rededor de pPos
     * @param pAct
     * @param pPos
     * @return
     */
    private boolean pretenecenAreaAccion(Posicion pAct, Posicion pPos) {
        Collection<Posicion>colindantes=pPos.colindantes();
        for(Posicion p:colindantes){
            if(p.equals(pAct)){
               return true;
            }
        }
        return false;
    }

    /**
     * método que coloca el radar en la posición dada como parámetro
     * @param pPos
     */
    public void colocarRadar(Posicion pPos){
        if(Battleship.getMyBattleship().turnoAct()==0){
            mostrarRadarJugador(pPos);
        }else{
            registrarRadarIA(pPos);
        }
    }

    /**
     * muestra el area del campo enemigo al jugador
     * @param pPos
     */
    public void mostrarRadarJugador(Posicion pPos) {
        if (numUsos > 0) {
            if (Battleship.getMyBattleship().getTipoVista().equals("consola")) {
                System.out.println("TableroVista Ordenador:\n");
            }
            numUsos -= 1;
            for (int i = 0; i < Battleship.getMyBattleship().maxFila(); i++) {
                String tablero = "";
                for (int j = 0; j < Battleship.getMyBattleship().maxCol(); j++) {
                    Posicion act=new Posicion(i, j);
                    if (pretenecenAreaAccion(act, pPos) || Tablero.getMiTablero().barcoHundidoEnemigo(act)) {
                        boolean esc =Tablero.getMiTablero().escudoEnemigo(act);
                        String estado=Tablero.getMiTablero().estadoCampoContrario(act);
                        if(estado!=null && esc) {
                            if (estado.equals("normal")) {
                                if (Battleship.getMyBattleship().getTipoVista().equals("consola")) {
                                    tablero += " BE ";
                                } else {
                                    ControladorTablero.getController().setBotonEnemigo(i, j, "escudo");
                                }
                            } else if (estado.equals("tocado")) {
                                if (Battleship.getMyBattleship().getTipoVista().equals("consola")) {
                                    tablero += " TE ";
                                } else {
                                    ControladorTablero.getController().setBotonEnemigo(i, j, "escudo");
                                }
                            }
                        }else if(estado!=null && ! esc){
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

    /**
     * registra la posicion avistada en la IA
     * @param pPos
     */
    public void registrarRadarIA(Posicion pPos) {
        if (numUsos > 0) {
            numUsos -= 1;
            for (int i = 0; i < Battleship.getMyBattleship().maxFila(); i++) {
                for (int j = 0; j < Battleship.getMyBattleship().maxCol(); j++) {
                    Posicion aRegistrar = new Posicion(i, j);
                    if (pretenecenAreaAccion(aRegistrar, pPos) ) {
                        Battleship.getMyBattleship().addRevisadaIA(aRegistrar);
                    }

                }

            }
        }

    }
}
