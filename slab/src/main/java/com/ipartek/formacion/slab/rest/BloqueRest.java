package com.ipartek.formacion.slab.rest;

import com.ipartek.formacion.slab.accesodatos.BloqueAccesoDatos;
import com.ipartek.formacion.slab.dtos.DetalleBloqueDTO;
import com.ipartek.formacion.slab.dtos.FotoDTO;
import com.ipartek.formacion.slab.dtos.InsertBloqueDTO;

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

//	@POST
//	public InsertBloqueDTO insertar(InsertBloqueDTO bloque, FotoDTO foto) {
//		return BloqueAccesoDatos.insertar(bloque, foto);
//	}

}
