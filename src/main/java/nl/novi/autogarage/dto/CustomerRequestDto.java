package nl.novi.autogarage.dto;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class CustomerRequestDto {

        //attributes
        @NotBlank
        @Size(min=2, message="Firstname should have at least 2 characters.")
        private String firstname;
        @NotBlank
        private String lastname;

        //getters and setters

        public String getFirstname() {
            return firstname;
        }

        public void setFirstname(String firstname) {
            this.firstname = firstname;
        }

        public String getLastname() {
            return lastname;
        }

        public void setLastname(String lastname) {
            this.lastname = lastname;
        }
    }
