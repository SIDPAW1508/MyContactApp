package com.seveneleven.mycontactapp.contact.filter;

import java.util.function.Predicate;
import com.seveneleven.mycontactapp.contact.model.Contact;

/**
 * Filter that selects contacts whose interaction count
 * is greater than or equal to a minimum threshold.
 *
 * Used for identifying frequently contacted users.
 */
public class FrequentContactFilter implements ContactFilter {

    private final int minCount;

    /**
     * @param minCount minimum number of interactions required
     */
    public FrequentContactFilter(int minCount) {
        this.minCount = minCount;
    }

    @Override
    public Predicate<Contact> toPredicate() {
        return contact -> contact.getContactCount() >= minCount;
    }
}