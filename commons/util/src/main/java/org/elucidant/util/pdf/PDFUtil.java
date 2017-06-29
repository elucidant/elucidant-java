/**
 *
 * Name:   PDFUtil.java
 * Author: John Domingo
 *
 * This file contains the implementation of the PDFUtil.
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

package org.elucidant.util.pdf;

import java.awt.Graphics2D;
import java.io.IOException;
import java.util.ResourceBundle;

import org.jfree.chart.JFreeChart;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.itextpdf.awt.PdfGraphics2D;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfTemplate;
import com.itextpdf.text.pdf.PdfWriter;

/**
 *
 * @author John Domingo
 *
 *
 */

public class PDFUtil
{
   private static Logger LOGGER =
      LoggerFactory.getLogger(PDFUtil.class);
   
   public static ResourceBundle errorMessageProperties = null;
      
   public final static int TWODIGITFLOATPRECISION = 2;
   public final static int THREEDIGITFLOATPRECISION = 3;
   public final static float DEFAULTXAXISIMAGESTART = 30;

   public final static Font TIMESROMAN6 = new Font(FontFamily.TIMES_ROMAN, 6f);
   public final static Font TIMESROMAN6BOLD =
      new Font(FontFamily.TIMES_ROMAN, 6f, Font.BOLD);
   public final static Font TIMESROMAN8 = new Font(FontFamily.TIMES_ROMAN, 8f);
   public final static Font TIMESROMAN8BOLD =
      new Font(FontFamily.TIMES_ROMAN, 8f, Font.BOLD);
   public final static Font TIMESROMAN10 =
      new Font(FontFamily.TIMES_ROMAN, 10f);
   public final static Font TIMESROMAN10BOLD =
      new Font(FontFamily.TIMES_ROMAN, 10f, Font.BOLD);
   public final static Font TIMESROMAN11 =
      new Font(FontFamily.TIMES_ROMAN, 11f);
   public final static Font TIMESROMAN11BOLD =
      new Font(FontFamily.TIMES_ROMAN, 11f, Font.BOLD);
   public final static Font TIMESROMAN18BOLD =
      new Font(FontFamily.TIMES_ROMAN, 18f, Font.BOLD);
  
   public final static Font COURIER8 = new Font(FontFamily.COURIER, 8f);
   public final static Font COURIER8BOLD =
      new Font(FontFamily.COURIER, 11f, Font.BOLD);
   
   static
   {
      errorMessageProperties =
         ResourceBundle.getBundle("util_exception_messages");
   }
   
   public static PdfPTable createPDFTable()
   {
      PdfPTable table = new PdfPTable(3); // 3 columns.

      PdfPCell cell1 = new PdfPCell(new Paragraph("Cell 1"));
      PdfPCell cell2 = new PdfPCell(new Paragraph("Cell 2"));
      PdfPCell cell3 = new PdfPCell(new Paragraph("Cell 3"));

      table.addCell(cell1);
      table.addCell(cell2);
      table.addCell(cell3);
      
      return table;
   }
   
   public static PdfPCell createPrecisionFloatPDFCell(
      int precision,
      double doubleValue,
      Font font)
   {
      PdfPCell cell = null;
      
      String precisionString = null;
      
      if (precision >= 0)
      {
         precisionString = Integer.toString(precision);
      }
      else
      {
         String message = String.format(
            errorMessageProperties.getString("INVALIDPRECISIONSPECIFIED"),
            precision);
         
         LOGGER.warn(message);
         
         // We can continue, but using precision of 0 by default...
         precisionString = Integer.toString(0);
      }

      String floatPrecisionFormat = "%." + precisionString + "f";
      
      LOGGER.debug("Using float precision string format: " +
         floatPrecisionFormat);
      
      String formattedFloatString =
         String.format(floatPrecisionFormat, doubleValue);
      
      LOGGER.debug("Formatted float: " + formattedFloatString);
      
      cell = new PdfPCell(
         new Phrase(formattedFloatString, font));
      
      return cell;
   }
   
   /**
    * 
    * Creates a bar chart inside a PDF document via the PdfWriter object.
    *
    * @param barChart JFreeChart object that represents the bar chart to embed
    *    in the PDF document.
    * @param pdfWriter PdfWriter object that will be used to embed a bar chart
    *    into a PDF document.
    * @param width Primitive integer representing the width of the bar chart.
    * @param height Primitive integer representing the height of the bar chart.
    * 
    * @throws BadElementException thrown when an error/exception occurs during
    *    creation of the bar chart. 
    * @throws IOException thrown when an error/exception occurs during the 
    *    creation of the bar chart into the PDF document.
    *
    */
   
   public static void createBarChart(
      JFreeChart barChart,
      PdfWriter pdfWriter,
      int width,
      int height)
      throws BadElementException, IOException
      
   {            
      PdfContentByte pdfContentByte = pdfWriter.getDirectContent();
      
      PdfTemplate pdfTemplate = pdfContentByte.createTemplate(width, height);
      
      //create graphics     
      Graphics2D graphics2d = new PdfGraphics2D(pdfTemplate, width, height);
      
      //create rectangle
      java.awt.geom.Rectangle2D rectangle2d =
         new java.awt.geom.Rectangle2D.Double(
            0, 0, width, height);

      barChart.draw(graphics2d, rectangle2d);

      graphics2d.dispose();
      
      // Need to make sure we start on the next vertical (y-axis) position on
      // the current PDF document and account for the height of the image.
      pdfContentByte.addTemplate(
         pdfTemplate,
         DEFAULTXAXISIMAGESTART,
         pdfWriter.getVerticalPosition(true) - height);
   }
   
   /**
    * 
    * Creates a bar chart image and returns it to the PDF processing layer.
    *
    * @param barChart JFreeChart object that represents the bar chart to embed
    *    in the PDF document.
    * @param pdfWriter PdfWriter object that will be used to embed a bar chart
    *    into a PDF document.
    * @param width Primitive integer representing the width of the bar chart.
    * @param height Primitive integer representing the height of the bar chart.
    * 
    * @return Image object created representing the bar chart.
    * 
    * @throws BadElementException thrown when an error/exception occurs during
    *    creation of the bar chart. 
    * @throws IOException thrown when an error/exception occurs during the 
    *    creation of the bar chart into the PDF document.
    *
    */
   
   public static Image createBarChartImage(
      JFreeChart barChart,
      PdfWriter pdfWriter,
      int width,
      int height)
      throws BadElementException, IOException
      
   {            
      PdfContentByte pdfContentByte = pdfWriter.getDirectContent();
      
      PdfTemplate pdfTemplate = pdfContentByte.createTemplate(width, height);
      
      //create graphics     
      Graphics2D graphics2d = new PdfGraphics2D(pdfTemplate, width, height);
      
      //create rectangle
      java.awt.geom.Rectangle2D rectangle2d =
         new java.awt.geom.Rectangle2D.Double(
            0, 0, width, height);

      barChart.draw(graphics2d, rectangle2d);

      graphics2d.dispose();
      
      return Image.getInstance(pdfTemplate);
   }
   
   
   public static void addTable(
      Document document,
      float verticalPosition,
      PdfPTable table,
      Paragraph tableHeaderParagraph)
      throws DocumentException
   {
      float tableHeaderParagraphHeight = 0f;
      
      PdfPTable paragraphTable = null;
      
      if (tableHeaderParagraph != null)
      {
         paragraphTable =
            createParagraphTable(tableHeaderParagraph);
         
         tableHeaderParagraphHeight = paragraphTable.getTotalHeight();
      }
      
      LOGGER.debug("In addTable(), table header paragraph height = " +
         tableHeaderParagraphHeight);
      LOGGER.debug("In addTable(), table total height = " +
         table.getTotalHeight());
      LOGGER.debug("In addTable(), table calculated heights = " +
         table.calculateHeights());
      
      // Check if there is enough space based on the paragraph table height,
      // table total height, and margin.
      float newEntryHeight =
         tableHeaderParagraphHeight +
         table.getTotalHeight();
      
      LOGGER.debug("In addTable(), verticalPostion = " + verticalPosition);
      LOGGER.debug("In addTable(), newEntryHeight = " + newEntryHeight);
      LOGGER.debug("In addTable(), bottom margin = " +
         document.bottomMargin());
      LOGGER.debug("In addTable(), bottom = " + document.bottom());
      
      // We want to make sure we compare to the bottom margin, not the bottom
      // of the document.
      if ((verticalPosition - newEntryHeight) < document.bottomMargin())
      {
         document.newPage();
         
         // Need to add lines to accommodate header...
         document.add(new Paragraph("\n"));
         document.add(new Paragraph("\n"));
      }
      
      if (paragraphTable != null)
      {
         document.add(paragraphTable);
      }
      
      document.add(table);
   }
   
   public static PdfPTable createParagraphTable(Paragraph paragraph)
      throws DocumentException
   {
      PdfPTable paragraphTable = new PdfPTable(1);
      
      paragraphTable.setHorizontalAlignment(Element.ALIGN_LEFT);
      paragraphTable.setWidths(new int[]{80});
      paragraphTable.setTotalWidth(590);
      paragraphTable.setLockedWidth(true);
      paragraphTable.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
      paragraphTable.getDefaultCell().
         setHorizontalAlignment(Element.ALIGN_LEFT);
      
      paragraphTable.addCell(paragraph);
      
      return paragraphTable;
   }
   
   public static void addImage(
      Document document,
      float verticalPosition,
      Image image)
      throws DocumentException
   {
      // writer.getVerticalPosition(true) 
      if (verticalPosition - image.getHeight() < document.bottom())
      {
         document.newPage();
      }
      
      document.add(image);
   }
}
