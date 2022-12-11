import java.util.*;
public class Booking{

    int booking_id, no_seats;
    public Flight flight;
    List<Passenger> passengers = new ArrayList<>();

    public int generateID() {
        return (int) ((Math.random() * (999 - 100)) + 100);
    }

    public void bookTicket(List<Flight> flights, int flight_no, List<String> pass_names){

        
        int noSeats = pass_names.size();

        flight = flights.get(flight_no-1);  // get flight object from flight number
        
        System.out.print("number seats: "+noSeats);
        // if enough tickets are available or 
        // 9 is the maximum no of tickets one can book at once.
        if (noSeats <= flight.seatsAvl || noSeats <= 9){ 
            System.out.println("if >> >> >> ");
            this.no_seats = noSeats;
            for(int i = 0; i < noSeats; i++){
                // System.out.println("Enter details of passenger-"+i);
                // System.out.print("Enter name: ");
                // String name=sc.next();
                String name = pass_names.get(i);
                System.out.println("name >> " + name); // -----------------------
                String passId = name.charAt(0)+name.charAt(name.length()-1)+Integer.toString(generateID());
               System.out.println("passid >> " + passId); // -----------------------
                int seatNo = flight.maxSeats - flight.seatsAvl + 1;
               System.out.println("seat no >> " + seatNo); // -----------------------
                Passenger p = new Passenger(passId, name, seatNo);
                p.flight = flight; // assign flight to each passenger
                this.passengers.add(p); // add passenger to List
                flight.seatsAvl-- ;
            }
            this.booking_id = (1+generateID())*2*flight_no*noSeats ;
//            System.out.println("bid> " + this.booking_id); // -----------------------


            // flight.seatsAvl = flight.seatsAvl - noSeats; // reduce the number of seats available
        }
        else {System.out.println("Cannot book ticket! Available seats: "+flight.seatsAvl);}
    
        // sc.close();
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

//    void cancelTicket(){
//        this.flight.seatsAvl += this.no_seats;
//        // get the index of booking id of ticket
//
//    }
}