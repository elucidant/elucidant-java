/**
 *
 * Name:   PDFUtilTest.java
 * Author: John Domingo
 *
 * This file contains the implementation of the PDFUtilTest.
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

import org.testng.Assert;
import org.testng.annotations.Test;

import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;

/**
 *
 * @author John Domingo
 *
 *
 */

public class PDFUtilTest
{
   @Test
   public void testCreatePrecisionFloatPDFCell()
   {
      PdfPCell pdfCell = PDFUtil.createPrecisionFloatPDFCell(
         0, 2.98888, PDFUtil.COURIER8);
      
      Assert.assertNotNull(pdfCell);
      
      Phrase phrase = pdfCell.getPhrase();
      
      Assert.assertEquals(phrase.getContent(), "3");
      
      pdfCell = PDFUtil.createPrecisionFloatPDFCell(
         1, 2.98888, PDFUtil.COURIER8);
      
      Assert.assertNotNull(pdfCell);
      
      phrase = pdfCell.getPhrase();
      
      Assert.assertEquals(phrase.getContent(), "3.0");
      
      pdfCell = PDFUtil.createPrecisionFloatPDFCell(
         2, 2.98888, PDFUtil.COURIER8);
      
      Assert.assertNotNull(pdfCell);
      
      phrase = pdfCell.getPhrase();
      
      Assert.assertEquals(phrase.getContent(), "2.99");
      
      pdfCell = PDFUtil.createPrecisionFloatPDFCell(
         -2, 2.98888, PDFUtil.COURIER8);
      
      Assert.assertNotNull(pdfCell);
      
      phrase = pdfCell.getPhrase();
      
      Assert.assertEquals(phrase.getContent(), "3");
   }
}
