/**
 *
 * Name:   EmailUtil.java
 * Author: John Domingo
 *
 * This file contains the implementation of the EmailUtil.
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

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.FileUtils;
import org.elucidant.util.net.URLUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import microsoft.exchange.webservices.data.core.ExchangeService;
import microsoft.exchange.webservices.data.core.PropertySet;
import microsoft.exchange.webservices.data.core.enumeration.misc.ExchangeVersion;
import microsoft.exchange.webservices.data.core.enumeration.property.WellKnownFolderName;
import microsoft.exchange.webservices.data.core.enumeration.search.FolderTraversal;
import microsoft.exchange.webservices.data.core.enumeration.search.SortDirection;
import microsoft.exchange.webservices.data.core.exception.service.local.ServiceLocalException;
import microsoft.exchange.webservices.data.core.service.item.EmailMessage;
import microsoft.exchange.webservices.data.core.service.item.Item;
import microsoft.exchange.webservices.data.core.service.schema.FolderSchema;
import microsoft.exchange.webservices.data.core.service.schema.ItemSchema;
import microsoft.exchange.webservices.data.credential.ExchangeCredentials;
import microsoft.exchange.webservices.data.credential.WebCredentials;
import microsoft.exchange.webservices.data.property.complex.AttachmentCollection;
import microsoft.exchange.webservices.data.property.complex.FileAttachment;
import microsoft.exchange.webservices.data.property.complex.ItemId;
import microsoft.exchange.webservices.data.search.FindFoldersResults;
import microsoft.exchange.webservices.data.search.FindItemsResults;
import microsoft.exchange.webservices.data.search.FolderView;
import microsoft.exchange.webservices.data.search.ItemView;
import microsoft.exchange.webservices.data.search.filter.SearchFilter;

/**
 *
 * @author John Domingo
 *
 *
 */

public class EmailUtil
{
   private static Logger LOGGER =
      LoggerFactory.getLogger(EmailUtil.class);
   
   private static Integer NUMBER_EMAILS_FETCH = 5; // only latest 5 emails
   
   /**
    * 
    * This method will send an email with OPTIONAL attachments out via an SMTP
    * specified host.  This method will NOT support secure send of emails. A
    * separate overloaded method should be created to support secure emails.
    * 
    * @param host String object representing the SMTP host to use to send the
    *    email.
    * @param fromAddress String object representing the sender's email address.
    * @param toAddresses String object array of recipient addresses.
    * @param subject String object representing the subject of the email to
    *    send.
    * @param message String object representing the message body to include as
    *    part of the email.
    * @param attachFiles String objects that represent the paths of files to 
    *    send as attachments to the email.
    * 
    * @throws AddressException thrown when an exception/error occurs during
    *    the sending of the email.
    * @throws MessagingException thrown when an exception/error occurs during
    *    the sending of the email.
    *
    */
   
   public static void sendEmailWithAttachments(
      final String host,
      final String fromAddress,
      final String[] toAddresses,
      final String subject,
      final String message,
      final String[] attachFiles)
      throws AddressException, MessagingException
   {
      sendEmailWithAttachments(
         host,
         null,
         fromAddress,
         null,
         toAddresses,
         subject,
         message,
         attachFiles);
   }
   
   /**
    * 
    * This method will send an email with OPTIONAL attachments out via an SMTP
    * specified host.  This method will NOT support secure send of emails. A
    * separate overloaded method should be created to support secure emails.
    * 
    * @param host String object representing the SMTP host to use to send the
    *    email.
    * @param port String object representing the port value of the SMTP host
    *    to send the email.
    * @param fromAddress String object representing the sender's email address.
    * @param fromName String object representing the sender's name.
    * @param toAddresses String object array of recipient addresses.
    * @param subject String object representing the subject of the email to
    *    send.
    * @param message String object representing the message body to include as
    *    part of the email.
    * @param attachFiles String objects that represent the paths of files to 
    *    send as attachments to the email.
    * 
    * @throws AddressException thrown when an exception/error occurs during
    *    the sending of the email.
    * @throws MessagingException thrown when an exception/error occurs during
    *    the sending of the email.
    *
    */
   
   public static void sendEmailWithAttachments(
      final String host,
      final String port,
      final String fromAddress,
      final String fromName,
      final String[] toAddresses,
      final String subject,
      final String message,
      final String[] attachFiles)
      throws AddressException, MessagingException
   {
      if (LOGGER.isDebugEnabled() == true)
      {
         LOGGER.debug("SMTP Host: " + host);
         LOGGER.debug("SMTP Host Port: " + port);
         LOGGER.debug("From: " + fromAddress);
         
         for (int i = 0; i < toAddresses.length; i++)
         {
            LOGGER.debug("To Address[" + i + "]: " + toAddresses[i]);
         }
         
         LOGGER.debug("Subject: " + subject);
         LOGGER.debug("Message: " + message);
                  
         if (attachFiles != null)
         {
            for (int i = 0; i < attachFiles.length; i++)
            {
               LOGGER.debug("File Attachement[" + i + "]: " + attachFiles[i]);
            }
         }
      }
      
      // Set SMTP server properties
      Properties properties = new Properties();
      
      properties.put("mail.smtp.host", host);
      
      if (port != null)
      {
         properties.put("mail.smtp.port", port);
      }

      properties.put("mail.user", fromAddress);
   
      Session session = Session.getInstance(properties);
      
      // creates a new e-mail message
      Message mimeMessage = new MimeMessage(session);

      if (fromName == null)
      {
         mimeMessage.setFrom(new InternetAddress(fromAddress));
      }
      else
      {
         mimeMessage.setFrom(
            new InternetAddress(fromName + "<" + fromAddress + ">"));
      }
      
      List<InternetAddress> internetAddressList =
         new ArrayList<InternetAddress>();
      
      for (int i = 0; i < toAddresses.length; i++)
      {
         internetAddressList.add(new InternetAddress(toAddresses[i]));
      }
      
      mimeMessage.setRecipients(
         Message.RecipientType.TO,
         internetAddressList.toArray(
            new InternetAddress[internetAddressList.size()]));
      
      mimeMessage.setSubject(subject);
      mimeMessage.setSentDate(new Date());

      // creates message part
      MimeBodyPart messageBodyPart = new MimeBodyPart();
      
      messageBodyPart.setContent(message, "text/html");

      // creates multi-part
      Multipart multipart = new MimeMultipart();
      
      multipart.addBodyPart(messageBodyPart);

      // adds attachments
      if (attachFiles != null && attachFiles.length > 0)
      {
         for (String filePath : attachFiles)
         {
            MimeBodyPart attachPart = new MimeBodyPart();

            try
            {
               attachPart.attachFile(filePath);
            }
            catch (IOException ioex)
            {
               ioex.printStackTrace();
            }

            multipart.addBodyPart(attachPart);
         }
      }

      // sets the multi-part as e-mail's content
      mimeMessage.setContent(multipart);

      // sends the e-mail
      Transport.send(mimeMessage);
   }
   
   public static ExchangeService createExchangeServiceConnection(
      String userName,
      String userPassword,
      String domain)
   {
      ExchangeService service = null;
      
      try
      {         
         service = new ExchangeService(ExchangeVersion.Exchange2010_SP2);
         //service = new ExchangeService(ExchangeVersion.Exchange2007_SP1); //depending on the version of your Exchange.
         
         //service.setUrl(new URI("https://socsentex1601.socsent.sharp.com/EWS/exchange.asmx"));
         service.setUrl(new URI("https://owa.socsent.sharp.com/EWS/exchange.asmx"));
         
         //ServicePointManager.ServerCertificateValidationCallback = (sender, certificate, chain, sslPolicyErrors) => true;
         
         ExchangeCredentials credentials = new WebCredentials(userName, userPassword, domain);
         
         service.setCredentials(credentials);
      }
      catch (Exception ex)
      {
         ex.printStackTrace();
      }
      
      return service;
   }
   
   public static List<EmailMessage> getMessages(ExchangeService service)
   {
      List<EmailMessage> messageList = null;
      
      try
      {                           
         messageList = EmailUtil.readEmails(service);
      }
      catch (Exception ex)
      {
         ex.printStackTrace();
      }
      
      return messageList;
   }
   
   /**
    * 
    * Number of email we want to read is defined as NUMBER_EMAILS_FETCH,
    *  
    */
   
   public static List<EmailMessage> readEmails(
      ExchangeService service)
   {
//      List<Map<String, String>> msgDataList =
//         new ArrayList<Map<String, String>>();
      List<EmailMessage> messageList = new ArrayList<EmailMessage>();
      
      try
      {
         ItemView view = new ItemView(Integer.MAX_VALUE);
         
         view.getOrderBy().add(
            ItemSchema.DateTimeReceived,
            SortDirection.Descending); 
         
         microsoft.exchange.webservices.data.core.service.folder.Folder folder =
            microsoft.exchange.webservices.data.core.service.folder.Folder.bind(
               service, WellKnownFolderName.Inbox);
         
         FindItemsResults<Item> results = service.findItems(
            folder.getId(),
            view);
         
         EmailMessage em = null;
         
         for (Item item : results)
         {
            // Guarantee that all components of the item has been loaded,
            // including attachments.
            item.load();
            
            //Map<String, String> messageData = new HashMap<String, String>();
            
            //messageData = EmailUtil.readEmailItem(service, item.getId());
            em = EmailUtil.readEmail(service, item.getId());
            
            messageList.add(em);
         }
      }
      catch (Exception ex)
      {
         ex.printStackTrace();
      }
      
      return messageList;
   }
   
   public static void moveEmail(
      ExchangeService service,
      EmailMessage em,
      String folderName)
      throws EmailUtilException
   {
      // Set View
      FolderView view = new FolderView(1);
      
      // Set Filter
      SearchFilter sf =
         new SearchFilter.IsEqualTo(FolderSchema.DisplayName, folderName);
      
      view.setTraversal(FolderTraversal.Deep);

      try
      {
         FindFoldersResults findFolderResults =
            service.findFolders(WellKnownFolderName.Root, sf, view);
         
         // Check to see if nothing returned, or more than one returned...
         if (findFolderResults.getTotalCount() < 1)
         {
            throw new EmailUtilException(
               "Cannot find folder [" + folderName + "]!");
         }
         
         if (findFolderResults.getTotalCount() > 1)
         {
            throw new EmailUtilException(
               "Multiple folders discovered with the same name [" +
               folderName + "]!");
         }
         
         em.move(findFolderResults.getFolders().get(0).getId());
      }
      catch (Exception ex)
      {
         // Unfortunately, the Folder.bind() method throws a generic Exception
         // back.  This will wrap the Exception and provide more context.
         throw new EmailUtilException(
            "Error/Exception while attempting to move folder [" +
            folderName + " ]: " +
            ex.getMessage(), ex);
      }
   }
   
   /**
    * 
    * Reading one email at a time. Using Item ID of the email.
    * Creating a message data map as a return value.   
    * 
    */
   
   public static EmailMessage readEmail(
      ExchangeService service,
      ItemId itemId)
   {
      //Map<String, String> messageData = new HashMap<String, String>();
      
      EmailMessage emailMessage = null;
      
      try
      {
         Item itm = Item.bind(service, itemId, PropertySet.FirstClassProperties);
         
         emailMessage = EmailMessage.bind(service, itm.getId());
         
         if (LOGGER.isDebugEnabled() == true)
         {
            LOGGER.debug("Email Retrieved:\n" + generateMessageInfo(emailMessage));
         }
      }
      catch (Exception ex)
      {
         ex.printStackTrace();
      }
      
      return emailMessage;
   }
   
   public static List<String> processURLsFromEmail(EmailMessage em)
      throws EmailUtilException
   {
      List<String> urlList = new ArrayList<String>();
      
      try
      {
         urlList = URLUtil.extractUrls(em.getBody().toString());
      }
      catch (ServiceLocalException slex)
      {
         throw new EmailUtilException("Error retrieving content from email!", slex);
      }
      
      return urlList;
   }
   
   public static List<String> processAttachementsFromEmail(
      EmailMessage em,
      String emailAttachmentFolderPath)
      throws EmailUtilException
   {
      List<String> osAttachementlList = new ArrayList<String>();
      
      try
      {
         // First create subfolder unique to the email in the email attachment
         // folder specified.  To create unique name, we will be taking the SHA256
         // of the EmailMessage's ID (which is rather long).
         
         String idDigest = DigestUtils.sha256Hex(em.getId().toString());
         
         String attachementFolderPath =
            emailAttachmentFolderPath +
            File.separator +
            idDigest;
         
         FileUtils.forceMkdir(new File(attachementFolderPath));
         
         String attachmentFilePath = null;
         
         if (em.getHasAttachments() == true)
         {
            AttachmentCollection ac = em.getAttachments();
         
            FileAttachment fa = null;
            
            for (int i = 0; i < ac.getCount(); i++)
            {
               fa = (FileAttachment)ac.getPropertyAtIndex(i);
               
               LOGGER.debug("Found File Attachement: " + fa.getName());
               
               attachmentFilePath =
                  attachementFolderPath +
                  File.separator +
                  fa.getName();
               
               fa.load(attachmentFilePath);
               
               LOGGER.debug("Writing temporary file to: " + attachmentFilePath);
               
               osAttachementlList.add(attachmentFilePath);
            }
         }
      }
      catch (IOException ioex)
      {
         throw new EmailUtilException(
            "Error retrieving attachment(s) from email!", ioex);
      }
      catch (ServiceLocalException slex)
      {
         throw new EmailUtilException(
            "Error retrieving attachment(s) from email!", slex);
      }
      catch (Exception ex)
      {
         throw new EmailUtilException(
            "Error retrieving attachment(s) from email!", ex);
      }
      
      return osAttachementlList;
   }
   
   public static String generateMessageInfo(EmailMessage em)
      throws ServiceLocalException
   {
      Date dateTimeCreated = em.getDateTimeCreated();
      Date dateTimeRecieved = em.getDateTimeReceived();
      
      StringBuffer sb = new StringBuffer();
      
      sb.append("Email ID: ").append(em.getId().toString()).append("\n").
         append("Subject : ").append(em.getSubject().toString()).append("\n").
         append("From Address: ").append(em.getFrom().getAddress().toString()).append("\n").
         append("Sender Name: ").append(em.getSender().getName().toString()).append("\n").
         append("Send Date: ").append(dateTimeCreated.toString()).append("\n").
         append("Recieved Date: ").append(dateTimeRecieved.toString()).append("\n").
         append("Size: ").append(em.getSize()).append("\n").
         append("Body: ").append(em.getBody().toString());
      
      return sb.toString();
   }
   
   
   public static List<Message> getIMAPEmail()
   {
      //String protocol = "imap";
      String protocol = "ewsstore";
      
      Properties props = new Properties();
     
//      props.put("mail.store.protocol", protocol);
//      props.put("mail.imap.auth.plain.disable", "true");
//      props.put("mail.imap.auth.ntlm.disable", "true");
      
//      props.put("mail.imaps.host", "socsent.sharp.com");
//      //props.put("mail.imaps.port", "993");
//      props.put("mail.imaps.port", "143");
//      props.put("mail.imaps.starttls.enable", "true");
      
      //props.put("mail.pop3s.host", "socsent.sharp.com");
      //props.put("mail.pop3s.port", "995");
      //props.put("mail.pop3s.port", "110");
      //props.put("mail.pop3.starttls.enable", "false");
      
      //properties.put("mail.smtp.auth", "true");
      //properties.put("mail.smtp.starttls.enable", "true");
      //properties.put("mail.smtp.host", "relay.jangosmtp.net");
      //properties.put("mail.smtp.port", "25");
     
      Message[] msgs = null;
      
      try
      {
         Session session = Session.getDefaultInstance(props);
        
         Store store = session.getStore(protocol);
        
         //store.connect("imap.gmail.com", "yourEmailId@gmail.com", "password");
         store.connect(
            "https://socsentex1601.socsent.sharp.com/EWS/exchange.asmx",
            "domjo2@socsent.sharp.com",
            "$parTan1990");
         
         Folder inbox = store.getFolder("INBOX");
        
         inbox.open(Folder.READ_ONLY);
        
         //Message msg = inbox.getMessage(inbox.getMessageCount());
         
         msgs = inbox.getMessages();
        
         for (int i = 0; i < msgs.length; i++)
         {
            System.out.println("Message [" + i + "]: " +
               generateMessageInfo(msgs[i]));
         }
      }
      catch (Exception mex)
      {
         mex.printStackTrace();
      }
      
      return Arrays.asList(msgs);
   }
   
   public static String generateMessageInfo(Message message)
      throws IOException,
         MessagingException
   {
      StringBuffer sb = new StringBuffer();
      
      Address[] in = message.getFrom();
      
      for (Address address : in)
      {
         sb.append("From:").append(address.toString());
      }
     
      Multipart mp = (Multipart)message.getContent();
     
      BodyPart bp = mp.getBodyPart(0);
     
      sb.append("Sent Date: ").append(message.getSentDate()).append("\n");
      sb.append("Subject: ").append(message.getSubject()).append("\n");
      sb.append("Content: ").append(bp.getContent()).append("\n");
      
      return sb.toString();
   }
}