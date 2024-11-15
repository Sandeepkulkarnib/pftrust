package com.coreintegra.pftrust.controllers.administration;

import com.coreintegra.pftrust.controllers.API;

public class AdministrationEndpoints {

    public static final String AUTH_SERVICE_ENDPOINT = API.API_VERSION_1 + API.PATH_SEPARATOR + "auth";

    public static final String TENANTS_ENDPOINT = API.PATH_SEPARATOR + "tenants";
    public static final String LOGIN_ENDPOINT = API.PATH_SEPARATOR + "login";

    public static final String ACCOUNT_SERVICE_ENDPOINT = API.API_VERSION_1 + API.PATH_SEPARATOR + "account";

    public static final String USER_PERMISSIONS_ENDPOINT = API.PATH_SEPARATOR + "permissions";
    public static final String USER_PROFILE_ENDPOINT = API.PATH_SEPARATOR + "profile";
    public static final String CHANGE_PASSWORD_ENDPOINT = API.PATH_SEPARATOR + "change-password";

    public static final String DEPARTMENTS = API.PATH_SEPARATOR + "departments";

    public static final String VALIDATE_PERMISSION = API.PATH_SEPARATOR + "validatePermissions";

    public static final String ACTIVITY_LOG_SERVICE_ENDPOINT = API.API_VERSION_1 + API.PATH_SEPARATOR + "logs";

}
