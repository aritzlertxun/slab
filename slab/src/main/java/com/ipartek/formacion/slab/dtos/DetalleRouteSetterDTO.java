package com.ipartek.formacion.slab.dtos;

import java.util.ArrayList;

public record DetalleRouteSetterDTO(Long id, String nombre, ArrayList<LeerBloqueDTO> bloques) {

}
