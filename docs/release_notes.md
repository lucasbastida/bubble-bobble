# release notes

#### Funcionalidad incluida
Breve listado de la funcionalidad incluida (con el estado de implementación de cada uno).

- Observacion de estadisticas: implementado
- Eliminacion de Items: implementado
- Movimiento del personaje: (horizontal y saltos): implementado
- Disparar de burbujas: implementado pero no como se deseaba en base a nuestra referencia de juego
- Eliminación del enemigo: implementado, ya sea burbuja normal o de fuego
- Obtener nueva habilidad: implementado
- El sistema controla a los enemigos: implementado a un nivel muy simple. Falta implementar algoritmos de movimiento de la AI (eg. A* path finding)
- Multi plataforma


- Diferentes Niveles: No implementado
- Cambiar de nivel automaticamente: no implementado
- Número de vidas: no implementado
### Pass/Fail Ratio

- Pruebas de sistema: 0.84 pass ratio
- Smoke Tests: 1.0 pass ratio
- Integración: 1.0 pass ratio
- Unitarias: 1.0 pass ratio

Cabe notar que no se realizo una cobertura completa del codigo (ver documento pruebas),.
Lo que fue enunciado se logro pasar ya sea manualmente o automaticamente.

### Número de defectos identificados y corregidos

1. [mantener apretado f dispara infinitamente](https://github.com/lucasbastida/bubble-bobble/issues/19)
2. Solucionar bug que hace que el jugador no aparezca hasta que se presione una tecla
3. Si saltas y mantenes apretada la flecha para arriba el personaje vuela (v1.0.1)

### Defectos conocidos (no resueltos)

Severo:
1. [animaciones no concuerdan aveces con el movimiento](https://github.com/lucasbastida/bubble-bobble/issues/23)
2. [saltar y mover al mismo tiempo](https://github.com/lucasbastida/bubble-bobble/issues/21)
3. Los enemigos vuelan y atraviesan las plataformas


### Dirección de acceso o archivo con los archivos del proyecto 

https://github.com/lucasbastida/bubble-bobble

### instrucciones minimas de instalación y ejecución

Descarga bubble-bobble-1.0.1.jar:

https://github.com/lucasbastida/bubble-bobble/releases/tag/v1.0.1

1. Instalar Java Runtime Environment 8 o mas nuevo, 
2. Ejecutar el archivo utilizando java desde la carpeta donde lo descargo:

```shell script
>java -jar bubble-bobble-1.0.1.jar
```


