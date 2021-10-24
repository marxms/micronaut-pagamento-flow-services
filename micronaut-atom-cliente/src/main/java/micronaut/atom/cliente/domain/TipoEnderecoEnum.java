package micronaut.atom.cliente.domain;

public enum TipoEnderecoEnum {

	R("RESIDENCIAL"), C("COMERCIAL"), O("OUTROS");

	private String value;

	private TipoEnderecoEnum(String value) {
		this.setValue(value);
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
