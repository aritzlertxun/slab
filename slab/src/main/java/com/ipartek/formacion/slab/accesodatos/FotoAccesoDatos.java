package com.ipartek.formacion.slab.accesodatos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import com.ipartek.formacion.slab.dtos.FotoDTO;

//TODO
public class FotoAccesoDatos {

	private static final String SQL_INSERT_URL = "INSERT INTO fotos (url) VALUES (?)";
	private static final String SQL_INSERT_FOTO_AGARRES_COORDENADAS = "INSERT INTO agarres (fotos_id, tipos_id, coordenadas) VALUES (?,?,?)";

	// TODO
	public static FotoDTO insertar(FotoDTO foto) {

		try (Connection con = AccesoDatos.obtenerConexion();
				PreparedStatement pst = con.prepareStatement(SQL_INSERT_URL, Statement.RETURN_GENERATED_KEYS)) {

			pst.setString(1, foto.url());
			pst.executeUpdate();

			var rs = pst.getGeneratedKeys();

			rs.next();

			Long idFoto = rs.getLong(1);

			PreparedStatement pst2 = con.prepareStatement(SQL_INSERT_FOTO_AGARRES_COORDENADAS);

			pst2.setLong(1, idFoto);

			for (var agarre : foto.agarres()) {
				pst2.setLong(2, agarre.id());
				pst2.setString(3, agarre.coordenadas());

				pst2.executeUpdate();
			}

			return foto;

		} catch (SQLException e) {
			throw new RuntimeException("Error en la insert", e);
		}

	}

//	public static FotoDTO obtenerPorId(Long id) {
//		Connection con = AccesoDatos.obtenerConexion();
//		PreparedStatement pst = con.prepareStatement(SQL_SELECT_ID_2);
//	}

}
