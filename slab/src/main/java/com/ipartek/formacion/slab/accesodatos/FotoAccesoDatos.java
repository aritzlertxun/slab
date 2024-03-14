package com.ipartek.formacion.slab.accesodatos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.ipartek.formacion.slab.dtos.AgarreDTO;
import com.ipartek.formacion.slab.dtos.InsertFotoDTO;

//TODO
public class FotoAccesoDatos {
	// TODO
	// private static final String SQL_INSERT = "INSERT INTO fotos_has_agarres
	// (fotos_id, agarres_id, coordenadas) VALUES (?,?,?)";

	private static final String SQL_INSERT_URL = "INSERT INTO fotos (url) VALUES (?)";
	private static final String SQL_INSERT_FOTO_AGARRES_COORDENADAS = "INSERT INTO agarres (fotos_id, tipos_id, coordenadas) VALUES (?,?,?)";

	// TODO
	public static void insertar (InsertFotoDTO insertFoto) {
		
		try (Connection con = AccesoDatos.obtenerConexion();
				PreparedStatement pst = con.prepareStatement(SQL_INSERT_URL, Statement.RETURN_GENERATED_KEYS)) {
			
			pst.setString(1, insertFoto.url());
			pst.executeUpdate();
			
			var rs = pst.getGeneratedKeys();
			
			rs.next();
			
			Long idFoto = rs.getLong(1);}
		
	//insert de agarres con coordenadas??
		//después en el rest la llamada a los que haga falta
	public static void insertarAgarres (DetalleInsertFotoDTO detalleInsertFoto) {
				
				try (Connection con = AccesoDatos.obtenerConexion();		
			
			PreparedStatement pst2 = con.prepareStatement(SQL_INSERT_FOTO_AGARRES_COORDENADAS);){
			
			pst2.setLong(1, idFoto);
			
			
			
			ArrayList<AgarreDTO> agarres = null;
			
			for( int i = 0; i < agarres.size(); i++) {

				
				pst2.getLong(2, agarre.id());
				pst2.setString(3, agarre.coordenadas());
				AgarreDTO agarre = new AgarreDTO(null, null);
				
				agarres.add(agarre);
				
			}
				
			
			
			pst2.executeQuery();
			
						
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
