
public class Disco {
	private String cod;
	private String nombre;
	private String fecha;
	private String cod_comp;
	private String cod_gru;
	
	public Disco() {
		super();
	}

	public Disco(String cod, String nombre, String fecha, String cod_comp, String cod_gru) {
		super();
		this.cod = cod;
		this.nombre = nombre;
		this.fecha = fecha;
		this.cod_comp = cod_comp;
		this.cod_gru = cod_gru;
	}

	public String getCod() {
		return cod;
	}

	public void setCod(String cod) {
		this.cod = cod;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getCod_comp() {
		return cod_comp;
	}

	public void setCod_comp(String cod_comp) {
		this.cod_comp = cod_comp;
	}

	public String getCod_gru() {
		return cod_gru;
	}

	public void setCod_gru(String cod_gru) {
		this.cod_gru = cod_gru;
	}

	@Override
	public String toString() {
		return "Disco [cod=" + cod + ", nombre=" + nombre + ", fecha=" + fecha + ", cod_comp=" + cod_comp + ", cod_gru="
				+ cod_gru + "]";
	}
	
}
