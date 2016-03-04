package com.pinaka.archcentral.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@SuppressWarnings("serial")
@Entity
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@NamedQueries({
	@NamedQuery(name="Club.findAll", query="SELECT c FROM Club c"),
	@NamedQuery(name="Club.findByName", query="SELECT c FROM Club c WHERE c.name LIKE :name")
})
public class Club {
	@Id
    @GeneratedValue
    private Long id;
	
	private String name;
	
	@Embedded
	private Address address;
	
	@Size(min = 1, max = 50)
	@Pattern(regexp = "[A-Za-z ]*", message = "must contain only letters and spaces")
	private String contactName;
	
    @Pattern(regexp="^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$",
            message="{invalid.phonenumber}")
	private String contactPhoneNumber;
    
    //TODO - add default
    private boolean enabled;
    
    @XmlTransient
    @OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="club")
    private Set<ClubMembership> clubMemberships = new HashSet<ClubMembership>();
    

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public String getContactName() {
		return contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	public String getContactPhoneNumber() {
		return contactPhoneNumber;
	}

	public void setContactPhoneNumber(String contactPhoneNumber) {
		this.contactPhoneNumber = contactPhoneNumber;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public Set<ClubMembership> getClubMemberships() {
		return clubMemberships;
	}

	public void setClubMemberships(Set<ClubMembership> clubMemberships) {
		this.clubMemberships = clubMemberships;
	}
    
    

}
