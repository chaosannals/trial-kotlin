package trial.sbdemo.app

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import trial.sbdemo.utilities.StringUtils
import org.apache.commons.text.WordUtils

@SpringBootApplication
class App {

}

fun main(args: Array<String>) {
    val tokens = StringUtils.split(MessageUtils.getMessage())
    val result = StringUtils.join(tokens)
    println(WordUtils.capitalize(result))

    runApplication<App>(*args) {
        
    }
}
