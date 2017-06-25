package pl.air.cars.io;

import java.util.List;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;

public class ReadingService {
	private List<String> lines = new ArrayList<String>();
	public List<String> getLines() {
		return lines;
	}

	private String uri;
	
	public ReadingService(String uri){
		this.uri = uri;
	}
	
	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public void readFormattedText() throws IOException{
		Reader in = new FileReader(uri);
		BufferedReader br = new BufferedReader(in);
		String line = br.readLine();
		while(line != null){
			line = br.readLine();
			lines.add(line);
		}
		br.close();
	}
}

