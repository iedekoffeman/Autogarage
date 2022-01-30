package nl.novi.autogarage.dto;


import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Pattern;

public class CarRequestDto {

    //attributes
    @Pattern(regexp="^(?=.{8}$)(?![\\d-]+$|[a-z-]+$)[^\\W_]+(?:-[^\\W_]+)+$", message = "{error.licensePlate.notValid}")
    private String licenseplate;

    private String licenseRegistrationFileName ;

    public String getLicenseplate() {
        return licenseplate;
    }

    public void setLicenseplate(String licenseplate) {
        this.licenseplate = licenseplate;
    }

    public String getLicenseRegistrationFileName() {
        return licenseRegistrationFileName;
    }

    public void setLicenseRegistrationFileName(String licenseRegistrationFileName) {
        this.licenseRegistrationFileName = licenseRegistrationFileName;
    }


}
