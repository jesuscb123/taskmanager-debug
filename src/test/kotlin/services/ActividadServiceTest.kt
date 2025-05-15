package services

import es.prog2425.taskmanager.datos.ActividadRepository
import es.prog2425.taskmanager.modelo.Actividad
import es.prog2425.taskmanager.modelo.Estado
import es.prog2425.taskmanager.modelo.Evento
import es.prog2425.taskmanager.modelo.Tarea
import es.prog2425.taskmanager.servicios.ActividadService
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import java.lang.IllegalArgumentException

class ActividadServiceTest : DescribeSpec({
    val mockRepositorio = mockk<ActividadRepository>(relaxed = true)
    val actividadService = ActividadService(mockRepositorio)
    describe("crear Evento"){
        it("Debe crear el evento"){
            val descripcion = "Evento nuevo"
            val fecha = "15-12-2026"
            val ubicacion = "Cádiz"
            actividadService.crearEvento(descripcion, fecha, ubicacion)
            verify { mockRepositorio.agregarEvento(any()) }
        }

        it("Debería fallar si la fecha es incorrecta"){
            val descripcion = "Evento nuevo con error de fecha"
            val fecha = "grgsgsg"
            val ubicacion = "Sevilla"

            shouldThrow<IllegalArgumentException> {
                actividadService.crearEvento(descripcion, fecha, ubicacion)
            }
        }
    }

    describe("Crear Tarea"){
        it("Debe retonar una instancia de Tarea"){
            val descripcion = "Tarea nueva"
            val tarea = actividadService.crearTarea(descripcion)
            tarea.descripcion shouldBe descripcion
            verify { mockRepositorio.agregarTarea(tarea) }
        }

        it ("Debe lanzar un error por tarea vacía"){
            val descripcion = ""
            shouldThrow<IllegalArgumentException> {
                actividadService.crearTarea(descripcion)
            }
        }


    }

    describe("asociar Subtarea") {
        it("Debe asociar una subtarea a la tarea principal.") {
            val tareaPrincipal = mockk<Tarea>(relaxed = true)
            val subtareaCreada = mockk<Tarea>()

            actividadService.asociarSubtarea(tareaPrincipal, subtareaCreada)

            verify { tareaPrincipal.agregarSubtarea(subtareaCreada) }
        }

        it("Si la subtarea es nula debería lanzar una excepción") {
            val tareaPrincipal = mockk<Tarea>(relaxed = true)
            val subtareaCreada: Tarea? = null

            shouldThrow<NullPointerException> {
                actividadService.asociarSubtarea(tareaPrincipal, subtareaCreada!!)
            }
        }
    }

    describe("Cambiar estado de la tarea"){
        it("Debe cambiar el estado de la tarea"){
            val tarea = mockk<Tarea>(relaxed = true)
            val estado = mockk<Estado>()

            actividadService.cambiarEstadoTarea(tarea, estado)

            verify { tarea.cambiarEstadoConHistorial(estado) }
  }

        it("Debe lanzar una excepción si introduces un estado nulo"){
            val tarea = mockk<Tarea>(relaxed = true)
            val estado: Estado? = null

            shouldThrow<NullPointerException> {
                actividadService.cambiarEstadoTarea(tarea, estado!!)
            }
        }
    }

    describe("Listar tareas"){
        it("Deberia listar las actividades"){
            val actividadesMock = listOf(mockk<Actividad>())

            every { mockRepositorio.obtenerActividades() } returns actividadesMock

            val resultado = actividadService.listarActividades()

            resultado shouldBe actividadesMock

        }
    }
})