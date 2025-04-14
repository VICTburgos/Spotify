import java.util.InputMismatchException;
import java.util.Scanner;


public class Principal {

	public static void main(String[] args) {

		Scanner s = new Scanner(System.in);
		final int CANT_CANCIONES = 80, CANT_ATRIBUTOS = 8,CANT_PLAYLISTS_MAX = 80;
		final int ID_MIN = 0, ID_MAX = 99999;
		String[][] canciones = new String[CANT_CANCIONES][CANT_ATRIBUTOS];  
		int cantCanciones = 0;
		int cantPlaylists = 0;
		String[][][] playlists = new String[CANT_PLAYLISTS_MAX][CANT_CANCIONES][CANT_ATRIBUTOS];
		String[] nombresPlaylists = new String[CANT_PLAYLISTS_MAX];
		
		boolean modoPrueba = false;
        System.out.println("¿Desea utilizar el modo de prueba? \n 0 : NO \n 1 : SÍ");

        int eleccion = ingresarEntero(s, 0, 1);

        modoPrueba = (eleccion == 1);

        if (modoPrueba) {
            cantCanciones = cargarDatosPrueba(cantCanciones, canciones);
        }
		
        int opc = 0;
        
        do {
        	opc = mostrarMenuYElegirOpcion(s);
        	int cancionBuscada = 0;
        	String infoCancion = "";
        	cantCanciones = generarAccion(s,opc,canciones,cantCanciones,ID_MIN,ID_MAX,CANT_ATRIBUTOS,cancionBuscada,infoCancion,opc,cantPlaylists,playlists,nombresPlaylists, CANT_CANCIONES);
        	
        }while(opc != 12);
        
	}
	
	private static int ingresarCancion(Scanner s, final String[][] CANCIONES, int cantCanciones, final int ID_MIN, final int ID_MAX) {
		int indiceIdBuscado = -1;
		int idCancion = 0;
		do {
			System.out.println("Ingrese ID de la canción");
			idCancion = ingresarEntero(s,ID_MIN,ID_MAX);
			indiceIdBuscado = verificarID(CANCIONES,0,idCancion,cantCanciones);
			if(indiceIdBuscado>=0) {
				System.out.println("El índice ingresado ya existe en el sistema");
				System.out.println("Vuelva a ingresar");
			}
		} while(indiceIdBuscado>=0);
		
		int indiceCadenaBuscada = -1;
		String nombreCancion;
		
		do {
			System.out.println("Ingrese título de la canción");
			nombreCancion = ingresarCadena(s);
			indiceCadenaBuscada = buscarCadenaEnMatriz(CANCIONES,1,nombreCancion,cantCanciones);
			if(indiceCadenaBuscada>=0) {
				System.out.println("El título ingresado ya existe en el sistema");
				System.out.println("Vuelva a ingresar");
			}
		}
		
		while (indiceCadenaBuscada>=0);

		String nombreArtista;
	
		System.out.println("Ingrese Nombre del artista");
		nombreArtista = ingresarCadena(s);
		
		String nombreAlbum;
		
		System.out.println("Ingrese género de la canción: 1 = Rock, 2 = Pop, 3 = Electrónica, 4 = Hip Hop, 5 = Clásica, 6 = Jazz, 7 = Otros");
		String genero = consultarGenero(s); 
		
		System.out.println("Ingrese duración de la canción en segundos");
		String duracion = formatearDuracion(s);
		
		do {
			System.out.println("Ingrese álbum al que pertenece");
			nombreAlbum = ingresarCadena(s);
			indiceCadenaBuscada = buscarCadenaEnMatriz(CANCIONES,1,nombreCancion,cantCanciones);
			if(indiceCadenaBuscada>=0) {
				System.out.println("El índice ingresado ya existe en el sistema");
				System.out.println("Vuelva a ingresar");
			}
		} 
		
		while (indiceCadenaBuscada>=0);
		
		int añoLanzamiento;
			
			System.out.println("Ingrese año de lanzamiento (1900 - 2025)");
			añoLanzamiento = ingresarEntero(s,1900,2025);
		
		int popularidad;
			
			System.out.println("Ingrese popularidad de la canción");
			popularidad = ingresarEntero(s,1,100);
		
		CANCIONES[cantCanciones][0] = String.valueOf(idCancion);
		CANCIONES[cantCanciones][1] = nombreCancion;
		CANCIONES[cantCanciones][2] = nombreArtista;
		CANCIONES[cantCanciones][3] = nombreAlbum;
		CANCIONES[cantCanciones][4] = genero;
		CANCIONES[cantCanciones][5] = duracion;
		CANCIONES[cantCanciones][6] = String.valueOf(añoLanzamiento);
		CANCIONES[cantCanciones][7] = String.valueOf(popularidad);
		
		
		return ++cantCanciones;
		
	}
	
	private static int mostrarMenuYElegirOpcion(Scanner s) {
		System.out.println(" --------------------------------------------");
        System.out.println("|                    MENU                    |");
        System.out.println(" --------------------------------------------");
        System.out.println("| 1) Agregar canción                         |");
        System.out.println("| 2) Consultar canción                       |");
        System.out.println("| 3) Modificar canción                       |");
        System.out.println("| 4) Eliminar canción                        |");
        System.out.println("| 5) Listar todas las canciones              |");
        System.out.println("| 6) Crear Playlist                          |");
        System.out.println("| 7) Buscar canciones por genero             |");
        System.out.println("| 8) Buscar canciones por artista            |");
        System.out.println("| 9) Buscar canciones por popularidad        |");    
        System.out.println("| 10) Calcular estadistica de la plataforma  |");
        System.out.println("| 11) Recomendar Canciones                   |");       
        System.out.println("| 12) salir                                  |");
        System.out.println(" --------------------------------------------");
		return ingresarEntero(s,1,12);
	}
	
	private static int generarAccion(Scanner s,final int OPC,final String[][] CANCIONES, int cantCanciones,final int ID_MIN,final int ID_MAX,final int CANT_ATRIBUTOS, int cancionBuscada, String infoCancion, int opc,int cantPlaylists, String[][][]PLAYLISTS,String nombresPlaylists[], final int CANT_CANCIONES) {
		switch(OPC) {
		case 1:
			if (cantCanciones < CANCIONES.length) {
				cantCanciones = ingresarCancion(s, CANCIONES, cantCanciones, ID_MIN, ID_MAX);
				System.out.println("¡Canción ingresada correctamente!");
			}
			else {
				System.out.println("Número Maximo de canciones alcanzado");
			}
			break;
		case 2:
			String informacionCancionEspecifica = consultarCancion(s,CANCIONES,cantCanciones, opc);
			System.out.println(informacionCancionEspecifica);
			break;
		case 3:
			cantCanciones = modificarCancion(s,CANCIONES,cantCanciones,ID_MIN,ID_MAX,CANT_ATRIBUTOS, opc);
			System.out.println("Información de la canción modificada");
			break;
		case 4:
			cantCanciones=eliminarCancion(s,CANCIONES,cantCanciones, opc);
			break;
		case 5: 
			System.out.println("Canciones Guardadas");
			cantCanciones= listarCanciones(CANCIONES, cantCanciones, infoCancion, cancionBuscada);
			break;
		case 6: 
		    cantPlaylists = crearPlaylist(s, CANT_CANCIONES, CANT_ATRIBUTOS, cantPlaylists, PLAYLISTS, nombresPlaylists, CANCIONES, cantCanciones);
		    break;
		case 7:
			cantCanciones = buscarCancionesPorGenero(s,cantCanciones,CANCIONES,infoCancion,cancionBuscada);
			break; 
		case 8:
			cantCanciones = buscarCancionesPorArtista(s,cantCanciones,CANCIONES,infoCancion,cancionBuscada);
			break;
		case 9:
			cantCanciones= buscarCancionesPopularidad(s, cantCanciones, CANCIONES, CANT_ATRIBUTOS, infoCancion, cancionBuscada);	
			break;
		case 10:
			cantCanciones= calcularEstadisticas(s, cantCanciones, CANCIONES);
			break;
		case 11:
			cantCanciones = RecomendarCanciones(s,cantCanciones,CANCIONES,CANT_ATRIBUTOS,infoCancion,cancionBuscada);
			break;
		case 12:
			System.out.println("¡Nos Vemos!");
			break;
		default:
			System.out.println("Número incorrecto. Vuelva a Ingresar");
			break;
		}
		return cantCanciones;
	}
	
	private static int RecomendarCanciones(Scanner s, int cantCanciones, String[][] CANCIONES, final int CANT_ATRIBUTOS, String infoCancion, int cancionBuscada) {
	    System.out.println("Ingrese método de recomendación: \n 0) Por Género \n 1) Por Artista");
	    int opc = ingresarEntero(s, 0, 1);

	    if (opc == 0) {
	        System.out.println("Ingrese género de interés: 1 = Rock , 2 = Pop , 3 = Electrónica, 4 = Hip Hop, 5 = Clásica, 6 = Jazz, 7 = Otros");
	        String interes = consultarGenero(s);
	        System.out.println("Recomendaciones para el género '" + interes + "'");


	    for (int i = 0; i < cantCanciones; i++) {
	    	if (CANCIONES[i][4].equalsIgnoreCase(interes)) {
	    		String artista = CANCIONES[i][2];

	    		for (int j = 0; j < cantCanciones; j++) {
	    			if (CANCIONES[j][2].equalsIgnoreCase(artista)) {
	    				System.out.println("---------------------------------------------------------------------------------------------------------------------------------");
	                    String info = mostrarInfoCancion(CANCIONES, cantCanciones, infoCancion, j);
	                    System.out.println(info);
	                }
	            }
	        }
	    }
	        
        	System.out.println("---------------------------------------------------------------------------------------------------------------------------------");
	 } 		else if (opc == 1) {
	        	System.out.println("Ingrese artista de interés:");
	        	String interes = ingresarCadena(s);
	        	System.out.println("Recomendaciones para el artista '" + interes + "'");

	        	for (int i = 0; i < cantCanciones; i++) {
	        		if (CANCIONES[i][2].equalsIgnoreCase(interes)) {
	        			String genero = CANCIONES[i][4];
	                
	        			for (int j = 0; j < cantCanciones; j++) {
	        				if (CANCIONES[j][4].equalsIgnoreCase(genero)) {
	        					System.out.println("---------------------------------------------------------------------------------------------------------------------------------");
	        					String info = mostrarInfoCancion(CANCIONES, cantCanciones, infoCancion, j);
	        					System.out.println(info);
	        			}
	               }
	           }
	       }
        	System.out.println("---------------------------------------------------------------------------------------------------------------------------------");
	   } 
	 		else {
	        System.out.println("Opción incorrecta.");
	    }

	    return cantCanciones;
	}


	
	private static int verificarID(final String[][] MATRIZ, final int IND_COL, final int ID_BUSCADA, final int LONGITUD) {
		int i = 0;		
		while(i<LONGITUD) {
			if(Integer.parseInt(MATRIZ[i][IND_COL]) == ID_BUSCADA) {
				return i;
			}			
			i++;
		}		
		return -1;
	}
	
	private static int buscarCancion(Scanner s,final String[][] CANCIONES, int cantCanciones, int opc) {
		System.out.println("Elija método de búsqueda \n 1) ID \n 2) TÍTULO \n 3) ARTISTA");		
		opc= ingresarEntero(s,1, 3);
		System.out.println("Ingrese la busqueda: ");
		String cancionBuscada = ingresarCadena(s);
	    int indiceCancionBuscada = -1; 
	        
		switch(opc){
		case 1:
			for(int i=0 ; i<cantCanciones ; i++) {
	            if(cancionBuscada.equals(CANCIONES[i][0])){
	                indiceCancionBuscada = i;
	                System.out.println("¡CANCIÓN ENCONTRADA! Su posición de memoria es: " + indiceCancionBuscada);
	            }
			}
			
			break;
			
		case 2:
			for(int i=0 ; i<cantCanciones ; i++) {
	            if(cancionBuscada.equals(CANCIONES[i][1])){
	                indiceCancionBuscada = i;
	                System.out.println("¡CANCION ENCONTRADA! Su posición de memoria es: " + indiceCancionBuscada);
	            }     
			}
			
			break;
			
		case 3:
			for(int i=0 ; i<cantCanciones ; i++) {
	            if(cancionBuscada.equals(CANCIONES[i][2])){
	                indiceCancionBuscada = i;
	                System.out.println("¡CANCIÓN ENCONTRADA! Su posición de memoria es: " + indiceCancionBuscada);
	            }  
			}
			
			break;	
			
		default:
			System.out.println("Vuelva a ingresar");	
			break;
		
		}
        	if (indiceCancionBuscada == -1){
        	System.out.println("Canción no encontrada");
        }
        return indiceCancionBuscada;
    }
	
	private static String consultarCancion(Scanner s,final String[][] CANCIONES, int cantCanciones, int opc) {
        String infoCancion = "";
        int cancionBuscada = buscarCancion(s,CANCIONES,cantCanciones, opc);
        return mostrarInfoCancion(CANCIONES, cantCanciones, infoCancion, cancionBuscada);
    }

	private static String mostrarInfoCancion(final String[][] CANCIONES, int cantCanciones, String infoCancion, int cancionBuscada) {
		for (int i = 0; i<cantCanciones;i++) {
            if (cancionBuscada == i) {
                infoCancion = ("ID: " + CANCIONES[i][0] + " | Título: " + CANCIONES[i][1] + " | Artista: " + CANCIONES[i][2] + " | Album: " + CANCIONES[i][3] + " | Género: " + CANCIONES[i][4] + " | Duración: " + CANCIONES[i][5] + " | Año de Lanzamiento: " + CANCIONES[i][6] + " | Popularidad: " + CANCIONES[i][7]);
            }
            	
        }
        return infoCancion;
        
	}
	
	private static int ingresarEntero(Scanner s, final int MIN, final int MAX) {
		int nro = 0;	

		boolean error = false;
		do {
			error = false;
			
			try {
				nro = s.nextInt();
				if(nro<MIN || nro>MAX) {
					error = true;
					System.out.println("Error. El número ingresado debe estar entre " + MIN + " y " + MAX);
					System.out.println("Vuelva a ingresar");
				}
			} catch(InputMismatchException e) {
				System.out.println("Error. Tipo de dato mal ingresado");
				System.out.println("Vuelva a ingresar");
				error = true;
			} catch(Exception e) {
				System.out.println("Error inesperado");
			} finally {
				s.nextLine();
			}
		} 
		while(error);
		
		return nro;
	}
	
	private static String ingresarCadena(Scanner s) {
		String cadena = "";
		boolean error = false;
		do {
			error = false;
			try {
				cadena = s.nextLine();
				if(cadena.isBlank()) {
					error = true;
					System.out.println("Cadena Vacía");
					System.out.println("Vuelva a ingresar");
				}
			} 
			
			catch(InputMismatchException e) {
				System.out.println("Error. Tipo de dato mal ingresado");
				System.out.println("Vuelva a ingresar");
				error = true;
			} catch(Exception e) {
			System.out.println("Error imprevisto");
			}
		} 
		
		while(error);
		
		return cadena;
		
	}
	
	private static int buscarCadenaEnMatriz(final String[][] MATRIZ, final int IND_COL, final String CADENA_BUSCADA, final int LONGITUD) {
		int i = 0;		
		while(i<LONGITUD) {
			if(MATRIZ[i][IND_COL].toLowerCase().equals(CADENA_BUSCADA.toLowerCase())) {
				return i;
			}	
			
			i++;
		}	
		return -1;
		
	}
	
	private static String consultarGenero(Scanner s) {
		String genero = "";
		int id_genero = ingresarEntero(s,1,7);
		if(id_genero == 1) {
			genero = "Rock";
		}
		
		else if(id_genero == 2) {
			genero = "Pop";
		}
		
		else if(id_genero == 3) {
			genero = "Electrónica";
		}
		
		else if(id_genero == 4) {
			genero = "Hip Hop";
		}
		
		else if(id_genero == 5) {
			genero = "Clásica";
		}
		
		else if(id_genero == 6) {
			genero = "Jazz";
		}
		
		else if(id_genero == 7) {
			genero = "Otros";
		}
		
		return genero;
		
	}

	private static String formatearDuracion(Scanner s) {
		int duracionEnSeg = ingresarEntero(s,1,Integer.MAX_VALUE);
		int	duracionEnMin = duracionEnSeg / 60;
		int	segundosExtra = duracionEnSeg % 60;
		String formatoMinSeg;
		if(segundosExtra < 10) {
		formatoMinSeg = (duracionEnMin + ":0" + segundosExtra);
		} 
		
		else {
		formatoMinSeg = (duracionEnMin + ":" + segundosExtra);	
		}
		return formatoMinSeg;
		
	}
	
	
	private static int modificarCancion(Scanner s,final String CANCIONES[][],int cantCanciones, final int ID_MIN,final int ID_MAX,final int CANT_ATRIBUTOS, int opc) {
		int cancionDatosModif = buscarCancion(s,CANCIONES,cantCanciones, opc);		
		int indiceIdBuscado = -1;
		int idCancion = 0;
		do {
			System.out.println("Ingrese nueva ID de la canción");
			idCancion = ingresarEntero(s,ID_MIN,ID_MAX);
			indiceIdBuscado = verificarID(CANCIONES,0,idCancion,cantCanciones);
				if(indiceIdBuscado>=0) {
					System.out.println("El índice ingresado ya existe en el sistema");
					System.out.println("Vuelva a ingresar");
				}
		} 
		while(indiceIdBuscado>=0);
		
		int indiceCadenaBuscada = -1;
		String nombreCancion;
		
		do {
			System.out.println("Ingrese nuevo Título de la canción");
			nombreCancion = ingresarCadena(s);
			indiceCadenaBuscada = buscarCadenaEnMatriz(CANCIONES,1,nombreCancion,cantCanciones);
				if(indiceCadenaBuscada>=0) {
					System.out.println("El título ingresado ya existe en el sistema");
					System.out.println("Vuelva a ingresar");
				}
		} 
		while (indiceCadenaBuscada>=0);

		String nombreArtista;
	
		System.out.println("Ingrese nuevo Nombre del artista");
		nombreArtista = ingresarCadena(s);
		
		String nombreAlbum;
		
		System.out.println("Ingrese nuevo género de la canción: 1 = Rock , 2 = Pop , 3 = Electronica, 4 = Hip Hop, 5 = Clásica, 6 = Jazz, 7 = Otros.");
		String genero = consultarGenero(s); 
		
		System.out.println("Ingrese nueva duración de la canción en segundos");
		String duracion = formatearDuracion(s);
		
		do {
			System.out.println("Ingrese nuevo Album al que pertenece");
			nombreAlbum = ingresarCadena(s);
			indiceCadenaBuscada = buscarCadenaEnMatriz(CANCIONES,1,nombreCancion,cantCanciones);
				if(indiceCadenaBuscada>=0) {
					System.out.println("El índice ingresado ya existe en el sistema");
					System.out.println("Vuelva a ingresar");
				}
		} 
		while (indiceCadenaBuscada>=0);
		
		int añoLanzamiento;
			
			System.out.println("Ingrese nuevo año de lanzamiento (1900 - 2025)");
			añoLanzamiento = ingresarEntero(s,1900,2025);
		
		int popularidad;
			
			System.out.println("Ingrese nueva popularidad de la canción");
			popularidad = ingresarEntero(s,1,100);
			
			CANCIONES[cancionDatosModif][0] = String.valueOf(idCancion);
            CANCIONES[cancionDatosModif][1] = nombreCancion;
            CANCIONES[cancionDatosModif][2] = nombreArtista;
            CANCIONES[cancionDatosModif][3] = nombreAlbum;
            CANCIONES[cancionDatosModif][4] = genero;
            CANCIONES[cancionDatosModif][5] = duracion;
            CANCIONES[cancionDatosModif][6] = String.valueOf(añoLanzamiento);
            CANCIONES[cancionDatosModif][7] = String.valueOf(popularidad);
												
		return cantCanciones;
		
	}
	
	private static int eliminarCancion(Scanner s,final String[][] CANCIONES, int cantCanciones, int opc) {
	    int cancionBuscada= buscarCancion(s,CANCIONES,cantCanciones, opc);
	    	if(cancionBuscada != -1) {
	    		   for(int i=cantCanciones; i<cantCanciones-1; i++) {
	    			   CANCIONES[i]= CANCIONES[i+1];
	    			   
	    		   }
	    		   CANCIONES[cantCanciones-1]= null;
	    		   System.out.println("Canción eliminada con éxito.");
	    		   cantCanciones--;
	    		}
	    	return cantCanciones;
	    	
	    	} 
   	
	    private static int listarCanciones(final String[][] CANCIONES, int cantCanciones,String infoCancion, int cancionBuscada) {
	    	cancionBuscada = 0;
	    	System.out.println("-----------------------------------------------------------------------------------------------------");
	    	for (int i = 0; i < cantCanciones; i++) {
	    			cancionBuscada= i;
	    			System.out.println(mostrarInfoCancion(CANCIONES, cantCanciones, infoCancion, cancionBuscada));
	    			System.out.println("-----------------------------------------------------------------------------------------------------");
			}   	
	    	return cantCanciones;
	    }
	    
	    private static int crearPlaylist(Scanner s, final int CANT_CANCIONES, final int CANT_ATRIBUTOS,
                int cantPlaylists, String[][][] PLAYLISTS,
                String[] nombresPlaylists, final String[][] CANCIONES, int cantCanciones) {

				if (cantPlaylists >= nombresPlaylists.length) {
					System.out.println("Límite de playlists alcanzado.");
					return cantPlaylists;
				}
				
				System.out.println("Ingrese Nombre de la playlist a crear");
				String nombrePlaylist = ingresarCadena(s);
				
				nombresPlaylists[cantPlaylists] = nombrePlaylist;
				PLAYLISTS[cantPlaylists] = new String[CANT_CANCIONES][CANT_ATRIBUTOS];
				System.out.println("Playlist '" + nombrePlaylist + "' creada con éxito.");
				
				System.out.println("¿Desea agregar canciones a esta playlist? \n 1) Si \n 0) No");
				int respuesta = ingresarEntero(s, 0, 1);
				
				if (respuesta == 1) {
					int cantCancionesAgregadas = 0;
					boolean[] cancionesAgregadas = new boolean[cantCanciones];
				
				do {
					boolean hayCancionesDisponibles = false;
					System.out.println("Canciones disponibles:");
				
				for (int i = 0; i < cantCanciones; i++) {
					if (CANCIONES[i][0] != null && CANCIONES[i][1] != null && !cancionesAgregadas[i]) {
						System.out.println((i + 1) + ". " + CANCIONES[i][0] + " - " + CANCIONES[i][1]);
						hayCancionesDisponibles = true;
					}
				}
				
				if (!hayCancionesDisponibles) {
					System.out.println("No hay más canciones disponibles para agregar.");
					break;
				}
				
				System.out.println("Seleccione una canción (1-" + cantCanciones + ") o 0 para finalizar:");
				int seleccion = Integer.parseInt(ingresarCadena(s));
				
				if (seleccion == 0) {
					break;
				} 
				
				else if (seleccion >= 1 && seleccion <= cantCanciones) {
					int indiceCancion = seleccion - 1;
				 
					if (cancionesAgregadas[indiceCancion]) {
						System.out.println("¡Esta canción ya está en la playlist!");
				 } 
					else {
						for (int j = 0; j < CANT_ATRIBUTOS; j++) {
							PLAYLISTS[cantPlaylists][cantCancionesAgregadas][j] = CANCIONES[indiceCancion][j];
				     }
						
						cancionesAgregadas[indiceCancion] = true;
						cantCancionesAgregadas++;
						System.out.println("Canción agregada. Canciones en playlist: " + cantCancionesAgregadas);
				 }
				} 
				
				else {
				 System.out.println("Selección no válida.");
				}
				
				if (cantCancionesAgregadas >= CANT_CANCIONES) {
					System.out.println("La playlist está llena.");
					break;
				}
				
				System.out.println("¿Desea agregar otra canción? \n1) Si \n0) No");
				respuesta = ingresarEntero(s, 0, 1);
				
				} 
				while (respuesta == 1);
				
				}
				
				return cantPlaylists + 1;
				}
	    
	    private static int buscarCancionesPorGenero(Scanner s,int cantCanciones,String[][] CANCIONES,String infoCancion,int cancionBuscada) {
	    	System.out.println("Ingrese género a buscar: Rock | Pop | Electronica | Hip Hop | Clásica | Jazz | Otros");
	    	String generoBuscado = ingresarCadena(s);
	    	boolean encontrada = false;
	    	System.out.println("----------------------------------------------------------------------------------------------");
	    	
	    	for(int i = 0; i<cantCanciones;i++) {
	    		if(generoBuscado.equalsIgnoreCase(CANCIONES[i][4])) {
	    			encontrada = true;
	    			cancionBuscada = i;
	    			String info = mostrarInfoCancion(CANCIONES,cantCanciones,infoCancion,cancionBuscada);
	    			System.out.println(info);
	    	    	System.out.println("----------------------------------------------------------------------------------------------");
	    		}
	    	}	
	    	
	    	if(encontrada == false) {
	    		System.out.println("No se encontraron canciones de ese género");
		    	System.out.println("----------------------------------------------------------------------------------------------");
	    	}

	    	return cantCanciones;
	    }
	    
	    private static int buscarCancionesPorArtista(Scanner s,int cantCanciones,String[][] CANCIONES,String infoCancion,int cancionBuscada) {
	    	System.out.println("Ingrese Artista a Buscar");
	    	String artistaBuscado = ingresarCadena(s);
	    	boolean encontrada = false;
	    	System.out.println("----------------------------------------------------------------------------------------------");
	    	for(int i = 0; i<cantCanciones;i++) {
	    		if(artistaBuscado.equalsIgnoreCase(CANCIONES[i][2])) {
	    			encontrada = true;
	    			cancionBuscada = i;
	    			String info = mostrarInfoCancion(CANCIONES,cantCanciones,infoCancion,cancionBuscada);
	    			System.out.println(info);
	    	    	System.out.println("----------------------------------------------------------------------------------------------");
	    		}
	    	}	
	    	if(encontrada == false) {
	    		System.out.println("No se encontraron canciones de este artista");
		    	System.out.println("----------------------------------------------------------------------------------------------");
	    	}

	    	return cantCanciones;
	    }
	
	    private static int buscarCancionesPopularidad(Scanner s, int cantCanciones, String[][] CANCIONES, final int CANT_ATRIBUTOS, String infoCancion, int cancionBuscada) {
	    	System.out.println("Como desea ordenar las canciones de \n 1) MAYOR A MENOR \n 2) MENOR A MAYOR");
	    	int numero= ingresarEntero(s, 1, 2);
	  //MAYOR
	    	if(numero==1) {
	        for (int i = 0; i < cantCanciones; i++) {
	            for (int j = 0; j < cantCanciones; j++) {
	                if (CANCIONES[j][7] != null && CANCIONES[j+1][7] != null) {
	                    if (Integer.parseInt(CANCIONES[j][7]) < Integer.parseInt(CANCIONES[j+1][7])) {
	                        String[] tmp = CANCIONES[j+1];
	                        CANCIONES[j+1] = CANCIONES[j];
	                        CANCIONES[j] = tmp;
	                    }
	                }
	            }
	        }
	    }
	  //MENOR
	    	if(numero==2) {
	        for (int i = 0; i < cantCanciones; i++) {
	            for (int j = 0; j < cantCanciones; j++) {
	                if (CANCIONES[j][7] != null && CANCIONES[j+1][7] != null) {
	                    if (Integer.parseInt(CANCIONES[j][7]) > Integer.parseInt(CANCIONES[j+1][7])) {
	                        String[] tmp = CANCIONES[j+1];
	                        CANCIONES[j+1] = CANCIONES[j];
	                        CANCIONES[j] = tmp;
	                    }
	                }
	            }
	        }
	    }
	        cantCanciones = listarCanciones(CANCIONES, cantCanciones, infoCancion, cancionBuscada);
	        
	        return cantCanciones;
	        
	    }
	    
	    private static int calcularEstadisticas(Scanner s, int cantCanciones, final String[][] CANCIONES) {
	    	System.out.println("Estadisticas: ");
	    	float sumaCancionesDuracion =0;
	    	float contRock=0,contPop=0,contElectronica=0,contHipHop=0,contClasica=0,contJazz=0,contOtros=0;
	    	String[] artistas = new String[cantCanciones];
		    int[] contadorCancionesPorArtista = new int[cantCanciones];
		    int numArtistas = 0;
	    	for(int i=0; i<cantCanciones; i++) {

		    		if(CANCIONES[i][4].equals("Rock")) {
		    			contRock++;
			    	}
		    		
			    	if(CANCIONES[i][4].equals("Pop")) {
			    		contPop++;
			    	}
			    	
		    	    if(CANCIONES[i][4].equals("Electrónica")) {
		    	    	contElectronica++;
		         	}
		    	    
			    	if(CANCIONES[i][4].equals("Hip Hop")) {
			    		contHipHop++;
					}
			    	
					if(CANCIONES[i][4].equals("Clásica")) {
						contClasica++;
					}
					
				    if(CANCIONES[i][4].equals("Jazz")) {
				    	contJazz++;
				 	}
				    
				    if(CANCIONES[i][4].equals("Otros")) {
				    	contOtros++;
				 	}
				    
				   
	    	}
	    	float porcRock=       (contRock/(float)cantCanciones)*100;
	    	float porcPop=        (contPop/(float)cantCanciones)*100;
	    	float porcElectronica=(contElectronica/(float)cantCanciones)*100;
	    	float porcHipHop=     (contHipHop/(float)cantCanciones)*100;
	    	float porcClasica=    (contClasica/(float)cantCanciones)*100;
	    	float porcJazz=       (contJazz/(float)cantCanciones)*100;
	    	float porcOtros=      (contOtros/(float)cantCanciones)*100;
	    
	    System.out.println("Estadisticas por género: \n Rock: "+ porcRock + " \n Pop: "+ porcPop + " \n Electrónica: " +porcElectronica+ " \n Hip Hop: " + porcHipHop + " \n Clásica: " +porcClasica+ " \n jazz: " + porcJazz + " \n Otros: " +porcOtros);
			   	    
	    for (int i = 0; i < cantCanciones; i++) {
	        try {
	            String[] partes = CANCIONES[i][5].split(":");
	            int minutos = Integer.parseInt(partes[0]);
	            int segundos = Integer.parseInt(partes[1]);
	            float duracionEnMinutos = minutos + (segundos / 60f);//el f no es una flashada, hace que el 60 sea float para usarla en la suma
	            sumaCancionesDuracion += duracionEnMinutos;
	        } 
	        
	        catch (Exception e) {
	            System.out.println("Error al procesar duración en fila " + i + ": " + CANCIONES[i][5]);
	        }
	    }


	    float promedioDeDuracion = sumaCancionesDuracion  / cantCanciones;

	    System.out.println("El promedio de duraciñon de las canciones es: " + promedioDeDuracion);
	    
	    for (int i = 0; i < cantCanciones; i++) {	        

	        boolean artistaEncontrado = false;
	        for (int j = 0; j < numArtistas; j++) {
	            if (artistas[j].equalsIgnoreCase(CANCIONES[i][2])) {
	                contadorCancionesPorArtista[j]++;
	                artistaEncontrado = true;
	                break;
	            }
	        }

	        if (!artistaEncontrado) {
	        	artistas[numArtistas] = CANCIONES[i][2];
	            contadorCancionesPorArtista[numArtistas] = 1;
	            numArtistas++;
	        }
	    }

	    String artistaConMasCanciones = "";
	    int maxCanciones = 0;
	    for (int i = 0; i < numArtistas; i++) {
	        if (contadorCancionesPorArtista[i] > maxCanciones) {
	            maxCanciones = contadorCancionesPorArtista[i];
	            artistaConMasCanciones = artistas[i];
	        }
	    }

	    if (!artistaConMasCanciones.isEmpty()) {
	        System.out.println("El artista con más canciones es: " + artistaConMasCanciones + " con " + maxCanciones + " canciones.");
	    } 
	    
	    else {
	        System.out.println("No se encontraron artistas.");
	    }

	    //esto es un array donde se guardan aparte los artistas, por ende si se repite se agrega la cancion pero NO el artista de nuevo y asi va sumando 
	    int[] decadas = new int[13];

	    int[] limites = {1900, 1910, 1920, 1930, 1940, 1950, 1960, 1970, 1980, 1990, 2000, 2010, 2020, 2026};

	    for (int i = 0; i < cantCanciones; i++) {
	    	int año = Integer.parseInt(CANCIONES[i][6]);
	            for (int j = 0; j < decadas.length; j++) {
	                if (año >= limites[j] && año < limites[j + 1]) {
	                    decadas[j]++;
	                }
	            }
	        } 

	    System.out.println("Distribución de Canciones por década");
	    
	    for (int j = 0; j < decadas.length; j++) {
	    	System.out.println("-----------------");
	        System.out.println(limites[j] + " a " + limites[j + 1] + ": " + decadas[j] + " |");
	    }
	    
    	System.out.println("-----------------");
	    return cantCanciones;
	    
	    }
	    
	    private static int cargarDatosPrueba(int cantCanciones,String[][] canciones) {
	    	cantCanciones = 10;
	    	canciones[0] = new String[] {"1", "Paint it Black", "The Rolling Stones", "Aftermath", "Rock", "3:47", "1966", "80"};
	    	canciones[1] = new String[] {"2", "Drive My Car", "The Rolling Stones", "Rubber Soul", "Pop", "2:15", "1965", "89"};
	    	canciones[2] = new String[] {"3", "Rehab", "Amy Winehouse", "Back to Black", "Otros", "3:34", "2006", "80"};
	    	canciones[3] = new String[] {"4", "Beat it", "Michael Jackson", "Thriller", "Rock", "4:59", "1983", "100"};
	    	canciones[4] = new String[] {"5", "Mary Poppins y el Deshollinador", "Fabiana Cantilo", "Algo Mejor", "Rock", "3:59", "1991", "70"};
	    	canciones[5] = new String[] {"6", "What a Wonderful World", "Louis Armstrong", "What a Wonderful World", "Jazz", "2:30", "1967", "65"};
	    	canciones[6] = new String[] {"7", "Fantastico", "Nat King Cole", "Fantastico", "Jazz", "1:53", "1997", "70"};
	    	canciones[7] = new String[] {"8", "Mr.SaxoBeat", "Alexandra Stan", "Mr.SaxoBeat", "Electrónica", "3:14", "2011", "64"};
	    	canciones[8] = new String[] {"9", "Esa Diva", "Melodie", "Esa Diva", "Pop", "3:10", "2025", "55"};
	    	canciones[9] = new String[] {"10", "Fur Elise", "Ludvig Van Beethoven", "Betthoven Top Hits", "Clásica", "3:10", "1900", "100"};
	    	return cantCanciones;
	    }
	   }