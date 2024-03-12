package cr.ac.una.tresenraya

class JuegoService {
    val matriz = Array(3) { CharArray(3) }
    var figura: Char = 'X'

    fun jugada(x: Int, y: Int): String {
        matriz[x][y] = figura
        val ganador = verificarGanador()
        if (ganador) {
            return "¡${figura} gana!"
        } else if (tableroLLeno()) {
            return "¡Empate!"
        } else {
            if (figura == 'X') figura = 'O' else figura = 'X'
            return "Turno de " + figura
        }
    }

    fun inicializar(){
        matriz[0][0] = ' '
        matriz[0][1] = ' '
        matriz[0][2] = ' '
        matriz[1][0] = ' '
        matriz[1][1] = ' '
        matriz[1][2] = ' '
        matriz[2][0] = ' '
        matriz[2][1] = ' '
        matriz[2][2] = ' '

    }
    fun verificarGanador(): Boolean {
        // Verifica las filas
        for (i in 0 until 3) {
            if (matriz[i][0] == matriz[i][1] && matriz[i][1] == matriz[i][2] && matriz[i][0] != ' ' && matriz[i][1] != ' ' && matriz[i][2] != ' ') {
                return true
            }
        }
        // Verifica las columnas
        for (j in 0 until 3) {
            if (matriz[0][j] == matriz[1][j] && matriz[1][j] == matriz[2][j] && matriz[0][j] != ' ' && matriz[1][j] != ' ' && matriz[2][j] != ' ') {
                return true
            }
        }
        // Verifica las diagonales
        if (matriz[0][0] == matriz[1][1] && matriz[1][1] == matriz[2][2] && matriz[0][0] != ' ' && matriz[1][1] != ' ' && matriz[2][2] != ' ') {
            return true
        }
        if (matriz[0][2] == matriz[1][1] && matriz[1][1] == matriz[2][0] && matriz[0][2] != ' ' && matriz[1][1] != ' ' && matriz[2][0] != ' ') {
            return true
        }

        return false
    }

    fun tableroLLeno(): Boolean {
        for (fila in matriz) {
            for (elemento in fila) {
                if (elemento == ' ') {
                    return false
                }
            }
        }
        return true
    }
}