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
![logo_preview](http://i.imgur.com/PQaoOqV.png)
##
# Primer Sprint
![logo_preview](http://i.imgur.com/wzR8wnZ.png)
![logo_preview](http://i.imgur.com/19dOhsa.png)
![logo_preview](http://i.imgur.com/iVExllC.png)
![logo_preview](http://i.imgur.com/kgsE0kM.png)
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
![logo_preview](http://i.imgur.com/9O7kT84.png)
![logo_preview](http://i.imgur.com/lvswpYf.png)
![logo_preview](http://i.imgur.com/8dY8LiF.png)
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
![logo_preview](http://i.imgur.com/pE57TGP.png)
![logo_preview](http://i.imgur.com/D9oBqa9.png)
![logo_preview](http://i.imgur.com/YKUwiKH.png)
![logo_preview](http://i.imgur.com/xNVVaMR.png)
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

![logo_preview](http://i.imgur.com/OUa21Dq.png)
En el rectangulo azul vemos el almacen,  nuestras armas en el inicio vacias y el precio de cada arma. El rectángulo naranja indica nuestro dinero que se ira reduciendo, el coste de los escudos, las reparacioney  los usos del radar. Finalmente el rectángulo rosa nos indica la fase en la que estamos. El rectangulo amarillo indica el arma seleccionada y el rectangulo morado las diferentos opciones.
HU1: 
La asociación del armamento, dinero y radar se realiza a los jugadores tanto humano como IA. Las flotas de ambos jugadores se almacenan y componen el tablero, estas flotas inicialmente contienen todos los barcos del juego inicializados pero sin posición, por defecto (-1,-1).

Sobre Battleship recae la responsabilidad de conocer todas las variables de juego, pudiendo acceder a ellas de forma sencilla (solo se pueden modificar mediante la codificación). De esta forma se convierte en fuente de conocimiento de todas aquellas clases que interactúen con información global, además contiene toda la información sobre el turno y las fases de cada turno (ver al final).

## Inicialización de barcos: 
La inicialización de barcos se realiza de uno en uno y consiste en asignar posiciones a un barco que coincida con el tipo especificado, como se había introducido antes los barcos ya están inicializados pero sin posición, por otro lado se realiza una inferencia para minimizar parámetros (pivote(x,y), direccion y tipoBarco). De esta información deducimos el barco que coincide y las coordenadas de cada posición que lo compone. Este proceso se encuentran todos los barcos inicializados, además nos proporciona la información necesaria para inicializar los barcos correctamente.

El Almacén actúa a modo de estanteria pudiendo coger la cantidad de armas que deseemos dentro del límite de provisiones, este almacen lo comparten tanto humano como ordenador y se inicializa antes de la fase de colocación de barcos. El Almacén genera el número de armas especificado en las variables globales del Battleship.

La interfaz gráfica, concretamente el tablero adversario (por consola es igual); cuenta con un sistema de refresco que permite ver al usuario todo lo que ha visto en el pasado pudiendo haber cambiado para el presente. Esto se ve claro en las interacciones de la IA después de la fase de disparo del jugador humano, de esta manera no tenemos porque almacenar posiciones avistadas para mostrarlas, la IA en cambio, las guardara para realizar su proximo movimiento.

Importante para pruebas:
* La flota ordenador se coloca automáticamente, este método es aplicable a la flota humano, útil para pruebas, cambiando unas lineas que están especificadas en la clase Battleship. 
![logo_preview](http://i.imgur.com/tJcMO48.png)
Para la colocación, pinchamos en una casilla (cuadrado negro) de nuestro tablero (representado en color azul). Una vez clickado observamos que se nos despliega una nueva ventana. Ahí escogemos el barco (rectángulo negro) y su orientación (rectángulo rojo) y añadimos la información (rectángulo cyan).
##

##
HU2 y HU3: 
Los escudos son atributos de los barcos y no se almacenan en ningún sitio, se instancian en el momento que se requiera colocarselo a algún barco. Aquellos barcos que no tienen escudo lo tendrán a nulo y recibiran directamente el impacto según el arma usada. Los escudos  son capaces de recibir dos impactos de bomba o uno de misil y protegen todas las posiciones de barco por igual.

El modo de interacción para la generación de escudos es igual para el jugador y la IA, se selecciona una posición a la que colocar el escudo y se resuelve el barco deseado en función de dicha posición. Si no existe el barco no actúa y lo avisará.
![logo_preview](http://i.imgur.com/TYXnNjV.png)
![logo_preview](http://i.imgur.com/Hicj3vv.png)

En la fase escudo pincharemos en una casilla donde tengamos un barco (rectángulo rosa) y se nos restara el dinero(rectámmgulo marron) de coste 75 (color azul cielo). Veremos como cambio de color el barco a azul cyan.
- La fase de escudo como ya sabemos está enlazada con la fase de comprar por lo que repetiremos la imagen.
 
##

##
H4 y H5:
La clase radar tiene un número de usos limitado a tres, cuando se desea utilizar, se le asigna una nueva posición que revelar y este revela las colindantes y reduce el uso. El método es igual para la IA y el humano, posteriormente la información recogida se guardara en el caso de la IA y se mostrará en el caso de usuario.
Actualmente el radar muestra las posiciones de alrededor y de la posición deseada, esto podría modificarse por el uso de Listas de posiciones cuya formación corre a cargo de la posición (es capaz de saber sus colindantes), posteriormente se realiza un filtrado del Tablero mostrando unicamente aquellas posiciones que pertenezcan a la lista dada.
![logo_preview](http://i.imgur.com/Ihhqusl.png)
Para el correcto uso del radar clickamos una casilla (rectángulo rojo) del tablero enemigo (rectámgulo en blanco). Este nos despliega la información de la casilla y de sus alrededores (rectángulo negro), como podemos observar se nos reducen los usos del radar (rectángulo color mostaza).
##

##
H6 y H7:
La clase Arma cuenta con un método abstracto disparar que se redefine para actuar de maneras diferentes en sus especializaciones (aplicado mediante patrón Strategy). El disparo cuenta con varios fallos contemplados (no existe arma, no existe barco en la posición) además de los impedimentos de interfaz(ver al final),en este caso la actividad del usuario se restringe al campo contrario. Cada disparo quedará guardado en posiciones revisadas de la IA y se mostrará por pantalla en el caso de humano (los barcos hundidos se registran enteros como posiciones conocidaa aunque solo hayamos atacado una posición).

La IA usa la misma idea del radar para las armas. Para ello reutiliza la lista de posiciones y verificara el lugar ideoneo para disparar, luego el arma actúa de la misma manera, ataca la posición y según que arma seleccionase, destruira más posiciones.

Cabe destacar que el disparo se realiza posición a posición y que existen método diferentes de comportamiento de impacto para bombas y misiles, la cadena de ejecución de este método termina en el estado de los barcos (en caso de acierto) aplicandose aquí el patrón State , en caso de fallar saltará un mensaje (solo por consola, era muy engorroso en interfaz y estaria sacando advertencias tanto para el turno de humano como para el turno de la IA).
![logo_preview](http://i.imgur.com/IXxWblB.png)
Para disparar al campo contrario, debemos seleccinar el arma (rectángulo amarillo) y seleccionar una posicion del tablero derecho (cuadrado negro), como se puede ver en este ejemplo, hemos usado un misil Boom que se nos ha restado de la lista (rectángulo rojo) y nos ha tocado las casillas (cuadrado negro y lineas negras). Cada tipo de arma tiene una función diferente.

##

##
H8 y H9:
El método de reparación se realiza en la IA y el humano por igual, se reparan barcos posición a posición, y en caso de fallar se devuelve el dinero que se le ha retirado al jugador, solo se podrá reparar un barco en cada fase reparación y el fallo contará como reparación. En el caso de la IA reparará el primer barco dañado que tenga ya que solo se puede realizar uno por turno (en caso de realizarse).
![logo_preview](http://i.imgur.com/LSm5cUc.png)
En la reparación debes seleccionar una casilla en rojo de un barco no hundido (cuadrado amarillo, no rectangulo verde), y si tienes mas dineró que el coste (
##

##
H10 y H11:
El método de comprar es equivalente en ambos jugadores humano e IA, es posible comprar varios articulos en la misma fase, en esta fase tambien se colocar escudos a modo de producto. Para la IA lo tenemos diferenciado en 2 pasos: comprar arma y colocar Escudo. De nuevo  usará  la lista de posiciones revisadas para decidir que tipo de arma le conviene utilizar en cada momento ,por otro lado colocará un escudo.

Los métodos de compra solo comprueban el dinero del usuario y lo decrementan, posteriormente al alcanzar el producto a comprar (arma o escudo) se devolvera el dinero en caso que no existiera o los parámetros no coincidieran con un barco, esta transición no la verá el usuario por nuestra política de refresco (ver al final), todos los fallos contemplados en la compra de armas y escudos avisarán al usuario que la operación no se ha realizado con éxito (falta de dinero, no existe arma, ya existía escudo, la posción seleccionada no es barco), existen otros impedimentos de interfaz (ver final).
![logo_preview](http://i.imgur.com/TYXnNjV.png)
Para comprar un arma, debemoso seleccionar la deseada (rectángulo negro) y darle a comprar (rectángulo a rojo). Podemos ver como el almacen (rectángulo amarillo) reduce sus existencias y nuestras armas aumentan (rectángulo verde) y como disminuye nuestro dinero (rectángulo naranja).

![logo_preview](http://i.imgur.com/eYnlFQb.png)
Al perder nuestro último barco podremos reiniciar el juego o salir (rectángulo negro). 
##

##
IA:
Existe un método asociado a cada fase del turno (ver final) para facilitar el siguiente movimiento a la IA, esos metodos han sido programados para que la IA tome decisiones que se usarán en los métodos generales de jugador, es decir; resuelva los parametros de los proximos métodos.

En cuanto a profundidad de inteligencia son simplemente scripts que interpretan las posiciones revisadas de la IA y las variables del juego que conoce (únicamente), es capaz de gestionar todos los aspectos del juego (colocación de barcos, radar, comprar y escudos, disparar, reparar) de una forma simple pero eficaz, recuerda que la información del campo enemigo es en función de lo que conoces en un instante no en tiempo real (niebla de guerra).

- Confusiones típicas:
	- Cuando se dispare un misil y un barco no se hunda será porque contaba con escudo.
	- Cuando ataquemos varias posiciones con bombas y aparezcan todas las posiciones tocadas puede ser que la IA las haya ido reparando y a nosotros no nos aparezca.
 
##

##
Fases:
Existen fases para distinguir cada momento del turno, esto sirve para gestionar los sucesos de forma secuencial y asíncrona a la vez, es decir; podemos decidir que fase saltar cuando suceda un evento o podemos darle la posibilidad al usuario de generar varios eventos en la misma fase (comprar varias armas por ejemplo), también somos capaces de gestionar el "evento vacio" que en nuestro caso se considera "saltar fase", este evento permite al usuario decidir en todo momento que prefiere hacer (el juego no te obliga a jugar una fase pero si el turno al fin y al cabo no decidir constituye una decisión intrinsecamente)
##

##
Política de refresco:
El juego solo mostrará los cambios en el juego en ciertos puntos, al final de cada fase concretamente. El refresco solo afecta a la visión del usuario sobre la información que tiene al alcance su análogo jugador-humano en el modelo.
##

##
Impedimentos de la interfaz:
Estos impedimentos evitan que se pueda realizar cualquier acción en la interfaz (GUI o consola), en función de la fase en que nos encontremos se nos permitirá interactuar con el programa de una forma determinada, están contemplados en el modelo pero suponen componentes básicos de interacción (dos tableros + opciones), en el  caso de la consola se irán mostrando cuando se necesiten (aquí se contempla más facilmente la política de refresco y la niebla de guerra)
##

##
Recursos anexos:
- IDE:
	- Hemos realizado el proyecto en el IDE IntelliJ de JetBrains por su facilidad y comodida a la hora de codificar, además cuenta con un depurador de código sofisticado que nos permite simplificar el código.

- TEST:
	- Para realizar las pruebas unitarias hemos decidido utilizar el Framework TestNG por su versatilidad de métodos de prueba y overrides funcionales.
![logo_preview](http://i.imgur.com/Il8rU37.png)
	
- GUI:
	- Para realizar la interfaz hemos utilizado las librerias  javax.swing de java.

- Versión de java:
	- Hemos utilizado la octava versión de Java para aprovechar las expresiones lambda y la genericidad.

- Control versiones: 
	- Para realizar el control de versiones del proyecto hemos utilizado GitHub, además nos ayuda a mantener el proyecto en su última versión compartido entre todos los integrantes del grupo.

- Readme: 
	- Hemos utilizado un archivo de tipo MD para guardar la información referente al proyecto.

- Diseño: 
	- Hemos utilizado el soporte informático Visual Paradigm para realizar el diagrama de clases y los diagramas de secuencia.
	
- Codificación:
	- El proyecto esta hecho en formato de codificación UTF-8.
##
	
Hecho por Edgar Andrés Santamaría y Unai Martín Gonzalez.