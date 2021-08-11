package com.company_name.utils

import com.company_name.utils.Helper.Const.ENCRYPTION_ALGORITHM_NAME
import java.security.InvalidKeyException
import javax.crypto.*
import javax.crypto.spec.SecretKeySpec
import kotlin.experimental.and

class StringEncryptionTools @Throws(Exception::class)
constructor() {

    private lateinit var key: SecretKey
    private lateinit var cipher: Cipher

    init {
        init()
    }

    @Throws(Exception::class)
    private fun init() {
        val keygenerator = KeyGenerator.getInstance(ENCRYPTION_ALGORITHM_NAME)
        val secretkey = keygenerator.generateKey()

        key = SecretKeySpec(secretkey.encoded, ENCRYPTION_ALGORITHM_NAME)
        cipher = Cipher.getInstance(ENCRYPTION_ALGORITHM_NAME)
    }

    @Throws(InvalidKeyException::class, BadPaddingException::class, IllegalBlockSizeException::class)
    fun encryptText(message: String): String? {
        cipher.init(Cipher.ENCRYPT_MODE, key)
        val encrypted = cipher.doFinal(message.toByteArray())

        return /*HexConverter.printHexBinary(encrypted)*/ bytesToHex(encrypted)
    }

    @Throws(InvalidKeyException::class, BadPaddingException::class, IllegalBlockSizeException::class)
    fun decryptText(encryptedString: String): String? {
        cipher.init(Cipher.DECRYPT_MODE, key)
        val decrypted = cipher.doFinal(/*HexConverter.parseHexBinary(encryptedString)*/hexToBytes(encryptedString))

        return /*HexConverter.printHexBinary(decrypted)*/bytesToHex(decrypted)
    }

    private fun hexToBytes(str: String?): ByteArray? {
        if (str == null) {
            return null
        } else if (str.length < 2) {
            return null
        } else {
            val len = str.length / 2
            val buffer = ByteArray(len)
            for (i in 0 until len) {
                buffer[i] = Integer.parseInt(str.substring(i * 2, i * 2 + 2), 16).toByte()
            }
            return buffer
        }
    }

    private fun bytesToHex(data: ByteArray?): String? {
        if (data == null) {
            return null
        } else {
            val str = StringBuilder()
            for (aData in data) {
                if (aData and 0xFF.toByte() < 16)
                    str.append("0").append(Integer.toHexString((aData and 0xFF.toByte()).toInt()))
                else
                    str.append(Integer.toHexString((aData and 0xFF.toByte()).toInt()))
            }
            return str.toString().toUpperCase()
        }
    }

}
