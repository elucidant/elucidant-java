/**
 *
 * Name:   TrustAllX509TrustManager.java
 * Author: John Domingo
 *
 * This file contains the implementation of the TrustAllX509TrustManager.
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

package org.elucidant.net.ssl;

import java.security.cert.X509Certificate;

import javax.net.ssl.X509TrustManager;

/**
 *
 * @author John Domingo
 *
 *
 */

public class TrustAllX509TrustManager
   implements X509TrustManager
{
   public X509Certificate[] getAcceptedIssuers()
   {
      return new X509Certificate[0];
   }

   public void checkClientTrusted(
      java.security.cert.X509Certificate[] certs,
      String authType)
   {
   }

   public void checkServerTrusted(
      java.security.cert.X509Certificate[] certs,
      String authType)
   {
   }
}
