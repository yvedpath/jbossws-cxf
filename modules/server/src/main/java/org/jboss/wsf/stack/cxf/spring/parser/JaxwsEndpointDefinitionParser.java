/*
 * JBoss, Home of Professional Open Source.
 * Copyright 2010, Red Hat Middleware LLC, and individual contributors
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
package org.jboss.wsf.stack.cxf.spring.parser;

import org.apache.cxf.Bus;
import org.apache.cxf.bus.spring.BusWiringBeanFactoryPostProcessor;
import org.apache.cxf.configuration.Configurer;
import org.apache.cxf.jaxws.spring.EndpointDefinitionParser;
import org.jboss.wsf.stack.cxf.client.configuration.JBossWSSpringConfigurer;
import org.jboss.wsf.stack.cxf.deployment.EndpointImpl;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * A parser for jaxws-endpoint beans that extends the CXF default one
 * allowing for a custom Endpoint implementation class to be used.
 * 
 * @author alessio.soldano@jboss.com
 * @since 01-Apr-2010
 *
 */
public class JaxwsEndpointDefinitionParser extends EndpointDefinitionParser
{
   public JaxwsEndpointDefinitionParser()
   {
      super();
      setBeanClass(JBossWSSpringEndpointImpl.class);
   }

   public static class JBossWSSpringEndpointImpl extends EndpointImpl implements ApplicationContextAware
   {

      boolean checkBlockConstruct;
       
      public JBossWSSpringEndpointImpl(Object implementor)
      {
         super((Bus)null, implementor);
      }

      public JBossWSSpringEndpointImpl(Bus bus, Object implementor)
      {
         super(bus, implementor);
      }

      public void setCheckBlockConstruct(Boolean b) {
          checkBlockConstruct = b;
      }
      
      public void setApplicationContext(ApplicationContext ctx) throws BeansException
      {
         if (checkBlockConstruct) {
             setBlocking(ctx, this);
         }
         if (getBus() == null)
         {
            Bus bus = BusWiringBeanFactoryPostProcessor.addDefaultBus(ctx);
            setBus(bus);
            Configurer configurer = bus.getExtension(Configurer.class);
            if (configurer instanceof JBossWSSpringConfigurer)
            {
               ((JBossWSSpringConfigurer) configurer).addApplicationContext(ctx);
            }
         }
      }
   }
}
