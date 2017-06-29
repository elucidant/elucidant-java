/**
 *
 * Name:   ByteUtilTest.java
 * Author: John Domingo
 *
 * This file contains the implementation of the ByteUtilTest.
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

package org.elucidant.util.lang;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 *
 * @author John Domingo
 *
 *
 */

public class ByteUtilTest
{
   public final static double TESTBYTES = 2345678901.0;
   public final static double TESTNEGATIVEBYTES = -2345678901.0;
   
   @Test
   public void testConvertBytesToGigabytes()
   {
      try
      {
         double gbValue = ByteUtil.convertBytesToGigabytes(TESTBYTES);
         
         System.out.println(TESTBYTES + " bytes: " + gbValue + " Gigabytes");
      }
      catch (ByteUtilException buex)
      {
         Assert.fail("Found UNEXPECTED ByteUtilException: " +
            buex.getMessage());
      }
   }
   
   @Test
   public void testConvertBytesToGigabytesNegativeBytes()
   {
      try
      {
         ByteUtil.convertBytesToGigabytes(TESTNEGATIVEBYTES);
         
         Assert.fail(
            "EXPECTED ByteUtilException due to NEGATIVE bytes provided!");
      }
      catch (ByteUtilException buex)
      {
         String message =
            String.format(
               ByteUtil.errorMessageProperties.getString(
                  "INVALIDBYTESSPECIFIEDNEGATIVE"),
            Double.toString(TESTNEGATIVEBYTES));
         
         System.out.println("Expected error message: " + message);
         
         Assert.assertTrue(buex.getMessage().contains(message));
      }
   }
}
