public class Flight{
    public int fNo, maxSeats, seatsAvl, fare;
    public String fName, src, dest, deptDateTime, arvDateTime;

    public Flight(int fNo, String fName, int maxSeats, int seatsAvl, int fare, String src, String dest, String deptDateTime, String arvDateTime){
        this.fNo = fNo;
        this.fName = fName;
        this.maxSeats = maxSeats;
        this.seatsAvl = seatsAvl;
        this.fare = fare;
        this.src = src;
        this.dest = dest;
        this.deptDateTime = deptDateTime;
        this.arvDateTime = arvDateTime;
    }

    void showFlight(){
        System.out.print("Flight No: "+this.fNo);
        System.out.println("\tFlight Name: "+this.fName);
        System.out.println("Source: "+this.src+" ("+this.deptDateTime+")");
        System.out.println("Destination: "+this.dest+" ("+this.arvDateTime+")");
        System.out.print("Available seats: "+this.seatsAvl);
        System.out.println("\tPrice: "+this.fare);
        System.out.println("----------------------------------------------------");
    }
}