package com.seveneleven.mycontactapp.contact.filter;
import com.seveneleven.mycontactapp.contact.model.Contact;
import java.util.function.Predicate;
public class TagFilter implements ContactFilter {
   private final String tag;
   public TagFilter(String tag) {
       this.tag = tag.toLowerCase();
   }
   @Override
   public Predicate<Contact> toPredicate() {
       return c -> c.getTags().stream()
               .anyMatch(t -> t.getName().equalsIgnoreCase(tag));
   }
}