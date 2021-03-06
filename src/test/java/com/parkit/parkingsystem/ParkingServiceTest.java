package com.parkit.parkingsystem;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Date;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.parkit.parkingsystem.constants.ParkingType;
import com.parkit.parkingsystem.dao.ParkingSpotDAO;
import com.parkit.parkingsystem.dao.TicketDAO;
import com.parkit.parkingsystem.dao.UserDAO;
import com.parkit.parkingsystem.model.ParkingSpot;
import com.parkit.parkingsystem.model.Ticket;
import com.parkit.parkingsystem.service.ParkingService;
import com.parkit.parkingsystem.util.InputReaderUtil;

@ExtendWith(MockitoExtension.class)
public class ParkingServiceTest {

	private static ParkingService parkingService;
	@Mock
	private static InputReaderUtil inputReaderUtil;
	@Mock
	private static ParkingSpotDAO parkingSpotDAO;
	@Mock
	private static TicketDAO ticketDAO;
	@Mock
	private static UserDAO userDAO;

	@DisplayName("process exiting vehicle")
	@Test
	public void processExitingVehicleTest() {
		try {
			when(inputReaderUtil.readVehicleRegistrationNumber()).thenReturn("ABCDEF");
			ParkingSpot parkingSpot = new ParkingSpot(1, ParkingType.CAR, false);
			Ticket ticket = new Ticket();
			ticket.setInTime(new Date(System.currentTimeMillis() - (60 * 60 * 1000)));
			ticket.setParkingSpot(parkingSpot);
			ticket.setVehicleRegNumber("ABCDEF");
			when(ticketDAO.getTicket(anyString())).thenReturn(ticket);
			when(ticketDAO.updateTicket(any(Ticket.class))).thenReturn(true);
			when(parkingSpotDAO.updateParking(any(ParkingSpot.class))).thenReturn(true);

			parkingService = new ParkingService(inputReaderUtil, parkingSpotDAO, ticketDAO);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Failed to set up test mock objects");
		}

		parkingService.processExitingVehicle();
		verify(parkingSpotDAO, Mockito.times(1)).updateParking(any(ParkingSpot.class));
		verify(ticketDAO, Mockito.times(1)).getTicket(any(String.class));
		verify(ticketDAO, Mockito.times(1)).updateTicket(any(Ticket.class));
	}

	@DisplayName("process incoming vehicle ")
	@Test
	public void processIncomingVehicleTest() {
		try {
			parkingService = new ParkingService(inputReaderUtil, parkingSpotDAO, ticketDAO);

			when(inputReaderUtil.readSelection()).thenReturn(1);
			ParkingSpot parkingSpot = new ParkingSpot(1, ParkingType.CAR, false);
			when(parkingSpotDAO.getNextAvailableSlot(ParkingType.CAR)).thenReturn(1);
			when(inputReaderUtil.readVehicleRegistrationNumber()).thenReturn("ABCDEF");

			when(parkingSpotDAO.updateParking(parkingSpot)).thenReturn(true);

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Failed to set up test mock objects");
		}

		parkingService.processIncomingVehicle();
		verify(parkingSpotDAO, Mockito.times(1)).updateParking(any(ParkingSpot.class));

		verify(ticketDAO, Mockito.times(1)).saveTicket(any(Ticket.class));

	}

	@DisplayName("process incoming vehicle when the parking spot is null")
	@Test
	void processIncomingWhenNullParkingSpotTest() {

		parkingService = new ParkingService(inputReaderUtil, parkingSpotDAO, ticketDAO);
		parkingService = Mockito.spy(parkingService);

		Mockito.doReturn(null).when(parkingService).getNextParkingNumberIfAvailable();

		parkingService.processIncomingVehicle();

		verify(parkingSpotDAO, never()).updateParking(any(ParkingSpot.class));
		verify(ticketDAO, never()).saveTicket(any(Ticket.class));

	}

	@DisplayName("process incoming vehicle when parking spot is negative")
	@Test
	void processIncomingWhenNegativeParkingSpotTest() {

		parkingService = new ParkingService(inputReaderUtil, parkingSpotDAO, ticketDAO);
		parkingService = Mockito.spy(parkingService);

		Mockito.doReturn(new ParkingSpot(-10, ParkingType.CAR, true)).when(parkingService)
				.getNextParkingNumberIfAvailable();

		parkingService.processIncomingVehicle();

		verify(parkingSpotDAO, never()).updateParking(any(ParkingSpot.class));
		verify(ticketDAO, never()).saveTicket(any(Ticket.class));

	}

}