package pl.air.cars.model;

import java.util.Date;

public class Car {
	private String brand;
	private float price;
	private int year;
	private Date firstRegDate;
	private long Mileage;
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(long price) {
		this.price = price;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public Date getFirstRegDate() {
		return firstRegDate;
	}
	public void setFirstRegDate(Date firstRegDate) {
		this.firstRegDate = firstRegDate;
	}
	public long getMileage() {
		return Mileage;
	}
	public void setMileage(long mileage) {
		Mileage = mileage;
	}
	public Car(String brand, float price, int year, Date firstRegDate, long mileage) {
		super();
		this.brand = brand;
		this.price = price;
		this.year = year;
		this.firstRegDate = firstRegDate;
		Mileage = mileage;
	}
	
}
