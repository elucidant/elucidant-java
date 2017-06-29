/**
 *
 * Name:   HeaderFooterPageEvent.java
 * Author: John Domingo
 *
 * This file contains the implementation of the HeaderFooterPageEvent.
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

import java.io.IOException;
import java.util.ResourceBundle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.ExceptionConverter;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfTemplate;
import com.itextpdf.text.pdf.PdfWriter;

/**
 *
 * This class supports Header and Footer page events when creating a PDF
 * document.
 * 
 * @author John Domingo
 *
 */

public class HeaderFooterPageEvent
   extends PdfPageEventHelper
{  
   private static Logger LOGGER =
      LoggerFactory.getLogger(HeaderFooterPageEvent.class);
   
   public static ResourceBundle errorMessageProperties = null;
   
   private Paragraph header;
   private Paragraph footer;
   
   private String headerImagePath;
   
   private final static float DEFAULTHEADERHEIGHT = 40f;
   private final static float DEFAULTFOOTERHEIGHT = 40f;

   /** The template with the total number of pages. */
   PdfTemplate pdfTemplate;
   
   static
   {
      errorMessageProperties =
         ResourceBundle.getBundle("util_exception_messages");
   }
   
   /**
    * 
    * Allows us to change the content of the header.
    * 
    * @param header The new header String
    * 
    */
   
   public void setHeader(String header)
   {
      this.header = new Paragraph(header, PDFUtil.TIMESROMAN18BOLD);
   }

   public void setFooter(String footer)
   {
      this.footer = new Paragraph(footer, PDFUtil.TIMESROMAN8);
   }

   /**
    * 
    * Retrieves the headerImagePath attribute.
    *
    * @return String object representing the value of the headerImagePath
    *    attribute.
    *
    */
   
   public String getHeaderImagePath()
   {
      return this.headerImagePath;
   }
   
   /**
    * 
    * Sets the headerImagePath attribute..
    * 
    * @param header String object to set the headerImagePath attribute to.
    * 
    */
   
   public void setHeaderImagePath(String headerImagePath)
   {
      this.headerImagePath = headerImagePath;
   }
   
   /**
    * 
    * Creates the PdfTemplate that will hold the total number of pages.
    * 
    * @see com.itextpdf.text.pdf.PdfPageEventHelper#onOpenDocument(
    *      com.itextpdf.text.pdf.PdfWriter, com.itextpdf.text.Document)
    */
   
   public void onOpenDocument(PdfWriter writer, Document document)
   {
      pdfTemplate = writer.getDirectContent().createTemplate(30, 16);
   }

   /**
    * 
    * Adds a header to every page
    * 
    * @see com.itextpdf.text.pdf.PdfPageEventHelper#onEndPage(
    *      com.itextpdf.text.pdf.PdfWriter, com.itextpdf.text.Document)
    *      
    */
   
   public void onEndPage(PdfWriter writer, Document document)
   {
      PdfPTable tblheader = new PdfPTable(3);
      PdfPTable tblfooter = new PdfPTable(3);
   
      try
      {
         tblheader.setWidths(new int[]{24, 48, 24});
         tblheader.setTotalWidth(590);
         tblheader.setLockedWidth(true);
         tblheader.getDefaultCell().setFixedHeight(DEFAULTHEADERHEIGHT);
         tblheader.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
         tblheader.getDefaultCell().
            setHorizontalAlignment(Element.ALIGN_CENTER);
         
         tblheader.addCell("");
                 
         tblheader.addCell(header);
         
         if (this.headerImagePath != null)
         {
            try
            {
               Image headerImage = Image.getInstance(headerImagePath);
               
               PdfPCell cell = new PdfPCell();
               
               // Chunk image moved right on x-axis to get log closer to right margin
               cell.addElement(new Chunk(headerImage, 50, 5));
               cell.setBorder(PdfPCell.NO_BORDER);
               
               tblheader.addCell(cell);
            }
            catch (IOException ioex)
            {
               String message = String.format(
                  errorMessageProperties.getString("INVALIDHEADERIMAGESPECIFIED"),
                  headerImagePath,
                  ioex.getMessage());
               
               LOGGER.warn(message);
               
               // Just print out warning and create an empty cell...
               tblheader.addCell("");
            }
         }
         else
         {
            tblheader.addCell("");
         }
         
         // Subtracting space into the left margin for true centering
         tblheader.writeSelectedRows(
            0,
            -1,
            document.leftMargin() - 25,
            document.top(),
            writer.getDirectContent());

         tblfooter.setHorizontalAlignment(Element.ALIGN_LEFT);
         tblfooter.setWidths(new int[]{24, 48, 24});
         tblfooter.setTotalWidth(590);
         tblfooter.setLockedWidth(true);
         tblfooter.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
         tblfooter.getDefaultCell().setFixedHeight(DEFAULTFOOTERHEIGHT);
         tblfooter.getDefaultCell().
            setHorizontalAlignment(Element.ALIGN_CENTER);
         
         tblfooter.addCell("");
         tblfooter.addCell(footer);
         
         String pageNumberText = "Page " + writer.getCurrentPageNumber();
         
         Paragraph pageNumberParagraph =
            new Paragraph(pageNumberText, PDFUtil.TIMESROMAN8);
         
         tblfooter.addCell(pageNumberParagraph);
         
         // Subtracting space into the left margin for true centering
         tblfooter.writeSelectedRows(
            0,
            -1,
            document.leftMargin() - 25,
            document.bottomMargin(),
            writer.getDirectContent());
      }
      catch(DocumentException dex)
      {
         throw new ExceptionConverter(dex);
      }
   }
}
