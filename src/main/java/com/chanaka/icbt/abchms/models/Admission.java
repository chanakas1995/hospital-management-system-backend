package com.chanaka.icbt.abchms.models;

import java.util.Date;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "admissions")
public class Admission {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String uuid;
	private String notes;
	private Date admissionDate;
	private Date dischargeDate;
	@JsonManagedReference
	@ManyToOne
	@JoinColumn(name = "branch_id", nullable = false)
	private Branch branch;
	@JsonManagedReference
	@ManyToOne
	@JoinColumn(name = "ward_id", nullable = false)
	private Ward ward;
	@JsonManagedReference
	@ManyToOne
	@JoinColumn(name = "patient_id", nullable = false)
	private Patient patient;

	public void generateUuid() {
		this.uuid = UUID.randomUUID().toString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Admission other = (Admission) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
