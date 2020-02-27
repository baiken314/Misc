package edu.monmouth.s1175816.ciphermaker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private val fourWaySymmetricals = listOf("a","b","c","d","h","j","k","l","o","q","u","x","y")
    private val twoWaySymmetricals = listOf("e","i","n","t")
    private val letters = fourWaySymmetricals + twoWaySymmetricals
    private val MAX_STRING_LENGTH: Int = 1400

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        txtOutput.visibility = View.GONE
    }

    fun btnChangeClick(view: View) {
        if (etxInput.visibility == View.VISIBLE) {
            etxInput.visibility = View.GONE
            txtOutput.visibility = View.VISIBLE
            txtOutput.text = etxInput.text.toString()
        }
        else {
            etxInput.visibility = View.VISIBLE
            txtOutput.visibility = View.GONE
        }
    }

    fun btnGenerateTextClick(view: View) {

        var string = ""

        for (i in 0..50) {

            var endProbability = 0.0

            while (endProbability < .785) {
                endProbability = Random.nextDouble()
                var nextChar = letters[(letters.size * Random.nextDouble()).toInt()]
                if (fourWaySymmetricals.contains(nextChar)) {
                    var probability = Random.nextDouble()
                    if (probability < .25) {
                        nextChar += nextChar
                    } else if (probability < .5) {
                        nextChar = nextChar.capitalize()
                    } else if (probability < .75) {
                        nextChar = nextChar.capitalize()
                        nextChar += nextChar
                    }
                }
                else if (twoWaySymmetricals.contains(nextChar)) {
                    var probability = Random.nextDouble()
                    if (probability < .5) {
                        nextChar = nextChar.capitalize()
                    }
                }
                string += nextChar + ","
            }

            if (Random.nextDouble() < .9) {
                string += " "
            }

        }

        etxInput.setText(string)
        txtOutput.text = string

    }

}
