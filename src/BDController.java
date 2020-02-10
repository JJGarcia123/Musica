
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class BDController {
	private Connection conexion;

	public BDController() {
		super();
		try {
			this.conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/musica20", "root", "");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Error en el constructor vacio del BDController");
			e.printStackTrace();
		}
	}

	public Connection getConexion() {
		return conexion;
	}

	public void setConexion(Connection conexion) {
		this.conexion = conexion;
	}

	public ArrayList<Artista> listadoArtistas() {
		ArrayList<Artista> artistas = new ArrayList<Artista>();
		// Creo el objeto tipo statement para poder hacer la consulta
		try {
			Statement miStatement = this.conexion.createStatement();
			ResultSet rs = miStatement.executeQuery("Select * from artista");
			while (rs.next()) {
				artistas.add(new Artista(rs.getString("dni"), rs.getString("nombre")));
			}
			miStatement.close();
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Error en listadoArtistas");
			e.printStackTrace();
		}
		return artistas;
	}

	public ArrayList<Artista> listadoArtistasPorLetra(String letra) {
		ArrayList<Artista> artistas = new ArrayList<Artista>();
		// Creo el objeto tipo statement para poder hacer la consulta
		try {
			Statement miStatement = this.conexion.createStatement();
			String cadena = "Select * from artista where nombre like '" + letra + "%';";
			System.out.println(cadena);
			ResultSet rs = miStatement.executeQuery(cadena);
			while (rs.next()) {
				artistas.add(new Artista(rs.getString("dni"), rs.getString("nombre")));
			}
			miStatement.close();
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Error en listadoArtistasPorLetra");
			e.printStackTrace();
		}
		return artistas;
	}

	public void dardeAltaArtista(Artista artista) {
		try {
			// Creo el objeto tipo statement para poder hacer la consulta
			Statement miStatement = this.conexion.createStatement();
			String cadena = "Insert artista (dni, nombre) values ('" + artista.getDni() + "', '" + artista.getNombre()
					+ "');";
			miStatement.executeUpdate(cadena);
			miStatement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Error en dardeAltaArtista");
			e.printStackTrace();
		}
	}

	public void dardeBajaArtista(String dni) {
		try {
			// Creo el objeto tipo statement para poder hacer la consulta
			Statement miStatement = this.conexion.createStatement();
			String cadena = "delete from artista where dni='" + dni + "';";
			miStatement.executeUpdate(cadena);
			miStatement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Error en dardeBajaArtista");
			e.printStackTrace();
		}
	}

	public ArrayList<Artista> listadoArtistasPorNumero(String numero) {
		ArrayList<Artista> artistas = new ArrayList<Artista>();
		// Creo el objeto tipo statement para poder hacer la consulta
		try {
			Statement miStatement = this.conexion.createStatement();
			ResultSet rs = miStatement.executeQuery("Select * from artista where dni like '%" + numero + "';");
			while (rs.next()) {
				artistas.add(new Artista(rs.getString("dni"), rs.getString("nombre")));
			}
			miStatement.close();
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Error en listadoArtistasPorNumero");
			e.printStackTrace();
		}
		return artistas;
	}

	public ArrayList<Cancion> listadoCanciones() {
		ArrayList<Cancion> canciones = new ArrayList<Cancion>();
		// Creo el objeto tipo statement para poder hacer la consulta
		try {
			Statement miStatement = this.conexion.createStatement();
			ResultSet rs = miStatement.executeQuery("Select * from cancion");
			while (rs.next()) {
				canciones.add(new Cancion(rs.getString("cod"), rs.getString("titulo"), rs.getDouble("duracion")));
			}
			miStatement.close();
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Error en listadoCanciones");
			e.printStackTrace();
		}
		return canciones;
	}

	public Cancion cancionmasLarga() {
		ArrayList<Cancion> canciones = new ArrayList<Cancion>();
		try {
			Statement miStatement = this.conexion.createStatement();
			ResultSet rs = miStatement.executeQuery("Select * from cancion order by duracion desc limit 0,1");
			while (rs.next()) {
				canciones.add(new Cancion(rs.getString("cod"), rs.getString("titulo"), rs.getDouble("duracion")));
			}
			miStatement.close();
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Error en cancionMasLarga");
			e.printStackTrace();
		}
		return canciones.get(0);
	}

	public ArrayList<Cancion> cancionesMenorLongitud(double duracion) {
		ArrayList<Cancion> canciones = new ArrayList<Cancion>();
		try {
			Statement miStatement = this.conexion.createStatement();
			ResultSet rs = miStatement.executeQuery("Select * from cancion where duracion<" + duracion);
			while (rs.next()) {
				canciones.add(new Cancion(rs.getString("cod"), rs.getString("titulo"), rs.getDouble("duracion")));
			}
			miStatement.close();
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Error en cancionesMenorLongitud");
			e.printStackTrace();
		}
		return canciones;
	}

	public ArrayList<Cancion> cancionesDeUnDisco(String disco) {
		ArrayList<Cancion> canciones = new ArrayList<Cancion>();
		try {
			Statement miStatement = this.conexion.createStatement();
			ResultSet rs = miStatement
					.executeQuery("Select cancion.titulo from cancion, esta, disco where disco.nombre = '" + disco
							+ "' and cancion.cod = esta.can and esta.cod = disco.cod order by cancion.titulo desc");
			while (rs.next()) {
				canciones.add(new Cancion(rs.getString("titulo")));
			}
			miStatement.close();
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Error en cancionesDeUnDisco");
			e.printStackTrace();
		}
		return canciones;
	}

	public boolean existeArtista(String dni) {
		boolean existe = false;
		try {
			Statement miStatement = this.conexion.createStatement();
			String cadena = "Select dni from artista where dni='" + dni + "';";
			System.out.println(cadena);
			ResultSet rs = miStatement.executeQuery(cadena);
			while (rs.next()) {
				existe = true;
			}
			miStatement.close();
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Error en existeArtista");
			e.printStackTrace();

		}
		return existe;
	}

	public boolean existeCancion(String cod) {
		boolean existe = false;
		try {
			Statement miStatement = this.conexion.createStatement();
			String cadena = "Select cod from cancion where cod='" + cod + "';";
			System.out.println(cadena);
			ResultSet rs = miStatement.executeQuery(cadena);
			while (rs.next()) {
				existe = true;
			}
			miStatement.close();
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Error en existeCancion");
			e.printStackTrace();

		}
		return existe;
	}

	public void dardeAltaCancion(Cancion cancion) {
		try {
			// Creo el objeto tipo statement para poder hacer la consulta
			Statement miStatement = this.conexion.createStatement();
			String cadena = "Insert cancion (cod, titulo, duracion) values ('" + cancion.getCod() + "', '"
					+ cancion.getTitulo() + "', '" + cancion.getDuracion() + "');";
			System.out.println(cadena);
			miStatement.executeUpdate(cadena);
			miStatement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Error en dardeAltaCancion");
			e.printStackTrace();
		}
	}

	public void dardeBajaCancion(String cod) {
		try {
			// Creo el objeto tipo statement para poder hacer la consulta
			Statement miStatement = this.conexion.createStatement();
			String cadena = "delete from cancion where cod='" + cod + "';";
			System.out.println(cadena);
			miStatement.executeUpdate(cadena);
			miStatement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Error en dardeBajaCancion");
			e.printStackTrace();
		}
	}

	public ArrayList<Grupo> listadoGrupos() {
		ArrayList<Grupo> grupos = new ArrayList<Grupo>();
		// Creo el objeto tipo statement para poder hacer la consulta
		try {
			Statement miStatement = this.conexion.createStatement();
			String cadena = "Select * from grupo";
			System.out.println(cadena);
			ResultSet rs = miStatement.executeQuery(cadena);
			while (rs.next()) {
				grupos.add(new Grupo(rs.getString("cod"), rs.getString("nombre"), rs.getString("fecha"),
						rs.getString("pais")));
			}
			miStatement.close();
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Error en listadoGrupos");
			e.printStackTrace();
		}
		return grupos;
	}

	public ArrayList<Disco> listadoDiscos() {
		ArrayList<Disco> discos = new ArrayList<Disco>();
		// Creo el objeto tipo statement para poder hacer la consulta
		try {
			Statement miStatement = this.conexion.createStatement();
			String cadena = "Select * from disco";
			System.out.println(cadena);
			ResultSet rs = miStatement.executeQuery(cadena);
			while (rs.next()) {
				discos.add(new Disco(rs.getString("cod"), rs.getString("nombre"), rs.getString("fecha"),
						rs.getString("cod_comp"), rs.getString("cod_gru")));
			}
			miStatement.close();
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Error en listadoDiscos");
			e.printStackTrace();
		}
		return discos;
	}

	public ArrayList<Artista> listadoArtistasGrupo(String nombre_grupo) {
		ArrayList<Artista> artistas = new ArrayList<Artista>();
		// Creo el objeto tipo statement para poder hacer la consulta
		try {
			Statement miStatement = this.conexion.createStatement();
			String cadena = "Select * from artista where dni in(select dni from pertenece where cod in(select cod from grupo where nombre='"
					+ nombre_grupo + "'));";
			System.out.println(cadena);
			ResultSet rs = miStatement.executeQuery(cadena);
			while (rs.next()) {
				artistas.add(new Artista(rs.getString("dni"), rs.getString("nombre")));
			}
			miStatement.close();
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Error en listadoArtistasGrupo");
			e.printStackTrace();
		}
		return artistas;
	}

	public boolean existeDisco(String cod) {
		boolean existe = false;
		try {
			Statement miStatement = this.conexion.createStatement();
			String cadena = "Select cod from disco where cod='" + cod + "';";
			System.out.println(cadena);
			ResultSet rs = miStatement.executeQuery(cadena);
			while (rs.next()) {
				existe = true;
			}
			miStatement.close();
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Error en existeDisco");
			e.printStackTrace();

		}
		return existe;
	}

	public void dardeAltaEsta(String cod_can, String cod_dis) {
		try {
			// Creo el objeto tipo statement para poder hacer la consulta
			Statement miStatement = this.conexion.createStatement();
			String cadena = "Insert esta values ('" + cod_can + "', '" + cod_dis + "');";
			System.out.println(cadena);
			miStatement.executeUpdate(cadena);
			miStatement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Error en dardeEsta");
			e.printStackTrace();
		}
	}

	public boolean existeGrupo(String cod) {
		boolean existe = false;
		try {
			Statement miStatement = this.conexion.createStatement();
			String cadena = "Select cod from grupo where cod='" + cod + "';";
			System.out.println(cadena);
			ResultSet rs = miStatement.executeQuery(cadena);
			while (rs.next()) {
				existe = true;
			}
			miStatement.close();
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Error en existeGrupo");
			e.printStackTrace();

		}
		return existe;
	}

	public void dardeAltaPertenece(String dni_artista, String cod_gru, String funcion) {
		try {
			// Creo el objeto tipo statement para poder hacer la consulta
			Statement miStatement = this.conexion.createStatement();
			String cadena = "Insert pertenece values ('" + dni_artista + "', '" + cod_gru + "', '" + funcion + "');";
			System.out.println(cadena);
			miStatement.executeUpdate(cadena);
			miStatement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Error en dardeAltaPertenece");
			e.printStackTrace();
		}
	}

	public boolean existeCancionenDisco(String cod_cancion, String cod_disco) {
		boolean existe = false;
		try {
			Statement miStatement = this.conexion.createStatement();
			String cadena = "Select * from esta where can='" + cod_cancion + "' and cod='" + cod_disco + "';";
			System.out.println(cadena);
			ResultSet rs = miStatement.executeQuery(cadena);
			while (rs.next()) {
				existe = true;
			}
			miStatement.close();
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Error en existCancionenDisco");
			e.printStackTrace();

		}
		return existe;
	}

	public boolean existeArtistaenGrupo(String dni_artista, String cod_gru) {
		boolean existe = false;
		try {
			Statement miStatement = this.conexion.createStatement();
			String cadena = "Select * from pertenece where dni='" + dni_artista + "' and cod='" + cod_gru + "';";
			System.out.println(cadena);
			ResultSet rs = miStatement.executeQuery(cadena);
			while (rs.next()) {
				existe = true;
			}
			miStatement.close();
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Error en existeArtistaenGrupo");
			e.printStackTrace();

		}
		return existe;
	}

	public boolean existeCompanya(String cod_companyia) {
		boolean existe = false;
		try {
			Statement miStatement = this.conexion.createStatement();
			String cadena = "Select * from companyia where cod='" + cod_companyia + "';";
			System.out.println(cadena);
			ResultSet rs = miStatement.executeQuery(cadena);
			while (rs.next()) {
				existe = true;
			}
			miStatement.close();
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Error en existeCompanyia");
			e.printStackTrace();

		}
		return existe;
	}

	public ArrayList<Companyia> listadoCompanyias() {
		ArrayList<Companyia> companyias = new ArrayList<Companyia>();
		// Creo el objeto tipo statement para poder hacer la consulta
		try {
			Statement miStatement = this.conexion.createStatement();
			String cadena = "Select * from companyia";
			System.out.println(cadena);
			ResultSet rs = miStatement.executeQuery(cadena);
			while (rs.next()) {
				companyias.add(new Companyia(rs.getString("cod"), rs.getString("nombre"), rs.getString("dir"),
						rs.getString("fax"), rs.getString("tfno")));
			}
			miStatement.close();
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Error en listadoCompanyias");
			e.printStackTrace();
		}
		return companyias;
	}

	public boolean existeCompanyaEnDisco(String cod_companyia, String cod_disco) {
		boolean existe = false;
		try {
			Statement miStatement = this.conexion.createStatement();
			String cadena = "Select * from disco where cod_comp='" + cod_companyia + "' and cod='" + cod_disco + "';";
			System.out.println(cadena);
			ResultSet rs = miStatement.executeQuery(cadena);
			while (rs.next()) {
				existe = true;
			}
			miStatement.close();
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Error en existeCompanyiaEnDisco");
			e.printStackTrace();

		}
		return existe;
	}

	public void cambiarCompanyia(String cod_companyia, String cod_disco) {
		try {
			Statement miStatement = this.conexion.createStatement();
			String cadena = "Update disco set cod_comp='" + cod_companyia + "' where cod='" + cod_disco + "';";
			System.out.println(cadena);
			miStatement.executeUpdate(cadena);
			miStatement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Error en cambiarCompanyia");
			e.printStackTrace();
		}
	}

	public ArrayList<Club> listadoClubs() {
		ArrayList<Club> clubs = new ArrayList<Club>();
		// Creo el objeto tipo statement para poder hacer la consulta
		try {
			Statement miStatement = this.conexion.createStatement();
			String cadena = "Select * from club";
			System.out.println(cadena);
			ResultSet rs = miStatement.executeQuery(cadena);
			while (rs.next()) {
				clubs.add(new Club(rs.getString("cod"), rs.getString("nombre"), rs.getString("sede"),
						rs.getString("num"), rs.getString("cod_gru")));
			}
			miStatement.close();
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Error en listadoClubs");
			e.printStackTrace();
		}
		return clubs;
	}

	public boolean existeClub(String cod_club) {
		boolean existe = false;
		try {
			Statement miStatement = this.conexion.createStatement();
			String cadena = "Select * from club where cod='" + cod_club + "';";
			System.out.println(cadena);
			ResultSet rs = miStatement.executeQuery(cadena);
			while (rs.next()) {
				existe = true;
			}
			miStatement.close();
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Error en existeClub");
			e.printStackTrace();

		}
		return existe;
	}

	public ArrayList<Grupo> listadoGruposClub(String cod_club) {
		ArrayList<Grupo> grupos = new ArrayList<Grupo>();
		// Creo el objeto tipo statement para poder hacer la consulta
		try {
			Statement miStatement = this.conexion.createStatement();
			String cadena = "Select * from grupo where cod in(Select cod_gru from club where cod='" + cod_club + "');";
			System.out.println(cadena);
			ResultSet rs = miStatement.executeQuery(cadena);
			while (rs.next()) {
				grupos.add(new Grupo(rs.getString("cod"), rs.getString("nombre"), rs.getString("fecha"),
						rs.getString("pais")));
			}
			miStatement.close();
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Error en listadoGruposClub");
			e.printStackTrace();
		}
		return grupos;
	}

	public ArrayList<Artista> listadoArtistasSinGrupo() {
		ArrayList<Artista> artistas = new ArrayList<Artista>();
		// Creo el objeto tipo statement para poder hacer la consulta
		try {
			Statement miStatement = this.conexion.createStatement();
			ResultSet rs = miStatement
					.executeQuery("Select * from artista where dni not in(Select dni from pertenece)");
			while (rs.next()) {
				artistas.add(new Artista(rs.getString("dni"), rs.getString("nombre")));
			}
			miStatement.close();
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Error en listadoArtistasSinGrupo");
			e.printStackTrace();
		}
		return artistas;
	}

	public ArrayList<Cancion> listadoCancionesSinDisco() {
		ArrayList<Cancion> canciones = new ArrayList<Cancion>();
		// Creo el objeto tipo statement para poder hacer la consulta
		try {
			Statement miStatement = this.conexion.createStatement();
			ResultSet rs = miStatement.executeQuery("Select * from cancion where cod not in(select can from esta);");
			while (rs.next()) {
				canciones.add(new Cancion(rs.getString("cod"), rs.getString("titulo"), rs.getDouble("duracion")));
			}
			miStatement.close();
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Error en listadoCancionesSinDisco");
			e.printStackTrace();
		}
		return canciones;
	}

	public ArrayList<Disco> listadoDiscosMasSeisCanciones() {
		ArrayList<Disco> discos = new ArrayList<Disco>();
		// Creo el objeto tipo statement para poder hacer la consulta
		try {
			Statement miStatement = this.conexion.createStatement();
			String cadena = "select nombre from disco where cod in(select cod from esta where cod in(select cod from esta group by cod HAVING count(can)>6))";
			System.out.println(cadena);
			ResultSet rs = miStatement.executeQuery(cadena);
			while (rs.next()) {
				discos.add(new Disco(rs.getString("cod"), rs.getString("nombre"), rs.getString("fecha"),
						rs.getString("cod_comp"), rs.getString("cod_gru")));
			}
			miStatement.close();
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Error en listadoDiscos");
			e.printStackTrace();
		}
		return discos;
	}
	
	public ArrayList<Disco> listadoDiscosWhereCancion(String cod_cancion) {
		ArrayList<Disco> discos = new ArrayList<Disco>();
		// Creo el objeto tipo statement para poder hacer la consulta
		try {
			Statement miStatement = this.conexion.createStatement();
			String cadena = "select * from disco where cod in(select cod from esta where can='" + cod_cancion + "');";
			System.out.println(cadena);
			ResultSet rs = miStatement.executeQuery(cadena);
			while (rs.next()) {
				discos.add(new Disco(rs.getString("cod"), rs.getString("nombre"), rs.getString("fecha"),
						rs.getString("cod_comp"), rs.getString("cod_gru")));
			}
			miStatement.close();
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Error en listadoDiscosWhereCancion");
			e.printStackTrace();
		}
		return discos;
	}
	public ArrayList<Grupo> listadoGruposWhereArtista(String dni_artista) {
		ArrayList<Grupo> grupos = new ArrayList<Grupo>();
		// Creo el objeto tipo statement para poder hacer la consulta
		try {
			Statement miStatement = this.conexion.createStatement();
			String cadena = "Select * from grupo where cod in(select cod from pertenece where dni='" + dni_artista + "');";
			System.out.println(cadena);
			ResultSet rs = miStatement.executeQuery(cadena);
			while (rs.next()) {
				grupos.add(new Grupo(rs.getString("cod"), rs.getString("nombre"), rs.getString("fecha"),
						rs.getString("pais")));
			}
			miStatement.close();
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Error en listadoGruposWhereArtista");
			e.printStackTrace();
		}
		return grupos;
	}
	
	public ArrayList<Club> listadoClubsWhereGrupo(String cod_gru) {
		ArrayList<Club> clubs = new ArrayList<Club>();
		// Creo el objeto tipo statement para poder hacer la consulta
		try {
			Statement miStatement = this.conexion.createStatement();
			String cadena = "Select * from club where cod_gru='" + cod_gru + "';";
			System.out.println(cadena);
			ResultSet rs = miStatement.executeQuery(cadena);
			while (rs.next()) {
				clubs.add(new Club(rs.getString("cod"), rs.getString("nombre"), rs.getString("sede"),
						rs.getString("num"), rs.getString("cod_gru")));
			}
			miStatement.close();
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Error en listadoClubsWhereGrupo");
			e.printStackTrace();
		}
		return clubs;
	}
	
	public void cambiarNombreCompanyia(String cod_companyia, String nombre) {
		try {
			Statement miStatement = this.conexion.createStatement();
			String cadena = "Update companyia set nombre='" + nombre + "' where cod='" + cod_companyia + "';";
			System.out.println(cadena);
			miStatement.executeUpdate(cadena);
			miStatement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Error en cambiarNombreCompanyia");
			e.printStackTrace();
		}
	}
	
	public void cambiaDireccionCompanyia(String cod_companyia, String direccion) {
		try {
			Statement miStatement = this.conexion.createStatement();
			String cadena = "Update companyia set dir='" + direccion + "' where cod='" + cod_companyia + "';";
			System.out.println(cadena);
			miStatement.executeUpdate(cadena);
			miStatement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Error en cambiarDireccionCompanyia");
			e.printStackTrace();
		}
	}
	
	public void cambiaFaxCompanyia(String cod_companyia, String fax) {
		try {
			Statement miStatement = this.conexion.createStatement();
			String cadena = "Update companyia set fax='" + fax + "' where cod='" + cod_companyia + "';";
			System.out.println(cadena);
			miStatement.executeUpdate(cadena);
			miStatement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Error en cambiarFaxCompanyia");
			e.printStackTrace();
		}
	}
	
	public void cambiaTfnoCompanyia(String cod_companyia, String tfno) {
		try {
			Statement miStatement = this.conexion.createStatement();
			String cadena = "Update companyia set tfno='" + tfno + "' where cod='" + cod_companyia + "';";
			System.out.println(cadena);
			miStatement.executeUpdate(cadena);
			miStatement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Error en cambiarTfnoCompanyia");
			e.printStackTrace();
		}
	}


}
