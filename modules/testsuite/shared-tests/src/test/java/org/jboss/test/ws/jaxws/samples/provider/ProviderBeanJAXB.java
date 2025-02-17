/*
 * JBoss, Home of Professional Open Source.
 * Copyright 2006, Red Hat Middleware LLC, and individual contributors
 * as indicated by the @author tags. See the copyright.txt file in the
 * distribution for a full listing of individual contributors.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */
package org.jboss.test.ws.jaxws.samples.provider;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.util.JAXBSource;
import javax.xml.transform.Source;
import jakarta.xml.ws.BindingType;
import jakarta.xml.ws.Provider;
import jakarta.xml.ws.Service;
import jakarta.xml.ws.ServiceMode;
import jakarta.xml.ws.WebServiceException;
import jakarta.xml.ws.WebServiceProvider;
import jakarta.xml.ws.http.HTTPBinding;

import org.jboss.logging.Logger;

/**
 * Test a Provider<Source> with JAXB
 *
 * @author Thomas.Diesler@jboss.org
 * @author <a href="jason.greene@jboss.com"/>Jason T. Greene</a>
 * @since 29-Jun-2006
 */
@WebServiceProvider(serviceName = "ProviderService", portName = "ProviderPort", targetNamespace = "http://org.jboss.ws/provider", wsdlLocation = "WEB-INF/wsdl/Provider.wsdl")
@BindingType(value = HTTPBinding.HTTP_BINDING)
@ServiceMode(value = Service.Mode.MESSAGE)
public class ProviderBeanJAXB implements Provider<Source>
{
   // provide logging
   private static Logger log = Logger.getLogger(ProviderBeanJAXB.class);

   public Source invoke(Source request)
   {
      try
      {
         JAXBContext jc = JAXBContext.newInstance(new Class[] { UserType.class });
         UserType user = (UserType)jc.createUnmarshaller().unmarshal(request);

         log.info("[string=" + user.getString() + ",qname=" + user.getQname() + "]");

         return new JAXBSource(jc, user);
      }
      catch (RuntimeException rte)
      {
         throw rte;
      }
      catch (Exception e)
      {
         throw new WebServiceException(e);
      }
   }
}
