package trial.controller

import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.GetMapping
import trial.util.Crypter

@RestController
class HomeController() {
    @GetMapping("/")
    fun index(): String {
        val crypter = Crypter("0123456789012345")
        val re = crypter.encrypt("hello")
        val rd = crypter.decrypt<String>(re)
        return re + "<br/>" + rd
    }
}