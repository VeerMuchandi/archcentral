package com.pinaka.archcentral.model;

import java.util.Calendar;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Archer.class)
public abstract class Archer_ {

	public static volatile SingularAttribute<Archer, Calendar> dateOfBirth;
	public static volatile SingularAttribute<Archer, Long> id;
	public static volatile SetAttribute<Archer, ClubMembership> clubMemberships;
	public static volatile SingularAttribute<Archer, String> name;
	public static volatile SingularAttribute<Archer, Character> gender;
	public static volatile SingularAttribute<Archer, String> userName;
	public static volatile SetAttribute<Archer, ScoreCard> scorecards;
	public static volatile SingularAttribute<Archer, ArcherClass> archerClass;

}

