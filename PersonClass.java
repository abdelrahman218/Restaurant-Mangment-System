/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package person;

/**
 *
 * @author Admin
 */
public class PersonClass {
    private String Name;
    private String Address;
    private String DateOfBirth;
    private int PhoneNum;
    private String Email;

    public PersonClass(String Name, String Address, String DateOfBirth, int PhoneNum, String Email) {
        this.Name = Name;
        this.Address = Address;
        this.DateOfBirth = DateOfBirth;
        this.PhoneNum = PhoneNum;
        this.Email = Email;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public void setAddress(String Address) {
        this.Address = Address;
    }

    public void setDateOfBirth(String DateOfBirth) {
        this.DateOfBirth = DateOfBirth;
    }

    public void setPhoneNum(int PhoneNum) {
        this.PhoneNum = PhoneNum;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getName() {
        return Name;
    }

    public String getAddress() {
        return Address;
    }

    public String getDateOfBirth() {
        return DateOfBirth;
    }

    public int getPhoneNum() {
        return PhoneNum;
    }

    public String getEmail() {
        return Email;
    }
    
}
