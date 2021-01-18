package beans;

public class Role {
    private String role_name; //nom de role
    private String identified; //IDENTIFIED or NOT IDENTIFIED
    private String identification; // BY / USING ...

    private Boolean externally;
    private Boolean globally;
    private Boolean byApp;
    private Boolean byPassword;

    private String password_;
    private String package_;
    private String schema_;

    public Role() {
    }

    public Role(String role_name, String identified, String identification, Boolean externally, Boolean globally, Boolean byApp, Boolean byPassword, String password_, String package_, String schema_) {
        this.role_name = role_name;
        this.identified = identified;
        this.identification = identification;
        this.externally = externally;
        this.globally = globally;
        this.byApp = byApp;
        this.byPassword = byPassword;
        this.password_ = password_;
        this.package_ = package_;
        this.schema_ = schema_;
    }

    public String getRole_name() {
        return role_name;
    }

    public void setRole_name(String role_name) {
        this.role_name = role_name;
    }

    public String getIdentified() {
        return identified;
    }

    public void setIdentified(String identified) {
        this.identified = identified;
    }

    public String getIdentification() {
        return identification;
    }

    public void setIdentification(String identification) {
        this.identification = identification;
    }

    public Boolean isExternally() {
        return externally;
    }

    public void setExternally(Boolean externally) {
        this.externally = externally;
    }

    public Boolean isGlobally() {
        return globally;
    }

    public void setGlobally(Boolean globally) {
        this.globally = globally;
    }

    public Boolean isByApp() {
        return byApp;
    }

    public void setByApp(Boolean byApp) {
        this.byApp = byApp;
    }

    public Boolean isByPassword() {
        return byPassword;
    }

    public void setByPassword(Boolean byPassword) {
        this.byPassword = byPassword;
    }

    public String getPassword_() {
        return password_;
    }

    public void setPassword_(String password_) {
        this.password_ = password_;
    }

    public String getPackage_() {
        return package_;
    }

    public void setPackage_(String package_) {
        this.package_ = package_;
    }

    public String getSchema_() {
        return schema_;
    }

    public void setSchema_(String schema_) {
        this.schema_ = schema_;
    }
}
