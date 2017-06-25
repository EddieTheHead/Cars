package pl.air.cars.database;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import pl.air.cars.model.Car;

public class CarsDatabase {
	private List<Car> carsRepo = new ArrayList<Car>();
	private Set<String> brandsRepo = new HashSet<String>();
	
	public Collection<Car> getByPrice(float min, float max){
		List<Car> result = new ArrayList<Car>();
		for(Car c: carsRepo){
			if(c.getPrice() <= max && c.getPrice() >= min) result.add(c);
		}
		return result;
	}
	
	public Collection<Car> getByBrand(String brand){
		List<Car> result = new ArrayList<Car>();
		for(Car c: carsRepo){
			if(c.getBrand().equals(brand)) result.add(c);
		}
		return result;
	}
	
	public Collection<Car> getByYearOfProduction(int min, int max){
		List<Car> result = new ArrayList<Car>();
		for(Car c: carsRepo){
			if(c.getYear() >= min && c.getYear() <= max) result.add(c);
		}
		return result;
	}
	
	public String[] getAllBrands(){
		return (String[]) brandsRepo.toArray(new String[brandsRepo.size()]);
	}
	
	public void add(Car car){
		carsRepo.add(car);
		brandsRepo.add(car.getBrand());
	}
	
}
