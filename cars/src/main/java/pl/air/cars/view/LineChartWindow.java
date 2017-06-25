package pl.air.cars.view;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.ui.ApplicationFrame;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;

public class LineChartWindow extends ApplicationFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	CategoryDataset dataset;
	public LineChartWindow( String windowTitle , String chartTitle, CategoryDataset dataset ) {
      super(windowTitle);
      this.dataset = dataset;
      JFreeChart lineChart = ChartFactory.createLineChart(
         chartTitle,
         "Rok","Cena",
         dataset,
         PlotOrientation.VERTICAL,
         true,true,false);
         
      ChartPanel chartPanel = new ChartPanel( lineChart );
      chartPanel.setPreferredSize( new java.awt.Dimension( 560 , 367 ) );
      setContentPane( chartPanel );
   }

}