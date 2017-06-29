/**
 *
 * Name:   BaseApp.java
 * Author: John Domingo
 *
 * This file contains the implementation of the BaseApp.
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

package org.elucidant.app;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.ResourceBundle;

import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author John Domingo
 *
 *
 */

public abstract class BaseApp
{
   private static Logger LOGGER =
      LoggerFactory.getLogger(BaseApp.class);

   public static final String JAVACMDLINE = "java -cp <JAR>";
   
   protected static String APPVERSION;
   protected static String APPVERSIONDESCRIPTION;
   protected static String VERSIONDESCRIPTION;
   protected static String HELPDESCRIPTION;
   protected static String CMDLINEINVOKATION;
   protected static String INVALIDOPTIONERRORMESSAGE;
   
   protected static Options adminOptions;
   
   protected String appMessage;
   
   public static void initializeApplicationConstants(
      String appClassName,
      ResourceBundle messageBundle,
      ResourceBundle errorMessageBundle)
   {
      LOGGER.debug("In BaseApp initializeApplicationConstants()...");
      
      // Initialize Application Messages
      APPVERSION = messageBundle.getString("APPVERSION");
      APPVERSIONDESCRIPTION = messageBundle.getString("APPVERSIONDESCRIPTION");
      VERSIONDESCRIPTION = messageBundle.getString("VERSIONDESCRIPTION");
      HELPDESCRIPTION = messageBundle.getString("HELPDESCRIPTION");
      
      CMDLINEINVOKATION = JAVACMDLINE + " " + appClassName;
      
      // Initialize Error Messages
      INVALIDOPTIONERRORMESSAGE =
         errorMessageBundle.getString("INVALIDOPTIONERRORMESSAGE");
      
      // Generate ADMIN Options
      adminOptions = new Options();
      
      adminOptions.addOption("h", false, HELPDESCRIPTION);
      adminOptions.addOption("v", "version", false, VERSIONDESCRIPTION);
   }
   
   /**
    *
    * Retrieves the appMessage attribute.
    *
    * @return String representing the appMessage attribute.
    *
    */
   
   public String getAppMessage()
   {
      return appMessage;
   }

   /**
    * 
    * Sets the appMessage attribute.
    *
    * @param appMessage String object to set the appMessage attribute to.
    *
    */
   
   public void setAppMessage(String appMessage)
   {
      this.appMessage = appMessage;
   }

   protected static void printUsage(
      final String appName,
      final int maxCharsPerLine,
      final Options options,
      final OutputStream os)
   {
      PrintWriter pw = new PrintWriter(os);
      
      HelpFormatter hf = new HelpFormatter();
      
      hf.printUsage(pw, maxCharsPerLine, appName, options);
   }
   
   /**
    * 
    * Print the help for options with the specified command line syntax.
    *
    * @param maxCharsPerLine Primitive integer representing the number of
    *    characters to be displayed per line.
    * @param cmdLineSyntax String object representing the syntax for this
    *    application.
    * @param header String object representing the banner to display at the
    *    beginning of the help.
    * @param options Options object containing the application options.
    * @param leftPad Primitive integer representing the number of characters
    *    of padding to be prefixed to each line.
    * @param descPad Primitive integer representing the number of characters
    *    of padding to be prefixed to each description line.
    * @param footer String object representing the banner to display at the end
    *    of the help.
    * @param autoUsage Primitive boolean TRUE indicating that to print the
    *    usage statement; FALSE otherwise.
    * @param os OutputStream object to write result output to.
    *
    */
   
   protected static void printHelp(
      int maxCharsPerLine,
      String cmdLineSyntax,
      String header,
      Options options,
      int leftPad,
      int descPad,
      String footer,
      boolean autoUsage,
      OutputStream os)
   {
      PrintWriter pw = new PrintWriter(os);
      
      HelpFormatter hf = new HelpFormatter();
      
      hf.printHelp(
         pw,
         maxCharsPerLine,
         cmdLineSyntax,
         header,
         options,
         leftPad,
         descPad,
         footer,
         autoUsage);
      
      pw.flush();
   }
   
   protected static void printLines(
      int lineCount,
      OutputStream os)
   {
      try
      {
         for (int i = 0; i < lineCount; i++)
         {
            os.write("\n".getBytes());
         }
      }
      catch (IOException ioex)
      {
         for (int i = 0; i < lineCount; i++)
         {
            System.out.println();
         }
      }
   }
   
   protected static void printVersion()
   {
      String version = String.format(APPVERSIONDESCRIPTION, APPVERSION);
      
      LOGGER.debug(version);
      
      System.out.println(version);
   }
   
   public static boolean optionFound(String[] args, String option)
   {
      return Arrays.asList(args).contains(option);
   }

   public boolean processAdministrativeOptions(String[] args)
   {
      boolean administrativeOptionFound = false;
      
      if ((args != null) && (args.length > 0))
      {
         if (optionFound(args, "-h") == true)
         {
            printHelp();
            
            administrativeOptionFound = true;
         }
         else if ((optionFound(args, "-v") == true) ||
            optionFound(args, "--version"))
         {
            printVersion();
            
            administrativeOptionFound = true;
         }
      }
      
      return administrativeOptionFound;
   }
   
   public void appExit(int exitCode, String message)
   {
      this.appMessage = message;
      
      System.exit(exitCode);
   }
   
   abstract public void printHelp();
}
