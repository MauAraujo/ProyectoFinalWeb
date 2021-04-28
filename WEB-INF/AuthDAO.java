package auth;

import java.sql.*;
import java.util.ArrayList;
import javax.crypto.*;
import java.security.*;
import java.util.*;
import com.google.gson.Gson;

public class AuthDAO {
  String result;

  public String LogIn(String name, String passwd) {
    String token = null;
    try {
      Class.forName("com.mysql.jdbc.Driver");
      Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/agileplanning", "root", "");
      Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
      ResultSet rs = stmt.executeQuery("SELECT * FROM `collaborators` WHERE name = " + "'" + name + "'");

      while (rs.next()) {
        String phash = rs.getString("passwd");
        byte[] salt = rs.getBytes("salt");
        String securePassword = getSecurePassword(passwd, salt);

        if (phash.equals(securePassword)) {
          Gson gson = new Gson();
          Map < String, Object > headerClaims = new HashMap();
          headerClaims.put("uid", rs.getString("uid"));
          headerClaims.put("name", rs.getString("name"));
          headerClaims.put("type", rs.getString("type"));
          token = gson.toJson(headerClaims);
        }
      }
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    } catch (SQLException e) {
      e.printStackTrace();
    }

    return token;
  }

  public String Register(String name, String passwd) {
    String token = null;
    try {
      Class.forName("com.mysql.jdbc.Driver");
      Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/agileplanning", "root", "");
      Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
      ResultSet rs = stmt.executeQuery("SELECT * FROM collaborators");

      byte[] salt = getSalt();
      String securePassword = getSecurePassword(passwd, salt);

      result = name + passwd;
      rs.moveToInsertRow();
      rs.updateString("name", name);
      rs.updateString("passwd", securePassword);
      rs.updateString("type", "admin");
      rs.updateBytes("salt", salt);
      rs.insertRow();

    } catch (ClassNotFoundException e) {
        result = e.getMessage();
      e.printStackTrace();
    } catch (SQLException e) {
        result = e.getMessage();
      e.printStackTrace();
    } catch (NoSuchAlgorithmException e) {
        result = e.getMessage();
      e.printStackTrace();
    }
    return token;
  }

  private static String getSecurePassword(String passwordToHash, byte[] salt) {
    String generatedPassword = null;
    try {
      MessageDigest md = MessageDigest.getInstance("MD5");
      md.update(salt);
      byte[] bytes = md.digest(passwordToHash.getBytes());
      StringBuilder sb = new StringBuilder();

      for (int i = 0; i < bytes.length; i++) {
        sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
      }

      generatedPassword = sb.toString();
    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
    }
    return generatedPassword;
  }

  private static byte[] getSalt() throws NoSuchAlgorithmException {
    SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
    byte[] salt = new byte[16];
    sr.nextBytes(salt);
    return salt;
  }

  public String getResult() {
    return result;
  }
}
