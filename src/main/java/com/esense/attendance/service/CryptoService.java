package com.esense.attendance.service;




import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class CryptoService {

    @Value("${app.hash.salt}")
    private String salt;

    public String Hash(String payload) {
        if(payload == null) return null;
        return DigestUtils.sha512Hex(salt + payload + salt);
    }
}
