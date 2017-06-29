/**
 *
 * Name:   IPUtil.java
 * Author: John Domingo
 *
 * This file contains the implementation of the IPUtil.
 *
 * Copyright 2017 by John Domingo
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
import java.util.ResourceBundle;

import org.apache.commons.net.util.SubnetUtils;
import org.elucidant.util.lang.ByteUtilException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author John Domingo
 *
 *
 */

public class IPUtil
{
   private static Logger LOGGER =
      LoggerFactory.getLogger(IPUtil.class);
   
   public static ResourceBundle errorMessageProperties = null;
   
   static
   {
      errorMessageProperties =
         ResourceBundle.getBundle("util_exception_messages");
   }
   
   /**
    * 
    * This method retrieves the list of IPS for a given CIDR IP address and
    * routing prefix.
    * 
    * Example 1: 192.168.100.14/24
    * This represents the IPv4 address 192.168.100.14 and its associated
    * routing prefix 192.168.100.0, or equivalently, its subnet mask
    * 255.255.255.0, which has 24 leading 1-bits.
    * 
    * Example 2: 192.168.100.0/22
    * This represents the 1024 IPv4 addresses from 192.168.100.0 to
    * 192.168.103.255.
    *
    * Example 3: 2001:db8::/48
    * This represents the block of IPv6 addresses from 2001:db8:0:0:0:0:0:0 to
    * 2001:db8:0:ffff:ffff:ffff:ffff:ffff
    * 
    * @param subNetString
    * 
    * @return
    *
    */
   
   public static String[] getListOfIPsFromCIDR(String cidrNotationIP)
   {
      LOGGER.debug("Getting List of IPs from Subnet: " + cidrNotationIP);
      
      String[] ips = null; 
         
      if (cidrNotationIP != null)
      {
         SubnetUtils utils = new SubnetUtils(cidrNotationIP);
         
         ips = utils.getInfo().getAllAddresses();
      }
      
      return ips;
   }
   
   public static String[] getListOfIPs(String ipAddress, String subNetMask)
   {
      LOGGER.debug(
         "Getting List of IPs from IP [" + ipAddress +
         "] and Subnet Mask [" + subNetMask + "]");
      
      String[] ips = null; 
         
      if ((ipAddress != null) && (subNetMask != null))
      {
         SubnetUtils utils = new SubnetUtils(ipAddress, subNetMask);
         
         ips = utils.getInfo().getAllAddresses();
      }
      
      return ips;
   }
   
   public static String[] getListFromRange(String ip)
   {
      //117.211.141-147.20-218
      String[] ipSegments = ip.split("\\.");    //split into 4 segments
      
      if (ipSegments.length != 4)
      {
         String errorMessage =
            String.format(
               errorMessageProperties.getString("INVALIDIPV4ADDRESSS"),
               ip);
         
         LOGGER.error(errorMessage);
         
         throw new ByteUtilException(errorMessage);
      }
      
      // Check if there is commas in the string...
      String[] commaDelimitedStrings = ip.split(",");
      
      String[] ips = null;
      
      if (commaDelimitedStrings.length > 1)
      {
         ips = getListFromRangeWithComma(ipSegments);
      }
      else
      {
         ips = getListFromRangeNoComma(ipSegments);
      }
      
      return ips;
   }
   
   private static String[] getListFromRangeNoComma(String[] ipSegments)
   {
      int seg1Lower;
      int seg1Upper;
      int seg2Lower;
      int seg2Upper;
      int seg3Lower;
      int seg3Upper;
      int seg4Lower;
      int seg4Upper;
      
      LOGGER.debug("Processing IP segments that have no comma(s)...");

      // get lower and upper bound of 1st segment
      String[] seg1 = ipSegments[0].split("-");
      
      if (seg1.length == 1)
      {
         seg1Lower = Integer.parseInt(seg1[0]);
         seg1Upper = Integer.parseInt(seg1[0]);
      }
      else
      {
         seg1Lower = Integer.parseInt(seg1[0]);
         seg1Upper = Integer.parseInt(seg1[1]);
      }

      // get lower and upper bound of 2nd segment
      String[] seg2 = ipSegments[1].split("-");
      
      if (seg2.length == 1)
      {
         seg2Lower = Integer.parseInt(seg2[0]);
         seg2Upper = Integer.parseInt(seg2[0]);
      }
      else
      {
         seg2Lower = Integer.parseInt(seg2[0]);
         seg2Upper = Integer.parseInt(seg2[1]);
      }

      // get lower and upper bound of 3rd segment
      String[] seg3 = ipSegments[2].split("-");
      
      if (seg3.length == 1)
      {
         seg3Lower = Integer.parseInt(seg3[0]);
         seg3Upper = Integer.parseInt(seg3[0]);
      }
      else
      {
         seg3Lower = Integer.parseInt(seg3[0]);
         seg3Upper = Integer.parseInt(seg3[1]);
      }

      // get lower and upper bound of 4th segment
      String[] seg4 = ipSegments[3].split("-");
      
      if (seg4.length == 1)
      {
         seg4Lower = Integer.parseInt(seg4[0]);
         seg4Upper = Integer.parseInt(seg4[0]);
      }
      else
      {
         seg4Lower = Integer.parseInt(seg4[0]);
         seg4Upper = Integer.parseInt(seg4[1]);
      }

      //generate all IPs
      List<String> ips = new ArrayList<>();
      
      for (int i = seg1Lower; i <= seg1Upper; i++)
      {
         for (int j = seg2Lower; j <= seg2Upper; j++)
         {
            for (int k = seg3Lower; k <= seg3Upper; k++)
            {
               for (int l = seg4Lower; l <= seg4Upper; l++)
               {
                  ips.add(i + "." + j + "." + k + "." + l);
               }
            }
         }
      }
      
      return ips.toArray(new String[0]);
   }
   
   private static String[] getListFromRangeWithComma(String[] ipSegments)
      throws IPUtilException
   {
      List<Integer> segment1bits = processSegment(ipSegments[0]);
      List<Integer> segment2bits = processSegment(ipSegments[1]);
      List<Integer> segment3bits = processSegment(ipSegments[2]);
      List<Integer> segment4bits = processSegment(ipSegments[3]);
      
      LOGGER.debug("Processing IP segments that have comma(s)...");
      
      //generate all IPs
      List<String> ips = new ArrayList<>();
      
      for (int i = 0; i < segment1bits.size(); i++)
      {
         for (int j = 0; j < segment2bits.size(); j++)
         {
            for (int k = 0; k < segment3bits.size(); k++)
            {
               for (int l = 0; l < segment4bits.size(); l++)
               {
                  ips.add(i + "." + j + "." + k + "." + l);
               }
            }
         }
      }

      return ips.toArray(new String[0]);
   }
   
   public static List<Integer> processSegment(String segment)
   {
      // Each segment can use as much as 256 "bits" (0 - 255)
      // Need to fill in the bits based on the segment specified
      // Examples of entries:
      // 1-255 : 255 bits
      // 1-5, 4-10, 100 : 11 bits
      // 1, 2, 3, 4 : 4 bits
      // 128 : 1 bit
      
      List<Integer> segmentBitsList = new ArrayList<Integer>();
      
      String[] segmentGroups = segment.split(",");
      
      List<Integer> segmentGroupBitsList = null;
      
      LOGGER.debug("Processing Segment [" + segment + "]");
      
      for (int i = 0; i < segmentGroups.length; i++)
      {
         segmentGroupBitsList = processSegmentGroup(segmentGroups[i]);
         
         for (Integer j : segmentGroupBitsList)
         {
            // We DO NOT want to add redundant entries if defined!
         
            if (!segmentBitsList.contains(j))
            {
               segmentBitsList.add(j);
            }
         }
      }
      
      if (LOGGER.isDebugEnabled() == true)
      {
         for (int i = 0; i < segmentBitsList.size(); i++)
         {
            LOGGER.debug("Bit List[" + i + "]: " + segmentBitsList.get(i));
         }
      }
      
      return segmentBitsList;
   }
   
   public static List<Integer> processSegmentGroup(String segmentGroup)
   {
      List<Integer> bitsList = new ArrayList<Integer>();
      
      // get lower and upper bound of 2nd segment
      String[] partialSegment = segmentGroup.split("-");
      
      int partialSegmentLower;
      int partialSegmentUpper;
      
      if (partialSegment.length == 1)
      {
         partialSegmentLower = Integer.parseInt(partialSegment[0]);
         partialSegmentUpper = Integer.parseInt(partialSegment[0]);
      }
      else
      {
         partialSegmentLower = Integer.parseInt(partialSegment[0]);
         partialSegmentUpper = Integer.parseInt(partialSegment[1]);
      }
      
      LOGGER.debug("Processing Segment Group[" + segmentGroup + "]");
      
      for (int i = partialSegmentLower; i <= partialSegmentUpper; i++)
      {
         bitsList.add(i);
      }
      
      
      
      return bitsList;
   }
}
