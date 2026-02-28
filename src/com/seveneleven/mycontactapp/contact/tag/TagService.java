package com.seveneleven.mycontactapp.contact.tag;
import java.util.HashSet;
import java.util.Set;
public class TagService {
   private final Set<Tag> allTags = new HashSet<>();
   public Tag createTag(String name) {
       Tag tag = new Tag(name);
       allTags.add(tag);
       return tag;
   }
   public Set<Tag> getAllTags() {
       return Set.copyOf(allTags);
   }
}