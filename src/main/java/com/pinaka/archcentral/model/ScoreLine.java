package com.pinaka.archcentral.model;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OrderColumn;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;


@SuppressWarnings("serial")
@Entity
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class ScoreLine implements Serializable, Cloneable{
	
    @Id
    @GeneratedValue
    private Long id;
    
    @XmlTransient
    @ManyToOne(fetch=FetchType.LAZY)
    private ScoreCard scoreCard;
    
	@NotNull
    private int round;
	
	@ElementCollection(fetch=FetchType.EAGER, targetClass=Score.class)
	@CollectionTable(name="Score", joinColumns={@JoinColumn(name="scoreLineId")})								
	@OrderColumn(name="scoreOrder")
	private List<Score> scores = new ArrayList<Score>();
	
	//TODO - add scoreLineTotal, scoreLineXs, and cumulativeTotal as transients
	
	public ScoreLine() {
		super();
	}

	public int getRound() {
		return round;
	}

	public void setRound(int round) {
		this.round = round;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ScoreCard getScoreCard() {
		return scoreCard;
	}

	public void setScoreCard(ScoreCard scoreCard) {
		this.scoreCard = scoreCard;
	}

	public List<Score> getScores() {
		return scores;
	}

	public void setScores(List<Score> scores) {
		this.scores = scores;
	}
	
	   @SuppressWarnings("unchecked")
		public <T> T deepCopy()
		{
		        try
		        {
		                ObjectOutputStream oos = null;
		                ObjectInputStream ois = null;
		                try
		                {
		                        ByteArrayOutputStream bos = new ByteArrayOutputStream();
		                        oos = new ObjectOutputStream(bos);
		                        oos.writeObject(this);
		                        oos.flush();
		                        ois = new ObjectInputStream(new ByteArrayInputStream(bos.toByteArray()));
		                        return (T) ois.readObject();
		                }
		                finally
		                {
		                        oos.close();
		                        ois.close();
		                }
		        }
		        catch ( ClassNotFoundException cnfe )
		        {
		                // Impossible, since both sides deal in the same loaded classes.
		                return null;
		        }
		        catch ( IOException ioe )
		        {
		                // This has to be "impossible", given that oos and ois wrap a *byte array*.
		                return null;
		        }
		}
	    
	   	public Object clone() {
	   		try{
	   		return super.clone();
	   		} catch (CloneNotSupportedException e) {
	   		  return null;
	    	 }
	   	}

}
