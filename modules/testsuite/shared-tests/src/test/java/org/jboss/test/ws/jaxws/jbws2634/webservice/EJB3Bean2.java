/*
 * JBoss, Home of Professional Open Source.
 * Copyright 2015, Red Hat Middleware LLC, and individual contributors
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
package org.jboss.test.ws.jaxws.jbws2634.webservice;

import jakarta.ejb.Stateless;
import jakarta.jws.WebService;

import org.jboss.ws.api.annotation.WebContext;

/**
 * EJB3 bean published as WebService injecting other EJB3 bean.
 *
 * @author <a href="mailto:alessio.soldano@jboss.com">Alessio Soldano</a>
 */
@WebService
(
   name="EJB3",
   serviceName = "EJB3Service2", 
   targetNamespace = "http://jbossws.org/JBWS2634", 
   endpointInterface="org.jboss.test.ws.jaxws.jbws2634.webservice.EndpointIface"
)
@Stateless
@WebContext
(
   urlPattern = "/EJB3Service2",
   contextRoot = "/jaxws-jbws2634-ejb3"
)
public class EJB3Bean2 extends AbstractEndpointImpl
{

   public String echo(String msg)
   {
      return super.echo(msg) + ":EJB3Bean2";
   }

}
