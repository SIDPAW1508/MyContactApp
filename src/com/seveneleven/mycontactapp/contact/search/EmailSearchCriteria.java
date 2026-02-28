package com.seveneleven.mycontactapp.contact.search;

import com.seveneleven.mycontactapp.contact.model.Contact;

public class EmailSearchCriteria implements SearchCriteria {

    private final String keyword;

    public EmailSearchCriteria(String keyword) {

        this.keyword = keyword.toLowerCase();

    }

    @Override

    public java.util.function.Predicate<Contact> toPredicate() {

        return c -> c.getEmailAddresses().stream()

                .anyMatch(e -> e.getEmail().toLowerCase().contains(keyword));

    }

}
 