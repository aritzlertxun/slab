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
	private static final String SQL_SELECT_ID = "SELECT r.id, r.nombre, b.nombre FROM route_setters r JOIN bloques b ON b.route_setters_id = r.id WHERE r.id = ?";
//TODO
	// segunda select para sacar los nombre de bloques creados

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
		// TODO
		// primero hacer la sentencia select para rellenar bloques con el while
		// después, cuando tengamos bloques creado hacemos la select_id
		// igual que en BloqueAccesoDatos el metodo obtenerPorId

		var bloques = new ArrayList<LeerBloqueDTO>();
		try (Connection con = AccesoDatos.obtenerConexion();
				PreparedStatement pst = con.prepareStatement(SQL_SELECT_ID)) {

			pst.setLong(1, id);
			ResultSet rs = pst.executeQuery();

			LeerBloqueDTO bloque;

			// TODO

			if (rs.next()) {

				bloques = new ArrayList<LeerBloqueDTO>();

				return new DetalleRouteSetterDTO(rs.getLong("r.id"), rs.getString("r.nombre"), bloques);

			} else {
				return null;
			}
		} catch (SQLException e) {
			throw new RuntimeException("Error en la select", e);
		}
	}

}
