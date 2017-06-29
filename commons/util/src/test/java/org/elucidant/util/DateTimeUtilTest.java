/**
 *
 * Name:   DateTimeUtilTest.java
 * Author: John Domingo
 *
 * This file contains the implementation of the DateTimeUtilTest.
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

import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;
import org.joda.time.DateTimeUtils;
import org.testng.Assert;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author John Domingo
 *
 *
 */

public class DateTimeUtilTest
{
   private final static String TESTDATEFORMATSTRING = "MM/dd/yyyy";
   
   private final static String TESTDATESTRING1 = "07/22/1972";
   private final static String TESTDATESTRING2 = "01/22/1972";
   
   @AfterTest
   public void cleanup()
   {
     // Make sure to reset system time...just in case!
     DateTimeUtils.setCurrentMillisSystem();
   }
   
   @Test
   public void testGetMonthIndex()
   {
      SimpleDateFormat sdf = new SimpleDateFormat(TESTDATEFORMATSTRING);
      
      Date testDate = null;
      
      int result;
      
      try
      {
         testDate = sdf.parse(TESTDATESTRING1);
         
         result = DateTimeUtil.getMonthIndex(testDate);
         
         System.out.println(
            "Test 1: Month Index for Date [" + TESTDATESTRING1 + "]: " + result);
         
         Assert.assertEquals(result, 7);
      }
      catch (ParseException pex)
      {
         Assert.fail("Found UNEXPECTED ParseException: " + pex.getMessage());
      }
      
      // Test a date in January...
      try
      {
         testDate = sdf.parse(TESTDATESTRING2);
         
         result = DateTimeUtil.getMonthIndex(testDate);
         
         System.out.println(
            "Test 2: Month Index for Date [" + TESTDATESTRING2 + "]: " + result);
         
         Assert.assertEquals(result, 1);
      }
      catch (ParseException pex)
      {
         Assert.fail("Found UNEXPECTED ParseException: " + pex.getMessage());
      }
   }
   
   @Test
   public void testGetMonthIndexFromSystemDate()
   {
      SimpleDateFormat sdf = new SimpleDateFormat(TESTDATEFORMATSTRING);
      
      Date testDate = null;
      
      int result;
      
      try
      {
         testDate = sdf.parse(TESTDATESTRING1);
         
         // Set the current system time that will be used...
         DateTimeUtils.setCurrentMillisFixed(testDate.getTime());
         
         result = DateTimeUtil.getMonthIndex();
         
         System.out.println(
            "Test 1: Month Index for Date [" + TESTDATESTRING1 + "]: " + result);
         
         Assert.assertEquals(result, 7);
      }
      catch (ParseException pex)
      {
         Assert.fail("Found UNEXPECTED ParseException: " + pex.getMessage());
      }
   }
   
   @Test
   public void testGetPreviousMonth()
   {
      SimpleDateFormat sdf = new SimpleDateFormat(TESTDATEFORMATSTRING);
      
      Date testDate = null;
      
      int result;
      
      // Test a date in July...we should get June of 1972
      try
      {
         testDate = sdf.parse(TESTDATESTRING1);
         
         Date testDateLessOneMonth = DateTimeUtil.getPreviousMonth(testDate);
         
         result = DateTimeUtil.getMonthIndex(testDateLessOneMonth);
         
         System.out.println(
            "Test 1: Last Month Index for Date [" + TESTDATESTRING1 + "]: " + result);
         
         Assert.assertEquals(result, 6);
         
         result = DateTimeUtil.getYear(testDateLessOneMonth);
         
         System.out.println(
            "Test 2: Year for Date [" + TESTDATESTRING2 + "]: " + result);
         
         Assert.assertEquals(result, 1972);
      }
      catch (ParseException pex)
      {
         Assert.fail("Found UNEXPECTED ParseException: " + pex.getMessage());
      }
      
      // Test a date in January...we should get December of 1971
      try
      {
         testDate = sdf.parse(TESTDATESTRING2);
         
         Date testDateLessOneMonth = DateTimeUtil.getPreviousMonth(testDate);
         
         result = DateTimeUtil.getMonthIndex(testDateLessOneMonth);
         
         System.out.println(
            "Test 2: Last Month Index for Date [" + TESTDATESTRING2 + "]: " + result);
         
         Assert.assertEquals(result, 12);
         
         result = DateTimeUtil.getYear(testDateLessOneMonth);
         
         System.out.println(
            "Test 2: Year for Date [" + TESTDATESTRING2 + "]: " + result);
         
         Assert.assertEquals(result, 1971);
      }
      catch (ParseException pex)
      {
         Assert.fail("Found UNEXPECTED ParseException: " + pex.getMessage());
      }
   }
   
   @Test
   public void testGetPreviousMonthFromSystemDate()
   {
      SimpleDateFormat sdf = new SimpleDateFormat(TESTDATEFORMATSTRING);
      
      Date testDate = null;
      
      int result;
      
      // Test a date in January...we should get December of 1971
      try
      {
         testDate = sdf.parse(TESTDATESTRING2);
         
         // Set the current system time that will be used...
         DateTimeUtils.setCurrentMillisFixed(testDate.getTime());
         
         Date testDateLessOneMonth = DateTimeUtil.getPreviousMonth();
         
         result = DateTimeUtil.getMonthIndex(testDateLessOneMonth);
         
         System.out.println(
            "Test 1: Last Month Index for Date [" + TESTDATESTRING2 + "]: " + result);
         
         Assert.assertEquals(result, 12);
      }
      catch (ParseException pex)
      {
         Assert.fail("Found UNEXPECTED ParseException: " + pex.getMessage());
      }
   }
   
   @Test
   public void testGetYear()
   {
      SimpleDateFormat sdf = new SimpleDateFormat(TESTDATEFORMATSTRING);
      
      Date testDate = null;
      
      try
      {
         testDate = sdf.parse(TESTDATESTRING1);
         
         int result = DateTimeUtil.getYear(testDate);
         
         System.out.println(
            "Test 1: Last Year for Date [" + TESTDATESTRING1 + "]: " + result);
         
         Assert.assertEquals(result, 1972);
      }
      catch (ParseException pex)
      {
         Assert.fail("Found UNEXPECTED ParseException: " + pex.getMessage());
      }
   }
   
   @Test
   public void testGetYearFromSystemDate()
   {
      SimpleDateFormat sdf = new SimpleDateFormat(TESTDATEFORMATSTRING);
      
      Date testDate = null;
      
      try
      {
         testDate = sdf.parse(TESTDATESTRING1);

         // Set the current system time that will be used...
         DateTimeUtils.setCurrentMillisFixed(testDate.getTime());
         
         int result = DateTimeUtil.getYear();
         
         System.out.println(
            "Test 1: Last Year for Date [" + TESTDATESTRING1 + "]: " + result);
         
         Assert.assertEquals(result, 1972);
      }
      catch (ParseException pex)
      {
         Assert.fail("Found UNEXPECTED ParseException: " + pex.getMessage());
      }
      catch (Exception ex)
      {
         Assert.fail("Found UNEXPECTED Exception: " + ex.getMessage());
      }
   }
   
   @Test
   public void testValidateMonthIndexStringInput()
   {
      try
      {
         String testMonthIndexString = "7";
         
         int result = DateTimeUtil.validateMonthIndexStringInput(
            testMonthIndexString);
         
         Assert.assertEquals(result, 7);
      }
      catch (DateTimeUtilException duex)
      {
         Assert.fail("Found UNEXPECTED DateTimeUtilException: " +
            duex.getMessage());
      }
      
      // Test zero (0) padded month index string...
      try
      {
         String testMonthIndexString = "07";
         
         int result = DateTimeUtil.validateMonthIndexStringInput(
            testMonthIndexString);
         
         Assert.assertEquals(result, 7);
      }
      catch (DateTimeUtilException duex)
      {
         Assert.fail("Found UNEXPECTED DateTimeUtilException: " +
            duex.getMessage());
      }

      // Test invalid index NULL
      String testMonthIndexString = null;
      
      try
      {        
         DateTimeUtil.validateMonthIndexStringInput(
            testMonthIndexString);
         
         Assert.fail(
            "Expected DateTimeUtilException for invalid month index string!");
      }
      catch (DateTimeUtilException duex)
      {
         String message = duex.getMessage();
         
         System.out.println("Exception message: " + message);
         
         String expectedMessage =
            String.format(
               DateTimeUtil.errorMessageProperties.getString("INVALIDMONTHINDEX"),
               testMonthIndexString);
         
         System.out.println("Expected Exception message: " + expectedMessage);
         
         Assert.assertTrue(message.contains(expectedMessage));
      }
      
      // Test invalid index 0
      testMonthIndexString = "0";
      
      try
      {        
         DateTimeUtil.validateMonthIndexStringInput(
            testMonthIndexString);
         
         Assert.fail(
            "Expected DateTimeUtilException for invalid month index string!");
      }
      catch (DateTimeUtilException duex)
      {
         String message = duex.getMessage();
         
         System.out.println("Exception message: " + message);
         
         String expectedMessage =
            String.format(
               DateTimeUtil.errorMessageProperties.getString("INVALIDMONTHINDEX"),
               testMonthIndexString);
         
         System.out.println("Expected Exception message: " + expectedMessage);
         
         Assert.assertTrue(message.contains(expectedMessage));
      }
      
      // Test invalid index 13
      testMonthIndexString = "13";
      
      try
      {        
         DateTimeUtil.validateMonthIndexStringInput(
            testMonthIndexString);
         
         Assert.fail(
            "Expected DateTimeUtilException for invalid month index string!");
      }
      catch (DateTimeUtilException duex)
      {
         String message = duex.getMessage();
         
         System.out.println("Exception message: " + message);
         
         String expectedMessage =
            String.format(
               DateTimeUtil.errorMessageProperties.getString("INVALIDMONTHINDEX"),
               testMonthIndexString);
         
         System.out.println("Expected Exception message: " + expectedMessage);
         
         Assert.assertTrue(message.contains(expectedMessage));
      }
      
      // Test invalid index GO BLACKHAWKS!
      testMonthIndexString = "GO BLACKHAWKS!";
      
      try
      {        
         DateTimeUtil.validateMonthIndexStringInput(
            testMonthIndexString);
         
         Assert.fail(
            "Expected DateTimeUtilException for invalid month index string!");
      }
      catch (DateTimeUtilException duex)
      {
         String message = duex.getMessage();
         
         System.out.println("Exception message: " + message);
         
         String expectedMessage =
            String.format(
               DateTimeUtil.errorMessageProperties.getString("INVALIDMONTHINDEX"),
               testMonthIndexString);
         
         System.out.println("Expected Exception message: " + expectedMessage);
         
         Assert.assertTrue(message.contains(expectedMessage));
      }
   }
   
   @Test
   public void testValidateYearStringInput()
   {
      String testYearString = "2016";
      
      try
      {
         int result = DateTimeUtil.validateYearStringInput(
            testYearString);
         
         Assert.assertEquals(result, 2016);
      }
      catch (DateTimeUtilException duex)
      {
         Assert.fail("Found UNEXPECTED DateTimeUtilException: " +
            duex.getMessage());
      }
      
      // Test null year string...
      testYearString = null;
      
      try
      {
         DateTimeUtil.validateYearStringInput(
            testYearString);
         
         Assert.fail("Expected DateTimeUtilException due to NULL year string!");
      }
      catch (DateTimeUtilException duex)
      {
         String message = duex.getMessage();
         
         System.out.println("Exception message: " + message);
         
         String expectedMessage =
            String.format(
               DateTimeUtil.errorMessageProperties.getString("INVALIDYEARSTRING"),
               testYearString);
         
         System.out.println("Expected Exception message: " + expectedMessage);
         
         Assert.assertTrue(message.contains(expectedMessage));
      }
      
      // Test 0 year string...
      testYearString = "0";
      
      try
      {
         DateTimeUtil.validateYearStringInput(
            testYearString);
         
         Assert.fail("Expected DateTimeUtilException due to 0 year string!");
      }
      catch (DateTimeUtilException duex)
      {
         String message = duex.getMessage();
         
         System.out.println("Exception message: " + message);
         
         String expectedMessage =
            String.format(
               DateTimeUtil.errorMessageProperties.getString("INVALIDYEARSTRING"),
               testYearString);
         
         System.out.println("Expected Exception message: " + expectedMessage);
         
         Assert.assertTrue(message.contains(expectedMessage));
      }
      
      // Test ABC year string...
      testYearString = "ABC";
      
      try
      {
         DateTimeUtil.validateYearStringInput(
            testYearString);
         
         Assert.fail("Expected DateTimeUtilException due to invalid year " +
            "integer string!");
      }
      catch (DateTimeUtilException duex)
      {
         String message = duex.getMessage();
         
         System.out.println("Exception message: " + message);
         
         String expectedMessage =
            String.format(
               DateTimeUtil.errorMessageProperties.getString("INVALIDYEARSTRING"),
               testYearString);
         
         System.out.println("Expected Exception message: " + expectedMessage);
         
         Assert.assertTrue(message.contains(expectedMessage));
      }
   }
   
   @Test
   public void testConvertSecondsToHours()
   {
      double testHourSeconds = 7200;
      
      try
      {
         double hours = DateTimeUtil.convertSecondsToHours(testHourSeconds);
         
         System.out.println("Seconds: " + testHourSeconds + "; Hours: " + hours);
         
         Assert.assertEquals(hours, 2.0);
      }
      catch (DateTimeUtilException duex)
      {
         Assert.fail("Found UNEXPECTED DateTimeUtilException: " +
            duex.getMessage());
      }
   }
}
