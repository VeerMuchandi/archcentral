package com.pinaka.archcentral.model;

import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@SuppressWarnings("serial")
@Entity
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@NamedQueries({
	@NamedQuery(name="ClubMembership.findAll", query="Select cm from ClubMembership cm"),
	@NamedQuery(name="ClubMembership.findByClub", query="SELECT cm FROM ClubMembership cm WHERE cm.club.id = :clubId"),
	@NamedQuery(name="ClubMembership.findByArcher", query="SELECT cm FROM ClubMembership cm WHERE cm.member.id = :archerId"),
	@NamedQuery(name="ClubMembership.findByArcherAndClub", query="SELECT cm FROM ClubMembership cm WHERE cm.club.id = :clubId AND cm.member.id = :archerId")
})
public class ClubMembership {
	@Id
    @GeneratedValue
    private Long id;
    
    @NotNull
    @Enumerated(EnumType.STRING)
    private MembershipType membershipType;
    
    @NotNull
    @Enumerated(EnumType.STRING)
    private MembershipDuration duration;
    
	@Temporal(TemporalType.DATE)
    private Calendar expiresOn;
	
    @XmlElement
    @ManyToOne(fetch=FetchType.EAGER)
    private Archer member;
    
    @XmlElement
    @ManyToOne(fetch=FetchType.EAGER)
    private Club club;

	public ClubMembership() {
		super();
	}

	public ClubMembership(Archer member,
			Club club, MembershipType membershipType,
			MembershipDuration duration, Calendar expiresOn) {
		super();
		this.membershipType = membershipType;
		this.duration = duration;
		this.expiresOn = expiresOn;
		this.member = member;
		this.club = club;
	}

	public Long getId() {
		return id;
	}

	public Archer getMember() {
		return member;
	}

	public void setMember(Archer member) {
		this.member = member;
	}

	public Club getClub() {
		return club;
	}

	public void setClub(Club club) {
		this.club = club;
	}

	public MembershipType getMembershipType() {
		return membershipType;
	}

	public void setMembershipType(MembershipType membershipType) {
		this.membershipType = membershipType;
	}

	public MembershipDuration getDuration() {
		return duration;
	}

	public void setDuration(MembershipDuration duration) {
		this.duration = duration;
	}

	public Calendar getExpiresOn() {
		return expiresOn;
	}

	public void setExpiresOn(Calendar expiresOn) {
		this.expiresOn = expiresOn;
	}
    
    

}
