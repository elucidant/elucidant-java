/**
 *
 * Name:   JFreeChartUtilTest.java
 * Author: John Domingo
 *
 * This file contains the implementation of the JFreeChartUtilTest.
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

import static org.testng.AssertJUnit.assertTrue;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import com.itextpdf.text.Document;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import org.elucidant.util.pdf.HeaderFooterPageEvent;

/**
 *
 * @author John Domingo
 *
 *
 */

public class JFreeChartUtilTest
{
   private final static String TESTPDFDOC = "testdoc.pdf";
   
   public static void writeChartToPDF(
      JFreeChart chart,
      int width,
      int height,
      String fileName)
   {
      PdfWriter writer = null;

      Document document = new Document();

      try
      {
         writer = PdfWriter.getInstance(
            document,
            new FileOutputStream(fileName));
         
         HeaderFooterPageEvent event = new HeaderFooterPageEvent();
         
         String header = "Test Report Header";
         
         String footer = "Test Report Footer";
         
         event.setHeader(header);
         event.setFooter(footer);
         
         writer.setPageEvent(event);
         
         //  Add document header attributes
         document.addAuthor("Test Crew");
         document.addCreationDate();
         document.addProducer();
         document.addCreator("Test Report Creator");
         document.addTitle("Test Report");
         document.setPageSize(PageSize.LETTER);
         
         // open document...
         document.open();
         
         // Create a paragraph
         Paragraph paragraph = new Paragraph(
            "This is a test report.");
         
         document.add(paragraph);
      }
      catch (Exception e)
      {
         e.printStackTrace();
      }
      finally
      {         
         if (document != null)
         {
            document.close();
         }
      }
   }
   
   public static JFreeChart generatePieChart()
   {
      DefaultPieDataset dataSet = new DefaultPieDataset();
      
      dataSet.setValue("China", 19.64);
      dataSet.setValue("India", 17.3);
      dataSet.setValue("United States", 4.54);
      dataSet.setValue("Indonesia", 3.4);
      dataSet.setValue("Brazil", 2.83);
      dataSet.setValue("Pakistan", 2.48);
      dataSet.setValue("Bangladesh", 2.38);

      JFreeChart chart = ChartFactory.createPieChart(
         "World Population by countries", dataSet, true, true, false);

      return chart;
   }
   
   @AfterTest
   public void afterTest()
      throws IOException
   {
      File testPdfDocFile = new File(TESTPDFDOC);
      
      System.out.println("Attempting to delete " +
         testPdfDocFile.getAbsolutePath() +
         " file...");
      
      if (testPdfDocFile.exists() == true)
      {
         System.out.println("File found...Deleting " +
            testPdfDocFile.getAbsolutePath() +
            " file...");
         
         FileUtils.forceDelete(new File(TESTPDFDOC));
      }
   }
   
   //@Test(enabled=false)
   @Test
   public void testGenerateBarChart()
   {
      DefaultCategoryDataset testDataset = new DefaultCategoryDataset();
      
      // By bandwidth
      testDataset.setValue(78.0, "Bandwidth (GB)", "gesmo");
      testDataset.setValue(57.4, "Bandwidth (GB)", "serfe");
      testDataset.setValue(51.2, "Bandwidth (GB)", "reeje3");
      testDataset.setValue(50.0, "Bandwidth (GB)", "trael");
      testDataset.setValue(48.3, "Bandwidth (GB)", "thopa2");
      testDataset.setValue(44.3, "Bandwidth (GB)", "frofi");
      testDataset.setValue(41.0, "Bandwidth (GB)", "leeka5");
      testDataset.setValue(40.9, "Bandwidth (GB)", "brora6");
      testDataset.setValue(38.4, "Bandwidth (GB)", "mikli");
      testDataset.setValue(37.7, "Bandwidth (GB)", "cabcy");
      
      String testTitle = "Top 10 Internet Users By Bandwidth";
      String testCategoryAxis = "User Login";
      String testValueAxis = "Bandwidth (GB)";
      
      JFreeChart chartResult = JFreeChartUtil.generateBarChart(
         testDataset,
         testTitle,
         testCategoryAxis,
         testValueAxis);
      
      writeChartToPDF(chartResult, 600, 200, TESTPDFDOC);
         
      assertTrue(new File(TESTPDFDOC).exists());   
   }
}
