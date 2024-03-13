package com.ipartek.formacion.slab.rest;

import java.util.ArrayList;

import com.ipartek.formacion.slab.accesodatos.RocodromoAccesoDatos;
import com.ipartek.formacion.slab.dtos.DetalleRocodromoDTO;
import com.ipartek.formacion.slab.dtos.LeerBloqueDTO;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;

@Path("/rocodromos")
public class RocodromoRest {
 
	@GET
	@Path("/{id}")
	public ArrayList<LeerBloqueDTO> obtenerTodosPorId(@PathParam("id") Long id) {
		return RocodromoAccesoDatos.obtenerTodosPorId(id);
	}
	
	@POST
	public DetalleRocodromoDTO insertar(DetalleRocodromoDTO rocodromo) {
		return RocodromoAccesoDatos.insertar(rocodromo);
	}

}
