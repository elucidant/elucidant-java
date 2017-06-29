/**
 *
 * Name:   JDBCUtil.java
 * Author: John Domingo
 *
 * This file contains the implementation of the JDBCUtil.
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

package org.elucidant.util.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author John Domingo
 *
 *
 */

public class JDBCUtil
{
   private static Logger LOGGER =
      LoggerFactory.getLogger(JDBCUtil.class);
   
   public static Connection createJDBCConnection(
      String url,
      String driver,
      String userName,
      String password)
      throws ClassNotFoundException, SQLException
   {
      Connection conn = null;
      
      Class.forName(driver);
      
      conn = DriverManager.getConnection(url, userName, password);

      LOGGER.debug("Connected to the database...");
      
      return conn;
   }
   
   /**
    * 
    * Used to log JDBC connection information.
    *
    * @param url String object representing the JDBC URL to use for
    *    generating a connection to a database.
    * @param driverClassName String object representing the JDBC driver class
    *    to use to generate a connection to a database.
    * @param userName String object representing the user name to use to create
    *    a connection to a database.
    * @param password String object representing the password to use to create
    *    a connection to a database.
    *    
    */
   
   public static void logJDBCConnectionInformation(
      final String url,
      final String driverClassName,
      final String userName,
      final String password)
   {
      StringBuffer sb = new StringBuffer();
      
      sb.append("JDBC Connection Information:\n").
         append("   JDBC Connection String: [").append(url).
            append("]\n").
         append("   JDBC Driver: [").append(driverClassName).append("]\n").
         append("   JDBC User Name: [").append(userName).append("]\n").
         append("   JDBC User Password: [").append(password).append("]\n");
      
      LOGGER.debug(sb.toString());
   }
   
}
