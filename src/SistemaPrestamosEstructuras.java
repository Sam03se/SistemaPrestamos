import java.util.*;

class Cliente {
    int id;
    String nombre;

    public Cliente(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }
}

class Prestamo implements Comparable<Prestamo> {
    int id;
    int idCliente;
    double monto;
    String destino;

    public Prestamo(int id, int idCliente, double monto, String destino) {
        this.id = id;
        this.idCliente = idCliente;
        this.monto = monto;
        this.destino = destino;
    }

    @Override
    public int compareTo(Prestamo otro) {
        return Double.compare(otro.monto, this.monto);
    }
}

public class SistemaPrestamosEstructuras {

    static ArrayList<Cliente> clientes = new ArrayList<>();
    static Queue<Prestamo> colaSolicitudes = new LinkedList<>();
    static Stack<String> historial = new Stack<>();
    static PriorityQueue<Prestamo> colaPrioridad = new PriorityQueue<>();

    static Scanner sc = new Scanner(System.in);
    static int idClienteCounter = 1;
    static int idPrestamoCounter = 1;

    public static void main(String[] args) {
        int opcion;
        do {
            System.out.println("\n======= MENÚ DE PRÉSTAMOS =======");
            System.out.println("1. Crear cliente");
            System.out.println("2. Mostrar clientes");
            System.out.println("3. Solicitar préstamo");
            System.out.println("4. Aprobar préstamo");
            System.out.println("5. Mostrar préstamos priorizados");
            System.out.println("6. Mostrar historial");
            System.out.println("7. Salir");
            System.out.print("Opción: ");
            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1 -> crearCliente();
                case 2 -> mostrarClientes();
                case 3 -> solicitarPrestamo();
                case 4 -> aprobarPrestamo();
                case 5 -> mostrarPrestamosPrioridad();
                case 6 -> mostrarHistorial();
                case 7 -> System.out.println("Cerrando sistema...");
                default -> System.out.println("Opción inválida.");
            }
        } while (opcion != 7);
    }

    static void crearCliente() {
        System.out.print("Nombre del cliente: ");
        String nombre = sc.nextLine();
        Cliente c = new Cliente(idClienteCounter++, nombre);
        clientes.add(c);
        historial.push("Cliente creado: " + nombre);
        System.out.println("Cliente registrado.");
    }

    static void mostrarClientes() {
        if (clientes.isEmpty()) {
            System.out.println("No hay clientes registrados.");
            return;
        }
        System.out.println("Lista de clientes:");
        for (Cliente c : clientes) {
            System.out.println("ID: " + c.id + " | Nombre: " + c.nombre);
        }
    }

    static void solicitarPrestamo() {
        if (clientes.isEmpty()) {
            System.out.println("Primero debes registrar al menos un cliente.");
            return;
        }
        System.out.print("ID del cliente: ");
        int idCliente = sc.nextInt();
        sc.nextLine();
        Cliente c = buscarCliente(idCliente);
        if (c == null) {
            System.out.println("Cliente no encontrado.");
            return;
        }

        System.out.print("Monto solicitado: ");
        double monto = sc.nextDouble();
        sc.nextLine();
        System.out.print("Destino del préstamo: ");
        String destino = sc.nextLine();

        Prestamo p = new Prestamo(idPrestamoCounter++, idCliente, monto, destino);
        colaSolicitudes.add(p);
        historial.push("Solicitud agregada: $" + monto + " para " + destino);
        System.out.println("Solicitud registrada.");
    }

    static void aprobarPrestamo() {
        if (colaSolicitudes.isEmpty()) {
            System.out.println("No hay solicitudes pendientes.");
            return;
        }
        Prestamo p = colaSolicitudes.poll();
        colaPrioridad.add(p);
        historial.push("Préstamo aprobado: $" + p.monto + " para " + p.destino);
        System.out.println("Préstamo aprobado y enviado a cola priorizada.");
    }

    static void mostrarPrestamosPrioridad() {
        if (colaPrioridad.isEmpty()) {
            System.out.println("No hay préstamos aprobados.");
            return;
        }
        System.out.println("Préstamos aprobados (ordenados por monto):");
        for (Prestamo p : colaPrioridad) {
            System.out.println("Cliente ID: " + p.idCliente + " | Monto: $" + p.monto + " | Destino: " + p.destino);
        }
    }

    static void mostrarHistorial() {
        if (historial.isEmpty()) {
            System.out.println("Historial vacío.");
            return;
        }
        System.out.println("Historial de operaciones:");
        while (!historial.isEmpty()) {
            System.out.println(historial.pop());
        }
    }

    static Cliente buscarCliente(int id) {
        for (Cliente c : clientes) {
            if (c.id == id) return c;
        }
        return null;
    }
}
