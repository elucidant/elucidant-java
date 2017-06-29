/**
 *
 * Name:   InternetUser.java
 * Author: John Domingo
 *
 * This file contains the implementation of the InternetUser.
 *
 * Copyright 2016 by John Domingo
 *
 * This file is part of some open source application.
 * 
 * Some open source application is free software: you can redistribute 
 * it and/or modify it under the terms of the GNU General Public 
 * License as published by the Free Software Foundation, either 
 * userLogin 3 of the License, or (at your option) any later userLogin.
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

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 *
 * @author John Domingo
 *
 *
 */

public class InternetUser
{
   private String userId;
   private String userLoginName;
   private String userLogin;

   private double bandwidth;
   private double browseTime;
   
   private List<InternetURL> topTenURLByBrowseTime;
   private List<InternetURL> topTenURLByBandwidth;
   
   /**
    * 
    * Empty Constructor
    * 
    */
   
   public InternetUser()
   {
      this.setTopTenURLByBrowseTime(null);
      this.setTopTenURLByBandwidth(null);
   }
   
   /**
    * 
    * Constructor.
    *
    * @param userId String object to set the userId attribute to.
    * @param userLoginName String object to set the userLoginName attribute to.
    * @param userLogin String object to set the userLogin attribute to.
    * @param bandwidth Primitive double to set the bandwidth attribute to.
    * @param browseTime Primitive double to set the browseTime attribute to.
    *
    */
   
   public InternetUser(
      String userId,
      String userLoginName,
      String userLogin,
      double bandwidth,
      double browseTime)
   {
      this.userId = userId;
      this.userLoginName = userLoginName;
      this.userLogin = userLogin;
      this.bandwidth = bandwidth;
      this.browseTime = browseTime;
      
      this.setTopTenURLByBrowseTime(null);
      this.setTopTenURLByBandwidth(null);
   }
   
   /**
    * 
    * Constructor.
    *
    * @param userId String object to set the userId attribute to.
    * @param userLoginName String object to set the userLoginName attribute to.
    * @param userLogin String object to set the userLogin attribute to.
    * @param bandwidth Primitive double to set the bandwidth attribute to.
    * @param browseTime Primitive double to set the browseTime attribute to.
    * @param topTenURLByBandwidth List object containing the top ten URLs by
    *    bandwidth.
    * @param topTenURLByBrowseTime List object containing the top ten URLs by
    *    browse time.
    *
    */
   
   public InternetUser(
      String userId,
      String userLoginName,
      String userLogin,
      double bandwidth,
      double browseTime,
      List<InternetURL> topTenURLByBandwidth,
      List<InternetURL> topTenURLByBrowseTime)
   {
      this.userId = userId;
      this.userLoginName = userLoginName;
      this.userLogin = userLogin;
      this.bandwidth = bandwidth;
      this.browseTime = browseTime;
      
      this.setTopTenURLByBrowseTime(topTenURLByBrowseTime);
      this.setTopTenURLByBandwidth(topTenURLByBandwidth);
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
    * @param userId String object to set the userLoginName attribute to.
    *
    */
  
   public void setUserId(String userId)
   {
      this.userId = userId;
   }
   
   /**
    *
    * Retrieves the userLoginName attribute.
    *
    * @return String representing the userLoginName attribute.
    *
    */
   
   public String getUserLoginName()
   {
      return userLoginName;
   }

   /**
    * 
    * Sets the userLoginName attribute.
    *
    * @param userLoginName String object to set the userLoginName attribute to.
    *
    */
   
   public void setUserLoginName(String userLoginName)
   {
      this.userLoginName = userLoginName;
   }

   /**
    *
    * Retrieves the userLogin attribute.
    *
    * @return String representing the userLogin attribute.
    *
    */
   
   public String getUserLogin()
   {
      return userLogin;
   }

   /**
    * 
    * Sets the userLogin attribute.
    *
    * @param userLogin String object to set the userLogin attribute to.
    *
    */
   
   public void setUserLogin(String userLogin)
   {
      this.userLogin = userLogin;
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
    * Retrieves the topTenURLByBrowseTime attribute.
    *
    * @return List<InternetURL> object representing the topTenURLByBrowseTime
    *    attribute.
    *
    */
   
   public List<InternetURL> getTopTenURLByBrowseTime()
   {
      return topTenURLByBrowseTime;
   }

   /**
    * 
    * Sets the topTenURLByBrowseTime attribute.
    *
    * @param topTenURLByBrowseTime List<InternetURL> object to set the
    *    topTenURLByBrowseTime attribute to.
    *
    */
   
   public void setTopTenURLByBrowseTime(
      List<InternetURL> topTenURLByBrowseTime)
   {
      if (topTenURLByBrowseTime == null)
      {
         this.topTenURLByBrowseTime = new ArrayList<InternetURL>();
      }
      else
      {
         this.topTenURLByBrowseTime = topTenURLByBrowseTime;
      }
   }

   /**
    *
    * Retrieves the topTenURLByBandwidth attribute.
    *
    * @return List<InternetURL> object representing the topTenURLByBandwidth
    *    attribute.
    *
    */
   public List<InternetURL> getTopTenURLByBandwidth()
   {
      return topTenURLByBandwidth;
   }

   /**
    * 
    * Sets the topTenURLByBandwidth attribute.
    *
    * @param topTenURLByBandwidth List<InternetURL> object to set the
    *    topTenURLByBandwidth attribute to.
    *
    */
   
   public void setTopTenURLByBandwidth(List<InternetURL> topTenURLByBandwidth)
   {
      if (topTenURLByBandwidth == null)
      {
         this.topTenURLByBandwidth = new ArrayList<InternetURL>();
      }
      else
      {
         this.topTenURLByBandwidth = topTenURLByBandwidth;
      }
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
         append(userLoginName).
         append(userLogin).
         append(bandwidth).
         append(browseTime).
         append(topTenURLByBrowseTime).
         append(topTenURLByBandwidth).
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
            final InternetUser other = (InternetUser)obj;
            
            result = new EqualsBuilder().
               append(userId, other.userId).
               append(userLoginName, other.userLoginName).
               append(userLogin, other.userLogin).
               append(bandwidth, other.bandwidth).
               append(browseTime, other.browseTime).
               append(topTenURLByBrowseTime, other.topTenURLByBrowseTime).
               append(topTenURLByBandwidth, other.topTenURLByBandwidth).
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
      
      sb.append("InternetUser:\n").
         append("   userId: " + userId + "\n").
         append("   userLoginName: " + userLoginName + "\n").
         append("   userLogin: " + userLogin + "\n").
         append("   bandwidth: " + String.format("%-10.3f", bandwidth) + "\n").
         append("   browseTime: " + String.format("%-10.3f", browseTime) + "\n");
      
      sb.append("\nTop 10 URLs By Browse Time:\n");
      
      if (topTenURLByBrowseTime.size() > 0)
      {           
         for (int i = 0; i < topTenURLByBrowseTime.size(); i++)
         {
            sb.append("\n").append(topTenURLByBrowseTime.get(i));
         }
      }
      else
      {
         sb.append("NONE\n");
      }
      
      sb.append("\nTop 10 URLs By Bandwidth:\n");
      
      if (topTenURLByBandwidth.size() > 0)
      {  
         for (int i = 0; i < topTenURLByBandwidth.size(); i++)
         {
            sb.append("\n").append(topTenURLByBandwidth.get(i));
         }
      }
      else
      {
         sb.append("NONE\n");
      }
      
      return sb.toString();
   }
}
