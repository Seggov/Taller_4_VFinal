# Taller 4 - Pokemon TCG

Este proyecto organiza una coleccion de cartas Pokemon TCG usando Java, POO y Swing.

## Objetivo

El sistema carga las cartas desde `Sobres.txt`, las guarda en una coleccion central y permite consultar la coleccion desde la interfaz.

## Arquitectura

```txt
Main
 |
 v
Sistema
 |-- usa Contexto para guardar ArrayList<Carta>
 |-- usa CartaFactory para crear cartas desde Sobres.txt
 |-- usa OrdenamientoStrategy para ordenar la coleccion
 |-- usa PoderVisitor para calcular el poder de cada carta
 |
 v
Vista
```

## Diagrama de clases simple

```txt
Main.Main
    |
    | usa
    v
controlador.Sistema  <<Singleton>>
    |
    | contiene
    v
controlador.Contexto
    |
    | guarda
    v
ArrayList<modelo.Carta>

controlador.Sistema
    |
    | usa para crear cartas desde Sobres.txt
    v
Factory.CartaFactory  <<Factory>>
    |
    | crea
    v
modelo.Pokemon
modelo.Item
modelo.Supporter
modelo.Energy

modelo.Carta  <<abstract>>
    ^
    |
    | heredan
    |
modelo.Pokemon
modelo.Item
modelo.Supporter
modelo.Energy

modelo.Carta
    |
    | acepta
    v
visitor.CartaVisitor  <<Visitor>>
    ^
    |
visitor.PoderVisitor

controlador.Sistema
    |
    | recibe
    v
Strategy.OrdenamientoStrategy  <<Strategy>>
    ^
    |
Strategy.OrdenarPorNombre
Strategy.OrdenarPorRareza
Strategy.OrdenarPorPoder

Vista.SistemaInterfaz
    |
    | contiene paneles
    v
Vista.Administracion
Vista.CrearColeccion
Vista.PanelConImagen
```

## Distribucion de carpetas

```txt
src/
|-- Main/
|   |-- Main.java
|
|-- controlador/
|   |-- Sistema.java
|   |-- Contexto.java
|
|-- modelo/
|   |-- Carta.java
|   |-- Pokemon.java
|   |-- Item.java
|   |-- Supporter.java
|   |-- Energy.java
|
|-- Factory/
|   |-- CartaFactory.java
|
|-- Strategy/
|   |-- OrdenamientoStrategy.java
|   |-- OrdenarPorNombre.java
|   |-- OrdenarPorRareza.java
|   |-- OrdenarPorPoder.java
|
|-- visitor/
|   |-- CartaVisitor.java
|   |-- PoderVisitor.java
|
|-- Vista/
|   |-- SistemaInterfaz.java
|   |-- Administracion.java
|   |-- CrearColeccion.java
|   |-- PanelConImagen.java
|
|-- CartasImagenes/
|   |-- ImagenData/
```

## Patrones usados

```txt
Singleton
- Sistema tiene una unica instancia con getInstancia().

Factory
- CartaFactory crea Pokemon, Item, Supporter o Energy desde una linea del archivo.

Strategy
- OrdenamientoStrategy permite cambiar el ordenamiento sin cambiar Sistema.
- Estrategias actuales: nombre, rareza y poder.

Visitor
- PoderVisitor centraliza las formulas de poder para cada tipo de carta.
```

## Paquetes

```txt
src/Main
- Main.java

src/controlador
- Sistema.java
- Contexto.java

src/modelo
- Carta.java
- Pokemon.java
- Item.java
- Supporter.java
- Energy.java

src/Factory
- CartaFactory.java

src/Strategy
- OrdenamientoStrategy.java
- OrdenarPorNombre.java
- OrdenarPorRareza.java
- OrdenarPorPoder.java

src/visitor
- CartaVisitor.java
- PoderVisitor.java

src/Vista
- SistemaInterfaz.java
- Administracion.java
- CrearColeccion.java
- PanelConImagen.java
```

## Formato de Sobres.txt

```txt
NombreCarta;Rareza;Tipo;...
```

Tipos soportados:

```txt
Pokemon   -> Nombre;Rareza;Pokemon;Dano;CantEnergias
Item      -> Nombre;Rareza;Item;Bonificacion
Supporter -> Nombre;Rareza;Supporter;EfectosPorTurno
Energy    -> Nombre;Rareza;Energy;Elemento
```

## Formulas de poder

```txt
Pokemon   -> (dano / cantEnergias) * 100
Item      -> bonificacion * 20
Supporter -> efectosPorTurno * 50
Energy    -> 1
```

## Estado actual

```txt
Implementado:
- Lectura inicial desde Sobres.txt.
- Coleccion central en Contexto.
- Singleton en Sistema.
- Factory para crear cartas.
- Strategy para ordenar por nombre, rareza y poder.
- Visitor para calcular poder.
- Interfaz Swing inicial.

Pendiente para completar el taller:
- Agregar carta desde GUI.
- Eliminar carta desde GUI.
- Modificar atributos adicionales desde GUI.
- Guardar cambios nuevamente en Sobres.txt.
- Visualizacion ampliada al hacer clic en una carta.
- Diagramas PDF de dominio y clases.
```
