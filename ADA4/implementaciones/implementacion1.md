# Implementación1

## Reorganización de la estructura del Proyecto

Antes de la implementación, todos los archivos relacionados con el juego como los niveles, los puntajes y el código del juego se encontraban dentro de una misma carpeta que 
contenía 44 archivos, todos en la carpeta raíz del proyecto, lo que ocasionaba varios problemas como:
- Dificultad para encontrar archivos
- Sin separación de código y recursos
- Dificultad al entender cada parte del Proyecto

Estructura anterior:


**Atributos de Calidad**
 
*Mantenibilidad*

Al tener todo en el mismo archivo se dificulta el poder corregir los errores ya que un error se puede extender a mas archivos y romper el funcionamiento del sistema, es mas
complicado de aislar componentes para poder probarlos, y también es mucho mas complicado añadirle funcionlidades si aumentan los requerimientos.

## Solución 

Para solucionar este problema los 44 archivos se distribuyeron en carpetas segun el propósito y el código se reorganizó siguiendo el patrón **MVC**(Modelo Vista Controlador),
un estándar para proyectos de software normalmente aplicado cuando se usan interfaces gráficas.

Estructura nueva:
  mazegame
├──  resources
│   ├──  images
│   │   ├──  D.png
│   │   ├──  E.png
│   │   ├──  H.png
│   │   ├──  M.png
│   │   └──  etc.png
│   ├──  levels
│   │   ├──  level1.maz
│   │   ├──  level10.maz
│   │   ├──  level11.maz
│   │   ├──  level12.maz
│   │   └──  etc.maz
│   └──  save
│       └──  scores.txt
└── 󱧼 src
    ├──  controller
    │   └──  FileLoader.java
    ├──  model
    │   ├──  HighScore.java
    │   ├──  TheArchitect.java
    │   ├──  TimeCalculator.java
    │   └──  TimeKeeper.java
    └──  view
        ├──  GameGui.java
        └──  ScoreGui.java

Esta nueva estructura proporciona una separación clara de responsabilidades, lo que facilita agregar cambios, entender la estructura del código y el trabajo en equipo.

## Cambios en el código
Se cambiaron rutas del código, pasaron de ser absolutas al sistema de archivos a relativas al classpath(ruta de busqueda de la VM de Java)

En la clase GameGui

´´´java
// nuevo atributo que guarda la ruta de los niveles.
private String levelsPath = Objects.requireNonNull(getClass().getResource("/levels/")).getPath();
´´´
Uso:
´´´java
public void nextLevelLoad() {
    // ...código
    fl.loadFile( levelsPath + fileName);
    // encuentra el archivo del siguiente nivel
}
´´´
Segundo cambio:

Para las imagenes .png y el ícono "yeababyyea.jpg"

´´´java
// rutas relativa al sistema de archivos
fancyLabel = new JLabel("",new ImageIcon("yeababyyea.jpg")),JLabel.LEFT);

fancyLabel = new JLabel("",new ImageIcon(filename)),JLabel.LEFT);
´´´

´´´java
// rutas relativas al classpath

shagLabel = new JLabel("",new ImageIcon(
                Objects.requireNonNull(getClass().getResource("/images/yeababyyea.jpg"))
        ),JLabel.LEFT);

fancyLabel = new JLabel("",new ImageIcon(
                    Objects.requireNonNull(getClass().getResource("/images/" + fileName + ".png"))
        ),JLabel.LEFT);

´´´

