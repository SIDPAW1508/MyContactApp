package com.seveneleven.mycontactapp.contact.tag;
import java.util.Objects;
public final class Tag {
   private final String name;
   public Tag(String name) {
       if (name == null || name.isBlank()) {
           throw new IllegalArgumentException("Tag name cannot be empty");
       }
       this.name = name.trim().toLowerCase();
   }
   public String getName() {
       return name;
   }
   // Required for Set uniqueness
   @Override
   public boolean equals(Object o) {
       if (this == o) return true;
       if (!(o instanceof Tag)) return false;
       Tag tag = (Tag) o;
       return name.equals(tag.name);
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