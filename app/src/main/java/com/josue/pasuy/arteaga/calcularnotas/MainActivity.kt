package com.josue.pasuy.arteaga.calcularnotas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.Switch
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    private lateinit var notaFinal : TextView
    private lateinit var  numeroPorcentaje : TextView
    private lateinit var Cnota: TextView
    private lateinit var Fnota : TextView
    private lateinit var resultado: TextView
    private lateinit var flopatext : TextView

    private lateinit var nombreX: EditText
    private lateinit var notaX: EditText
    private lateinit var porcentajeN: EditText
    
    private lateinit var terminarN: Button
    private lateinit var SiguienteNota: Button
    private lateinit var  SiguienteEstudiante : Button
   

    private lateinit var barritaxd: ProgressBar

    private lateinit var floppa : ImageView

    private lateinit var floppaMode : Switch


    var porcentajeAcumulado = 0

    val listaNotas = mutableListOf<Double>()
    val listaPorcentaje = mutableListOf<Int>()

    var estudianteActual = Estudiante()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Cnota = findViewById(R.id.aa)
        Fnota = findViewById(R.id.bb)
        notaFinal = findViewById(R.id.notafinal)
        resultado = findViewById(R.id.promedio)
        numeroPorcentaje = findViewById(R.id.showporcentage)


        nombreX = findViewById(R.id.nombreXD)
        notaX = findViewById(R.id.notaXD)
        porcentajeN = findViewById(R.id.porcentajeNota)


        terminarN = findViewById(R.id.terminar)
        SiguienteNota = findViewById(R.id.siguiente_nota)
        SiguienteEstudiante = findViewById(R.id.Siguiente_estudiante)

        barritaxd = findViewById<ProgressBar>(R.id.barra_INSANA)

         floppa = findViewById(R.id.floppa)
        floppaMode = findViewById(R.id.floppamode)
       flopatext = findViewById(R.id.textfloppa)

        flopatext.visibility = View.INVISIBLE
        floppa.visibility = View.INVISIBLE

        floppaMode.setOnClickListener {
            if (floppaMode.isChecked){
               flopatext.visibility  = View.VISIBLE
               floppa.visibility = View.VISIBLE
                flopatext.setText("SAQUEME 5 PROFESOR ")

        Toast.makeText(this, "Floppa mode activado" ,Toast.LENGTH_SHORT).show()
            }else {

                flopatext.visibility = View.INVISIBLE
                floppa.visibility = View.INVISIBLE
                Toast.makeText(this, "Floppa mode desactivado" ,Toast.LENGTH_SHORT).show()
            }
        }

        SiguienteNota.setOnClickListener {

            val nombre = nombreX.text.toString()
            val nota = notaX.text.toString()
            val porcentaje = porcentajeN.text.toString()


            if (validarVacio(nombre, nota, porcentaje)) {
                if (validarNombre(nombre)
                    && validarNota(nota.toDouble())
                    && validarPorcentaje(
                        porcentaje.toInt()
                    )
                ) {

                    listaNotas.add(nota.toDouble())
                    listaPorcentaje.add(porcentaje.toInt())

                    porcentajeAcumulado += porcentaje.toInt()

                    actualizarProgress(porcentajeAcumulado)

                    SiguienteEstudiante.isEnabled = false
                    SiguienteEstudiante.visibility = View.INVISIBLE



                    nombreX.isEnabled = false
                    notaX.text.clear()
                    porcentajeN.text.clear()


                    Toast.makeText(this, "La nota fue ingresada bien", Toast.LENGTH_LONG).show()

                } else {
                    Toast.makeText(this, "Ingreso los datos mal ", Toast.LENGTH_LONG).show()

                }
            }else{
                Toast.makeText(
                    this, "Ingreso los datos mal ",
                    Toast.LENGTH_LONG
                ).show()
            }
        }

        terminarN.setOnClickListener {
            estudianteActual.nombre = nombreX.text.toString()
            estudianteActual.notas = listaNotas
            estudianteActual.porcentajes = listaPorcentaje
            resultado.text = estudianteActual.calcularPromedio().toString()
            notaFinal.text = estudianteActual.notaFinal().toString()
            barritaxd.progress = 0

            notaFinal.visibility = View.VISIBLE
            resultado.visibility = View.VISIBLE
            SiguienteEstudiante.isEnabled = true
            SiguienteEstudiante.visibility = View.VISIBLE


            //viev visible
        }



        SiguienteEstudiante.setOnClickListener {

            terminarN.visibility = View.VISIBLE


            notaFinal.visibility = View.VISIBLE
            resultado.visibility = View.VISIBLE
            nombreX.text.clear()
            notaX.text.clear()
            porcentajeN.text.clear()
            numeroPorcentaje.text = ""
            nombreX.isEnabled = true
            barritaxd.progress = 0
            porcentajeAcumulado = 0
            resultado.text = ""
            notaFinal.text = ""


            listaNotas.clear()
            listaPorcentaje.clear()
        }


    }



    fun actualizarProgress(porcentaje: Int)  {
        barritaxd.progress = porcentaje


        numeroPorcentaje.text = "$porcentaje%"
        if (porcentaje >= 100 ) {
            terminarN.isEnabled = true
        }else{
            terminarN.isEnabled = false
        }

    }

    fun mostrarMensaje(mensaje: String) {
        Toast.makeText(
            this,
            mensaje,
            Toast.LENGTH_LONG
        ).show()
    }

    fun validarVacio(nombre: String, nota: String, porcentaje: String): Boolean {
        return !nombre.isNullOrEmpty() && !nota.isNullOrEmpty() && !porcentaje.isNullOrEmpty()
    }

    fun validarNombre(nombre: String): Boolean {
        return !nombre.matches(Regex(".*\\d.*"))

    }

    fun validarNota(nota: Double): Boolean {
        return nota >= 0 && nota <= 5
    }

    fun validarPorcentaje(porcentaje: Int): Boolean {
        return porcentajeAcumulado + porcentaje <= 100


    }
}
class Estudiante() {
    var nombre: String = ""
    var notas: List<Double> = listOf()
    var porcentajes: List<Int> = listOf()
    ///
    fun calcularPromedio(): Double {
        var sumaNotas = 0.0
        for (n in notas) {
            sumaNotas += n
        }

        return  Math.round(sumaNotas * 100.00) / 100.00  /notas.size

    }

    fun notaFinal(): Double {
        var notaF: Double = 0.0
        var contador = 0

        for (n in notas) {
            notaF += (n * porcentajes[contador] / 100)
            contador++
        }
        return Math.round(notaF * 100.00) / 100.00
    }

}