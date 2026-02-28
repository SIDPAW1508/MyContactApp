package com.seveneleven.mycontactapp.contact.tag;
public enum PredefinedTag {
   FAMILY,
   WORK,
   FRIENDS;
   public Tag toTag() {
       return new Tag(name());
   }
}