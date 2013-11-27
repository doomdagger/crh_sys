package sys.crh.data.model;
 
import java.util.*;

public class Crh {
	private long id;
	private String crhNo;
	private String crhModelType;
	private String crhType;
	private String factory;
	private Date produceDate;
	private String routeLine;
	private String remark;
	private Date createTime;
	private Date editTime;
	private long creator;
	private long editor;
	
	
	public Crh() {}
	
	
	public Crh(long id, String crhNo, String crhModelType, String crhType,
			String factory, Date produceDate, String routeLine, String remark,
			Date createTime, Date editTime, long creator, long editor) {
		this.id = id;
		this.crhNo = crhNo;
		this.crhModelType = crhModelType;
		this.crhType = crhType;
		this.factory = factory;
		this.produceDate = produceDate;
		this.routeLine = routeLine;
		this.remark = remark;
		this.createTime = createTime;
		this.editTime = editTime;
		this.creator = creator;
		this.editor = editor;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getCrhNo() {
		return crhNo;
	}
	public void setCrhNo(String crhNo) {
		this.crhNo = crhNo;
	}
	public String getCrhModelType() {
		return crhModelType;
	}
	public void setCrhModelType(String crhModelType) {
		this.crhModelType = crhModelType;
	}
	public String getCrhType() {
		return crhType;
	}
	public void setCrhType(String crhType) {
		this.crhType = crhType;
	}
	public String getFactory() {
		return factory;
	}
	public void setFactory(String factory) {
		this.factory = factory;
	}
	public Date getProduceDate() {
		return produceDate;
	}
	public void setProduceDate(Date produceDate) {
		this.produceDate = produceDate;
	}
	public String getRouteLine() {
		return routeLine;
	}
	public void setRouteLine(String routeLine) {
		this.routeLine = routeLine;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getEditTime() {
		return editTime;
	}
	public void setEditTime(Date editTime) {
		this.editTime = editTime;
	}
	public long getCreator() {
		return creator;
	}
	public void setCreator(long creator) {
		this.creator = creator;
	}
	public long getEditor() {
		return editor;
	}
	public void setEditor(long editor) {
		this.editor = editor;
	}
	
	
	
}
