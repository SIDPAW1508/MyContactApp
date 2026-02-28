package com.seveneleven.mycontactapp.contact.search;
import com.seveneleven.mycontactapp.contact.model.Contact;
public class TagSearchCriteria implements SearchCriteria {
   private final String tag;
   public TagSearchCriteria(String tag) {
       this.tag = tag.toUpperCase();
   }
   @Override
   public java.util.function.Predicate<Contact> toPredicate() {
       return c -> c.getTags().contains(tag);
   }
}