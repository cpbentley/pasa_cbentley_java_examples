package pasa.cbentley.java.examples.crypto;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SealedObject;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import pasa.cbentley.java.examples.common.Person;

/**
 * https://stackoverflow.com/questions/16950833/is-there-an-easy-way-to-encrypt-a-java-object
 * 
 * @author Charles Bentley
 *
 */
public class SealedObjectWriting {

   public static void main(String[] args) throws NoSuchAlgorithmException, NoSuchPaddingException, IOException, InvalidKeyException, IllegalBlockSizeException, ClassNotFoundException, BadPaddingException {

      String fileName = "result.dat"; //some result file

      //You may use any combination, but you should use the same for writing and reading
      SecretKey key64 = new SecretKeySpec(new byte[] { 0x00, 0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07 }, "Blowfish");
      Cipher cipher = Cipher.getInstance("Blowfish");

      //Code to write your object to file
      cipher.init(Cipher.ENCRYPT_MODE, key64);
      Person person = new Person("Frank", "Smith"); //some object to serialise
      SealedObject sealedObject = new SealedObject(person, cipher);
      FileOutputStream fileOut = new FileOutputStream(fileName);
      CipherOutputStream cipherOutputStream = new CipherOutputStream(new BufferedOutputStream(fileOut), cipher);
      ObjectOutputStream outputStream = new ObjectOutputStream(cipherOutputStream);
      outputStream.writeObject(sealedObject);
      outputStream.close();

      //Code to read your object from file
      cipher.init(Cipher.DECRYPT_MODE, key64);
      CipherInputStream cipherInputStream = new CipherInputStream(new BufferedInputStream(new FileInputStream(fileName)), cipher);
      ObjectInputStream inputStream = new ObjectInputStream(cipherInputStream);
      SealedObject sealedObjectRead = (SealedObject) inputStream.readObject();
      Person person1 = (Person) sealedObjectRead.getObject(cipher);
      
      System.out.println(person1.getFirstName() + " "+ person1.getLastName());
      
      inputStream.close();
   }
}
