package com.ipartek.formacion.slab.accesodatos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.ipartek.formacion.slab.dtos.DetalleRouteSetterDTO;
import com.ipartek.formacion.slab.dtos.LeerBloqueDTO;
import com.ipartek.formacion.slab.dtos.LeerRouteSetterDTO;

public class RouteSetterAccesoDatos {
	private static final String SQL_INSERT = "INSERT INTO route_setters (nombre) VALUES (?)";
	private static final String SQL_SELECT = "SELECT nombre FROM route_setters";

	private static final String SQL_SELECT_ID = "SELECT rs.id, rs.nombre FROM route_setters rs JOIN bloques b ON b.route_setters_id = rs.id WHERE rs.id = ?";
	private static final String SQL_SELECT_ID_2 = "SELECT b.nombre FROM bloques b JOIN route_setters rs ON rs.id = b.route_setters_id WHERE rs.id = ?";

	public static LeerRouteSetterDTO insertar(LeerRouteSetterDTO routeSetter) {

		try (Connection con = AccesoDatos.obtenerConexion(); PreparedStatement pst = con.prepareStatement(SQL_INSERT)) {

			pst.setString(1, routeSetter.nombre());

			pst.executeUpdate();
			return routeSetter;
		} catch (SQLException e) {
			throw new RuntimeException("Error en la insert", e);
		}

	}

	public static ArrayList<LeerRouteSetterDTO> obtenerTodos() {
		var routeSetters = new ArrayList<LeerRouteSetterDTO>();

		Connection con = AccesoDatos.obtenerConexion();
		try (PreparedStatement pst = con.prepareStatement(SQL_SELECT); ResultSet rs = pst.executeQuery()) {
			LeerRouteSetterDTO routeSetter;

			while (rs.next()) {
				routeSetter = new LeerRouteSetterDTO(rs.getString("nombre"));

				routeSetters.add(routeSetter);
			}
			return routeSetters;

		} catch (SQLException e) {
			throw new RuntimeException("Error en la select", e);
		}
	}

	public static DetalleRouteSetterDTO obtenerPorId(Long id) {

		var bloques = new ArrayList<LeerBloqueDTO>();

		try (Connection con = AccesoDatos.obtenerConexion();
				PreparedStatement pst = con.prepareStatement(SQL_SELECT_ID_2)) {

			pst.setLong(1, id);
			ResultSet rs = pst.executeQuery();

			LeerBloqueDTO bloque;

			while (rs.next()) {

				bloque = new LeerBloqueDTO(rs.getString("b.nombre"));
				bloques.add(bloque);
			}

			PreparedStatement pst2 = con.prepareStatement(SQL_SELECT_ID);
			pst2.setLong(1, id);
			ResultSet rs2 = pst2.executeQuery();

			if (rs2.next()) {
				return new DetalleRouteSetterDTO(rs2.getLong("rs.id"), rs2.getString("rs.nombre"), bloques);
			} else {
				return null;
			}

		} catch (SQLException e) {
			throw new RuntimeException("Error en la select", e);
		}
	}

}
