package com.kodcu.entity;

import java.text.MessageFormat;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author kodcu
 * 
 */
@Entity
public class Result extends DomainObject {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String bmiResult;

	@Temporal(TemporalType.TIMESTAMP)
	private Date date;
	private String description;



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBmiResult() {
		return bmiResult;
	}

	public void setBmiResult(String bmiResult) {
		this.bmiResult = bmiResult;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((bmiResult == null) ? 0 : bmiResult.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Result other = (Result) obj;
		if (date == null) {
			if (other.date != null) {
				return false;
			}
		}
		else if (!date.equals(other.date)) {
			return false;
		}
		if (description == null) {
			if (other.description != null) {
				return false;
			}
		}
		else if (!description.equals(other.description)) {
			return false;
		}
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		}
		else if (!id.equals(other.id)) {
			return false;
		}

		if (bmiResult == null) {
			if (other.bmiResult != null) {
				return false;
			}
		}
		else if (!bmiResult.equals(other.bmiResult)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return MessageFormat.format("Result [date={0}, description={1}, id={2}, location={3}, bmiResult={4}]", new Object[] { date, description, id, bmiResult});
	}

}
