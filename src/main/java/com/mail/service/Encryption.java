package com.mail.service;

import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;

@Component
public class Encryption {

    @PersistenceContext
    private EntityManager em;

    public String crypt(String text, String key){
        if(text.isEmpty() || key.isEmpty())
            return null;

        StoredProcedureQuery storedProcedure = em.createStoredProcedureQuery("encrypted");

        storedProcedure.registerStoredProcedureParameter("text", String.class, ParameterMode.IN);
        storedProcedure.registerStoredProcedureParameter("cryptKey", String.class, ParameterMode.IN);
        storedProcedure.registerStoredProcedureParameter("encoded", String.class, ParameterMode.OUT);
        storedProcedure.setParameter("text", text);
        storedProcedure.setParameter("cryptKey", key);

        storedProcedure.execute();

        return (String)storedProcedure.getOutputParameterValue("encoded");
    }

    public String decrypt(String text, String key){
        if(text.isEmpty() || key.isEmpty())
            return null;

        StoredProcedureQuery storedProcedure = em.createStoredProcedureQuery("decrypted");

        storedProcedure.registerStoredProcedureParameter("text", String.class, ParameterMode.IN);
        storedProcedure.registerStoredProcedureParameter("cryptKey", String.class, ParameterMode.IN);
        storedProcedure.registerStoredProcedureParameter("decoded", String.class, ParameterMode.OUT);
        storedProcedure.setParameter("text", text);
        storedProcedure.setParameter("cryptKey", key);

        storedProcedure.execute();

        return (String)storedProcedure.getOutputParameterValue("decoded");
    }
}
