/**
 *
 * Name:   EmailUtilTest.java
 * Author: John Domingo
 *
 * This file contains the implementation of the EmailUtilTest.
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

package org.elucidant.util.mail;

import java.util.List;
import java.util.ResourceBundle;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.itextpdf.text.log.Logger;

import microsoft.exchange.webservices.data.core.ExchangeService;
import microsoft.exchange.webservices.data.core.service.item.EmailMessage;

/**
 *
 * @author John Domingo
 *
 *
 */

public class EmailUtilTest
{
   public static String TESTATTACHMENTFILE1 = null;
   public static String TESTSMTPHOST = null;
   public static String TESTSENDER = null;
   public static String TESTSENDERNAME = null;
   public static String TESTRECEIVER = null;
   
   static
   {
      ResourceBundle properties = ResourceBundle.getBundle("testresources");
      
      TESTATTACHMENTFILE1 = properties.getString("TESTATTACHMENTFILE1");
      
      TESTSMTPHOST = properties.getString("TESTSMTPHOST");
      TESTSENDER = properties.getString("TESTSENDER");
      TESTSENDERNAME = properties.getString("TESTSENDERNAME");
      TESTRECEIVER = properties.getString("TESTRECEIVER");
      TESTSMTPHOST = properties.getString("TESTSMTPHOST");
   }
   
   /**
    * 
    * This tests the sending of an email with attachment.
    *
    * NOTE: Disabling this test method since we need to mock the use of an
    * SMTP server.  For now, this can be used for testing purposes against a
    * real SMTP server...and in the near future a mock smtp server can be
    * built.
    *
    */
   
   @Test(enabled=false)
   public void testSendEmailWithAttachments()
   {
      try
      {
         String testPort = null;
         
         String testSubject = "This is a test!";
         String testMessage = "Do not be alarmed...this is ONLY a test!";
         
         String[] testAttachFiles = { TESTATTACHMENTFILE1 };
         
         String[] testToAddresses = { TESTRECEIVER }; 
            
         EmailUtil.sendEmailWithAttachments(
            TESTSMTPHOST,
            testPort,
            TESTSENDER,
            TESTSENDERNAME,
            testToAddresses,
            testSubject,
            testMessage,
            testAttachFiles);
      }
      catch (AddressException aex)
      {
         Assert.fail("Found unexpected AddressException: " + aex.getMessage());
      }
      catch (MessagingException mex)
      {
         Assert.fail("Found unexpected AddressException: " + mex.getMessage());
      }
   }
   
   @Test(enabled=false)
   public void testGetIMAPEmail()
   {
      try
      {
         List<Message> messageList = EmailUtil.getIMAPEmail();
         
         Assert.assertEquals(messageList.size(), 1);
      }
      catch (Exception ex)
      {
         Assert.fail("Found unexpected Exception: " + ex.getMessage());
      }
   }
   
   @Test(enabled=false)
   public void testGetExchangeMailBoxMessages()
   {     
      try
      {
         ExchangeService es = EmailUtil.createExchangeServiceConnection(
            "domjo2",
            "$parTan1990",
            "socsent.sharp.com");
         
         List<EmailMessage> messageList = EmailUtil.getMessages(es);
         
         Assert.assertEquals(messageList.size(), 1);
      }
      catch (Exception ex)
      {
         Assert.fail("Found unexpected Exception: " + ex.getMessage());
      }
   }
   
   @Test(enabled=false)
   public void testGetExchangeMailBoxMessagesAndGetURLs()
   {     
      try
      {
         ExchangeService es = EmailUtil.createExchangeServiceConnection(
            "domjo2",
            "$parTan1990",
            "socsent.sharp.com");
         
         List<EmailMessage> messageList = EmailUtil.getMessages(es);

         Assert.assertEquals(messageList.size(), 4);
         
         // We should process each email separately from pulling them from
         // the Exchange mailbox...
         
         EmailMessage em = null;
         
         List<String> urlList = null;
         
         for (int i = 0; i < messageList.size(); i++)
         {
            em = messageList.get(i);
            
            urlList = EmailUtil.processURLsFromEmail(em);
            
            for (int j = 0; j < urlList.size(); j++)
            {
               if (j == 0)
               {
                  System.out.println(
                     "URL's Found [" + urlList.size() + "] for Email ID: " +
                     em.getId().toString());
               }
               
               System.out.println("URL[" + j + "]: " + urlList.get(j));
            }
            
            System.out.println("Moving email[" + i + "] ID: " + em.getId().toString());
            
            // Move the email to the "Processed" folder..
            EmailUtil.moveEmail(es, em, "Processed");
         }
      }
      catch (Exception ex)
      {
         Assert.fail("Found unexpected Exception: " + ex.getMessage());
      }
   }
   
   @Test(enabled=false)
   public void testGetAttachements()
   {     
      try
      {
         ExchangeService es = EmailUtil.createExchangeServiceConnection(
            "domjo2",
            "$parTan1990",
            "socsent.sharp.com");
         
         List<EmailMessage> messageList = EmailUtil.getMessages(es);

         Assert.assertEquals(messageList.size(), 4);
         
         // We should process each email separately from pulling them from
         // the Exchange mailbox...
         
         EmailMessage em = null;
         
         List<String> urlList = null;
         List<String> attachmentList = null;
         
         for (int i = 0; i < messageList.size(); i++)
         {
            em = messageList.get(i);
            
            urlList = EmailUtil.processURLsFromEmail(em);
            
            for (int j = 0; j < urlList.size(); j++)
            {
               if (j == 0)
               {
                  System.out.println(
                     "URL's Found [" + urlList.size() + "] for Email ID: " +
                     em.getId().toString());
               }
               
               System.out.println("URL[" + j + "]: " + urlList.get(j));
            }
            
            attachmentList = EmailUtil.processAttachementsFromEmail(
               em, "F:\\My Documents\\ITRM\\projects\\eclipse\\commons\\util\\temp");
            
            for(int k = 0; k < attachmentList.size(); k++)
            {
               if (k == 0)
               {
                  System.out.println("Attachments Found [" + attachmentList.size() + "]:");
               }
               
               System.out.println("Attachement File[" + k + "]: " + attachmentList.get(k));
            }
            
            System.out.println("Moving email[" + i + "] ID: " + em.getId().toString());
            
            // Move the email to the "Processed" folder..
            EmailUtil.moveEmail(es, em, "Processed");
         }
      }
      catch (Exception ex)
      {
         Assert.fail("Found unexpected Exception: " + ex.getMessage());
      }
   }
}
