import java.util.*;
import javax.swing.* ;
import java.awt.event.*;

public class Main {
    public static void main(String[] args) {

        java.util.List<Booking> tickets = new ArrayList<Booking>();
        java.util.List <Flight> flights = new ArrayList<Flight>();
        flights.add(new Flight(1,"Air India",300, 300, 7500, "BOM", "DEL", "07-12-2022 11:45 IST", "07-12-2022 14:30 IST"));
        flights.add(new Flight(2,"Indigo",   250, 250, 7450, "DEL", "BOM", "07-12-2022 16:30 IST", "07-12-2022 18:40 IST"));
        flights.add(new Flight(3,"Spice jet",250, 250, 5100, "BOM", "BLR", "08-12-2022 23:00 IST", "09-12-2022 00:45 IST"));
        flights.add(new Flight(4,"Indigo",   300, 300, 4950, "BLR", "BOM", "09-12-2022 12:00 IST", "09-12-2022 14:45 IST"));

        JFrame frame = new JFrame();
        JLabel no_pass_label, flight_no_label;
        JTextField no_pass;
        JButton home, book, proceed, show, buy, cancel;
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

        
        frame.add(panel);

        frame.setSize(800,800);
        frame.setVisible(true);
        frame.setLayout(null);        
        
        home = new JButton("Home");
        book = new JButton("Book Tickets");
        show = new JButton("Show Tickets");
        buy = new JButton("Buy Tickets");
        cancel = new JButton("Cancel Tickets");

        home.setBounds(500, 50, 100, 30);

        book.setBounds(100, 100, 200, 30);
        panel.add(book);

        show.setBounds(100, 150, 200, 30);
        panel.add(show);

        cancel.setBounds(100, 200, 200, 30);
        panel.add(cancel);

        java.util.List<JTextField> name_fields = new java.util.ArrayList<JTextField>();
        java.util.List<JLabel> name_labels = new java.util.ArrayList<JLabel>();
        
        home.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                panel.removeAll();

                book.setBounds(100, 100, 200, 30);
                panel.add(book);

                show.setBounds(100, 150, 200, 30);
                panel.add(show);

                cancel.setBounds(100, 200, 200, 30);
                panel.add(cancel);
                
                frame.revalidate();
                frame.repaint();
            }
        });
        
        book.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                panel.removeAll();
                
                panel.add(no_pass_label);
                panel.add(no_pass);
                panel.add(flight_no_label);
                panel.add(cb);
                panel.add(proceed);
                panel.add(home);
                home.setBounds(500, 50, 100, 30);

                frame.revalidate();
                frame.repaint();
            }
        });

        proceed.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try{
                    int no_seats = Integer.parseInt(no_pass.getText());
                    if (no_seats <= 0){
                        throw new NumberFormatException("Invalid input");
                    }
                    
                    int flight_num = cb.getSelectedIndex() + 1;
                    Flight selFlight = flights.get(flight_num - 1);

                    JLabel flight_number = new JLabel("Flight no.: " + flight_num);
                    flight_number.setBounds(450, 50, 200, 30);
                    panel.add(flight_number);
                    
                    JLabel flight_name = new JLabel(selFlight.fName);
                    flight_name.setBounds(550, 50, 200, 30);
                    panel.add(flight_name);

                    JLabel flight_src_dest = new JLabel(
                            "Source: " + selFlight.src + " - Destination: " + selFlight.dest);
                    flight_src_dest.setBounds(450, 80, 250, 30);
                    panel.add(flight_src_dest);

                    JLabel flight_tot_fare = new JLabel("Total Price: Rs." + (selFlight.fare * no_seats) + "/-");
                    flight_tot_fare.setBounds(450, 120, 250, 30);
                    panel.add(flight_tot_fare);

                    
                    buy.setBounds(200, (no_seats*50)+150, 100, 30);
                    // details.setBounds(200, (no_seats*50)+150, 100, 30);
                    // panel.add(details);
                    panel.add(buy);
                    frame.add(panel);
                    home.setBounds(50, (no_seats * 50) + 150, 100, 30);
                    panel.add(home);
                    
                    panel.remove(no_pass_label);
                    panel.remove(no_pass);
                    panel.remove(flight_no_label);
                    panel.remove(proceed);
                    panel.remove(cb);

                    for(int i=1;i<= no_seats;i++){
                        name_fields.add(new JTextField());
                        name_labels.add(new JLabel("Name of passenger"+i));
                    }
                    for(int i=0;i< no_seats;i++){
                        name_labels.get(i).setBounds(50,(i*50)+50,150,30);
                        panel.add(name_labels.get(i));
                    };
                    for(int i=0;i< no_seats;i++){
                        name_fields.get(i).setBounds(200, (i*50)+50,150,30);
                        panel.add(name_fields.get(i));
                    };
                    
                    frame.revalidate();
                    frame.repaint();

                } catch (NumberFormatException nfe) {
                    JOptionPane.showMessageDialog(frame, "Passenger names must be number greater than 0");
                }
                
            }
        });
    
        buy.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                panel.removeAll();
                
                int no_seats = name_fields.size();
                Booking b = new Booking();
                List<String> pass_names = new ArrayList<String>();
                String pass_name;
                for (int i = 0; i < no_seats; i++) {
                    pass_name = name_fields.get(i).getText();
                    // JLabel name = new JLabel("Passenger" + (i + 1) + ": " + pass_name);
                    // name.setBounds(50, (i * 30) + 150, 150, 30);
                    // panel.add(name);
                    pass_names.add(pass_name); // add name to passenger list of string type
                }

                try {
                    b.bookTicket(flights, no_seats, pass_names);
                    tickets.add(b);
                    panel.remove(buy);
                    JOptionPane.showMessageDialog(frame,"Booked Successfully!");
                } catch (Exception booking_err) {
                    JOptionPane.showMessageDialog(frame, booking_err);
                } 

                home.setBounds(100, 100, 100, 30);
                panel.add(home);

                show.setBounds(250,100,150,30);
                panel.add(show);
                
                frame.revalidate();
                frame.repaint();
            }
        });

        show.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                panel.removeAll();
                JLabel title = new JLabel("Booked Tickets");
                title.setBounds(50,50,150,30);
                panel.add(title);

                home.setBounds(500, 50, 100, 30);
                panel.add(home);

                tickets.forEach(t->{
                    JLabel b_id = new JLabel("Booking Id:" + tickets.get(0).booking_id);
                    b_id.setBounds(50,100,150,30);
                    panel.add(b_id);
                    
                    JLabel noTickets = new JLabel("Number of tickets:" + tickets.get(0).no_seats);
                    noTickets.setBounds(50, 150, 200, 30);
                    panel.add(noTickets);

                    t.passengers.forEach(p -> {
                        JLabel pass_data = new JLabel(p.passId + "     " + p.name + "     " + p.seatNo);
                        pass_data.setBounds(50, ((t.passengers.indexOf(p)+1) * 50) +120, 600, 50);
                        panel.add(pass_data);

                    });
                    
                    Flight selFlight = t.passengers.get(0).flight;

                    JLabel flight_number = new JLabel("Flight no.: " + selFlight.fNo);
                    flight_number.setBounds(450, 100, 200, 30);
                    panel.add(flight_number);

                    JLabel flight_name = new JLabel(selFlight.fName);
                    flight_name.setBounds(550, 100, 200, 30);
                    panel.add(flight_name);

                    JLabel flight_src_dest = new JLabel(
                            "Source: " + selFlight.src + " - Destination: " + selFlight.dest);
                    flight_src_dest.setBounds(450, 130, 250, 30);
                    panel.add(flight_src_dest);

                    JLabel flight_tot_fare = new JLabel("Total Price: Rs." + (selFlight.fare * t.passengers
                            .size()) + "/-");
                    flight_tot_fare.setBounds(450, 150, 250, 30);
                    panel.add(flight_tot_fare);
                });

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