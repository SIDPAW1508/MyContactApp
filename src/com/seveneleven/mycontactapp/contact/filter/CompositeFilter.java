package com.seveneleven.mycontactapp.contact.filter;
import com.seveneleven.mycontactapp.contact.model.Contact;
import java.util.List;
import java.util.function.Predicate;
public class CompositeFilter implements ContactFilter {
   private final List<ContactFilter> filters;
   public CompositeFilter(List<ContactFilter> filters) {
       this.filters = filters;
   }
   @Override
   public Predicate<Contact> toPredicate() {
       return filters.stream()
                     .map(ContactFilter::toPredicate)
                     .reduce(Predicate::and)
                     .orElse(c -> true);
   }
}