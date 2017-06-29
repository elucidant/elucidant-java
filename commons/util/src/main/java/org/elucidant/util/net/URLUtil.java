/**
 *
 * Name:   URLUtil.java
 * Author: John Domingo
 *
 * This file contains the implementation of the URLUtil.
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

package org.elucidant.util.net;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author John Domingo
 *
 *
 */

public class URLUtil
{
//   public final static String URLREGEX =
//      "((https?|ftp|gopher|telnet|file):" +
//      "((//)|(\\\\))+[\\w\\d:#@%/;$()~_?\\+-=\\\\\\.&]*)";
   
   public final static String URLREGEX =
      "\\b((?:https?|ftp|file):" +
      "//[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|])";
      
   public static List<String> extractUrls(String text)
   {
       List<String> containedUrls = new ArrayList<String>();
       
       Pattern pattern = Pattern.compile(URLREGEX, Pattern.CASE_INSENSITIVE);
       
       Matcher urlMatcher = pattern.matcher(text);

       while (urlMatcher.find())
       {
          containedUrls.add(text.substring(urlMatcher.start(0),
             urlMatcher.end(0)));
       }

       return containedUrls;
   }
}
