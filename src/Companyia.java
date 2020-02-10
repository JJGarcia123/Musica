
public class Companyia {
	private String cod;
	private String nombre;
	private String dir;
	private String fax;
	private String tfno;
	
	public Companyia() {
		super();
	}

	public Companyia(String cod, String nombre, String dir, String fax, String tfno) {
		super();
		this.cod = cod;
		this.nombre = nombre;
		this.dir = dir;
		this.fax = fax;
		this.tfno = tfno;
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

	public String getDir() {
		return dir;
	}

	public void setDir(String dir) {
		this.dir = dir;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getTfno() {
		return tfno;
	}

	public void setTfno(String tfno) {
		this.tfno = tfno;
	}

	@Override
	public String toString() {
		return "Companyia [cod=" + cod + ", nombre=" + nombre + ", dir=" + dir + ", fax=" + fax + ", tfno=" + tfno
				+ "]";
	}
	
	
}
