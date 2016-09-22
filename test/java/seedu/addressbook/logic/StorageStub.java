package seedu.addressbook.logic;


import seedu.addressbook.data.AddressBook;
import seedu.addressbook.storage.Storage;

/**
 * Stub of Storage which doesn't do much
 */
public class StorageStub implements Storage {

    @Override
    public void save(AddressBook addressBook) throws StorageOperationException {

    }

    @Override
    public AddressBook load() throws StorageOperationException {
        return new AddressBook();
    }

    @Override
    public String getPath() {
        return null;
    }
}
