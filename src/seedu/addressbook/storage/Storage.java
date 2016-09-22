package seedu.addressbook.storage;

import seedu.addressbook.data.AddressBook;
import seedu.addressbook.data.exception.IllegalValueException;

/**
 * Abstraction of storage
 */
public interface Storage {
    void save(AddressBook addressBook) throws StorageOperationException;

    AddressBook load() throws StorageOperationException;

    String getPath();

    /**
     * Signals that the given file path does not fulfill the storage filepath constraints.
     */
    class InvalidStorageFilePathException extends IllegalValueException {
        public InvalidStorageFilePathException(String message) {
            super(message);
        }
    }

    /**
     * Signals that some error has occured while trying to convert and read/write data between the application
     * and the storage file.
     */
    class StorageOperationException extends Exception {
        public StorageOperationException(String message) {
            super(message);
        }
    }
}
