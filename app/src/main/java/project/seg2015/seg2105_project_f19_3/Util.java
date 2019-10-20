package project.seg2015.seg2105_project_f19_3;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Util {
    public static byte[] stringToHash(String origin) {
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("SHA-256");
            md.update(new String(origin).getBytes());
            return md.digest();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }
}
