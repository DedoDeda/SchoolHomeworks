package _22._11._03._14.ListTask;

public class ListTaskMain {

    public static PhoneBook mergePhoneBooks(PhoneBook pb1, PhoneBook pb2) {
        PhoneBook result = new PhoneBook();
        for (String name : pb1.getAllContactsNames()) {
            result.addContact(name, pb1.getPhone(name));
        }

        for (String name : pb2.getAllContactsNames()) {
            // Add only if the contact isn't int the result.
            if (result.getPhone(name) == null) {
                result.addContact(name, pb2.getPhone(name));
            }
        }

        return result;
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
