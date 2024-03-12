package com.ipartek.formacion.slab.rest;

import java.util.ArrayList;

import com.ipartek.formacion.slab.accesodatos.RouteSetterAccesoDatos;
import com.ipartek.formacion.slab.dtos.DetalleRouteSetterDTO;
import com.ipartek.formacion.slab.dtos.LeerRouteSetterDTO;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;

@Path("/route_setters")
public class RouteSetterRest {

	@POST
	public LeerRouteSetterDTO insertar(LeerRouteSetterDTO routeSetter) {
		return RouteSetterAccesoDatos.insertar(routeSetter);
	}

	@GET
	public ArrayList<LeerRouteSetterDTO> obtenerTodos() {
		return RouteSetterAccesoDatos.obtenerTodos();
	}
	
	@GET
	@Path("/{id}")
	public DetalleRouteSetterDTO obtenerPorId(@PathParam("id") Long id) {
		return RouteSetterAccesoDatos.obtenerPorId(id);
	}
	
}
