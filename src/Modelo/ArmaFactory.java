package Modelo;

public class ArmaFactory {
    /**
     * atributos de la factoria de armas
     */
    private static ArmaFactory miArmaFactory;

    /**
     * constructora privada de la factoria de armas
     */
    private ArmaFactory() {
    }

    /**
     * método que devuelve la única instancia de  la factoria
     * @return
     */
    public static ArmaFactory getArmaFactory() {
        if (miArmaFactory == null) {
            miArmaFactory = new ArmaFactory();
        }
        return miArmaFactory;
    }

    /**
     * método para crear armas
     * @param pTipoArma
     * @return
     */
    public Arma crearArma(String pTipoArma) {
        Arma a = null;
        if (pTipoArma.equalsIgnoreCase("bomba")) {
            a = new Bomba(Battleship.getMyBattleship().getPrecioBomba());
        } else if (pTipoArma.equalsIgnoreCase("misil")) {
            a = new Misil(Battleship.getMyBattleship().getPrecioMisil());
        } else if (pTipoArma.equalsIgnoreCase("misilEO")) {
            a = new MisilEO(Battleship.getMyBattleship().getPrecioMisilEO());
        } else if (pTipoArma.equalsIgnoreCase("misilNS")) {
            a =  new MisilNS(Battleship.getMyBattleship().getPrecioMisilNS());
        } else if (pTipoArma.equalsIgnoreCase("misilBOOM")) {
            a = new MisilBoom(Battleship.getMyBattleship().getPrecioMisilBOOM());
        }
        return a;
    }

}