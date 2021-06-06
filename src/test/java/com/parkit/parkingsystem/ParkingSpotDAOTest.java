package com.parkit.parkingsystem;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.parkit.parkingsystem.constants.ParkingType;
import com.parkit.parkingsystem.dao.ParkingSpotDAO;
import com.parkit.parkingsystem.model.ParkingSpot;

class ParkingSpotDAOTest {

	private ParkingSpot parkingSpot;

	@BeforeEach
	private void setUpPerTest() {
		parkingSpot = new ParkingSpot(1, ParkingType.CAR, false);
	}

	@DisplayName("parking spot car  availability")
	@Test
	public void getNextAvailableSlotCarTest() {
		ParkingSpotDAO parkingSpotDAO = new ParkingSpotDAO();

		assertEquals(1, parkingSpotDAO.getNextAvailableSlot(ParkingType.CAR));
	}

	@DisplayName("parking spot bike  availability")
	@Test
	public void getNextAvailableSlotBikeTest() {
		ParkingSpotDAO parkingSpotDAO = new ParkingSpotDAO();

		assertEquals(8, parkingSpotDAO.getNextAvailableSlot(ParkingType.BIKE));
	}

	@DisplayName("updating parking spot")
	@Test
	public void updateParkingTest() {
		ParkingSpotDAO parkingSpotDAO = new ParkingSpotDAO();
		ParkingSpot parkingSpot = new ParkingSpot(1, ParkingType.CAR, true);
		assertEquals(true, parkingSpotDAO.updateParking(parkingSpot));

	}

	@DisplayName("get and set for int id ")
	@Test
	public void setAndGetIntIdTest() {

		parkingSpot.setId(3);
		assertEquals(3, parkingSpot.getId());
	}

	@DisplayName("get and set parking spot for car")
	@Test

	public void setAndGetParkingTypeCarTest() {

		parkingSpot.setParkingType(ParkingType.CAR);

		assertEquals(ParkingType.CAR, parkingSpot.getParkingType());
	}

	@DisplayName("get and set parking spot for bike")
	@Test

	public void setAndGetParkingTypeBikeTest() {

		parkingSpot.setParkingType(ParkingType.BIKE);
		assertEquals(ParkingType.BIKE, parkingSpot.getParkingType());
	}

	@DisplayName("get and set availability")
	@Test

	public void setAndGetAvailability() {

		parkingSpot.setAvailable(true);
		assertEquals(true, parkingSpot.isAvailable());
	}

	@DisplayName("return false when parking spot is null")
	@Test
	public void falseWhenPrkinSpotIsNull() {

		assertEquals(false, parkingSpot.equals(null));
	}
}
