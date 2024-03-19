package com.ipartek.formacion.slab.dtos;

import java.util.ArrayList;

public record BloqueDTO(Long id, String nombre, RocodromoDTO rocodromo, GradoDTO grado, LeerRouteSetterDTO routeSetter,
		ArrayList<EscaladorDTO> usuarios, FotoDTO foto) {


}
