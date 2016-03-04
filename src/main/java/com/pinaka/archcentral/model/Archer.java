package com.pinaka.archcentral.model;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.String;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * Entity implementation class for Entity: Archer
 *
 */
@SuppressWarnings("serial")
@Entity
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@NamedQueries({
	@NamedQuery(name="Archer.findAll", query="Select a from Archer a")
})
public class Archer implements Serializable, Cloneable {

    @Id
    @GeneratedValue
    private Long id;
    
    @NotNull
	private String name;
    
    @NotNull
    @Column(unique=true)
	private String userName;
    
//    @NotNull
    @XmlTransient
	@Temporal(TemporalType.DATE)
    private Calendar dateOfBirth;
    
	@NotNull
	private char gender;
    
    @NotNull
    @Enumerated(EnumType.STRING)
	private ArcherClass archerClass;

    @XmlTransient
    @OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="participant")
    private Set<ScoreCard> scorecards = new HashSet<ScoreCard>();
    
    @XmlTransient
    @OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="member")
    private Set<ClubMembership> clubMemberships = new HashSet<ClubMembership>();
    
	public Set<ClubMembership> getClubMemberships() {
		return clubMemberships;
	}
	public void setClubMemberships(Set<ClubMembership> clubMemberships) {
		this.clubMemberships = clubMemberships;
	}
	public Archer() {
		super();
	}   
	public Long getId() {
		return id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}   
	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}   
	public Calendar getDateOfBirth() {
		return this.dateOfBirth;
	}

	public void setDateOfBirth(Calendar dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}   
	public char getGender() {
		return this.gender;
	}

	public void setGender(char gender) {
		this.gender = gender;
	}   
	public ArcherClass getArcherClass() {
		return this.archerClass;
	}

	public void setArcherClass(ArcherClass archerClass) {
		this.archerClass = archerClass;
	}
	public Set<ScoreCard> getScorecards() {
		return scorecards;
	}
	public void setScorecards(Set<ScoreCard> scorecards) {
		this.scorecards = scorecards;
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
