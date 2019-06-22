/*
 * =============================================================================
 * 
 *   Copyright (c) 2011-2016, The THYMELEAF team (http://www.thymeleaf.org)
 * 
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 * 
 *       http://www.apache.org/licenses/LICENSE-2.0
 * 
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 * 
 * =============================================================================
 */
package edu.mum.emailservice;

import java.util.Locale;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring4.SpringTemplateEngine;

import edu.mum.domain.Order;
import edu.mum.domain.RouteOrder;

@Service("emailService")
public class EmailService {

     private static final String IM_THE_GUY = "templates/images/imtheguy.jpg";
    
     private static final String JPG_MIME = "image/jpg";
     private static final String DOCX_MIME = "application/vnd.openxmlformats-officedocument.wordprocessingml.document";

  @Autowired
    private JavaMailSender mailSender;
 
  @Autowired
    private SpringTemplateEngine templateEngine;
    
    /* 
     * Send HTML mail  
     */
  @ServiceActivator()
  public MimeMessage  handle(RouteOrder routeOrder) 
          throws MessagingException {
      
	  Order order = routeOrder.getOrder();
	  // Need to use your own recipient here
	  String recipientEmail = "fff@mum.edu";
	  String recipientName = "Joe";
	  	return handle( recipientName, recipientEmail, order,null);
  
  		}
 
	    public MimeMessage  handle(
	            final String recipientName, final String recipientEmail, Order order,Locale locale) 
	            throws MessagingException {
	        
	    	if (locale == null) locale = new Locale("en");
	    	
        // Prepare the evaluation context
        final Context thymeContext = new Context(locale);
        thymeContext.setVariable("name", recipientName);
        thymeContext.setVariable("order", order);
          
        // Prepare message using a Spring helper
        final MimeMessage mimeMessage = this.mailSender.createMimeMessage();
        final MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true,"UTF-8");
        message.setSubject("RouteOrder Details");
 
        message.setTo(recipientEmail);

        // Create the HTML body using Thymeleaf
    //    final String htmlContent = this.templateEngine.process(new ClassPathResource("orderReceivedMail").getPath(), thymeContext);
        String htmlContent = null;
        try {
             htmlContent = this.templateEngine.process("orderReceivedMail", thymeContext);
        }
        catch (Exception e ) {System.out.println("Can't find email template");
             return mimeMessage;
        }
        message.setText(htmlContent, true /* isHtml */);
   
        message.addInline("imtheguy", new ClassPathResource(IM_THE_GUY), JPG_MIME);

        return mimeMessage;
    }

 
}
