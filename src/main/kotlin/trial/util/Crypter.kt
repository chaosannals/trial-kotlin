package trial.util

import kotlin.random.Random
import kotlinx.serialization.*
import kotlinx.serialization.json.*
import java.util.Base64
import java.nio.charset.StandardCharsets
import javax.crypto.Cipher
import javax.crypto.spec.SecretKeySpec
import javax.crypto.spec.IvParameterSpec

/**
 * 
 */
class Crypter {
    val key: ByteArray
    val algorithm: String
    val skey: SecretKeySpec

    constructor(key: ByteArray, algorithm: String ="AES/CBC/PKCS5Padding") {
        this.key = key
        this.algorithm = algorithm
        this.skey = SecretKeySpec(key, algorithm.split("/")[0])
    }

    constructor(key: String, algorithm: String = "AES/CBC/PKCS5Padding") {
        this.key = key.toByteArray()
        this.algorithm = algorithm
        this.skey = SecretKeySpec(this.key, algorithm.split("/")[0])
    }

    fun encrypt(content: ByteArray) : ByteArray {
        val iv = ByteArray(16)
        Random.nextBytes(iv)
        val ivps = IvParameterSpec(iv)
        val cipher = Cipher.getInstance(algorithm)
        cipher.init(Cipher.ENCRYPT_MODE, skey, ivps)
        var r = cipher.doFinal(content)
        return iv + r
    }

    inline fun <reified T> encrypt(content: T) : String {
        val c = Json.encodeToString(content)
        val r = encrypt(c.toByteArray())
        val b64encoder = Base64.getEncoder()
        return b64encoder.encodeToString(r)
    }

    fun decrypt(ciphertext: ByteArray) : ByteArray {
        val iv = ciphertext.take(16).toByteArray()
        val d = ciphertext.drop(16).toByteArray()
        val ivps = IvParameterSpec(iv)
        val cipher = Cipher.getInstance(algorithm)
        cipher.init(Cipher.DECRYPT_MODE, skey, ivps)
        return cipher.doFinal(d)
    }

    inline fun <reified T> decrypt(ciphertext: String): T {
        val b64decoder = Base64.getDecoder()
        val raw = b64decoder.decode(ciphertext)
        val r = decrypt(raw)
        val t = String(r, Charsets.UTF_8)
        return Json.decodeFromString<T>(t)
    }
}