package com.pinaka.archcentral.model;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * Entity implementation class for Entity: Tournament
 *
 */
@SuppressWarnings("serial")
@Entity
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@NamedQueries({
	@NamedQuery(name="Tournament.findAll", query="Select t from Tournament t")
})
public class Tournament implements Serializable, Cloneable {
	
	   	@Id
	    @GeneratedValue
	    private Long id;   
    
	    @NotNull
		private String tournamentName;
	    
		private String tournamentDescription;
	    
	    @Temporal(TemporalType.DATE)
//TODO- @NotNull
		private Calendar date;
	    
		@NotNull
		@Min(0)
		private int numberOfRounds;
		
		@NotNull
		@Min(0)
		private int shotsPerRound;
		
		//TODO - add age rules
		//TODO - add costs (including family costs)
		
		@XmlTransient
	    @OneToMany(fetch=FetchType.LAZY, mappedBy="tournament")
	    private Set<ScoreCard> scorecards = new HashSet<ScoreCard>();

		public Tournament() {
			super();
		}

		public Long getId() {
			return id;
		}

		public String getTournamentName() {
			return tournamentName;
		}

		public void setTournamentName(String tournamentName) {
			this.tournamentName = tournamentName;
		}

		public String getTournamentDescription() {
			return tournamentDescription;
		}

		public void setTournamentDescription(String tournamentDescription) {
			this.tournamentDescription = tournamentDescription;
		}

		public Calendar getDate() {
			return date;
		}

		public void setDate(Calendar date) {
			this.date = date;
		}

		public int getNumberOfRounds() {
			return numberOfRounds;
		}

		public void setNumberOfRounds(int numberOfRounds) {
			this.numberOfRounds = numberOfRounds;
		}

		public int getShotsPerRound() {
			return shotsPerRound;
		}

		public void setShotsPerRound(int shotsPerRound) {
			this.shotsPerRound = shotsPerRound;
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

			@Override
			public int hashCode() {
				final int prime = 31;
				int result = 1;
				result = prime * result
						+ ((date == null) ? 0 : date.hashCode());
				result = prime * result + ((id == null) ? 0 : id.hashCode());
				result = prime * result + numberOfRounds;
				result = prime * result
						+ ((scorecards == null) ? 0 : scorecards.hashCode());
				result = prime * result + shotsPerRound;
				result = prime
						* result
						+ ((tournamentDescription == null) ? 0
								: tournamentDescription.hashCode());
				result = prime
						* result
						+ ((tournamentName == null) ? 0 : tournamentName
								.hashCode());
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
				Tournament other = (Tournament) obj;
				if (date == null) {
					if (other.date != null)
						return false;
				} else if (!date.equals(other.date))
					return false;
				if (id == null) {
					if (other.id != null)
						return false;
				} else if (!id.equals(other.id))
					return false;
				if (numberOfRounds != other.numberOfRounds)
					return false;
				if (scorecards == null) {
					if (other.scorecards != null)
						return false;
				} else if (!scorecards.equals(other.scorecards))
					return false;
				if (shotsPerRound != other.shotsPerRound)
					return false;
				if (tournamentDescription == null) {
					if (other.tournamentDescription != null)
						return false;
				} else if (!tournamentDescription
						.equals(other.tournamentDescription))
					return false;
				if (tournamentName == null) {
					if (other.tournamentName != null)
						return false;
				} else if (!tournamentName.equals(other.tournamentName))
					return false;
				return true;
			}
		   	

}
