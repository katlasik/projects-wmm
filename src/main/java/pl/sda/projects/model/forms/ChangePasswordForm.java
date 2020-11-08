package pl.sda.projects.model.forms;

import javax.validation.constraints.Size;
import java.util.Objects;

public class ChangePasswordForm {
    @Size(min = 6, max = 24, message = "{registration.errorMsg.password}")
    private String currentPassword;

    @Size(min = 6, max = 24, message = "{registration.errorMsg.password}")
    private String newPassword;

    @Size(min = 6, max = 24, message = "{registration.errorMsg.password}")
    private String confirmPassword;

    public ChangePasswordForm(String currentPassword, String newPassword, String confirmPassword) {
        this.currentPassword = currentPassword;
        this.newPassword = newPassword;
        this.confirmPassword = confirmPassword;
    }
    public String getCurrentPassword(){
        return currentPassword;
    }
    public void setCurrentPassword(String name){
        this.currentPassword = name;
    }
    public String getNewPassword(){
        return newPassword;
    }
    public String getConfirmPassword(){
        return confirmPassword;
    }
    public void setConfirmPassword(String confirmPassword){
        this.confirmPassword = confirmPassword;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ChangePasswordForm that = (ChangePasswordForm) o;
        return Objects.equals(currentPassword, that.currentPassword) &&
                Objects.equals(newPassword, that.newPassword) &&
                Objects.equals(confirmPassword, that.confirmPassword);
    }

    @Override
    public int hashCode() {
        return Objects.hash(currentPassword, newPassword, confirmPassword);

    }
}



