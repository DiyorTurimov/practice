package uz.msi.crmmarketboot.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Customer {
    private Long id;
    /**
     * Username is unique phone number
     */
    private String username;
    private String firstName;
    private String lastName;
    private String email;
}