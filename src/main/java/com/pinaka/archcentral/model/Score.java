package com.pinaka.archcentral.model;

import java.io.Serializable;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Entity implementation class for Entity: Score
 *
 */
@SuppressWarnings("serial")
@Embeddable
@XmlRootElement
public class Score implements Serializable {
  
  
    @NotNull
	private int shotNumber;
    
    @NotNull
    @Max(10)
    @Min(0)
	private int scoreValue;
    
    @NotNull
	private boolean is10X;
    
    private String status;
    

	public Score() {
		super();
	}

	public int getShotNumber() {
		return shotNumber;
	}

	public void setShotNumber(int shotNumber) {
		this.shotNumber = shotNumber;
	}

	public int getScoreValue() {
		return scoreValue;
	}

	public void setScoreValue(int scoreValue) {
		this.scoreValue = scoreValue;
	}

	public boolean isIs10X() {
		return is10X;
	}

	public void setIs10X(boolean is10x) {
		is10X = is10x;
	}   

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (is10X ? 1231 : 1237);
		result = prime * result + scoreValue;
		result = prime * result + shotNumber;
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Score other = (Score) obj;
		if (is10X != other.is10X)
			return false;
		if (scoreValue != other.scoreValue)
			return false;
		if (shotNumber != other.shotNumber)
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		return true;
	}
   
}
