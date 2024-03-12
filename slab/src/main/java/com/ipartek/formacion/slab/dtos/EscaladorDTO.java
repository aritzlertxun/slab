package com.ipartek.formacion.slab.dtos;

import java.util.ArrayList;

public record EscaladorDTO(Long id, String nombre, ArrayList<LeerBloqueDTO> bloques) {

}
