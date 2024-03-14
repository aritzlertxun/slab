package com.ipartek.formacion.slab.dtos;

import java.util.ArrayList;

public record InsertFotoDTO(Long idFoto, String url, ArrayList<AgarreDTO> agarres ) {

}
