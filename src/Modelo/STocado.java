package Modelo;

public class STocado  implements State{

    /**
     * constructora de estados tocado
     */
    public  STocado(){

    }

    /**
     * método tocar que cambia el estado a tocado
     * @return
     */
    @Override
    public State tocar() {
        return new STocado();
    }

    /**
     * método reparar que cambia el estado a normal
     * @return
     */
    @Override
    public State reparar() {
        return new SNormal();
    }
}