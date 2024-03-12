package com.ipartek.formacion.slab.accesodatos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.ipartek.formacion.slab.dtos.LeerEscaladorDTO;

public class EscaladorAccesoDatos {

	private static final String SQL_INSERT = "INSERT INTO escaladores (nombre) VALUES (?)";
	private static final String SQL_SELECT = "SELECT nombre FROM escaladores";
	private static final String SQL_SELECT_ID = "SELECT nombre FROM escaladores WHERE id=?";

	public static LeerEscaladorDTO insertar(LeerEscaladorDTO escalador) {

		try (Connection con = AccesoDatos.obtenerConexion(); PreparedStatement pst = con.prepareStatement(SQL_INSERT)) {

			pst.setString(1, escalador.nombre());

			pst.executeUpdate();

			return escalador;

		} catch (SQLException e) {
			throw new RuntimeException("Error en la insert", e);
		}
	}

	public static ArrayList<LeerEscaladorDTO> obtenerTodos() {

		var escaladores = new ArrayList<LeerEscaladorDTO>();

		Connection con = AccesoDatos.obtenerConexion();
		try (PreparedStatement pst = con.prepareStatement(SQL_SELECT); ResultSet rs = pst.executeQuery()) {
			LeerEscaladorDTO escalador;

			while (rs.next()) {
				escalador = new LeerEscaladorDTO(rs.getString("nombre"));

				escaladores.add(escalador);
			}
			return escaladores;

		} catch (SQLException e) {
			throw new RuntimeException ("Error en la select", e);
		}
	}

	public static LeerEscaladorDTO obtenerPorId(Long id) {
		try (Connection con = AccesoDatos.obtenerConexion();
				PreparedStatement pst = con.prepareStatement(SQL_SELECT_ID)) {
			pst.setLong(1, id);
			
			ResultSet rs = pst.executeQuery();
			
			
			if (rs.next()) {
				return new LeerEscaladorDTO(rs.getString("nombre"));
			} else {
				return null;
			}
		} catch (SQLException e) {
			throw new RuntimeException("Error en la select", e);
		}
	}

	
}
