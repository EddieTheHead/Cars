package pl.air.cars;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.ui.RefineryUtilities;
import org.joda.time.Days;
import org.joda.time.LocalDate;

import pl.air.cars.database.CarsDatabase;
import pl.air.cars.io.CsvParser;
import pl.air.cars.io.ReadingService;
import pl.air.cars.model.Car;
import pl.air.cars.view.PieChartWindow;

public class Main {
	CarsDatabase db;
	static String file = "Project_Data.csv";
	ReadingService rs;
	CsvParser parser;

	public static void main(String[] args) {
		if (args.length == 1)
			file = args[0];
		Main m = new Main();
		m.doYourJob();

	}

	public void doYourJob() {

		try {
			rs = new ReadingService(file);
			db = new CarsDatabase();
			parser = new CsvParser(rs, db);
			parser.parse();

			printNumberOfCarsByBrand();
			printMeanAgeByBrand();
			printMedianMileageByYearOfProduction(2000, 2005);
			printMedianMileageByYearOfProduction(2006, 2010);
			printMedianMileageByYearOfProduction(2011, 2015);

			printDailyMeanMileageOfCarsMadeInYear(2013);
			showBrandShare();

		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}

	}

	private void showBrandShare() {
		String[] brands = db.getAllBrands();
		DefaultPieDataset dataset = new DefaultPieDataset();
		for (String brand : brands) {
			ArrayList<Car> carsByBrand = (ArrayList<Car>) db.getByBrand(brand);
			dataset.setValue(brand, new Double(carsByBrand.size()));
		}
		PieChartWindow window = new PieChartWindow("Udzia³ marek", dataset,
				"Udzia³ ka¿dej z marek w ogólnej liczbie samochodów");
		window.setSize(560, 367);
		RefineryUtilities.centerFrameOnScreen(window);
		window.setVisible(true);
	}

	private void printNumberOfCarsByBrand() {
		String[] brands = db.getAllBrands();
		System.out.println("Liczba samochodów wed³ug marki:");
		for (String brand : brands) {
			System.out.print(brand + " --> ");
			ArrayList<Car> carsByBrand = (ArrayList<Car>) db.getByBrand(brand);
			System.out.println(carsByBrand.size());
		}
	}

	private void printMeanAgeByBrand() {
		String[] brands = db.getAllBrands();
		System.out.println("Œredni wiek samochodów wed³ug marki (w latach):");
		for (String brand : brands) {
			System.out.print(brand + " --> ");
			ArrayList<Car> carsByBrand = (ArrayList<Car>) db.getByBrand(brand);

			DescriptiveStatistics stats = new DescriptiveStatistics();
			int currentYear = Calendar.getInstance().get(Calendar.YEAR);

			for (Car c : carsByBrand) {
				stats.addValue(currentYear - c.getYear());
			}
			System.out.println(stats.getMean());
		}
	}

	private void printMedianMileageByYearOfProduction(int startingYear, int endYear) {
		ArrayList<Car> cars = (ArrayList<Car>) db.getByYearOfProduction(startingYear, endYear);
		System.out
				.println("Mediana przebiegu samochodów wyprodukowanych w latach " + startingYear + "-" + endYear + ":");
		DescriptiveStatistics stats = new DescriptiveStatistics();
		for (Car c : cars) {
			stats.addValue(c.getMileage());
		}
		System.out.println(stats.getPercentile(50));
	}

	private void printDailyMeanMileageOfCarsMadeInYear(int year) {
		System.out.println("Œredni dzienny przebieg samochodów wyprodukowanych w roku " + year + " wed³ug marki:");
		ArrayList<Car> carsByYear = (ArrayList<Car>) db.getByYearOfProduction(year, year);

		String[] brands = db.getAllBrands();
		for (String b : brands) {

			List<Car> carsInCurrentBrand = new ArrayList<Car>();
			for (Car c : carsByYear) {
				if (c.getBrand().equals(b))
					carsInCurrentBrand.add(c);
			}
			if (carsInCurrentBrand.size() == 0)
				continue;
			LocalDate now = new LocalDate();
			DescriptiveStatistics stats = new DescriptiveStatistics();
			for (Car c : carsInCurrentBrand) {
				Days age = Days.daysBetween(new LocalDate(c.getFirstRegDate()), now);
				float dailyMileage = (float) c.getMileage() / (float) age.getDays();
				stats.addValue(dailyMileage);
			}
			double meanDailyMileage = stats.getMean();
			System.out.println(b + " --> " + meanDailyMileage);

		}

	}
}
