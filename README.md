[![Build Status](https://travis-ci.com/txusyk/gradle-is-battleship.svg?token=dryu6zQsjtcB9hDhpSk7&branch=master)](https://travis-ci.com/txusyk/gradle-is-battleship)

# Diseño UI

## Idea inicial
![logo_sketch](http://i.imgur.com/El7k7Wg.png)

## Evolucion de UIs

### UI 1.0
![logo_preview](http://i.imgur.com/R0ipCfv.png)

### UI actual 1.0
![logo_preview](https://i.imgur.com/P9xwH3h.png)

### UI actual 2.0
![logo_preview](http://i.imgur.com/RUmELvl.png)
![logo_preview](http://i.imgur.com/6Mwnoup.png)

## UI final
### Ventana de login
![Imgur](http://i.imgur.com/GYHycz3.png)
### Ventana de juego
![Imgur](http://i.imgur.com/m8XKqAl.png)
![Imgur](http://i.imgur.com/DFAa4DJ.png)

# Primer Sprint - Battleship

## Tecnologías empleadas

- **Github**: Todo el código, los diagramas y la documentación se encuentra en Github. Así mismo, es la única herramienta que utilizamos para compartir código, errores o fechas de entrega. Gracias a su integración con *IntelliJ* y con *Telegram* logramos por un lado, gestionar el código del respositorio de una forma fácil y por otro lado, recibir las notificaciones de cada cambio.
- **Github Gh-pages**: Disponemos de una página web desde donde se puede seguir la evolución del proyecto y desde donde siempre esta disponible la descarga del código del mismo.
- **IntelliJ**: Utilizamos esta herramienta de Jetbrains gracias a la cuenta premium que nos ha provisto la UPV/EHU.
- **TestNG**: En lugar de utilizar *JUnit* para los test unitarios, hemos decidido emplear este framework para las pruebas ya que comparte la sintaxis con *JUnit* pero es capaz de aportar nuevas funcionalidades.
- **Markdown**: Toda la documentación, tanto en las entregas a vosotros los profesores como lo que hay en Github está escrito integramente en este lenguaje que es 100% compatible con Github y aporta muchísima sencillez a la edición de documentos.
- **Visual Paradigm**: Gracias a la licencia que provee la Escuela, hemos decidido que todos los diagramas serán realizados con esta herramienta, siguiendo unas normas y estándares.
- **XML**: Tanto para generar el TestSuite de *TestNG* como para cargar los datos de la inicialización del juego, hemos decidio externalizar los datos para que sea mas fácil acceder a los mismos y modificarlos en caso de ser necesario. De esta forma tambien reducimos los cambios a realizar en caso de que el cliente modifique las especificaciones de la inicialización.
- **TravisCI**: Utilizamos travis para asegurar el correcto funcionamiento del repositorio y del código que este almacena con cada commit. Para ello, hemos implementado el proyecto con gradle y lo ejecutamos sincronizado con el servidor de TravisCI.

## Precondiciones de funcionamiento del juego

- El número de armas de las que dispone el Modelo.Almacen al inicializarse al comienzo de una partida, variará en función de la dificultad de la misma
- El precio de las reparaciones de los barcos y el dinero inicial con el que comienza una partida también se verá afectado por dicho factor
- Los barcos se colocarán teniendo en cuenta su cabeza (0,0) y de la siguiente forma:
	- *Horizontalmente* -> Hacia la derecha unicamente
	- *Verticalmente* -> Hacia abajo

 Al establecer dichos ajustes de esta forma, nos es mas fácil gestionar el posicionamiento de un barco.

- Los jugadores podrán acceder en todo momento a ambos tableros (con distintas propiedades en función de cual sea cada uno) y dispondrán de una flota posicionada sobre uno de los mismos
- Cuando el jugador impacte sobre una casilla de la flota rival, obtendrá una bonificación economica que se verá incrementada de forma inversamente proporcional al tamaño del barco impactado una vez sea destruido.

## Ajustes iniciales del juego


### Tabla cantidades armas

|         | facil | medio | dificil |
|---------|-------|-------|---------|
|  Modelo.Bomba  |  ∞    |   ∞   |    ∞    |
| Misil	  | 15    |  10   |    4    |
| Misil Dirig | 8 |  6    |   2     |
| Radar   | 10    |  7    |   3     |
| Modelo.Escudo  | 10    |  7    |   3     |

### Tabla precio armas

|         | facil | medio | dificil |
|---------|-------|-------|---------|
|  Modelo.Bomba  |  free | free  |  free   |
| Misil	  | 2500  |  3500 |  5000   |
| Misil Dirig | 8000 |  10000 | 15000|
| Radar   | 5000  |  7500 |  12000  |
| Modelo.Escudo  | 5000  |  7500 |  12000  |

### Tabla dinero

|         | facil | medio | dificil |
|---------|-------|-------|---------|
| Dinero Inicial |  15000 | 13000 | 5000 |
| Precio Reparacion | 2500 | 4000 | 7000 |
| Precio Impacto | 500 |  400 | 250  |
