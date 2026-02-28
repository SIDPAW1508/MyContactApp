package com.seveneleven.mycontactapp.contact.search;
import java.util.regex.Pattern;
import com.seveneleven.mycontactapp.contact.model.Contact;
public class PhoneSearchCriteria implements SearchCriteria {
   private final Pattern pattern;
   public PhoneSearchCriteria(String phoneFragment) {
       this.pattern = Pattern.compile(".*" + phoneFragment + ".*");
   }
   @Override
   public java.util.function.Predicate<Contact> toPredicate() {
       return c -> c.getPhoneNumbers().stream()
               .anyMatch(p -> pattern.matcher(p.getNumber()).matches());
   }
}