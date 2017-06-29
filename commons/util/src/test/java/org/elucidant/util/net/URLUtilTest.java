/**
 *
 * Name:   URLUtilTest.java
 * Author: John Domingo
 *
 * This file contains the implementation of the URLUtilTest.
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

package org.elucidant.util.net;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 *
 * @author John Domingo
 *
 *
 */

public class URLUtilTest
{
   public final static String TESTURLSTRING1 = 
      "It was a problem with the Exchange Server certificate, I was using.  " +
      "For more details on the error, check \"" +
      "http://www.jroller.com/hasant/entry/" +
      "no_subject_alternative_names_matching\".  Instead of using the IP in " +
      "the URL, I used the Domain Name and it started working fine.\n" +
      "Thanks,\nPaul";
   
   @Test
   public void testExtractUrls()
   {
      try
      {
         List<String> urlList = URLUtil.extractUrls(TESTURLSTRING1);
         
         for (int i = 0; i < urlList.size(); i++)
         {
            if (i == 0)
            {
               System.out.println("URLs Found:\n");
            }
            
            System.out.println(urlList.get(i));
         }
      }
      catch (Exception ex)
      {
         Assert.fail("Unexpected Exception found: " + ex.getMessage());
      }
   }
}
