package com.ipartek.formacion.slab.accesodatos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.ipartek.formacion.slab.dtos.AgarreDTO;
import com.ipartek.formacion.slab.dtos.FotoDTO;

public class FotoAccesoDatos {

	private static final String SQL_SELECT = "SELECT f.url FROM fotos f WHERE f.id = ?";
	private static final String SQL_SELECT_2 = "SELECT a.tipos_id, a.coordenadas FROM agarres a JOIN fotos f ON f.id = a.fotos_id WHERE f.id = ?"; 
	
	private static final String SQL_INSERT_URL = "INSERT INTO fotos (url) VALUES (?)";
	private static final String SQL_INSERT_FOTO_AGARRES_COORDENADAS = "INSERT INTO agarres (fotos_id, tipos_id, coordenadas) VALUES (?,?,?)";
	
	
	public static FotoDTO obtenerPorId(Long id) {
		
		var agarres = new ArrayList<AgarreDTO>();
		
		try (Connection con = AccesoDatos.obtenerConexion();
				PreparedStatement pst = con.prepareStatement(SQL_SELECT_2)) {
			pst.setLong(1, id);
			
			ResultSet rs = pst.executeQuery();
			
			AgarreDTO agarre;
			
			while(rs.next()) {
				agarre = new AgarreDTO(rs.getLong("a.tipos_id"), rs.getString("a.coordenadas"));
				agarres.add(agarre);
			}
			
			PreparedStatement pst2 = con.prepareStatement(SQL_SELECT);
			pst2.setLong(1, id);
			ResultSet rs2 = pst2.executeQuery();
			
			if(rs2.next()) {
				return new FotoDTO(rs2.getString("f.url"), agarres);
			}else {
				return null;
			}
			
			
		} catch (SQLException e) {
			throw new RuntimeException("Error en la select", e);
			
		}
	}

	public static FotoDTO insertarFoto(FotoDTO foto) {

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

}
