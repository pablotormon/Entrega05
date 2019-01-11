# Entrega 05

App que muestra una recycler view donde puede ser editada su estructura, hacer ofertas con gestos para tener descuentos y una calculadora de valorde la isla


### 3 Activities ✓

Main Activity, OfertaActivity, PerfilActivity 

### 2 Fragmetns comunicados ✓

PrimerFragment, Segundo Fragment
```
Para llegar a ellos, abrir el drawer y abrir calculadora
```

### Intents con Extras ✓

Para pasar de la MainActivity al perfil he usado intents con extras para saber que informacion usar en el ProfileActivity

### RecyclerView y CardView ✓

En el main activity hay un recycler con cardviews

### Shared Preferences ✓

La uso para almacenar las ofertas que se hacen y mostrarlas en Drawer/Ofertas, se almacena en ProfileActivity

### Anmaciones ✓

En ProfileActivity cuando se gira el telefono la imagen se redimensiona con una animación para que no oculte la view

### Gestures ✓

En ProfileActivity si dibujas una "M" te provoca un descuento
En OfertaActivity debes hacer un LongPress para poder eliminar las ofertas

### SeekBar y ProgressBar ✓

IntroMainActivity se inica con una progressbar
En ProfileActivity hay una seekbar funcional para ofertar

### Constrain Layout ✓
island_card_layout es un constrain layout

### Menu Funcional ✓

En el MainActiity hay un menu gfuncinal que activa el modo noche y modo lista

### Toast ✓

Todas las opciones que no están habilitadas muestran un Toast, por ejemplo "añadir Isla"

### Snack Bar ✓

En ProfileActivity al cancelar la oferta que repregunta con un SnackBar

### Logs ✓

Para programar heusado infinidad de logs, los he ido quitando. En PrimerFragment.kt->onStart he dejado uno

## Comentarios ✓

He comentado casi todas las funciones
