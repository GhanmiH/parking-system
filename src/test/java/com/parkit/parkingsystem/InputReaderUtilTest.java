package com.parkit.parkingsystem;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;
import java.util.Scanner;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.parkit.parkingsystem.util.InputReaderUtil;

class InputReaderUtilTest {

	private InputReaderUtil inputReaderUtil;

	@BeforeEach
	private void setUpPerTest() {
		inputReaderUtil = new InputReaderUtil();
	}

	@DisplayName("readerutil return an int upper than zero")
	@Test
	public void readerUtilReturnAnIntUpperThanZero() throws Exception {
		Scanner scan = new Scanner(new ByteArrayInputStream("1".getBytes()));
		inputReaderUtil.setScan(scan);
		assertEquals(1, inputReaderUtil.readSelection());
	}

	@DisplayName("readerutil return a string ")
	@Test
	public void readerUtilReturnAString() throws Exception {
		Scanner scan = new Scanner(new ByteArrayInputStream("test".getBytes()));
		inputReaderUtil.setScan(scan);
		assertEquals(-1, inputReaderUtil.readSelection());
	}

	@DisplayName("readVehiculeRegistrationNumber return a string")
	@Test
	public void readVehiculeRegistrationNumbereturnAString() throws Exception {
		Scanner scan = new Scanner(new ByteArrayInputStream("test".getBytes()));
		inputReaderUtil.setScan(scan);
		assertEquals("test", inputReaderUtil.readVehicleRegistrationNumber());
	}

	@DisplayName("readVehiculeRegistrationNumber return a special charactar")
	@Test
	public void rreadVehiculeRegistrationNumbereturnASpecialCharact() throws Exception {
		Scanner scan = new Scanner(new ByteArrayInputStream("\n".getBytes()));
		inputReaderUtil.setScan(scan);
		assertThrows(IllegalArgumentException.class, () -> inputReaderUtil.readVehicleRegistrationNumber());

	}

}
