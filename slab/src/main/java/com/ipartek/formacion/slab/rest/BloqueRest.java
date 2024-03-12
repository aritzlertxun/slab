package com.ipartek.formacion.slab.rest;

import java.util.ArrayList;

import com.ipartek.formacion.slab.accesodatos.BloqueAccesoDatos;
import com.ipartek.formacion.slab.dtos.DetalleBloqueDTO;
import com.ipartek.formacion.slab.dtos.LeerBloqueDTO;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;

@Path("/bloques")
public class BloqueRest {

	@GET
	@Path("/{id}")
	public DetalleBloqueDTO obtenerBloquePorId(@PathParam("id") Long id) {
		return BloqueAccesoDatos.obtenerBloquePorId(id);
	}
	
	
	//TODO
//	@POST
//	public LeerBloqueDTO insertar(LeerBloqueDTO bloque) {
//		return BloqueAccesoDatos.insertar(bloque);
//	}

	//TODO
	@GET
	public ArrayList<LeerBloqueDTO> obtenerTodos() {
		return BloqueAccesoDatos.obtenerTodos();
	}

}
