# TaskManager-Debug

## Introducción
La actividad consiste en instalar y usar un analizador de código estático (Detekt o Ktlint) en el proyecto que vienes desarrollando, capturar evidencias gráficas, detectar y clasificar errores, aplicar soluciones y explorar las posibilidades de configuración de la herramienta elegida.

### Instalación de Ktlint 
-  solo debemos incluir arriba del fichero *bluid.gradle.kts* esta línea de código:

https://github.com/jesuscb123/taskmanager-debug/blob/a1f4ae4f2e376827256711e9f073f1264f12c1e3/build.gradle.kts#L1

- Luego en plugins añadimos esta línea:

https://github.com/jesuscb123/taskmanager-debug/blob/a1f4ae4f2e376827256711e9f073f1264f12c1e3/build.gradle.kts#L5

- y por último la configuracion de ktlin:

  https://github.com/jesuscb123/taskmanager-debug/blob/a1f4ae4f2e376827256711e9f073f1264f12c1e3/build.gradle.kts#L9-L21