package com.ipartek.formacion.slab.rest;

import com.ipartek.formacion.slab.accesodatos.FotoAccesoDatos;
import com.ipartek.formacion.slab.dtos.FotoDTO;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;

@Path("/fotos")
public class FotoRest {

	@GET
	@Path("/{id}")
	public static FotoDTO obtenerPorId(@PathParam("id") Long id) {
		return FotoAccesoDatos.obtenerPorId(id);
	}

	@POST
	public static FotoDTO insertar(FotoDTO foto) {
		return FotoAccesoDatos.insertarFoto(foto);
	}

}
