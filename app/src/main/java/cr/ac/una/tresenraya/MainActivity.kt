package cr.ac.una.tresenraya

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.app.AlertDialog

class MainActivity : AppCompatActivity() {
    lateinit var jugar :Button
    lateinit var btnImagen1: ImageButton
    lateinit var btnImagen2: ImageButton
    lateinit var btnImagen3: ImageButton
    lateinit var btnImagen4: ImageButton
    lateinit var btnImagen5: ImageButton
    lateinit var btnImagen6: ImageButton
    lateinit var btnImagen7: ImageButton
    lateinit var btnImagen8: ImageButton
    lateinit var btnImagen9: ImageButton
    var juegoService = JuegoService()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        jugar = findViewById(R.id.btnJugar)
        btnImagen1 = findViewById(R.id.btnImagen1)
        btnImagen2 = findViewById(R.id.btnImagen2)
        btnImagen3 = findViewById(R.id.btnImagen3)
        btnImagen4 = findViewById(R.id.btnImagen4)
        btnImagen5 = findViewById(R.id.btnImagen5)
        btnImagen6 = findViewById(R.id.btnImagen6)
        btnImagen7 = findViewById(R.id.btnImagen7)
        btnImagen8 = findViewById(R.id.btnImagen8)
        btnImagen9 = findViewById(R.id.btnImagen9)

        jugar.setOnClickListener() {
            enableDisableButton()
            mostrarDialogoInicioJuego()
        }
        btnImagen1.setOnClickListener(){
            seleccionafigura(it as ImageButton)
            muestraDialogo (juegoService.jugada(0,0))
        }
        btnImagen2.setOnClickListener(){
            seleccionafigura(it as ImageButton)
            muestraDialogo (juegoService.jugada(0,1))
        }
        btnImagen3.setOnClickListener(){
            seleccionafigura(it as ImageButton)
            muestraDialogo (juegoService.jugada(0,2))
        }
        btnImagen4.setOnClickListener(){
            seleccionafigura(it as ImageButton)
            muestraDialogo (juegoService.jugada(1,0))
        }
        btnImagen5.setOnClickListener(){
            seleccionafigura(it as ImageButton)
            muestraDialogo (juegoService.jugada(1,1))
        }
        btnImagen6.setOnClickListener(){
            seleccionafigura(it as ImageButton)
            muestraDialogo (juegoService.jugada(1,2))
        }
        btnImagen7.setOnClickListener(){
            seleccionafigura(it as ImageButton)
            muestraDialogo (juegoService.jugada(2,0))
        }
        btnImagen8.setOnClickListener(){
            seleccionafigura(it as ImageButton)
            muestraDialogo (juegoService.jugada(2,1))
        }
        btnImagen9.setOnClickListener(){
            seleccionafigura(it as ImageButton)
            muestraDialogo (juegoService.jugada(2,2))
            //reiniciarBotones()
        }

        juegoService.inicializar()
        enableDisableButton()
    }

    private fun enableDisableButton(){
        btnImagen1.isEnabled =  !btnImagen1.isEnabled
        btnImagen2.isEnabled =  !btnImagen2.isEnabled
        btnImagen3.isEnabled =  !btnImagen3.isEnabled
        btnImagen4.isEnabled =  !btnImagen4.isEnabled
        btnImagen5.isEnabled =  !btnImagen5.isEnabled
        btnImagen6.isEnabled =  !btnImagen6.isEnabled
        btnImagen7.isEnabled =  !btnImagen7.isEnabled
        btnImagen8.isEnabled =  !btnImagen8.isEnabled
        btnImagen9.isEnabled =  !btnImagen9.isEnabled
    }
    private fun seleccionafigura(imageButton: ImageButton){
        if (juegoService.figura == 'O')
            imageButton.setBackgroundResource(R.drawable.circulo)
        else
            imageButton.setBackgroundResource(R.drawable.cruz)
        imageButton.isEnabled=false
    }
    private fun muestraDialogo(mensaje: String){
        val builder: AlertDialog.Builder = AlertDialog.Builder(this)
        builder
            .setMessage(mensaje)
            .setTitle("Aviso")
            .setPositiveButton("¡Entendido!") { dialog, which ->
                // Verificar si el juego terminó por un ganador
                if (mensaje.contains(" gana!")) {
                    juegoService.inicializar()
                    mostrarDialogoReinicioJuego()
                }else if(mensaje.contains("¡Empate!")){
                    juegoService.inicializar()
                    mostrarDialogoReinicioJuego()
                }
            }

        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    private fun reiniciarBotones() {
        val imagenes = mutableListOf(btnImagen1, btnImagen2, btnImagen3, btnImagen4, btnImagen5, btnImagen6, btnImagen7, btnImagen8, btnImagen9)
        for (btn in imagenes) {
            btn.setBackgroundResource(R.drawable.limpio)
            btn.isEnabled = true
        }
    }

    private fun mostrarDialogoInicioJuego() {
        val builder: AlertDialog.Builder = AlertDialog.Builder(this)
        builder
            .setMessage("¿Quieres iniciar el juego?")
            .setPositiveButton("Sí") { dialog, which ->
                // Si se selecciona "Sí", desactiva el botón "Jugar"
                jugar.isEnabled = false
                mostrarAvisoTurno("X")
            }
            .setNegativeButton("No") { dialog, which ->
                // Si se selecciona "No", no haces nada
            }
            .show()
    }

    private fun mostrarDialogoReinicioJuego() {
        val builder: AlertDialog.Builder = AlertDialog.Builder(this)
        builder
            .setMessage("¿Quieres iniciar el juego?")
            .setPositiveButton("Sí") { dialog, which ->
                // Si se selecciona "Sí", desactiva el botón "Jugar"
                jugar.isEnabled = false
                reiniciarBotones()
                mostrarAvisoTurno("X")
            }
            .setNegativeButton("No") { dialog, which ->
                // Si se selecciona "No", no haces nada
            }
            .show()
    }

    private fun mostrarAvisoTurno(jugador: String) {
        val builder: AlertDialog.Builder = AlertDialog.Builder(this)
        builder
            .setMessage("Es el turno de $jugador")
            .setPositiveButton("Entendido") { dialog, which ->
                // Aquí puedes añadir cualquier acción que quieras realizar cuando se presiona "Entendido"
            }
            .show()
    }
}