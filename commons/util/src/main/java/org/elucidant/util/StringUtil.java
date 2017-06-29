/**
 *
 * Name:   StringUtil.java
 * Author: John Domingo
 *
 * This file contains the implementation of the StringUtil.
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

package org.elucidant.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 *
 * @author John Domingo
 *
 *
 */

public class StringUtil
{
   /**
    * 
    * This method will convert an InputStream object to a String object.
    *
    * @param is InputStream object whose value will be converted into a
    *    String object.
    *    
    * @return String object created from InputStream object.
    * 
    * @throws IOException thrown if an error/exception occurs during the
    *    conversion of the InputStream object to a String.
    *
    */
   
   public static String convertInputStreamToString(final InputStream is)
      throws IOException
   {
      String bufferString = null;
      
      StringBuffer sb = new StringBuffer();
      
      BufferedReader streamReader = new BufferedReader(
         new InputStreamReader(is));
      
      while ((bufferString = streamReader.readLine()) != null)
      {
         sb.append(bufferString).
            append("\n");
      }
      
      return sb.toString();
   }
}
