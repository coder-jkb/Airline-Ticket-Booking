import java.util.*;
public class Passenger{
    public String passId, name;
    public int seatNo;
    public Flight flight; // flight object

    public Passenger(String passId, String name, int seatNo){
        this.passId = passId;
        this.name = name;
        this.seatNo = seatNo;
    }
}

