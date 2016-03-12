package entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(HoogieGroup.class)
public abstract class HoogieGroup_ {

	public static volatile SingularAttribute<HoogieGroup, String> address;
	public static volatile SingularAttribute<HoogieGroup, Integer> groupId;
	public static volatile SingularAttribute<HoogieGroup, String> description;
	public static volatile SingularAttribute<HoogieGroup, String> picture;
	public static volatile SingularAttribute<HoogieGroup, Integer> maxParticipants;
	public static volatile SingularAttribute<HoogieGroup, Integer> duration;
	public static volatile SingularAttribute<HoogieGroup, String> groupName;
	public static volatile SingularAttribute<HoogieGroup, String> phoneNumber;
	public static volatile ListAttribute<HoogieGroup, Review> reviews;
	public static volatile SingularAttribute<HoogieGroup, String> groupHour;
	public static volatile SingularAttribute<HoogieGroup, Interest> interest;
	public static volatile SingularAttribute<HoogieGroup, Integer> price;
	public static volatile SingularAttribute<HoogieGroup, HoogieUser> organizer;
	public static volatile SingularAttribute<HoogieGroup, String> days;
	public static volatile ListAttribute<HoogieGroup, Message> messages;
	public static volatile SingularAttribute<HoogieGroup, String> email;
	public static volatile ListAttribute<HoogieGroup, HoogieUser> participants;

}

