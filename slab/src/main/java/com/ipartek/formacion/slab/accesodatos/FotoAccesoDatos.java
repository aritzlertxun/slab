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
	public static void insertar (FotoDTO foto) {
		
		try (Connection con = AccesoDatos.obtenerConexion();
				PreparedStatement pst = con.prepareStatement(SQL_INSERT_URL, Statement.RETURN_GENERATED_KEYS)) {
			
			pst.setString(1, foto.url());
			pst.executeUpdate();
			
			var rs = pst.getGeneratedKeys();
			
			rs.next();
			
			Long idFoto = rs.getLong(1);
		
			PreparedStatement pst2 = con.prepareStatement(SQL_INSERT_FOTO_AGARRES_COORDENADAS);
			
			pst2.setLong(1, idFoto);
			
			for(var agarre: foto.agarres()) {
				pst2.setLong(2, agarre.id());
				pst2.setString(3, agarre.coordenadas());
				
				pst2.executeUpdate();
			}
			
			
			
			
			
//			ArrayList<AgarreDTO> agarres = null;
//			
//			for( int i = 0; i < agarres.size(); i++) {
//
//				
//				pst2.getLong(2, agarre.id());
//				pst2.setString(3, agarre.coordenadas());
//				AgarreDTO agarre = new AgarreDTO(null, null);
//				
//				agarres.add(agarre);
//				
//			}
//				
//			
//			
//			pst2.executeQuery();
//			
						
		} catch (SQLException e) {
			throw new RuntimeException("Error en la insert", e);
		}	
		
	}

	// TODO
	// Juntar ambos inserts: pasar el id de foto generado al "subir la f.url"
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
