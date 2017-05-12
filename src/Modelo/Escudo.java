package Modelo;

public class Escudo {
    /**
     * atributos de la clase escudo
     */
    private int impactosRestantes;

    /**
     * constructora de escudos
     */
    public Escudo() {
        this.impactosRestantes = 2;
    }

    /**
     * método que devuelve el número de impacts restantes del escudo
     * @return
     */
    public int getImpactosRestantes() {
        return this.impactosRestantes;
    }

    /**
     * método que destruye el escudo (directamente)
     */
    public void destruir() {
        this.impactosRestantes=0;

    }

    /**
     * método que reduce la durabilidad del escudo en un punto
     * @return
     */
    public boolean recibirImpacto() {
        this.impactosRestantes--;
        return impactosRestantes==0;

    }
}