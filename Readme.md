# Pokemon — Colección de Cartas

Aplicación de escritorio en Java con interfaz gráfica Swing que permite cargar, visualizar y ordenar una colección de cartas Pokémon desde un archivo de texto. El proyecto aplica cuatro patrones de diseño clásicos: **Singleton**, **Factory**, **Strategy** y **Visitor**.

---

## Estructura del proyecto

```
t_4/
├── src/
│   ├── Main/
│   │   └── Main.java                      # Punto de entrada
│   ├── controlador/
│   │   ├── Sistema.java                   # Núcleo del programa (Singleton)
│   │   └── Contexto.java                  # Memoria central de cartas
│   ├── Factory/
│   │   └── CartaFactory.java              # Creación de cartas (Factory)
│   ├── modelo/
│   │   ├── Carta.java                     # Clase base abstracta
│   │   ├── Pokemon.java
│   │   ├── Item.java
│   │   ├── Supporter.java
│   │   └── Energy.java
│   ├── Strategy/
│   │   ├── OrdenamientoStrategy.java      # Interfaz Strategy
│   │   ├── OrdenarPorNombre.java
│   │   ├── OrdenarPorPoder.java
│   │   └── OrdenarPorRareza.java
│   ├── visitor/
│   │   ├── CartaVisitor.java              # Interfaz Visitor
│   │   └── PoderVisitor.java              # Cálculo de poder
│   ├── Vista/
│   │   ├── SistemaInterfaz.java           # Ventana principal
│   │   ├── Administracion.java            # Panel de administración
│   │   ├── CrearColeccion.java            # Panel de visualización
│   │   └── PanelConImagen.java            # Panel con imagen de carta
│   └── CartasImagenes/
│       └── ImagenData/                    # Imágenes .png de cada carta
├── Sobres.txt                             # Base de datos de cartas
└── README.md
```

---

## Cómo ejecutar

1. Abrir el proyecto en **Eclipse IDE**
2. Ir a **Project → Clean** para forzar una compilación limpia
3. Verificar que `Sobres.txt` esté en la **raíz del proyecto** (mismo nivel que `src/`)
4. Ejecutar `Main.java` como aplicación Java

---

## Formato de Sobres.txt

Cada línea representa una carta con campos separados por `;`:

```
# Pokemon: Nombre;Rareza;Pokemon;Daño;CantEnergias
Mega Charizard-Ex;5;Pokemon;280;3

# Item: Nombre;Rareza;Item;Bonificacion
Potion;1;Item;10

# Supporter: Nombre;Rareza;Supporter;EfectosPorTurno
Professor's Research;4;Supporter;5

# Energy: Nombre;Rareza;Energy;Elemento
Fire Energy;1;Energy;Fire
```

La rareza va de 1 (común) a 5 (legendaria).

---

## Patrones de diseño aplicados

### 1. Singleton — `controlador/Sistema.java`

**¿Qué problema resuelve?**
Garantiza que exista una única instancia de `Sistema` en toda la aplicación. Sin este patrón, cada parte del programa podría crear su propio `Sistema` con su propio `Contexto`, resultando en colecciones de cartas separadas e inconsistentes.

**¿Dónde está?**

```java
// Sistema.java
private static Sistema instanciaUnica;

private Sistema() { }   // Constructor privado: nadie puede hacer new Sistema()

public static Sistema getInstancia() {
    if (instanciaUnica == null) {
        instanciaUnica = new Sistema();
    }
    return instanciaUnica;
}
```

**¿Quién lo usa?**
- `Main.java` llama `Sistema.getInstancia()` para arrancar el programa
- `CrearColeccion.java` llama `Sistema.getInstancia().ordenarColeccion(...)` desde la GUI para pedir las cartas ordenadas, accediendo siempre al mismo `Contexto`

---

### 2. Factory — `Factory/CartaFactory.java`

**¿Qué problema resuelve?**
Centraliza la creación de objetos `Carta`. `Sistema` solo lee líneas de texto; no necesita saber cómo se construye cada tipo concreto de carta. Esa responsabilidad queda completamente aislada en `CartaFactory`. Si se agrega un nuevo tipo de carta, solo se modifica la Factory, no el sistema principal.

**¿Dónde está?**

```java
// CartaFactory.java
public Carta crearCartaDesdeLinea(String linea) {
    String[] datos = linea.split(";");
    String nombre = datos[0];
    int rareza   = Integer.parseInt(datos[1]);
    String tipo  = datos[2];

    if (tipo.equals("Pokemon")   && datos.length >= 5) return new Pokemon(...);
    if (tipo.equals("Item")      && datos.length >= 4) return new Item(...);
    if (tipo.equals("Supporter") && datos.length >= 4) return new Supporter(...);
    if (tipo.equals("Energy")    && datos.length >= 4) return new Energy(...);

    return null;  // tipo desconocido: la línea se ignora
}
```

**¿Quién lo usa?**

```java
// Sistema.java — cargarCartasDesdeTxt()
Carta carta = cartaFactory.crearCartaDesdeLinea(linea);
// Sistema no sabe ni le importa si la carta es Pokemon, Item, etc.
```

---

### 3. Strategy — `Strategy/OrdenamientoStrategy.java`

**¿Qué problema resuelve?**
Permite cambiar el criterio de ordenamiento de la colección en tiempo de ejecución, sin modificar `Sistema`. Cada botón de la GUI inyecta una estrategia diferente; `Sistema.ordenarColeccion()` siempre recibe la misma interfaz y ejecuta el orden que corresponda.

**¿Dónde está la interfaz?**

```java
// OrdenamientoStrategy.java
public interface OrdenamientoStrategy {
    ArrayList<Carta> ordenar(ArrayList<Carta> cartas);
}
```

**Las tres estrategias concretas:**

| Clase | Criterio | Orden |
|---|---|---|
| `OrdenarPorNombre` | `Carta::getNombre` | Alfabético A → Z |
| `OrdenarPorRareza` | `Carta::getRareza` | Mayor rareza primero |
| `OrdenarPorPoder` | `Carta::calcularPoder` | Mayor poder primero |

Cada una trabaja sobre una **copia** de la lista para no modificar la colección original en `Contexto`.

**¿Quién lo usa?**

```java
// Sistema.java
public ArrayList<Carta> ordenarColeccion(OrdenamientoStrategy estrategia) {
    return estrategia.ordenar(contexto.getCartas());
    // Sistema no sabe qué estrategia recibe; simplemente la ejecuta
}

// CrearColeccion.java — los botones de la GUI inyectan la estrategia
op1.addActionListener(e -> mostrarColeccion(new OrdenarPorNombre(), "..."));
op2.addActionListener(e -> mostrarColeccion(new OrdenarPorRareza(), "..."));
op3.addActionListener(e -> mostrarColeccion(new OrdenarPorPoder(),  "..."));
```

---

### 4. Visitor — `visitor/CartaVisitor.java` y `visitor/PoderVisitor.java`

**¿Qué problema resuelve?**
Separa el algoritmo de cálculo de poder del modelo de datos. Sin Visitor, habría que poner la fórmula dentro de cada subclase (`Pokemon`, `Item`, etc.) o llenar `Sistema` de `instanceof`. Con Visitor, la lógica de cálculo queda en una sola clase (`PoderVisitor`) y se puede agregar nuevas operaciones sobre las cartas sin tocar el modelo.

**¿Dónde está la interfaz?**

```java
// CartaVisitor.java
public interface CartaVisitor {
    void visitar(Pokemon  pokemon);
    void visitar(Item     item);
    void visitar(Supporter supporter);
    void visitar(Energy   energy);
}
```

**El Visitor concreto y sus fórmulas:**

```java
// PoderVisitor.java
public void visitar(Pokemon pokemon) {
    // Fórmula: (daño / energías) × 100
    poder = (pokemon.getDano() / pokemon.getCantEnergias()) * 100;
}
public void visitar(Item item) {
    // Fórmula: bonificación × 20
    poder = item.getBonificacion() * 20;
}
public void visitar(Supporter supporter) {
    // Fórmula: efectosPorTurno × 50
    poder = supporter.getEfectosPorTurno() * 50;
}
public void visitar(Energy energy) {
    poder = 1;  // Las energías tienen poder fijo
}
```

**¿Cómo se conecta con el modelo?**

Cada subclase de `Carta` implementa `aceptar()` llamando al método correcto del Visitor:

```java
// Pokemon.java
public void aceptar(CartaVisitor visitor) { visitor.visitar(this); }

// Item.java
public void aceptar(CartaVisitor visitor) { visitor.visitar(this); }
// ... igual para Supporter y Energy
```

`Carta` dispara el ciclo completo desde `calcularPoder()`:

```java
// Carta.java
public int calcularPoder() {
    PoderVisitor visitor = new PoderVisitor();
    aceptar(visitor);          // la carta le dice al visitor quién es
    return visitor.getPoder(); // el visitor devuelve el poder calculado
}
```

**¿Quién lo usa?**
- `OrdenarPorPoder` llama `carta.calcularPoder()` para ordenar
- `CrearColeccion` llama `carta.calcularPoder()` para mostrarlo en la GUI

---

## Flujo de ejecución

```
Main.main()
  └── Sistema.getInstancia()          [Singleton: una sola instancia]
        └── iniciarSistema()
              ├── cargarCartasDesdeTxt()
              │     └── Scanner lee Sobres.txt línea por línea
              │           └── CartaFactory.crearCartaDesdeLinea()   [Factory]
              │                 └── new Pokemon / Item / Supporter / Energy
              │                       └── Contexto.agregarCarta()
              └── SistemaInterfaz.creacionInterfazGeneral()
                    └── GUI visible al usuario

[El usuario presiona un botón de orden]
  └── CrearColeccion.mostrarColeccion(new OrdenarPorNombre())    [Strategy]
        └── Sistema.ordenarColeccion(estrategia)
              └── estrategia.ordenar(contexto.getCartas())
                    └── carta.calcularPoder()                    [Visitor]
                          └── PoderVisitor.visitar(carta)
                                └── fórmula según tipo de carta
```

---

## Tecnologías utilizadas

- **Java 21** (compatible con Java 17+)
- **Swing** para la interfaz gráfica
- **Eclipse IDE** como entorno de desarrollo
- Lectura de archivo con `java.util.Scanner`
- Sin dependencias externas

---

## Nota sobre las imágenes

Las imágenes de las cartas se cargan desde `src/CartasImagenes/ImagenData/`. El nombre del archivo debe coincidir exactamente con el nombre de la carta en `Sobres.txt`, reemplazando espacios por guiones bajos:

```
"Mega Charizard-Ex"  →  Mega_Charizard-Ex.png
"Fire Energy"        →  Fire_Energy.png
```

Si la imagen de una carta no existe, el sistema busca `Predeterminado.png` como imagen de respaldo.
