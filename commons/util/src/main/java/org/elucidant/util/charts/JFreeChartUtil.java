/**
 *
 * Name:   JFreeChartUtil.java
 * Author: John Domingo
 *
 * This file contains the implementation of the JFreeChartUtil.
 *
 * Copyright 2016 by John Domingo
 *
 * This file is part of some open source application.
 * 
 * Some open source application is free software: you can redistribute 
 * it and/or modify it under the terms of the GNU General Public 
 * License as published by the Free Software Foundation, either 
 * version 3 of the License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty  of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301  USA
 * 
 */

package org.elucidant.util.charts;

import java.awt.Color;
import java.awt.Font;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.ItemLabelAnchor;
import org.jfree.chart.labels.ItemLabelPosition;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.StackedBarRenderer;
import org.jfree.chart.renderer.category.StandardBarPainter;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.CategoryDataset;
import org.jfree.ui.TextAnchor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.elucidant.util.awt.ColorUtil;
import org.elucidant.util.awt.FontUtil;

/**
 *
 * @author John Domingo
 *
 *
 */

public class JFreeChartUtil
{
   private static Logger LOGGER =
      LoggerFactory.getLogger(JFreeChartUtil.class);
   
   private final static boolean CHARTLEGENDDEFAULT = false;
   private final static boolean CHARTTOOLTIPSDEFAULT = true;
   private final static boolean CHARTURLSDEFAULT = false;
   
   private final static Color BARCOLORDEFAULT = ColorUtil.SHARPBLUE;
   
   private final static Font TITLEFONTDEFAULT = FontUtil.TIMESROMANBOLD20;
   private final static Font CATEGORYAXISFONTDEFAULT =
      FontUtil.TIMESROMANBOLD15;
   private final static Font VALUEAXISFONTDEFAULT =
      FontUtil.TIMESROMANBOLD15;
   private final static Font TICKLABLECATEGORYAXISFONTDEFAULT =
      FontUtil.TIMESROMANBOLD8;
   private final static Font TICKLABLEAVALUEXISFONTDEFAULT =
      FontUtil.TIMESROMANBOLD8;
   
   private final static PlotOrientation CHARTPODEFAULT =
      PlotOrientation.VERTICAL;
   
   /**
    *
    * Empty constructor.
    *
    */
   
   public JFreeChartUtil()
   {
   }

   public static JFreeChart generateBarChart(
      CategoryDataset dataset,
      String title,
      String categoryAxis,
      String valueAxis)
   {
      JFreeChart chart = generateBarChart(
         dataset,
         title,
         TITLEFONTDEFAULT,
         categoryAxis,
         CATEGORYAXISFONTDEFAULT,
         valueAxis,
         VALUEAXISFONTDEFAULT,
         TICKLABLECATEGORYAXISFONTDEFAULT,
         TICKLABLEAVALUEXISFONTDEFAULT,
         CHARTPODEFAULT,
         CHARTLEGENDDEFAULT,
         CHARTTOOLTIPSDEFAULT,
         CHARTURLSDEFAULT,
         null);

      return chart;
   }
   
   public static JFreeChart generateBarChart(
      CategoryDataset dataset,
      String title,
      String categoryAxis,
      String valueAxis,
      Color barColor)
   {      
      JFreeChart chart = JFreeChartUtil.generateBarChart(
         dataset,
         title,
         TITLEFONTDEFAULT,
         categoryAxis,
         CATEGORYAXISFONTDEFAULT,
         valueAxis,
         VALUEAXISFONTDEFAULT,
         TICKLABLECATEGORYAXISFONTDEFAULT,
         TICKLABLEAVALUEXISFONTDEFAULT,
         CHARTPODEFAULT,
         CHARTLEGENDDEFAULT,
         CHARTTOOLTIPSDEFAULT,
         CHARTURLSDEFAULT,
         barColor);
      
      return chart;
   }
   
   public static JFreeChart generateBarChart(
      CategoryDataset dataset,
      String title,
      Font titleFont,
      String categoryAxis,
      Font categoryAxisFont,
      String valueAxis,
      Font valueAxisFont,
      Font categoryAxisTickLabelFont,
      Font valueAxisTickLabelFont,
      PlotOrientation po,
      boolean legend,
      boolean tooltips,
      boolean urls,
      Color barColor)
   {
      Color theBarColor = null;
      
      if (LOGGER.isDebugEnabled() == true)
      {
         LOGGER.debug("Generating Bar Chart...");
         LOGGER.debug("   Bar Chart Title: " + title);
         LOGGER.debug("   Category Axis (x) Title: " + categoryAxis);
         LOGGER.debug("   Value Axis (y) Title: " + valueAxis);
      }
      
      JFreeChart chart = ChartFactory.createBarChart(
         title,
         categoryAxis,
         valueAxis,
         dataset,
         po,
         legend,
         tooltips,
         urls);
      
      // Set the Title
      TextTitle textTile = chart.getTitle();
      
      if (textTile != null)
      {
         textTile.setFont(titleFont);
      }
      
      CategoryPlot plot = chart.getCategoryPlot();
      
      // Set the Axis fonts...
      plot.getDomainAxis().setLabelFont(categoryAxisFont);
      plot.getRangeAxis().setLabelFont(valueAxisFont);
      
      // Set the tick label fonts...
      plot.getDomainAxis().setTickLabelFont(categoryAxisTickLabelFont);
      plot.getRangeAxis().setTickLabelFont(valueAxisTickLabelFont);

      // Display amounts on bars...
      StackedBarRenderer renderer = new StackedBarRenderer(false);
      
      renderer.setBaseItemLabelGenerator(
         new StandardCategoryItemLabelGenerator());
      
      renderer.setBaseItemLabelsVisible(true);
      
      // Using Tick Label Font for the Item Label Font, and then changing
      // color...
      Font itemLabelFont = new Font(
         valueAxisTickLabelFont.getName(),
         Font.PLAIN,
         valueAxisTickLabelFont.getSize() - 2);
            
      renderer.setBaseItemLabelFont(itemLabelFont);
      
      renderer.setBaseItemLabelPaint(ColorUtil.SHARPYELLOW);
      
      ItemLabelPosition position = new ItemLabelPosition(
         ItemLabelAnchor.CENTER, 
         TextAnchor.TOP_CENTER);
      
      renderer.setBasePositiveItemLabelPosition(position);
      
      // Ternary operation to assign theBarColor local variable...
      theBarColor = (barColor != null) ? barColor : BARCOLORDEFAULT;
      
      renderer.setSeriesPaint(0, theBarColor);
            
      renderer.setItemMargin(.1);
      
      // Removing Gradient on Bars...
      renderer.setBarPainter(new StandardBarPainter());
      
      chart.getCategoryPlot().setRenderer(renderer);

      return chart;
   }
}
