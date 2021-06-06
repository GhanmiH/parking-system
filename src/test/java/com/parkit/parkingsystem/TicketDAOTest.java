package com.parkit.parkingsystem;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;

import org.junit.jupiter.api.Test;

import com.parkit.parkingsystem.constants.Fare;
import com.parkit.parkingsystem.constants.ParkingType;
import com.parkit.parkingsystem.dao.TicketDAO;
import com.parkit.parkingsystem.model.ParkingSpot;
import com.parkit.parkingsystem.model.Ticket;

class TicketDAOTest {
	private String vehicleRegNumber = "ABCDEF";
	private Ticket ticket;

	@Test
	public void saveTicketTest() {

		Ticket ticket = new Ticket();
		// ticket properties.

		Date inTime = new Date();
		inTime.setTime(System.currentTimeMillis() - (60 * 60 * 1000));
		ticket.setInTime(inTime);
		ticket.setParkingSpot(new ParkingSpot(2, ParkingType.CAR, false));
		ticket.setVehicleRegNumber(vehicleRegNumber);
		ticket.setPrice(0);

		TicketDAO ticketDAO = new TicketDAO();
		boolean result = ticketDAO.saveTicket(ticket);
		// That doesn't seem to be a clean way of making it.
		assertEquals(false, result);
	}

	@Test
	public void getTicketTest() {
		saveTicketTest();
		TicketDAO ticketDAO = new TicketDAO();
		Ticket ticketReturned = ticketDAO.getTicket(vehicleRegNumber);
		assertEquals(ticketReturned.getClass(), Ticket.class);
	}

	@Test
	public void updateTicketTest() {
		saveTicketTest();
		TicketDAO ticketDAO = new TicketDAO();
		Ticket ticketReturned = ticketDAO.getTicket(vehicleRegNumber);
		ticketReturned.setOutTime(new Date(System.currentTimeMillis()));
		assertEquals(true, ticketDAO.updateTicket(ticketReturned));
	}

	@Test
	public void updateTicketWithOutTimeTest() {

		ticket = new Ticket();
		ticket.getId();
		ticket.setInTime(new Date(System.currentTimeMillis() - (60 * 60 * 1000)));
		ticket.setParkingSpot(new ParkingSpot(1, ParkingType.CAR, false));
		ticket.setVehicleRegNumber(vehicleRegNumber);
		ticket.setPrice(Fare.CAR_RATE_PER_HOUR);
		ticket.setInTime(new Date(System.currentTimeMillis() - (60 * 60 * 1000)));
		TicketDAO ticketDAO = new TicketDAO();
		ticketDAO.saveTicket(ticket);
		ticket.setOutTime(new Date());

		boolean updated = ticketDAO.updateTicket(ticket);

		assertTrue(updated);
	}

	@Test
	public void verifyEligibilityForADiscount() {
		Ticket ticket = new Ticket();
		// ticket properties.

		Date inTime = new Date();
		inTime.setTime(System.currentTimeMillis() - (60 * 60 * 1000));
		ticket.setInTime(inTime);
		ticket.setParkingSpot(new ParkingSpot(2, ParkingType.CAR, false));
		ticket.setVehicleRegNumber(vehicleRegNumber);
		ticket.setPrice(0);

		TicketDAO ticketDAO = new TicketDAO();
		assertEquals(Ticket.class, ticketDAO.getTicket(vehicleRegNumber).getClass());
	}

}
