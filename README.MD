# TaskManager-Debug

## Introducción

Para este ejercicio he utilizado la clase ActividadService, que es la encargada de crear eventos y tareas.
He implementado las dependencias de kotest y mock para poder realizar las pruebas necesarias y pasar los tests a cada método.

https://github.com/jesuscb123/taskmanager-debug/blob/6bc88680d133ff77e6c31b5119fc2681ab2a64e2/build.gradle.kts#L12-L18

Tabla 

| Método                  | Caso de prueba                | Estado inicial del mock              | Acción                                                             | Resultado esperado                                                         |
|-------------------------|-------------------------------|--------------------------------------|--------------------------------------------------------------------|----------------------------------------------------------------------------|
| crearEvento(...)        | Datos válidos                 | mockRepositorio preparado con verify | llamar crearEvento(...)                                            | evento guardado ya que llama a agregarEvento.                              |
| crearEvento(...)        | Fecha inválida                | mockRepo devuelve vacío              | llamar crearEvento(...) fecha inválida                             | lanza una excepción IllegalArgumentException                               |
| crearTarea(...)         | datos válidos                 | mockRepo devuelve tarea              | llamar crearTarea(...)                                             | retorna una tarea con descripción y llama a mockRepositorio.agregarTarea() |
| crearTarea(...)         | descripción vacía             | mockRepo devuelve vacío              | llamar crearTarea() descripción vacía                              | lanza una excepción IllegalArgumentException                               |
| asociarSubtarea(...)    | subtarea válida               | mockRepo devuelve subtarea           | llamar asociarSubtarea(tareaPrincipal, subtarea)                   | llama a tareaPrincipal.agregarSubtarea(...)                                |
| asociarSubtarea(...)    | subtarea nula                 | mockRepo devuelve null               | llamar asociarSubtarea(tareaPrincipal, subtarea) con subtarea null | lanza una excepción NullPointerException                                   |
| cambiarEstadoTarea(...) | estado válido                 | tarea y estado mockeados             | llamar cambiarEstadoTarea(tarea, estado)                           | llama a tarea.cambiarEstadoConHistorial(...)                               |
| CambiarEstadoTarea(...) | estado nulo                   | mockRepo devuelve null               | llamar cambiarEstadoTarea(tarea, estado)  con estado nulo          | lanza una excepción NullPointerException                                   |
 | listarActividades()     | devuelve lista de actividades | mockrepo devuelve lista<Actividad>   | retorna la misma lista que el mock                                 |                                                                            |