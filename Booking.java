import java.util.*;
public class Booking{

    int booking_id, no_seats;
    public Flight flight;
    List<Passenger> passengers = new ArrayList<>();

    public int generateID() {
        return (int) ((Math.random() * (999 - 100)) + 100);
    }

    public void bookTicket(int noSeats, List<Flight> flights){
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter flight number: ");
        int flight_no = sc.nextInt();

        flight = flights.get(flight_no-1);

        if (noSeats <= flight.seatsAvl){
            this.no_seats = noSeats;
            for(int i = 1; i<=noSeats; i++){
                System.out.println("Enter details of passenger-"+i);
                System.out.print("Enter name: ");
                String name=sc.next();
                String passId = name.charAt(0)+name.charAt(name.length()-1)+Integer.toString(generateID());
                int seatNo = flight.maxSeats - flight.seatsAvl + 1;
                Passenger p = new Passenger(passId, name, seatNo);
                p.flight = flight;
                this.passengers.add(p);

                flight.seatsAvl-- ;
            }
            this.booking_id = (1+generateID())*2*flight_no*noSeats ;
        } else {System.out.println("Cannot book ticket! Available seats: "+flight.seatsAvl);}
        
        sc.close();
    }

    void showTicket(){
        System.out.println("----------------------------------------------------");
        System.out.println("Booking Id: "+this.booking_id);
        System.out.print("From: "+this.passengers.get(0).flight.src);
        System.out.println("\t To: "+this.passengers.get(0).flight.dest);
        System.out.println("Arrival: "+this.passengers.get(0).flight.deptDateTime);
        System.out.println("Departure: "+this.passengers.get(0).flight.arvDateTime);
        System.out.println("Passenger ID\tName\tSeatNo");
        this.passengers.forEach(p->{
            System.out.println(p.passId+ "\t\t" +p.name+"\t"+p.seatNo); });

        System.out.println("----------------------------------------------------");
    }
}