package com.pinaka.archcentral.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Club.class)
public abstract class Club_ {

	public static volatile SingularAttribute<Club, Long> id;
	public static volatile SingularAttribute<Club, Boolean> enabled;
	public static volatile SingularAttribute<Club, Address> address;
	public static volatile SetAttribute<Club, ClubMembership> clubMemberships;
	public static volatile SingularAttribute<Club, String> contactName;
	public static volatile SingularAttribute<Club, String> name;
	public static volatile SingularAttribute<Club, String> contactPhoneNumber;

}

