package Employees;

public abstract class Person {
    private int ID;
    private static int idGenerator=0;
    private String Name;
    private String Address;
    private String DateOfBirth;
    private String PhoneNum;
    private String Email;

    public Person(String Name, String Address, String DateOfBirth, String PhoneNum, String Email) {
        this.Name = Name;
        this.Address = Address;
        this.DateOfBirth = DateOfBirth;
        this.PhoneNum = PhoneNum;
        this.Email = Email;
        ID=++idGenerator;
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

    public void setPhoneNum(String PhoneNum) {
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

    public String getPhoneNum() {
        return PhoneNum;
    }

    public String getEmail() {
        return Email;
    }
    public int getId(){
        return ID;
    }
    
}