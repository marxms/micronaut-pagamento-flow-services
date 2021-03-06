package micronaut.atom.cliente.domain;

public enum GeneroEnum {

	M("MASCULINO"), FEMININO("FEMININO");
	
	private String value;
	
	private GeneroEnum(String value) {
		this.setValue(value);
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
