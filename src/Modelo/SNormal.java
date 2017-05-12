package Modelo;

public class SNormal implements State{
    /**
     * constructora de estado normal
     */
    public SNormal(){

    }

    /**
     * metodo tocar cambia el estado a tocado
     * @return
     */
    @Override
    public State tocar() {
        return new STocado();
    }

    /**
     * metodo  reparar no hace nada
     * @return
     */
    @Override
    public State reparar() {
        return new SNormal();
    }
}
