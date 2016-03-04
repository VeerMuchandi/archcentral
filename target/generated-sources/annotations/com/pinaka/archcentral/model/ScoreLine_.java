package com.pinaka.archcentral.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(ScoreLine.class)
public abstract class ScoreLine_ {

	public static volatile SingularAttribute<ScoreLine, Long> id;
	public static volatile ListAttribute<ScoreLine, Score> scores;
	public static volatile SingularAttribute<ScoreLine, ScoreCard> scoreCard;
	public static volatile SingularAttribute<ScoreLine, Integer> round;

}

