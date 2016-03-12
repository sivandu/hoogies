package entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(HoogieUser.class)
public abstract class HoogieUser_ {

	public static volatile ListAttribute<HoogieUser, HoogieGroup> managedGroups;
	public static volatile SingularAttribute<HoogieUser, String> firstName;
	public static volatile SingularAttribute<HoogieUser, String> lastName;
	public static volatile SingularAttribute<HoogieUser, String> password;
	public static volatile SingularAttribute<HoogieUser, String> phoneNumber;
	public static volatile ListAttribute<HoogieUser, Review> reviews;
	public static volatile ListAttribute<HoogieUser, HoogieGroup> groups;
	public static volatile SingularAttribute<HoogieUser, String> userName;
	public static volatile SingularAttribute<HoogieUser, Integer> userId;
	public static volatile SingularAttribute<HoogieUser, String> email;
	public static volatile SingularAttribute<HoogieUser, String> picture;

}

