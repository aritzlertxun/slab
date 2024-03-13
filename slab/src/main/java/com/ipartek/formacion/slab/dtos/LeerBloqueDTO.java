package com.ipartek.formacion.slab.dtos;

public record LeerBloqueDTO(String nombre, LeerFotoDTO foto, GradoDTO grado, RocodromoDTO rocodromo) {

	public LeerBloqueDTO(String nombre){
		this(nombre, null, null, null);
	};
	
	
}
