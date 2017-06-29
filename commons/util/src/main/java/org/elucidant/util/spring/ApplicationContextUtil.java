/**
 *
 * Name:   ApplicationContextUtil.java
 * Author: John Domingo
 *
 * This file contains the implementation of the ApplicationContextUtil.
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

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * This class implements ApplicationContextAware and provides the
 * setApplicationContext() method which will be invoked by Spring container and
 * the application context will be passed to it.  It store the application
 * context as a static variable and exposes it through a getter method so that
 * it can be accessed all throughout a Java stand-alone application.
 * 
 * @author John Domingo
 *
 */

public class ApplicationContextUtil
   implements ApplicationContextAware
{
   private static ApplicationContext appContext;

   @Override
   public void setApplicationContext(ApplicationContext applicationContext)
      throws BeansException
   {
      appContext = applicationContext;
   }

   /**
    * 
    * This method will retrieve the current Application Context.
    * The ApplicationContextUtil class must be instantiated first for the
    * Spring Application Context Awareness to be successful.
    * 
    * @return ApplicationContext object established.
    *
    */
   
   public static ApplicationContext getApplicationContext()
   {
      return appContext;
   }
   
   /**
    * 
    * This method will retrieve the current Application Context.  If one is not
    * currently defined, it will use the context file name passed in to
    * establish the Application Context based on whether it can be found on the
    * classpath.
    *
    * @param applicationContextFileString String object representing the name
    *    of the application context file to find on the classpath.
    *    
    * @return ApplicationContext object established.
    *
    */
   
   public static ApplicationContext getApplicationContext(
      String applicationContextFileString)
   {
      if ((applicationContextFileString != null) &&
         (applicationContextFileString.equals("") == false))
      {
         // This should trigger the Application Context Aware class to get set!
         new ClassPathXmlApplicationContext(
            applicationContextFileString);
      }

      return appContext;
   }
}
