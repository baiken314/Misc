import java.net.URL

fun main() {

    println("START")

    val apiResponse = URL("https://multris.glitch.me/scores").readText()
    println(apiResponse)

    println("END")

}