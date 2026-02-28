package com.seveneleven.mycontactapp.contact.search;
import java.util.function.Predicate;
import com.seveneleven.mycontactapp.contact.model.Contact;
@FunctionalInterface
public interface SearchCriteria {
	Predicate<Contact> toPredicate();
}