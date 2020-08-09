/*
 * JBoss, Home of Professional Open Source
 * Copyright 2015, Red Hat, Inc. and/or its affiliates, and individual
 * contributors by the @authors tag. See the copyright.txt in the
 * distribution for a full listing of individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jboss.as.quickstarts.rshelloworld;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.eclipse.microprofile.metrics.MetricUnits;
import org.eclipse.microprofile.metrics.annotation.Counted;
import org.eclipse.microprofile.metrics.annotation.Timed;
import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.info.Info;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

/**
 * A simple REST service which is able to say hello to someone using
 * HelloService Please take a look at the web.xml where JAX-RS is enabled
 *
 * @author gbrey@redhat.com
 *
 */

@Path("/")
@OpenAPIDefinition(info = @Info(title = "Saludos Resource", description = "Servicios que entregar saludos", version = "1.0"))
public class HelloWorld {
	@Inject
	HelloService helloService;

	@GET
	@Path("/json")
	@Counted(description = "Contador saludo 1", absolute = true)
	@Timed(name = "saludo1-time", description = "Tiempo de procesamiento de saludo 1", unit = MetricUnits.MILLISECONDS, absolute = true)
	@APIResponse(
		responseCode = "200",
		description = "Respuesta saludo 1",
		content = @Content(mediaType = "application/json")
	)
	@Operation(summary = "Saludo 1", description = "Retorna el valor de saludo 1")
	@Produces({ "application/json" })
	public String getHelloWorldJSON() {
		return "{\"result\":\"" + helloService.createHelloMessage("World") + "\"}";
	}

	@GET
	@Path("/xml")
	@Counted(description = "Contador saludo 2", absolute = true)
	@Timed(name = "saludo2-time", description = "Tiempo de procesamiento de saludo 2", unit = MetricUnits.MILLISECONDS, absolute = true)
	@APIResponse(
		responseCode = "200",
		description = "Respuesta saludo 2",
		content = @Content(mediaType = "application/xml")
	)
	@Operation(summary = "Saludo 2", description = "Retorna el valor de saludo 2")
	@Produces({ "application/xml" })
	public String getHelloWorldXML() {
		return "<xml><result>" + helloService.createHelloMessage("World") + "</result></xml>";
	}

	@GET
	@Path("/echo/{texto}")
	@Counted(description = "Contador echo", absolute = true)
	@Timed(name = "echo-time", description = "Tiempo de procesamiento de echo", unit = MetricUnits.MILLISECONDS, absolute = true)
	@APIResponse(
		responseCode = "200",
		description = "Respuesta Echo",
		content = @Content(mediaType = "application/json")
	)
	@Operation(summary = "Echo", description = "Retorna el valor de echo")
	@Produces({ "application/json" })
	public String replyEcho(
			@Parameter(description = "texto requerido para reply", required = true, example = "foo", schema = @Schema(type =SchemaType.STRING)) @PathParam("texto") String texto) {
		return "{\"echo\":\"" + texto + "\"}";
	}

}
