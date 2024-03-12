package com.ipartek.formacion.slab.accesodatos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.ipartek.formacion.slab.dtos.AgarreDTO;
import com.ipartek.formacion.slab.dtos.DetalleBloqueDTO;
import com.ipartek.formacion.slab.dtos.FotoDTO;
import com.ipartek.formacion.slab.dtos.GradoDTO;
import com.ipartek.formacion.slab.dtos.LeerBloqueDTO;

public class BloqueAccesoDatos {

	private static final String SQL_SELECT = "SELECT b.nombre, g.grado, f.url, fa.agarres_id, fa.coordenadas FROM bloques b JOIN grados g ON b.grados_id = g.id JOIN fotos f ON f.id = b.fotos_id JOIN fotos_has_agarres fa ON f.id = fa.fotos_id";

	// Las dos SELECT por ID para generar el ArrayList de Agarres
	private static final String SQL_SELECT_ID = "SELECT b.nombre, g.grado, f.url FROM bloques b JOIN grados g ON b.grados_id = g.id JOIN fotos f ON f.id = b.fotos_id WHERE b.id = ?";
	private static final String SQL_SELECT_ID_2 = "SELECT fa.agarres_id, fa.coordenadas FROM fotos_has_agarres fa JOIN fotos f ON f.id = fa.fotos_id JOIN bloques b ON f.id = b.fotos_id WHERE b.id = ?";

	// private static final String SQL_INSERT = "INSERT INTO bloques b (b.nombre,
	// b.url_foto, g.grado, rc.nombre, rs.nombre, a.nombre, e.nombre) VALUES
	// (?,?,?,?,?,?) JOIN ";

//	public static LeerBloqueDTO insertar(DetalleBloqueDTO bloque) {
//
//		// TODO
//
//		try (Connection con = AccesoDatos.obtenerConexion(); PreparedStatement pst = con.prepareStatement(SQL_INSERT)) {
//
//			pst.setString(1, bloque.nombre());
//			pst.setString(2, bloque.grado().grado());
//			pst.setString(3, bloque.rocodromo().());
//			pst.setString(4, bloque.routeSetter().nombre());
//
//			pst.executeQuery();
//			return bloque;
//
//		} catch (SQLException e) {
//			throw new RuntimeException("Error en la insert", e);
//		}
//	}
//
	public static ArrayList<LeerBloqueDTO> obtenerTodos() {

		// TODO

		var bloques = new ArrayList<LeerBloqueDTO>();

		Connection con = AccesoDatos.obtenerConexion();

		try (PreparedStatement pst = con.prepareStatement(SQL_SELECT); ResultSet rs = pst.executeQuery()) {

			LeerBloqueDTO bloque;
			GradoDTO grado;

			while (rs.next()) {

				grado = new GradoDTO(rs.getString("g.grado"));

				bloque = new LeerBloqueDTO(rs.getString("b.nombre"), rs.getString("b.url_foto"), grado);
				bloques.add(bloque);
			}
			return bloques;

		} catch (SQLException e) {

			throw new RuntimeException("Error en la select", e);
		}
	}

	public static DetalleBloqueDTO obtenerBloquePorId(Long id) {

		var agarres = new ArrayList<AgarreDTO>();

		try (Connection con = AccesoDatos.obtenerConexion();
				PreparedStatement pst = con.prepareStatement(SQL_SELECT_ID_2)) {

			pst.setLong(1, id);

			ResultSet rs = pst.executeQuery();

			AgarreDTO agarre;

			while (rs.next()) {
				agarre = new AgarreDTO(rs.getLong("fa.agarres_id"), rs.getString("fa.coordenadas"));

				agarres.add(agarre);
			}

			PreparedStatement pst2 = con.prepareStatement(SQL_SELECT_ID);
			pst2.setLong(1, id);
			ResultSet rs2 = pst2.executeQuery();

			FotoDTO foto;
			GradoDTO grado;

			if (rs2.next()) {
				grado = new GradoDTO(rs2.getString("g.grado"));
				foto = new FotoDTO(rs2.getString("f.url"), agarres);
				return new DetalleBloqueDTO(rs2.getString("b.nombre"), grado, foto);

			} else {
				return null;
			}

		} catch (SQLException e) {
			throw new RuntimeException("Error en la select", e);
		}

	}
}
