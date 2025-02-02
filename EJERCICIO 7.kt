


class Cuenta(private val numeroCuenta: String, private var saldo: Double) {

    fun consultarSaldo(): Double {
        return saldo
    }

    fun abonar(monto: Double) {
        saldo += monto
    }

    fun realizarPago(monto: Double): Boolean {
        return if (saldo >= monto) {
            saldo -= monto
            true
        } else {
            false
        }
    }
}




class Persona(val dni: String) {

    private val cuentas = ArrayList<Cuenta>()


    fun añadirCuenta(cuenta: Cuenta): Boolean {
        if (cuentas.size < 3) {
            cuentas.add(cuenta)
            return true
        }
        return false
    }




    fun esMorosa(): Boolean {
        for (cuenta in cuentas) {
            if (cuenta.consultarSaldo() < 0) {
                return true
            }
        }
        return false
    }




    fun realizarTransferencia(
        personaOrigen: Persona, cuentaOrigenIndex: Int,
        personaDestino: Persona, cuentaDestinoIndex: Int, monto: Double
    ): Boolean {
        val cuentaOrigen = personaOrigen.cuentas[cuentaOrigenIndex]
        val cuentaDestino = personaDestino.cuentas[cuentaDestinoIndex]


        if (cuentaOrigen.consultarSaldo() >= monto) {
            cuentaOrigen.realizarPago(monto)
            cuentaDestino.abonar(monto)
            return true
        }
        return false
    }
}


    fun main() {

    val persona1 = Persona("12345678A")
    val cuenta1 = Cuenta("ES1234567890123456789012", 0.0)
    val cuenta2 = Cuenta("ES2345678901234567890123", 700.0)


    persona1.añadirCuenta(cuenta1)
    persona1.añadirCuenta(cuenta2)


    cuenta1.abonar(1100.0)


    cuenta2.realizarPago(750.0)


    println("¿La persona es morosa? ${persona1.esMorosa()}")



    if (persona1.realizarTransferencia(persona1, 0, persona1, 1, 400.0)) {
        println("Transferencia realizada con éxito.")
    } else {
        println("No se pudo realizar la transferencia.")
    }


    println("Saldo de la cuenta 1: ${cuenta1.consultarSaldo()} EUR")
    println("Saldo de la cuenta 2: ${cuenta2.consultarSaldo()} EUR")
    println("¿La persona es morosa después de la transferencia? ${persona1.esMorosa()}")
}
