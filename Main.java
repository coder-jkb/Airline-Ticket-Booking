import java.util.*;
import javax.swing.* ;
import java.awt.event.*; // awt List also gets imported giving ambiguous type error 
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        java.util.List <Flight> flights = new ArrayList<Flight>();
        flights.add(new Flight(1,"Air India",300, 300, 7500, "BOM", "DEL", "07-12-2022 11:45 IST", "07-12-2022 14:30 IST"));
        flights.add(new Flight(2,"Indigo",   250, 250, 7450, "DEL", "BOM", "07-12-2022 16:30 IST", "07-12-2022 18:40 IST"));
        flights.add(new Flight(3,"Spice jet",250, 250, 5100, "BOM", "BLR", "08-12-2022 23:00 IST", "09-12-2022 00:45 IST"));
        flights.add(new Flight(4,"Indigo",   300, 300, 4950, "BLR", "BOM", "09-12-2022 12:00 IST", "09-12-2022 14:45 IST"));

        JFrame frame = new JFrame();

        JLabel no_pass_label, flight_no_label;
        JTextField no_pass;
        JButton proceed, back, price;
        String[] flight_no = {"1. BOM - DEL","2. DEL - BOM","3. BOM - BLR","4. BLR - BOM"};
        JComboBox<String> cb = new JComboBox<String>(flight_no);

        no_pass_label = new JLabel("Number of passengers: ");
        no_pass = new JTextField(2);
        flight_no_label = new JLabel("Select Fight:");
        proceed = new JButton("Proceed");

        cb.setSelectedItem(0);

        flight_no_label.setBounds(50, 50, 100, 30);
        cb.setBounds(200,50,200,30);

        no_pass_label.setBounds(50, 100, 150, 30);
        no_pass.setBounds(200,100,200,30);

        proceed.setBounds(100,180,100,30);

        
        JPanel panel = new JPanel();
        panel.setLayout(null);

        panel.add(no_pass_label);
        panel.add(no_pass);
        panel.add(flight_no_label);
        panel.add(cb);
        panel.add(proceed);

        frame.add(panel);

        frame.setSize(800,800);
        frame.setVisible(true);
        frame.setLayout(null);        
        
        back = new JButton("Back");
        back.setBounds(50, 300, 100, 30);
        
        price = new JButton("Show Price");
        price.setBounds(200, 300, 100, 30);


        java.util.List<JTextField> name_fields = new java.util.ArrayList<JTextField>();
        java.util.List<JLabel> name_labels = new java.util.ArrayList<JLabel>();

        proceed.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                panel.add(back);
                panel.add(price);
                panel.remove(proceed);

                frame.add(panel);

                int no_seats = Integer.parseInt(no_pass.getText());

                for(int i=1;i<= no_seats;i++){
                    name_fields.add(new JTextField());
                    name_labels.add(new JLabel("Name of passenger"+i));
                }
                for(int i=1;i<= no_seats;i++){
                    name_labels.get(i-1).setBounds(50,(i*50)+100,150,30);
                    panel.add(name_labels.get(i-1));
                };
                for(int i=1;i<= no_seats;i++){
                    name_fields.get(i-1).setBounds(200, (i*50)+100,150,30);
                    panel.add(name_fields.get(i-1));
                };
                
                frame.revalidate();
                frame.repaint();
            }
        });
    
        price.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                panel.removeAll();
                int no_seats = name_fields.size();
                int flight_num = cb.getSelectedIndex()+1;
                Flight selFlight = flights.get(flight_num-1);
                
                JLabel flight_num_name = new JLabel("Flight no.: "+flight_num+"  Flight Name: "+selFlight.fName);
                flight_num_name.setBounds(50,50,200,30);
                panel.add(flight_num_name);
                
                JLabel flight_src_dest = new JLabel("Source: "+selFlight.src+" - Destination: "+selFlight.dest);
                flight_src_dest.setBounds(50,80,250,30);
                panel.add(flight_src_dest);
                
                JLabel flight_tot_fare = new JLabel("Total Price: Rs." + (selFlight.fare * no_seats)+"/-");
                flight_tot_fare.setBounds(50,120,250,30);
                panel.add(flight_tot_fare);
                

                for (int i = 1; i <= no_seats; i++) {
                    JLabel name = new JLabel("Passenger" +i+": "+name_fields.get(i - 1).getText() );
                    name.setBounds(50,(i*30)+150,150,30);
                    panel.add(name);
                }

                JButton buy = new JButton("Buy Tickets");
                buy.setBounds(150,500,150,30);
                panel.add(buy);

                frame.revalidate();
                frame.repaint();
            }
        });

    /*
        System.out.println("----------------------------------------------------");
        System.out.println("Details of available flight");
        System.out.println("----------------------------------------------------");
        flights.forEach(f->{ f.showFlight(); });

        java.util.List <Booking> tickets = new ArrayList<Booking>();

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

                if (no_pass <= 0){
                    System.out.println("Booking Failed! Number of passengers can't be zero or negative");
                } else{
                    b.bookTicket(no_pass, flights);
                    tickets.add(b);
                }
                
            } else if (choice==2){ // show all tickets
                
                if(tickets.size() == 0){
                    System.out.println("No tickets booked yet...!");
                }
                else {
                    tickets.forEach(t->{t.showTicket();}); 
                }
            } else if (choice == 3) { // cancellation
                System.out.print("Enter booking id of ticket to be cancelled: ");
                int bId = sc.nextInt();
                boolean tkt_not_found = true;
                Iterator <Booking> iter_tkt = tickets.iterator();
                while (iter_tkt.hasNext()) {
                    Booking t = iter_tkt.next();
                    if (t.booking_id == bId) {
                        tkt_not_found = false;
                        System.out.println(
                                "Booking Id: " + t.booking_id + " Do you want to delete " + t.no_seats
                                        + " ticket(s) [Y/N]");
                        char cnf = sc.next().charAt(0);
                        if (cnf == 'y' || cnf == 'Y') {
                            t.passengers.get(0).flight.seatsAvl += t.no_seats;
                            iter_tkt.remove();
                            System.out.println("Cancelled Successfully!");
                            break;
                        } else {
                            System.out.println("Cancellation Declined!");
                            break;
                        }
                    }
                }
                if (tkt_not_found) {
                    System.out.println("Ticket to be cancelled was not found.");
                }
            }
        } while (choice != 0);

        sc.close();



    */
    }
}