import java.util.*;
public class Main {
    public static void main(String[] args) {
        List <Flight> flights = new ArrayList<Flight>();
        flights.add(new Flight(1,"Air India",300, 300, 7500, "BOM", "DEL", "07-12-2022 11:45 IST", "07-12-2022 14:30 IST"));
        flights.add(new Flight(2,"Indigo",   250, 250, 7450, "DEL", "BOM", "07-12-2022 16:30 IST", "07-12-2022 18:40 IST"));
        flights.add(new Flight(3,"Spice jet",250, 250, 5100, "BOM", "BLR", "08-12-2022 23:00 IST", "09-12-2022 00:45 IST"));
        flights.add(new Flight(4,"Indigo",   300, 300, 4950, "BLR", "BOM", "09-12-2022 12:00 IST", "09-12-2022 14:45 IST"));

        System.out.println("----------------------------------------------------");
        System.out.println("Details of available flight");
        System.out.println("----------------------------------------------------");
        flights.forEach(f->{ f.showFlight(); });

        List <Booking> tickets = new ArrayList<Booking>();
        System.out.println("Options:");
        System.out.println("[1] Book Ticket");
        System.out.println("[2] Show Ticket");
        System.out.println("[3] Cancel Ticket");
        System.out.println("[0] Exit");
        int choice=0;

        Scanner sc = new Scanner(System.in);
        do {
            Booking b = new Booking();
            System.out.print("Enter your choice >> ");
            choice = sc.nextInt();
            if (choice==0){
                System.out.println("Thank You!");
            } else if (choice==1){
                System.out.print("Enter number of passengers: ");
                int no_pass = sc.nextInt();
                b.bookTicket(no_pass, flights);
                tickets.add(b);
            } else if (choice==2){
                tickets.forEach(t->{t.showTicket();});
            } else if (choice==3){
                System.out.print("Enter booking id of ticket to be cancelled: ");
                // cancel all passengers or selective?
                // find ticket in 'tickets' List
                // remove the Booking object entire ticket is to be cancelled
                // remove particular passenger from ticket.passenger in case of specific cancellation
            }
        } while (choice != 0);
    }
}