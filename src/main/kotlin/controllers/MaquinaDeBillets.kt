package org.example.controllers


/**
 * @author Adrián Galinsoga
 * @date 10/12/2024
 *
 * Màquina de venda de bitllets de transport dels FGC i TMB
 *
 * Objectius:
 * Els objectius d’aquest exercici són:
 * • Treballar estructures condicionals
 * • Treballar estructures iteratives
 * • Implementar estructures de control d’input de dades
 * • La programació modular
 *
 *
 */


/**
 * Funció principal que crida a la funció iniciarVenta
 */
fun main() {
}


/**
 * Funció que permet seleccionar un tipus de ticket i la quantitat de zones
 * @return Pair amb el preu final del ticket i el tipus de ticket seleccionat
 *
 */

fun seleccionarTicket(): Pair<Double, Int> {
    val opcionesTicket = """
        1 - Ticket sencillo ............... 2,20€ (1 zona)
        2 - TCasual ...................... 11,35€ (1 zona)
        3 - TUsual (TMes) ................. 20,00€ (1 zona)
        4 - Tarjeta T-70/90 FM/FN ......... 31,75€ (1 zona)
        5 - TJove ......................... 40,00€ (Todas las zonas)
    """.trimIndent()

    var precioFinal = 0.0
    var seleccionValida = false
    var zonaValida = false

    println("\nSelecciona un tipo de ticket:\n")
    println(opcionesTicket)

    var seleccion = 0
    while (!seleccionValida) {
        try {
            print("Ingresa el número del ticket: ")
            seleccion = readLine()?.toIntOrNull() ?: -1
            if (seleccion in 1..5) {
                seleccionValida = true
                precioFinal = when (seleccion) {
                    1 -> 2.2
                    2 -> 11.35
                    3 -> 20.0
                    4 -> 31.75
                    5 -> 40.0
                    else -> 0.0
                }

                if (seleccion != 5) {
                    while (!zonaValida) {
                        try {
                            print("Selecciona la cantidad de zonas: 1, 2 o 3\n")
                            val zonas = readLine()?.toIntOrNull() ?: -1

                            if (zonas in 1..3) {
                                zonaValida = true
                                println("Cargando plataforma de pagos...")
                                precioFinal *= when (zonas) {
                                    2 -> 1.35
                                    3 -> 1.89
                                    else -> 1.0
                                }
                            } else {
                                println("Por favor, selecciona una zona válida")
                            }
                        } catch (e: Exception) {
                            println("Por favor, ingresa un número válido para las zonas")
                        }
                    }
                }
            } else {
                println("Por favor, selecciona un ticket válido")
            }
        } catch (e: Exception) {
            println("Por favor, ingresa un número válido")
        }
    }

    return Pair(precioFinal, seleccion)
}





