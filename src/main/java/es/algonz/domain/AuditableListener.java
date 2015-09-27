package es.algonz.domain;

import java.util.Date;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;


public class AuditableListener implements java.io.Serializable {


	private static final long serialVersionUID = 5166917970075893930L;

	@PrePersist
	 public void prePersist(AuditableBaseEntity e) {
		 
		 String name = "UNKNOWN";
		 try{
		 Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	     name = auth.getName(); //get logged in username
		 }catch (Exception ex){
			 
		 }
		 
	  e.setCreatedBy(name);
	  e.setCreatedDtTm(new Date());

	 }

	 @PreUpdate
	 public void preUpdate(AuditableBaseEntity e) {
		 String name = "UNKNOWN";
		 try{
		 Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	     name = auth.getName(); //get logged in username
		 }catch (Exception ex){
			 
		 }
		 
	 
	  e.setModifiedBy(name);
	  e.setModifiedDtTm(new Date());
	 }

}
