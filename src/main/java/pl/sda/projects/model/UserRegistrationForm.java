package pl.sda.projects.model;

import java.util.Objects;

public class UserRegistrationForm {

        private String username;
        private String password;
        private String passwordConfirmation;
        private String emailAdress;
        private String male;
        private String birghday;

        public String getEmailAdress() {
                return emailAdress;
        }

        public void setEmailAdress(String emailAdress) {
                this.emailAdress = emailAdress;
        }

        public String getMale() {
                return male;
        }

        public void setMale(String male) {
                this.male = male;
        }

        public String getBirghday() {
                return birghday;
        }

        public void setBirghday(String birghday) {
                this.birghday = birghday;
        }

        public String getUsername() {
                return username;
        }

        public void setUsername(String username) {
                this.username = username;
        }

        public String getPassword() {
                return password;
        }

        public void setPassword(String password) {
                this.password = password;
        }

        public String getPasswordConfirmation() {
                return passwordConfirmation;
        }

        public void setPasswordConfirmation(String passwordConfirmation) {
                this.passwordConfirmation = passwordConfirmation;
        }

        @Override
        public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;
                UserRegistrationForm that = (UserRegistrationForm) o;
                return Objects.equals(username, that.username) &&
                        Objects.equals(password, that.password) &&
                        Objects.equals(passwordConfirmation, that.passwordConfirmation) &&
                        Objects.equals(emailAdress, that.emailAdress) &&
                        Objects.equals(male, that.male) &&
                        Objects.equals(birghday, that.birghday);
        }

        @Override
        public int hashCode() {
                return Objects.hash(username, password, passwordConfirmation, emailAdress, male, birghday);
        }

        @Override
        public String toString() {
                return "UserRegistration{" +
                        "username='" + username + '\'' +
                        ", password='" + password + '\'' +
                        ", passwordConfirmation='" + passwordConfirmation + '\'' +
                        ", emailAress='" + emailAdress + '\'' +
                        ", male='" + male + '\'' +
                        ", birghtday='" + birghday + '\'' +
                        '}';
        }
}
