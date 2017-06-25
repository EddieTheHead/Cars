package pl.air.cars.view;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

public class PieChartWindow extends ApplicationFrame {

	private PieDataset dataset;
	private String chartTitle;

	public String getChartTitle() {
		return chartTitle;
	}

	public void setChartTitle(String chartTitle) {
		this.chartTitle = chartTitle;
	}

	public PieDataset getDataset() {
		return dataset;
	}

	public void setDataset(PieDataset dataset) {
		this.dataset = dataset;
	}

public PieChartWindow( String title, PieDataset dataset,String chartTitle ) {
      super( title ); 
	  this.dataset = dataset;
	  this.chartTitle = chartTitle;
	  JFreeChart chart = createChart(dataset);
      setContentPane(new ChartPanel(chart));
   }

	private JFreeChart createChart(PieDataset dataset) {
		JFreeChart chart = ChartFactory.createPieChart(chartTitle, // chart title
				dataset, // data
				true, // include legend
				true, false);
		return chart;
	}

}