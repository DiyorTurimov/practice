package uz.msi.crmmarketboot.repository;

import uz.msi.crmmarketboot.entity.Customer;

import java.sql.SQLException;
import java.util.Optional;

/**
 * This interface defines operations to interact with the customer data store.
 */
public interface CustomerDao {

    /**
     * Finds a customer by their unique identifier.
     *
     * @param id The unique identifier of the customer.
     * @return The customer object if found, otherwise null.
     */
    Optional<Customer> findById(Long id);

    /**
     * Finds a customer by their username (phone number).
     *
     * @param username The username (phone number) of the customer.
     * @return The customer object if found, otherwise null.
     */
    Optional<Customer> findByUsername(String username);

    /**
     * Saves or updates a customer in the data store.
     *
     * @param customer The customer object to save or update.
     * @return The saved or updated customer object.
     */
    Customer save(Customer customer); // INSERT UPDATE

    /**
     * Deletes a customer from the data store by their unique identifier.
     *
     * @param id The unique identifier of the customer to delete.
     * @return The deleted customer object if found and deleted, otherwise null.
     */
    Optional<Customer> deleteById(Long id);

    /**
     * Deletes a customer from the data store by their username (phone number).
     *
     * @param username The username (phone number) of the customer to delete.
     * @return The deleted customer object if found and deleted, otherwise null.
     */
    Optional<Customer> deleteByUsername(String username);
}
