
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int opc = 0;
		BDController controladorBD = new BDController();
		Scanner sc = new Scanner(System.in);
		Scanner sn = new Scanner(System.in);

		do {
			Main.mostrarMenu();
			opc = sn.nextInt();
			switch (opc) {
			case 1:
				for (int i = 0; i < controladorBD.listadoArtistas().size(); i++) {
					System.out.print("Nombre: ");
					System.out.print(controladorBD.listadoArtistas().get(i).getNombre());
					System.out.print(" DNI: ");
					System.out.println(controladorBD.listadoArtistas().get(i).getDni());
				}
				System.out.println("--------------");
				break;
			case 2:
				System.out.println("Introduce una letra");
				String letra = sc.nextLine();
				for (int i = 0; i < controladorBD.listadoArtistasPorLetra(letra).size(); i++) {
					System.out.print("Nombre: ");
					System.out.print(controladorBD.listadoArtistasPorLetra(letra).get(i).getNombre());
					System.out.print(" DNI: ");
					System.out.println(controladorBD.listadoArtistasPorLetra(letra).get(i).getDni());
				}
				break;
			case 3:
				boolean existe;
				System.out.println("¿Quieres dar de alta o de baja a un artista?");
				System.out.println("1. Baja");
				System.out.println("2. Alta");
				int opc2 = sn.nextInt();
				String dni;
				if (opc2 == 1) {
					System.out.println("Introduce el dni de un artista");
					dni = sc.nextLine();
					boolean encontrado = false;
					if (controladorBD.existeArtista(dni)) {
						controladorBD.dardeBajaArtista(dni);
						encontrado = true;
					}
					if (encontrado == false) {
						System.out.println("No hay ningún artista con ese dni");
					}
				} else if (opc2 == 2) {
					do {
						System.out.println("Introduce el dni del artista");
						dni = sc.nextLine();
						existe = controladorBD.existeArtista(dni);
						if (existe == true) {
							System.out.println("Ese dni ya existe");
						}
					} while (existe == true);
					System.out.println("Introduce el nombre del artista");
					String nombre = sc.nextLine();
					controladorBD.dardeAltaArtista(new Artista(dni, nombre));
				}
				break;
			case 4:
				System.out.println("Introduce una letra");
				String numero = sc.nextLine();
				for (int i = 0; i < controladorBD.listadoArtistasPorNumero(numero).size(); i++) {
					System.out.print("Nombre: ");
					System.out.print(controladorBD.listadoArtistasPorNumero(numero).get(i).getNombre());
					System.out.print(" DNI: ");
					System.out.println(controladorBD.listadoArtistasPorNumero(numero).get(i).getDni());
				}
				break;
			case 5:
				for (int i = 0; i < controladorBD.listadoCanciones().size(); i++) {
					System.out.print("Título: ");
					System.out.print(controladorBD.listadoCanciones().get(i).getTitulo());
					System.out.print(" Código: ");
					System.out.print(controladorBD.listadoCanciones().get(i).getCod());
					System.out.print(" Duración: ");
					System.out.println(controladorBD.listadoCanciones().get(i).getDuracion());
				}
				System.out.println("--------------");
				break;
			case 6:
				System.out.print("Título: ");
				System.out.print(controladorBD.cancionmasLarga().getTitulo());
				System.out.print(" Código: ");
				System.out.print(controladorBD.cancionmasLarga().getCod());
				System.out.print(" Duración: ");
				System.out.println(controladorBD.cancionmasLarga().getDuracion());
				System.out.println("--------------");
				break;
			case 7:
				System.out.println("Introduce una duracion");
				double duracion = sn.nextDouble();
				ArrayList<Cancion> canciones = controladorBD.cancionesMenorLongitud(duracion);
				for (int i = 0; i < canciones.size(); i++) {
					System.out.print("Título: ");
					System.out.print(canciones.get(i).getTitulo());
					System.out.print(" Código: ");
					System.out.print(canciones.get(i).getCod());
					System.out.print(" Duración: ");
					System.out.println(canciones.get(i).getDuracion());
				}
				System.out.println("--------------");
				break;
			case 8:
				System.out.println("Introduce el nombre de un disco");
				String disco = sc.nextLine();
				for (int i = 0; i < controladorBD.cancionesDeUnDisco(disco).size(); i++) {
					System.out.print("Título: ");
					System.out.println(controladorBD.cancionesDeUnDisco(disco).get(i).getTitulo());
				}
				System.out.println("--------------");
				break;
			case 9:
				existe = false;
				System.out.println("¿Quieres dar de alta o de baja una canción?");
				System.out.println("1. Baja");
				System.out.println("2. Alta");
				opc2 = sn.nextInt();
				String cod;
				if (opc2 == 1) {
					System.out.println("Introduce el código de la canción");
					cod = sc.nextLine();
					boolean encontrado = false;
					if (controladorBD.existeCancion(cod)) {
						controladorBD.dardeBajaCancion(cod);
						encontrado = true;
					}
					if (encontrado == false) {
						System.out.println("No hay ninguna canción con ese código");
					}
				} else if (opc2 == 2) {
					do {
						System.out.println("Introduce el código de la canción");
						cod = sc.nextLine();
						existe = controladorBD.existeCancion(cod);
						if (existe == true) {
							System.out.println("Ese código ya existe");
						}
					} while (existe == true);
					System.out.println("Introduce el ntítulo de la canción");
					String titulo = sc.nextLine();
					System.out.println("Introduce la duración de la canción");
					duracion = sn.nextDouble();
					controladorBD.dardeAltaCancion(new Cancion(cod, titulo, duracion));
				}
				break;
			case 10:
				ArrayList<Grupo> grupos = controladorBD.listadoGrupos();
				for (int i = 0; i < grupos.size(); i++) {
					System.out.print("Código: ");
					System.out.print(grupos.get(i).getCod());
					System.out.print(" Nombre: ");
					System.out.print(grupos.get(i).getNombre());
					System.out.print(" Fecha: ");
					System.out.print(grupos.get(i).getFecha());
					System.out.print(" Pais: ");
					System.out.println(grupos.get(i).getPais());
				}
				System.out.println("--------------");
				break;
			case 11:
				ArrayList<Disco> discos = controladorBD.listadoDiscos();
				for (int i = 0; i < discos.size(); i++) {
					System.out.print("Código: ");
					System.out.print(discos.get(i).getCod());
					System.out.print(" Nombre: ");
					System.out.print(discos.get(i).getNombre());
					System.out.print(" Fecha: ");
					System.out.print(discos.get(i).getFecha());
					System.out.print(" Código de compañía: ");
					System.out.print(discos.get(i).getCod_comp());
					System.out.print(" Código de grupo: ");
					System.out.println(discos.get(i).getCod_gru());

				}
				System.out.println("--------------");
				break;
			case 12:
				System.out.println("Introduce el nombre del grupo");
				String nombre_grupo = sc.nextLine();
				ArrayList<Artista> artistas = controladorBD.listadoArtistasGrupo(nombre_grupo);
				for (int i = 0; i < artistas.size(); i++) {
					System.out.print("Nombre: ");
					System.out.print(artistas.get(i).getNombre());
					System.out.print(" DNI: ");
					System.out.println(artistas.get(i).getDni());
				}
				break;
			case 13:
				ArrayList<Cancion> listaCanciones = controladorBD.listadoCanciones();
				for (int i = 0; i < listaCanciones.size(); i++) {
					System.out.print("Título: ");
					System.out.print(listaCanciones.get(i).getTitulo());
					System.out.print(" Código: ");
					System.out.print(listaCanciones.get(i).getCod());
					System.out.print(" Duración: ");
					System.out.println(listaCanciones.get(i).getDuracion());
				}
				System.out.println("--------------");
				System.out.println("Introduce el código de la canción");
				String cod_cancion = sc.nextLine();
				if (controladorBD.existeCancion(cod_cancion)) {
					discos = controladorBD.listadoDiscos();
					for (int i = 0; i < discos.size(); i++) {
						System.out.print("Código: ");
						System.out.print(discos.get(i).getCod());
						System.out.print(" Nombre: ");
						System.out.print(discos.get(i).getNombre());
						System.out.print(" Fecha: ");
						System.out.print(discos.get(i).getFecha());
						System.out.print(" Código de compañía: ");
						System.out.print(discos.get(i).getCod_comp());
						System.out.print(" Código de grupo: ");
						System.out.println(discos.get(i).getCod_gru());

					}
					System.out.println("--------------");
					System.out.println("Introduce el código del disco");
					String cod_dis = sc.nextLine();
					if (controladorBD.existeDisco(cod_dis)) {
						if (!controladorBD.existeCancionenDisco(cod_cancion, cod_dis)) {
							controladorBD.dardeAltaEsta(cod_cancion, cod_dis);
						} else {
							System.out.println("Esa canción ya está en el disco");
						}
					} else {
						System.out.println("No existe tal disco");
					}
				} else {
					System.out.println("No existe tal cancion");
				}
				break;

			case 14:
				ArrayList<Artista> listaArtistas = controladorBD.listadoArtistas();
				for (int i = 0; i < listaArtistas.size(); i++) {
					System.out.print("Nombre: ");
					System.out.print(listaArtistas.get(i).getNombre());
					System.out.print(" DNI: ");
					System.out.println(listaArtistas.get(i).getDni());
				}
				System.out.println("--------------");
				System.out.println("Introduce el dni del artista");
				String dni_artista = sc.nextLine();
				if (controladorBD.existeArtista(dni_artista)) {
					grupos = controladorBD.listadoGrupos();
					for (int i = 0; i < grupos.size(); i++) {
						System.out.print("Código: ");
						System.out.print(grupos.get(i).getCod());
						System.out.print(" Nombre: ");
						System.out.print(grupos.get(i).getNombre());
						System.out.print(" Fecha: ");
						System.out.print(grupos.get(i).getFecha());
						System.out.print(" Pais: ");
						System.out.println(grupos.get(i).getPais());
					}
					System.out.println("--------------");
					System.out.println("Introduce el código del grupo");
					String cod_gru = sc.nextLine();
					if (controladorBD.existeGrupo(cod_gru)) {
						if (!controladorBD.existeArtistaenGrupo(dni_artista, cod_gru)) {
							System.out.println("Introduce la funcion del artista");
							String funcion = sc.nextLine();
							controladorBD.dardeAltaPertenece(dni_artista, cod_gru, funcion);
						} else {
							System.out.println("Ese artista ya está en el grupo");
						}
					} else {
						System.out.println("No existe tal grupo");
					}
				} else {
					System.out.println("No existe tal artista");
				}

				break;
			case 15:
				discos = controladorBD.listadoDiscos();
				for (int i = 0; i < discos.size(); i++) {
					System.out.print("Código: ");
					System.out.print(discos.get(i).getCod());
					System.out.print(" Nombre: ");
					System.out.print(discos.get(i).getNombre());
					System.out.print(" Fecha: ");
					System.out.print(discos.get(i).getFecha());
					System.out.print(" Código de compañía: ");
					System.out.print(discos.get(i).getCod_comp());
					System.out.print(" Código de grupo: ");
					System.out.println(discos.get(i).getCod_gru());

				}
				System.out.println("--------------");
				System.out.println("Introduce el código del disco del que euiqres cambiar la compañía");
				String cod_disco = sc.nextLine();
				if (controladorBD.existeDisco(cod_disco)) {
					ArrayList<Companyia> companyias = controladorBD.listadoCompanyias();
					for (int i = 0; i < companyias.size(); i++) {
						System.out.print(" Código: ");
						System.out.print(companyias.get(i).getCod());
						System.out.print(" Nombre: ");
						System.out.print(companyias.get(i).getNombre());
						System.out.print(" Dirección: ");
						System.out.print(companyias.get(i).getDir());
						System.out.print(" Fax: ");
						System.out.print(companyias.get(i).getFax());
						System.out.print(" Teléfono: ");
						System.out.println(companyias.get(i).getTfno());
					}
					System.out.println("--------------");
					System.out.println("Introduce el código de la compañía");
					String cod_companyia = sc.nextLine();
					if (controladorBD.existeCompanya(cod_companyia)
							&& !controladorBD.existeCompanyaEnDisco(cod_companyia, cod_disco)) {
						controladorBD.cambiarCompanyia(cod_companyia, cod_disco);
					} else {
						System.out.println("No existe tal compañía o el disco ya pertenece a ella");
					}
				} else {
					System.out.println("No existe tal disco");
				}
				break;
			case 16:
				ArrayList<Club> clubs = controladorBD.listadoClubs();
				for (int i = 0; i < clubs.size(); i++) {
					System.out.print("Código: ");
					System.out.print(clubs.get(i).getCod());
					System.out.print(" Nombre: ");
					System.out.print(clubs.get(i).getNombre());
					System.out.print(" Sede: ");
					System.out.print(clubs.get(i).getSede());
					System.out.print(" Número: ");
					System.out.print(clubs.get(i).getNum());
					System.out.print(" Código de grupo: ");
					System.out.println(clubs.get(i).getCod_gru());

				}
				System.out.println("--------------");
				System.out.println("Introduce el código del club");
				String cod_club = sc.nextLine();
				if (controladorBD.existeClub(cod_club)) {
					grupos = controladorBD.listadoGruposClub(cod_club);
					for (int i = 0; i < grupos.size(); i++) {
						System.out.print("Código: ");
						System.out.print(grupos.get(i).getCod());
						System.out.print(" Nombre: ");
						System.out.print(grupos.get(i).getNombre());
						System.out.print(" Fecha: ");
						System.out.print(grupos.get(i).getFecha());
						System.out.print(" Pais: ");
						System.out.println(grupos.get(i).getPais());
					}
					System.out.println("--------------");
				} else {
					System.out.println("Ese club no existe");
				}
				break;
			case 17:
				artistas = controladorBD.listadoArtistasSinGrupo();
				for (int i = 0; i < artistas.size(); i++) {
					System.out.print("Nombre: ");
					System.out.print(artistas.get(i).getNombre());
					System.out.print(" DNI: ");
					System.out.println(artistas.get(i).getDni());
				}
				System.out.println("--------------");
				break;
			case 18:
				canciones = controladorBD.listadoCancionesSinDisco();
				for (int i = 0; i < canciones.size(); i++) {
					System.out.print("Título: ");
					System.out.print(canciones.get(i).getTitulo());
					System.out.print(" Código: ");
					System.out.print(canciones.get(i).getCod());
					System.out.print(" Duración: ");
					System.out.println(canciones.get(i).getDuracion());
				}
				break;
			case 19:
				discos = controladorBD.listadoDiscosMasSeisCanciones();
				for (int i = 0; i < discos.size(); i++) {
					System.out.print("Código: ");
					System.out.print(discos.get(i).getCod());
					System.out.print(" Nombre: ");
					System.out.print(discos.get(i).getNombre());
					System.out.print(" Fecha: ");
					System.out.print(discos.get(i).getFecha());
					System.out.print(" Código de compañía: ");
					System.out.print(discos.get(i).getCod_comp());
					System.out.print(" Código de grupo: ");
					System.out.println(discos.get(i).getCod_gru());

				}
				System.out.println("--------------");
			case 20:
				listaCanciones = controladorBD.listadoCanciones();
				for (int i = 0; i < listaCanciones.size(); i++) {
					System.out.print("Título: ");
					System.out.print(listaCanciones.get(i).getTitulo());
					System.out.print(" Código: ");
					System.out.print(listaCanciones.get(i).getCod());
					System.out.print(" Duración: ");
					System.out.println(listaCanciones.get(i).getDuracion());
				}
				System.out.println("--------------");
				System.out.println("Introduce el código de la canción");
				cod_cancion = sc.nextLine();
				if (controladorBD.existeCancion(cod_cancion)) {
					discos = controladorBD.listadoDiscosWhereCancion(cod_cancion);
					for (int i = 0; i < discos.size(); i++) {
						System.out.print("Código: ");
						System.out.print(discos.get(i).getCod());
						System.out.print(" Nombre: ");
						System.out.print(discos.get(i).getNombre());
						System.out.print(" Fecha: ");
						System.out.print(discos.get(i).getFecha());
						System.out.print(" Código de compañía: ");
						System.out.print(discos.get(i).getCod_comp());
						System.out.print(" Código de grupo: ");
						System.out.println(discos.get(i).getCod_gru());

					}
					System.out.println("--------------");
				} else {
					System.out.println("No existe tal cancion");
				}
				break;
			case 21:
				listaArtistas = controladorBD.listadoArtistas();
				for (int i = 0; i < listaArtistas.size(); i++) {
					System.out.print("Nombre: ");
					System.out.print(listaArtistas.get(i).getNombre());
					System.out.print(" DNI: ");
					System.out.println(listaArtistas.get(i).getDni());
				}
				System.out.println("--------------");
				System.out.println("Introduce el dni del artista");
				dni_artista = sc.nextLine();
				if (controladorBD.existeArtista(dni_artista)) {
					grupos = controladorBD.listadoGruposWhereArtista(dni_artista);
					for (int i = 0; i < grupos.size(); i++) {
						System.out.print("Código: ");
						System.out.print(grupos.get(i).getCod());
						System.out.print(" Nombre: ");
						System.out.print(grupos.get(i).getNombre());
						System.out.print(" Fecha: ");
						System.out.print(grupos.get(i).getFecha());
						System.out.print(" Pais: ");
						System.out.println(grupos.get(i).getPais());
					}
					System.out.println("--------------");
				} else {
					System.out.println("No existe tal artista");
				}
				break;
			case 22:
				grupos = controladorBD.listadoGrupos();
				for (int i = 0; i < grupos.size(); i++) {
					System.out.print("Código: ");
					System.out.print(grupos.get(i).getCod());
					System.out.print(" Nombre: ");
					System.out.print(grupos.get(i).getNombre());
					System.out.print(" Fecha: ");
					System.out.print(grupos.get(i).getFecha());
					System.out.print(" Pais: ");
					System.out.println(grupos.get(i).getPais());
				}
				System.out.println("--------------");
				System.out.println("Introduce el código de un grupo");
				String cod_gru = sc.nextLine();
				if (controladorBD.existeGrupo(cod_gru)) {
					clubs = controladorBD.listadoClubsWhereGrupo(cod_gru);
					for (int i = 0; i < clubs.size(); i++) {
						System.out.print("Código: ");
						System.out.print(clubs.get(i).getCod());
						System.out.print(" Nombre: ");
						System.out.print(clubs.get(i).getNombre());
						System.out.print(" Sede: ");
						System.out.print(clubs.get(i).getSede());
						System.out.print(" Número: ");
						System.out.print(clubs.get(i).getNum());
						System.out.print(" Código de grupo: ");
						System.out.println(clubs.get(i).getCod_gru());

					}
					System.out.println("--------------");
				} else {
					System.out.println("No existe tal grupo");
				}
				break;
			case 23:
				ArrayList<Companyia> companyias = controladorBD.listadoCompanyias();
				for (int i = 0; i < companyias.size(); i++) {
					System.out.print(" Código: ");
					System.out.print(companyias.get(i).getCod());
					System.out.print(" Nombre: ");
					System.out.print(companyias.get(i).getNombre());
					System.out.print(" Dirección: ");
					System.out.print(companyias.get(i).getDir());
					System.out.print(" Fax: ");
					System.out.print(companyias.get(i).getFax());
					System.out.print(" Teléfono: ");
					System.out.println(companyias.get(i).getTfno());
				}
				System.out.println("--------------");
				System.out.println("Introduce el código de la compañía");
				String cod_companyia = sc.nextLine();
				int opc3;
				if (controladorBD.existeCompanya(cod_companyia)) {
					do {
						System.out.println("Selecciona el dato que quieres cambiar");
						System.out.println("1. Nombre");
						System.out.println("2. Dirección");
						System.out.println("3. Fax");
						System.out.println("4. Teléfono");
						System.out.println("5. Ninguno");
						opc3 = sn.nextInt();
						switch (opc3) {
						case 1:
							System.out.println("Introduce el nuevo nombre");
							String nombre = sc.nextLine();
							controladorBD.cambiarNombreCompanyia(cod_companyia, nombre);
							break;
						case 2:
							System.out.println("Introduce la nueva direccion");
							String direccion = sc.nextLine();
							controladorBD.cambiaDireccionCompanyia(cod_companyia, direccion);
							break;
						case 3:
							System.out.println("Introduce el nuevo fax");
							String fax = sc.nextLine();
							controladorBD.cambiaFaxCompanyia(cod_companyia, fax);
							break;
						case 4:
							System.out.println("Introduce el nuevo teléfono");
							String tfno = sc.nextLine();
							controladorBD.cambiaTfnoCompanyia(cod_companyia, tfno);
							break;
						}
					} while (opc3 != 5);

				} else {
					System.out.println("No existe tal campañía");
				}
				break;
			case 24:
				System.out.println("Adiós");
				break;
			default:
				System.out.println("Error");
				break;
			}
		} while (opc != 24);

	}

	public static void mostrarMenu() {
		System.out.println("Selecciona una opción");
		System.out.println("1. Listado de artistas");
		System.out.println("2. Listado de artistas que empicen por una letra");
		System.out.println("3. Alta o baja de artista");
		System.out.println("4. Lista de artistas que su dni acabe en una letra");
		System.out.println("5. Listado de canciones");
		System.out.println("6. Canción más larga");
		System.out.println("7. Canciones con una duración menor a la que elijas");
		System.out.println("8. Listado canciones de un disco");
		System.out.println("9. Dar de alta una cancion");
		System.out.println("10. Listado de grupos");
		System.out.println("11. Listado de discos");
		System.out.println("12. Listado de artistas de un grupo");
		System.out.println("13. Meter una canción en un disco");
		System.out.println("14. Meter artista en grupo");
		System.out.println("15. Modificar compañía de disco");
		System.out.println("16. Mostrar los datos de un grupo que toca en un club");
		System.out.println("17. Listado de artistas que no tienen grupo");
		System.out.println("18. Listado de canciones sin disco");
		System.out.println("19. Mostrar título de discos con más de 6 canciones");
		System.out.println("20. Mostrar la informacion de todos los discos en los que está una canción");
		System.out.println("21. Mostrar la información de todos los grupos en los que está un artista");
		System.out.println("22. Mostrar la información de todos los clubes donde toca un grupo");
		System.out.println("23. Modificar datos de compañía.");
		System.out.println("24. Salir");
	}
}
