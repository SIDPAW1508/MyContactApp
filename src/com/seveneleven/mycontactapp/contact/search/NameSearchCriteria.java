package com.seveneleven.mycontactapp.contact.search;
import com.seveneleven.mycontactapp.contact.model.Contact;
public class NameSearchCriteria implements SearchCriteria {
   private final String keyword;
   public NameSearchCriteria(String keyword) {
       this.keyword = keyword.toLowerCase();
   }
   @Override
   public java.util.function.Predicate<Contact> toPredicate() {
       return c -> c.getName().toLowerCase().contains(keyword);
   }
}