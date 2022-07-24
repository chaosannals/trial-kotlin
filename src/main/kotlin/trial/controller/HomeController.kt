package trial.controller

import kotlin.random.Random
import java.util.Base64
import java.nio.charset.StandardCharsets
import javax.crypto.Cipher
import javax.crypto.spec.SecretKeySpec
import javax.crypto.spec.IvParameterSpec
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.GetMapping

@RestController
class HomeController() {
    @GetMapping("/")
    fun index(): String {
        val key = "123456789012345678901234567890AB"
        val sk = SecretKeySpec(key.toByteArray(), "AES")
        val cipher = Cipher.getInstance("AES/CBC/PKCS5Padding")
        val iv = ByteArray(16)
        Random.nextBytes(iv)
        val ivps = IvParameterSpec(iv)
        cipher.init(Cipher.ENCRYPT_MODE, sk, ivps)

        var content = "hello"
        var r = cipher.doFinal(content.toByteArray())
        val b64encoder = Base64.getEncoder()
        return b64encoder.encodeToString(r)
    }
}