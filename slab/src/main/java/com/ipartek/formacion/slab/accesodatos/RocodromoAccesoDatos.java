package com.ipartek.formacion.slab.accesodatos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.ipartek.formacion.slab.dtos.GradoDTO;
import com.ipartek.formacion.slab.dtos.LeerBloqueDTO;
import com.ipartek.formacion.slab.dtos.LeerFotoDTO;
import com.ipartek.formacion.slab.dtos.RocodromoDTO;

public class RocodromoAccesoDatos {

	private static final String SQL_SELECT_ROCO = "SELECT b.nombre, g.grado, f.url, r.nombre FROM bloques b JOIN grados g ON b.grados_id = g.id JOIN fotos f ON f.id = b.fotos_id JOIN rocodromos r ON r.id = b.rocodromos_id WHERE r.id = ?";;

	public static ArrayList<LeerBloqueDTO> obtenerTodosPorId(Long id) {

		var bloques = new ArrayList<LeerBloqueDTO>();

		try (Connection con = AccesoDatos.obtenerConexion();
				PreparedStatement pst = con.prepareStatement(SQL_SELECT_ROCO);) {

			pst.setLong(1, id);

			ResultSet rs = pst.executeQuery();

			RocodromoDTO rocodromo;
			GradoDTO grado;
			LeerFotoDTO foto;
			LeerBloqueDTO bloque;

			while (rs.next()) {

				rocodromo = new RocodromoDTO(rs.getString("r.nombre"));
				foto = new LeerFotoDTO(rs.getString("f.url"));
				grado = new GradoDTO(rs.getString("g.grado"));

				bloque = new LeerBloqueDTO(rs.getString("b.nombre"), foto, grado, rocodromo);
				bloques.add(bloque);
			}
			return bloques;

		} catch (SQLException e) {

			throw new RuntimeException("Error en la select", e);
		}
	}

}
