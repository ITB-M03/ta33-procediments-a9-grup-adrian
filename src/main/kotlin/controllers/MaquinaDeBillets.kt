package org.example.controllers

fun main() {
    iniciarVenta()
}

fun seleccionarTicket(): Pair<Double, Int> {
    println("Selecciona un tipo de ticket:")
    println("1 - Ticket sencillo (2,20€)\n2 - TCasual (11,35€)\n3 - TUsual (20,00€)\n4 - T-70/90 (31,75€)\n5 - TJove (40,00€)")

    val precios = listOf(2.2, 11.35, 20.0, 31.75, 40.0)
    val seleccion = obtenerNumero("Ingresa el número del ticket (1-5):", 1..5)

    var precioFinal = precios[seleccion - 1]

    if (seleccion != 5) {
        val zonas = obtenerNumero("Selecciona la cantidad de zonas (1-3):", 1..3)
        precioFinal *= when (zonas) {
            2 -> 1.35
            3 -> 1.89
            else -> 1.0
        }
    }

    return Pair(precioFinal, seleccion)
}
fun comprarTicket(precio: Double, seleccion: Int) {
    val tiposTicket = listOf("Ticket sencillo", "TCasual", "TUsual", "T-70/90", "TJove")
    println("El precio del ${tiposTicket[seleccion - 1]} es: ${"%.2f".format(precio)}€")

    var totalDinero = 0.0
    while (totalDinero < precio) {
        val dinero = obtenerDinero("Introduce dinero (ej. 0.1, 1.0):")
        totalDinero += dinero
        if (totalDinero < precio) {
            println("Faltan ${"%.2f".format(precio - totalDinero)}€.")
        }
    }
    println("¡Compra realizada! Cambio: ${"%.2f".format(totalDinero - precio)}€.")
}

fun iniciarVenta() {
    val codigoSecreto = "4321"
    println("Presiona Enter para continuar o escribe el código secreto para detener la máquina:")

    if (readLine() == codigoSecreto) {
        println("Máquina detenida.")
        return
    }

    do {
        val (precio, tipo) = seleccionarTicket()
        comprarTicket(precio, tipo)
        println("¿Quieres comprar otro ticket? (si/no):")
    } while (readLine()?.lowercase() == "si")

    println("¡Gracias por usar la máquina de tickets!")
}

fun obtenerNumero(mensaje: String, rango: IntRange?): Int {
    var numero: Int?
    do {
        println(mensaje)
        numero = readLine()?.toIntOrNull()
        if (numero == null || (rango != null && numero !in rango)) {
            println("Por favor, ingresa un número válido.")
        }
    } while (numero == null || (rango != null && numero !in rango))
    return numero
}

fun obtenerDinero(mensaje: String): Double {
    var dinero: Double?
    do {
        println(mensaje)
        dinero = readLine()?.toDoubleOrNull()
        if (dinero == null || dinero <= 0) {
            println("Por favor, ingresa una cantidad válida.")
        }
    } while (dinero == null || dinero <= 0)
    return dinero
}
