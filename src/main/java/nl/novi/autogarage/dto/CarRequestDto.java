package nl.novi.autogarage.dto;


import org.springframework.web.multipart.MultipartFile;

public class CarRequestDto {

    //attributes
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
