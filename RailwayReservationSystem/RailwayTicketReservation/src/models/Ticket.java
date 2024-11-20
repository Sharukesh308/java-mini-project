package models;

import java.util.Date;

public class Ticket {
    private int ticketId;
    private int trainNo;
    private String passengerName;
    private String email;
    private int numberOfSeats;
    private Date bookingDate;

    // Constructor
    public Ticket(int ticketId, int trainNo, String passengerName, String email, int numberOfSeats, Date bookingDate) {
        this.ticketId = ticketId;
        this.trainNo = trainNo;
        this.passengerName = passengerName;
        this.email = email;
        this.numberOfSeats = numberOfSeats;
        this.bookingDate = bookingDate;
    }

    // Getters and Setters
    public int getTicketId() {
        return ticketId;
    }

    public void setTicketId(int ticketId) {
        this.ticketId = ticketId;
    }

    public int getTrainNo() {
        return trainNo;
    }

    public void setTrainNo(int trainNo) {
        this.trainNo = trainNo;
    }

    public String getPassengerName() {
        return passengerName;
    }

    public void setPassengerName(String passengerName) {
        this.passengerName = passengerName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(int numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }

    public Date getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(Date bookingDate) {
        this.bookingDate = bookingDate;
    }

    // Override toString for better representation
    @Override
    public String toString() {
        return "Ticket{" +
                "ticketId=" + ticketId +
                ", trainNo=" + trainNo +
                ", passengerName='" + passengerName + '\'' +
                ", email='" + email + '\'' +
                ", numberOfSeats=" + numberOfSeats +
                ", bookingDate=" + bookingDate +
                '}';
    }
}

