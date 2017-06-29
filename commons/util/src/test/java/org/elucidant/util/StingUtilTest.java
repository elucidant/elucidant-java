/**
 *
 * Name:   StingUtilTest.java
 * Author: John Domingo
 *
 * This file contains the implementation of the StingUtilTest.
 *
 * Copyright 2017 by John Domingo
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

package org.elucidant.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ResourceBundle;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 *
 * @author John Domingo
 *
 *
 */

public class StingUtilTest
{
   public static String TESTIOFILE = null;
   
   static
   {
      ResourceBundle properties = ResourceBundle.getBundle("testresources");
      
      TESTIOFILE = properties.getString("TESTIOFILE");
   }
   
   @Test
   public void testconvertInputStreamToString()
      throws IOException
   {
      File testFile = new File(TESTIOFILE);
      
      FileInputStream fis = null;
      
      try
      {
         fis = new FileInputStream(testFile);
         
         String testFileString = StringUtil.convertInputStreamToString(fis);
         
         Assert.assertTrue(testFileString.contains("This is a test!"));
      }
      catch (Exception ex)
      {
         Assert.fail("Found UNEXPECTED Exception: " + ex.getMessage());
      }
      finally
      {
         if (fis != null)
         {
            fis.close();
         }
      }
   }
}
