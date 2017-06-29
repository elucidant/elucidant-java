/**
 *
 * Name:   ByteUtil.java
 * Author: John Domingo
 *
 * This file contains the implementation of the ByteUtil.
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

import java.util.ResourceBundle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.elucidant.util.DateTimeUtil;

/**
 *
 * @author John Domingo
 *
 *
 */

public class ByteUtil
{
   private static Logger LOGGER =
      LoggerFactory.getLogger(DateTimeUtil.class);
   
   public static ResourceBundle errorMessageProperties = null;
   
   static
   {
      errorMessageProperties =
         ResourceBundle.getBundle("util_exception_messages");
   }
   
   public final static long BYTES_IN_KB = 1024;
   public final static long BYTES_IN_MB = BYTES_IN_KB * 1024;
   public final static long BYTES_IN_GB = BYTES_IN_MB * 1024;
   public final static long BYTES_IN_TB = BYTES_IN_GB * 1024;
   
   public static double convertBytesToGigabytes(double bytes)
      throws ByteUtilException
   {
      double gigaBytes = 0;
      
      if (bytes >= 0)
      {
         if (bytes > 0)
         {
            gigaBytes = bytes / BYTES_IN_GB;
         }
      }
      else
      {
         String errorMessage =
            String.format(
               errorMessageProperties.getString("INVALIDBYTESSPECIFIEDNEGATIVE"),
            Double.toString(bytes));
         
         LOGGER.error(errorMessage);
         
         throw new ByteUtilException(errorMessage);
      }
      
      return gigaBytes;
   }
}
