package com.ipartek.formacion.slab.accesodatos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import com.ipartek.formacion.slab.dtos.FotoDTO;
import com.ipartek.formacion.slab.dtos.UrlFotoDTO;

//TODO
public class FotoAccesoDatos {
	//TODO
	//private static final String SQL_INSERT = "INSERT INTO fotos_has_agarres (fotos_id, agarres_id, coordenadas) VALUES (?,?,?)";
	
	private static final String SQL_INSERT_URL = "INSERT INTO fotos (url) VALUES (?)";
	private static final String SQL_INSERT_AGARRE = "INSERT INTO fotos_has_agarres (fotos_id, agarres_id, coordenadas) VALUES (?,?,?)";

	//TODO
	public static Long insertarUrl (UrlFotoDTO urlFoto) {
		
		try (Connection con = AccesoDatos.obtenerConexion();
				PreparedStatement pst = con.prepareStatement(SQL_INSERT_URL, Statement.RETURN_GENERATED_KEYS)) {
			
			pst.setString(1, urlFoto.url());
			pst.executeUpdate();
			
			var rs = pst.getGeneratedKeys();
			
			rs.next();
			
			Long id = rs.getLong(1);
			
			return id;
						
		} catch (SQLException e) {
			throw new RuntimeException("Error en la insert", e);
		}	
		
	}
	
	//TODO
	//Insertar agarres con coordenadas
	
	//TODO
	//Juntar ambos inserts: pasar el id de foto generado al "subir la f.url"
	// pasar el array de agarres con id y coordenadas
//	public static FotoDTO insertar (UrlFotoDTO urlFoto, InsertarFotoDTO insertarFoto) {
//		
//		Long id = insertarUrl(urlFoto);
//		
//		try (Connection con = AccesoDatos.obtenerConexion();
//				PreparedStatement pst = con.prepareStatement(SQL_INSERT)) {
//			
//			pst.setLong(1, id);
//			pst.setLong(2, insertarFoto.);
//			
//			
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
		
		
		
	}

}
