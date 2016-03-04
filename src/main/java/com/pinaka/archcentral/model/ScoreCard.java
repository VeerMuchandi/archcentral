package com.pinaka.archcentral.model;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.String;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * Entity implementation class for Entity: ScoreCard
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(
        name="ScoreCard", 
        uniqueConstraints=
            @UniqueConstraint(columnNames={"tournament_id", "participant_id", "archerClass"})
    )
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@NamedQueries({
	@NamedQuery(name="ScoreCard.findAll", query="Select s from ScoreCard s"),
	@NamedQuery(name="ScoreCard.findUniqueScoreCard", query= "SELECT sc FROM ScoreCard sc WHERE sc.participant.id = :archerId AND sc.tournament.id = :tournamentId AND sc.archerClass = :archerClass"),
	@NamedQuery(name="ScoreCard.findByArcherAndTournament", query= "SELECT sc FROM ScoreCard sc WHERE sc.participant.id = :archerId AND sc.tournament.id = :tournamentId"),
	@NamedQuery(name="ScoreCard.findByArcher", query= "SELECT sc FROM ScoreCard sc WHERE sc.participant.id = :archerId"),
	@NamedQuery(name="ScoreCard.findByTournament", query= "SELECT sc FROM ScoreCard sc WHERE sc.tournament.id = :tournamentId")
})
public class ScoreCard implements Serializable, Cloneable {

    @Id
    @GeneratedValue
    private Long id;
    
    @NotNull
    @Enumerated(EnumType.STRING)
	private ArcherClass archerClass;
    
    @NotNull
	private String division;
    
    //TODO - is this transient?
	private int totalScore; 
	
    @XmlElement
    @ManyToOne(fetch=FetchType.EAGER)
    private Archer participant;
    
    @XmlElement
    @ManyToOne(fetch=FetchType.EAGER)
    private Tournament tournament;
	
	@XmlElement
    @OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER, mappedBy="scoreCard")
    private Set<ScoreLine> scoreLines = new HashSet<ScoreLine>();
	
	public ScoreCard() {
		super();
	}  
	
	public ScoreCard(Archer archer, Tournament tournament, ArcherClass archerClass) {
		this.participant= archer;
		this.tournament = tournament;
		this.archerClass = archerClass;
	}

	public ArcherClass getArcherClass() {
		return this.archerClass;
	}

	public void setArcherClass(ArcherClass archerClass) {
		this.archerClass = archerClass;
	}   
	public String getDivision() {
		return this.division;
	}

	public void setDivision(String division) {
		this.division = division;
	}   
	public int getTotalScore() {
		return this.totalScore;
	} 

	public Archer getParticipant() {
		return participant;
	}
	public void setParticipant(Archer participant) {
		this.participant = participant;
	}

	public Long getId() {
		return id;
	}

	public Tournament getTournament() {
		return tournament;
	}
	public void setTournament(Tournament tournament) {
		this.tournament = tournament;
	}

	public Set<ScoreLine> getScoreLines() {
		return scoreLines;
	}

	public void setScoreLines(Set<ScoreLine> scoreLines) {
		this.scoreLines = scoreLines;
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
