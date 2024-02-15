package com.josue.pasuy.arteaga.calcularnotas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.ProgressBar

class MainActivity : AppCompatActivity() {

    private lateinit var barritaxd : ProgressBar
    private lateinit var numero : EditText
    private lateinit var nota : EditText


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        barritaxd = findViewById(R.id.barra_INSANA)
        numero = findViewById(R.id.insano)
        nota = findViewById(R.id.waos)


// 1. Examen parcial 20%     nata estudiante 3.2
// 2. Examen parcial 20%     nota estudiante 1.5
// 3. Examen final 30%       nota estudiante 4.8
// 4. participación 5%       nota estudiante 5.0
// 5. Quiz 10&               nota estudiante 3.9
// 6. Talleres 15%           nota estudiante 2.5

    }
    /*Recolectamos la informacion que necesitamos, como ya sabemos necesitamos calcular el porcentaje de notas que saco el joven o estudiante
     *  para ello necesitariamos valores exactos de cuanto vale cada nota del examen parcial 1 y 2 y el examen final, etc. Eso lo declarariamos
     *  como un valor ya que es elporcentaje que vale cada nota
     */

    class estudiante(){
        val nombre : String,
        val nota : Array<Double>,
        val porcentaje : Array<Int>


        fun cNotas(): Double {
            val insane = 0
            val indice = 0

            for (n in nota) {
                +=

            }


        }}}




















































