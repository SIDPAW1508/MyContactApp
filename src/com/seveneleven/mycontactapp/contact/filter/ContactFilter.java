package com.seveneleven.mycontactapp.contact.filter;
import com.seveneleven.mycontactapp.contact.model.Contact;
import java.util.function.Predicate;
public interface ContactFilter {
	Predicate<Contact> toPredicate();
}