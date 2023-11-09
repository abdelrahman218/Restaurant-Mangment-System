package system;
import Employees.Receptionist;
public class Main {
    public static void main(String[] args) {
        Table[] i=new Table[20];
        for(int j=0;j<3;j++){
            i[j]=new Table(10);
        }
        Receptionist mona=new Receptionist("Mona", "", "12/11/2004", "01002196513", "jghwdghfgdfjds");
        
        mona.createReservation();
        mona.cancelReservation();
    }
}