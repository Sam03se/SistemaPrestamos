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
        // Prioridad: monto mayor tiene más prioridad
        return Double.compare(otro.monto, this.monto);
    }
}

public class SistemaPrestamosEstructuras {
    static ArrayList<Cliente> clientes = new ArrayList<>();
    static Queue<Prestamo> colaSolicitudes = new LinkedList<>();
    static Stack<String> historial = new Stack<>();
    static PriorityQueue<Prestamo> colaPrioridad = new PriorityQueue<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Crear cliente sencillo
        Cliente c1 = new Cliente(1, "Dome");
        clientes.add(c1);
        historial.push("Cliente creado: " + c1.nombre);

        // Crear préstamo (agregado a la cola normal)
        Prestamo p1 = new Prestamo(1, 1, 5000, "Estudios");
        colaSolicitudes.add(p1);
        historial.push("Solicitud agregada a cola: " + p1.destino + " $" + p1.monto);

        // Aprobar solicitud (de cola normal a cola de prioridad)
        Prestamo aprobado = colaSolicitudes.poll();
        if (aprobado != null) {
            colaPrioridad.add(aprobado);
            historial.push("Préstamo aprobado y enviado a cola priorizada");
        }

        // Mostrar cola de prioridad
        System.out.println("📝 Préstamos priorizados por monto:");
        for (Prestamo p : colaPrioridad) {
            System.out.println("Préstamo #" + p.id + " | Cliente: " + p.idCliente + " | $" + p.monto + " | Destino: " + p.destino);
        }

        // Mostrar historial de operaciones (último al primero)
        System.out.println("\n📚 Historial de operaciones:");
        while (!historial.isEmpty()) {
            System.out.println(historial.pop());
        }
    }
}
//TIP Press <shortcut actionId="Debug"/> to start debugging your code. We have set one <icon src="AllIcons.Debugger.Db_set_breakpoint"/> breakpoint
            // for you, but you can always add more by pressing <shortcut actionId="ToggleLineBreakpoint"/>.
            System.out.println("i = " + i);
        }
    }
}