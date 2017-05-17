# Diseño UI: BattleShip
##
# Introducción:
En este proyecto se plantea implementar una variante del tradicional juego “Hundir la flota”. Su dinámica básica es similar a la del juego de siempre y se describe brevemente a continuación:
- Tanto el jugador como el ordenador disponen de una flota formada por 10 barcos de distintos tipos: 1 portaaviones (cuya longitud es 4), 2 submarinos (de longitud 3), 3 destructores (de longitud 2) y 4 fragatas (de longitud 1).
- El jugador dispone de dos tableros de tamaño 10x10. En uno de ellos, al que llamaremos “flota jugador” colocará los barcos de su flota en las posiciones que considere adecuadas, teniendo en cuenta las siguientes reglas:
	- Las posiciones del tablero en las que no hay barcos, son agua.
	- Los barcos pueden colocarse tanto en posición horizontal como vertical, pero nunca
pueden estar juntos, es decir, cada barco debe estar totalmente rodeado de agua.
- Su oponente, que en este proyecto es el ordenador, también dispondrá de dos tableros y colocará sobre su tablero “flota ordenador”, los barcos de su flota. Las posiciones en las que ordenador sitúa cada barco se obtendrán de forma aleatoria, respetando siempre las dos reglas previamente expuestas.
-Una vez colocados los barcos, puede comenzar la batalla en la que cada flota tratará de hundir los barcos del adversario. La flota que consiga dejar sin barcos a su oponente será la ganadora. Para ello, cada flota dispone de armamento de diferentes tipos y de dinero para comprar más armamento o reparar los barcos dañados en el combate. Se consideran, al menos, los siguientes tipos de armamento:
	- Radar: inicialmente se sitúa en una posición del tablero elegida de form aleatoria. Su función es indicar la posición de un barco enemigo que esté colocado en las posiciones que rodean al radar. Cuenta con un número de consultas posibles que se decrementa cada vez que se utiliza. Es posible moverlo durante el transcurso de la batalla. Una vez finalizadas las consultas que se le pueden hacer, desaparece:
	- Bombas: destruyen únicamente la posición del barco sobre la que caen.
	- Misiles: destruyen el barco que contiene la posición sobre la que impactan.
	- Misiles dirigidos: pueden lanzarse en dirección norte-sur (NS), en dirección este-oeste (EO) o en todas direcciones (BOOM). Los primeros destruyen todos los barcos del enemigo que se encuentren en la horizontal de la posición sobre la que impactan, los segundos acaban con todos los que estén en la vertical y los terceros eliminan los que se encuentran tanto en la horizontal como en la vertical de la posición del impacto.  Escudos: recubren al barco, impidiendo que se dañe por el impacto de una bomba o un misil. El escudo se destruye cuando sobre él impacta un misil o cuando es alcanzado en dos ocasiones por una bomba.
- El juego también cuenta con un almacén en el que las flotas pueden comprar armamento. Inicialmente el almacén dispondrá de unidades preestablecidas de cada tipo de armamento, que se irán decrementando a medida que las flotas hacen compras. El material del almacén no puede reponerse.
- Al comienzo del combate, las flotas disponen de una cantidad predeterminada de armamento y de dinero (ambas flotas poseen lo mismo).
- El juego se desarrolla por turnos, comenzando siempre el jugador. Si la flota de jugador dispone de radar, puede consultar si hay barcos enemigos en alguna posición al alcance de su radar. Para ello, la aplicación debe acceder al tablero “flota ordenador” que contiene los barcos de ordenador y comprobar si en las posiciones alrededor del radar de jugador hay algún barco. En caso afirmativo, devolverá una posición del barco detectado. A continuación jugador realizará un disparo con una unidad de su armamento dirigiéndolo a la posición que desee de la “flota ordenador”. El jugador marcará en su segundo tablero, al que llamaremos “flota adversario”, el disparo realizado y el resultado de su impacto sobre la flota enemiga (agua, tocado, hundido, escudo).
- Tras un disparo, la flota adversaria (ordenador) decide si desea gastar dinero en comprar armamento o en reparar alguno de sus barcos dañados. Cada tipo de armamento, así como la reparación de un barco tienen un coste preestablecido, que se restará del dinero de la flota que lo compre el armamento o solicite el arreglo. 
- A continuación, el turno pasa al ordenador, repitiéndose el proceso: ordenador podrá consultar su radar y disparar con su armamento sobre alguna posición de la flota del jugador. Para ello, ordenador debe disponer de algún método de ataque que determine cuál es el armamento y la posición adecuados para hacer el disparo.
- Es necesario implementar adecuadamente métodos para determinar las propiedades de los disparos que van realizando tanto ordenador como jugador durante la partida, además de mantener actualizados los tableros de “flota adversario” con la información del estado de la “flota jugador” y de la “flota ordenador”.
Este proyecto se realizará en grupo, siguiendo un proceso inspirado en la metodología SCRUM, en el que se establecerán tres sprint. Antes de comenzar a realizar el proyecto, los miembros del grupo deben leer detenidamente este enunciado general y establecer los valores que desean asignar a cada propiedad para la que se indica que tiene un valor inicial predeterminado. Como valor añadido, en este proyecto hay que implementar la estrategia que utiliza ordenador para determinar la posición del tablero “flota jugador” sobre la que quiere disparar, así como para elegir el armamento con el que desea hacerlo.

# Diagrama de clases:
![logo_preview](http://i.imgur.com/loBCsOI.png)
##
# Primer Sprint
![logo_preview](http://i.imgur.com/iZCc8tv.png)
![logo_preview](http://i.imgur.com/EHBvhJy.png)
![logo_preview](http://i.imgur.com/bZJjh9L.png)
![logo_preview](http://i.imgur.com/i52UHKf.png)
##
	# HU1: Inicializar el juego
		- Colocar los barcos de “flota jugador” solicitando al jugador las posiciones en las que desea situar 
		sus barcos. Es necesario que las posiciones en las que se sitúen los barcos respeten los dos requisitos
		especificados en el enunciado general.
		- Colocar los barcos de “flota ordenador” en posiciones obtenidas aleatoriamente. Es necesario que las
		posiciones en las que se sitúen los barcos respeten los dos requisitos especificados en el enunciado general.
		- Asociar a ambas flotas el armamento y el dinero inicial.
		- Establecer el número de consultas del radar.		
		- Inicializar la información de “flota adversario” del jugador y del ordenador.
		- Inicializar el almacén con los distintos tipos de armamento que tiene, las unidades disponibles y
		su precio unitario.
		- Establecer el precio de las reparaciones de los barcos.
	# HU2: Activar escudo jugador
		- Si el jugador dispone de algún escudo, se activa sobre el barco que indique el jugador.
	# HU3: Activar escudo ordenador
		- Si el ordenador dispone de algún escudo, se activa sobre el barco que indique el ordenador.
##
	
# Segundo Sprint
![logo_preview](http://i.imgur.com/iA07MUR.png)
![logo_preview](http://i.imgur.com/z0hBoYA.png)
![logo_preview](http://i.imgur.com/iZCc8tv.png)
![logo_preview](http://i.imgur.com/j4nB3P3.png)
##
	# HU4: Consultar radar jugador
		- El jugador consulta su radar para comprobar si hay algún barco de la “flota ordenador” en las posiciones
		que rodean al radar de jugador. En caso afirmativo, devolverá una posición del barco detectado. Antes de
		hacer la consulta puede desplazar el radar a otra posición del tablero.
	# HU5: Consultar radar ordenador
		- El ordenador consulta su radar para comprobar si hay algún barco de la “flota jugador” en las posiciones
		que rodean al radar de ordenador. En caso afirmativo, devolverá una posición del barco detectado. Antes de
		hacer la consulta puede desplazar el radar a otra posición del tablero.
	# HU6: Disparar el jugador
		- El jugador indica las coordenadas sobre las que desea disparar y el armamento que desea utilizar; el ges-
		tor	del juego determina el efecto del disparo sobre la “flota del ordenador”. Además muestra en pantalla 
		su resultado.
	# HU7: Disparar el ordenador
		- El ordenador decide las coordenadas sobre las que va disparar y el armamento que desea utilizar; el gestor
		del	juego determina el efecto del disparo sobre la “flota del jugador”. Además se muestra en pantalla su 
		resultado. En una primera versión del juego, es posible establecer que el ordenador elige aleatoriamente 
		las posiciones sobre las que realiza el disparo. Después se puede ir refinando esta estrategia de juego para 
		añadirle algún tipo de conocimiento y razonamiento sobre estrategias de juego.
##
	
# Tercer Sprint 
![logo_preview](http://i.imgur.com/CiW4Ag6.png)
![logo_preview](http://i.imgur.com/945l6ZT.png)
![logo_preview](http://i.imgur.com/7m7rSvx.png)
![logo_preview](http://i.imgur.com/2e5LpYG.png)
##
	# HU8: Reparar barco jugador
		- Si el jugador dispone de dinero suficiente, se realiza la reparación del barco que indique, disminuyendo
		la cantidad de dinero del jugador.
	# HU9: Reparar barco ordenador
		- Si el ordenador dispone de dinero suficiente, se realiza la reparación del barco que indique, disminu-
		yendo la cantidad de dinero del ordenador.
	# HU10: Comprar armamento jugador
		- Si el jugador desea comprar algún armamento y dispone de dinero suficiente, pasa a tener el armamento 
		comprado disminuyendo la cantidad de dinero del jugador.
	# HU11: Comprar armamento ordenador
		- Si el ordenador desea comprar algún armamento y dispone de dinero suficiente, pasa a tener el armamento 
		comprado disminuyendo la cantidad de dinero del ordenador.
##
##	
HU1: 
La asociación del armamento dinero y radar se realiza a los jugadores 
tanto humano como IA , cabe destacar que esta asociación tambien incluye 
a la flota  de cada jugador (ambas contenidas en la clase Tablero),
estas flotas inicialmente contiene todos los barcos del juego pero sin 
posición específica (-1,-1).

El Almacén actúa a modo de estanteria pudiendo coger la cantidad de 
armas que deseemos dentro del límite de provisiones , este almacen 
lo comparten humano y ordenador.

Sobre Battleship recae la responsabilidad de conocer todas las variables 
de juego pudiendo acceder a ellas de forma sencilla (solo se pueden
 modificar mediante la codificación) , de esta forma se convierte en 
fuente de conocimiento de todas aquellas clases que interactúen con 
información global.

La interfaz gráfica concretamente el tablero adversario ( por consola es igual) 
cuenta con un sistema de refresco que permite ver al usuario todo lo que ha visto 
en el pasado pudiendo haber cambiado para el presente , esto se ve claro en las 
interacciones de la Ia después de la fase de disparo del jugador humano ,
de esta manera no tenemos porque almacenar posiciones avistadas para mostrarlas
,la IA por su parte las guardara para realizar su proximo movimiento.

El programa cuenta con un gestor de inicialización, 
comprueba que no sea una posición colindante, sin contar las esquinas; 
además de que la posición se encuentre en el tablero. Posteriormente asignará 
la posicion deseada a un barco de la flota que cumpla sus características
,es decir; que sea del mismo tipo, para utilizar este gestor por interfaz gráfica bastará con seguir las
instrucciones de las informaciones emergentes (donde estas se podrán desactivar).

*La flota ordenador se coloca automáticamente ,este método es aplicable 
a la flota humano, útil para pruebas, cambiando unas lineas que están especificadas en la
clase Battleship. 
##
##
HU2 y HU3: 
Los escudos son atributos de los barcos y no se almacenan en ningún sitio, se
instancian en el momento que se requiera colocarselo a algún barco, aquellos barcos
que no tienen escudo lo tendrán a nulo y recibiran directamente el impacto según el arma usada.
Los escudos  son capaces de recibir dos impactos de bomba o uno de misil y protegen todas 
las posiciones de barco por igual.

El modo de interacción para la generación de escudos es igual para el jugador y la IA , se selecciona una posición a la que colocar el escudo y se resuelve el barco deseado en función de dicha posición ,si no existe el barco no actúa y lo avisará
##
##
H4 y H5 :
La clase radar tiene un número de usos limitado a tres, cuando se desea se le
asigna una nueva posición que explorar. El método es igual para la IA y el humano, exceptuando que la IA contiene una lista de posiciones revisadas, mediante esa lista comprobara el lugar idoneo para colocar el radar. En cambio el humano verá actualizarse la interfaz gráfica.

Actualmente el radar muestra las posiciones de alrededor y de la posición deseada, esto podría modificarse por el uso de Listas de posiciones cuya formación corre a cargo de la posición (es capaz de saber sus colindantes),posteriormente se realiza un filtrado del Tablero mostrando unicamente aquellas posiciones que pertenezcan a la lista dada.
##
##
H6 y H7:
La clase Arma cuenta con un método abstracto disparar que se redefine para actuar de maneras diferentes en sus especializaciones (aplicado mediante patrón Strategy).
La IA usa la misma idea del radar para las armas. Para ello reutiliza la lista de posiciones y verificara el lugar ideoneo para disparar.

Cabe destacar que el disparo se realiza posición a posición y que existen método diferentes de comportamiento de impacto para bombas y misiles, la cadena de ejecución de
este método termina en el estado de los barcos (en caso de acierto) aplicandose aquí el 
patrón State , en caso de fallar saltará un mensaje (solo por consola , 
era muy engorroso en interfaz y estaria sacando advertencias tanto para el turno de humano como para el turno de la IA).
##
##
H8 y H9:
El método de reparación se realiza en la IA y el humano por igual, se reparan barcos posición a posición, y en caso de fallar se devuelve el dinero que se le ha retirado al jugador. En el caso de la IA si tiene algun barco por reparar solo reparará 1 y saltará de fase.
##
##
H10 y H11:
El método de comprar es equivalente en ambos jugadores humano e IA, es posible comprar varios articulos en la misma fase, en esta fase tambien se colocar escudos a modo de producto. 
Para la IA lo tenemos diferenciado en 2 fases: comprar arma y colocar Escudo. De nuevo a usará de la lista de posiciones, donde revisará si tiene alguna posicion revisada que contiene un barco y comprueba que arma puede comprar.
En la siguiente fase, comprará colocará un escudo y si no es posible se le devolverá el dinero, una vez terminada continuara a la siguiente fase.
##
##
IA:
Existe un método asociado a cada método anterior para facilitar el siguiente movimiento a la IA, esos metodos han sido programados para que la IA tome decisiones y una vez tomadas usará los metodos en común para la función de cada en cada fase.

##
##
Fases :
Existen fases para distinguir cada momento del juego, se han creado para dar una secuencialidad al juego. De esta manera tambien le damos cierto orden de actuación a la IA y así puede jugar de una manera más audaz.
##