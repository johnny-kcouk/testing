package es.algonz.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

@MappedSuperclass
@EntityListeners(value={ AuditableListener.class })
public abstract class AuditableBaseEntity {
	private String createdBy;
	private Date createdDtTm;
	private Date modifiedDtTm;
	private String modifiedBy;

	@Column(name = "CreatedBy", length = 30, updatable = false)
	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}


	@Column(name = "CreatedDtTm", length = 23, updatable = false)
	public Date getCreatedDtTm() {
		return createdDtTm;
	}

	public void setCreatedDtTm(Date createdDtTm) {
		this.createdDtTm = createdDtTm;
	}

	@Column(name = "ModifiedDtTm", length = 23)
	public Date getModifiedDtTm() {
		return modifiedDtTm;
	}

	public void setModifiedDtTm(Date modifiedDtTm) {
		this.modifiedDtTm = modifiedDtTm;
	}

	@Column(name = "ModifiedBy", length = 30)
	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this,
				ToStringStyle.SHORT_PREFIX_STYLE);
	}
	
	
}
