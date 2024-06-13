package uz.msi.crmmarketboot.repository.query;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CustomerQuery {

    FIND_BY_ID("SELECT * FROM customers WHERE id = :id"),

    FIND_BY_USERNAME("SELECT * FROM customers WHERE username = :username"),

    SAVE_NEW_ONE("""
            INSERT INTO customers(username, first_name, last_name, email)
            VALUES (:username, :firstname, :lastname, :email)
            RETURNING id;
            """),

    UPDATE_USER_BY_ID("""
            UPDATE customers
            SET first_name = :firstname, last_name = :lastname, email = :email
            WHERE id = :id;
            """),

    DELETE_BY_ID("DELETE FROM customers WHERE id = :id"),

    DELETE_BY_USERNAME("DELETE FROM customers WHERE username = :username");

    private String query;

}