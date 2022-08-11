package trial.sbdemo.app.controller

import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.GetMapping

@RestController
class HomeController() {
    @GetMapping("/")
    fun index(): String {
        return "aaaa"
    }
}