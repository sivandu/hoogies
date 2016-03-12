package entity;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Review.class)
public abstract class Review_ {

	public static volatile SingularAttribute<Review, Date> rtimestamp;
	public static volatile SingularAttribute<Review, Integer> reviewId;
	public static volatile SingularAttribute<Review, HoogieUser> user;
	public static volatile SingularAttribute<Review, String> content;
	public static volatile SingularAttribute<Review, HoogieGroup> group;

}

