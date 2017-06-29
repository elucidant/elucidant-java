/**
 *
 * Name:   IPUtilTest.java
 * Author: John Domingo
 *
 * This file contains the implementation of the IPUtilTest.
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

package org.elucidant.util.net;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 *
 * @author John Domingo
 *
 *
 */

public class IPUtilTest
{
   public static final String TESTSUBNETSTRINGSLASH30 = "192.168.0.0/30";
   public static final String TESTSUBNETSTRINGSLASH17 = "192.168.0.0/17";
   public static final String TESTSUBNETSTRINGSLASH16 = "192.168.0.0/16";
   public static final String TESTSUBNETSTRINGSLASH8 = "192.168.0.0/8";
 
   public static final String TESTIPLISTRANGE1 = "192.168.0.0-200";
   public static final String TESTIPLISTRANGE2 = "192.168.0-1.0-200";
   public static final String TESTIPLISTRANGE3 = "192.168.3-5,7.1";
   
   //(enabled=false)
   @Test
   public void testGetListOfIPsFromSubnet()
      throws IOException
   {
      String[] resultIPs = null;
      
      try
      {
         resultIPs = IPUtil.getListOfIPsFromCIDR(TESTSUBNETSTRINGSLASH30);
         
         System.out.println("/30 Host Count: " + resultIPs.length);
         
         /*
          * /30 Host Count: 2
          */
         Assert.assertEquals(resultIPs.length, 2);
               
         resultIPs = IPUtil.getListOfIPsFromCIDR(TESTSUBNETSTRINGSLASH16);
         
         /*
          * /16 Host Count: 65534
          */
         Assert.assertEquals(resultIPs.length, 65534);
         
         System.out.println("/16 Host Count: " + resultIPs.length);
         
         resultIPs = IPUtil.getListOfIPsFromCIDR(TESTSUBNETSTRINGSLASH8);
         
         /*
          * /8 Host Count: 16777214
          */
         Assert.assertEquals(resultIPs.length, 16777214);
         
         System.out.println("/8 Host Count: " + resultIPs.length);
      }
      catch (Exception ex)
      {
         Assert.fail("Found unexpected Exception: " + ex.getMessage());
      }
   }
   
   @Test
   public void testGetListFromRange()
   {
      String[] resultIPs = null;
      
      try
      {
         resultIPs = IPUtil.getListFromRange(TESTIPLISTRANGE1);
         
         /*
          * Host Count: 201
          */
         Assert.assertEquals(resultIPs.length, 201);
         
         System.out.println("Host Count: " + resultIPs.length);
         
//         for (int i = 0; i < resultIPs.length; i++)
//         {
//            System.out.println("IP[" + i + "]: " + resultIPs[i]);
//         }
         
         resultIPs = IPUtil.getListFromRange(TESTIPLISTRANGE2);
         
         /*
          * Host Count: 402
          */
         Assert.assertEquals(resultIPs.length, 402);
         
         System.out.println("Host Count: " + resultIPs.length);
         
         resultIPs = IPUtil.getListFromRange(TESTIPLISTRANGE3);
         
         /*
          * Host Count: 7
          * 192.168.3.1
          * 192.168.4.1
          * 192.168.5.1
          * 192.168.7.1
          * 
          */
         Assert.assertEquals(resultIPs.length, 4);
         
         System.out.println("Host Count: " + resultIPs.length);
      }
      catch (Exception ex)
      {
         Assert.fail("Found unexpected Exception: " + ex.getMessage());
      }
   }
}
