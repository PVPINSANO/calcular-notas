package com.josue.pasuy.arteaga.calcularnotas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    private lateinit var numeroPorcantaje: TextView
    private lateinit var notaFinal: TextView
    private lateinit var resultado: TextView
    private lateinit var aprobo: TextView


    private lateinit var nombreX: EditText
    private lateinit var notaX: EditText
    private lateinit var porcentajeN: EditText

    private lateinit var terminarN: Button
    private lateinit var agregarN: Button

    private lateinit var barritaxd: ProgressBar


    var porcentajeAcumulado = 0

    val listaNotas = mutableListOf<Double>()
    val listaPorcentaje = mutableListOf<Int>()

    var estudianteInsano = Estudiante()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        numeroPorcantaje = findViewById(R.id.showporcentage)
        notaFinal = findViewById(R.id.notafinal)
        resultado = findViewById(R.id.promedio)
        aprobo = findViewById(R.id.bb)



        nombreX = findViewById(R.id.nombreXD)
        notaX = findViewById(R.id.notaXD)
        porcentajeN = findViewById(R.id.porcentajeNota)
        terminarN = findViewById(R.id.terminar)
        agregarN = findViewById(R.id.siguiente_nota)

        barritaxd = findViewById<ProgressBar>(R.id.barra_INSANA)


        agregarN.setOnClickListener {
            val nombre = nombreX.text.toString()
            val nota = notaX.text.toString()
            val porcentaje = porcentajeN.text.toString()
            val porcentajex = numeroPorcantaje.setText(String())

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





                    nombreX.isEnabled = false
                    notaX.text.clear()
                    porcentajeN.text.clear()


                    Toast.makeText(
                        this,
                        "La nota fue ingresada bien", Toast.LENGTH_LONG
                    ).show()

                } else {
                    Toast.makeText(
                        this, "Ingreso los datos mal ",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }
        terminarN.setOnClickListener {
           estudianteInsano.nombre = nombreX.text.toString()
           estudianteInsano.notas = listaNotas
           estudianteInsano.porcentajes = listaPorcentaje
            resultado.text = estudianteInsano.calcularPromedio().toString()
           notaFinal.text = estudianteInsano.notaFinal().toString()
            //tvMostrar.setText(String.valueOf(resultado)) mostrar resultado en textview


        }


    }

    fun actualizarProgress(porcentaje: Int) {
        barritaxd.progress = porcentaje


        if (porcentaje >= 100) {
            terminarN.isEnabled = true
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




    /*Recolectamos la informacion que necesitamos, como ya sabemos necesitamos calcular el porcentaje de notas que saco el joven o estudiante
     *  para ello necesitariamos valores exactos de cuanto vale cada nota del examen parcial 1 y 2 y el examen final, etc. Eso lo declarariamos
     *  como una variable ya que es elporcentaje que vale cada nota



*/
}

    class Estudiante() {
        var nombre: String = ""
        var notas: List<Double> = listOf()
        var porcentajes: List<Int> = listOf()

        fun calcularPromedio(): Double {
            var sumaNotas = 0.0
            for (n in notas) {
                sumaNotas += n
            }

            return sumaNotas / notas.size

        }

        fun notaFinal(): Double {
            var notaF: Double = 0.0
            var contador = 0

            for (n in notas) {
                notaF += (n * porcentajes[contador] / 100)
               contador++
            }

            return notaF


        }


    }




