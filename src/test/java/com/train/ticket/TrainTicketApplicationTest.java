package com.train.ticket;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import com.train.ticket.model.TicketRequest;
import com.train.ticket.model.User;

@ExtendWith(MockitoExtension.class)
public class TrainTicketApplicationTest {
	@InjectMocks
	TrainTicketApplication ticketApplication;
	
	@BeforeEach
    void setUp() {
        Map<String, String> seatAllocations = new HashMap<>();
        Map<String, String> userSeats = new HashMap<>();
	}
	
	@Test
	void purchaseTicketTest() {
		TicketRequest ticketRequest = new TicketRequest();
		User user = new User();
		user.setFirstName("Ram");
		user.setLastName("Kumar");
		user.setEmail("ram@test.com");
		ticketRequest.setUser(user);
		ticketRequest.setFrom("London");
		ticketRequest.setTo("France");
		Assertions.assertNotNull(ticketApplication.purchaseTicket(ticketRequest));
	}
	
	@Test
	void viewReceiptDetailsTest() {
		Map<String, String> userSeats = new HashMap<String, String>();
		userSeats.put("ram@test.com", "A1");
		Assertions.assertNotNull(ticketApplication.viewReceiptDetails("ram@test.com"));
	}
	
	@Test
	void viewSeatAllocationTestForSectionA() {
		Assertions.assertNotNull(ticketApplication.viewSeatAllocation("A"));
	}
	
	@Test
	void viewSeatAllocationTestForSectionB() {
		Assertions.assertNotNull(ticketApplication.viewSeatAllocation("B"));
	}
	
	@Test
	void removeUserFromTrainTest() {
		Map<String, String> request = new HashMap<String, String>();
		Assertions.assertNotNull(ticketApplication.removeUserFromTrain(request));
	}
	
	@Test
	void modifyUserSeatTest() {
		Map<String, String> request = new HashMap<String, String>();
		Assertions.assertNotNull(ticketApplication.modifyUserSeat(request));
	}
	
}
