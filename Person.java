package Employees;

public abstract class Person {
    private String Name;
    private String Address;
    private String DateOfBirth;
    private long PhoneNum;
    private String Email;

    public Person(String Name, String Address, String DateOfBirth, long PhoneNum, String Email) {
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

    public long getPhoneNum() {
        return PhoneNum;
    }

    public String getEmail() {
        return Email;
    }
    
}
