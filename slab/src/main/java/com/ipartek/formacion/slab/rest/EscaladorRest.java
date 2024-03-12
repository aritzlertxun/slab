package com.ipartek.formacion.slab.rest;

import java.util.ArrayList;

import com.ipartek.formacion.slab.accesodatos.EscaladorAccesoDatos;
import com.ipartek.formacion.slab.dtos.LeerEscaladorDTO;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;

@Path("/escaladores")
public class EscaladorRest {

	@POST
	public static LeerEscaladorDTO insertar(LeerEscaladorDTO escalador) {
		return EscaladorAccesoDatos.insertar(escalador);
	}
	
	@GET
	public static ArrayList<LeerEscaladorDTO> obtenerEscaladores() {
		return EscaladorAccesoDatos.obtenerTodos();
		
	}
	
	@GET
	@Path("/{id}")
	public static LeerEscaladorDTO obtenerEscaladorPorId(@PathParam("id") Long id) {
		return EscaladorAccesoDatos.obtenerPorId(id);
	}
}
