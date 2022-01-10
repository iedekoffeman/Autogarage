package nl.novi.autogarage.dto;


import org.springframework.web.multipart.MultipartFile;

public class CarRequestDto {

    //attributes
    private String licenseplate;
    private String licenseRegistrationFileName ;
    private MultipartFile file;

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

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }

}
