package com.seveneleven.mycontactapp.contact.tag;

import java.util.Objects;

public class Tag {

    private final String name;

    public Tag(String name) {

        if (name == null || name.isBlank()) {

            throw new IllegalArgumentException("Tag name cannot be empty");

        }

        this.name = name.toLowerCase();

    }

    public String getName() {

        return name;

    }

    // VERY IMPORTANT for Set behavior

    @Override

    public boolean equals(Object o) {

        if (this == o) return true;

        if (!(o instanceof Tag)) return false;

        Tag tag = (Tag) o;

        return name.equalsIgnoreCase(tag.name);

    }

    @Override

    public int hashCode() {

        return Objects.hash(name);

    }

    @Override

    public String toString() {

        return name;

    }

}
 