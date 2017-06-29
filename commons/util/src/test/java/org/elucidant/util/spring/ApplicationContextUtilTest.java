/**
 *
 * Name:   ApplicationContextUtilTest.java
 * Author: John Domingo
 *
 * This file contains the implementation of the ApplicationContextUtilTest.
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

package org.elucidant.util.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 *
 * @author John Domingo
 *
 *
 */

public class ApplicationContextUtilTest
{
   private final static String TESTBEANSTRING1 = "This is a test!";
   private final static String TESTBEANSTRING2 = "This is a test too!";
   
   static
   {
      // This should trigger the Application Context Aware class to get set!
      new ClassPathXmlApplicationContext("testApplicationContext.xml");
   }
   
   @Test
   public void testGetApplicationContext()
   {
      try
      {
         ApplicationContext appContext =
            ApplicationContextUtil.getApplicationContext();
         
         String result = (String)appContext.getBean("testString");
         
         System.out.println("testString from Application Context: " + result);
         
         Assert.assertEquals(result, TESTBEANSTRING1);
      }
      catch (Exception ex)
      {
         Assert.fail("Found UNEXPECTED Exception: " + ex.getMessage());
      }
   }
   
   @Test
   public void testGetApplicationContextWithAppContext()
   {
      try
      {
         ApplicationContext appContext =
            ApplicationContextUtil.getApplicationContext(
               "testApplicationContext2.xml");
         
         String result = (String)appContext.getBean("testString2");
         
         System.out.println("testString2 from Application Context: " + result);
         
         Assert.assertEquals(result, TESTBEANSTRING2);
      }
      catch (Exception ex)
      {
         Assert.fail("Found UNEXPECTED Exception: " + ex.getMessage());
      }
   }
   
   @Test
   public void testGetApplicationContextAppContextDoesNotExist()
   {
      try
      {
         ApplicationContextUtil.getApplicationContext(
            "testApplicationContext3");
         
         Assert.fail("Expected Exception due to invalid Application Context file!");
      }
      catch (Exception ex)
      {
         System.out.println("Found EXPECTED Excpetion: " + ex.getMessage());
         
         Assert.assertTrue(true);
      }
   }
}
