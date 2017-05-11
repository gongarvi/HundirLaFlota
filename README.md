# Diseño UI

# segundo Sprint - Battleship

## Importante

- compilad y ejecutad el programa y vereis como muestra cada paso de interacción con el usuario, solo hay que seguir las instrucciones (el ordenador no tiene ia)
-topologia del mapa
    ?=desconocido
    A=agua
    B=cubierta
    T=tocado
- he cambiado  todas las clases que he visto necesario modificar para que el programa siguiera el diseño que os he enviado por email
- destacare cambios en las clases (mas relevantes):
        simplificada la interacción con la flota , añado método especificos para cada operación(he probado todos pero sin el testing)
        los jugadores poseen la capacidad de visionaar su campo mediante conprobacion de Posicion , en todos los puntos donde aparezca :" T " ó " B " ó " A " ó " ? " ,se pueden insertar llamadas a controladores o a  vista o a lo que se quiera para probar
        los metodos de inicialización de barcos incorporan la clase "ListaPosicion" que contiene toda la funcionalidad para gestionar laspartes del barco , de esta misma clase desciendes tantos tipos de listas como tipos de barco contiene el juego con la redefinición de su inicialización
        Flota desaparece por que a mi parecer es redundante a una ListaBarcos
        la secuencia de inicializacion del tablero requiere de turno
        clase misilDirig pasa a ser tres clases: MisilBoom , misilNS ,misilEO
        el método disparar lleva hacia adelante la ejecución hasta las posiciones de un barco, por otro lado ,sigue un patrón strategy
        aplicados cambion pertinentes en los modos de creacion de objetos(factorias) e inicializadores de varias clases (acomodaciones nada más)
        actualmente esta configurada la flotahumana para que su inicialización sea automática (pruebas) , si quereis probar manualmente cambiad el método Battleship.colocarFlotas por:

    public void colocarFlotas() {
       humano.colocarBarcos();
       cambiarTurno();
       ia.colocarBarcos();
       cambiarTurno();
    }

        a lo largo de las ejecuciones podreis visualizar los mensajes de fallo de cada método , esos puntos podrian aprovecharse para lanzar excepciones o ventanas emergentes
        la estructura intrinseca de las clases sigue intacta aunque he dejado de usar las clases:
            Flota
            HerramientasJuego
            GestorFicheros(tenia problemas a la hora de recibir resultados y decidi meterlos a mano en ciertos puntos , ayuda con eso porfa Josu ;) )
            MisilDirig


- disculpa de antemano por no haber estado activo estos dias , no he tenido casi tiempo para nada , si necesitais aclarar algo del codigo o alguna explicación no dudeis en mandarme whatsapp