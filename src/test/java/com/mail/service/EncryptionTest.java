package com.mail.service;

import org.junit.Test;

public class EncryptionTest {
    private final String parola = "parola";
    private final String key = "123";
    private String resultCrypt;

    private Encryption encryption = new Encryption();

    @Test
    public void crypt() {
        resultCrypt = encryption.crypt("", key);
        assert(resultCrypt ==null);

        resultCrypt = encryption.crypt(parola, "");
        assert(resultCrypt ==null);

        resultCrypt = encryption.crypt(parola, key);
    }

    @Test
    public void decrypt() {
        String resultDeCrypt = encryption.decrypt("", key);
        assert(resultDeCrypt ==null);

        resultDeCrypt = encryption.decrypt(parola, "");
        assert(resultDeCrypt ==null);

        resultDeCrypt = encryption.decrypt(resultCrypt, key);
        assert(resultDeCrypt.equals(parola));

    }
}