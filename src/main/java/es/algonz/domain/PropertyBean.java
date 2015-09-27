package es.algonz.domain;


public class PropertyBean implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2426561515899570316L;
	private Integer id;
	private String value;

	public PropertyBean() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}


}
