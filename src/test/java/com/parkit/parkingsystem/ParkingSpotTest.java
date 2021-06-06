package com.parkit.parkingsystem;

import com.parkit.parkingsystem.constants.ParkingType;
import com.parkit.parkingsystem.model.ParkingSpot;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class ParkingSpotTest {

	private ParkingSpot parkingSpot;

	@BeforeEach
	private void setUpPerTest() {
		parkingSpot = new ParkingSpot(1, ParkingType.CAR, false);
	}

	@Test
	public void setAndGetIDTest() {

		parkingSpot.setId(3);

		assertEquals(3, parkingSpot.getId());
	}

	@Test
	public void setAndGetParkingTypeCarTest() {

		parkingSpot.setParkingType(ParkingType.CAR);

		assertEquals(ParkingType.CAR, parkingSpot.getParkingType());
	}

	@Test
	public void setAndGetParkingTypeBikeTest() {

		parkingSpot.setParkingType(ParkingType.BIKE);

		assertEquals(ParkingType.BIKE, parkingSpot.getParkingType());
	}

	@Test
	public void setAndGetIsAvailableTest() {

		parkingSpot.setAvailable(true);

		assertEquals(true, parkingSpot.isAvailable());
	}

	@Test
	public void hashCodeTest() {

		parkingSpot.setId(4);

		assertEquals(4, parkingSpot.hashCode());
	}
}