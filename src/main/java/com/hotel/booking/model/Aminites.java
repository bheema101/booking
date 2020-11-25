package com.hotel.booking.model;

import java.io.Serializable;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Aminites implements Serializable {
	private boolean ac;
	private boolean carParking;
	private boolean swimmingPool;
	private boolean room;
	
	

	public Aminites() {
		
	}

	public boolean isAc() {
		return ac;
	}

	public void setAc(boolean ac) {
		this.ac = ac;
	}

	public boolean isCarParking() {
		return carParking;
	}

	public void setCarParking(boolean carParking) {
		this.carParking = carParking;
	}

	public boolean isSwimmingPool() {
		return swimmingPool;
	}

	public void setSwimmingPool(boolean swimmingPool) {
		this.swimmingPool = swimmingPool;
	}

	public boolean isRoom() {
		return room;
	}

	public void setRoom(boolean room) {
		this.room = room;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (ac ? 1231 : 1237);
		result = prime * result + (carParking ? 1231 : 1237);
		result = prime * result + (room ? 1231 : 1237);
		result = prime * result + (swimmingPool ? 1231 : 1237);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Aminites other = (Aminites) obj;
		if (ac != other.ac)
			return false;
		if (carParking != other.carParking)
			return false;
		if (room != other.room)
			return false;
		if (swimmingPool != other.swimmingPool)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Aminites [ac=" + ac + ", carParking=" + carParking + ", swimmingPool=" + swimmingPool + ", room=" + room
				+ "]";
	}
public static void main(String[] args) {
	try {
		Aminites aminites = new Aminites();
		aminites.setAc(true);
		System.out.println(new ObjectMapper().writeValueAsString(aminites));
	} catch (JsonProcessingException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
}
