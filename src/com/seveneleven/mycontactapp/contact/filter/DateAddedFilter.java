package com.seveneleven.mycontactapp.contact.filter;
import com.seveneleven.mycontactapp.contact.model.Contact;
import java.time.LocalDate;
import java.util.function.Predicate;
public class DateAddedFilter implements ContactFilter {
	private final LocalDate afterDate;
	public DateAddedFilter(LocalDate afterDate) {
		this.afterDate = afterDate;
	}
	@Override
	public Predicate<Contact> toPredicate() {
		return c -> c.getCreatedAt().toLocalDate().isAfter(afterDate);
	}
}