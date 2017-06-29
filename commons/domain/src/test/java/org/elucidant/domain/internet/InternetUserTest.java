/**
 *
 * Name:   InternetUserTest.java
 * Author: John Domingo
 *
 * This file contains the implementation of the InternetUserTest.
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

import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 *
 * This class is the unit test class for InternetUser.
 * 
 * @author John Domingo
 *
 */

public class InternetUserTest
{   
   private String testUserId1 = "Id1";
   private String testUserId2 = "Id2";
   
   private String testUserLoginName1 = "Name1";
   private String testUserLoginName2 = "Name2";
   
   private String testUserLogin1 = "UserLogin1";
   private String testUserLogin2 = "UserLogin2";
   
   private double testBrowseTime1 = 23.45;
   private double testBrowseTime2 = 45.23;
   
   private double testBandwidth1 = 23.45;
   private double testBandwidth2 = 45.23;
   
   private String testURLName1 = "Name1";
   private String testURLName2 = "Name2";
   
   List<InternetURL> testInternetURLList1 = null;
   List<InternetURL> testInternetURLList2 = null;
   
   private InternetURL testInternetURL1 = null;
   private InternetURL testInternetURL2 = null;
   
   private InternetUser testInternetUser1 = null;
   private InternetUser testInternetUser2 = null;
   
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
      
      testInternetURLList1 = new ArrayList<InternetURL>();
      
      testInternetURLList1.add(testInternetURL1);
      testInternetURLList1.add(testInternetURL2);
      
      testInternetURLList2 = new ArrayList<InternetURL>();
      
      testInternetURLList2.add(testInternetURL2);
      testInternetURLList2.add(testInternetURL1);
      
      testInternetUser1 = new InternetUser(
         testUserId1,
         testUserLoginName1,
         testUserLogin1,
         testBandwidth1,
         testBrowseTime1,
         testInternetURLList1,
         testInternetURLList2);
      
      testInternetUser2 = new InternetUser(
         testUserId2,
         testUserLoginName2,
         testUserLogin2,
         testBandwidth2,
         testBrowseTime2);
   }
   
   @Test
   public void testConstructors()
   {
      Assert.assertNotNull(testInternetUser1);
      Assert.assertNotNull(testInternetUser2);
      
      Assert.assertEquals(testInternetUser1.getUserId(), testUserId1);
      Assert.assertEquals(testInternetUser1.getUserLoginName(),
         testUserLoginName1);
      Assert.assertEquals(testInternetUser1.getUserLogin(), testUserLogin1);
      Assert.assertEquals(testInternetUser1.getBrowseTime(), testBrowseTime1);
      Assert.assertEquals(testInternetUser1.getBandwidth(), testBandwidth1);
      Assert.assertEquals(testInternetUser1.getTopTenURLByBandwidth(),
         testInternetURLList1);
      Assert.assertEquals(testInternetUser1.getTopTenURLByBrowseTime(),
         testInternetURLList2);
      
      // Test empty constructor
      InternetUser testInternetUser = new InternetUser();
      
      Assert.assertNull(testInternetUser.getUserId());
      Assert.assertNull(testInternetUser.getUserLoginName());
      Assert.assertNull(testInternetUser.getUserLogin());
      Assert.assertEquals(testInternetUser.getBrowseTime(), 0.0);
      Assert.assertEquals(testInternetUser.getBandwidth(), 0.0);
      Assert.assertEquals(testInternetUser.getTopTenURLByBandwidth().size(), 0);
      Assert.assertEquals(testInternetUser.getTopTenURLByBrowseTime().size(), 0);
   }
   
   @Test
   public void testGettersSetters()
   {
      InternetUser testInternetUser = new InternetUser();
      
      testInternetUser.setUserId(testUserId1);
      testInternetUser.setUserLoginName(testUserLoginName1);
      testInternetUser.setUserLogin(testUserLogin1);
      testInternetUser.setBrowseTime(testBrowseTime1);
      testInternetUser.setBandwidth(testBandwidth1);
      testInternetUser.setTopTenURLByBandwidth(testInternetURLList1);
      testInternetUser.setTopTenURLByBrowseTime(testInternetURLList2);
      
      Assert.assertEquals(testInternetUser.getUserId(),
         testUserId1);
      Assert.assertEquals(testInternetUser.getUserLoginName(),
         testUserLoginName1);
      Assert.assertEquals(testInternetUser.getUserLogin(), testUserLogin1);
      Assert.assertEquals(testInternetUser.getBrowseTime(), testBrowseTime1);
      Assert.assertEquals(testInternetUser.getBandwidth(), testBandwidth1);
      Assert.assertEquals(testInternetUser.getTopTenURLByBandwidth(),
         testInternetURLList1);
      Assert.assertEquals(testInternetUser.getTopTenURLByBrowseTime(),
         testInternetURLList2);
   }
   
   @Test
   public void testEqualsHashCodeToString()
   {
      // Test equals()...
      Assert.assertTrue(testInternetUser1.equals(testInternetUser1));
      Assert.assertFalse(testInternetUser1.equals(testInternetUser2));
      
      // Test hashCode()...
      Assert.assertEquals(testInternetUser1.hashCode(),
         testInternetUser1.hashCode());
      Assert.assertNotEquals(testInternetUser1.hashCode(),
         testInternetUser2.hashCode());
      
      // Test toString()...
      Assert.assertEquals(testInternetUser1.toString(),
         testInternetUser1.toString());
      Assert.assertNotEquals(testInternetUser1.toString(),
         testInternetUser2.toString());
   }
}
