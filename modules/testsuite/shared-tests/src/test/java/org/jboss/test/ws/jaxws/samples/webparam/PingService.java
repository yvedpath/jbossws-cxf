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
package org.jboss.test.ws.jaxws.samples.webparam;

import jakarta.jws.Oneway;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebResult;
import jakarta.jws.WebService;
import jakarta.jws.WebParam.Mode;
import jakarta.jws.soap.SOAPBinding;
import jakarta.jws.soap.SOAPBinding.Style;
import jakarta.xml.ws.Holder;

/**
 * This class was generated by the JAXWS SI.
 * JAX-WS RI 2.0-b26-ea3
 * Generated source version: 2.0
 */
@WebService(name = "PingService", targetNamespace = "http://www.openuri.org/jsr181/WebParamExample", wsdlLocation = "META-INF/wsdl/PingService.wsdl")
@SOAPBinding(style = Style.RPC)
public interface PingService {


    /**
     * 
     * @param ping
     */
    @WebMethod(operationName = "PingOneWay")
    @Oneway
    public void pingOneWay(
        @WebParam(name = "Ping", partName = "Ping")
        PingDocument ping);

    /**
     * 
     * @param ping
     */
    @WebMethod(operationName = "PingTwoWay")
    public void pingTwoWay(
        @WebParam(name = "Ping", mode = Mode.INOUT, partName = "Ping")
        Holder<PingDocument> ping);

    /**
     * 
     * @param secHeader
     * @param ping
     */
    @WebMethod(operationName = "SecurePing")
    @Oneway
    public void securePing(
        @WebParam(name = "Ping", partName = "Ping")
        PingDocument ping,
        @WebParam(name = "SecHeader", targetNamespace = "http://www.openuri.org/jsr181/WebParamExample", header = true, partName = "SecHeader")
        SecurityHeader secHeader);

    /**
     * 
     * @param arg0
     * @return
     *     returns org.jboss.test.ws.jaxws.samples.webparam.PingDocument
     */
    @WebMethod
    @WebResult(targetNamespace = "http://www.openuri.org/jsr181/WebParamExample", partName = "return")
    public PingDocument echo(
        @WebParam(name = "arg0", partName = "arg0")
        PingDocument arg0);

}
