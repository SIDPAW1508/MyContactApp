package com.seveneleven.mycontactapp.contact.search;

import java.util.List;

import java.util.function.Predicate;

import com.seveneleven.mycontactapp.contact.model.Contact;

public class CompositeSearchCriteria implements SearchCriteria {

    private final List<SearchCriteria> criteriaList;

    public CompositeSearchCriteria(List<SearchCriteria> criteriaList) {

        this.criteriaList = criteriaList;

    }

    @Override

    public Predicate<Contact> toPredicate() {

        return criteriaList.stream()

                .map(SearchCriteria::toPredicate)

                .reduce(contact -> true, Predicate::and);

    }

}
 