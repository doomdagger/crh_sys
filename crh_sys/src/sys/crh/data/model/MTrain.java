package sys.crh.data.model;

public class MTrain {
	private long id;
	private String crhType;
	private String crhModelType;
	private short[] carts;
	
	public MTrain() {}
	
	public MTrain(long id, String crhType, String crhModelType, short[] carts) {
		super();
		this.id = id;
		this.crhType = crhType;
		this.crhModelType = crhModelType;
		this.carts = carts;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getCrhType() {
		return crhType;
	}
	public void setCrhType(String crhType) {
		this.crhType = crhType;
	}
	public String getCrhModelType() {
		return crhModelType;
	}
	public void setCrhModelType(String crhModelType) {
		this.crhModelType = crhModelType;
	}
	public short[] getCarts() {
		return carts;
	}
	public void setCarts(short[] carts) {
		this.carts = carts;
	}
	
	
}
