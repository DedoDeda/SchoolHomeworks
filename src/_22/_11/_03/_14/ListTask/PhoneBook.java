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
     * Merge two phone books into a new book, keeping the result sorted,
     * and without duplicated contacts.
     */
    public static PhoneBook merge(PhoneBook book1, PhoneBook book2) {
        // If one of the books (or both) is empty, return a copy of the other one.
        if (book1.contacts == null) {
            return new PhoneBook(book2);
        }
        if (book2.contacts == null) {
            return new PhoneBook(book1);
        }

        // The merged contact list.
        Node<Contact> mergedContacts;
        // Positions for iteration over the two contact lists.
        Node<Contact> pos1 = book1.contacts;
        Node<Contact> pos2 = book2.contacts;

        // Make a first node to the new list.
        int comparisonRes = pos1.getValue().name.compareTo(pos2.getValue().name);
        if (comparisonRes < 0) {
            // pos1's name should be before pos2's name.
            mergedContacts = new Node<>(pos1.getValue());
            pos1 = pos1.getNext();
        } else if (comparisonRes == 0) {
            // The names are the same, so add one of them, but
            // increase both positions. (We don't want duplicates.)
            mergedContacts = new Node<>(pos1.getValue());
            pos1 = pos1.getNext();
            pos2 = pos2.getNext();
        } else {
            // pos2's name should be before pos1's name.
            mergedContacts = new Node<>(pos2.getValue());
            pos2 = pos2.getNext();
        }
        // Set a position node to the merged list.
        Node<Contact> mergedPos = mergedContacts;

        /* As long as we aren't done with one of the lists (or both),
        continue. */
        while (pos1 != null && pos2 != null) {
            // The result of the comparison between the two current contacts' names.
            comparisonRes = pos1.getValue().name.compareTo(pos2.getValue().name);
            if (comparisonRes < 0) {
                // pos1's name should be before pos2's name.
                mergedPos = ListUtils.insertAfter(mergedPos, pos1.getValue());
                pos1 = pos1.getNext();
            } else if (comparisonRes == 0) {
                // The names are the same, so add one of them, but
                // increase both positions. (We don't want duplicates.)
                mergedPos = ListUtils.insertAfter(mergedPos, pos1.getValue());
                pos1 = pos1.getNext();
                pos2 = pos2.getNext();
            } else {
                // pos2's name should be before pos1's name.
                mergedPos = ListUtils.insertAfter(mergedPos, pos2.getValue());
                pos2 = pos2.getNext();
            }
        }

        // If one of the lists isn't over yet, extend the result by its copy.
        if (pos1 == null) {
            mergedPos.setNext(ListUtils.deepCopy(pos2));
        } else { // pos2 = null
            mergedPos.setNext(ListUtils.deepCopy(pos1));
        }

        // Simply creating the book via the constructor will copy the given list,
        // but this list is already a copy.
        return makeFromListRef(mergedContacts);
    }

    /**
     * Makes a new book, given a contacts list, without copying the list.
     */
    private static PhoneBook makeFromListRef(Node<Contact> list) {
        PhoneBook book = new PhoneBook();
        book.contacts = list;
        return book;
    }

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

        /** Makes a contact given its name and phone. */
        public Contact(String name, String phone) {
            this.name = name;
            this.phone = phone;
        }

        /** Copy constructor. */
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

    public static void main(String[] args) {
        PhoneBook pb = new PhoneBook();
        pb.addContact("Galit Israel", "03-9089730");
        pb.addContact("Avner Chohen", "02-7474747");
        pb.addContact("Gershon Avraham", "02-8900011");
        pb.addContact("Daniela Yariv", "04-5677708");
        pb.addContact("Alice Marlo", "04-5699300");
        pb.addContact("Bob Denver", "04-5699300");

        System.out.println(pb);
    }
}
