package com.ipartek.formacion.slab.accesodatos;

import java.util.ArrayList;

import com.ipartek.formacion.slab.dtos.AgarreDTO;

public record InsertarFotoDTO(Long id, ArrayList<AgarreDTO> agarres ) {

}
