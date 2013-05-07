package org.lacassandra.smooshyfaces;

import org.lacassandra.smooshyfaces.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

public class SessionKeyGenerator {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public String generateKey(User user) {
        String key = null;
        UUID uuid = UUID.randomUUID();
        String data = String.format("%s:%s:%s",
                uuid.toString(),
                user.getId().toString(),
                Long.toString(System.currentTimeMillis()));
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            digest.update(data.getBytes());
            byte[] digestBytes = digest.digest();

            StringBuffer buffer = new StringBuffer();
            for (int i = 0; i < digestBytes.length; ++i) {
                buffer.append(String.format("%02x", digestBytes[i]));
            }
            key = buffer.toString();
        }
        catch (NoSuchAlgorithmException e) {
            logger.error("Error in SessionKeyGenerator", e);
        }
        return key;
    }
}
