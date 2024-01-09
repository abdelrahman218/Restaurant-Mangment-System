package employees;
import java.io.Serializable;
public abstract class Person implements Serializable{
    private String Name;
    private String UserName;
    private String Password;
    private String Address;
    private String DateOfBirth;
    private String PhoneNum;
    private String Email;
    private int Id;
    private static int idGenerator=0;
    public Person(String Name, String Address, String DateOfBirth, String PhoneNum, String Email,String UserName,String Password) {
        setName(Name);
        setAddress(Address);
        setDateOfBirth(DateOfBirth);
        setPhoneNum(PhoneNum);
        setEmail(Email);
        setUserName(UserName);
        setPassword(Password);
        Id=++idGenerator;
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
    public void setUserName(String UserName) {
        this.UserName = UserName;
    }
    public void setPassword(String Password) {
        this.Password = encreptPassword(Password);
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
        return Id;
    }
    public String getUserName() {
        return UserName;
    }
    public String getPassword() {
    String encp="";
     int j=2;
     for(int i=0;i<Password.length();i++){
     encp+=(char)(Password.charAt(i)-j);
     }
        return encp;
    }
    public boolean checkpassword(String password){
    String check=encreptPassword(password);
    if(Password.equals(check)){
    return true;
    }else{
    return false;
    }
    }
    private String encreptPassword(String pass){
    String encp="";
    int j=2;
    for(int i=0;i<pass.length();i++){
    encp+=(char)(pass.charAt(i)+j);
    }
    return encp;
    }
}