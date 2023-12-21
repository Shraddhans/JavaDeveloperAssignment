package com.assignment.shraddha.entity;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
@Entity
@Table(name = "CustomerDetails", uniqueConstraints = @UniqueConstraint(columnNames = "email"))
@NoArgsConstructor
@AllArgsConstructor
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Name shouldn't be null or empty")
    private String name;
    @NotBlank(message = "email shouldn't be null or empty")
    @Pattern(regexp = "^[A-Za-z0-9+_.-]+@(.+)$", message = "Invalid Email Id")
    private String email;
    @NotBlank(message = "dob shouldn't be null or empty")
    @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}", message = "Date of birth must be in yyyy-MM-dd format")
    private String dob;
    @Enumerated(EnumType.STRING)

    @NotNull(message = "occupation shouldn't be null or empty")
    private Occupation occupation;
    @Enumerated(EnumType.STRING)
    private CustomerGroup customerGroup;
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public Occupation getOccupation() {
        return occupation;
    }

    public void setOccupation(Occupation occupation) {
        this.occupation = occupation;
    }

    public CustomerGroup getCustomerGroup() {
        return customerGroup;
    }

    public void setCustomerGroup(CustomerGroup customerGroup) {
        this.customerGroup = customerGroup;
    }

}
