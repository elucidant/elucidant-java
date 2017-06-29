/**
 *
 * Name:   InternetURL.java
 * Author: John Domingo
 *
 * This file contains the implementation of the InternetURL.
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

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 *
 * @author John Domingo
 *
 *
 */

public class InternetURL
{
   private String userId;
   private String urlName;
   
   private double bandwidth;
   private double browseTime;
   
   /**
    * 
    * Empty Constructor
    * 
    */
   
   public InternetURL()
   {
   }
   
   /**
    * 
    * Constructor.
    *
    * @param userId String object to set the userId attribute to.
    * @param urlName String object to set the urlName attribute to.
    * @param bandwidth Primitive double to set the bandwidth attribute to.
    * @param browseTime Primitive double to set the browseTime attribute to.
    *
    */
   
   public InternetURL(
      String userId,
      String urlName,
      double bandwidth,
      double browseTime)
   {
      this.userId = userId;
      this.urlName = urlName;
      this.bandwidth = bandwidth;
      this.browseTime = browseTime;
   }
   
   /**
    *
    * Retrieves the userId attribute.
    *
    * @return String representing the userId attribute.
    *
    */
  
   public String getUserId()
   {
      return userId;
   }

   /**
    * 
    * Sets the userId attribute.
    *
    * @param userId String object to set the userId attribute to.
    *
    */
  
   public void setUserId(String userId)
   {
      this.userId = userId;
   }
   
   /**
    *
    * Retrieves the urlName attribute.
    *
    * @return String representing the urlName attribute.
    *
    */
   
   public String getUrlName()
   {
      return urlName;
   }

   /**
    * 
    * Sets the urlName attribute.
    *
    * @param urlName String object to set the urlName attribute to.
    *
    */
   
   public void setUrlName(String urlName)
   {
      this.urlName = urlName;
   }

   /**
    *
    * Retrieves the bandwidth attribute.
    *
    * @return Primitive double representing the bandwidth attribute.
    *
    */
   
   public double getBandwidth()
   {
      return bandwidth;
   }

   /**
    * 
    * Sets the bandwidth attribute.
    *
    * @param bandwidth Primitive double to set the bandwidth attribute to.
    *
    */
   
   public void setBandwidth(double bandwidth)
   {
      this.bandwidth = bandwidth;
   }

   /**
    *
    * Retrieves the browseTime attribute.
    *
    * @return Primitive double representing the browseTime attribute.
    *
    */
   
   public double getBrowseTime()
   {
      return browseTime;
   }

   /**
    * 
    * Sets the browseTime attribute.
    *
    * @param browseTime Primitive double to set the browseTime attribute to.
    *
    */
   
   public void setBrowseTime(double browseTime)
   {
      this.browseTime = browseTime;
   }
   
   /**
    * 
    * This method computes a hash code for this class.
    *
    * @see java.lang.Object#hashCode()
    * 
    * @return Primitive int representing the calculated hash code.
    *
    */
   
   @Override
   public int hashCode()
   {
      return new HashCodeBuilder().
         append(userId).
         append(urlName).
         append(bandwidth).
         append(browseTime).
         toHashCode();
   }
   
   /**
    * 
    * This method will determine the equality of this object with another
    * object.
    *
    * @see java.lang.Object#equals(java.lang.Object)
    * 
    * @return Primitive boolean TRUE if the objects are equal; FALSE otherwise.
    *
    */

   @Override
   public boolean equals(Object obj)
   {
      boolean result = false;

      if (obj == this)
      {
         result = true;
      }
      else
      {
         if ((obj != null) && (obj instanceof InternetUser))
         {
            final InternetURL other = (InternetURL)obj;
            
            result = new EqualsBuilder().
               append(userId, other.userId).
               append(urlName, other.urlName).
               append(bandwidth, other.bandwidth).
               append(browseTime, other.browseTime).
               isEquals();
         }
      }

      return result;
   }

   /**
    *
    * This method will generate a string representation of the object.
    *
    * @see java.lang.Object#toString()
    * 
    * @return String object generated.
    *
    */
   
   @Override
   public String toString()
   {
      StringBuilder sb = new StringBuilder();
      
      sb.append("Internet URL:\n").
         append("   userId: " + userId + "\n").
         append("   urlName: " + urlName + "\n").
         append("   bandwidth: " + String.format("%-10.3f", bandwidth) + "\n").
         append("   browseTime: " + String.format("%-10.3f", browseTime));
      
      return sb.toString();
   }
}
