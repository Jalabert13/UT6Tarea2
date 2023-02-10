import java.util.*;

public class UD6Tarea2 {
    public static class song {
 String titulo;
 double duracion;

        public song(String titulo, double duracion) {
            this.titulo= titulo;
            this.duracion = duracion;
        }

        public String getTitulo() {
            return titulo;
        }

        @Override
        public String toString() {
            return "titulo: " + titulo + '\'' +
                    ", duracion: " + duracion ;
        }
    }
    public static class album {
        String nombre;
        String artista;
        ArrayList<song> canciones =  new ArrayList<>();
        public album(String nombre, String artista) {
            this.nombre= nombre;
            this.artista = artista;
        }

        public static song crearcancion(String titulo, double duracion) {
            return new song (titulo, duracion);
        }

        private  int findSong(String titulo) {
            for (int i = 0; i < canciones.size(); i++) {
                if (canciones.get(i).getTitulo().equals(titulo)) {
                    return i;
                }
            }
            return -1;
        }

        public  boolean addSong (String titulo, double duracion){
            if (findSong(titulo) == -1) {
                canciones.add(crearcancion(titulo, duracion));
                return true;
            }
                else {
                System.out.println("Esta cancion ya existe");
                    return false;
                }
            }

        public  boolean addToPlayList (int numpista, LinkedList <song> listarep){
            ListIterator<song> it = listarep.listIterator();
            if (numpista >= 0 && numpista < canciones.size() ) {
                it.add(canciones.get(numpista));
            return true;
            }
            else
                return false;
        }
        public  boolean addToPlayList (String titulo,  LinkedList <song>  listarep){
            ListIterator<song> it = listarep.listIterator();
            if (findSong(titulo) >= 0 && findSong(titulo) < canciones.size() ) {
                it.add(canciones.get(findSong(titulo)));
                return true;
            }
            else
                return false;
        }

    }
    static class main {
        static LinkedList<album> albums = new LinkedList<>();
        static LinkedList<song> playlist = new LinkedList<>();
        public static void main(String[] args) throws InterruptedException {
            album album1 = new album("Back to Rockport", "Kidd Keo");
            album album2 = new album("Un perro andaluz", "Delaossa");
            albums.add(album1);
            albums.add(album2);
            album1.addSong("EBISU", 2.51 );
            album1.addSong("VAMONOS", 2.33);
            album1.addSong("MOON TALK", 3.04);
            album2.addSong("Dicen de mí", 4.02);
            album2.addSong("Mil en multas", 3.04);
            album2.addSong("Debut y Hat Trick", 3.51);
            album1.addToPlayList(0,playlist);
            album1.addToPlayList("VAMONOS",playlist);
            album1.addToPlayList(2,playlist);
            album2.addToPlayList(0,playlist);
            album2.addToPlayList(1,playlist);
            album2.addToPlayList("Debut y Hat Trick",playlist);
            printList(playlist);
            play(playlist);

        }
        public static void play(LinkedList<song> listo) {
            Scanner scanner = new Scanner(System.in);
            boolean continuar = true;
            ListIterator<song> it = listo.listIterator();

            if (listo.isEmpty()) {
                System.out.println("No hay canciones en la playlist");
                return;
            } else {
                System.out.println("Escuchando " + it.next());
                menuopt();
            }

            boolean haciaAdelante = true;
            while(continuar) {
                int opcion = scanner.nextInt();
                scanner.nextLine();
                switch (opcion) {
                    case 0:
                        System.out.println("Gracias por la aclaración, nos vemos!!");
                        continuar = false;
                        break;
                    case 1:
                        if (!haciaAdelante) {
                            if (it.hasNext())
                                it.next();
                            haciaAdelante = true;
                        }
                        if (it.hasNext()) {
                            System.out.println("Escuchando " + it.next());
                        } else {
                            System.out.println("Ya no hay más canciones en la lista");
                            haciaAdelante = false;
                        }
                        break;
                    case 2:
                        if(haciaAdelante) {
                            if (it.hasPrevious())
                                it.previous();
                            haciaAdelante = false;
                        }
                        if (it.hasPrevious()) {
                            System.out.println("Escuchando " + it.previous());
                        } else {
                            System.out.println("Es la primera cancion de la lista");
                            it.next();
                            haciaAdelante = true;
                        }
                        break;
                    case 3:
                        it.previous();
                        System.out.println("Escuchando: " + it.next());
                        break;
                    case 4:
                        printList(playlist);
                        break;
                    case 5:
                        menuopt();
                        break;
                    case 6:
                        it.previous();
                        System.out.println(it.next().getTitulo()+" ha sido eliminada de la lista.");
                        it.remove();
                        if (it.hasNext()) {
                            System.out.println("Escuchando " + it.next());
                        }
                        else {
                            System.out.println("Escuchando " + it.previous());
                        }
                        break;
                }
            }
        }

        public static void menuopt() {

            System.out.println("¿Qué quiere hacer?" +
                    "\n---------------------------------------------------------------" +
                    "\n0- Salir de la lista de reproducción" +
                    "\n1- Reproducir siguiente canción de la lista " +
                    "\n2- Reproducir la canción previa de la lista" +
                    "\n3- Repetir la canción actual" +
                    "\n4- Imprimir la lista de canciones en la playlist " +
                    "\n5 – Volver a imprimir el menú." +
                    "\n6 – Borrar cancion actual." +
                    "\n---------------------------------------------------------------");

        }
        public static void printList(LinkedList<song> listo) {
            Iterator<song> it = listo.iterator();
            while (it.hasNext()) {
                System.out.println("Nodo: " + it.next());
            }
            System.out.println("-----");
        }









            }

    }

