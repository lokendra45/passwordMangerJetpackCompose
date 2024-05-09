package com.loken.crypto

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.Base64
import javax.crypto.Cipher
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec

object EncryptionUtil {
    private const val ALGORITHM = "AES/CBC/PKCS7Padding"
    private const val KEY_SIZE = 256
    private const val IV_SIZE = 16

    suspend fun encrypt(data: String, key: String): String? {
        val secretKey = SecretKeySpec(key.toByteArray(), "AES")
        val iv = IvParameterSpec(ByteArray(IV_SIZE))
        val cipher = Cipher.getInstance(ALGORITHM)
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, iv)
        return withContext(Dispatchers.Default){Base64.getEncoder().encodeToString(cipher.doFinal(data.toByteArray()))}

    }

    suspend fun decrypt(data: String, key: String): String {
        val secretKey = SecretKeySpec(key.toByteArray(), "AES")
        val iv = IvParameterSpec(ByteArray(IV_SIZE))
        val cipher = Cipher.getInstance(ALGORITHM)
        cipher.init(Cipher.DECRYPT_MODE, secretKey, iv)
        return withContext(Dispatchers.Default) {String(cipher.doFinal(Base64.getDecoder().decode(data)))}
    }
}