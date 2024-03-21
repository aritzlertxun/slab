package com.ipartek.formacion.slab.dtos;

import java.util.ArrayList;

public record FotoDTO(Long id, String url, ArrayList <AgarreDTO> agarres) {
	
	public FotoDTO(String url, ArrayList <AgarreDTO> agarres ) {
		this(null, url, agarres);
	}
}
