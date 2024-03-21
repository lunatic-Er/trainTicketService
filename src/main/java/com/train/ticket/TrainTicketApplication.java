package com.train.ticket;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import com.train.ticket.model.TicketRequest;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
@RestController
public class TrainTicketApplication {
	
	private Map<String, String> seatAllocations = new HashMap<>();
    private Map<String, String> userSeats = new HashMap<>();

    public static void main(String[] args) {
        SpringApplication.run(TrainTicketApplication.class, args);
    }

    @PostMapping("/purchase_ticket")
    public String purchaseTicket(@RequestBody TicketRequest ticketRequest) {
        String seat = allocateSeat(ticketRequest.getUser().getEmail());
        String receipt = "From: " + ticketRequest.getFrom() +
                        "\nTo: " + ticketRequest.getTo() +
                        "\nUser: " + ticketRequest.getUser().getFirstName() + " " + ticketRequest.getUser().getLastName() +
                        "\nEmail: " + ticketRequest.getUser().getEmail() +
                        "\nPrice Paid: $5" +
                        "\nSeat: " + seat;
        userSeats.put(ticketRequest.getUser().getEmail(), seat);
        return receipt;
    }

    @GetMapping("/receipt/{user_email}")
    public String viewReceiptDetails(@PathVariable("user_email") String userEmail) {
        String seat = userSeats.get(userEmail);
        if (seat != null) {
            return "User: " + userEmail +
                    "\nSeat: " + seat;
        } else {
            return "User not found or hasn't purchased a ticket.";
        }
    }

    @GetMapping("/seat_allocation/{section}")
    public Map<String, String> viewSeatAllocation(@PathVariable("section") String section) {
        Map<String, String> sectionSeats = new HashMap<>();
        for (Map.Entry<String, String> entry : seatAllocations.entrySet()) {
            if (entry.getValue().equals(section)) {
                sectionSeats.put(entry.getKey(), userSeats.get(entry.getKey()));
            }
        }
        return sectionSeats;
    }

    @DeleteMapping("/remove_user")
    public String removeUserFromTrain(@RequestBody Map<String, String> request) {
        String userEmail = request.get("user_email");
        if (userSeats.containsKey(userEmail)) {
            String seat = userSeats.get(userEmail);
            userSeats.remove(userEmail);
            seatAllocations.remove(userEmail);
            return "User with email " + userEmail + " and seat " + seat + " removed from the train.";
        } else {
            return "User not found or hasn't purchased a ticket.";
        }
    }

    @PutMapping("/modify_seat")
    public String modifyUserSeat(@RequestBody Map<String, String> request) {
        String userEmail = request.get("user_email");
        String newSeat = request.get("new_seat");
        if (userSeats.containsKey(userEmail)) {
            userSeats.put(userEmail, newSeat);
            return "Seat for user with email " + userEmail + " modified to " + newSeat;
        } else {
            return "User not found or hasn't purchased a ticket.";
        }
    }

    private String allocateSeat(String userEmail) {
        // Logic to allocate seat, for example: alternating between sections A and B
        String section = seatAllocations.size() % 2 == 0 ? "A" : "B";
        String seat = section + (seatAllocations.size() + 1);
        seatAllocations.put(userEmail, section);
        return seat;
    }
}
