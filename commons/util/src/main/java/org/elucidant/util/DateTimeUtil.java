/**
 *
 * Name:   DateTimeUtil.java
 * Author: John Domingo
 *
 * This file contains the implementation of the DateTimeUtil.
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

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.ResourceBundle;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.elucidant.util.lang.ByteUtilException;

/**
 *
 * @author John Domingo
 *
 *
 */

public class DateTimeUtil
{
   private static Logger LOGGER =
      LoggerFactory.getLogger(DateTimeUtil.class);
   
   public static ResourceBundle errorMessageProperties = null;
   
   public final static long SECONDS_IN_MINUTE = 60;
   public final static long SECONDS_IN_HOUR = SECONDS_IN_MINUTE * 60;
   public final static long SECONDS_IN_DAY = SECONDS_IN_HOUR * 24;
   
   static
   {
      errorMessageProperties =
         ResourceBundle.getBundle("util_exception_messages");
   }
   
   /**
    * 
    * This utility method returns the month index (1 based, starting with
    * January based on the date passed in.
    * 
    * @param date Date object representing the date to retrieve the month
    * index from.
    *
    * @return Primitive integer representing the current date's month index.
    *
    */
   
   public static int getMonthIndex(Date date)
   {
      Calendar cal = Calendar.getInstance();
      
      cal.setTime(date);
      
      LOGGER.debug("Date: " + date);
      
      // Need to add one more since the month index is ZERO based.
      return cal.get(Calendar.MONTH) + 1;
   }

   /**
    * 
    * This utility method returns the month index (1 based, starting with
    * January based on the current system date.
    *
    * @return Primitive integer representing the current date's month index.
    *
    */
   
   public static int getMonthIndex()
   {
      DateTime today = new DateTime();
      
      return getMonthIndex(today.toDate());
   }
   
   public static Date getPreviousMonth(Date date)
   {
      Calendar cal = Calendar.getInstance();
      
      cal.setTime(date);
      
      cal.add(Calendar.MONTH, -1);
      
      return cal.getTime();
   }
   
   public static Date getPreviousMonth()
   {
      DateTime today = new DateTime();
      
      return DateTimeUtil.getPreviousMonth(today.toDate());
   }
   
   public static int getYear(Date date)
   {      
      Calendar cal = Calendar.getInstance();
      
      cal.setTime(date);
      
      LOGGER.debug("Date: " + date);
      
      return cal.get(Calendar.YEAR);
   }
   
   public static int getYear()
   {
      DateTime today = new DateTime();
      
      LOGGER.debug("In getYear(), Date: " + today.toDate());
      
      return getYear(today.toDate());
   }
   
   /**
    * 
    * This method will validate a month index string passed in and return the
    * primitive integer value of the month index string.  This will value will
    * be between 1 (January) and 12 (December).
    *
    *
    * @param monthIndexString String object representing the month index string
    *    to validate and convert to a primitive integer.
    * 
    * @return Primitive integer representing the integer version of the month
    *    index string passed in.
    *    
    * @throws DateTimeUtilException thrown if an error/exception occurs during the
    *    validation of the month index string.
    *
    */
   public static int validateMonthIndexStringInput(String monthIndexString)
      throws DateTimeUtilException
   {
      int monthIndex = 0;
      
      try
      {
         LOGGER.debug("In validateMonthIndexStringInput, month index string: " +
            monthIndexString);
         
         if (monthIndexString != null)
         {
            monthIndex = Integer.parseInt(monthIndexString);
            
            if ((monthIndex < 1) || (monthIndex > 12))
            {
               // In this case, report the exception and throw out!
               throw new DateTimeUtilException(
                  String.format(
                     errorMessageProperties.getString("INVALIDMONTHINDEX"),
                     monthIndexString));
            }
         }
         else
         {
            // In this case, report the exception and throw out!
            throw new DateTimeUtilException(
               String.format(
                  errorMessageProperties.getString("INVALIDMONTHINDEX"),
                  monthIndexString));
         }
      }
      catch (NumberFormatException nfex)
      {
         // In this case, report the exception and throw out!
         throw new DateTimeUtilException(
            String.format(
               errorMessageProperties.getString("INVALIDMONTHINDEX"),
               monthIndexString));
      }
      
      return monthIndex;
   }
   
   /**
    * 
    * This method will validate a year string passed in and return the
    * primitive integer value of the year string.
    *
    * @param yearString String object representing the year string to validate
    *    and convert to a primitive integer.
    * 
    * @return Primitive integer representing the integer version of the year
    *    string passed in.
    *    
    * @throws DateTimeUtilException thrown if an error/exception occurs during the
    *    validation of the year string.
    *
    */
   public static int validateYearStringInput(String yearString)
      throws DateTimeUtilException
   {
      int year = 0;
      
      try
      {
         if (yearString != null)
         {
            year = Integer.parseInt(yearString);
            
            if (year < 1)
            {
               // In this case, report the exception and throw out!
               throw new DateTimeUtilException(
                  String.format(
                     errorMessageProperties.getString("INVALIDYEARSTRING"),
                     yearString));
            }
         }
         else
         {
            // In this case, report the exception and throw out!
            throw new DateTimeUtilException(
               String.format(
                  errorMessageProperties.getString("INVALIDYEARSTRING"),
                  yearString));
         }
      }
      catch (NumberFormatException nfex)
      {
         // In this case, report the exception and throw out!
         throw new DateTimeUtilException(
            String.format(
               errorMessageProperties.getString("INVALIDYEARSTRING"),
               yearString));
      }
      
      return year;
   }
   
   public static double convertSecondsToHours(double seconds)
      throws ByteUtilException
   {
      double hours = 0;
      
      if (seconds >= 0)
      {
         if (seconds > 0)
         {
            hours = seconds / SECONDS_IN_HOUR;
         }
      }
      else
      {
         String errorMessage =
            String.format(
               errorMessageProperties.getString("INVALIDSECONDSSPECIFIEDNEGATIVE"),
            Double.toString(seconds));
         
         LOGGER.error(errorMessage);
         
         throw new DateTimeUtilException(errorMessage);
      }
      
      return hours;
   }
   
   public static int getMonthEndDate(int month, int year)
   {
      Calendar cal = new GregorianCalendar(year, month, 0);
      
      if (LOGGER.isDebugEnabled() == true)
      {
         Date date = cal.getTime();
         
         DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
         
         LOGGER.debug("Month End Date Calculated: " + sdf.format(date));
      }
      
      return cal.get(Calendar.DAY_OF_MONTH);
   }
}
