package pasa.cbentley.java.examples.common;

import java.io.Serializable;

public class Person implements Serializable{
   
   private String lastName;
   
   private String firstName;
   
   public Person(String firstName, String lastName) {
      this.firstName = firstName;
      this.lastName = lastName;
      
   }

   public String getLastName() {
      return lastName;
   }

   public String getFirstName() {
      return firstName;
   }

}
