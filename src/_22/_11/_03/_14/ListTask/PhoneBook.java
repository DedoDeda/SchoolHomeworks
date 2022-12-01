package _22._11._03._14.ListTask;

import Reusable.Copyable;
import Reusable.ListUtils;
import unit4.collectionsLib.Node;

import java.util.function.Predicate;

/**
 * A phone book containing a contacts list.
 */
public class PhoneBook {

    /**
     * A lexicographically sorted contacts list. No repeated names are expected.
     */
    Node<Contact> contacts;

    /**
     * Makes an empty phone book.
     */
    public PhoneBook() {
        contacts = null;
    }

    /**
     * Makes a new phone book, copying the given list.
     */
    public PhoneBook(Node<Contact> contacts) {
        this.contacts = ListUtils.deepCopy(contacts);
    }

    /**
     * Copy constructor.
     */
    public PhoneBook(PhoneBook orig) {
        this(orig.contacts);
    }

    /**
     * Add a new contact to the book.
     */
    public void addContact(String name, String phone) {
        Contact newContact = new Contact(name, phone);
        contacts = ListUtils.insertSorted(contacts, newContact,
                (c1, c2) -> c1.name.compareTo(c2.name));
    }

    /**
     * Delete the contact of the given name.
     */
    public void delContact(String name) {
        contacts = ListUtils.removeFirstOccur(contacts,
                (Predicate<Contact>) contact -> contact.name.equals(name));
    }

    /**
     * Get all the contacts' names in the book as a string array.
     */
    public String[] getAllContactsNames() {
        // Make a new array from the list based on its size.
        String[] names = new String[ListUtils.size(contacts)];

        // Iterate over the list, setting each array element with its
        // matching list element.
        int idx = 0;
        for (Node<Contact> pos = contacts; pos != null; pos = pos.getNext(), idx++) {
            Contact contact = pos.getValue();
            names[idx] = contact.name;
        }

        return names;
    }

    /**
     * Get the phone number (as a string) of the contact of the given name.
     * Returns null if there's no contact of the given name in the book.
     */
    public String getPhone(String name) {
        // Try to find the relevant contact.
        Contact target = getContact(name);
        // If found, return its phone, otherwise return null.
        return target == null ? null : target.getPhone();
    }

    /**
     * Update the phone number of the contact of the given name.
     */
    public void setPhone(String name, String newPhone) {
        // Try to find the relevant contact.
        Contact targetContact = getContact(name);
        // If we found, update its phone number.
        if (targetContact != null) {
            targetContact.setPhone(newPhone);
        }
    }

    /**
     * Get the contact of the given name. (For internal use.)
     */
    private Contact getContact(String name) {
        // Find the contact with the given name using a predicate.
        Node<Contact> targetContactNode = ListUtils.findNode(contacts,
                contactNode -> contactNode.getValue().name.equals(name));
        return targetContactNode == null ? null : targetContactNode.getValue();
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof PhoneBook castedOther) {
            return ListUtils.equals(contacts, castedOther.contacts);
        }

        return super.equals(other);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (Node<Contact> pos = contacts; pos != null; pos = pos.getNext()) {
            builder.append(pos.getValue()).append("\n");
        }

        return builder.toString();
    }

    /**
     * A contact that has a name and phone number.
     */
    public static class Contact implements Copyable<Contact> {
        /**
         * The contact's name.
         */
        private final String name;
        /**
         * The contact's phone number.
         */
        private String phone;

        /**
         * Makes a contact given its name and phone.
         */
        public Contact(String name, String phone) {
            this.name = name;
            this.phone = phone;
        }

        /**
         * Copy constructor.
         */
        public Contact(Contact orig) {
            this.name = orig.name;
            this.phone = orig.phone;
        }

        @Override
        public Contact copy() {
            return new Contact(this);
        }

        public String getName() {
            return name;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        @Override
        public boolean equals(Object other) {
            if (other instanceof Contact castedOther) {
                return name.equals(castedOther.name) && phone.equals(castedOther.phone);
            }

            return super.equals(other);
        }

        @Override
        public String toString() {
            return String.format("<%s> <%s>", name, phone);
        }
    }
}