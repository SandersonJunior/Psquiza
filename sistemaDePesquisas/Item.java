package sistemaDePesquisas;

import java.io.Serializable;

public class Item implements Serializable {
	private String status;
	private String codigoItem;
	
	public Item(String codigoItem, String status) {
		this.status = status;
		this.codigoItem = codigoItem;
	}

	public String toString() {
		return " | " + status + " - " + codigoItem;
	}
	
	public String getStatus() {
		return this.status;
	}
	
	public String setStatus(String status) {
		return status;
	}
}