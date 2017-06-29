/**
 *
 * Name:   InternetURLTest.java
 * Author: John Domingo
 *
 * This file contains the implementation of the InternetURLTest.
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

package org.elucidant.domain.internet;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 *
 * @author John Domingo
 *
 *
 */

public class InternetURLTest
{
   private String testUserId1 = "Id1";
   private String testUserId2 = "Id2";
   
   private String testURLName1 = "Name1";
   private String testURLName2 = "Name2";

   private double testBrowseTime1 = 23.45;
   private double testBrowseTime2 = 45.23;
   
   private double testBandwidth1 = 23.45;
   private double testBandwidth2 = 45.23;
   
   private InternetURL testInternetURL1 = null;
   private InternetURL testInternetURL2 = null;
   
   @BeforeClass
   public void setUp()
   {         
      testInternetURL1 = new InternetURL(
         testUserId1,
         testURLName1,
         testBandwidth1,
         testBrowseTime1);
      
      testInternetURL2 = new InternetURL(
         testUserId2,
         testURLName2,
         testBandwidth2,
         testBrowseTime2);
   }
   
   @Test
   public void testConstructors()
   {
      Assert.assertNotNull(testInternetURL1);
      Assert.assertNotNull(testInternetURL2);
      
      Assert.assertEquals(testInternetURL1.getUserId(),
         testUserId1);
      Assert.assertEquals(testInternetURL1.getUrlName(),
         testURLName1);
      Assert.assertEquals(testInternetURL1.getBrowseTime(), testBrowseTime1);
      Assert.assertEquals(testInternetURL1.getBandwidth(), testBandwidth1);
      
      // Test empty constructor
      InternetURL testInternetURL = new InternetURL();
      
      Assert.assertNull(testInternetURL.getUserId());
      Assert.assertNull(testInternetURL.getUrlName());
      Assert.assertEquals(testInternetURL.getBrowseTime(), 0.0);
      Assert.assertEquals(testInternetURL.getBandwidth(), 0.0);
   }
   
   @Test
   public void testGettersSetters()
   {
      InternetURL testInternetURL = new InternetURL();
      
      testInternetURL.setUserId(testUserId1);
      testInternetURL.setUrlName(testURLName1);
      testInternetURL.setBrowseTime(testBrowseTime1);
      testInternetURL.setBandwidth(testBandwidth1);
      
      Assert.assertEquals(testInternetURL.getUserId(),
         testUserId1);
      Assert.assertEquals(testInternetURL.getUrlName(),
         testURLName1);
      Assert.assertEquals(testInternetURL.getBrowseTime(), testBrowseTime1);
      Assert.assertEquals(testInternetURL.getBandwidth(), testBandwidth1);
   }
   
   @Test
   public void testEqualsHashCodeToString()
   {
      // Test equals()...
      Assert.assertTrue(testInternetURL1.equals(testInternetURL1));
      Assert.assertFalse(testInternetURL1.equals(testInternetURL2));
      
      // Test hashCode()...
      Assert.assertEquals(testInternetURL1.hashCode(),
         testInternetURL1.hashCode());
      Assert.assertNotEquals(testInternetURL1.hashCode(),
         testInternetURL2.hashCode());
      
      // Test toString()...
      Assert.assertEquals(testInternetURL1.toString(),
         testInternetURL1.toString());
      Assert.assertNotEquals(testInternetURL1.toString(),
         testInternetURL2.toString());
   }
}
