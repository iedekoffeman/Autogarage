package nl.novi.autogarage.dto;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CustomerRequestDto {

        //attributes
        @NotBlank
        @Size(min=2, message="{error.firstName.min2Characters}")
        private String firstname;
        @NotBlank
        private String lastname;
        @NotNull
        @Size(min=10, max=10, message="{error.phoneNumber.minMax10Characters}")
        private String phonenumber;

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

        public String getPhonenumber() {
            return phonenumber;
        }

        public void setPhonenumber(String phonenumber) {
            this.phonenumber = phonenumber;
        }
    }
