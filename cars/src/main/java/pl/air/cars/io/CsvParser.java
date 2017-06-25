package pl.air.cars.io;

import java.io.IOException;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import pl.air.cars.database.CarsDatabase;
import pl.air.cars.model.Car;

public class CsvParser {
	private CarsDatabase db;
	private ReadingService rs;
	
	public CsvParser(ReadingService rs,CarsDatabase db){
		this.db = db;
		this.rs = rs;
	}
	public void parse() throws IOException, ParseException{
		rs.readFormattedText();
		ArrayList<String> lines = (ArrayList<String>) rs.getLines();
		for(int i = 1; i<lines.size(); ++i ){
			String line = lines.get(i);
			if(line == null) continue;
			String[] entries = line.split(";");
			if(entries == null) continue;
			String brand = entries[0];
			
			NumberFormat nf = NumberFormat.getInstance(Locale.GERMAN);
			Number n =  nf.parse(entries[1]);
			float  price = n.floatValue();
			
			int year = Integer.parseInt(entries[2]);
			
			DateFormat df = new SimpleDateFormat("dd.mm.yyyy");
			Date firstRegDate = df.parse(entries[3]);
			
			long mileage = Long.parseLong(entries[4]);
			Car c = new Car(brand, price, year, firstRegDate, mileage);
			db.add(c);
		}
	}
}
