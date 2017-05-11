# Diseño UI: BattleShip
##Introducción:
En este proyecto se plantea implementar una variante del tradicional juego “Hundir la flota”. Su dinámica básica es similar a la del juego de siempre y se describe brevemente a continuación:
1. Tanto el jugador como el ordenador disponen de una flota formada por 10 barcos de distintos tipos: 1 portaaviones (cuya longitud es 4), 2 submarinos (de longitud 3), 3 destructores (de longitud 2) y 4 fragatas (de longitud 1).
2. El jugador dispone de dos tableros de tamaño 10x10. En uno de ellos, al que llamaremos “flota jugador” colocará los barcos de su flota en las posiciones que considere adecuadas, teniendo en cuenta las siguientes reglas:
-- Las posiciones del tablero en las que no hay barcos, son agua.
-- Los barcos pueden colocarse tanto en posición horizontal como vertical, pero nunca
pueden estar juntos, es decir, cada barco debe estar totalmente rodeado de agua.
3. Su oponente, que en este proyecto es el ordenador, también dispondrá de dos tableros y colocará sobre su tablero “flota ordenador”, los barcos de su flota. Las posiciones en las que ordenador sitúa cada barco se obtendrán de forma aleatoria, respetando siempre las dos reglas previamente expuestas.
4. Una vez colocados los barcos, puede comenzar la batalla en la que cada flota tratará de hundir los barcos del adversario. La flota que consiga dejar sin barcos a su oponente será la ganadora. Para ello, cada flota dispone de armamento de diferentes tipos y de dinero para comprar más armamento o reparar los barcos dañados en el combate. Se consideran, al menos, los siguientes tipos de armamento:
-- Radar: inicialmente se sitúa en una posición del tablero elegida de forma aleatoria. Su función es indicar la posición de un barco enemigo que esté colocado en las posiciones que rodean al radar. Cuenta con un número de consultas posibles que se decrementa cada vez que se utiliza. Es posible moverlo durante el transcurso de la batalla. Una vez finalizadas las consultas que se le pueden hacer, desaparece:
-- Bombas: destruyen únicamente la posición del barco sobre la que caen.
-- Misiles: destruyen el barco que contiene la posición sobre la que impactan.
-- Misiles dirigidos: pueden lanzarse en dirección norte-sur (NS), en dirección este-oeste (EO) o en todas direcciones (BOOM). Los primeros destruyen todos los barcos del enemigo que se encuentren en la horizontal de la posición sobre la que impactan, los segundos acaban con todos los que estén en la vertical y los terceros eliminan los que se encuentran tanto en la horizontal como en la vertical de la posición del impacto.  Escudos: recubren al barco, impidiendo que se dañe por el impacto de una bomba o un misil. El escudo se destruye cuando sobre él impacta un misil o cuando es alcanzado en dos ocasiones por una bomba.
5. El juego también cuenta con un almacén en el que las flotas pueden comprar armamento. Inicialmente el almacén dispondrá de unidades preestablecidas de cada tipo de armamento, que se irán decrementando a medida que las flotas hacen compras. El material del almacén no puede reponerse.
6. Al comienzo del combate, las flotas disponen de una cantidad predeterminada de armamento y de dinero (ambas flotas poseen lo mismo).
7. El juego se desarrolla por turnos, comenzando siempre el jugador. Si la flota de jugador dispone de radar, puede consultar si hay barcos enemigos en alguna posición al alcance de su radar. Para ello, la aplicación debe acceder al tablero “flota ordenador” que contiene los barcos de ordenador y comprobar si en las posiciones alrededor del radar de jugador hay algún barco. En caso afirmativo, devolverá una posición del barco detectado. A continuación jugador realizará un disparo con una unidad de su armamento dirigiéndolo a la posición que desee de la “flota ordenador”. El jugador marcará en su segundo tablero, al que llamaremos “flota adversario”, el disparo realizado y el resultado de su impacto sobre la flota enemiga (agua, tocado, hundido, escudo).
8. Tras un disparo, la flota adversaria (ordenador) decide si desea gastar dinero en comprar armamento o en reparar alguno de sus barcos dañados. Cada tipo de armamento, así como la reparación de un barco tienen un coste preestablecido, que se restará del dinero de la flota que lo compre el armamento o solicite el arreglo. 
9. A continuación, el turno pasa al ordenador, repitiéndose el proceso: ordenador podrá consultar su radar y disparar con su armamento sobre alguna posición de la flota del jugador. Para ello, ordenador debe disponer de algún método de ataque que determine cuál es el armamento y la posición adecuados para hacer el disparo.
10. Es necesario implementar adecuadamente métodos para determinar las propiedades de los disparos que van realizando tanto ordenador como jugador durante la partida, además de mantener actualizados los tableros de “flota adversario” con la información del estado de la “flota jugador” y de la “flota ordenador”.
Este proyecto se realizará en grupo, siguiendo un proceso inspirado en la metodología SCRUM, en el que se establecerán tres sprint. Antes de comenzar a realizar el proyecto, los miembros del grupo deben leer detenidamente este enunciado general y establecer los valores que desean asignar a cada propiedad para la que se indica que tiene un valor inicial predeterminado. Como valor añadido, en este proyecto hay que implementar la estrategia que utiliza ordenador para determinar la posición del tablero “flota jugador” sobre la que quiere disparar, así como para elegir el armamento con el que desea hacerlo.
# Primer Sprint
-

# Segundo Sprint - Battleship

# Tercer Sprint 