package uz.msi.crmmarketboot.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Component;
import uz.msi.crmmarketboot.entity.Customer;
import uz.msi.crmmarketboot.repository.CustomerDao;
import uz.msi.crmmarketboot.repository.mapper.CustomerRowMapper;
import uz.msi.crmmarketboot.repository.query.CustomerQuery;

import javax.swing.text.html.Option;
import java.sql.SQLException;
import java.util.Locale;
import java.util.Objects;
import java.util.Optional;

@Component
public class CustomerDaoImpl implements CustomerDao {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    private CustomerRowMapper customerRowMapper;

    /**
     * Finds a customer by their unique identifier.
     *
     * @param id The unique identifier of the customer.
     * @return The customer object if found, otherwise null.
     */
    @Override
    public Optional<Customer> findById(Long id) {
        return Optional.ofNullable(jdbcTemplate.queryForObject(CustomerQuery.FIND_BY_ID.getQuery(),
                new MapSqlParameterSource().addValue("id", id),
                customerRowMapper));
    }

    /**
     * Finds a customer by their username (phone number).
     *
     * @param username The username (phone number) of the customer.
     * @return The customer object if found, otherwise null.
     */
    @Override
    public Optional<Customer> findByUsername(String username) {
        return Optional.ofNullable(jdbcTemplate.queryForObject(CustomerQuery.FIND_BY_USERNAME.getQuery(),
                new MapSqlParameterSource().addValue("username", username), customerRowMapper));
    }

    /**
     * Saves or updates a customer in the data store.
     *
     * @param customer The customer object to save or update.
     * @return The saved or updated customer object.
     */
    @Override
    public Customer save(Customer customer) {
        if (Objects.nonNull(customer.getId())) {
            jdbcTemplate.update(CustomerQuery.UPDATE_USER_BY_ID.getQuery(),
                    new MapSqlParameterSource().addValue("firstname",customer.getFirstName())
                            .addValue("lastname",customer.getLastName())
                            .addValue("email",customer.getEmail()));
        } else {
            Long id = jdbcTemplate.queryForObject(CustomerQuery.SAVE_NEW_ONE.getQuery(),
                    new MapSqlParameterSource()
                            .addValue("username", customer.getUsername())
                            .addValue("firstname", customer.getFirstName())
                            .addValue("lastname", customer.getLastName())
                            .addValue("email", customer.getEmail()), Long.class);
            customer.setId(id);
        }

        return customer;

    }

    /**
     * Deletes a customer from the data store by their unique identifier.
     *
     * @param id The unique identifier of the customer to delete.
     * @return The deleted customer object if found and deleted, otherwise null.
     */
    @Override
    public Optional<Customer> deleteById(Long id) {
        return findById(id).map(customer -> {
            jdbcTemplate.queryForObject(CustomerQuery.DELETE_BY_ID.getQuery(),
                    new MapSqlParameterSource().addValue("id", id), customerRowMapper);
            return customer;
        });
    }

    /**
     * Deletes a customer from the data store by their username (phone number).
     *
     * @param username The username (phone number) of the customer to delete.
     * @return The deleted customer object if found and deleted, otherwise null.
     */
    @Override
    public Optional<Customer> deleteByUsername(String username) {
        return findByUsername(username).map(customer -> {
            jdbcTemplate.update(CustomerQuery.DELETE_BY_USERNAME.getQuery(),
                    new MapSqlParameterSource().addValue("username", username));
            return customer;
        });
    }
}
