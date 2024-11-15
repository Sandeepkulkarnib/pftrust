insert into privilege(id, created_at, updated_at, name)
VALUES (1, sysdate(), sysdate(), 'READ'),
       (2, sysdate(), sysdate(), 'CREATE'),
       (3, sysdate(), sysdate(), 'UPDATE'),
       (4, sysdate(), sysdate(), 'DELETE');

insert into role(id, created_at, updated_at, role)
VALUES (1, sysdate(), sysdate(), 'ADMIN'),
       (2, sysdate(), sysdate(), 'STAFF'),
       (3, sysdate(), sysdate(), 'VIEW');

insert into role_privilege(role_id, privilege_id)
VALUES (1, 1),(1, 2),(1, 3),(1, 4),
       (2, 1),(2, 2), (2, 3),
       (3, 1);

insert into pf_department(id, created_at, updated_at, name, label, type, mdi_icon, path)
VALUES (1, sysdate(), sysdate(), 'EMPLOYEE', 'Employee Management', 'pf', 'mdi-account-supervisor', '/employees'),
       (2, sysdate(), sysdate(), 'CONTRIBUTION', 'Contribution Management', 'pf', 'mdi-briefcase-plus', '/contributions'),
       (3, sysdate(), sysdate(), 'LOAN', 'loans Management', 'pf', 'mdi-cart-arrow-up', '/loans'),
       (4, sysdate(), sysdate(), 'TRANSFER_IN', 'TransferIn Management', 'pf', 'mdi-cart-arrow-down', '/transfer-ins'),
       (5, sysdate(), sysdate(), 'SETTLEMENT', 'Settlement Management', 'pf', 'mdi-human-dolly', '/settlements'),
       (6, sysdate(), sysdate(), 'TRANSFER_OUT', 'TransferOut Management', 'pf', 'mdi-human-dolly', '/transfer-outs'),
       (7, sysdate(), sysdate(), 'BACK_OFFICE', 'Pf Management', 'pf', 'mdi-key', '/back-office'),
       (9, sysdate(), sysdate(), 'JOBS', 'Pf Job Management', 'pf', 'mdi-account-hard-hat', '/jobs'),
       (12, sysdate(), sysdate(), 'DASHBOARD', 'Dashboard', 'pf', '', '/dashboard'),
       (13, sysdate(), sysdate(), 'YEAR_END_PROCESS', 'Year End Process', 'pf', 'mdi-account-hard-hat', '/year-end-process'),
        (8, sysdate(), sysdate(), 'ACCOUNT', 'Account', 'account', 'mdi-account-supervisor', '/accounts'),
       (10, sysdate(), sysdate(), 'USERS', 'User Management', 'account', 'mdi-account-hard-hat', '/accounts/users'),
       (11, sysdate(), sysdate(), 'TENANTS','Tenant Management', 'account', 'mdi-account-hard-hat', '/accounts/tenant'),
       (14, sysdate(), sysdate(), 'ACTIVITY_LOGS', 'Activity Logs', 'account', 'mdi-account-hard-hat', '/accounts/logs'),
       (15, sysdate(), sysdate(), 'CUSTROMER_SERVICE', 'customer service', 'pf', 'mdi-account-supervisor', '/customer-service-dashboard');

insert into sub_department(created_at, entity_id, is_active, updated_at, label, mdi_icon, name, path,
                           type, pf_department_id, permission)
VALUES (sysdate(), '', 1, sysdate(), 'Unit Codes', '', 'UNIT_CODES', '/unitcodes', 'backoffice', 7, 'READ|CREATE|UPDATE|DELETE'),
       (sysdate(), '', 1, sysdate(), 'Interest Rates', '', 'INTEREST_RATES', '/interest-rates', 'backoffice', 7, 'READ|CREATE|UPDATE|DELETE');

insert into sub_department(created_at, entity_id, is_active, updated_at, label, mdi_icon, name, path, type, pf_department_id, permission)
VALUES (sysdate(), '', 1, sysdate(), 'Fetch Employees', '', 'FETCH_EMPLOYEES', '/fetch-employees',
        'jobs', 9, 'READ|CREATE|UPDATE|DELETE'),
       (sysdate(), '', 1, sysdate(), 'Fetch Contributions', '', 'FETCH_CONTRIBUTIONS', '/fetch-contributions',
        'jobs', 9, 'READ|CREATE|UPDATE|DELETE'),
       (sysdate(), '', 1, sysdate(), 'Fetch Loans', '', 'FETCH_LOANS', '/fetch-loans',
        'jobs', 9, 'READ|CREATE|UPDATE|DELETE'),
       (sysdate(), '', 1, sysdate(), 'Fetch TransferIns', '', 'FETCH_TRANSFER_INS', '/fetch-transfer-in',
        'jobs', 9, 'READ|CREATE|UPDATE|DELETE'),
       (sysdate(), '', 1, sysdate(), 'Fetch Settlement/Transfer Out', '', 'FETCH_SETTLEMENTS', '/fetch-settlements',
        'jobs', 9, 'READ|CREATE|UPDATE|DELETE');

insert into sub_department(created_at, entity_id, is_active, updated_at, label, mdi_icon, name, path, type, pf_department_id, permission)
VALUES(sysdate(), '', 1, sysdate(), 'Search Employees', '', 'SEARCH_EMPLOYEES', '', 'employee', 1, 'READ|CREATE|UPDATE|DELETE'),
      (sysdate(), '', 1, sysdate(), 'Hire New Employees', '', 'HIRE_NEW_EMPLOYEES', '/hire', 'employee', 1, 'CREATE|UPDATE|DELETE'),
      (sysdate(), '', 1, sysdate(), 'Monthly Status Process', '', 'MONTHLY_STATUS_PROCESS', '/monthly-status-process', 'employee',
       1, 'CREATE|UPDATE|DELETE');

insert into sub_department(created_at, entity_id, is_active, updated_at, label, mdi_icon, name, path, type, pf_department_id, permission)
VALUES(sysdate(), '', 1, sysdate(), 'Search Loans', '', 'SEARCH_LOANS', '', 'loan', 3, 'READ|CREATE|UPDATE|DELETE'),
      (sysdate(), '', 1, sysdate(), 'Create Loans', '', 'CREATE_LOANS', '/create', 'loan', 3, 'CREATE|UPDATE|DELETE'),
      (SYSDATE(), '', 1, sysdate(), 'Send Emails', '', 'SEND_EMAILS', '/send-emails', 'loan', 3, 'CREATE|UPDATE|DELETE'),
      (SYSDATE(), '', 1, sysdate(), 'Loan Types', '', 'SEARCH_LOANS', '/loan-types', 'loan', 3, 'CREATE|UPDATE|DELETE'),
      (sysdate(), '', 1, sysdate(), 'Generate Bank Sheet', '', 'BANK_SHEET', '/generate-bank-sheet', 'loan', 3, 'CREATE|UPDATE|DELETE');

insert into sub_department(created_at, entity_id, is_active, updated_at, label, mdi_icon, name, path, type, pf_department_id, permission)
VALUES(sysdate(), '', 1, sysdate(), 'Search TransferIns', '', 'SEARCH_TRANSFER_IN', '', 'transferin', 4, 'READ|CREATE|UPDATE|DELETE'),
      (sysdate(), '', 1, sysdate(), 'Create TransferIns', '', 'CREATE_TRANSFER_IN', '/create', 'transferin', 4, 'CREATE|UPDATE|DELETE'),
      (sysdate(), '', 1, sysdate(), 'Send Emails', '', 'SEND_EMAILS', '/send-emails', 'transferin', 4, 'READ|CREATE|UPDATE|DELETE');

insert into sub_department(created_at, entity_id, is_active, updated_at, label, mdi_icon, name, path, type, pf_department_id, permission)
VALUES(sysdate(), '', 1, sysdate(), 'Search TransferOuts', '', 'SEARCH_TRANSFER_OUT', '', 'transferout', 6, 'READ|CREATE|UPDATE|DELETE'),
      (sysdate(), '', 1 , sysdate(), 'Create TransferOuts', '', 'CREATE_TRANSFER_OUT', '/create', 'transferout', 6, 'CREATE|UPDATE|DELETE'),
      (SYSDATE(), '', 1, sysdate(), 'Send Emails', '', 'SEND_EMAILS', '/send-emails', 'transferout', 6, 'CREATE|UPDATE|DELETE'),
      (sysdate(), '', 1, sysdate(), 'Generate Bank Sheet', '', 'BANK_SHEET', '/generate-bank-sheet', 'transferout', 6, 'CREATE|UPDATE|DELETE');

insert into sub_department(created_at, entity_id, is_active, updated_at, label, mdi_icon, name, path, type, pf_department_id, permission)
VALUES(sysdate(), '', 1, sysdate(), 'Search Settlements', '', 'SEARCH_SETTLEMENTS', '', 'settlement', 5, 'READ|CREATE|UPDATE|DELETE'),
      (sysdate(), '', 1, sysdate(), 'Create Settlements', '', 'CREATE_SETTLEMENTS', '/create', 'settlement', 5, 'CREATE|UPDATE|DELETE'),
      (sysdate(), '', 1, sysdate(), 'Send Emails', '', 'SEND_EMAILS', '/send-emails', 'settlement', 5, 'CREATE|UPDATE|DELETE'),
      (sysdate(), '', 1, sysdate(), 'Generate Bank Sheet', '', 'BANK_SHEET', '/generate-bank-sheet', 'settlement', 5, 'CREATE|UPDATE|DELETE');

insert into sub_department(created_at, entity_id, is_active, updated_at, label, mdi_icon, name, path, permission,
                           type, pf_department_id)
VALUES (sysdate(), '', 1, sysdate(), 'Profile', '', 'PROFILE', '', 'READ|CREATE|UPDATE|DELETE', 'account', 8),
       (sysdate(), '', 1, sysdate(), 'Edit Profile', '', 'EDIT PROFILE', '/edit', 'UPDATE|DELETE', 'account', 8),
       (sysdate(), '', 1, sysdate(), 'Change Password', '', 'CHANGE PASSWORD', '/change-password', 'UPDATE|DELETE',
        'account', 8);

insert into sub_department(created_at, entity_id, is_active, updated_at, label, mdi_icon, name, path, permission,
                           type, pf_department_id)
VALUES (sysdate(), '', 1, sysdate(), 'Users', '', 'USERS', '', 'READ|CREATE|UPDATE|DELETE', 'user', 10),
       (sysdate(), '', 1, sysdate(), 'Create User', '', 'CREATE_USERS', '/create', 'CREATE|UPDATE|DELETE', 'user', 10);

insert into sub_department(created_at, entity_id, is_active, updated_at, label, mdi_icon, name, path, permission,
                           type, pf_department_id)
VALUES (sysdate(), '', 1, sysdate(), 'Details', '', 'TENANT_DETAILS', '/details', 'READ|CREATE|UPDATE|DELETE', 'tenant', 11),
       (sysdate(), '', 1, sysdate(), 'Images', '', 'TENANT_IMAGES', '/images', 'READ|CREATE|UPDATE|DELETE', 'tenant', 11),
       (sysdate(), '', 1, sysdate(), 'Email Designs', '', 'TENANT_EMAIL_DESIGNS', '/email-designs', 'READ|CREATE|UPDATE|DELETE', 'tenant', 11),
       (sysdate(), '', 1, sysdate(), 'PDF Designs', '', 'TENANT_PDF_DESIGNS', '/pdf-designs', 'READ|CREATE|UPDATE|DELETE', 'tenant', 11);

insert into sub_department(created_at, entity_id, is_active, updated_at, label, mdi_icon, name, path, permission,
                           type, pf_department_id)
VALUES(sysdate(), '', 1, sysdate(), 'Search Contributions', '', 'CONTRIBUTIONS', '', 'READ|CREATE|UPDATE|DELETE',
       'contribution', 2);

insert into sub_department(created_at, entity_id, is_active, updated_at, label, mdi_icon, name, path,
                                      permission, type, pf_department_id)
values (sysdate(), '', 1, sysdate(), 'For Loan', '', 'FOR_LOAN', '/loan', 'CREATE|UPDATE|DELETE', 'yearend', 13),
       (sysdate(), '', 1, sysdate(), 'For Settlement', '', 'FOR_SETTLEMENT', '/settlement', 'CREATE|UPDATE|DELETE', 'yearend', 13),
       (sysdate(), '', 0, sysdate(), 'For Yearly Process', '', 'FOR_YEAR_END', '/yearly', 'CREATE|UPDATE|DELETE', 'yearend', 13);

insert into pf_department_role_group(id, created_at, entity_id, is_active, updated_at, description, name)
VALUES (1, sysdate(), 'fa557a2e-2668-4d12-b53f-812be2bbd47c', 1, sysdate(), '', 'Department Head');

insert into pf_department_role_group(id, created_at, entity_id, is_active, updated_at, description, name)
VALUES (2, sysdate(), '6f6fbaab-e91e-4b8e-9566-d59a6bdd10c2', 1, sysdate(), '', 'Loans Head');

insert into pf_department_role_group(id, created_at, entity_id, is_active, updated_at, description, name)
VALUES (3, sysdate(), 'f88fda13-4761-4ce9-9e43-2fad276fc05a', 1, sysdate(), '', 'Settlements Head');

insert into pf_department_role(id, CREATED_AT, UPDATED_AT, NAME, PATH, PF_DEPARTMENT_ID, ROLE_ID, entity_id)
VALUES (1, sysdate(), sysdate(), 'ROLE_EMPLOYEE_ADMIN', '/employees', 1, 1, '792b88bd-3f2b-4ba0-ace0-31fa55dbeade'),
       (2, sysdate(), sysdate(), 'ROLE_CONTRIBUTION_ADMIN', '/contributions', 2, 1, '97a15d4d-200c-40c9-b695-016a0f6b4f9b'),
       (3, sysdate(), sysdate(), 'ROLE_LOAN_ADMIN', '/loans', 3, 1, 'f9387bd6-e03f-4a96-8884-555093cb972f'),
       (4, sysdate(), sysdate(), 'ROLE_TRANSFER_IN_ADMIN', '/transfer-ins', 4, 1, '8545969e-696a-48cd-a456-8bf7084c6a3a'),
       (5, sysdate(), sysdate(), 'ROLE_SETTLEMENT_ADMIN', '/settlements', 5, 1, '4e5ce6df-3a5d-4152-b40f-7e22c88a6eca'),
       (6, sysdate(), sysdate(), 'ROLE_TRANSFER_OUT_ADMIN', '/transfer-outs', 6, 1, 'ee39dc6e-1fc5-4b91-a365-5f81a98e7397'),
       (7, sysdate(), sysdate(), 'ROLE_BACK_OFFICE_ADMIN', '/back-office', 7, 1, '9ec02e42-b1da-4ae6-8f86-5960619a2916'),
       (8, sysdate(), sysdate(), 'ROLE_ACCOUNT_ADMIN', '/account', 8, 1, '5445902f-e81e-4943-895a-6bb738a7d897'),
       (9, sysdate(), sysdate(), 'ROLE_JOB_ADMIN', '/jobs', 9, 1, '6af1b3c9-5dd6-415a-8e9a-bdfd4c5871d9'),
       (10, sysdate(), sysdate(), 'ROLE_USER_ADMIN', '/users', 10, 1, 'd7cdfafd-e7a4-4a62-a5c5-eb2dea05b7e8'),
       (11, sysdate(), sysdate(), 'ROLE_DASHBOARD_VIEW', '/dashboard', 12, 3, '0fc92ced-998f-41d2-b8ab-a56957a02062'),
       (12, sysdate(), sysdate(), 'ROLE_YEAR_END_PROCESS_ADMIN', '/year-end-process', 13, 1, 'ae8a2f41-7c8c-4124-be5f-c18c110b4596'),
       (13, sysdate(), sysdate(), 'ROLE_ACTIVITY_LOGS_VIEW', '/logs', 14, 3, 'ceb2b858-0b68-4f63-8aa4-741938d412ca'),
       (14, sysdate(), sysdate(), 'ROLE_TENANT_ADMIN', '/tenants', 11, 1, '05ffa5f0-ecd1-4efc-91b1-ff4b8c73b175');

insert into pf_department_role_group_mapping(pf_department_role_id, pf_department_role_group_id)
VALUES (1, 1),(2, 1), (3, 1), (4, 1), (5, 1), (6, 1), (7, 1), (8, 1), (10, 1), (11, 1), (12, 1), (13, 1),
       (9, 1), (14, 1);

insert into pf_department_role_group_mapping(pf_department_role_id, pf_department_role_group_id)
VALUES (1, 2),(2, 2),(3, 2),(11, 2),(8, 2);

insert into pf_department_role_group_mapping(pf_department_role_id, pf_department_role_group_id)
VALUES (1, 3),(2, 3),(5, 3), (6, 3),(11, 3),(8, 3);

insert into user(id, created_at, updated_at, email, first_name, is_account_non_expired, is_account_non_locked,
                 is_credentials_non_expired, is_default_password, is_enabled, last_name, password, username,
                 profile_image, entity_id)
values (1, sysdate(), sysdate(), 'shaikhrayeesahmed@gmail.com', 'Rayees Ahmed', 1, 1, 1, 1, 1, 'Shaikh',
        '$2a$10$wNmrMOXpCobgnQbqVyeDwee76yZEPEdU8/.Hg6lUWmdzdh822J5lG', 'shaikhrayeesahmed',
        'https://cdn.vuetifyjs.com/images/lists/ali.png', '53adf409-2ba8-4546-8e65-eb41dd47aa01');

insert into gender(created_at, entity_id, is_active, updated_at, details, symbol, created_by, updated_by)
VALUES (sysdate(), '', 1, sysdate(), 'Male', 'M', 1, 1),
       (sysdate(), '', 1, sysdate(), 'Female', 'F', 1, 1);

insert into contribution_status(created_at, entity_id, is_active, updated_at, description, symbol, created_by,
                                updated_by)
values (sysdate(), '', 1, sysdate(), 'Active', 'A', 1, 1),
       (sysdate(), '', 1, sysdate(), 'InActive', 'I', 1, 1),
       (sysdate(), '', 1, sysdate(), 'Settled', 'S', 1, 1),
       (sysdate(), '', 1, sysdate(), 'Merged', 'M', 1, 1),
       (sysdate(), '', 1, sysdate(), 'InOperable', 'IO', 1, 1),
       (sysdate(), '', 1, sysdate(), 'F', 'F', 1, 1),
       (sysdate(), '', 1, sysdate(), 'c', 'c', 1, 1),
       (sysdate(), '', 1, sysdate(), 'No Status Received', 'NA', 1, 1);

-- departments
insert into department(id, created_at, updated_at, title, is_active)
VALUES (1, sysdate(), sysdate(), 'NA', 1);

insert into department(id, created_at, updated_at, title, is_active)
VALUES (2, sysdate(), sysdate(), 'Plant Head\'s Office', 1);

insert into department(id, created_at, updated_at, title, is_active)
VALUES (3, sysdate(), sysdate(), 'Capital Purchase', 1);

insert into department(id, created_at, updated_at, title, is_active)
VALUES (4, sysdate(), sysdate(), 'CPPC', 1);

insert into department(id, created_at, updated_at, title, is_active)
VALUES (5, sysdate(), sysdate(), 'Product Cost Management', 1);

insert into department(id, created_at, updated_at, title, is_active)
VALUES (6, sysdate(), sysdate(), 'Product Development Quality', 1);

insert into department(id, created_at, updated_at, title, is_active)
VALUES (7, sysdate(), sysdate(), 'PTD CME', 1);

insert into department(id, created_at, updated_at, title, is_active)
VALUES (8, sysdate(), sysdate(), 'Spares Sourcing', 1);

insert into sapstatus(id, created_at, updated_at, description, status, is_active)
VALUES (1, sysdate(), sysdate(), 'Not Ready', 1, 1);

insert into sapstatus(id, created_at, updated_at, description, status, is_active)
VALUES (2, sysdate(), sysdate(), 'Ready', 2, 1);

insert into sapstatus(id, created_at, updated_at, description, status, is_active)
VALUES (3, sysdate(), sysdate(), 'Pushed', 3, 1);

insert into financial_year(created_at, entity_id, is_active, updated_at, is_current, year, created_by, updated_by)
VALUES (sysdate(), '', 0, sysdate(), 0, 2010, 1, 1),
       (sysdate(), '', 0, sysdate(), 0, 2011, 1, 1),
       (sysdate(), '', 0, sysdate(), 0, 2012, 1, 1),
       (sysdate(), '', 0, sysdate(), 0, 2013, 1, 1),
       (sysdate(), '', 0, sysdate(), 0, 2014, 1, 1),
       (sysdate(), '', 0, sysdate(), 0, 2015, 1, 1),
       (sysdate(), '', 0, sysdate(), 0, 2016, 1, 1),
       (sysdate(), '', 0, sysdate(), 0, 2017, 1, 1),
       (sysdate(), '', 0, sysdate(), 0, 2018, 1, 1),
       (sysdate(), '', 1, sysdate(), 0, 2019, 1, 1),
       (sysdate(), '', 1, sysdate(), 0, 2020, 1, 1),
       (sysdate(), '', 1, sysdate(), 0, 2021, 1, 1),
       (sysdate(), '', 1, sysdate(), 0, 2022, 1, 1),
       (sysdate(), '', 1, sysdate(), 1, 2023, 1, 1);

insert into financial_month(created_at, entity_id, is_active, updated_at, is_current, label, financial_month,
                            calender_month, created_by, updated_by)
VALUES (sysdate(), '', 1, sysdate(), 0, 'OBL', 0, 0, 1, 1),
       (sysdate(), '', 1, sysdate(), 0, 'Apr', 1, 4, 1, 1),
       (sysdate(), '', 1, sysdate(), 0, 'May', 2, 5, 1, 1),
       (sysdate(), '', 1, sysdate(), 0, 'Jun', 3, 6, 1, 1),
       (sysdate(), '', 1, sysdate(), 0, 'Jul', 4, 7, 1, 1),
       (sysdate(), '', 1, sysdate(), 0, 'Aug', 5, 8, 1, 1),
       (sysdate(), '', 1, sysdate(), 0, 'Sep', 6, 9, 1, 1),
       (sysdate(), '', 1, sysdate(), 0, 'Oct', 7, 10, 1, 1),
       (sysdate(), '', 1, sysdate(), 1, 'Nov', 8, 11, 1, 1),
       (sysdate(), '', 1, sysdate(), 0, 'Dec', 9, 12, 1, 1),
       (sysdate(), '', 1, sysdate(), 0, 'Jan', 10, 1, 1, 1),
       (sysdate(), '', 1, sysdate(), 0, 'Feb', 11, 2, 1, 1),
       (sysdate(), '', 1, sysdate(), 0, 'Mar', 12, 3, 1, 1);


-- Creat Mahindra PF Trust Tenant

-- 1. Create Tenant
insert into tenant(id, entity_id, created_at, updated_at, name, code, description, tenant_id, theme, clock,
                   logo_image, height, width,
                   style_classes)
values (2, 'f0c61237-c594-4931-aec5-37343f053ed0', sysdate(), sysdate(), 'Mahindra PF Trust', 'MAHINDRA_PF_TRUST', 'Mahindra PF Trust', '6dd282f3-7d17-4501-8711-63f8e6d01fc0',
        '{
                "primary": "#dc3545",
                "secondary": "#3f51b5",
                "accent": "#4caf50",
                "error": "#f44336",
                "warning": "#ff9800",
                "info": "#ffeb3b",
                "success": "#8bc34a"
            }', 'https://free.timeanddate.com/clock/i8easafx/n54/fs18/fcfff/tcdc3545/tt0/tm1',
        'data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAVkAAABaCAYAAAASESXBAAAACXBIWXMAAAsTAAALEwEAmpwYAAAKT2lDQ1BQaG90b3Nob3AgSUNDIHByb2ZpbGUAAHjanVNnVFPpFj333vRCS4iAlEtvUhUIIFJCi4AUkSYqIQkQSoghodkVUcERRUUEG8igiAOOjoCMFVEsDIoK2AfkIaKOg6OIisr74Xuja9a89+bN/rXXPues852zzwfACAyWSDNRNYAMqUIeEeCDx8TG4eQuQIEKJHAAEAizZCFz/SMBAPh+PDwrIsAHvgABeNMLCADATZvAMByH/w/qQplcAYCEAcB0kThLCIAUAEB6jkKmAEBGAYCdmCZTAKAEAGDLY2LjAFAtAGAnf+bTAICd+Jl7AQBblCEVAaCRACATZYhEAGg7AKzPVopFAFgwABRmS8Q5ANgtADBJV2ZIALC3AMDOEAuyAAgMADBRiIUpAAR7AGDIIyN4AISZABRG8lc88SuuEOcqAAB4mbI8uSQ5RYFbCC1xB1dXLh4ozkkXKxQ2YQJhmkAuwnmZGTKBNA/g88wAAKCRFRHgg/P9eM4Ors7ONo62Dl8t6r8G/yJiYuP+5c+rcEAAAOF0ftH+LC+zGoA7BoBt/qIl7gRoXgugdfeLZrIPQLUAoOnaV/Nw+H48PEWhkLnZ2eXk5NhKxEJbYcpXff5nwl/AV/1s+X48/Pf14L7iJIEyXYFHBPjgwsz0TKUcz5IJhGLc5o9H/LcL//wd0yLESWK5WCoU41EScY5EmozzMqUiiUKSKcUl0v9k4t8s+wM+3zUAsGo+AXuRLahdYwP2SycQWHTA4vcAAPK7b8HUKAgDgGiD4c93/+8//UegJQCAZkmScQAAXkQkLlTKsz/HCAAARKCBKrBBG/TBGCzABhzBBdzBC/xgNoRCJMTCQhBCCmSAHHJgKayCQiiGzbAdKmAv1EAdNMBRaIaTcA4uwlW4Dj1wD/phCJ7BKLyBCQRByAgTYSHaiAFiilgjjggXmYX4IcFIBBKLJCDJiBRRIkuRNUgxUopUIFVIHfI9cgI5h1xGupE7yAAygvyGvEcxlIGyUT3UDLVDuag3GoRGogvQZHQxmo8WoJvQcrQaPYw2oefQq2gP2o8+Q8cwwOgYBzPEbDAuxsNCsTgsCZNjy7EirAyrxhqwVqwDu4n1Y8+xdwQSgUXACTYEd0IgYR5BSFhMWE7YSKggHCQ0EdoJNwkDhFHCJyKTqEu0JroR+cQYYjIxh1hILCPWEo8TLxB7iEPENyQSiUMyJ7mQAkmxpFTSEtJG0m5SI+ksqZs0SBojk8naZGuyBzmULCAryIXkneTD5DPkG+Qh8lsKnWJAcaT4U+IoUspqShnlEOU05QZlmDJBVaOaUt2ooVQRNY9aQq2htlKvUYeoEzR1mjnNgxZJS6WtopXTGmgXaPdpr+h0uhHdlR5Ol9BX0svpR+iX6AP0dwwNhhWDx4hnKBmbGAcYZxl3GK+YTKYZ04sZx1QwNzHrmOeZD5lvVVgqtip8FZHKCpVKlSaVGyovVKmqpqreqgtV81XLVI+pXlN9rkZVM1PjqQnUlqtVqp1Q61MbU2epO6iHqmeob1Q/pH5Z/YkGWcNMw09DpFGgsV/jvMYgC2MZs3gsIWsNq4Z1gTXEJrHN2Xx2KruY/R27iz2qqaE5QzNKM1ezUvOUZj8H45hx+Jx0TgnnKKeX836K3hTvKeIpG6Y0TLkxZVxrqpaXllirSKtRq0frvTau7aedpr1Fu1n7gQ5Bx0onXCdHZ4/OBZ3nU9lT3acKpxZNPTr1ri6qa6UbobtEd79up+6Ynr5egJ5Mb6feeb3n+hx9L/1U/W36p/VHDFgGswwkBtsMzhg8xTVxbzwdL8fb8VFDXcNAQ6VhlWGX4YSRudE8o9VGjUYPjGnGXOMk423GbcajJgYmISZLTepN7ppSTbmmKaY7TDtMx83MzaLN1pk1mz0x1zLnm+eb15vft2BaeFostqi2uGVJsuRaplnutrxuhVo5WaVYVVpds0atna0l1rutu6cRp7lOk06rntZnw7Dxtsm2qbcZsOXYBtuutm22fWFnYhdnt8Wuw+6TvZN9un2N/T0HDYfZDqsdWh1+c7RyFDpWOt6azpzuP33F9JbpL2dYzxDP2DPjthPLKcRpnVOb00dnF2e5c4PziIuJS4LLLpc+Lpsbxt3IveRKdPVxXeF60vWdm7Obwu2o26/uNu5p7ofcn8w0nymeWTNz0MPIQ+BR5dE/C5+VMGvfrH5PQ0+BZ7XnIy9jL5FXrdewt6V3qvdh7xc+9j5yn+M+4zw33jLeWV/MN8C3yLfLT8Nvnl+F30N/I/9k/3r/0QCngCUBZwOJgUGBWwL7+Hp8Ib+OPzrbZfay2e1BjKC5QRVBj4KtguXBrSFoyOyQrSH355jOkc5pDoVQfujW0Adh5mGLw34MJ4WHhVeGP45wiFga0TGXNXfR3ENz30T6RJZE3ptnMU85ry1KNSo+qi5qPNo3ujS6P8YuZlnM1VidWElsSxw5LiquNm5svt/87fOH4p3iC+N7F5gvyF1weaHOwvSFpxapLhIsOpZATIhOOJTwQRAqqBaMJfITdyWOCnnCHcJnIi/RNtGI2ENcKh5O8kgqTXqS7JG8NXkkxTOlLOW5hCepkLxMDUzdmzqeFpp2IG0yPTq9MYOSkZBxQqohTZO2Z+pn5mZ2y6xlhbL+xW6Lty8elQfJa7OQrAVZLQq2QqboVFoo1yoHsmdlV2a/zYnKOZarnivN7cyzytuQN5zvn//tEsIS4ZK2pYZLVy0dWOa9rGo5sjxxedsK4xUFK4ZWBqw8uIq2Km3VT6vtV5eufr0mek1rgV7ByoLBtQFr6wtVCuWFfevc1+1dT1gvWd+1YfqGnRs+FYmKrhTbF5cVf9go3HjlG4dvyr+Z3JS0qavEuWTPZtJm6ebeLZ5bDpaql+aXDm4N2dq0Dd9WtO319kXbL5fNKNu7g7ZDuaO/PLi8ZafJzs07P1SkVPRU+lQ27tLdtWHX+G7R7ht7vPY07NXbW7z3/T7JvttVAVVN1WbVZftJ+7P3P66Jqun4lvttXa1ObXHtxwPSA/0HIw6217nU1R3SPVRSj9Yr60cOxx++/p3vdy0NNg1VjZzG4iNwRHnk6fcJ3/ceDTradox7rOEH0x92HWcdL2pCmvKaRptTmvtbYlu6T8w+0dbq3nr8R9sfD5w0PFl5SvNUyWna6YLTk2fyz4ydlZ19fi753GDborZ752PO32oPb++6EHTh0kX/i+c7vDvOXPK4dPKy2+UTV7hXmq86X23qdOo8/pPTT8e7nLuarrlca7nuer21e2b36RueN87d9L158Rb/1tWeOT3dvfN6b/fF9/XfFt1+cif9zsu72Xcn7q28T7xf9EDtQdlD3YfVP1v+3Njv3H9qwHeg89HcR/cGhYPP/pH1jw9DBY+Zj8uGDYbrnjg+OTniP3L96fynQ89kzyaeF/6i/suuFxYvfvjV69fO0ZjRoZfyl5O/bXyl/erA6xmv28bCxh6+yXgzMV70VvvtwXfcdx3vo98PT+R8IH8o/2j5sfVT0Kf7kxmTk/8EA5jz/GMzLdsAAAAgY0hSTQAAeiUAAICDAAD5/wAAgOkAAHUwAADqYAAAOpgAABdvkl/FRgAAENtJREFUeNrsnXu0HVV9xz/n3tx7k5gnCQlJCG+zMEAjhRUemgrSAFIVLIUEgahQBISyrI1IWgQqKFARVKzgAxWQli7Raq1SKSKoGCjlGSCBEBOKCAkhb/K4N/ee/vHbZ2XOvufM45yZOY/7/ax11p25s2fPnj0z39mz9+/324VisfgcsBuwk9anAHQAa4ClwH8A/0r7837gNqAPKIak6wG+D3yqScp9KvAdoDciXY+7lmch6uEq4CJge8Qz1A1cDnxTVVY/w4CZbXheU4BZwHzgY8B5wMttfB3HA5Nipt23ico9FhgTM+1eelzrZjowMWbayaqudOgANrT5Oc4FngYmtPE5bk+QdoPKPWTZlCDtW6qu9ER2KDAWeFCXWwghkc2Og4GP6JILISSy2fFJXXIhhEQ2O96JWVIIIYRENiPepcvekuyhKhAS2dZgki57S/KAqkC0IsOG4DkXddlbjguBW1UNot1Ftg94DhjAvEKihKwDmAGMrKFczwPbQlraA0AXcEiMslTaV7QGLwLzgKdUFWpgDAWRXQscmjD/KZh96owE+5wF3BUz7f7AncBRupRtx7eAT9Ae7t7NQqvXZaEVXxRZdxe8BnwO85ePw8MJBBZgBXC8O86oDM/jRMxzbFrGN2on5gX1EvAocH+O98LHgSOBEUB/DeVei8WL+Bn1uTD3AZdU6B7YHVhXpWyTgX+s8SEsuvJ31HHNtgDLgHuB39eYz1gslsOBmOtrMWVBKZCNC/1JwDHYwGTaZS7R457xhRXyPxM4GvPozPrZ3OS+rh4EHs9CZAvuE70vYeFWJUj7TA0nv8Xd3KdlVLnXA5c26CV4r7uJ1md8nK84YUuDLwOLgC/VsO9S4GRguff/rwOHu5cAVUT2/Cb5HL8CuCbhfme7azA+w7I9B/w8pA5r4W6sOycPLmFwV99PseBIjeAbwAVxEuZhXTAyo7RBNmZU9j9voMACvM+9eEZkeIzDUxRY3Iv4BvdyiqLXu2lnegK7H2ZVcKF7uVfrT9/RRJ+zVwP/nGCfvwXuyFhgcV98T6aY3+U5Cuy/ATd7/7usgQKLe6nHsnjpyOnGyyJtkO6Myn5HEzy4ewI/zjD/rPK+FHhvRJoD3d+PVGgVLHAt22Pd+hpah08Ap8RIdxBwYw7lWQ78EXMtT4MJ7mWSF+dVOP61TXCdj8W6qRousq3KddjAXTNwfAzBqoVFWD9zVnwnYvsj7ryCL7PxWL/87d7Ls7PF7p9vx3i+rsupLKWYHaNTyu+dOdbjR4HNOTUMauEKIsJCSmSrtx4/02RlOjulfEpitTvwhYzLvDfhgy33A7/yukeWAB9ug3toAnBYxD2Wx+fub4DFbrk/pTzfllMdPu5etkHmAu9usmt9hkQ2Obc3YZnenlI+W93fH+VU7tkx030VG5iZ1kb30TEh296TUxkuySDPvGzN51XQq7ua8DqH3uND0eMrio9l9GmeRssoDZZg9s55tQb2jJHmfuC4NryXwj4jR+Zw/HvIxpEjD1vVyzATzSC3ui+wZqNbLdn4TCW6H7FRFFLK5wTM9CYvoh7Iz7epwEK43WaWrcGNwE1kZ9ZYD1FdFiswiwvfOuUIBg+ANQtFtWTj0wfMwexSixneZJPcp9BFDfhEG+1a6xsSlnmEawHfhBnOp1HuieRvIrcS+IsEL67Suc/CLAF2S+nhq8Vp4jPAQ5ht+LAqL+IOd44bmui56nfdFg+6cnWFNCJWVcljDWbjuzljoZyOWYecLJHNhjfcL2tewAYkdpD/zLFLgd/VuO9T2EDVMswLp16ObcA9+Jarg1rO/QF37UY04N48BZuxtxWZB/wwhZfjypyej/swR6AT08hQ3QWNZRG7BqLyol6BWEV6gw+NEKuuOvZ9hfgu4mmyrIUF9tUUBLYRXJ5WRhLZxtKLGYm3Gml5DvUP4XNP+mJrVZ5s0XK/mlZG6i6ozlSs/62TdPpnX6vSFVFowboZnlI+xSF87nm1vhtNFg25fbCxhXqfnQI2ZvAig12zh7n7s+7nUyJbmRuBi1O+uTdjU98saQORHcpfQI0494LqCzDzra8Bp6dcxhWY9cKbXrkLeliy4T7MhCTt1sNomtOkRohW+YJ4LAOBBYtLfWJWBZfIljPX/bKiT1UsRE1cjLlpt5wWSmTLOT3j/He22aegEHlxTs7PZmrPpUS2nKmqAiGajm5aOKaFRDa6pZkmE1TFQtSkU1l3tY3JKmNZF5QT13V1DYOtBOLwuKpYiMQUEzybS6nN9ny5RLZ52IRFmH9DVSFEU7GcbCaLVHdBzuQV36CZ0WBd/i05Ec3KZrzHJbJq/Yvmp1dVEIueZiyURDY5accB7VeViggOSimfN9u8ntIcuE6trtQqq63OxjtxrGdyv373kutSlYoI9gJ+gU042Vfjp2wROKrN62kE5lnZWefn/k5sOnqJbIPYEwt5N5CSyI5UlYoYHO9+ojpHYNGz6o070J+mNkpkk1Mgv9k6Rfuirrr06SS9ac91oYWQyApd6NZ8EwqRB6tUBYm+Hlv22ZTISmRFY/glsFbVEIsBmtQ8SyKbHLm9irzoJ/9JNFuVXmBxK4vsuJhpx9Z4jCQmSrUOKCXZrztk2800r93qmBrPyScNa4bhKaVNUu5RKX2BjMnx3KMmirwTuLVJ77fhKT3To1Iqz8Im1tFQ/RmGuYiOJ9x1r0Dtxrm9mN1ZlGtgAYsLUAubEhxje8j2N7ApYq7DbArTmt+rXgrAupDt2xOcfxrz1m9NcLytKZV7Y8j2ncSL0hRVj2meewewJUZ+FwIPA+diJkhdTXDPRV23JM/0hpTK9DQ2tfgiLD5BRxM9m6G6VSgWi5NjFLjgWnhraihEDzYhYZyb8q2Ihymslf02or2xOoD1wLYYee7ubviBJrmQfVTvwxvu6nggwzr239xjYx5vU4jYJCn3NnftqjUWJrp6KtZRj2mf++aEL7UJ7nlp9D3X4e6Rt1J4psOuW63E0aw862pr2MukUCwq9oQQQmSpwkIIISSyQgghkRVCCCGRFUIIiawQQkhkhRBCSGSFEEIiK4QY0hSAy4H/Ar6MOZi0PAraLYQIchxwKTCNeF5VJS+7HZhn12LgGeAn7n9x6cYik73brZ+AeZUtaPk3hzy+hBCOkVR3pU3Ka1gMkK/GTP+nDI6C9xgwW90FQoh2oQtYjcVb2E7liHSlVtkAFihma5XW7hTgK8BdMY9dSdwXt0OlSmSFECU2YjPjTgOmAx/ytn8D2BvYx/2djk0sug9wEnATg2d8+LBr0UbxAnCNE9sdwE+Bz7ZDpaq7QAhRjaOA3wXWTwd+EKM1fC3wd97/9wb+L8Yxd8NCjL7RLpWolqwQohoHeetxQp32YQG2f+b9/+9jHnNdOwmsRFYIEcYEbz1JXNgFlAdSP36oVqJMuIQQ1TjAW08S7H0d8N9YXy2Y5UIn1ad36sSCuPcTPntJGPtj1giT3fH6XJn/CPya5DOv7IdZPeyBTVtUxIJzr8RseSWyQoi6ODiwvIXkM6P83tOaLk9kJ2H9t7OxKbBGuu2vA38NPBrzOMcBVwJzQtKsB76N2QBHcbJLd3RImpXA54HbJLJCiFroBmZ4orItYR6FwHKv+5UYASzFBrp8JrruhjgiextwTox044FPY10g51ZJMw34LjA3Rn77OtHew4mtRFYIkYjJngC+UEMePYHlbZTPXTbMy7+0rTROFGfi1p8AH/T+9zDwqmsRjwMOdIJYYn4VkZ2BWVIE+6FfcV0ez2ODcVOAWcAZgTTXAP/u0khkhRCx8VuYtYjszMDyCm/bZszOdhxmFzuOcueDVyPyvsgT2G8CXwReqpD2EODjwMVU7kvdDXiC8qm9F2LOFDsrpL8ZeIhdU6Ofy2CTNYmsECIU37JgdcL9p1Pep3lnhTR/cL9SF0FnYFvUjMLXBJZ/BJwfknYJ8DeuK2B5he33eAI7B/htSH6LMceMi936gWEFlQmXECKqFQohU15X4R8Cy/1EOzHMqiDA1ZjjWr5gFgNnxyzTEwyeon0+cGxg/YwIgS3x2yrdIhJZIUQs9vXW1yXYd7bXslxEtFlW0PFhR0T3RLCFvBKLn1ArVweWfwncHXO/YP9yr0RWCJGUYJ9sEeuDjMMC4JHA+o+xvtIo9vO6JjbE1K2xdZzjYZTbAi9KsO+hgeXQQTr1yQohKjHBa1n+CTbCvhEL4rINM9EajZkxHQbMAz4Q2O8e4LQaRD2qa+LFwPI+rvvgNzWc4we9FvFjMffrAi4IrN8rkRVCJCXYshyOmUaVBHcb1rfZiXlCjfL23QpcQgxD/QCTAstRsQt+4a3/p+tCeC7hOf5VYPlzMfc5BPgXzO4WbIDubomsECIJYzAX1Ur0uN+4Ctv+17Xqvo55bcWlQPkI/fMR6bc4YZsfKO9TwPXALUSbf4HZvAYH9+YDU51obmNXPN1OzPJgGvBe9wtyAuX9s4NPTqEOhRAe+1Nub7rMidcsbEBsuBOWvTBTrRKzsKlnkjLJiXLJQ+xTWGzaMEZhbru7e//fgQUKvzGiZTub+G67lXgZ63/+dVRCDXwJIXz8waT/waaROde15I7G5uKaRbmr7Q01Hm8i5S64cSwZtmD9xPdXaGmfAzwL/BCLiVuJqYHlPiyITNScZKsxC4QLgLfHEVh1FwghqolekJVV0q0Hvgdc6NbnAu/AYhIkwXd8eDPmfq+7Y57nWr++U8Bfut/VwBXetmmB5TWu9T7W5THB/bpdi321q4PniTDXksgKIeIww1sPi751U0BkcWJ2Rp3HW5Fw/2+530nuE36et/2zmBVCcObb0YHl0my7a0geaSwSdRcIIXz8FuGGkLTLsQGvEvMrtEyjCNqcrqXcRCsJP3fHP5LBMzOcza7pxv0GZg/lLr0SWSFEpvjBuqPiFiz01q9PeLzgKP+zVA/sHZdHgfdjoQ2DBKNvBQN4j2fwAJpEVgiRGRMrtFbDeIjyfthzKA+4EkVQ4F5P8TxuoNxRIBgf4WWvVfsOiawQIi+C/ZV/IN4sszcGlguU99NGEfT2WpfyudweWA52CfjndKhEVgiRB52UOxrE7R+9g/KJExcSb2B9EuXeXlEhDocnPJ8eryuixEtet8QnU67HbomsEKLap3tw4CruaHuv15qdTPVpXoIc4IlxmGXBqZgp1ZUJzicYY+CBwPJmzD22xHSSBYihyktkpMv3FWAVcJlEVggRZD/vszrJNOBf8NY/HWMf3xKhGNLCvhMLRnMV8H0Gx6D1uZJdzggDmHNCkBsqlP+CGGWeg5mMrQKOCfy/C/gVZsI2CdgbuFZ2skKIIHt462sT7LvJCdmpbn1/bDaCHmww7UwGB38Z4a1fj7m8Dsc8yx4DPurEN5j2TPd7HAsY86B7IZQGsc6j3NvrIgaboj2Dzc/1ocD/bsFi4d6NeboNYIN4M4F3YV5mweA5B7ljgwWPme238BW7QAjhf17fElg/C4sFEJcjKZ+rK8hx3ic72IDTEyH5rWfXwNhVCbsKSlTy+CrRA9wH/FkN+a4AjmCXh9phlNsMA+qTFUKU44+6v5xw/0dcC9DnKSoHZHmS6gHBe10rloDILnB5xWEJ5pxwRUiaHcB7sOm9k/AD4HDKXYCfZfDUNbeoJSuE8DndfQa/jplAJZ3eZTTwT+4ze6NrqX43Yp/z3ad4l+t2WIbNLFttrq+ZwMFun1Fuvy4sROErWPCWpFG2DgBOca3rKewKlLPRlWk18LTrHggLx3gaFoDmUeCR/x8A/YA3HjZnLYUAAAAASUVORK5CYII=',
        '43px', '162px', 'ml-10');

-- 2. Assign Tenant to User
insert into user_tenant_mapping(created_at, is_active, updated_at, tenant_id, user_id)
values (sysdate(), 1, sysdate(), 2, 1);

-- 3. Assign role group to User
insert into user_tenant_pf_department_role_mapping
(created_at, is_active, updated_at, pf_department_role_group_id, tenant_id, user_id)
VALUES (sysdate(), 1, sysdate(), 1, 2, 1);

-- 4. Create unit codes
insert into unit_code(created_at, entity_id, is_active, updated_at, unit_code, tenant_id)
VALUES (sysdate(), 'e673b8e6-f8cf-499f-b92e-a62bb40e3b0b', 1, sysdate(), '899000', 2),
       (sysdate(), 'c20211f3-2c39-4c6c-8d1c-e6b658bf309b', 1, sysdate(), '899001', 2),
       (sysdate(), '8162f90f-bbf5-44b5-8b24-7c8030d3290a', 1, sysdate(), '899005', 2),
       (sysdate(), 'e1814eab-a3a6-45f9-8532-4cc3f2804fa8', 1, sysdate(), '899010', 2),
       (sysdate(), '4e4e1c70-d2fb-4f8d-a079-369a715005bc', 1, sysdate(), '899011', 2),
       (sysdate(), 'f94d6815-e528-46f8-8dcd-2835e55779a6', 1, sysdate(), '899012', 2),
       (sysdate(), '5c0daf0e-b584-42ad-b527-6d8aed16a797', 1, sysdate(), '899013', 2),
       (sysdate(), '51fad500-12ce-4efa-9e38-eb20f38f3756', 1, sysdate(), '899022', 2),
       (sysdate(), '2ce2828e-f1f5-4f48-b880-d7f77133cff2', 1, sysdate(), '899023', 2),
       (sysdate(), 'caef0108-3e4f-4767-9901-647e6f96fa40', 1, sysdate(), '899024', 2),
       (sysdate(), '2317596f-b40a-4775-8701-b780c1a5084c', 1, sysdate(), '899021', 2),
       (sysdate(), '2ce77544-0e97-490c-a5d4-e1a39253a29f', 1, sysdate(), '899018', 2),
       (sysdate(), '1a899d37-07e6-4268-baad-2f3569e72b4f', 1, sysdate(), '899019', 2),
       (sysdate(), '88e50d31-4080-4b1e-a5a9-1b02128d1f45', 1, sysdate(), '899020', 2),
       (sysdate(), 'a20cb75f-ceec-4623-a709-bc1617f77aaa', 1, sysdate(), '899007', 2),
       (sysdate(), '55120569-5ee4-440e-99c4-fcf87dabdd02', 1, sysdate(), '899008', 2),
       (sysdate(), '8088f6b6-94a1-4fe8-b5ff-ef76e6cd7586', 1, sysdate(), '899014', 2),
       (sysdate(), '13023407-f97b-44d8-b38a-043b16515c1c', 1, sysdate(), '899015', 2),
       (sysdate(), '58d5bf5a-a1c8-4525-8d1f-31249bbaa537', 1, sysdate(), '899025', 2),
       (sysdate(), 'a50675c8-6aa3-4b8d-a701-38fbff402bcd', 1, sysdate(), '899026', 2),
       (sysdate(), '2468dce4-59da-4e74-9c92-5061bd40ce46', 1, sysdate(), '899027', 2),
       (sysdate(), 'b4bc9d87-cfc2-4201-85a7-276f69167f73', 1, sysdate(), '899028', 2),
       (sysdate(), '546edeb7-730a-4975-985b-0a3d320f888d', 1, sysdate(), '899110', 2),
       (sysdate(), 'bcebd946-13c6-4b4e-8814-079037ab8c82', 1, sysdate(), '899029', 2),
       (sysdate(), 'd862afdb-1727-4a83-9a5f-fc3440093b97', 1, sysdate(), '899030', 2),
       (sysdate(), '2f1d84ab-0783-4dc7-938f-5baa9de5bed4', 1, sysdate(), '899031', 2),
       (sysdate(), '62e0a08f-ba93-4ac6-87cb-6ab78d4692e2', 1, sysdate(), '899055', 2),
       (sysdate(), 'd13e5b2c-ff18-47a5-a36f-10d480f58057', 1, sysdate(), '899105', 2),
       (sysdate(), 'fb91e507-d7b2-4eec-a1c8-88772492b701', 1, sysdate(), '899006', 2),
       (sysdate(), 'ec459577-a08a-4bd2-b9e4-dcacd1f4431e', 1, sysdate(), '899009', 2),
       (sysdate(), 'eecab90a-4e97-46ac-9af4-bec199da84c5', 1, sysdate(), '899016', 2),
       (sysdate(), '04c02645-44d0-44fb-a78a-c1c2e260a55c', 1, sysdate(), '899032', 2),
       (sysdate(), '4812543b-cf82-4262-b917-acf1ed4fe891', 1, sysdate(), '899017', 2),
       (sysdate(), '3659bf3f-4968-455a-b4f5-af6022fc810d', 1, sysdate(), '899902', 2),
       (sysdate(), 'd4d6885b-0b4a-4509-bd21-aa79e17521b0', 1, sysdate(), '899901', 2),
       (sysdate(), '48e1c8bc-eb9a-463a-b036-2a938b8d950f', 1, sysdate(), '899122', 2),
       (sysdate(), '9df80b8f-c11e-4c82-a5ed-dea84a3c604f', 1, sysdate(), '899132', 2),
       (sysdate(), 'afe12766-41e7-458f-a12d-8b03e20fba5e', 1, sysdate(), '899133', 2);

-- 5. Create documents
insert into document(id, created_at, entity_id, is_active, updated_at, name, created_by, updated_by, tenant_id)
VALUES(41, sysdate(), '7aae194a-eedd-4719-85ea-b530194b11fc', 1, sysdate(), 'Death Certificate', 1, 1, 2),
      (42, sysdate(), '6852f6eb-d6fe-4c32-835c-f8c5d4b374bf', 1, sysdate(), 'Pan Card', 1, 1, 2),
      (43, sysdate(), 'ea49b030-1f19-42da-9843-fa80d3061e5b', 1, sysdate(), 'Cancelled Cheque', 1, 1, 2),
      (44, sysdate(), '40dbb744-417e-4471-bd2d-bea9623506a0', 1, sysdate(), 'Bank Passbook', 1, 1, 2),
      (45, sysdate(), '7e527d5d-9560-47ae-8fa8-69ccc13ca7cf', 1, sysdate(), 'Form 20', 1, 1, 2),
      (46, sysdate(), '3ed5cde2-058c-4f25-b510-6d2e8950a4c7', 1, sysdate(), 'Form 19', 1, 1, 2),
      (47, sysdate(), 'e52f7c0c-28d2-4846-a043-b68cf5fa373f', 1, sysdate(), 'Cover Letter', 1, 1, 2),
      (48, sysdate(), '17f1acb0-186d-420c-8e6b-6d11091f6ec3', 1, sysdate(), 'Annexure K', 1, 1, 2),
      (49, sysdate(), '65c8c41b-61cd-48a1-bb81-bc39bdf1042f', 1, sysdate(), 'Form 13', 1, 1, 2),
      (50, sysdate(), 'c098c550-261e-416d-b42b-2849fbed1270', 1, sysdate(), 'Form 16', 1, 1, 2),
      (51, sysdate(), '7b798087-283d-4c61-858d-bcc219bd5b5f', 1, sysdate(), 'Pay Slip', 1, 1, 2),
      (52, sysdate(), 'ac34d61a-0017-4ad6-bd3a-533ecc8061fa', 1, sysdate(), 'Sale Agreemen', 1, 1, 2),
      (53, sysdate(), '1c3b9cce-1ffd-4727-85ad-f974486d11b4', 1, sysdate(), 'Stamp Duty/Registration Receipt', 1, 1, 2),
      (54, sysdate(), '96a3f608-234a-4110-a10c-3a385bc5267f', 1, sysdate(), 'Builders Receipt', 1, 1, 2),
      (55, sysdate(), 'e588799f-38c1-4497-a549-87010ad68282', 1, sysdate(), 'NOC from Society', 1, 1, 2),
      (56, sysdate(), 'b67c7e74-fdab-4884-b310-7779df2d17d0', 1, sysdate(), 'Estimate of Construction', 1, 1, 2),
      (57, sysdate(), '9bc971eb-66d4-49b2-8cd0-73bf49c0f48b', 1, sysdate(), 'Financial Institute Certificate', 1, 1, 2),
      (58, sysdate(), '3b5448e1-63b2-46fc-95dd-5c0dc88cf394', 1, sysdate(), 'Land Purchase Document', 1, 1, 2),
      (59, sysdate(), '4947e757-39a8-4680-be46-200931ec9469', 1, sysdate(), '7 X 12 Extract', 1, 1, 2),
      (60, sysdate(), '8739d6a7-597d-46e4-9c57-5e4febcb8f3b', 1, sysdate(), 'NA Certificate', 1, 1, 2),
      (61, sysdate(), '09c23b04-9790-4fc4-839a-3bd8ab9f75e5', 1, sysdate(), 'Proof of House/Building is 5years Old', 1, 1, 2),
      (62, sysdate(), '17321b48-45f4-478c-8ed3-d6531d1edc86', 1, sysdate(), 'Estimate of Cost of Repair', 1, 1, 2),
      (63, sysdate(), 'd6ca8284-b111-4555-ab01-2206378b52de', 1, sysdate(), 'Certificate from Doctor', 1, 1, 2),
      (64, sysdate(), '8b5ce934-8fd6-433b-a3a2-e376fd285a3b', 1, sysdate(), 'Certificate from Employer', 1, 1, 2),
      (65, sysdate(), '2ebc754d-d7dd-4d34-a1d4-34d9cb93b63f', 1, sysdate(), 'Marriage Invitation Card', 1, 1, 2),
      (66, sysdate(), 'e19c16b6-28f9-4948-98d8-5689d8a89aba', 1, sysdate(), 'Hall Booking Receipt', 1, 1, 2),
      (67, sysdate(), '63a5616d-8f43-4f69-8744-5bef33a6b8e9', 1, sysdate(), 'Certificate from Grampanchayat, Priest, Church or Budha Vihar', 1, 1, 2),
      (68, sysdate(), 'bc6ef842-0bd1-425e-9c33-86b8c1257c15', 1, sysdate(), 'Estimate Marriage Expenses', 1, 1, 2),
      (69, sysdate(), '8e684186-0a14-415f-96ad-541b27267371', 1, sysdate(), 'Payment Receipt', 1, 1, 2),
      (70, sysdate(), 'e729bf73-3088-451c-bdaa-1b9d837306a9', 1, sysdate(), 'Admission Receipt', 1, 1, 2),
      (71, sysdate(), 'f7744170-e198-44d7-942e-0169cb40b895', 1, sysdate(), 'Admit Card', 1, 1, 2),
      (72, sysdate(), '313f525d-a423-48a7-9b4e-63f83b53c619', 1, sysdate(), 'Certificate from Institution giving the Cost of Education', 1, 1, 2),
      (73, sysdate(), 'fabeacdb-3523-4525-898d-dca1ccd74354', 1, sysdate(), 'Certificate from HR or ER Dept', 1, 1, 2);

-- 6. Create Settlement Types
insert into settlement_type(id, created_at, entity_id, is_active, updated_at, code, title, created_by, updated_by, tenant_id)
VALUES(9, sysdate(), 'b2dddbd9-7927-46bb-82a2-119e8a6e614d', 1, sysdate(), '01', 'Death', 1, 1, 2),
      (10, sysdate(), '55844a6f-df84-44ba-937b-03a366cb086b', 1, sysdate(), '02', 'Retirement VRS', 1, 1, 2),
      (11, sysdate(), '1ead260c-795e-4999-b77e-aaf897c36aca', 1, sysdate(), '03', 'Retirement Normal', 1, 1, 2),
      (12, sysdate(), '4a24f5b6-bfd6-4aa5-9d46-cf2d1f20b884', 1, sysdate(), '04', 'Resignation', 1, 1, 2),
      (13, sysdate(), '6ac8ac42-72e7-4081-8522-64354e2f06b6', 1, sysdate(), '05', 'Termination', 1, 1, 2),
      (14, sysdate(), 'ac837b13-6c00-4a40-a88a-61f96a4545f2', 1, sysdate(), '08', 'Migration', 1, 1, 2),
      (15, sysdate(), 'a0f67213-ab51-4f5e-bfca-8b265d886df6', 1, sysdate(), '09', 'Dismissal', 1, 1, 2),
      (16, sysdate(), 'cafd0abf-45c1-4c38-928b-6f3e783b343a', 1, sysdate(), '10', 'Closure of Establishment', 1, 1, 2);

-- 7. Assign documents to settlement types
INSERT INTO `settlement_documents`(settlement_type_id, document_id) VALUES (9,41),(9,45),(9,43),(9,44);
INSERT INTO `settlement_documents`(settlement_type_id, document_id) VALUES (10,46),(10,42),(10,43),(10,44);
INSERT INTO `settlement_documents`(settlement_type_id, document_id) VALUES (11,46),(11,42),(11,43),(11,44);
INSERT INTO `settlement_documents`(settlement_type_id, document_id) VALUES (12,46),(12,42),(12,43),(12,44);
INSERT INTO `settlement_documents`(settlement_type_id, document_id) VALUES (13,46),(13,42),(13,43),(13,44);
INSERT INTO `settlement_documents`(settlement_type_id, document_id) VALUES (14,46),(14,42),(14,43),(14,44);
INSERT INTO `settlement_documents`(settlement_type_id, document_id) VALUES (15,46),(15,42),(15,43),(15,44);
INSERT INTO `settlement_documents`(settlement_type_id, document_id) VALUES (16,46);

-- 8. Create TransferOut Types
insert into transfer_out_type(id, created_at, entity_id, is_active, updated_at, code, title, created_by, updated_by, tenant_id)
VALUES (3, sysdate(), 'ad6d659c-4ac0-4abe-83d7-e91374f26c3c', 1, sysdate(), '06', 'RPFC', 1, 1, 2),
       (4, sysdate(), 'd529cf93-e9ee-4d8a-8d8b-096bf06653e5', 1, sysdate(), '07', 'TRUST', 1, 1, 2);

-- 9. Assign documents to TransferOut types
INSERT INTO `tranferout_documents`(transferout_type_id, document_id) VALUES (3,49);
INSERT INTO `tranferout_documents`(transferout_type_id, document_id) VALUES (4,49);

-- 10. Create Banks
insert into bank(id, created_at, entity_id, is_active, updated_at, name, created_by, updated_by, tenant_id)
VALUES (1, sysdate(), 'f0c2d187-96a8-4547-aaaf-5551402bb81f', 1, sysdate(), 'Kotak Mahindra Bank', 1, 1, 2),
       (2, sysdate(), 'ccdef99c-b008-4c51-ab87-9599cfa5e8ef', 1, sysdate(), 'HDFC Bank', 1, 1, 2);

-- 11. Create Payment Modes
insert into payment_mode(id, created_at, entity_id, is_active, updated_at, code, mode, created_by, updated_by, tenant_id)
VALUES(1, sysdate(), 'd4416df6-fcdc-4de6-84f7-cbea5181b5c1', 1, sysdate(), 'A', 'Cheque', 1, 1, 2),
      (2, sysdate(), '2246b808-c0af-4dab-922c-7a653cd38b6f', 1, sysdate(), 'E', 'NEFT', 1, 1, 2),
      (3, sysdate(), 'd8446a95-7366-4a2b-a0a5-d92e21b7fd26', 1, sysdate(), 'L', 'NECS', 1, 1, 2),
      (4, sysdate(), '4a6a9b0e-6614-48d9-9075-fe3608c69217', 1, sysdate(), 'I', 'DEMAND DRAFT', 1, 1, 2);

-- 12. Create PF Account Holder
insert into pf_account_holder(id, created_at, entity_id, is_active, updated_at, name, code, created_by, updated_by, tenant_id)
VALUES (1, sysdate(), 'b5949891-7f9d-4830-9d47-82b214798bdf', 1, sysdate(), 'RPFC', '06', 1, 1, 2),
       (2, sysdate(), '28576e78-8cf3-495d-b3f0-8660911dbc93', 1, sysdate(), 'Trust', '07', 1, 1, 2);

-- 13. Create TransferIn Status
insert into transfer_in_status(id, created_at, entity_id, is_active, updated_at, code, title, created_by, updated_by, tenant_id)
VALUES(1, sysdate(), 'd15fad8d-97a1-4136-a8a9-334c741b8217', 1, sysdate(), 'P', 'In Process', 1, 1, 2),
      (2, sysdate(), 'fa4ad102-b034-49f9-aba8-36afa2ee4a6b', 1, sysdate(), 'R', 'Completed', 1, 1, 2),
      (3, sysdate(), '3f6f8340-6004-4e9d-bfac-fdc8fc1a9125', 1, sysdate(), 'C', 'Canceled', 1, 1, 2);

-- 14. Create TransferIn Status
insert into loan_status(id, created_at, is_active, updated_at, code, title, created_by, updated_by, tenant_id, entity_id)
VALUES (1, sysdate(), 1, sysdate(), 'P', 'Pending', 1, 1, 2, '65591f56-4d36-4d9c-ae73-caf07bbc53a7'),
       (2, sysdate(), 1, sysdate(), 'R', 'Rejected', 1, 1, 2, 'e8a525fa-72e9-43d2-bc85-2fd1837afdf4'),
       (3, sysdate(), 1, sysdate(), 'A', 'Approved', 1, 1, 2, '0e955b57-caf8-4680-bc57-1d29ae3e4983');

-- 15. Create TransferOut Status
insert into transfer_out_status(id, created_at, entity_id, is_active, updated_at, title, created_by, updated_by, tenant_id)
VALUES (1, sysdate(), '4b9355fe-3b67-454f-9372-cc8710d4cbc6', 1, sysdate(), 'In Process', 1, 1, 2),
       (2, sysdate(), '1ecd1825-cf01-4842-87b2-1cdc0362cd43', 1, sysdate(), 'Completed', 1, 1, 2),
       (3, sysdate(), '9e90aca8-d823-471e-b550-55bf03a06445', 1, sysdate(), 'Canceled', 1, 1, 2);

-- 16. Create Settlement Status
insert into settlement_status(id, created_at, entity_id, is_active, updated_at, title, created_by, updated_by, tenant_id)
VALUES (1, sysdate(), '08a7e140-297d-4ae1-96b5-731a7ade11bb', 1, sysdate(), 'In Process', 1, 1, 2),
       (2, sysdate(), '9a4ab157-41fa-48f2-80c7-69f8606178ef', 1, sysdate(), 'Completed', 1, 1, 2),
       (3, sysdate(), 'f89f5741-62f2-498b-9499-a635fc6204a7', 1, sysdate(), 'Canceled', 1, 1, 2);

-- 17. Create Loan Type
insert into loan_type(id, created_at, is_active, updated_at, code, loan_group, maximum_number_of_withdrawals,
                      minimum_membership_tenure_in_months, title, created_by, updated_by, tenant_id, entity_id,
                      from_retirement_date, next_eligibility, pf_base_salary_in_month, member_balance_in_percentage,
                      company_balance_in_percentage, vpf_balance_in_percentage, total_cost_in_percentage,
                      applied_amount_in_percentage)
VALUES (11, sysdate(), 1, sysdate(), '01', 'A', 100, 60, 'Purchase of Residential House/Flat', 1, 1, 2, 'a7e266a8-0b59-4696-94f7-b4f4df83dc5f', 0, 0, 36, 100, 100, 100, 100, 100),
       (12, sysdate(), 1, sysdate(), '13', 'A', 1, 120, 'Repayment of Housing Loan', 1, 1, 2, '7b277cf5-de07-41f8-a562-23412d9b7ab0', 0, 0, 36, 100, 100, 100, 100, 100),
       (13, sysdate(), 1, sysdate(), '02', 'A', 5, 60, 'Alteration/Improvement/Additions to Residential House/Flat', 1, 1, 2, '2a507be4-f98e-47cd-895c-add6668e2493', 0, 10, 12, 100, 0, 100, 0, 100),
       (14, sysdate(), 1, sysdate(), '11', 'A', 1, 60, 'Construction of Residential House/Flat', 1, 1, 2, 'b3539674-0144-4857-ac7f-799e62aea975', 0, 0, 36, 100, 100, 100, 100, 100),
       (15, sysdate(), 1, sysdate(), '12', 'A', 100, 60, 'Purchase of Site/Plot House', 1, 1, 2, 'e8f06694-118b-4424-b743-f0d26bcac8e1', 0, 0, 24, 100, 100, 100, 100, 100),
       (16, sysdate(), 1, sysdate(), '03', 'B', 3, 84, 'Marriage', 1, 1, 2, '3a760d3d-698e-4634-983e-5e554f07d650', 0, 0, 0, 50, 0, 50, 0, 100),
       (17, sysdate(), 1, sysdate(), '04', 'C', 5, 0, 'Medical(Hospitalization & Major Operations/Sickness)', 1, 1, 2, '7720fbdc-85ae-4f15-a457-742474aa6fca', 0, 0, 6, 100, 0, 100, 0, 100),
       (18, sysdate(), 1, sysdate(), '08', 'D', 3, 84, 'Education(Post Matriculation) of Children', 1, 1, 2, 'da5844bb-4cbb-45b9-8195-ee8de2332c73', 0, 0, 0, 100, 0, 100, 0, 100),
       (19, sysdate(), 1, sysdate(), '99', 'E', 0, 1, 'Pre-Retirement Withdrawal(Within 1 Year from retirement date)', 1, 1, 2, 'd1344250-1a01-4fa5-8852-986a0cf84593', 1, 0, 0, 90, 90, 90, 0, 0),
       (20, sysdate(), 1, sysdate(), '98', 'E', 2, 3, 'Outbreak of any epidemic or pandemic Situation', 1, 1, 2, 'd59aeb25-5226-4872-9f4f-dc8bd3f2d4f5', 0, 0, 3, 75, 75, 75, 0, 100);

-- 18. Assign Loan Document
insert into loan_documents(loan_type_id, document_id) values (11, 51), (11, 52), (11, 53), (11, 54),
                                                           (11, 55), (11, 56), (11, 57);

insert into loan_documents(loan_type_id, document_id) values (12, 51), (12, 52), (12, 53), (12, 54),
                                                              (12, 55), (12, 56), (12, 57);

insert into loan_documents(loan_type_id, document_id) values (13, 51), (13, 52), (13, 53), (13, 54),
                                                           (13, 55), (13, 56), (13, 57);

insert into loan_documents(loan_type_id, document_id) values (14, 51), (14, 58), (14, 59), (14, 60);

insert into loan_documents(loan_type_id, document_id) values (15, 61), (15, 55), (15, 62);

insert into loan_documents(loan_type_id, document_id) values (16, 51), (16, 63), (16, 64);

insert into loan_documents(loan_type_id, document_id) values (17, 65), (17, 66), (17, 67), (17, 68);

insert into loan_documents(loan_type_id, document_id) values (18, 69), (18, 70), (18, 71), (18, 72);

insert into loan_documents(loan_type_id, document_id) values (19, 63);

-- 19. Assign Loan Amount
insert into loan_amount(created_at, entity_id, is_active, updated_at, applied_amount, own_account_pf_balance, pf_salary,
                        total_cost, total_pf_balance, created_by, updated_by, loan_type_id, tenant_id)
VALUES (sysdate(), 'f55f1ab7-c699-462f-b897-7b0471096e18', 1, sysdate(), 100, 0, 36, 100, 100, 1, 1, 11, 2),
       (sysdate(), 'd9cca112-b291-491e-a11d-6a89ea3e0fad', 1, sysdate(), 100, 0, 36, 100, 100, 1, 1, 12, 2),
       (sysdate(), '2b7fd3f1-8447-48d7-ac78-8dab855f5bf4', 1, sysdate(), 100, 0, 36, 100, 100, 1, 1, 14, 2),
       (sysdate(), 'c662b448-d585-4955-9620-45fa296eaeae', 1, sysdate(), 100, 0, 24, 100, 100, 1, 1, 15, 2),
       (sysdate(), '1bd45e0d-369f-4118-bf7b-2edcff5b29d9', 1, sysdate(), 100, 100, 12, 0, 0, 1, 1, 13, 2),
       (sysdate(), '66123dd0-9993-44e2-ac9d-b21b89984936', 1, sysdate(), 100, 100, 6, 0, 0, 1, 1, 17, 2),
       (sysdate(), '1db5c060-83b1-4a81-bf41-3f5478611a31', 1, sysdate(), 100, 50, 0, 0, 0, 1, 1, 16, 2),
       (sysdate(), '155e5c7d-d688-420b-ae3e-6a93d05cbf74', 1, sysdate(), 100, 50, 0, 0, 0, 1, 1, 18, 2),
       (sysdate(), '6438049c-8dff-4b32-95ff-4882418381d7', 1, sysdate(), 0, 0, 0, 0, 90, 1, 1, 19, 2),
       (sysdate(), '168c609d-31ff-4c71-91c2-3d10ee742a8b', 1, sysdate(), 100, 0, 3, 0, 75, 1, 1, 20, 2);

-- 20. Create PDF Document Deign Templates

insert into pdf_document_design(created_at, entity_id, is_active, updated_at, document, document_type,
                                tenant_id)
VALUES (sysdate(), '', 1, sysdate(), '<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.1" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:fo="http://www.w3.org/1999/XSL/Format" exclude-result-prefixes="fo">


    <xsl:template match="loanReceipt">
        <fo:root xmlns:fo="http://www.w3.org/1999/XSL/Format">

            <fo:layout-master-set>

                <fo:simple-page-master master-name="simpleA4"
                                       page-height="29.7cm" page-width="21cm"
                                       margin-top="1cm" margin-bottom="1cm"
                                       margin-left="1cm" margin-right="1cm">
                    <fo:region-body/>
                </fo:simple-page-master>

            </fo:layout-master-set>


            <fo:page-sequence master-reference="simpleA4">

                <fo:flow flow-name="xsl-region-body" font-size="12pt" line-height="1.5"
                         font-family="Times-Roman" word-spacing="6pt" letter-spacing="1pt">


                    <!-- logo and address -->
                    <fo:table line-height="1" word-spacing="6pt">
                        <fo:table-column column-width="25%"/>
                        <fo:table-column column-width="75%"/>

                        <fo:table-body>
                            <fo:table-row>
                                <fo:table-cell>
                                    <fo:block>
                                        <fo:external-graphic src="https://developerspftrust.coreintegra.com/images/mahindra-full-logo.png"
                                                             content-height="110pt" content-width="110pt"/>
                                    </fo:block>
                                </fo:table-cell>
                                <fo:table-cell>
                                    <fo:block margin-top="15pt">
                                        <fo:block font-weight="600" font-size="12pt">MAHINDRA &amp; MAHINDRA LIMITED STAFF PROVIDENT FUND</fo:block>
                                    </fo:block>
                                </fo:table-cell>
                            </fo:table-row>
                        </fo:table-body>
                    </fo:table>

                    <fo:block line-height="1.2" margin-top="20pt">
                        <fo:block>Date: <xsl:value-of select="approvalDate"/></fo:block>
                        <fo:block margin-top="20pt">Ref.: Application No.: <xsl:value-of select="applicationNumber"/></fo:block>
                    </fo:block>

                    <fo:block margin-top="20pt">Dear Sir / Madam,</fo:block>

                    <fo:block margin-top="20pt" line-height="1.2" text-align="justify">
                        Subject: Non Refundable Loan
                    </fo:block>

                    <fo:block margin-top="20pt" line-height="1.2" text-align="justify">
                        <fo:inline padding-left="50pt">With reference</fo:inline> to your application we have credited your
                        account No. <xsl:value-of select="accountNumber"/> with Bank "<xsl:value-of select="bankName"/>"
                        for Rs. <xsl:value-of select="amount"/> ( <xsl:value-of select="amountInWords"/> Only )
                        on <xsl:value-of select="paymentDate"/> by <xsl:value-of select="paymentMethod"/>
                        Facility through <xsl:value-of select="paymentBank"/>.
                    </fo:block>


                    <fo:block margin-top="30pt">Mahindra &amp; Mahindra Limited</fo:block>
                    <fo:block>Staff Provident Fund</fo:block>

                    <fo:block margin-top="30pt" padding-bottom="30pt"
                              margin-bottom="5pt"
                              border-bottom="1pt solid red">
                        This is a computer generated statement hence no signature required.</fo:block>

                    <fo:block text-align="center" font-size="12" font-weight="600"
                               margin-top="50pt"   >
                        RECEIPT
                    </fo:block>

                    <fo:block margin-top="20pt" text-align="justify">
                        Received from Mahindra &amp; Mahindra Limited Staff Provident Fund
                        The Sum of Rs. <xsl:value-of select="amount"/> (<xsl:value-of select="amountInWords"/> Only) Being
                        the Provident Fund Loan (Non-Refundable) sanctioned to me.
                    </fo:block>

                    <fo:block margin-top="50pt">Receiver''s Signature</fo:block>

                    <fo:block margin-top="10pt">Name: <fo:inline padding-left="30pt"><xsl:value-of select="name"/></fo:inline></fo:block>

                    <fo:block margin-top="10pt">Company Name: <xsl:value-of select="deptCode"/> </fo:block>

                    <fo:block margin-top="10pt">
                         <fo:inline>P.F.No.: <xsl:value-of select="pfNumber"/></fo:inline>
                         <fo:inline padding-left="60pt">Token.No.: <xsl:value-of select="tokenNumber"/></fo:inline>
                    </fo:block>
                </fo:flow>
            </fo:page-sequence>

        </fo:root>
    </xsl:template>


</xsl:stylesheet>', 'LOAN_RECEIPT', 2);

insert into pdf_document_design(created_at, entity_id, is_active, updated_at, document, document_type,
                                tenant_id)
VALUES(sysdate(), '', 1, sysdate(), '<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.1" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:fo="http://www.w3.org/1999/XSL/Format" exclude-result-prefixes="fo">

    <xsl:template match="annuxurek">
        <fo:root xmlns:fo="http://www.w3.org/1999/XSL/Format">

            <fo:layout-master-set>
                <fo:simple-page-master master-name="simpleA4"
                                       page-height="29.7cm" page-width="21cm"
                                       margin-top="1cm" margin-bottom="1cm"
                                       margin-left="1cm" margin-right="1cm">
                    <fo:region-body/>
                </fo:simple-page-master>
            </fo:layout-master-set>


            <fo:page-sequence master-reference="simpleA4">
                <fo:flow flow-name="xsl-region-body" font-size="11pt" line-height="1.5"
                         font-family="Times-Roman" word-spacing="6pt" letter-spacing="1pt">


                    <!-- logo and address -->
                    <fo:block>
                        <fo:table line-height="1" word-spacing="6pt">
                            <fo:table-column column-width="100pt"/>
                            <fo:table-column/>

                            <fo:table-body>
                                <fo:table-row>
                                    <fo:table-cell padding-bottom="10pt">
                                        <fo:block padding-bottom="10pt">
                                            <fo:external-graphic src="url(''https://developerspftrust.coreintegra.com/images/mahindra-full-logo.png'')"
                                                                 content-height="75pt" content-width="75pt"/>
                                        </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell>
                                        <fo:block  text-align="center" margin-top="10pt">
                                            <fo:block>
                                                MAHINDRA  &amp;  MAHINDRA  LIMITED  .  STAFF  PROVIDENT  FUND
                                            </fo:block>
                                            <fo:block>
                                                MITC BUILDING,  6th FLOOR AKURLI ROAD,
                                            </fo:block>
                                            <fo:block>
                                                KANDIVALI (EAST)  MUMBAI - 400101
                                            </fo:block>
                                            <fo:block margin-top="10pt">
                                                PF TRANSFER DETAILS - ANNEXURE '' K ''.
                                            </fo:block>
                                        </fo:block>
                                    </fo:table-cell>
                                </fo:table-row>
                            </fo:table-body>
                        </fo:table>
                    </fo:block>
                    <!-- logo and address end -->

                    <fo:block color="#27292d" margin-top="20pt" line-height="1.2">
                        Co-Cd/  Serial  No./Doc.No.   <xsl:value-of select="unitCode"/>   /
                        <xsl:value-of select="serialNumber"/>   /   <xsl:value-of select="documentNumber"/>
                    </fo:block>

                    <fo:list-block color="#27292d"
                                   margin-top="10pt" line-height="1.2"
                                   word-spacing="6pt"  margin-right="20pt">

                        <fo:list-item>
                            <fo:list-item-label end-indent="label-end()">
                                <fo:block>1.</fo:block>
                            </fo:list-item-label>

                            <fo:list-item-body start-indent="body-start()">
                                <fo:block>No.  &amp;  Date  of  advice  for  transfer  of  account  in  respect  of  the  following  subscriber: </fo:block>
                            </fo:list-item-body>
                        </fo:list-item>


                        <fo:list-item margin-top="10pt">
                            <fo:list-item-label end-indent="label-end()">
                                <fo:block>2.</fo:block>
                            </fo:list-item-label>

                            <fo:list-item-body start-indent="body-start()">
                                <fo:block>
                                    <fo:inline text-align="left">From A/C No. <xsl:value-of select="fromAccountNumber"/> </fo:inline>
                                    <fo:inline text-align="right"> To A/C No. <xsl:value-of select="toAccountNumber"/></fo:inline>
                                </fo:block>
                            </fo:list-item-body>
                        </fo:list-item>

                        <fo:list-item margin-top="10pt">
                            <fo:list-item-label end-indent="label-end()">
                                <fo:block> </fo:block>
                            </fo:list-item-label>
                            <fo:list-item-body>
                                <fo:block line-height="1" margin-top="10pt" word-spacing="6pt" margin-left="13pt">
                                    <fo:table padding="0" margin="0">
                                        <fo:table-column column-width="30%" padding="0" margin="0"/>
                                        <fo:table-column column-width="70%" padding="0" margin="0"/>

                                        <fo:table-body>
                                            <fo:table-row>
                                                <fo:table-cell padding="0" margin="0">

                                                    <xsl:if test="transferOutType = ''RPFC''">
                                                        <fo:block padding="0" margin="0">Name and Address of</fo:block>
                                                        <fo:block padding="0" margin="0">Current Employer: </fo:block>
                                                    </xsl:if>

                                                    <xsl:if test="transferOutType = ''TRUST''">
                                                        <fo:block padding="0" margin="0">Payee Name and Address: </fo:block>
                                                    </xsl:if>

                                                </fo:table-cell>
                                                <fo:table-cell padding="0" margin="0">
                                                <xsl:if test="transferOutType = ''TRUST''">
                                                    <fo:block padding="0" margin="0"><xsl:value-of select="payeeName"/></fo:block>

                                                    <fo:block padding="0" margin="0"><xsl:value-of select="addressLine1"/></fo:block>
                                                    <fo:block padding="0" margin="0"><xsl:value-of select="addressLine2"/></fo:block>
                                                    <fo:block padding="0" margin="0"><xsl:value-of select="addressLine3"/></fo:block>
                                                    <fo:block padding="0" margin="0"><xsl:value-of select="addressLine4"/></fo:block>
                                                    </xsl:if>

                                                <xsl:if test="transferOutType = ''RPFC''">
                                                    <fo:block padding="0" margin="0"><xsl:value-of select="currentEmployerName"/></fo:block>

                                                    <fo:block padding="0" margin="0"><xsl:value-of select="currentAddressLine1"/></fo:block>
                                                    <fo:block padding="0" margin="0"><xsl:value-of select="currentAddressLine2"/></fo:block>
                                                    <fo:block padding="0" margin="0"><xsl:value-of select="currentAddressLine3"/></fo:block>
                                                    <fo:block padding="0" margin="0"><xsl:value-of select="currentAddressLine4"/></fo:block>
                                                    </xsl:if>
                                                </fo:table-cell>
                                            </fo:table-row>
                                        </fo:table-body>
                                    </fo:table>
                                </fo:block>
                            </fo:list-item-body>
                        </fo:list-item>


                        <fo:list-item margin-top="15pt">
                            <fo:list-item-label end-indent="label-end()">
                                <fo:block>3.</fo:block>
                            </fo:list-item-label>
                            <fo:list-item-body start-indent="body-start()">
                                <fo:block>Name of Subscriber : <xsl:value-of select="subscriber"/></fo:block>
                            </fo:list-item-body>
                        </fo:list-item>


                        <fo:list-item margin-top="10pt">
                            <fo:list-item-label end-indent="label-end()">
                                <fo:block>4.</fo:block>
                            </fo:list-item-label>
                            <fo:list-item-body start-indent="body-start()">
                                <fo:block>Balance of P. F. accumulation as on <xsl:value-of select="yearOpeningDate"/></fo:block>
                                <fo:table>
                                    <fo:table-column column-width="70%"/>
                                    <fo:table-column column-width="30%"/>

                                    <fo:table-body>
                                        <fo:table-row>
                                            <fo:table-cell text-align="left">
                                                <fo:block>a. Employee''s Contribution to P. F. </fo:block>
                                            </fo:table-cell>
                                            <fo:table-cell text-align="right">
                                                <fo:block><xsl:value-of select="yearOpeningEmployeeContribution"/></fo:block>
                                            </fo:table-cell>
                                        </fo:table-row>

                                        <fo:table-row>
                                            <fo:table-cell text-align="left">
                                                <fo:block>b. Employer''s Contribution to P. F. </fo:block>
                                            </fo:table-cell>
                                            <fo:table-cell text-align="right">
                                                <fo:block><xsl:value-of select="yearOpeningEmployerContribution"/></fo:block>
                                            </fo:table-cell>
                                        </fo:table-row>

                                        <fo:table-row>
                                            <fo:table-cell text-align="left">
                                                <fo:block>c. Voluntary Contribution to P. F. </fo:block>
                                            </fo:table-cell>
                                            <fo:table-cell text-align="right">
                                                <fo:block><xsl:value-of select="yearOpeningVoluntaryContribution"/></fo:block>
                                            </fo:table-cell>
                                        </fo:table-row>
                                    </fo:table-body>
                                </fo:table>

                            </fo:list-item-body>
                        </fo:list-item>

                        <fo:list-item margin-top="10pt">
                            <fo:list-item-label end-indent="label-end()">
                                <fo:block>5. </fo:block>
                            </fo:list-item-label>
                            <fo:list-item-body start-indent="body-start()">
                                <fo:block>Contribution &amp; Interest during current </fo:block>
                                <fo:block>accounting period (upto the last day of transfer ) </fo:block>


                                <fo:table margin-top="10pt">
                                    <fo:table-column column-width="70%"/>
                                    <fo:table-column column-width="30%"/>

                                    <fo:table-body>

                                        <fo:table-row>
                                            <fo:table-cell text-align="left">
                                                <fo:block>d. Employee Contribution to P. F. </fo:block>
                                            </fo:table-cell>
                                            <fo:table-cell text-align="right">
                                                <fo:block><xsl:value-of select="employeeContribution"/></fo:block>
                                            </fo:table-cell>
                                        </fo:table-row>

                                        <fo:table-row>
                                            <fo:table-cell text-align="left">
                                                <fo:block>e. Employee Contribution Transfer Received </fo:block>
                                            </fo:table-cell>
                                            <fo:table-cell text-align="right">
                                                <fo:block><xsl:value-of select="employeeContributionTransferIn"/></fo:block>
                                            </fo:table-cell>
                                        </fo:table-row>

                                        <fo:table-row>
                                            <fo:table-cell padding-top="10pt" text-align="left">
                                                <fo:block>f. Interest On Employee''s Contribution P.F </fo:block>
                                            </fo:table-cell>
                                            <fo:table-cell padding-top="10pt" text-align="right">
                                                <fo:block><xsl:value-of select="interestOnEmployeeContribution"/></fo:block>
                                            </fo:table-cell>
                                        </fo:table-row>


                                        <fo:table-row>
                                            <fo:table-cell text-align="left">
                                                <fo:block>g. Employer''s Contribution to P. F. </fo:block>
                                            </fo:table-cell>
                                            <fo:table-cell text-align="right">
                                                <fo:block><xsl:value-of select="employerContribution"/></fo:block>
                                            </fo:table-cell>
                                        </fo:table-row>

                                        <fo:table-row>
                                            <fo:table-cell text-align="left">
                                                <fo:block>h. Employer''s Contribution Transfer Received </fo:block>
                                            </fo:table-cell>
                                            <fo:table-cell text-align="right">
                                                <fo:block><xsl:value-of select="employerContributionTransferIn"/></fo:block>
                                            </fo:table-cell>
                                        </fo:table-row>

                                        <fo:table-row>
                                            <fo:table-cell padding-top="10pt" text-align="left">
                                                <fo:block>i. Interest On Employer''s Contribution P.F </fo:block>
                                            </fo:table-cell>
                                            <fo:table-cell padding-top="10pt" text-align="right">
                                                <fo:block><xsl:value-of select="interestOnEmployerContribution"/></fo:block>
                                            </fo:table-cell>
                                        </fo:table-row>

                                        <fo:table-row>
                                            <fo:table-cell text-align="left">
                                                <fo:block>j. Voluntary Contribution to P. F.</fo:block>
                                            </fo:table-cell>
                                            <fo:table-cell text-align="right">
                                                <fo:block><xsl:value-of select="voluntaryContribution"/></fo:block>
                                            </fo:table-cell>
                                        </fo:table-row>

                                        <fo:table-row>
                                            <fo:table-cell padding-top="10pt" text-align="left">
                                                <fo:block>k. Interest On Voluntary Contribution P.F </fo:block>
                                            </fo:table-cell>
                                            <fo:table-cell padding-top="10pt" text-align="right">
                                                <fo:block><xsl:value-of select="interestOnVoluntaryContribution"/></fo:block>
                                            </fo:table-cell>
                                        </fo:table-row>


                                    </fo:table-body>
                                </fo:table>

                            </fo:list-item-body>
                        </fo:list-item>

                        <fo:list-item margin-top="10pt">
                            <fo:list-item-label end-indent="label-end()">
                                <fo:block>6. </fo:block>
                            </fo:list-item-label>
                            <fo:list-item-body start-indent="body-start()">
                                <fo:table>
                                    <fo:table-column column-width="70%"/>
                                    <fo:table-column column-width="30%"/>

                                    <fo:table-body>
                                        <fo:table-row>
                                            <fo:table-cell text-align="left">
                                                <fo:block>Total Contribution ( a to k ) </fo:block>
                                            </fo:table-cell>
                                            <fo:table-cell text-align="right">
                                                <fo:block><xsl:value-of select="totalContribution"/></fo:block>
                                            </fo:table-cell>
                                        </fo:table-row>
                                    </fo:table-body>
                                </fo:table>

                            </fo:list-item-body>
                        </fo:list-item>


                        <fo:list-item margin-top="10pt">
                            <fo:list-item-label end-indent="label-end()">
                                <fo:block>7. </fo:block>
                            </fo:list-item-label>
                            <fo:list-item-body start-indent="body-start()">
                                <fo:table>
                                    <fo:table-column column-width="70%"/>
                                    <fo:table-column column-width="30%"/>

                                    <fo:table-body>
                                        <fo:table-row>
                                            <fo:table-cell text-align="left">
                                                <fo:block> Withdrawals on loan (N.R. ) </fo:block>
                                            </fo:table-cell>
                                            <fo:table-cell text-align="right">
                                                <fo:block><xsl:value-of select="loanWithdrawals"/></fo:block>
                                            </fo:table-cell>
                                        </fo:table-row>
                                    </fo:table-body>
                                </fo:table>

                            </fo:list-item-body>
                        </fo:list-item>


                        <fo:list-item margin-top="10pt">
                            <fo:list-item-label end-indent="label-end()">
                                <fo:block>8. </fo:block>
                            </fo:list-item-label>
                            <fo:list-item-body start-indent="body-start()">
                                <fo:table>
                                    <fo:table-column column-width="70%"/>
                                    <fo:table-column column-width="30%"/>

                                    <fo:table-body>
                                        <fo:table-row>
                                            <fo:table-cell text-align="left">
                                                <fo:block>Net Credit to the account as on <xsl:value-of select="dueDate"/> </fo:block>
                                            </fo:table-cell>
                                            <fo:table-cell text-align="right">
                                                <fo:block><xsl:value-of select="netCredit"/></fo:block>
                                            </fo:table-cell>
                                        </fo:table-row>
                                    </fo:table-body>
                                </fo:table>

                            </fo:list-item-body>
                        </fo:list-item>

                        <fo:list-item margin-top="10pt">
                            <fo:list-item-label end-indent="label-end()">
                                <fo:block>9. </fo:block>
                            </fo:list-item-label>
                            <fo:list-item-body start-indent="body-start()">
                                <fo:table>
                                    <fo:table-column column-width="70%"/>
                                    <fo:table-column column-width="30%"/>

                                    <fo:table-body>
                                        <fo:table-row>
                                            <fo:table-cell text-align="left">
                                                <fo:block>Date of Joining Service ( M&amp;M Ltd ) </fo:block>
                                            </fo:table-cell>
                                            <fo:table-cell text-align="right">
                                                <fo:block><xsl:value-of select="dateOfJoiningService"/></fo:block>
                                            </fo:table-cell>
                                        </fo:table-row>
                                    </fo:table-body>
                                </fo:table>

                            </fo:list-item-body>
                        </fo:list-item>
                        <fo:list-item >
                            <fo:list-item-label end-indent="label-end()">
                                <fo:block>10. </fo:block>
                            </fo:list-item-label>
                            <fo:list-item-body start-indent="body-start()">
                                <fo:table>
                                    <fo:table-column column-width="70%"/>
                                    <fo:table-column column-width="30%"/>

                                    <fo:table-body>
                                        <fo:table-row>
                                            <fo:table-cell text-align="left">
                                                <fo:block>Date of Joining P. F. </fo:block>
                                            </fo:table-cell>
                                            <fo:table-cell text-align="right">
                                                <fo:block><xsl:value-of select="dateOfJoiningPf"/></fo:block>
                                            </fo:table-cell>
                                        </fo:table-row>
                                    </fo:table-body>
                                </fo:table>

                            </fo:list-item-body>
                        </fo:list-item>
                        <fo:list-item>
                            <fo:list-item-label end-indent="label-end()">
                                <fo:block>11. </fo:block>
                            </fo:list-item-label>
                            <fo:list-item-body start-indent="body-start()">
                                <fo:table>
                                    <fo:table-column column-width="50%"/>
                                    <fo:table-column column-width="50%"/>

                                    <fo:table-body>
                                        <fo:table-row>
                                            <fo:table-cell text-align="left">
                                                <fo:block>Employees Pension Scheme A/C No </fo:block>
                                            </fo:table-cell>
                                            <fo:table-cell text-align="right">
                                                <fo:block><xsl:value-of select="epsNumber"/></fo:block>
                                            </fo:table-cell>
                                        </fo:table-row>
                                    </fo:table-body>
                                </fo:table>

                            </fo:list-item-body>
                        </fo:list-item>
                        <fo:list-item>
                            <fo:list-item-label end-indent="label-end()">
                                <fo:block>12. </fo:block>
                            </fo:list-item-label>
                            <fo:list-item-body start-indent="body-start()">
                                <fo:table>
                                    <fo:table-column column-width="70%"/>
                                    <fo:table-column column-width="30%"/>

                                    <fo:table-body>
                                        <fo:table-row>
                                            <fo:table-cell text-align="left">
                                                <fo:block>Date of Leaving Service </fo:block>
                                            </fo:table-cell>
                                            <fo:table-cell text-align="right">
                                                <fo:block><xsl:value-of select="dateOfLeavingService"/></fo:block>
                                            </fo:table-cell>
                                        </fo:table-row>
                                    </fo:table-body>
                                </fo:table>

                            </fo:list-item-body>
                        </fo:list-item>
                        <fo:list-item>
                            <fo:list-item-label end-indent="label-end()">
                                <fo:block>13. </fo:block>
                            </fo:list-item-label>
                            <fo:list-item-body start-indent="body-start()">
                                <fo:table>
                                    <fo:table-column column-width="70%"/>
                                    <fo:table-column column-width="30%"/>

                                    <fo:table-body>
                                        <fo:table-row>
                                            <fo:table-cell text-align="left">
                                                <fo:block>DOJ of Previous Employer </fo:block>
                                            </fo:table-cell>
                                            <fo:table-cell text-align="right">
                                                <fo:block><xsl:value-of select="dateOfJoiningPreviousEmployer"/></fo:block>
                                            </fo:table-cell>
                                        </fo:table-row>
                                    </fo:table-body>
                                </fo:table>

                            </fo:list-item-body>
                        </fo:list-item>

                    </fo:list-block>


                    <fo:table margin-top="30pt">
                        <fo:table-column column-width="50%"/>
                        <fo:table-column column-width="50%"/>

                        <fo:table-body>
                            <fo:table-row>
                                <fo:table-cell text-align="left">
                                    <fo:block>User: <fo:inline><xsl:value-of select="createdBy"/></fo:inline> </fo:block>
                                </fo:table-cell>
                                <fo:table-cell>
                                    <fo:block line-height="1.2" word-spacing="6pt" letter-spacing="1pt">
                                        <fo:block text-align="center">MAHINDRA &amp; MAHINDRA LIMITED</fo:block>
                                        <fo:block text-align="center">STAFF PROVIDENT FUND</fo:block>
                                        <fo:block text-align="center" margin-top="40pt">Authorized Signatory</fo:block>
                                    </fo:block>
                                </fo:table-cell>
                            </fo:table-row>
                        </fo:table-body>
                    </fo:table>

                </fo:flow>
            </fo:page-sequence>

        </fo:root>
    </xsl:template>

</xsl:stylesheet>
', 'ANNUXURE_K', 2);

insert into pdf_document_design(created_at, entity_id, is_active, updated_at, document, document_type,
                                tenant_id)
VALUES (sysdate(), '', 1, sysdate(), '<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.1" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:fo="http://www.w3.org/1999/XSL/Format" exclude-result-prefixes="fo">

    <xsl:template match="annualStatement">
        <fo:root xmlns:fo="http://www.w3.org/1999/XSL/Format">

            <fo:layout-master-set>
                <fo:simple-page-master master-name="simpleA4"
                                       page-width="29.7cm" page-height="21cm"
                                       margin-top="0.3cm" margin-bottom="0.3cm"
                                       margin-left="1cm" margin-right="1cm"
                                       >
                    <fo:region-body/>

                </fo:simple-page-master>

                <fo:simple-page-master master-name="annualAnnexure"
                                       page-width="29.7cm" page-height="21cm"
                                       margin-top="0.3cm" margin-bottom="0.3cm"
                                       margin-left="1cm" margin-right="1cm"
                                       >
                    <fo:region-body/>

                </fo:simple-page-master>
            </fo:layout-master-set>


            <fo:page-sequence master-reference="simpleA4">
                <fo:flow flow-name="xsl-region-body" font-size="11pt" line-height="1.2"
                         font-family="Times-Roman" word-spacing="6pt" letter-spacing="1pt">

                    <fo:block>

                        <fo:table line-height="1">
                            <fo:table-column column-width="13%"/>
                            <fo:table-column column-width="87%"/>

                            <fo:table-body>
                                <fo:table-row>
                                    <fo:table-cell margin="0" padding="0">
                                        <fo:block margin="0" padding="0">
                                            <fo:external-graphic src="https://developerspftrust.coreintegra.com/images/mahindra_logo.png" content-height="100pt" content-width="100pt"/>
                                        </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell margin="0" padding="0">
                                        <fo:block margin="0" padding="0" text-align="center" line-height="1.2">
                                            <fo:block font-weight="600">MAHINDRA &amp; MAHINDRA LIMITED STAFF PROVIDENT FUND</fo:block>
                                            <fo:block>ANNUAL STATEMENT AS ON <xsl:value-of select="closingDateWord"/></fo:block>
                                        </fo:block>
                                    </fo:table-cell>
                                </fo:table-row>
                            </fo:table-body>
                        </fo:table>
                        <!-- logo and address end -->

                        <fo:block margin="10pt 0pt" margin-bottom="0pt" margin-top="0pt"
                                  border="1pt solid black" line-height="1" padding="5pt pt0">

                                <fo:inline>Employee Name : <xsl:value-of select="name"/></fo:inline>
                                <fo:inline padding-left="30pt">Token No. : <xsl:value-of select="tokenNumber"/></fo:inline>
                                <fo:inline padding-left="30pt">P.F.No. : <xsl:value-of select="pfNumber"/></fo:inline>
                                <fo:inline padding-left="30pt">Unit Code. : <xsl:value-of select="unitCode"/></fo:inline>
                                <fo:inline padding-left="30pt">Dept.Cd : <xsl:value-of select="deptCode"/></fo:inline>

                        </fo:block>




                        <fo:block margin="10pt 0pt" margin-bottom="0pt" margin-top="0pt"
                                  border="1pt solid black" border-top="0" line-height="1" padding="5pt pt0">

                            <fo:table>
                                <fo:table-column column-width="25%"/>
                                <fo:table-column column-width="12.5%"/>
                                <fo:table-column column-width="12.5%"/>

                                <fo:table-column column-width="25%"/>
                                <fo:table-column column-width="12.5%"/>
                                <fo:table-column column-width="12.5%"/>

                                <fo:table-body>
                                    <fo:table-row>
                                        <fo:table-cell>
                                            <fo:block>Nominees</fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell>
                                            <fo:block>Relationship</fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell>
                                            <fo:block>Share%</fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell>
                                            <fo:block>Nominees</fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell>
                                            <fo:block>Relationship</fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell>
                                            <fo:block>Share%</fo:block>
                                        </fo:table-cell>
                                    </fo:table-row>
                                </fo:table-body>
                            </fo:table>

                        </fo:block>



                        <fo:block margin="10pt 0pt" margin-bottom="0pt" margin-top="0pt"
                                  border="1pt solid black" border-top="0" line-height="1" padding="5pt pt0">

                            <fo:table>
                                <fo:table-column column-width="25%"/>
                                <fo:table-column column-width="12.5%"/>
                                <fo:table-column column-width="12.5%"/>

                                <fo:table-column column-width="25%"/>
                                <fo:table-column column-width="12.5%"/>
                                <fo:table-column column-width="12.5%"/>

                                <fo:table-body>
                                    <fo:table-row>
                                        <fo:table-cell>
                                            <fo:block><xsl:value-of select="/annualStatement/nominee[1]/name"/></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell>
                                            <fo:block><xsl:value-of select="/annualStatement/nominee[1]/relationship"/></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell>
                                            <fo:block><xsl:value-of select="/annualStatement/nominee[1]/share"/></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell>
                                            <fo:block><xsl:value-of select="/annualStatement/nominee[2]/name"/></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell>
                                            <fo:block><xsl:value-of select="/annualStatement/nominee[2]/relationship"/></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell>
                                            <fo:block><xsl:value-of select="/annualStatement/nominee[2]/share"/></fo:block>
                                        </fo:table-cell>
                                    </fo:table-row>
                                </fo:table-body>
                            </fo:table>

                        </fo:block>

                        <fo:block margin="10pt 0pt" margin-bottom="0pt" margin-top="0pt"
                                  border="1pt solid black" border-top="0" line-height="1"
                                  padding="5pt pt0">

                            <fo:table>
                                <fo:table-column column-width="25%"/>
                                <fo:table-column column-width="12.5%"/>
                                <fo:table-column column-width="12.5%"/>

                                <fo:table-column column-width="25%"/>
                                <fo:table-column column-width="12.5%"/>
                                <fo:table-column column-width="12.5%"/>

                                <fo:table-body>
                                    <fo:table-row>
                                        <fo:table-cell>
                                            <fo:block><xsl:value-of select="/annualStatement/nominee[3]/name"/></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell>
                                            <fo:block><xsl:value-of select="/annualStatement/nominee[3]/relationship"/></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell>
                                            <fo:block><xsl:value-of select="/annualStatement/nominee[3]/share"/></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell>
                                            <fo:block><xsl:value-of select="/annualStatement/nominee[4]/name"/></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell>
                                            <fo:block><xsl:value-of select="/annualStatement/nominee[4]/relationship"/></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell>
                                            <fo:block><xsl:value-of select="/annualStatement/nominee[4]/share"/></fo:block>
                                        </fo:table-cell>
                                    </fo:table-row>
                                </fo:table-body>
                            </fo:table>

                        </fo:block>


                        <fo:block margin="10pt 0pt" margin-bottom="0pt" margin-top="0pt"
                                  border="1pt solid black" border-top="0" line-height="1"
                                  padding="5pt pt0" font-size="11" font-weight="600">

                            <fo:table>
                                <fo:table-column column-width="32%"/>
                                <fo:table-column column-width="17%"/>
                                <fo:table-column column-width="17%"/>
                                <fo:table-column column-width="17%"/>
                                <fo:table-column column-width="17%"/>

                                <fo:table-body>

                                    <fo:table-row>
                                        <fo:table-cell text-align="left">
                                            <fo:block>Account Particulars (Figures in Rupees)</fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right">
                                            <fo:block>Employee''s</fo:block>
                                            <fo:block>Contributions A/C</fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right">
                                            <fo:block>Employer''s</fo:block>
                                            <fo:block>Contributions A/C</fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right">
                                            <fo:block>VPF</fo:block>
                                            <fo:block>Contributions A/C</fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right" padding-right="10pt">
                                            <fo:block>Total</fo:block>
                                            <fo:block>(Rupees)</fo:block>
                                        </fo:table-cell>
                                    </fo:table-row>

                                </fo:table-body>
                            </fo:table>
                        </fo:block>



                        <fo:block margin="10pt 0pt" margin-bottom="0pt" margin-top="0pt"
                                  border="1pt solid black" border-top="0" line-height="1"
                                  padding="10pt pt0" font-size="11" font-weight="600">

                            <fo:table>
                                <fo:table-column column-width="32%"/>
                                <fo:table-column column-width="17%"/>
                                <fo:table-column column-width="17%"/>
                                <fo:table-column column-width="17%"/>
                                <fo:table-column column-width="17%"/>
                                <fo:table-body>

                                    <fo:table-row>
                                        <fo:table-cell text-align="left">
                                            <fo:block>Opening Balance as on <xsl:value-of select="yearOpeningDate"/></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right">
                                            <fo:block><xsl:value-of select="yearOpeningMemberContribution"/></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right">
                                            <fo:block><xsl:value-of select="yearOpeningCompanyContribution"/></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right">
                                            <fo:block><xsl:value-of select="yearOpeningVpfContribution"/></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right" padding-right="10pt">
                                            <fo:block><xsl:value-of select="yearOpeningTotalContribution"/></fo:block>
                                        </fo:table-cell>
                                    </fo:table-row>


                                </fo:table-body>
                            </fo:table>

                        </fo:block>

                        <fo:block margin="10pt 0pt" margin-bottom="0pt" margin-top="0pt"
                                  border="1pt solid black" border-top="0" line-height="1"
                                  padding="5pt pt0">

                            Additions:-

                        </fo:block>

                        <fo:block margin="10pt 0pt" margin-bottom="0pt" margin-top="0pt"
                                  border="1pt solid black" border-top="0" line-height="1"
                                  padding="5pt pt0">

                            <fo:table>
                                <fo:table-column column-width="32%"/>
                                <fo:table-column column-width="17%"/>
                                <fo:table-column column-width="17%"/>
                                <fo:table-column column-width="17%"/>
                                <fo:table-column column-width="17%"/>
                                <fo:table-body>

                                    <fo:table-row>
                                        <fo:table-cell text-align="left">
                                            <fo:block>Interest on Opening Balance</fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right">
                                            <fo:block><xsl:value-of select="interestOnYearOpeningMemberContribution"/></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right">
                                            <fo:block><xsl:value-of select="interestOnYearOpeningCompanyContribution"/></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right">
                                            <fo:block><xsl:value-of select="interestOnYearOpeningVpfContribution"/></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right" padding-right="10pt">
                                            <fo:block><xsl:value-of select="interestOnYearOpeningTotalContribution"/></fo:block>
                                        </fo:table-cell>
                                    </fo:table-row>

                                </fo:table-body>
                            </fo:table>

                        </fo:block>



                        <fo:block margin="10pt 0pt" margin-bottom="0pt" margin-top="0pt"
                                  border="1pt solid black" border-top="0" line-height="1"
                                  padding="5pt pt0">

                            <fo:table>
                                <fo:table-column column-width="32%"/>
                                <fo:table-column column-width="17%"/>
                                <fo:table-column column-width="17%"/>
                                <fo:table-column column-width="17%"/>
                                <fo:table-column column-width="17%"/>


                                <fo:table-body>

                                    <fo:table-row>
                                        <fo:table-cell text-align="left">
                                            <fo:block>Contributions during the year</fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right">
                                            <fo:block><xsl:value-of select="currentYearMemberContribution"/></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right">
                                            <fo:block><xsl:value-of select="currentYearCompanyContribution"/></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right">
                                            <fo:block><xsl:value-of select="currentYearVpfContribution"/></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right" padding-right="10pt">
                                            <fo:block><xsl:value-of select="currentYearTotalContribution"/></fo:block>
                                        </fo:table-cell>
                                    </fo:table-row>


                                </fo:table-body>
                            </fo:table>

                        </fo:block>



                        <fo:block margin="10pt 0pt" margin-bottom="0pt" margin-top="0pt"
                                  border="1pt solid black" border-top="0" line-height="1"
                                  padding="5pt pt0">

                            <fo:table>
                                <fo:table-column column-width="32%"/>
                                <fo:table-column column-width="17%"/>
                                <fo:table-column column-width="17%"/>
                                <fo:table-column column-width="17%"/>
                                <fo:table-column column-width="17%"/>

                                <fo:table-body>

                                    <fo:table-row>
                                        <fo:table-cell text-align="left">
                                            <fo:block>Interest on Contribution</fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right">
                                            <fo:block><xsl:value-of select="interestOnCurrentYearMemberContribution"/></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right">
                                            <fo:block><xsl:value-of select="interestOnCurrentYearCompanyContribution"/></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right">
                                            <fo:block><xsl:value-of select="interestOnCurrentYearVpfContribution"/></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right" padding-right="10pt">
                                            <fo:block><xsl:value-of select="interestOnCurrentYearTotalContribution"/></fo:block>
                                        </fo:table-cell>
                                    </fo:table-row>


                                </fo:table-body>
                            </fo:table>

                        </fo:block>


                        <fo:block margin="10pt 0pt" margin-bottom="0pt" margin-top="0pt"
                                  border="1pt solid black" border-top="0" line-height="1"
                                  padding="5pt pt0">

                            <fo:table>
                                <fo:table-column column-width="32%"/>
                                <fo:table-column column-width="17%"/>
                                <fo:table-column column-width="17%"/>
                                <fo:table-column column-width="17%"/>
                                <fo:table-column column-width="17%"/>


                                <fo:table-body>

                                    <fo:table-row>
                                        <fo:table-cell text-align="left">
                                            <fo:block>Transfer from Other Funds</fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right">
                                            <fo:block><xsl:value-of select="currentYearTransferInMemberContribution"/></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right">
                                            <fo:block><xsl:value-of select="currentYearTransferInCompanyContribution"/></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right">
                                            <fo:block><xsl:value-of select="currentYearTransferInVpfContribution"/></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right" padding-right="10pt">
                                            <fo:block><xsl:value-of select="currentYearTransferInTotalContribution"/></fo:block>
                                        </fo:table-cell>
                                    </fo:table-row>


                                </fo:table-body>
                            </fo:table>

                        </fo:block>


                        <fo:block margin="10pt 0pt" margin-bottom="0pt" margin-top="0pt"
                                  border="1pt solid black" border-top="0" line-height="1"
                                  padding="5pt pt0">

                            <fo:table>
                                <fo:table-column column-width="32%"/>
                                <fo:table-column column-width="17%"/>
                                <fo:table-column column-width="17%"/>
                                <fo:table-column column-width="17%"/>
                                <fo:table-column column-width="17%"/>



                                <fo:table-body>

                                    <fo:table-row>
                                        <fo:table-cell text-align="left">
                                            <fo:block>Interest on Transfer</fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right">
                                            <fo:block><xsl:value-of select="interestOnCurrentYearTransferInMemberContribution"/></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right">
                                            <fo:block><xsl:value-of select="interestOnCurrentYearTransferInCompanyContribution"/></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right">
                                            <fo:block><xsl:value-of select="interestOnCurrentYearTransferInVpfContribution"/></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right" padding-right="10pt">
                                            <fo:block><xsl:value-of select="interestOnCurrentYearTransferInTotalContribution"/></fo:block>
                                        </fo:table-cell>
                                    </fo:table-row>


                                </fo:table-body>
                            </fo:table>

                        </fo:block>


                        <fo:block margin="10pt 0pt" margin-bottom="0pt" margin-top="0pt"
                                  border="1pt solid black" border-top="0" line-height="1"
                                  padding="5pt pt0">

                            Less:-

                        </fo:block>



                        <fo:block margin="10pt 0pt" margin-bottom="0pt" margin-top="0pt"
                                  border="1pt solid black" border-top="0" line-height="1"
                                  padding="5pt pt0">

                            <fo:table>
                                <fo:table-column column-width="32%"/>
                                <fo:table-column column-width="17%"/>
                                <fo:table-column column-width="17%"/>
                                <fo:table-column column-width="17%"/>
                                <fo:table-column column-width="17%"/>


                                <fo:table-body>

                                    <fo:table-row>
                                        <fo:table-cell text-align="left">
                                            <fo:block>NRL Withdrawals during the year</fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right">
                                            <fo:block><xsl:value-of select="currentYearLoanWithdrawalMemberContribution"/></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right">
                                            <fo:block><xsl:value-of select="currentYearLoanWithdrawalCompanyContribution"/></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right">
                                            <fo:block><xsl:value-of select="currentYearLoanWithdrawalVpfContribution"/></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right" padding-right="10pt">
                                            <fo:block><xsl:value-of select="currentYearLoanWithdrawalTotalContribution"/></fo:block>
                                        </fo:table-cell>
                                    </fo:table-row>


                                </fo:table-body>
                            </fo:table>

                        </fo:block>


                        <fo:block margin="10pt 0pt" margin-bottom="0pt" margin-top="0pt"
                                  border="1pt solid black" border-top="0" line-height="1"
                                  padding="5pt pt0">

                            <fo:table>
                                <fo:table-column column-width="32%"/>
                                <fo:table-column column-width="17%"/>
                                <fo:table-column column-width="17%"/>
                                <fo:table-column column-width="17%"/>
                                <fo:table-column column-width="17%"/>




                                <fo:table-body>

                                    <fo:table-row>
                                        <fo:table-cell text-align="left">
                                            <fo:block>Interest on NRL</fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right">
                                            <fo:block><xsl:value-of select="interestOnCurrentYearLoanWithdrawalMemberContribution"/></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right">
                                            <fo:block><xsl:value-of select="interestOnCurrentYearLoanWithdrawalCompanyContribution"/></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right">
                                            <fo:block><xsl:value-of select="interestOnCurrentYearLoanWithdrawalVpfContribution"/></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right" padding-right="10pt">
                                            <fo:block><xsl:value-of select="interestOnCurrentYearLoanWithdrawalTotalContribution"/></fo:block>
                                        </fo:table-cell>
                                    </fo:table-row>


                                </fo:table-body>
                            </fo:table>

                        </fo:block>



                        <fo:block margin="10pt 0pt" margin-bottom="0pt" margin-top="0pt"
                                  border="1pt solid black" border-top="0" line-height="1"
                                  padding="10pt pt0" font-size="11" font-weight="600">

                            <fo:table>
                                <fo:table-column column-width="32%"/>
                                <fo:table-column column-width="17%"/>
                                <fo:table-column column-width="17%"/>
                                <fo:table-column column-width="17%"/>
                                <fo:table-column column-width="17%"/>



                                <fo:table-body>

                                    <fo:table-row>
                                        <fo:table-cell text-align="left">
                                            <fo:block>Closing Balance as on <xsl:value-of select="closingDate"/></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right">
                                            <fo:block><xsl:value-of select="totalMemberContribution"/></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right">
                                            <fo:block><xsl:value-of select="totalCompanyContribution"/></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right">
                                            <fo:block><xsl:value-of select="totalVpfContribution"/></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right" padding-right="10pt">
                                            <fo:block><xsl:value-of select="totalContribution"/></fo:block>
                                        </fo:table-cell>
                                    </fo:table-row>


                                </fo:table-body>
                            </fo:table>

                        </fo:block>


                        <fo:block margin="10pt 0pt" margin-bottom="0pt" margin-top="0pt"
                                  border="1pt solid black" border-top="0" line-height="1"
                                  padding="5pt" padding-left="0pt">

                            <fo:table>
                                <fo:table-column column-width="70%"/>
                                <fo:table-column column-width="30%"/>

                                <fo:table-body>

                                    <fo:table-row>

                                        <fo:table-cell>

                                            <fo:block>1.Interest @<fo:inline><xsl:value-of select="interestRate"/></fo:inline>% p.a. is calculated on monthly running
                                            balance as per PF Act &amp; Rules.</fo:block>

                                            <fo:block>2.EPS-1995 Contributions has been paid to the RPFC directly
                                            by the company.</fo:block>

                                            <fo:block>3.Error, if any should be reported within 30 days.</fo:block>

                                            <fo:block>4.Please refer <fo:inline font-weight="600">Annexure</fo:inline> (given below / attached) for during the
                                                Year''s Transactions.</fo:block>

                                        </fo:table-cell>
                                        <fo:table-cell>

                                            <fo:block margin="0" padding="0">
                                                <fo:block text-align="center">For Mahindra &amp; Mahindra Limited</fo:block>
                                                <fo:block text-align="center">Staff Provident Fund</fo:block>
                                                <fo:block margin-top="5pt" text-align="center">
                                                    <fo:external-graphic src="https://cleansecar-prod.s3.us-east-2.amazonaws.com/OndemandFiles/f2842707-9609-406f-8fab-d3f1ebf3d183.png"
                                                                         content-height="70pt"
                                                                         content-width="200pt"/>
                                                </fo:block>
                                                <fo:block text-align="center">Trustee</fo:block>
                                            </fo:block>


                                        </fo:table-cell>

                                    </fo:table-row>


                                </fo:table-body>
                            </fo:table>



                            <fo:block>
                                <fo:inline>Date:- <xsl:value-of select="date"/></fo:inline>
                            </fo:block>


                        </fo:block>



                    </fo:block>

            </fo:flow>
            </fo:page-sequence>




            <fo:page-sequence master-reference="annualAnnexure">
                <fo:flow flow-name="xsl-region-body" font-size="11pt" line-height="1.2"
                         font-family="Times-Roman" word-spacing="6pt" letter-spacing="1pt">

                    <fo:block>

                        <fo:table line-height="1">
                            <fo:table-column column-width="13%"/>
                            <fo:table-column column-width="87%"/>

                            <fo:table-body>
                                <fo:table-row>
                                    <fo:table-cell margin="0" padding="0">
                                        <fo:block margin="0" padding="0">
                                            <fo:external-graphic src="https://developerspftrust.coreintegra.com/images/mahindra_logo.png" content-height="100pt" content-width="100pt"/>
                                        </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell margin="0" padding="0">
                                        <fo:block margin="0" padding="0" text-align="center" line-height="1.2">
                                            <fo:block font-weight="600">MAHINDRA &amp; MAHINDRA LIMITED STAFF PROVIDENT FUND</fo:block>
                                            <fo:block>ANNEXURE TO ANNUAL STATEMENT AS ON <xsl:value-of select="closingDateWord"/></fo:block>
                                        </fo:block>
                                    </fo:table-cell>
                                </fo:table-row>
                            </fo:table-body>
                        </fo:table>
                        <!-- logo and address end -->
<fo:inline padding-left="650pt">(Figures in Rupees)</fo:inline>
                        <fo:block margin="10pt 0pt" margin-bottom="0pt" margin-top="0pt"
                                  border="1pt solid black" line-height="1" padding="5pt pt0">

                            <fo:inline>Employee Name : <xsl:value-of select="name"/></fo:inline>
                            <fo:inline padding-left="30pt">Token No. : <xsl:value-of select="tokenNumber"/></fo:inline>
                            <fo:inline padding-left="30pt">P.F.No. : <xsl:value-of select="pfNumber"/></fo:inline>
                            <fo:inline padding-left="30pt">Unit Code. : <xsl:value-of select="unitCode"/></fo:inline>
                            <fo:inline padding-left="30pt">Dept.Cd : <xsl:value-of select="deptCode"/></fo:inline>

                        </fo:block>



                        <fo:block margin="10pt 0pt" margin-bottom="0pt" margin-top="0pt"
                                  border="1pt solid black" border-top="0" text-align="center"
                                  line-height="1">

                            <fo:table line-height="1">
                                <fo:table-column column-width="12%" border-right="1pt solid black"/>
                                <fo:table-column column-width="22%" border-right="1pt solid black"/>
                                <fo:table-column column-width="22%" border-right="1pt solid black"/>
                                <fo:table-column column-width="22%" border-right="1pt solid black"/>
                                <fo:table-column column-width="22%"/>

                                <fo:table-body>
                                    <fo:table-row>
                                        <fo:table-cell padding="2pt 0pt">
                                            <fo:block>Month</fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell padding="2pt 0pt">
                                            <fo:block>Employee''s</fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell padding="2pt 0pt">
                                            <fo:block>Employer''s</fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell padding="2pt 0pt">
                                            <fo:block>VPF</fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell padding="2pt 0pt">
                                            <fo:block>Total</fo:block>
                                        </fo:table-cell>
                                    </fo:table-row>
                                </fo:table-body>
                            </fo:table>

                        </fo:block>



                        <fo:block margin="10pt 0pt" margin-bottom="0pt" margin-top="0pt"
                                  border="1pt solid black" border-top="0" text-align="center"
                                  line-height="1">

                            <fo:table line-height="1">

                                <fo:table-column column-width="12%" border-right="1pt solid black"/>
                                <fo:table-column column-width="22%" border-right="1pt solid black"/>
                                <fo:table-column column-width="22%" border-right="1pt solid black"/>
                                <fo:table-column column-width="22%" border-right="1pt solid black"/>
                                <fo:table-column column-width="22%"/>

                                <fo:table-body>
                                    <fo:table-row>
                                        <fo:table-cell>
                                            <fo:block></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell>

                                            <fo:table line-height="1">
                                                <fo:table-column column-width="50%"
                                                                 border-right="1pt solid black"/>
                                                <fo:table-column column-width="50%" />

                                                <fo:table-body>
                                                    <fo:table-row>
                                                        <fo:table-cell><fo:block>Contributions</fo:block></fo:table-cell>
                                                        <fo:table-cell><fo:block>Interest</fo:block></fo:table-cell>
                                                    </fo:table-row>
                                                </fo:table-body>
                                            </fo:table>

                                        </fo:table-cell>
                                        <fo:table-cell>

                                            <fo:table line-height="1">
                                                <fo:table-column column-width="50%"
                                                                 border-right="1pt solid black"/>
                                                <fo:table-column column-width="50%" />

                                                <fo:table-body>
                                                    <fo:table-row>
                                                        <fo:table-cell><fo:block>Contributions</fo:block></fo:table-cell>
                                                        <fo:table-cell><fo:block>Interest</fo:block></fo:table-cell>
                                                    </fo:table-row>
                                                </fo:table-body>
                                            </fo:table>

                                        </fo:table-cell>
                                        <fo:table-cell>

                                            <fo:table line-height="1">
                                                <fo:table-column column-width="50%"
                                                                 border-right="1pt solid black"/>
                                                <fo:table-column column-width="50%" />

                                                <fo:table-body>
                                                    <fo:table-row>
                                                        <fo:table-cell><fo:block>Contributions</fo:block></fo:table-cell>
                                                        <fo:table-cell><fo:block>Interest</fo:block></fo:table-cell>
                                                    </fo:table-row>
                                                </fo:table-body>
                                            </fo:table>

                                        </fo:table-cell>
                                        <fo:table-cell>

                                            <fo:table line-height="1">
                                                <fo:table-column column-width="50%"
                                                                 border-right="1pt solid black"/>
                                                <fo:table-column column-width="50%" />

                                                <fo:table-body>
                                                    <fo:table-row>
                                                        <fo:table-cell><fo:block>Contributions</fo:block></fo:table-cell>
                                                        <fo:table-cell><fo:block>Interest</fo:block></fo:table-cell>
                                                    </fo:table-row>
                                                </fo:table-body>
                                            </fo:table>

                                        </fo:table-cell>
                                    </fo:table-row>
                                </fo:table-body>

                            </fo:table>

                        </fo:block>


                        <xsl:for-each select="currentYearContributions">

                        <fo:block margin="10pt 0pt" margin-bottom="0pt" margin-top="0pt"
                                  border="1pt solid black" border-top="0" text-align="center"
                                  line-height="1">

                            <fo:table line-height="1">

                                <fo:table-column column-width="12%" border-right="1pt solid black"/>
                                <fo:table-column column-width="22%" border-right="1pt solid black"/>
                                <fo:table-column column-width="22%" border-right="1pt solid black"/>
                                <fo:table-column column-width="22%" border-right="1pt solid black"/>
                                <fo:table-column column-width="22%"/>

                                <fo:table-body>
                                    <fo:table-row>
                                        <fo:table-cell>
                                            <fo:block padding="3pt 0pt"><xsl:value-of select="monthName"/></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell>

                                            <fo:table line-height="1">
                                                <fo:table-column column-width="50%"
                                                                 border-right="1pt solid black"/>
                                                <fo:table-column column-width="50%" />

                                                <fo:table-body>
                                                    <fo:table-row>
                                                        <fo:table-cell><fo:block padding="3pt 0pt" text-align="right"><xsl:value-of select="memberContributionfr"/></fo:block></fo:table-cell>
                                                        <fo:table-cell><fo:block padding="3pt 0pt" text-align="right"><xsl:value-of select="interestOnMemberContributionfr"/></fo:block></fo:table-cell>
                                                    </fo:table-row>
                                                </fo:table-body>
                                            </fo:table>

                                        </fo:table-cell>
                                        <fo:table-cell>

                                            <fo:table line-height="1">
                                                <fo:table-column column-width="50%"
                                                                 border-right="1pt solid black"/>
                                                <fo:table-column column-width="50%" />

                                                <fo:table-body>
                                                    <fo:table-row>
                                                        <fo:table-cell><fo:block padding="3pt 0pt" text-align="right"><xsl:value-of select="companyContributionfr"/></fo:block></fo:table-cell>
                                                        <fo:table-cell><fo:block padding="3pt 0pt" text-align="right"><xsl:value-of select="interestOnCompanyContributionfr"/></fo:block></fo:table-cell>
                                                    </fo:table-row>
                                                </fo:table-body>
                                            </fo:table>

                                        </fo:table-cell>
                                        <fo:table-cell>

                                            <fo:table line-height="1">
                                                <fo:table-column column-width="50%"
                                                                 border-right="1pt solid black"/>
                                                <fo:table-column column-width="50%" />

                                                <fo:table-body>
                                                    <fo:table-row>
                                                        <fo:table-cell><fo:block padding="3pt 0pt" text-align="right"><xsl:value-of select="vpfContributionfr"/></fo:block></fo:table-cell>
                                                        <fo:table-cell><fo:block padding="3pt 0pt" text-align="right"><xsl:value-of select="interestOnVpfContributionfr"/></fo:block></fo:table-cell>
                                                    </fo:table-row>
                                                </fo:table-body>
                                            </fo:table>

                                        </fo:table-cell>
                                        <fo:table-cell>

                                            <fo:table line-height="1">
                                                <fo:table-column column-width="50%"
                                                                 border-right="1pt solid black"/>
                                                <fo:table-column column-width="50%" />

                                                <fo:table-body>
                                                    <fo:table-row>
                                                        <fo:table-cell><fo:block padding="3pt 0pt" text-align="right"><xsl:value-of select="totalContributionfr"/></fo:block></fo:table-cell>
                                                        <fo:table-cell><fo:block padding="3pt 0pt" text-align="right"><xsl:value-of select="totalInterestfr"/></fo:block></fo:table-cell>
                                                    </fo:table-row>
                                                </fo:table-body>
                                            </fo:table>

                                        </fo:table-cell>
                                    </fo:table-row>
                                </fo:table-body>

                            </fo:table>

                        </fo:block>

                        </xsl:for-each>



                        <fo:block margin="10pt 0pt" margin-bottom="0pt" margin-top="0pt"
                                  border="1pt solid black" border-top="0" text-align="center"
                                  line-height="1" font-weight="600">

                            <fo:table line-height="1">

                                <fo:table-column column-width="12%" border-right="1pt solid black"/>
                                <fo:table-column column-width="22%" border-right="1pt solid black"/>
                                <fo:table-column column-width="22%" border-right="1pt solid black"/>
                                <fo:table-column column-width="22%" border-right="1pt solid black"/>
                                <fo:table-column column-width="22%"/>

                                <fo:table-body>
                                    <fo:table-row>
                                        <fo:table-cell>
                                            <fo:block padding="3pt 0pt">Total</fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell>

                                            <fo:table line-height="1">
                                                <fo:table-column column-width="50%"
                                                                 border-right="1pt solid black"/>
                                                <fo:table-column column-width="50%" />

                                                <fo:table-body>
                                                    <fo:table-row>
                                                        <fo:table-cell><fo:block padding="3pt 0pt" text-align="right"><xsl:value-of select="currentYearContributionsTotal/memberContributionfr"/></fo:block></fo:table-cell>
                                                        <fo:table-cell><fo:block padding="3pt 0pt" text-align="right"><xsl:value-of select="currentYearContributionsTotal/interestOnMemberContributionfr"/></fo:block></fo:table-cell>
                                                    </fo:table-row>
                                                </fo:table-body>
                                            </fo:table>

                                        </fo:table-cell>
                                        <fo:table-cell>

                                            <fo:table line-height="1">
                                                <fo:table-column column-width="50%"
                                                                 border-right="1pt solid black"/>
                                                <fo:table-column column-width="50%" />

                                                <fo:table-body>
                                                    <fo:table-row>
                                                        <fo:table-cell><fo:block padding="3pt 0pt" text-align="right"><xsl:value-of select="currentYearContributionsTotal/companyContributionfr"/></fo:block></fo:table-cell>
                                                        <fo:table-cell><fo:block padding="3pt 0pt" text-align="right"><xsl:value-of select="currentYearContributionsTotal/interestOnCompanyContributionfr"/></fo:block></fo:table-cell>
                                                    </fo:table-row>
                                                </fo:table-body>
                                            </fo:table>

                                        </fo:table-cell>
                                        <fo:table-cell>

                                            <fo:table line-height="1">
                                                <fo:table-column column-width="50%"
                                                                 border-right="1pt solid black"/>
                                                <fo:table-column column-width="50%" />

                                                <fo:table-body>
                                                    <fo:table-row>
                                                        <fo:table-cell><fo:block padding="3pt 0pt" text-align="right"><xsl:value-of select="currentYearContributionsTotal/vpfContributionfr"/></fo:block></fo:table-cell>
                                                        <fo:table-cell><fo:block padding="3pt 0pt" text-align="right"><xsl:value-of select="currentYearContributionsTotal/interestOnVpfContributionfr"/></fo:block></fo:table-cell>
                                                    </fo:table-row>
                                                </fo:table-body>
                                            </fo:table>

                                        </fo:table-cell>
                                        <fo:table-cell>

                                            <fo:table line-height="1">
                                                <fo:table-column column-width="50%"
                                                                 border-right="1pt solid black"/>
                                                <fo:table-column column-width="50%" />

                                                <fo:table-body>
                                                    <fo:table-row>
                                                        <fo:table-cell><fo:block padding="3pt 0pt" text-align="right"><xsl:value-of select="currentYearContributionsTotal/totalContributionfr"/></fo:block></fo:table-cell>
                                                        <fo:table-cell><fo:block padding="3pt 0pt" text-align="right"><xsl:value-of select="currentYearContributionsTotal/totalInterestfr"/></fo:block></fo:table-cell>
                                                    </fo:table-row>
                                                </fo:table-body>
                                            </fo:table>

                                        </fo:table-cell>
                                    </fo:table-row>
                                </fo:table-body>

                            </fo:table>

                        </fo:block>



                        <fo:block margin="10pt" font-size="12"
                                  font-weight="600" text-align="center">
                            During the Year <xsl:value-of select="finantialPeriod"/> Transfer In and Loan Withdrawals
                        </fo:block>

                        <fo:block font-size="12" font-weight="600">Transfer In</fo:block>

                        <fo:block margin="10pt 0pt" margin-bottom="0pt" margin-top="0pt"
                                  border="1pt solid black" text-align="center"
                                  line-height="1" font-weight="600">

                            <fo:table line-height="1">
                                <fo:table-column column-width="25%" border-right="1pt solid black"/>
                                <fo:table-column column-width="25%" border-right="1pt solid black"/>
                                <fo:table-column column-width="25%" border-right="1pt solid black"/>
                                <fo:table-column column-width="25%"/>

                                <fo:table-body>
                                    <fo:table-row>
                                        <fo:table-cell padding="2pt 0pt">
                                            <fo:block>Date of Transfer</fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell padding="2pt 0pt">
                                            <fo:block>Employee''s A/C</fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell padding="2pt 0pt">
                                            <fo:block>Employer''s A/C</fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell padding="2pt 0pt">
                                            <fo:block>TOTAL</fo:block>
                                        </fo:table-cell>
                                    </fo:table-row>
                                </fo:table-body>
                            </fo:table>

                        </fo:block>

                        <fo:block margin="10pt 0pt" margin-bottom="0pt" margin-top="0pt"
                                  border="1pt solid black" border-top="0" text-align="center"
                                  line-height="1">

                            <fo:table line-height="1">
                                <fo:table-column column-width="25%" border-right="1pt solid black"/>
                                <fo:table-column column-width="25%" border-right="1pt solid black"/>
                                <fo:table-column column-width="25%" border-right="1pt solid black"/>
                                <fo:table-column column-width="25%"/>

                                <fo:table-body>


                                    <xsl:for-each select="transferInDetails">

                                    <fo:table-row>
                                        <fo:table-cell padding="2pt 0pt">
                                            <fo:block><xsl:value-of select="receiptAtDate"/></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell padding="2pt 0pt">
                                            <fo:block><xsl:value-of select="employeeContribution"/></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell padding="2pt 0pt">
                                            <fo:block><xsl:value-of select="employerContribution"/></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell padding="2pt 0pt">
                                            <fo:block><xsl:value-of select="total"/></fo:block>
                                        </fo:table-cell>
                                    </fo:table-row>

                                    </xsl:for-each>


                                    <fo:table-row>
                                        <fo:table-cell padding="2pt 0pt">
                                            <fo:block></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell padding="2pt 0pt">
                                            <fo:block text-align="left"></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell padding="2pt 0pt">
                                            <fo:block></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell padding="2pt 0pt">
                                            <fo:block></fo:block>
                                        </fo:table-cell>
                                    </fo:table-row>

                                </fo:table-body>
                            </fo:table>

                        </fo:block>


                        <fo:block font-size="12" margin-top="20pt"
                                  font-weight="600">Loan Withdrawals</fo:block>

                        <fo:block margin="10pt 0pt" margin-bottom="0pt" margin-top="0pt"
                                  border="1pt solid black" text-align="center"
                                  line-height="1" font-weight="600">

                            <fo:table line-height="1">
                                <fo:table-column column-width="10%" border-right="1pt solid black"/>
                                <fo:table-column column-width="20%" border-right="1pt solid black"/>
                                <fo:table-column column-width="10%" border-right="1pt solid black"/>
                                <fo:table-column column-width="15%" border-right="1pt solid black"/>
                                <fo:table-column column-width="15%" border-right="1pt solid black"/>
                                <fo:table-column column-width="15%" border-right="1pt solid black"/>
                                <fo:table-column column-width="15%"/>

                                <fo:table-body>
                                    <fo:table-row>
                                        <fo:table-cell padding="2pt 0pt">
                                            <fo:block>Loan Type</fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell padding="2pt 0pt">
                                            <fo:block>Description</fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell padding="2pt 0pt">
                                            <fo:block>Date</fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell padding="2pt 0pt">
                                            <fo:block>Employee''s A/C</fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell padding="2pt 0pt">
                                            <fo:block>Employer''s A/C</fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell padding="2pt 0pt">
                                            <fo:block>VPF A/C</fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell padding="2pt 0pt">
                                            <fo:block>TOTAL</fo:block>
                                        </fo:table-cell>
                                    </fo:table-row>
                                </fo:table-body>
                            </fo:table>

                        </fo:block>


                        <fo:block margin="10pt 0pt" margin-bottom="0pt" margin-top="0pt"
                                  border="1pt solid black" border-top="0" text-align="center"
                                  line-height="1">

                            <fo:table line-height="1">
                                <fo:table-column column-width="10%" border-right="1pt solid black"/>
                                <fo:table-column column-width="20%" border-right="1pt solid black"/>
                                <fo:table-column column-width="10%" border-right="1pt solid black"/>
                                <fo:table-column column-width="15%" border-right="1pt solid black"/>
                                <fo:table-column column-width="15%" border-right="1pt solid black"/>
                                <fo:table-column column-width="15%" border-right="1pt solid black"/>
                                <fo:table-column column-width="15%"/>

                                <fo:table-body>


                                    <xsl:for-each select="loanDetails">
                                    <fo:table-row>
                                        <fo:table-cell padding="2pt 0pt">
                                            <fo:block><xsl:value-of select="code"/></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell padding="2pt 0pt">
                                            <fo:block text-align="left"><xsl:value-of select="type"/></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell padding="2pt 0pt">
                                            <fo:block><xsl:value-of select="loanApprovalDate"/></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell padding="2pt 0pt">
                                            <fo:block><xsl:value-of select="employeeContribution"/></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell padding="2pt 0pt">
                                            <fo:block><xsl:value-of select="employerContribution"/></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell padding="2pt 0pt">
                                            <fo:block><xsl:value-of select="vpfContribution"/></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell padding="2pt 0pt">
                                            <fo:block><xsl:value-of select="totalContribution"/></fo:block>
                                        </fo:table-cell>
                                    </fo:table-row>
                                    </xsl:for-each>


                                    <fo:table-row>
                                        <fo:table-cell padding="2pt 0pt">
                                            <fo:block></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell padding="2pt 0pt">
                                            <fo:block text-align="left"></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell padding="2pt 0pt">
                                            <fo:block></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell padding="2pt 0pt">
                                            <fo:block></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell padding="2pt 0pt">
                                            <fo:block></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell padding="2pt 0pt">
                                            <fo:block></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell padding="2pt 0pt">
                                            <fo:block></fo:block>
                                        </fo:table-cell>
                                    </fo:table-row>


                                </fo:table-body>
                            </fo:table>

                        </fo:block>

                    </fo:block>

                </fo:flow>
            </fo:page-sequence>







        </fo:root>
    </xsl:template>

</xsl:stylesheet>
', 'ANNUAL_STATEMENT', 2);

insert into pdf_document_design(created_at, entity_id, is_active, updated_at, document, document_type,
                                tenant_id)
VALUES (sysdate(), '', 1, sysdate(), '<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.1" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:fo="http://www.w3.org/1999/XSL/Format" exclude-result-prefixes="fo">

    <xsl:template match="loanHistorySheet">
        <fo:root xmlns:fo="http://www.w3.org/1999/XSL/Format">

            <fo:layout-master-set>
                <fo:simple-page-master master-name="simpleA4"
                                       page-width="29.7cm" page-height="21cm"
                                       margin-top="0.3cm" margin-bottom="0.3cm"
                                       margin-left="1cm" margin-right="1cm"
                                       >
                    <fo:region-body/>

                </fo:simple-page-master>
            </fo:layout-master-set>


            <fo:page-sequence master-reference="simpleA4">
                <fo:flow flow-name="xsl-region-body" font-size="11pt" line-height="1.2"
                         font-family="Times-Roman" word-spacing="6pt" letter-spacing="1pt">

                    <fo:block padding="10pt"
                              padding-top="0pt" padding-bottom="5pt">

                        <fo:table line-height="1" margin="0pt 5pt">
                            <fo:table-column column-width="13%"/>
                            <fo:table-column column-width="87%"/>

                            <fo:table-body>
                                <fo:table-row>
                                    <fo:table-cell margin="0" padding="0">
                                        <fo:block margin="0" padding="0">
                                            <fo:external-graphic src="https://developerspftrust.coreintegra.com/images/mahindra_logo.png" content-height="100pt" content-width="100pt"/>
                                        </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell margin="0" padding="0">
                                        <fo:block margin="0" padding="0" text-align="center" line-height="1.2">
                                            <fo:block font-weight="600">MAHINDRA &amp; MAHINDRA LIMITED STAFF PROVIDENT FUND</fo:block>
                                            <fo:block>NON REFUNDABLE WITHDRAWAL WORKSHEET</fo:block>
                                        </fo:block>
                                    </fo:table-cell>
                                </fo:table-row>
                            </fo:table-body>
                        </fo:table>

                        <!-- logo and address end -->

                        <fo:block border-bottom="1pt dashed red" padding-bottom="5pt">

                            <fo:table line-height="1">
                                <fo:table-column column-width="40%" />
                                <fo:table-column column-width="20%" />
                                <fo:table-column column-width="20%" />
                                <fo:table-column column-width="20%" />

                                <fo:table-body>
                                    <fo:table-row>
                                        <fo:table-cell>
                                            <fo:block><fo:inline>Employee Name : <xsl:value-of select="name"/></fo:inline></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell>
                                            <fo:block><fo:inline>Token No. : <xsl:value-of select="tokenNumber"/></fo:inline></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell>
                                            <fo:block><fo:inline>P.F.No. : <xsl:value-of select="pfNumber"/></fo:inline></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell>
                                            <fo:block><fo:inline>Unit Code. : <xsl:value-of select="unitCode"/></fo:inline></fo:block>
                                        </fo:table-cell>
                                    </fo:table-row>
                                </fo:table-body>
                            </fo:table>

                            <fo:table line-height="1" margin-top="5pt">
                                <fo:table-column column-width="40%" />
                                <fo:table-column column-width="30%"/>
                                <fo:table-column column-width="30%" />

                                <fo:table-body>
                                    <fo:table-row>
                                        <fo:table-cell>
                                            <fo:block><fo:inline>Dept. Name : <xsl:value-of select="department"/></fo:inline></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell>
                                            <fo:block><fo:inline>Application No. : <xsl:value-of select="applicationNo"/></fo:inline></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell>
                                            <fo:block><fo:inline>Last Recovery Date : <xsl:value-of select="lastRecoveryDate"/></fo:inline></fo:block>
                                        </fo:table-cell>
                                    </fo:table-row>
                                </fo:table-body>
                            </fo:table>


                            <fo:table line-height="1"  margin-top="5pt">
                                <fo:table-column column-width="40%" />
                                <fo:table-column column-width="30%"/>
                                <fo:table-column column-width="30%" />

                                <fo:table-body>
                                    <fo:table-row>
                                        <fo:table-cell>
                                            <fo:block><fo:inline>Date of Birth. : <xsl:value-of select="dateOfBirth"/></fo:inline></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell>
                                            <fo:block></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell>
                                            <fo:block><fo:inline>Date of Joining PF. : <xsl:value-of select="dateOfJoiningPf"/></fo:inline></fo:block>
                                        </fo:table-cell>
                                    </fo:table-row>
                                </fo:table-body>
                            </fo:table>


                            <fo:table line-height="1"  margin-top="5pt">
                                <fo:table-column column-width="40%" />
                                <fo:table-column column-width="30%"/>
                                <fo:table-column column-width="30%" />

                                <fo:table-body>
                                    <fo:table-row>
                                        <fo:table-cell>
                                            <fo:block><fo:inline>Date. of Joining Prior : <xsl:value-of select="dateOfJoiningPrior"/></fo:inline></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell>
                                            <fo:block><fo:inline>Application Date. : <xsl:value-of select="applicationDate"/></fo:inline></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell>
                                            <fo:block><fo:inline>PF Base Salary : <xsl:value-of select="pfBase"/></fo:inline></fo:block>
                                        </fo:table-cell>
                                    </fo:table-row>
                                </fo:table-body>
                            </fo:table>

                        </fo:block>


                        <fo:block margin-top="10pt">

                            <fo:table line-height="1"  margin-top="10pt">
                                <fo:table-column column-width="25%" />
                                <fo:table-column column-width="15%" />
                                <fo:table-column column-width="30%" />
                                <fo:table-column column-width="10%" />
                                <fo:table-column column-width="20%" />

                                <fo:table-body>
                                    <fo:table-row>
                                        <fo:table-cell>
                                            <fo:block></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell>
                                            <fo:block font-weight="600"><fo:inline>Loan Code</fo:inline></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell>
                                            <fo:block font-weight="600"><fo:inline>Loan Type</fo:inline></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell>
                                            <fo:block font-weight="600"><fo:inline>Loan Date</fo:inline></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell>
                                            <fo:block text-align="right"
                                                      font-weight="600"><fo:inline>Loan Amount</fo:inline></fo:block>
                                        </fo:table-cell>
                                    </fo:table-row>


                                    <xsl:for-each select="loan">
                                        <fo:table-row>
                                            <fo:table-cell>
                                                <fo:block padding-top="10pt">NRL Loan History</fo:block>
                                            </fo:table-cell>
                                            <fo:table-cell>
                                                <fo:block padding-top="10pt"><fo:inline><xsl:value-of select="loanCode"/></fo:inline></fo:block>
                                            </fo:table-cell>
                                            <fo:table-cell>
                                                <fo:block padding-top="10pt"><fo:inline><xsl:value-of select="loanType"/></fo:inline></fo:block>
                                            </fo:table-cell>
                                            <fo:table-cell>
                                                <fo:block padding-top="10pt"><fo:inline><xsl:value-of select="date"/></fo:inline></fo:block>
                                            </fo:table-cell>
                                            <fo:table-cell>
                                                <fo:block text-align="right"
                                                          padding-top="10pt"><fo:inline><xsl:value-of select="amount"/></fo:inline></fo:block>
                                            </fo:table-cell>
                                        </fo:table-row>
                                    </xsl:for-each>


                                </fo:table-body>
                            </fo:table>


                        </fo:block>




                    </fo:block>

            </fo:flow>
            </fo:page-sequence>


        </fo:root>
    </xsl:template>

</xsl:stylesheet>
', 'LOAN_HISTORY', 2);

insert into pdf_document_design(created_at, entity_id, is_active, updated_at, document, document_type,
                                tenant_id)
VALUES (sysdate(), '', 1, sysdate(), '<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.1" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:fo="http://www.w3.org/1999/XSL/Format" exclude-result-prefixes="fo">

    <xsl:template match="loan">
        <fo:root xmlns:fo="http://www.w3.org/1999/XSL/Format">

            <fo:layout-master-set>
                <fo:simple-page-master master-name="simpleA4"
                                       page-width="29.7cm" page-height="21cm"
                                       margin-top="0.3cm" margin-bottom="0.3cm"
                                       margin-left="1cm" margin-right="1cm"
                                       >
                    <fo:region-body/>

                </fo:simple-page-master>


                <fo:simple-page-master master-name="loanHistory"
                                       page-width="29.7cm" page-height="21cm"
                                       margin-top="0.3cm" margin-bottom="0.3cm"
                                       margin-left="1cm" margin-right="1cm">
                    <fo:region-body/>

                </fo:simple-page-master>


            </fo:layout-master-set>


            <fo:page-sequence master-reference="simpleA4">
                <fo:flow flow-name="xsl-region-body" font-size="11pt" line-height="1.2"
                         font-family="Times-Roman" word-spacing="6pt" letter-spacing="1pt">

                    <fo:block padding="10pt"
                              padding-top="0pt" padding-bottom="5pt"
                              border="1pt solid red">

                        <fo:table line-height="1" margin="0pt 5pt">
                            <fo:table-column column-width="13%"/>
                            <fo:table-column column-width="87%"/>

                            <fo:table-body>
                                <fo:table-row>
                                    <fo:table-cell margin="0" padding="0">
                                        <fo:block margin="0" padding="0">
                                            <fo:external-graphic src="https://developerspftrust.coreintegra.com/images/mahindra_logo.png" content-height="100pt" content-width="100pt"/>
                                        </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell margin="0" padding="0">
                                        <fo:block margin="0" padding="0" text-align="center" line-height="1.2">
                                            <fo:block font-weight="600">MAHINDRA &amp; MAHINDRA LIMITED STAFF PROVIDENT FUND</fo:block>
                                            <fo:block>NON REFUNDABLE WITHDRAWAL WORKSHEET</fo:block>
                                        </fo:block>
                                    </fo:table-cell>
                                </fo:table-row>
                            </fo:table-body>
                        </fo:table>

                        <!-- logo and address end -->

                        <fo:block border-bottom="1pt dashed red" padding-bottom="5pt">

                            <fo:table line-height="1">
                                <fo:table-column column-width="40%" />
                                <fo:table-column column-width="20%" />
                                <fo:table-column column-width="20%" />
                                <fo:table-column column-width="20%" />

                                <fo:table-body>
                                    <fo:table-row>
                                        <fo:table-cell>
                                            <fo:block><fo:inline>Employee Name : <xsl:value-of select="name"/></fo:inline></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell>
                                            <fo:block><fo:inline>Token No. : <xsl:value-of select="tokenNumber"/></fo:inline></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell>
                                            <fo:block><fo:inline>P.F.No. : <xsl:value-of select="pfNumber"/></fo:inline></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell>
                                            <fo:block><fo:inline>Unit Code. : <xsl:value-of select="unitCode"/></fo:inline></fo:block>
                                        </fo:table-cell>
                                    </fo:table-row>
                                </fo:table-body>
                            </fo:table>

                            <fo:table line-height="1" margin-top="5pt">
                                <fo:table-column column-width="40%" />
                                <fo:table-column column-width="30%"/>
                                <fo:table-column column-width="30%" />

                                <fo:table-body>
                                    <fo:table-row>
                                        <fo:table-cell>
                                            <fo:block><fo:inline>Dept. Name : <xsl:value-of select="department"/></fo:inline></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell>
                                            <fo:block><fo:inline>Application No. : <xsl:value-of select="applicationNumber"/></fo:inline></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell>
                                            <fo:block><fo:inline>Last Recovery Date : <xsl:value-of select="lastRecoveryDate"/></fo:inline></fo:block>
                                        </fo:table-cell>
                                    </fo:table-row>
                                </fo:table-body>
                            </fo:table>


                            <fo:table line-height="1"  margin-top="5pt">
                                <fo:table-column column-width="40%" />
                                <fo:table-column column-width="30%"/>
                                <fo:table-column column-width="30%" />

                                <fo:table-body>
                                    <fo:table-row>
                                        <fo:table-cell>
                                            <fo:block><fo:inline>Date of Birth. : <xsl:value-of select="dateOfBirth"/></fo:inline></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell>
                                            <fo:block></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell>
                                            <fo:block><fo:inline>Date of Joining PF. : <xsl:value-of select="dateOfJoiningPf"/></fo:inline></fo:block>
                                        </fo:table-cell>
                                    </fo:table-row>
                                </fo:table-body>
                            </fo:table>


                            <fo:table line-height="1"  margin-top="5pt">
                                <fo:table-column column-width="40%" />
                                <fo:table-column column-width="30%"/>
                                <fo:table-column column-width="30%" />

                                <fo:table-body>
                                    <fo:table-row>
                                        <fo:table-cell>
                                            <fo:block><fo:inline>Date. of Joining Prior : <xsl:value-of select="dateOfJoiningPrior"/></fo:inline></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell>
                                            <fo:block><fo:inline>Application Date. : <xsl:value-of select="applicationDate"/></fo:inline></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell>
                                            <fo:block><fo:inline>PF Base Salary : <xsl:value-of select="pfBase"/></fo:inline></fo:block>
                                        </fo:table-cell>
                                    </fo:table-row>
                                </fo:table-body>
                            </fo:table>

                        </fo:block>


                        <fo:block>

                            <fo:table line-height="1"  margin-top="5pt">
                                <fo:table-column column-width="30%" />
                                <fo:table-column column-width="70%"/>

                                <fo:table-body>
                                    <fo:table-row>
                                        <fo:table-cell>
                                            <fo:block><fo:inline>Ln Code : <xsl:value-of select="loanCode"/></fo:inline></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell>
                                            <fo:block>
                                                <fo:inline>Ln Type : <xsl:value-of select="loanType"/></fo:inline>
                                            </fo:block>
                                        </fo:table-cell>
                                    </fo:table-row>
                                </fo:table-body>
                            </fo:table>


                            <fo:table line-height="1"  margin-top="5pt">
                                <fo:table-column column-width="30%"/>
                                <fo:table-column column-width="70%"/>

                                <fo:table-body>
                                    <fo:table-row>
                                        <fo:table-cell>
                                            <fo:block><fo:inline>Loan Amount Applied :</fo:inline></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell>
                                            <fo:block>
                                                <fo:inline><xsl:value-of select="appliedAmount"/></fo:inline>
                                            </fo:block>
                                        </fo:table-cell>
                                    </fo:table-row>
                                </fo:table-body>
                            </fo:table>


                            <fo:table line-height="1"  margin-top="5pt">
                                <fo:table-column column-width="30%"/>
                                <fo:table-column column-width="70%"/>

                                <fo:table-body>
                                    <fo:table-row>
                                        <fo:table-cell>
                                            <fo:block><fo:inline>Cost Involved :</fo:inline></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell>
                                            <fo:block>
                                                <fo:inline><xsl:value-of select="cost"/></fo:inline>
                                            </fo:block>
                                        </fo:table-cell>
                                    </fo:table-row>
                                </fo:table-body>
                            </fo:table>

                        </fo:block>



                        <fo:block line-height="1" margin-top="5pt" font-size="11" font-weight="600">

                            <fo:table>
                                <fo:table-column column-width="40%"/>
                                <fo:table-column column-width="15%"/>
                                <fo:table-column column-width="15%"/>
                                <fo:table-column column-width="15%"/>
                                <fo:table-column column-width="15%"/>

                                <fo:table-body>

                                    <fo:table-row>
                                        <fo:table-cell text-align="left">
                                            <fo:block>Account Particulars (Figures in Rupees)</fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right">
                                            <fo:block>Mem Cont</fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right">
                                            <fo:block>Empr Cont</fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right">
                                            <fo:block>Vpf Cont</fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right">
                                            <fo:block>Total Rs.</fo:block>
                                        </fo:table-cell>
                                    </fo:table-row>

                                </fo:table-body>
                            </fo:table>
                        </fo:block>

                        <fo:block margin-top="5pt">Additions: </fo:block>

                        <fo:block margin-top="2pt">

                            <fo:table>
                                <fo:table-column column-width="40%" />
                                <fo:table-column column-width="15%"/>
                                <fo:table-column column-width="15%"/>
                                <fo:table-column column-width="15%"/>
                                <fo:table-column column-width="15%"/>

                                <fo:table-body>

                                    <fo:table-row>
                                        <fo:table-cell text-align="left">
                                            <fo:block>Opening Balance as on <fo:inline><xsl:value-of select="yearOpeningDate"/></fo:inline></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right">
                                            <fo:block><xsl:value-of select="yearOpeningMemberContribution"/></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right">
                                            <fo:block><xsl:value-of select="yearOpeningCompanyContribution"/></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right">
                                            <fo:block><xsl:value-of select="yearOpeningVpfContribution"/></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right">
                                            <fo:block><xsl:value-of select="yearOpeningTotalContribution"/></fo:block>
                                        </fo:table-cell>
                                    </fo:table-row>

                                    <fo:table-row>
                                        <fo:table-cell text-align="left" padding-top="2pt">
                                            <fo:block>Contributions during the year</fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right" padding-top="2pt">
                                            <fo:block><xsl:value-of select="currentYearMemberContribution"/></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right" padding-top="2pt">
                                            <fo:block><xsl:value-of select="currentYearCompanyContribution"/></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right" padding-top="2pt">
                                            <fo:block><xsl:value-of select="currentYearVpfContribution"/></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right" padding-top="2pt">
                                            <fo:block><xsl:value-of select="currentYearTotalContribution"/></fo:block>
                                        </fo:table-cell>
                                    </fo:table-row>

                                    <fo:table-row>
                                        <fo:table-cell text-align="left" padding-top="2pt">
                                            <fo:block>Transfer from Other Funds</fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right" padding-top="2pt">
                                            <fo:block><xsl:value-of select="currentYearTiMemberContribution"/></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right" padding-top="2pt">
                                            <fo:block><xsl:value-of select="currentYearTiCompanyContribution"/></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right" padding-top="2pt">
                                            <fo:block><xsl:value-of select="currentYearTiVpfContribution"/></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right" padding-top="2pt">
                                            <fo:block><xsl:value-of select="currentYearTiTotalContribution"/></fo:block>
                                        </fo:table-cell>
                                    </fo:table-row>

                                </fo:table-body>
                            </fo:table>

                        </fo:block>

                        <fo:block margin-top="2pt">Less: </fo:block>


                        <fo:block margin-top="2pt">

                            <fo:table>
                                <fo:table-column column-width="40%" />
                                <fo:table-column column-width="15%"/>
                                <fo:table-column column-width="15%"/>
                                <fo:table-column column-width="15%"/>
                                <fo:table-column column-width="15%"/>

                                <fo:table-body>

                                    <fo:table-row>
                                        <fo:table-cell text-align="left">
                                            <fo:block>NRL Withdrawals during the year</fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right">
                                            <fo:block><xsl:value-of select="currentYearLwMemberContribution"/></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right">
                                            <fo:block><xsl:value-of select="currentYearLwCompanyContribution"/></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right">
                                            <fo:block><xsl:value-of select="currentYearLwVpfContribution"/></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right">
                                            <fo:block><xsl:value-of select="currentYearLwTotalContribution"/></fo:block>
                                        </fo:table-cell>
                                    </fo:table-row>

                                </fo:table-body>
                            </fo:table>

                        </fo:block>

                        <fo:block margin-top="2pt" padding="5pt 0pt">

                            <fo:table>
                                <fo:table-column column-width="40%"/>
                                <fo:table-column column-width="15%"/>
                                <fo:table-column column-width="15%"/>
                                <fo:table-column column-width="15%"/>
                                <fo:table-column column-width="15%"/>

                                <fo:table-body>

                                    <fo:table-row>
                                        <fo:table-cell text-align="left">
                                            <fo:block>Total</fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right">
                                            <fo:block><xsl:value-of select="totalMemberContribution"/></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right">
                                            <fo:block><xsl:value-of select="totalCompanyContribution"/></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right">
                                            <fo:block><xsl:value-of select="totalVpfContribution"/></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right">
                                            <fo:block><xsl:value-of select="totalContribution"/></fo:block>
                                        </fo:table-cell>
                                    </fo:table-row>

                                </fo:table-body>
                            </fo:table>

                        </fo:block>


                        <fo:block margin-top="0pt" padding="2pt 0pt">

                            <fo:table>
                                <fo:table-column column-width="40%"/>
                                <fo:table-column column-width="15%"/>
                                <fo:table-column column-width="15%"/>
                                <fo:table-column column-width="15%"/>
                                <fo:table-column column-width="15%"/>

                                <fo:table-body>

                                    <xsl:if test="loanCode = ''01'' or loanCode = ''13'' or loanCode = ''11'' or loanCode = ''12''">
                                    <fo:table-row>
                                        <fo:table-cell text-align="left">
                                            <fo:block>Less: Minimum Balance Retained</fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right">
                                            <fo:block>(1000)</fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right">
                                            <fo:block>(1000)</fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right">
                                            <fo:block></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right">
                                            <fo:block></fo:block>
                                        </fo:table-cell>>
                                    </fo:table-row>
                                    </xsl:if>

                                    <fo:table-row>
                                        <fo:table-cell text-align="left">
                                            <fo:block>PF Net Amount Available</fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right">
                                            <fo:block><xsl:value-of select="netMemberContribution"/></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right">
                                            <fo:block><xsl:value-of select="netCompanyContribution"/></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right">
                                            <fo:block><xsl:value-of select="netVpfContribution"/></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right">
                                            <fo:block><xsl:value-of select="netContribution"/></fo:block>
                                        </fo:table-cell>
                                    </fo:table-row>

                                    <fo:table-row>
                                        <fo:table-cell text-align="left">
                                            <fo:block>Withdrawal Amount</fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right">
                                            <fo:block><xsl:value-of select="loanAmountOnMember"/></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right">
                                            <fo:block><xsl:value-of select="loanAmountOnCompany"/></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right">
                                            <fo:block><xsl:value-of select="loanAmountOnVpf"/></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right">
                                            <fo:block><xsl:value-of select="totalLoanAmount"/></fo:block>
                                        </fo:table-cell>
                                    </fo:table-row>

                                </fo:table-body>
                            </fo:table>

                        </fo:block>

                        <fo:block margin-top="10pt" font-weight="600">WITHDRAWAL ELIGIBILITY </fo:block>

                        <fo:block>

                            <fo:table line-height="1"  margin-top="5pt">
                                <fo:table-column  />
                                <fo:table-column />
                                <fo:table-column  />

                                <fo:table-body>
                                    <fo:table-row>
                                        <fo:table-cell>
                                            <fo:block><fo:inline>No.of years membership : <xsl:value-of select="noOfYearsOfMembership"/></fo:inline></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell>
                                            <fo:block><fo:inline>Minimum Required : <xsl:value-of select="minimumRequiredYears"/></fo:inline></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell>
                                            <fo:block><fo:inline>Actual : <xsl:value-of select="actualNoOfYears"/></fo:inline></fo:block>
                                        </fo:table-cell>
                                    </fo:table-row>
                                </fo:table-body>
                            </fo:table>


                            <fo:table line-height="1"  margin-top="5pt">
                                <fo:table-column  />
                                <fo:table-column />

                                <fo:table-body>
                                    <fo:table-row>
                                        <fo:table-cell>
                                            <fo:block><fo:inline>Marriage / Education Loan Maximum permitted : 3 time</fo:inline></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell>
                                            <fo:block><fo:inline>Already Availed : <xsl:value-of select="count"/></fo:inline></fo:block>
                                        </fo:table-cell>
                                    </fo:table-row>
                                </fo:table-body>
                            </fo:table>


                        </fo:block>

                        <fo:block margin-top="5pt">Least of the following Amounts :-</fo:block>

                        <fo:block>
                            <fo:table line-height="1"  margin-top="5pt">
                                <fo:table-column  />
                                <fo:table-column />
                                <fo:table-column />
                                <fo:table-column />

                                <fo:table-body>
                                    <fo:table-row>
                                        <fo:table-cell>
                                            <fo:block></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell>
                                            <fo:block>1) Month Salary(For(<fo:inline><xsl:value-of select="salaryMonths"/></fo:inline>) Month)</fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell>
                                            <fo:block text-align="right"><xsl:value-of select="loanAmountBasedOnSalary"/></fo:block>
                                        </fo:table-cell>
                                    </fo:table-row>
                                    <fo:table-row>
                                        <fo:table-cell>
                                            <fo:block></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell>
                                        <xsl:if test="loanCode=''98''">
                                            <fo:block>2) PF Accumulated Balance(<fo:inline><xsl:value-of select="totalPfBalance"/></fo:inline>%)</fo:block>
                                            </xsl:if>
                                            <xsl:if test="loanCode=''99'' or loanCode = ''01'' or loanCode = ''13'' or loanCode = ''11'' or loanCode = ''12'' or loanCode = ''02'' or loanCode = ''03'' or loanCode = ''04'' or loanCode =  ''08'' or loanCode = ''14'' or loanCode = ''10''">
                                            <fo:block>2) PF Accumulated Balance</fo:block>
                                            </xsl:if>>
                                        </fo:table-cell>
                                        <fo:table-cell>
                                            <fo:block text-align="right"><xsl:value-of select="loanAmountBasedOnPf"/></fo:block>
                                        </fo:table-cell>
                                    </fo:table-row>
                                    <fo:table-row>
                                        <fo:table-cell>
                                            <fo:block></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell>
                                            <fo:block>3) Applied For</fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell>
                                            <fo:block text-align="right"><xsl:value-of select="appliedAmount"/></fo:block>
                                        </fo:table-cell>
                                    </fo:table-row>
                                    <fo:table-row>
                                        <fo:table-cell>
                                            <fo:block></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell>
                                            <fo:block>1) Cost involved</fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell>
                                            <fo:block text-align="right"><xsl:value-of select="cost"/></fo:block>
                                        </fo:table-cell>
                                    </fo:table-row>
                                    <fo:table-row>
                                        <fo:table-cell>
                                            <fo:block></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell>
                                            <fo:block font-weight="600">ELIGIBLE AMOUNT : </fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell>
                                            <fo:block font-weight="600" text-align="right"><xsl:value-of select="eligibleAmount"/></fo:block>
                                        </fo:table-cell>
                                    </fo:table-row>

                                </fo:table-body>
                            </fo:table>

                        </fo:block>


                        <fo:block>
                            <fo:table line-height="1"  margin-top="20pt">
                                <fo:table-column  />
                                <fo:table-column />
                                <fo:table-body>
                                    <fo:table-row>
                                        <fo:table-cell>
                                            <fo:block>
                                                <fo:inline>User: <xsl:value-of select="createdBy"/></fo:inline>
                                                <fo:inline padding-left="20pt">Date: <xsl:value-of select="approvedDate"/></fo:inline>
                                            </fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell>
                                            <fo:block>
                                                <fo:block text-align="center">For Mahindra &amp; Mahindra Limited</fo:block>
                                                <fo:block text-align="center">Staff Provident Fund</fo:block>
                                                 <fo:block text-align="center" margin-top="35pt">Trustee</fo:block>
                                                      <!--  <fo:block margin-top="05pt" text-align="center">
                                                <fo:external-graphic src="https://cleansecar-prod.s3.us-east-2.amazonaws.com/OndemandFiles/f2842707-9609-406f-8fab-d3f1ebf3d183.png"
                                                                         content-height="70pt"
                                                                         content-width="70pt"/>

                                               </fo:block> -->
                                                <!-- <fo:block text-align="center" >Trustee</fo:block> -->
                                            </fo:block>
<!-- margin-top="00pt"-->
                                        </fo:table-cell>
                                    </fo:table-row>
                                </fo:table-body>
                            </fo:table>

                        </fo:block>

                    </fo:block>

            </fo:flow>
            </fo:page-sequence>

            <fo:page-sequence master-reference="loanHistory">
                <fo:flow flow-name="xsl-region-body" font-size="11pt" line-height="1.2"
                         font-family="Times-Roman" word-spacing="6pt" letter-spacing="1pt">

                    <fo:block padding="10pt"
                              padding-top="0pt" padding-bottom="5pt">

                        <fo:table line-height="1" margin="0pt 5pt">
                            <fo:table-column column-width="13%"/>
                            <fo:table-column column-width="87%"/>

                            <fo:table-body>
                                <fo:table-row>
                                    <fo:table-cell margin="0" padding="0">
                                        <fo:block margin="0" padding="0">
                                            <fo:external-graphic src="https://developerspftrust.coreintegra.com/images/mahindra_logo.png" content-height="100pt" content-width="100pt"/>
                                        </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell margin="0" padding="0">
                                        <fo:block margin="0" padding="0" text-align="center" line-height="1.2">
                                            <fo:block font-weight="600">MAHINDRA &amp; MAHINDRA LIMITED STAFF PROVIDENT FUND</fo:block>
                                            <fo:block>NON REFUNDABLE WITHDRAWAL WORKSHEET</fo:block>
                                        </fo:block>
                                    </fo:table-cell>
                                </fo:table-row>
                            </fo:table-body>
                        </fo:table>

                        <!-- logo and address end -->

                        <fo:block border-bottom="1pt dashed red" padding-bottom="5pt">

                            <fo:table line-height="1">
                                <fo:table-column column-width="40%" />
                                <fo:table-column column-width="20%" />
                                <fo:table-column column-width="20%" />
                                <fo:table-column column-width="20%" />

                                <fo:table-body>
                                    <fo:table-row>
                                        <fo:table-cell>
                                            <fo:block><fo:inline>Employee Name : <xsl:value-of select="name"/></fo:inline></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell>
                                            <fo:block><fo:inline>Token No. : <xsl:value-of select="tokenNumber"/></fo:inline></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell>
                                            <fo:block><fo:inline>P.F.No. : <xsl:value-of select="pfNumber"/></fo:inline></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell>
                                            <fo:block><fo:inline>Unit Code. : <xsl:value-of select="unitCode"/></fo:inline></fo:block>
                                        </fo:table-cell>
                                    </fo:table-row>
                                </fo:table-body>
                            </fo:table>

                            <fo:table line-height="1" margin-top="5pt">
                                <fo:table-column column-width="40%" />
                                <fo:table-column column-width="30%"/>
                                <fo:table-column column-width="30%" />

                                <fo:table-body>
                                    <fo:table-row>
                                        <fo:table-cell>
                                            <fo:block><fo:inline>Dept. Name : <xsl:value-of select="department"/></fo:inline></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell>
                                            <fo:block><fo:inline>Application No. : <xsl:value-of select="applicationNumber"/></fo:inline></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell>
                                            <fo:block><fo:inline>Last Recovery Date : <xsl:value-of select="lastRecoveryDate"/></fo:inline></fo:block>
                                        </fo:table-cell>
                                    </fo:table-row>
                                </fo:table-body>
                            </fo:table>


                            <fo:table line-height="1"  margin-top="5pt">
                                <fo:table-column column-width="40%" />
                                <fo:table-column column-width="30%"/>
                                <fo:table-column column-width="30%" />

                                <fo:table-body>
                                    <fo:table-row>
                                        <fo:table-cell>
                                            <fo:block><fo:inline>Date of Birth. : <xsl:value-of select="dateOfBirth"/></fo:inline></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell>
                                            <fo:block><fo:inline>Date of Joining. : <xsl:value-of select="dateOfJoining"/></fo:inline></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell>
                                            <fo:block><fo:inline>Date of Joining PF. : <xsl:value-of select="dateOfJoiningPf"/></fo:inline></fo:block>
                                        </fo:table-cell>
                                    </fo:table-row>
                                </fo:table-body>
                            </fo:table>


                            <fo:table line-height="1"  margin-top="5pt">
                                <fo:table-column column-width="40%" />
                                <fo:table-column column-width="30%"/>
                                <fo:table-column column-width="30%" />

                                <fo:table-body>
                                    <fo:table-row>
                                        <fo:table-cell>
                                            <fo:block><fo:inline>Date. of Joining Prior : <xsl:value-of  select="dateOfJoiningPrior"/></fo:inline></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell>
                                            <fo:block><fo:inline>Application Date. : <xsl:value-of select="applicationDate"/></fo:inline></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell>
                                            <fo:block><fo:inline>PF Base Salary : <xsl:value-of select="pfBase"/></fo:inline></fo:block>
                                        </fo:table-cell>
                                    </fo:table-row>
                                </fo:table-body>
                            </fo:table>

                        </fo:block>


                        <fo:block margin-top="10pt">

                            <fo:table line-height="1"  margin-top="10pt">
                                <fo:table-column column-width="25%" />
                                <fo:table-column column-width="15%" />
                                <fo:table-column column-width="30%" />
                                <fo:table-column column-width="10%" />
                                <fo:table-column column-width="20%" />

                                <fo:table-body>
                                    <fo:table-row>
                                        <fo:table-cell>
                                            <fo:block></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell>
                                            <fo:block font-weight="600"><fo:inline>Loan Code</fo:inline></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell>
                                            <fo:block font-weight="600"><fo:inline>Loan Type</fo:inline></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell>
                                            <fo:block font-weight="600"><fo:inline>Loan Date</fo:inline></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell>
                                            <fo:block text-align="right"
                                                      font-weight="600"><fo:inline>Loan Amount</fo:inline></fo:block>
                                        </fo:table-cell>
                                    </fo:table-row>


                                    <xsl:for-each select="loanHistory/loan">
                                        <fo:table-row>
                                            <fo:table-cell>
                                                <fo:block padding-top="10pt">NRL Loan History</fo:block>
                                            </fo:table-cell>
                                            <fo:table-cell>
                                                <fo:block padding-top="10pt"><fo:inline><xsl:value-of select="loanCode"/></fo:inline></fo:block>
                                            </fo:table-cell>
                                            <fo:table-cell>
                                                <fo:block padding-top="10pt"><fo:inline><xsl:value-of select="loanType"/></fo:inline></fo:block>
                                            </fo:table-cell>
                                            <fo:table-cell>
                                                <fo:block padding-top="10pt"><fo:inline><xsl:value-of select="date"/></fo:inline></fo:block>
                                            </fo:table-cell>
                                            <fo:table-cell>
                                                <fo:block text-align="right"
                                                          padding-top="10pt"><fo:inline><xsl:value-of select="amount"/></fo:inline></fo:block>
                                            </fo:table-cell>
                                        </fo:table-row>
                                    </xsl:for-each>


                                </fo:table-body>
                            </fo:table>


                        </fo:block>




                    </fo:block>

                </fo:flow>
            </fo:page-sequence>


        </fo:root>
    </xsl:template>

</xsl:stylesheet>
', 'LOAN_WORKSHEET', 2);

insert into pdf_document_design(created_at, entity_id, is_active, updated_at, document, document_type,
                                tenant_id)
VALUES (sysdate(), '', 1, sysdate(), '<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.1" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:fo="http://www.w3.org/1999/XSL/Format" exclude-result-prefixes="fo">

    <xsl:template match="monthlyStatement">
        <fo:root xmlns:fo="http://www.w3.org/1999/XSL/Format">

            <fo:layout-master-set>
                <fo:simple-page-master master-name="simpleA4"
                                       page-width="29.7cm" page-height="21cm"
                                       margin-top="0.3cm" margin-bottom="0.3cm"
                                       margin-left="1cm" margin-right="1cm"
                                       >
                    <fo:region-body/>

                </fo:simple-page-master>
            </fo:layout-master-set>


            <fo:page-sequence master-reference="simpleA4">
                <fo:flow flow-name="xsl-region-body" font-size="11pt" line-height="1.2"
                         font-family="Times-Roman" word-spacing="6pt" letter-spacing="1pt">

                    <fo:block padding="10pt"
                              padding-top="0pt" padding-bottom="5pt"
                              border="1pt solid red">

                        <fo:table line-height="1">
                            <fo:table-column column-width="10%"/>
                            <fo:table-column column-width="90%"/>

                            <fo:table-body>
                                <fo:table-row>
                                    <fo:table-cell margin="0" padding="0">
                                        <fo:block margin="0" padding="0">
                                            <fo:external-graphic src="https://developerspftrust.coreintegra.com/images/mahindra-full-logo.png" content-height="75pt" content-width="75pt"/>
                                        </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell margin="0" padding="0">
                                        <fo:block margin="0" padding="0" text-align="center" line-height="1.2">
                                            <fo:block font-weight="600">Mahindra &amp; Mahindra Limited. Staff Provident Fund</fo:block>
                                            <fo:block>MONTHLY PROVIDENT FUND MEMBER''S BALANCE AS ON <xsl:value-of select="closingDate"/></fo:block>
                                        </fo:block>
                                    </fo:table-cell>
                                </fo:table-row>
                            </fo:table-body>
                        </fo:table>
                        <!-- logo and address end -->

                        <fo:block margin="5pt 0pt" margin-bottom="0pt" margin-top="0pt"
                                  border="1pt solid black" line-height="1" padding="5pt pt0">

                            <fo:block>
                                <fo:inline>Employee Name : <xsl:value-of select="name"/></fo:inline>
                                <fo:inline padding-left="35pt">Token No. : <xsl:value-of select="tokenNumber"/></fo:inline>
                                <fo:inline padding-left="35pt">P.F.No. : <xsl:value-of select="pfNumber"/></fo:inline>
                                <fo:inline padding-left="35pt">Unit Code. : <xsl:value-of select="unitCode"/></fo:inline>
                            </fo:block>

                            <fo:block margin-top="5pt">
<!--                                <fo:inline padding-left="35pt">Pern No. : 900000</fo:inline>-->
                                <fo:inline>Last Rec Dt : <xsl:value-of select="lastRecoveryDate"/></fo:inline>
                                <fo:inline padding-left="35pt">Status : <xsl:value-of select="status"/></fo:inline>
                                <fo:inline padding-left="35pt">Settlement Dt. : <xsl:value-of select="statusDate"/></fo:inline>
                            </fo:block>

                        </fo:block>

                        <fo:block margin="10pt 0pt" margin-top="0pt" margin-bottom="0"
                                  border="1pt solid black" border-top="0" line-height="1">

                            <fo:table >
                                <fo:table-column column-width="50%"/>
                                <fo:table-column column-width="50%"/>

                                <fo:table-body>
                                    <fo:table-row>
                                        <fo:table-cell margin="0" padding="0" border-right="1pt solid black">

                                            <fo:table >
                                                <fo:table-column column-width="50%"/>
                                                <fo:table-column column-width="25%"/>
                                                <fo:table-column column-width="25%"/>


                                                <fo:table-body>

                                                    <fo:table-row font-weight="600" line-height="1" font-size="11">
                                                        <fo:table-cell>
                                                            <fo:block>Nominees</fo:block>
                                                        </fo:table-cell>
                                                        <fo:table-cell>
                                                            <fo:block>Relationship</fo:block>
                                                        </fo:table-cell>
                                                        <fo:table-cell>
                                                            <fo:block>Share%</fo:block>
                                                        </fo:table-cell>
                                                    </fo:table-row>

                                                </fo:table-body>
                                            </fo:table>

                                        </fo:table-cell>
                                        <fo:table-cell margin="0" padding="0">

                                            <fo:table >

                                                <fo:table-column column-width="50%"/>
                                                <fo:table-column column-width="25%"/>
                                                <fo:table-column column-width="25%"/>

                                                <fo:table-body>

                                                    <fo:table-row font-weight="600" line-height="1" font-size="11">
                                                        <fo:table-cell>
                                                            <fo:block>Nominees</fo:block>
                                                        </fo:table-cell>
                                                        <fo:table-cell>
                                                            <fo:block>Relationship</fo:block>
                                                        </fo:table-cell>
                                                        <fo:table-cell>
                                                            <fo:block>Share%</fo:block>
                                                        </fo:table-cell>
                                                    </fo:table-row>

                                                </fo:table-body>
                                            </fo:table>

                                        </fo:table-cell>
                                    </fo:table-row>

                                </fo:table-body>
                            </fo:table>

                        </fo:block>


                        <fo:block margin="0pt"
                                  border="1pt solid black"
                                  border-top="0" line-height="1">

                            <fo:table >
                                <fo:table-column column-width="50%"/>
                                <fo:table-column column-width="50%"/>

                                <fo:table-body>
                                    <fo:table-row>
                                        <fo:table-cell margin="0" padding="0" border-right="1pt solid black">

                                            <fo:table >

                                                <fo:table-column column-width="50%"/>
                                                <fo:table-column column-width="25%"/>
                                                <fo:table-column column-width="25%"/>

                                                <fo:table-body>

                                                    <fo:table-row font-size="10" line-height="1.2">
                                                        <fo:table-cell padding="1pt" >
                                                            <fo:block><xsl:value-of select="/monthlyStatement/nominees[1]/name"/></fo:block>
                                                        </fo:table-cell>
                                                        <fo:table-cell padding="1pt">
                                                            <fo:block><xsl:value-of select="/monthlyStatement/nominees[1]/relationship"/></fo:block>
                                                        </fo:table-cell>
                                                        <fo:table-cell padding="1pt">
                                                            <fo:block><xsl:value-of select="/monthlyStatement/nominees[1]/share"/></fo:block>
                                                        </fo:table-cell>
                                                    </fo:table-row>

                                                    <fo:table-row font-size="10" line-height="1.2">
                                                        <fo:table-cell padding="1pt" >
                                                            <fo:block><xsl:value-of select="/monthlyStatement/nominees[2]/name"/></fo:block>
                                                        </fo:table-cell>
                                                        <fo:table-cell padding="1pt">
                                                            <fo:block><xsl:value-of select="/monthlyStatement/nominees[2]/relationship"/></fo:block>
                                                        </fo:table-cell>
                                                        <fo:table-cell padding="1pt">
                                                            <fo:block><xsl:value-of select="/monthlyStatement/nominees[2]/share"/></fo:block>
                                                        </fo:table-cell>
                                                    </fo:table-row>

                                                </fo:table-body>
                                            </fo:table>

                                        </fo:table-cell>
                                        <fo:table-cell margin="0" padding="0">

                                            <fo:table >
                                                <fo:table-column column-width="50%"/>
                                                <fo:table-column column-width="25%"/>
                                                <fo:table-column column-width="25%"/>

                                                <fo:table-body>

                                                    <fo:table-row font-size="10" line-height="1.2">
                                                        <fo:table-cell padding="1pt" >
                                                            <fo:block><xsl:value-of select="/monthlyStatement/nominees[3]/name"/></fo:block>
                                                        </fo:table-cell>
                                                        <fo:table-cell padding="1pt">
                                                            <fo:block><xsl:value-of select="/monthlyStatement/nominees[3]/relationship"/></fo:block>
                                                        </fo:table-cell>
                                                        <fo:table-cell padding="1pt">
                                                            <fo:block><xsl:value-of select="/monthlyStatement/nominees[3]/share"/></fo:block>
                                                        </fo:table-cell>
                                                    </fo:table-row>

                                                    <fo:table-row font-size="10" line-height="1.2">
                                                        <fo:table-cell padding="1pt" >
                                                            <fo:block><xsl:value-of select="/monthlyStatement/nominees[4]/name"/></fo:block>
                                                        </fo:table-cell>
                                                        <fo:table-cell padding="1pt">
                                                            <fo:block><xsl:value-of select="/monthlyStatement/nominees[4]/relationship"/></fo:block>
                                                        </fo:table-cell>
                                                        <fo:table-cell padding="1pt">
                                                            <fo:block><xsl:value-of select="/monthlyStatement/nominees[4]/share"/></fo:block>
                                                        </fo:table-cell>
                                                    </fo:table-row>

                                                </fo:table-body>
                                            </fo:table>

                                        </fo:table-cell>
                                    </fo:table-row>

                                </fo:table-body>
                            </fo:table>

                        </fo:block>


                        <fo:block line-height="1" margin-top="10pt" font-size="11" font-weight="600">

                            <fo:table>
                                <fo:table-column column-width="30%" />
                                <fo:table-column column-width="17%"/>
                                <fo:table-column column-width="17%"/>
                                <fo:table-column column-width="17%"/>
                                <fo:table-column column-width="17%"/>
<!--                                 <fo:table-column column-width="20%"/>
 -->
                                <fo:table-body>

                                    <fo:table-row>
                                        <fo:table-cell text-align="left">
                                            <fo:block>Account Particulars</fo:block>
                                            <fo:block>====================</fo:block>
                                        </fo:table-cell>
                                     <!--    <fo:table-cell text-align="right">
                                            <fo:block>PF Base</fo:block>
                                            <fo:block>========</fo:block>
                                        </fo:table-cell> -->
                                        <fo:table-cell text-align="right">
                                            <fo:block>Member</fo:block>
                                            <fo:block>Contribution</fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right">
                                            <fo:block>Company</fo:block>
                                            <fo:block>Contribution</fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right">
                                            <fo:block>Vpf</fo:block>
                                            <fo:block>Contribution</fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right">
                                            <fo:block>Total</fo:block>
                                            <fo:block>(Rupees)</fo:block>
                                        </fo:table-cell>
                                    </fo:table-row>

                                </fo:table-body>
                            </fo:table>
                        </fo:block>

                        <fo:block margin-top="10pt"
                                  border-bottom="1pt dashed black" padding-bottom="5pt">
                            <fo:table>
                                <fo:table-column column-width="30%" />
                                <fo:table-column column-width="17%"/>
                                <fo:table-column column-width="17%"/>
                                <fo:table-column column-width="17%"/>
                                <fo:table-column column-width="17%"/>
<!--                                 <fo:table-column column-width="20%"/>
 -->
                                <fo:table-body>

                                    <fo:table-row>
                                        <fo:table-cell  text-align="left">
                                            <fo:block>Opening Balance as on <xsl:value-of select="yearOpeningDate"/></fo:block>
                                        </fo:table-cell>
                                   <!--      <fo:table-cell  text-align="right">
                                            <fo:block></fo:block>
                                        </fo:table-cell> -->
                                        <fo:table-cell text-align="right">
                                            <fo:block><xsl:value-of select="yearOpeningMemberContribution"/></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell  text-align="right">
                                            <fo:block><xsl:value-of select="yearOpeningCompanyContribution"/></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell  text-align="right">
                                            <fo:block><xsl:value-of select="yearOpeningVpfContribution"/></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right">
                                            <fo:block><xsl:value-of select="yearOpeningTotalContribution"/></fo:block>
                                        </fo:table-cell>
                                    </fo:table-row>

                                </fo:table-body>
                            </fo:table>

                        </fo:block>


                        <fo:block margin-bottom="10pt"
                                  padding-top="10pt" padding-bottom="10pt"
                                  border-bottom="1pt dashed black"
                                  line-height="1.2">

                            <fo:table>
                                <fo:table-column column-width="30%" />
                                <fo:table-column column-width="17%"/>
                                <fo:table-column column-width="17%"/>
                                <fo:table-column column-width="17%"/>
                                <fo:table-column column-width="17%"/>
<!--                                 <fo:table-column column-width="20%"/>
 -->
                                <fo:table-body>

                                    <fo:table-row>
                                        <fo:table-cell  text-align="left">
                                            <fo:block>Monthly Contribution (<xsl:value-of select="finantialPeriod"/>): </fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell  text-align="right">
                                            <fo:block></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right">
                                            <fo:block></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell  text-align="right">
                                            <fo:block></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell  text-align="right">
                                            <fo:block></fo:block>
                                        </fo:table-cell>
                                        <!-- <fo:table-cell text-align="right">
                                            <fo:block></fo:block>
                                        </fo:table-cell> -->
                                    </fo:table-row>

                                    <xsl:for-each select="contributionDetails">
                                        <fo:table-row>
                                            <fo:table-cell  text-align="left">
                                                <fo:block><xsl:value-of select="monthName"/></fo:block>
                                            </fo:table-cell>
                                           <!--  <fo:table-cell  text-align="right">
                                                <fo:block><xsl:value-of select="pfBaseSalary"/></fo:block>
                                            </fo:table-cell> -->
                                            <fo:table-cell text-align="right">
                                                <fo:block><xsl:value-of select="pfMemberContribution"/></fo:block>
                                            </fo:table-cell>
                                            <fo:table-cell  text-align="right">
                                                <fo:block><xsl:value-of select="pfCompanyContribution"/></fo:block>
                                            </fo:table-cell>
                                            <fo:table-cell  text-align="right">
                                                <fo:block><xsl:value-of select="pfVpfContribution"/></fo:block>
                                            </fo:table-cell>
                                            <fo:table-cell text-align="right">
                                                <fo:block><xsl:value-of select="pfTotalContribution"/></fo:block>
                                            </fo:table-cell>
                                        </fo:table-row>
                                    </xsl:for-each>

                                </fo:table-body>
                            </fo:table>

                        </fo:block>



                    <fo:block padding-bottom="10pt"
                              line-height="1.2">

                        <fo:table>
                            <fo:table-column column-width="30%" />
                            <fo:table-column column-width="17%"/>
                            <fo:table-column column-width="17%"/>
                            <fo:table-column column-width="17%"/>
                            <fo:table-column column-width="17%"/>
<!--                             <fo:table-column column-width="20%"/>
 -->
                            <fo:table-body>
                                <fo:table-row>
                                    <fo:table-cell  text-align="left">
                                        <fo:block>Total Contributions </fo:block>
                                    </fo:table-cell>
                                    <!-- <fo:table-cell text-align="right">
                                        <fo:block></fo:block>
                                    </fo:table-cell> -->
                                    <fo:table-cell  text-align="right">
                                        <fo:block><xsl:value-of select="totalMemberContribution"/></fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell text-align="right">
                                        <fo:block><xsl:value-of select="totalCompanyContribution"/></fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell  text-align="right">
                                        <fo:block><xsl:value-of select="totalVpfContribution"/></fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell  text-align="right">
                                        <fo:block><xsl:value-of select="totalContribution"/></fo:block>
                                    </fo:table-cell>
                                </fo:table-row>
                            </fo:table-body>
                        </fo:table>
                    </fo:block>


                    <fo:block padding-bottom="5pt"
                              line-height="1.2">

                        <fo:table>
                            <fo:table-column column-width="30%" />
                            <fo:table-column column-width="17%"/>
                            <fo:table-column column-width="17%"/>
                            <fo:table-column column-width="17%"/>
                            <fo:table-column column-width="17%"/>
<!--                             <fo:table-column column-width="20%"/>
 -->
                            <fo:table-body>
                                <fo:table-row>
                                    <fo:table-cell  text-align="left">
                                        <fo:block>Transfer from Other Fund </fo:block>
                                    </fo:table-cell>
                                  <!--   <fo:table-cell text-align="right">
                                        <fo:block></fo:block>
                                    </fo:table-cell> -->
                                    <fo:table-cell  text-align="right">
                                        <fo:block><xsl:value-of select="transferInMemberContribution"/></fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell text-align="right">
                                        <fo:block><xsl:value-of select="transferInCompanyContribution"/></fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell  text-align="right">
                                        <fo:block><xsl:value-of select="transferInVpfContribution"/></fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell  text-align="right">
                                        <fo:block><xsl:value-of select="transferInTotalContribution"/></fo:block>
                                    </fo:table-cell>
                                </fo:table-row>
                            </fo:table-body>
                        </fo:table>
                    </fo:block>


                    <fo:block padding-bottom="10pt"
                              line-height="1.2" border-bottom="1pt dashed black">

                        <fo:table>
                            <fo:table-column column-width="30%" />
                            <fo:table-column column-width="17%"/>
                            <fo:table-column column-width="17%"/>
                            <fo:table-column column-width="17%"/>
                            <fo:table-column column-width="17%"/>
<!--                             <fo:table-column column-width="20%"/>
 -->
                            <fo:table-body>
                                <fo:table-row>
                                    <fo:table-cell  text-align="left">
                                        <fo:block>Less : NRL Withdrawals </fo:block>
                                    </fo:table-cell>
                              <!--       <fo:table-cell text-align="right">
                                        <fo:block></fo:block>
                                    </fo:table-cell> -->
                                    <fo:table-cell  text-align="right">
                                        <fo:block><xsl:value-of select="loanOnMemberContribution"/></fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell text-align="right">
                                        <fo:block><xsl:value-of select="loanOnCompanyContribution"/></fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell  text-align="right">
                                        <fo:block><xsl:value-of select="loanOnVpfContribution"/></fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell  text-align="right">
                                        <fo:block><xsl:value-of select="loanOnTotalContribution"/></fo:block>
                                    </fo:table-cell>
                                </fo:table-row>
                            </fo:table-body>
                        </fo:table>
                    </fo:block>


                    <fo:block margin-top="5pt" padding-bottom="5pt"
                              line-height="1.2" border-bottom="1pt dashed black">

                        <fo:table>
                            <fo:table-column column-width="30%" />
                            <fo:table-column column-width="17%"/>
                            <fo:table-column column-width="17%"/>
                            <fo:table-column column-width="17%"/>
                            <fo:table-column column-width="17%"/>
<!--                             <fo:table-column column-width="20%"/>
 -->
                            <fo:table-body>
                                <fo:table-row>
                                    <fo:table-cell  text-align="left">
                                        <fo:block>Closing Balance as on <xsl:value-of select="closingDate"/> </fo:block>
                                    </fo:table-cell>
                          <!--           <fo:table-cell text-align="right">
                                        <fo:block></fo:block>
                                    </fo:table-cell> -->
                                    <fo:table-cell  text-align="right">
                                        <fo:block><xsl:value-of select="netMemberContribution"/></fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell text-align="right">
                                        <fo:block><xsl:value-of select="netCompanyContribution"/></fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell  text-align="right">
                                        <fo:block><xsl:value-of select="netVpfContribution"/></fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell  text-align="right">
                                        <fo:block><xsl:value-of select="netContribution"/></fo:block>
                                    </fo:table-cell>
                                </fo:table-row>
                            </fo:table-body>
                        </fo:table>
                    </fo:block>







 <xsl:if test="transferInDetailsForMS = ''TRUST''">
                    <fo:block line-height="1" border-top="1pt dashed black"
                              font-size="9" padding-top="10pt"
                                margin-bottom="0" padding-bottom="0">

                        <fo:table>

                            <fo:table-column column-width="20%" />
                            <fo:table-column column-width="10%"/>
                            <fo:table-column column-width="14%"/>
                            <fo:table-column column-width="14%"/>
                            <fo:table-column column-width="30%"/>
                            <fo:table-column column-width="12%"/>

                            <fo:table-body>

                                <fo:table-row>
                                    <fo:table-cell text-align="left">
                                        <fo:block>Date of Transfer-IN</fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell text-align="center">
                                        <fo:block>Receipt Date</fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell text-align="center">
                                        <fo:block>EE contributions</fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell text-align="center">
                                        <fo:block>ER Contributions</fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell text-align="center">
                                        <fo:block>Company Name</fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell text-align="center">
                                        <fo:block>Document Nos.</fo:block>
                                    </fo:table-cell>
                                </fo:table-row>

                            </fo:table-body>
                        </fo:table>
                    </fo:block>

</xsl:if>

                        <fo:block line-height="1" border-top="1pt dashed black" margin-bottom="0"
                                  font-size="8" padding-top="2pt" padding-bottom="1pt">

                            <fo:table>
                                <fo:table-column column-width="20%" />
                                <fo:table-column column-width="10%"/>
                                <fo:table-column column-width="14%"/>
                                <fo:table-column column-width="14%"/>
                                <fo:table-column column-width="30%"/>
                                <fo:table-column column-width="12%"/>

                                <fo:table-body>

                                    <xsl:for-each select="transferInDetailsForMS">
                                    <fo:table-row>
                                        <fo:table-cell text-align="left">
                                            <fo:block><xsl:value-of select="createdAtDate"/></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="center">
                                            <fo:block><xsl:value-of select="receiptAtDate"/></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="center">
                                            <fo:block><xsl:value-of select="employeeContribution"/></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="center">
                                            <fo:block><xsl:value-of select="employerContribution"/></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="center">
                                            <fo:block><xsl:value-of select="companyName"/></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="center">
                                            <fo:block><xsl:value-of select="documentNumber"/></fo:block>
                                        </fo:table-cell>
                                    </fo:table-row>

                                    </xsl:for-each>

                                    <fo:table-row>
                                        <fo:table-cell text-align="left">
                                            <fo:block></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="center">
                                            <fo:block></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="center">
                                            <fo:block></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="center">
                                            <fo:block></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="center">
                                            <fo:block></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="center">
                                            <fo:block></fo:block>
                                        </fo:table-cell>
                                    </fo:table-row>

                                </fo:table-body>
                            </fo:table>
                        </fo:block>

<fo:block padding-left="10pt">1.The Closing Balance shown above exclude interest for the year.</fo:block>

<fo:block padding-left="10pt">2.Interest on PF account will get credited on 31st March after interest rate notified by Government.</fo:block>
                    </fo:block>

            </fo:flow>
            </fo:page-sequence>

        </fo:root>
    </xsl:template>

</xsl:stylesheet>
', 'MONTHLY_STATEMENT', 2);

insert into pdf_document_design(created_at, entity_id, is_active, updated_at, document, document_type,
                                tenant_id)
VALUES (sysdate(), '', 1, sysdate(), '<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.1" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:fo="http://www.w3.org/1999/XSL/Format" exclude-result-prefixes="fo">

    <xsl:template match="settlementAnnexure">
        <fo:root xmlns:fo="http://www.w3.org/1999/XSL/Format">

            <fo:layout-master-set>
                <fo:simple-page-master master-name="simpleA4"
                                       page-width="29.7cm" page-height="21cm"
                                       margin-top="0.3cm" margin-bottom="0.3cm"
                                       margin-left="1cm" margin-right="1cm"
                                       >
                    <fo:region-body/>

                </fo:simple-page-master>
            </fo:layout-master-set>


            <fo:page-sequence master-reference="simpleA4">
                <fo:flow flow-name="xsl-region-body" font-size="11pt" line-height="1.2"
                         font-family="Times-Roman" word-spacing="6pt" letter-spacing="1pt">

                    <fo:block>

                        <fo:table line-height="1">
                            <fo:table-column column-width="13%"/>
                            <fo:table-column column-width="87%"/>

                            <fo:table-body>
                                <fo:table-row>
                                    <fo:table-cell margin="0" padding="0">
                                        <fo:block margin="0" padding="0">
                                            <fo:external-graphic src="https://developerspftrust.coreintegra.com/images/mahindra_logo.png" content-height="100pt" content-width="100pt"/>
                                        </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell margin="0" padding="0">
                                        <fo:block margin="0" padding="0" text-align="center" line-height="1.2">
                                            <fo:block font-weight="600">MAHINDRA &amp; MAHINDRA LIMITED STAFF PROVIDENT FUND</fo:block>
                                            <fo:block>ANNEXURE TO SETTLEMENT STATEMENT AS ON <xsl:value-of select="date"/></fo:block>
                                        </fo:block>
                                    </fo:table-cell>
                                </fo:table-row>
                            </fo:table-body>
                        </fo:table>
                        <!-- logo and address end -->

                        <fo:block margin="10pt 0pt" margin-bottom="0pt" margin-top="0pt"
                                  border="1pt solid black" line-height="1" padding="5pt pt0">

                                <fo:inline>Employee Name : <xsl:value-of select="name"/></fo:inline>
                                <fo:inline padding-left="30pt">Token No. :   <xsl:value-of select="tokenNumber"/></fo:inline>
                                <fo:inline padding-left="30pt">P.F.No. :  <xsl:value-of select="pfNumber"/></fo:inline>
                                <fo:inline padding-left="30pt">Unit Code. :  <xsl:value-of select="unitCode"/></fo:inline>
                                <fo:inline padding-left="30pt">Dept.Cd :  <xsl:value-of select="unitCode"/></fo:inline>

                        </fo:block>

                        <fo:block margin-top="10pt" font-weight="600">Month Wise contribution and Interest (Figures in Rupees)</fo:block>

                        <fo:block margin="20pt 0pt" margin-bottom="0pt" margin-top="0pt"
                                  border="1pt solid black" text-align="center"
                                  line-height="1">

                            <fo:table line-height="1">
                                <fo:table-column column-width="11%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="11%"/>

                                <fo:table-body>
                                    <fo:table-row>
                                        <fo:table-cell padding="2pt 0pt">
                                            <fo:block></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell padding="2pt 0pt">
                                            <fo:block>Apr</fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell padding="2pt 0pt">
                                            <fo:block>May</fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell padding="2pt 0pt">
                                            <fo:block>Jun</fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell padding="2pt 0pt">
                                            <fo:block>Jul</fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell padding="2pt 0pt">
                                            <fo:block>Aug</fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell padding="2pt 0pt">
                                            <fo:block>Sep</fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell padding="2pt 0pt">
                                            <fo:block>Oct</fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell padding="2pt 0pt">
                                            <fo:block>Nov</fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell padding="2pt 0pt">
                                            <fo:block>Dec</fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell padding="2pt 0pt">
                                            <fo:block>Jan</fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell padding="2pt 0pt">
                                            <fo:block>Feb</fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell padding="2pt 0pt">
                                            <fo:block>Mar</fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell padding="2pt 0pt">
                                            <fo:block>Total</fo:block>
                                        </fo:table-cell>
                                    </fo:table-row>
                                </fo:table-body>
                            </fo:table>
                        </fo:block>

                        <fo:block margin="10pt 0pt" margin-bottom="0pt" margin-top="0pt"
                                  border="1pt solid black" border-top="0" font-size="9" text-align="right"
                                  line-height="1">

                            <fo:table line-height="1">

                                <fo:table-column column-width="11%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="11%"/>

                                <fo:table-body>
                                    <fo:table-row>
                                        <fo:table-cell padding="14pt 0pt">
                                            <fo:block text-align="left"
                                            font-size="10" font-weight="600">MEM. CONT A/C</fo:block>
                                        </fo:table-cell>

                                        <xsl:for-each select="memberContributions">
                                            <fo:table-cell padding="14pt 0pt">
                                                <fo:block> <xsl:value-of select="amount"/></fo:block>
                                            </fo:table-cell>
                                        </xsl:for-each>

                                        <fo:table-cell padding="14pt 0pt">
                                            <fo:block> <xsl:value-of select="totalMemberContributions"/></fo:block>
                                        </fo:table-cell>

                                    </fo:table-row>
                                </fo:table-body>
                            </fo:table>
                        </fo:block>



                        <fo:block margin="10pt 0pt" margin-bottom="0pt" margin-top="0pt"
                                  border="1pt solid black" border-top="0" font-size="9" text-align="right"
                                  line-height="1">

                            <fo:table line-height="1">

                                <fo:table-column column-width="11%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="11%"/>

                                <fo:table-body>
                                    <fo:table-row>
                                        <fo:table-cell padding="14pt 0pt">
                                            <fo:block text-align="left" font-size="10"
                                                      font-weight="600">COMP. CONT A/C</fo:block>
                                        </fo:table-cell>

                                        <xsl:for-each select="CompanyContributions">
                                            <fo:table-cell padding="14pt 0pt">
                                                <fo:block> <xsl:value-of select="amount"/></fo:block>
                                            </fo:table-cell>
                                        </xsl:for-each>

                                        <fo:table-cell padding="14pt 0pt">
                                            <fo:block> <xsl:value-of select="totalCompanyContributions"/></fo:block>
                                        </fo:table-cell>

                                    </fo:table-row>
                                </fo:table-body>
                            </fo:table>
                        </fo:block>


                        <fo:block margin="10pt 0pt" margin-bottom="0pt" margin-top="0pt"
                                  border="1pt solid black" border-top="0" font-size="9" text-align="right"
                                  line-height="1">

                            <fo:table line-height="1">

                                <fo:table-column column-width="11%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="11%"/>

                                <fo:table-body>
                                    <fo:table-row>
                                        <fo:table-cell padding="14pt 0pt">
                                            <fo:block text-align="left" font-size="10" font-weight="600">V.P.F. CONT A/C</fo:block>
                                        </fo:table-cell>

                                        <xsl:for-each select="vpfContributions">
                                            <fo:table-cell padding="14pt 0pt">
                                                <fo:block> <xsl:value-of select="amount"/></fo:block>
                                            </fo:table-cell>
                                        </xsl:for-each>

                                        <fo:table-cell padding="14pt 0pt">
                                            <fo:block> <xsl:value-of select="totalVpfContributions"/></fo:block>
                                        </fo:table-cell>

                                    </fo:table-row>
                                </fo:table-body>
                            </fo:table>
                        </fo:block>




                        <fo:block margin="10pt 0pt" margin-bottom="0pt" margin-top="0pt"
                                  border="1pt solid black" border-top="0" font-size="9" text-align="right"
                                  line-height="1">

                            <fo:table line-height="1">

                                <fo:table-column column-width="11%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="11%"/>

                                <fo:table-body>
                                    <fo:table-row>
                                        <fo:table-cell padding="14pt 0pt">
                                            <fo:block text-align="left" font-size="10" font-weight="600">Total Rs.</fo:block>
                                        </fo:table-cell>

                                        <xsl:for-each select="totalContributionsMonthWise">
                                            <fo:table-cell padding="14pt 0pt">
                                                <fo:block> <xsl:value-of select="amount"/></fo:block>
                                            </fo:table-cell>
                                        </xsl:for-each>

                                        <fo:table-cell padding="14pt 0pt">
                                            <fo:block><xsl:value-of select="totalContribution"/></fo:block>
                                        </fo:table-cell>


                                    </fo:table-row>
                                </fo:table-body>
                            </fo:table>
                        </fo:block>



                        <fo:block margin-top="10pt" font-weight="600"><xsl:value-of select="interestRate"/> Month wise Interest (Figures in Rupees)
                            [on monthly running balance met]</fo:block>

                        <fo:block margin="20pt 0pt" margin-bottom="0pt" margin-top="0pt"
                                  border="1pt solid black" text-align="center"
                                  line-height="1">

                            <fo:table line-height="1">
                                <fo:table-column column-width="11%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="11%"/>

                                <fo:table-body>
                                    <fo:table-row>
                                        <fo:table-cell padding="2pt 0pt">
                                            <fo:block></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell padding="2pt 0pt">
                                            <fo:block>Apr</fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell padding="2pt 0pt">
                                            <fo:block>May</fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell padding="2pt 0pt">
                                            <fo:block>Jun</fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell padding="2pt 0pt">
                                            <fo:block>Jul</fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell padding="2pt 0pt">
                                            <fo:block>Aug</fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell padding="2pt 0pt">
                                            <fo:block>Sep</fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell padding="2pt 0pt">
                                            <fo:block>Oct</fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell padding="2pt 0pt">
                                            <fo:block>Nov</fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell padding="2pt 0pt">
                                            <fo:block>Dec</fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell padding="2pt 0pt">
                                            <fo:block>Jan</fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell padding="2pt 0pt">
                                            <fo:block>Feb</fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell padding="2pt 0pt">
                                            <fo:block>Mar</fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell padding="2pt 0pt">
                                            <fo:block>Total</fo:block>
                                        </fo:table-cell>
                                    </fo:table-row>
                                </fo:table-body>
                            </fo:table>
                        </fo:block>

                        <fo:block margin="10pt 0pt" margin-bottom="0pt" margin-top="0pt"
                                  border="1pt solid black" border-top="0" font-size="9" text-align="right"
                                  line-height="1">

                            <fo:table line-height="1">

                                <fo:table-column column-width="11%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="11%"/>

                                <fo:table-body>
                                    <fo:table-row>
                                        <fo:table-cell padding="14pt 0pt">
                                            <fo:block text-align="left"
                                                      font-size="10" font-weight="600">MEM. CONT A/C</fo:block>
                                        </fo:table-cell>

                                        <xsl:for-each select="interestOnMemberContributions">
                                            <fo:table-cell padding="14pt 0pt">
                                                <fo:block> <xsl:value-of select="amount"/></fo:block>
                                            </fo:table-cell>
                                        </xsl:for-each>

                                        <fo:table-cell padding="14pt 0pt">
                                            <fo:block><xsl:value-of select="totalInterestOnMemberContribution"/></fo:block>
                                        </fo:table-cell>



                                    </fo:table-row>
                                </fo:table-body>
                            </fo:table>
                        </fo:block>



                        <fo:block margin="10pt 0pt" margin-bottom="0pt" margin-top="0pt"
                                  border="1pt solid black" border-top="0" font-size="9" text-align="right"
                                  line-height="1">

                            <fo:table line-height="1">

                                <fo:table-column column-width="11%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="11%"/>

                                <fo:table-body>
                                    <fo:table-row>
                                        <fo:table-cell padding="14pt 0pt">
                                            <fo:block text-align="left" font-size="10"
                                                      font-weight="600">COMP. CONT A/C</fo:block>
                                        </fo:table-cell>

                                        <xsl:for-each select="interestOnCompanyContributions">
                                            <fo:table-cell padding="14pt 0pt">
                                                <fo:block> <xsl:value-of select="amount"/></fo:block>
                                            </fo:table-cell>
                                        </xsl:for-each>

                                        <fo:table-cell padding="14pt 0pt">
                                            <fo:block><xsl:value-of select="totalInterestOnCompanyContribution"/></fo:block>
                                        </fo:table-cell>

                                    </fo:table-row>
                                </fo:table-body>
                            </fo:table>
                        </fo:block>


                        <fo:block margin="10pt 0pt" margin-bottom="0pt" margin-top="0pt"
                                  border="1pt solid black" border-top="0" font-size="9" text-align="right"
                                  line-height="1">

                            <fo:table line-height="1">

                                <fo:table-column column-width="11%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="11%"/>

                                <fo:table-body>
                                    <fo:table-row>
                                        <fo:table-cell padding="14pt 0pt">
                                            <fo:block text-align="left" font-size="10" font-weight="600">V.P.F. CONT A/C </fo:block>
                                        </fo:table-cell>


                                        <xsl:for-each select="interestOnVpfContributions">
                                            <fo:table-cell padding="14pt 0pt">
                                                <fo:block> <xsl:value-of select="amount"/></fo:block>
                                            </fo:table-cell>
                                        </xsl:for-each>

                                        <fo:table-cell padding="14pt 0pt">
                                            <fo:block><xsl:value-of select="totalInterestOnVpfContribution"/></fo:block>
                                        </fo:table-cell>

                                    </fo:table-row>
                                </fo:table-body>
                            </fo:table>
                        </fo:block>




                        <fo:block margin="10pt 0pt" margin-bottom="0pt" margin-top="0pt"
                                  border="1pt solid black" border-top="0" font-size="9" text-align="right"
                                  line-height="1">

                            <fo:table line-height="1">

                                <fo:table-column column-width="11%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="11%"/>

                                <fo:table-body>
                                    <fo:table-row>
                                        <fo:table-cell padding="14pt 0pt">
                                            <fo:block text-align="left" font-size="10" font-weight="600">Total Rs. </fo:block>
                                        </fo:table-cell>


                                        <xsl:for-each select="totalInterestMonthWise">
                                            <fo:table-cell padding="14pt 0pt">
                                                <fo:block> <xsl:value-of select="amount"/></fo:block>
                                            </fo:table-cell>
                                        </xsl:for-each>

                                        <fo:table-cell padding="14pt 0pt">
                                            <fo:block><xsl:value-of select="totalInterest"/></fo:block>
                                        </fo:table-cell>

                                    </fo:table-row>
                                </fo:table-body>
                            </fo:table>
                        </fo:block>

                    </fo:block>

            </fo:flow>
            </fo:page-sequence>

        </fo:root>
    </xsl:template>

</xsl:stylesheet>
', 'SETTLEMENT_ANNEXURE', 2);

insert into pdf_document_design(created_at, entity_id, is_active, updated_at, document, document_type,
                                tenant_id)
VALUES (sysdate(), '', 1, sysdate(), '<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.1" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:fo="http://www.w3.org/1999/XSL/Format" exclude-result-prefixes="fo">

    <xsl:template match="settlementDispatchLetter">
        <fo:root xmlns:fo="http://www.w3.org/1999/XSL/Format">

            <fo:layout-master-set>
                <fo:simple-page-master master-name="simpleA4"
                                       page-height="29.7cm" page-width="21cm"
                                       margin-top="1cm" margin-bottom="1cm"
                                       margin-left="1cm" margin-right="1cm">
                    <fo:region-body/>
                    <fo:region-after region-name="xsl-region-after" extent=".3in"/>
                </fo:simple-page-master>
            </fo:layout-master-set>


            <fo:page-sequence master-reference="simpleA4">

                <fo:static-content flow-name="xsl-region-after" font-size="12pt" line-height="1"
                                   font-family="Times-Roman" word-spacing="6pt" letter-spacing="1pt">
                    <fo:block>
                        This is a computer generated statement hence no signature required.
                    </fo:block>
                </fo:static-content>


                <fo:flow flow-name="xsl-region-body" font-size="12pt" line-height="1.5"
                         font-family="Times-Roman" word-spacing="6pt" letter-spacing="1pt">

                    <!-- logo and address -->
                    <fo:block>
                        <fo:external-graphic src="https://developerspftrust.coreintegra.com/images/mahindra-full-logo.png" content-height="75pt" content-width="75pt"/>
                    </fo:block>

                    <fo:block text-align="center" line-height="1.2">
                        <fo:block font-weight="600">Mahindra &amp; Mahindra Limited. Staff Provident Fund</fo:block>
                        <fo:block>MITC Building, 6th Floor, Akurli Road,</fo:block>
                        <fo:block>Kandivali (East), Mumbai - 400101</fo:block>
                        <fo:block>Tel : (022) 68135640/5621</fo:block>
                        <fo:block>Fax : (022) 29653484</fo:block>
                    </fo:block>
                    <!-- logo and address end -->

                    <fo:table margin-top="20pt" line-height="1">
                        <fo:table-column column-width="50%"/>
                        <fo:table-column column-width="50%"/>

                        <fo:table-body>
                            <fo:table-row>
                                <fo:table-cell>
                                    <fo:block text-align="left">
                                        <fo:block>Ref. No. SPF/ <xsl:value-of select="refNo"/></fo:block>
                                        <fo:block>Stl.S. No. : <xsl:value-of select="stlNo"/></fo:block>
                                    </fo:block>
                                </fo:table-cell>
                                <fo:table-cell>
                                    <fo:block text-align="right">
                                        <fo:block><xsl:value-of select="date"/></fo:block>
                                    </fo:block>
                                </fo:table-cell>
                            </fo:table-row>
                        </fo:table-body>
                    </fo:table>

                    <fo:table margin-top="20pt" line-height="1">
                        <fo:table-column column-width="50%"/>

                        <fo:table-body>
                            <fo:table-row>
                                <fo:table-cell>
                                    <fo:block text-align="left" font-size="11" font-weight="600">
                                        <fo:block><xsl:value-of select="name"/></fo:block>
                                        <fo:block><xsl:value-of select="addressLine1"/></fo:block>
                                        <fo:block><xsl:value-of select="addressLine2"/></fo:block>
                                        <fo:block><xsl:value-of select="addressLine3"/></fo:block>
                                        <fo:block><xsl:value-of select="addressLine4"/></fo:block>
                                    </fo:block>
                                </fo:table-cell>
                            </fo:table-row>
                        </fo:table-body>
                    </fo:table>

                    <fo:block margin-top="20pt" margin-bottom="10pt">Dear Sir/Madam, </fo:block>

                    <fo:block margin-top="20pt" margin-left="40pt" word-spacing="8pt" line-height="1.2">
                        <fo:block>Subject : Settlement of your Provident Fund dues</fo:block>
                        <fo:block>Provident fund number : <xsl:value-of select="fromPfNumber"/></fo:block>
                    </fo:block>

                    <fo:block margin-top="20pt" word-spacing="6pt"
                              line-height="1.2" text-align="justify">
                        With reference to your advance stamped receipt in Form 19, we have credited
                        your Saving Bank Account No. <fo:inline><xsl:value-of select="accountNo"/></fo:inline> with <fo:inline><xsl:value-of select="memberBank"/></fo:inline> bank with amount of
                        Rs. <fo:inline><xsl:value-of select="amount"/></fo:inline> (<fo:inline><xsl:value-of select="amountInWords"/></fo:inline>) on
                        <fo:inline><xsl:value-of select="paymentDate"/></fo:inline> by <fo:inline><xsl:value-of select="paymentMode"/></fo:inline> Facility through <fo:inline><xsl:value-of select="bank"/></fo:inline>. being the amount paid to
                        you towards the full and final settlement of your Provident Fund dues.
                    </fo:block>

                    <fo:block margin-top="20pt" word-spacing="6pt" line-height="1.2">
                        We also enclose herewith a statement for your record.
                    </fo:block>


                    <fo:block margin-top="20pt" word-spacing="6pt" line-height="1.2">
                        Kindly check your bank account for the above credit.
                    </fo:block>

                    <fo:block margin-top="20pt">Thanking you,</fo:block>
                    <fo:block margin-top="10pt">Yours faithfully,</fo:block>
                    <fo:block>For MAHINDRA &amp; MAHINDRA LIMITED,</fo:block>
                    <fo:block>STAFF PROVIDENT FUND</fo:block>

                    <fo:block margin-top="40pt">Authorized Signatory</fo:block>

                </fo:flow>
            </fo:page-sequence>

        </fo:root>
    </xsl:template>

</xsl:stylesheet>
', 'SETTLEMENT_DISPATCH_LETTER', 2);

insert into pdf_document_design(created_at, entity_id, is_active, updated_at, document, document_type,
                                tenant_id)
VALUES (sysdate(), '', 1, sysdate(), '<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.1" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:fo="http://www.w3.org/1999/XSL/Format" exclude-result-prefixes="fo">

    <xsl:template match="workSheet">
        <fo:root xmlns:fo="http://www.w3.org/1999/XSL/Format">

            <fo:layout-master-set>
                <fo:simple-page-master master-name="simpleA4"
                                       page-width="29.7cm" page-height="21cm"
                                       margin-top="0.3cm" margin-bottom="0.3cm"
                                       margin-left="1cm" margin-right="1cm"
                                       >
                    <fo:region-body/>

                </fo:simple-page-master>


                <fo:simple-page-master master-name="annexure"
                                       page-width="29.7cm" page-height="21cm"
                                       margin-top="0.3cm" margin-bottom="0.3cm"
                                       margin-left="1cm" margin-right="1cm"
                >
                    <fo:region-body/>

                </fo:simple-page-master>

            </fo:layout-master-set>



            <fo:page-sequence master-reference="simpleA4">
                <fo:flow flow-name="xsl-region-body" font-size="11pt" line-height="1.2"
                         font-family="Times-Roman" word-spacing="6pt" letter-spacing="1pt">

                    <fo:block padding="10pt"
                              padding-top="0pt" padding-bottom="5pt"
                              border="1pt solid red">

                        <fo:table line-height="1">
                            <fo:table-column column-width="10%"/>
                            <fo:table-column column-width="90%"/>

                            <fo:table-body>
                                <fo:table-row>
                                    <fo:table-cell margin="0" padding="0">
                                        <fo:block margin="0" padding="0">
                                            <fo:external-graphic src="https://developerspftrust.coreintegra.com/images/mahindra_logo.png" content-height="75pt" content-width="75pt"/>
                                        </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell margin="0" padding="0">
                                        <fo:block margin="0" padding="0" text-align="center" line-height="1.2">
                                            <fo:block font-weight="600">Mahindra &amp; Mahindra Limited. Staff Provident Fund</fo:block>
                                            <fo:block>Statement by the Trustees to a member seceding from the fund</fo:block>
                                        </fo:block>
                                    </fo:table-cell>
                                </fo:table-row>
                            </fo:table-body>
                        </fo:table>
                        <!-- logo and address end -->

                        <fo:block margin-top="7pt">
                            <fo:table line-height="1">
                                <fo:table-column/>
                                <fo:table-column/>
                                <fo:table-column/>

                                <fo:table-body>
                                    <fo:table-row>
                                        <fo:table-cell margin="0" padding="0">
                                            <fo:block margin="0" padding="0">
                                                Settlement No. : <fo:inline><xsl:value-of select="settlementNumber"/></fo:inline>
                                            </fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell margin="0" padding="0">
                                            <fo:block>Date of Cessation: <fo:inline><xsl:value-of select="dateOfCessation"/></fo:inline></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell margin="0" padding="0">
                                            <fo:block>Document No. : <fo:inline><xsl:value-of select="documentNo"/></fo:inline></fo:block>
                                        </fo:table-cell>
                                    </fo:table-row>
                                </fo:table-body>
                            </fo:table>
                        </fo:block>

                        <fo:block margin="10pt 0pt" margin-bottom="0pt" margin-top="0pt"
                                  border="1pt solid black" line-height="1" padding="5pt pt0">

                            <fo:block>
                                <fo:inline>Employee Name : <fo:inline><xsl:value-of select="name"/></fo:inline></fo:inline>
                                <fo:inline padding-left="30pt">Token No. : <fo:inline><xsl:value-of select="tokenNumber"/></fo:inline></fo:inline>
                                <fo:inline padding-left="30pt">P.F.No. : <fo:inline><xsl:value-of select="pfNumber"/></fo:inline></fo:inline>
                                <fo:inline padding-left="30pt">Unit Code. : <fo:inline><xsl:value-of select="unitCode"/></fo:inline></fo:inline>
                                <fo:inline padding-left="30pt">Status : <xsl:value-of select="status"/></fo:inline>
                            </fo:block>

                            <fo:block margin-top="5pt">
                                <fo:inline>Dt.of Birth : <fo:inline><xsl:value-of select="dateOfBirth"/></fo:inline></fo:inline>
                                <fo:inline padding-left="30pt">Dt.of Joining PF : <fo:inline><xsl:value-of select="dateOfJoining"/></fo:inline></fo:inline>
                                <fo:inline padding-left="30pt">DOJ Previous Employer : <fo:inline><xsl:value-of select="dateOfJoiningPrior"/></fo:inline></fo:inline>
                                <fo:inline padding-left="30pt">Last Rec Dt. : <fo:inline><xsl:value-of select="lastRecoveryDate"/></fo:inline></fo:inline>
                            </fo:block>

                        </fo:block>

                        <fo:block margin="10pt 0pt" margin-top="0pt" margin-bottom="0"
                                  border="1pt solid black" border-top="0" line-height="1">

                            <fo:table >
                                <fo:table-column column-width="50%"/>
                                <fo:table-column column-width="50%"/>

                                <fo:table-body>
                                    <fo:table-row>
                                        <fo:table-cell margin="0" padding="0" border-right="1pt solid black">

                                            <fo:table >
                                                <fo:table-column column-width="50%"/>
                                                <fo:table-column column-width="25%"/>
                                                <fo:table-column column-width="25%"/>


                                                <fo:table-body>

                                                    <fo:table-row font-weight="600" line-height="1" font-size="11">
                                                        <fo:table-cell>
                                                            <fo:block>Nominees</fo:block>
                                                        </fo:table-cell>
                                                        <fo:table-cell>
                                                            <fo:block>Relationship</fo:block>
                                                        </fo:table-cell>
                                                        <fo:table-cell>
                                                            <fo:block>Share%</fo:block>
                                                        </fo:table-cell>
                                                    </fo:table-row>

                                                </fo:table-body>
                                            </fo:table>

                                        </fo:table-cell>
                                        <fo:table-cell margin="0" padding="0">

                                            <fo:table >


                                                <fo:table-column column-width="50%"/>
                                                <fo:table-column column-width="25%"/>
                                                <fo:table-column column-width="25%"/>


                                                <fo:table-body>

                                                    <fo:table-row font-weight="600" line-height="1" font-size="11">
                                                        <fo:table-cell>
                                                            <fo:block>Nominees</fo:block>
                                                        </fo:table-cell>
                                                        <fo:table-cell>
                                                            <fo:block>Relationship</fo:block>
                                                        </fo:table-cell>
                                                        <fo:table-cell>
                                                            <fo:block>Share%</fo:block>
                                                        </fo:table-cell>
                                                    </fo:table-row>

                                                </fo:table-body>
                                            </fo:table>

                                        </fo:table-cell>
                                    </fo:table-row>

                                </fo:table-body>
                            </fo:table>

                        </fo:block>


                        <fo:block margin="0pt"
                                  border="1pt solid black"
                                  border-top="0" line-height="1">

                            <fo:table >
                                <fo:table-column column-width="50%"/>
                                <fo:table-column column-width="50%"/>

                                <fo:table-body>
                                    <fo:table-row>
                                        <fo:table-cell margin="0" padding="0" border-right="1pt solid black">

                                            <fo:table >

                                                <fo:table-column column-width="50%"/>
                                                <fo:table-column column-width="25%"/>
                                                <fo:table-column column-width="25%"/>

                                                <fo:table-body>

                                                    <fo:table-row font-size="10" line-height="1.2">
                                                        <fo:table-cell padding="1pt" >
                                                            <fo:block><xsl:value-of select="/workSheet/nominee[1]/name"/></fo:block>
                                                        </fo:table-cell>
                                                        <fo:table-cell padding="1pt">
                                                            <fo:block><xsl:value-of select="/workSheet/nominee[1]/relationship"/></fo:block>
                                                        </fo:table-cell>
                                                        <fo:table-cell padding="1pt">
                                                            <fo:block><xsl:value-of select="/workSheet/nominee[1]/share"/></fo:block>
                                                        </fo:table-cell>
                                                    </fo:table-row>

                                                    <fo:table-row font-size="10" line-height="1.2">
                                                        <fo:table-cell padding="1pt" >
                                                            <fo:block><xsl:value-of select="/workSheet/nominee[2]/name"/></fo:block>
                                                        </fo:table-cell>
                                                        <fo:table-cell padding="1pt">
                                                            <fo:block><xsl:value-of select="/workSheet/nominee[2]/relationship"/></fo:block>
                                                        </fo:table-cell>
                                                        <fo:table-cell padding="1pt">
                                                            <fo:block><xsl:value-of select="/workSheet/nominee[2]/share"/></fo:block>
                                                        </fo:table-cell>
                                                    </fo:table-row>

                                                </fo:table-body>
                                            </fo:table>


                                        </fo:table-cell>
                                        <fo:table-cell margin="0" padding="0">

                                            <fo:table >
                                                <fo:table-column column-width="50%"/>
                                                <fo:table-column column-width="25%"/>
                                                <fo:table-column column-width="25%"/>

                                                <fo:table-body>


                                                    <fo:table-row font-size="10" line-height="1.2">
                                                        <fo:table-cell padding="1pt" >
                                                            <fo:block><xsl:value-of select="/workSheet/nominee[3]/name"/></fo:block>
                                                        </fo:table-cell>
                                                        <fo:table-cell padding="1pt">
                                                            <fo:block><xsl:value-of select="/workSheet/nominee[3]/relationship"/></fo:block>
                                                        </fo:table-cell>
                                                        <fo:table-cell padding="1pt">
                                                            <fo:block><xsl:value-of select="/workSheet/nominee[3]/share"/></fo:block>
                                                        </fo:table-cell>
                                                    </fo:table-row>

                                                    <fo:table-row font-size="10" line-height="1.2">
                                                        <fo:table-cell padding="1pt" >
                                                            <fo:block><xsl:value-of select="/workSheet/nominee[4]/name"/></fo:block>
                                                        </fo:table-cell>
                                                        <fo:table-cell padding="1pt">
                                                            <fo:block><xsl:value-of select="/workSheet/nominee[4]/relationship"/></fo:block>
                                                        </fo:table-cell>
                                                        <fo:table-cell padding="1pt">
                                                            <fo:block><xsl:value-of select="/workSheet/nominee[4]/share"/></fo:block>
                                                        </fo:table-cell>
                                                    </fo:table-row>

                                                </fo:table-body>
                                            </fo:table>

                                        </fo:table-cell>
                                    </fo:table-row>

                                </fo:table-body>
                            </fo:table>

                        </fo:block>


                        <fo:block line-height="1" margin-top="10pt" font-size="11" font-weight="600">

                            <fo:table>
                                <fo:table-column column-width="40%"/>
                                <fo:table-column column-width="15%"/>
                                <fo:table-column column-width="15%"/>
                                <fo:table-column column-width="15%"/>
                                <fo:table-column column-width="15%"/>

                                <fo:table-body>

                                    <fo:table-row>
                                        <fo:table-cell text-align="left">
                                            <fo:block>Account Particulars (Figures in Rupees)</fo:block>
                                            <fo:block>====================</fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right">
                                            <fo:block>Member</fo:block>
                                            <fo:block>Contribution</fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right">
                                            <fo:block>Company</fo:block>
                                            <fo:block>Contribution</fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right">
                                            <fo:block>Vpf</fo:block>
                                            <fo:block>Contribution</fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right">
                                            <fo:block>Total</fo:block>
                                            <fo:block>(Rupees)</fo:block>
                                        </fo:table-cell>
                                    </fo:table-row>

                                </fo:table-body>
                            </fo:table>
                        </fo:block>

                        <fo:block margin-top="10pt">Additions: </fo:block>

                        <fo:block margin-top="5pt">

                            <fo:table>
                                <fo:table-column column-width="40%" />
                                <fo:table-column column-width="15%"/>
                                <fo:table-column column-width="15%"/>
                                <fo:table-column column-width="15%"/>
                                <fo:table-column column-width="15%"/>

                                <fo:table-body>

                                    <fo:table-row>
                                        <fo:table-cell text-align="left">
                                            <fo:block>Opening Balance as on <fo:inline><xsl:value-of select="yearOpeningDate"/></fo:inline></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right">
                                            <fo:block><xsl:value-of select="yearOpeningMemberContribution"/></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right">
                                            <fo:block><xsl:value-of select="yearOpeningCompanyContribution"/></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right">
                                            <fo:block><xsl:value-of select="yearOpeningVpfContribution"/></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right">
                                            <fo:block><xsl:value-of select="yearOpeningTotalContribution"/></fo:block>
                                        </fo:table-cell>
                                    </fo:table-row>

                                    <fo:table-row>
                                        <fo:table-cell text-align="left" padding-top="10pt">
                                            <fo:block>Interest on Opening Balance @ <fo:inline><xsl:value-of select="interestRate"/></fo:inline>% p.a</fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right"  padding-top="10pt">
                                            <fo:block><xsl:value-of select="interestOnYearOpeningMemberContribution"/></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right" padding-top="10pt">
                                            <fo:block><xsl:value-of select="interestOnYearOpeningCompanyContribution"/></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right" padding-top="10pt">
                                            <fo:block><xsl:value-of select="interestOnYearOpeningVpfContribution"/></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right" padding-top="10pt">
                                            <fo:block><xsl:value-of select="interestOnYearOpeningTotalContribution"/></fo:block>
                                        </fo:table-cell>
                                    </fo:table-row>

                                    <fo:table-row>
                                        <fo:table-cell text-align="left" padding-top="10pt">
                                            <fo:block>Contributions for the year</fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right" padding-top="10pt">
                                            <fo:block><xsl:value-of select="currentYearMemberContribution"/></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right" padding-top="10pt">
                                            <fo:block><xsl:value-of select="currentYearCompanyContribution"/></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right" padding-top="10pt">
                                            <fo:block><xsl:value-of select="currentYearVpfContribution"/></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right" padding-top="10pt">
                                            <fo:block><xsl:value-of select="currentYearTotalContribution"/></fo:block>
                                        </fo:table-cell>
                                    </fo:table-row>

                                    <fo:table-row>
                                        <fo:table-cell text-align="left" padding-top="10pt">
                                            <fo:block>Interest on Contribution for the year @ <fo:inline><xsl:value-of select="interestRate"/></fo:inline>% p.a</fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right"  padding-top="10pt">
                                            <fo:block><xsl:value-of select="interestOnCurrentYearMemberContribution"/></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right" padding-top="10pt">
                                            <fo:block><xsl:value-of select="interestOnCurrentYearCompanyContribution"/></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right" padding-top="10pt">
                                            <fo:block><xsl:value-of select="interestOnCurrentYearVpfContribution"/></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right" padding-top="10pt">
                                            <fo:block><xsl:value-of select="interestOnCurrentYearTotalContribution"/></fo:block>
                                        </fo:table-cell>
                                    </fo:table-row>


                                    <fo:table-row>
                                        <fo:table-cell text-align="left" padding-top="10pt">
                                            <fo:block>Transfer from Other Funds</fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right" padding-top="10pt">
                                            <fo:block><xsl:value-of select="currentYearTransferInMemberContribution"/></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right" padding-top="10pt">
                                            <fo:block><xsl:value-of select="currentYearTransferInCompanyContribution"/></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right" padding-top="10pt">
                                            <fo:block><xsl:value-of select="currentYearTransferInVpfContribution"/></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right" padding-top="10pt">
                                            <fo:block><xsl:value-of select="currentYearTransferInTotalContribution"/></fo:block>
                                        </fo:table-cell>
                                    </fo:table-row>

                                    <fo:table-row>
                                        <fo:table-cell text-align="left" padding-top="10pt">
                                            <fo:block>Interest on Transfer In @ <fo:inline><xsl:value-of select="interestRate"/></fo:inline>% p.a</fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right"  padding-top="10pt">
                                            <fo:block><xsl:value-of select="interestOnCurrentYearTransferInMemberContribution"/></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right" padding-top="10pt">
                                            <fo:block><xsl:value-of select="interestOnCurrentYearTransferInCompanyContribution"/></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right" padding-top="10pt">
                                            <fo:block><xsl:value-of select="interestOnCurrentYearTransferInVpfContribution"/></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right" padding-top="10pt">
                                            <fo:block><xsl:value-of select="interestOnCurrentYearTransferInTotalContribution"/></fo:block>
                                        </fo:table-cell>
                                    </fo:table-row>

                                </fo:table-body>
                            </fo:table>

                        </fo:block>

                        <fo:block margin-top="10pt">Less: </fo:block>


                        <fo:block margin-top="5pt">

                            <fo:table>
                                <fo:table-column column-width="40%" />
                                <fo:table-column column-width="15%"/>
                                <fo:table-column column-width="15%"/>
                                <fo:table-column column-width="15%"/>
                                <fo:table-column column-width="15%"/>

                                <fo:table-body>

                                    <fo:table-row>
                                        <fo:table-cell text-align="left">
                                            <fo:block>NRL Withdrawals during the year</fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right">
                                            <fo:block><xsl:value-of select="currentYearLoanWithdrawalMemberContribution"/></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right">
                                            <fo:block><xsl:value-of select="currentYearLoanWithdrawalCompanyContribution"/></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right">
                                            <fo:block><xsl:value-of select="currentYearLoanWithdrawalVpfContribution"/></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right">
                                            <fo:block><xsl:value-of select="currentYearLoanWithdrawalTotalContribution"/></fo:block>
                                        </fo:table-cell>
                                    </fo:table-row>

                                    <fo:table-row>
                                        <fo:table-cell text-align="left" padding-top="10pt">
                                            <fo:block>Interest on NR withdrawals @ <fo:inline><xsl:value-of select="interestRate"/></fo:inline> % p.a</fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right"  padding-top="10pt">
                                            <fo:block><xsl:value-of select="interestOnCurrentYearLoanWithdrawalMemberContribution"/></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right" padding-top="10pt">
                                            <fo:block><xsl:value-of select="interestOnCurrentYearLoanWithdrawalCompanyContribution"/></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right" padding-top="10pt">
                                            <fo:block><xsl:value-of select="interestOnCurrentYearLoanWithdrawalVpfContribution"/></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right" padding-top="10pt">
                                            <fo:block><xsl:value-of select="interestOnCurrentYearLoanWithdrawalTotalContribution"/></fo:block>
                                        </fo:table-cell>
                                    </fo:table-row>

                                </fo:table-body>
                            </fo:table>

                        </fo:block>




                        <fo:block margin-top="10pt" border-top="1pt dashed black"
                                  border-bottom="1pt dashed black" padding="5pt 0pt">

                            <fo:table>
                                <fo:table-column column-width="40%"/>
                                <fo:table-column column-width="15%"/>
                                <fo:table-column column-width="15%"/>
                                <fo:table-column column-width="15%"/>
                                <fo:table-column column-width="15%"/>

                                <fo:table-body>

                                    <fo:table-row>
                                        <fo:table-cell text-align="left">
                                            <fo:block>PF Balance Due/Settlement Date: <fo:inline><xsl:value-of select="dueDate"/></fo:inline></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right">
                                            <fo:block><xsl:value-of select="totalMemberContribution"/></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right">
                                            <fo:block><xsl:value-of select="totalCompanyContribution"/></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right">
                                            <fo:block><xsl:value-of select="totalVpfContribution"/></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right">
                                            <fo:block><xsl:value-of select="totalContribution"/></fo:block>
                                        </fo:table-cell>
                                    </fo:table-row>

                                </fo:table-body>
                            </fo:table>

                        </fo:block>


                        <fo:block margin-top="10pt">
                            <fo:table>
                                <fo:table-column column-width="20%" />
                                <fo:table-column column-width="10%"/>
                                <fo:table-column column-width="20%"/>
                                <fo:table-column column-width="10%"/>
                                <fo:table-column column-width="15%"/>
                                <fo:table-column column-width="25%"/>

                                <fo:table-body>


                                    <fo:table-row>
                                        <fo:table-cell text-align="center">
                                            <fo:block>
                                                <fo:block>Income Tax : <xsl:value-of select="incomeTax"/></fo:block>
                                            </fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="center">
                                            <fo:block>
                                                <fo:block>+</fo:block>
                                            </fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right">
                                            <fo:block>Edu.cess : <xsl:value-of select="eduCess"/></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right">
                                            <fo:block> = </fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right">
                                            <fo:block> TDS Amount: </fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right">
                                            <fo:block><xsl:value-of select="tds"/></fo:block>
                                        </fo:table-cell>
                                    </fo:table-row>

                                    <fo:table-row>
                                        <fo:table-cell text-align="center">
                                            <fo:block></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="center">
                                            <fo:block></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right">
                                            <fo:block></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right">
                                            <fo:block></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right">
                                            <fo:block>NET Amount:</fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right">
                                            <fo:block><xsl:value-of select="netContribution"/></fo:block>
                                        </fo:table-cell>
                                    </fo:table-row>


                                </fo:table-body>
                            </fo:table>

                        </fo:block>

                        <fo:block margin-top="10pt" margin-left="60%">
                            <fo:block text-align="center">For Mahindra &amp; Mahindra Limited</fo:block>
                            <fo:block text-align="center">Staff Provident Fund</fo:block>
                            <fo:block text-align="center" margin-top="35pt">Authorized Signatory</fo:block>
                        </fo:block>

                        <fo:block>
                            <fo:inline>Date:- <xsl:value-of select="processedOnDate"/></fo:inline>
                        </fo:block>

                    </fo:block>

            </fo:flow>
            </fo:page-sequence>






            <fo:page-sequence master-reference="annexure">
                <fo:flow flow-name="xsl-region-body" font-size="11pt" line-height="1.2"
                         font-family="Times-Roman" word-spacing="6pt" letter-spacing="1pt">

                    <fo:block>

                        <fo:table line-height="1">
                            <fo:table-column column-width="13%"/>
                            <fo:table-column column-width="87%"/>

                            <fo:table-body>
                                <fo:table-row>
                                    <fo:table-cell margin="0" padding="0">
                                        <fo:block margin="0" padding="0">
                                            <fo:external-graphic src="https://developerspftrust.coreintegra.com/images/mahindra_logo.png" content-height="100pt" content-width="100pt"/>
                                        </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell margin="0" padding="0">
                                        <fo:block margin="0" padding="0" text-align="center" line-height="1.2">
                                            <fo:block font-weight="600">MAHINDRA &amp; MAHINDRA LIMITED STAFF PROVIDENT FUND</fo:block>
                                            <fo:block>ANNEXURE TO SETTLEMENT STATEMENT AS ON <xsl:value-of select="settlementAnnexure/date"/></fo:block>
                                        </fo:block>
                                    </fo:table-cell>
                                </fo:table-row>
                            </fo:table-body>
                        </fo:table>
                        <!-- logo and address end -->

                        <fo:block margin="10pt 0pt" margin-bottom="0pt" margin-top="0pt"
                                  border="1pt solid black" line-height="1" padding="5pt pt0">

                            <fo:inline>Employee Name : <xsl:value-of select="settlementAnnexure/name"/></fo:inline>
                            <fo:inline padding-left="30pt">Token No. :   <xsl:value-of select="tokenNumber"/></fo:inline>
                            <fo:inline padding-left="30pt">P.F.No. :  <xsl:value-of select="pfNumber"/></fo:inline>
                            <fo:inline padding-left="30pt">Unit Code. :  <xsl:value-of select="unitCode"/></fo:inline>
                            <fo:inline padding-left="30pt">Dept.Cd :  <xsl:value-of select="deptCode"/></fo:inline>

                        </fo:block>

                        <fo:block margin-top="10pt" font-weight="600">Month Wise contribution and Interest (Figures in Rupees)</fo:block>

                        <fo:block margin="20pt 0pt" margin-bottom="0pt" margin-top="0pt"
                                  border="1pt solid black" text-align="center"
                                  line-height="1">

                            <fo:table line-height="1">
                                <fo:table-column column-width="11%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="11%"/>

                                <fo:table-body>
                                    <fo:table-row>
                                        <fo:table-cell padding="2pt 0pt">
                                            <fo:block></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell padding="2pt 0pt">
                                            <fo:block>Apr</fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell padding="2pt 0pt">
                                            <fo:block>May</fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell padding="2pt 0pt">
                                            <fo:block>Jun</fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell padding="2pt 0pt">
                                            <fo:block>Jul</fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell padding="2pt 0pt">
                                            <fo:block>Aug</fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell padding="2pt 0pt">
                                            <fo:block>Sep</fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell padding="2pt 0pt">
                                            <fo:block>Oct</fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell padding="2pt 0pt">
                                            <fo:block>Nov</fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell padding="2pt 0pt">
                                            <fo:block>Dec</fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell padding="2pt 0pt">
                                            <fo:block>Jan</fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell padding="2pt 0pt">
                                            <fo:block>Feb</fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell padding="2pt 0pt">
                                            <fo:block>Mar</fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell padding="2pt 0pt">
                                            <fo:block>Total</fo:block>
                                        </fo:table-cell>
                                    </fo:table-row>
                                </fo:table-body>
                            </fo:table>
                        </fo:block>

                        <fo:block margin="10pt 0pt" margin-bottom="0pt" margin-top="0pt"
                                  border="1pt solid black" border-top="0" font-size="9" text-align="right"
                                  line-height="1">

                            <fo:table line-height="1">

                                <fo:table-column column-width="11%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="11%"/>

                                <fo:table-body>
                                    <fo:table-row>
                                        <fo:table-cell padding="14pt 0pt">
                                            <fo:block text-align="left"
                                                      font-size="10" font-weight="600">MEM. CONT A/C</fo:block>
                                        </fo:table-cell>

                                        <xsl:for-each select="settlementAnnexure/memberContributions">
                                            <fo:table-cell padding="14pt 0pt">
                                                <fo:block> <xsl:value-of select="amount"/></fo:block>
                                            </fo:table-cell>
                                        </xsl:for-each>

                                        <fo:table-cell padding="14pt 0pt">
                                            <fo:block> <xsl:value-of select="settlementAnnexure/totalMemberContributions"/></fo:block>
                                        </fo:table-cell>

                                    </fo:table-row>
                                </fo:table-body>
                            </fo:table>
                        </fo:block>



                        <fo:block margin="10pt 0pt" margin-bottom="0pt" margin-top="0pt"
                                  border="1pt solid black" border-top="0" font-size="9" text-align="right"
                                  line-height="1">

                            <fo:table line-height="1">

                                <fo:table-column column-width="11%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="11%"/>

                                <fo:table-body>
                                    <fo:table-row>
                                        <fo:table-cell padding="14pt 0pt">
                                            <fo:block text-align="left" font-size="10"
                                                      font-weight="600">COMP. CONT A/C</fo:block>
                                        </fo:table-cell>

                                        <xsl:for-each select="settlementAnnexure/CompanyContributions">
                                            <fo:table-cell padding="14pt 0pt">
                                                <fo:block> <xsl:value-of select="amount"/></fo:block>
                                            </fo:table-cell>
                                        </xsl:for-each>

                                        <fo:table-cell padding="14pt 0pt">
                                            <fo:block> <xsl:value-of select="settlementAnnexure/totalCompanyContributions"/></fo:block>
                                        </fo:table-cell>

                                    </fo:table-row>
                                </fo:table-body>
                            </fo:table>
                        </fo:block>


                        <fo:block margin="10pt 0pt" margin-bottom="0pt" margin-top="0pt"
                                  border="1pt solid black" border-top="0" font-size="9" text-align="right"
                                  line-height="1">

                            <fo:table line-height="1">

                                <fo:table-column column-width="11%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="11%"/>

                                <fo:table-body>
                                    <fo:table-row>
                                        <fo:table-cell padding="14pt 0pt">
                                            <fo:block text-align="left" font-size="10" font-weight="600">V.P.F. CONT A/C</fo:block>
                                        </fo:table-cell>

                                        <xsl:for-each select="settlementAnnexure/vpfContributions">
                                            <fo:table-cell padding="14pt 0pt">
                                                <fo:block> <xsl:value-of select="amount"/></fo:block>
                                            </fo:table-cell>
                                        </xsl:for-each>

                                        <fo:table-cell padding="14pt 0pt">
                                            <fo:block> <xsl:value-of select="settlementAnnexure/totalVpfContributions"/></fo:block>
                                        </fo:table-cell>

                                    </fo:table-row>
                                </fo:table-body>
                            </fo:table>
                        </fo:block>




                        <fo:block margin="10pt 0pt" margin-bottom="0pt" margin-top="0pt"
                                  border="1pt solid black" border-top="0" font-size="9" text-align="right"
                                  line-height="1">

                            <fo:table line-height="1">

                                <fo:table-column column-width="11%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="11%"/>

                                <fo:table-body>
                                    <fo:table-row>
                                        <fo:table-cell padding="14pt 0pt">
                                            <fo:block text-align="left" font-size="10" font-weight="600">Total Rs.</fo:block>
                                        </fo:table-cell>

                                        <xsl:for-each select="settlementAnnexure/totalContributionsMonthWise">
                                            <fo:table-cell padding="14pt 0pt">
                                                <fo:block> <xsl:value-of select="amount"/></fo:block>
                                            </fo:table-cell>
                                        </xsl:for-each>

                                        <fo:table-cell padding="14pt 0pt">
                                            <fo:block><xsl:value-of select="settlementAnnexure/totalContribution"/></fo:block>
                                        </fo:table-cell>


                                    </fo:table-row>
                                </fo:table-body>
                            </fo:table>
                        </fo:block>



                        <fo:block margin-top="10pt" font-weight="600"><xsl:value-of select="settlementAnnexure/interestRate"/> Month wise Interest (Figures in Rupees)
                            [on monthly running balance met]</fo:block>

                        <fo:block margin="20pt 0pt" margin-bottom="0pt" margin-top="0pt"
                                  border="1pt solid black" text-align="center"
                                  line-height="1">

                            <fo:table line-height="1">
                                <fo:table-column column-width="11%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="11%"/>

                                <fo:table-body>
                                    <fo:table-row>
                                        <fo:table-cell padding="2pt 0pt">
                                            <fo:block></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell padding="2pt 0pt">
                                            <fo:block>Apr</fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell padding="2pt 0pt">
                                            <fo:block>May</fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell padding="2pt 0pt">
                                            <fo:block>Jun</fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell padding="2pt 0pt">
                                            <fo:block>Jul</fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell padding="2pt 0pt">
                                            <fo:block>Aug</fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell padding="2pt 0pt">
                                            <fo:block>Sep</fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell padding="2pt 0pt">
                                            <fo:block>Oct</fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell padding="2pt 0pt">
                                            <fo:block>Nov</fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell padding="2pt 0pt">
                                            <fo:block>Dec</fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell padding="2pt 0pt">
                                            <fo:block>Jan</fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell padding="2pt 0pt">
                                            <fo:block>Feb</fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell padding="2pt 0pt">
                                            <fo:block>Mar</fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell padding="2pt 0pt">
                                            <fo:block>Total</fo:block>
                                        </fo:table-cell>
                                    </fo:table-row>
                                </fo:table-body>
                            </fo:table>
                        </fo:block>

                        <fo:block margin="10pt 0pt" margin-bottom="0pt" margin-top="0pt"
                                  border="1pt solid black" border-top="0" font-size="9" text-align="right"
                                  line-height="1">

                            <fo:table line-height="1">

                                <fo:table-column column-width="11%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="11%"/>

                                <fo:table-body>
                                    <fo:table-row>
                                        <fo:table-cell padding="14pt 0pt">
                                            <fo:block text-align="left"
                                                      font-size="10" font-weight="600">MEM. CONT A/C</fo:block>
                                        </fo:table-cell>

                                        <xsl:for-each select="settlementAnnexure/interestOnMemberContributions">
                                            <fo:table-cell padding="14pt 0pt">
                                                <fo:block> <xsl:value-of select="amount"/></fo:block>
                                            </fo:table-cell>
                                        </xsl:for-each>

                                        <fo:table-cell padding="14pt 0pt">
                                            <fo:block><xsl:value-of select="settlementAnnexure/totalInterestOnMemberContribution"/></fo:block>
                                        </fo:table-cell>



                                    </fo:table-row>
                                </fo:table-body>
                            </fo:table>
                        </fo:block>



                        <fo:block margin="10pt 0pt" margin-bottom="0pt" margin-top="0pt"
                                  border="1pt solid black" border-top="0" font-size="9" text-align="right"
                                  line-height="1">

                            <fo:table line-height="1">

                                <fo:table-column column-width="11%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="11%"/>

                                <fo:table-body>
                                    <fo:table-row>
                                        <fo:table-cell padding="14pt 0pt">
                                            <fo:block text-align="left" font-size="10"
                                                      font-weight="600">COMP. CONT A/C</fo:block>
                                        </fo:table-cell>

                                        <xsl:for-each select="settlementAnnexure/interestOnCompanyContributions">
                                            <fo:table-cell padding="14pt 0pt">
                                                <fo:block> <xsl:value-of select="amount"/></fo:block>
                                            </fo:table-cell>
                                        </xsl:for-each>

                                        <fo:table-cell padding="14pt 0pt">
                                            <fo:block><xsl:value-of select="settlementAnnexure/totalInterestOnCompanyContribution"/></fo:block>
                                        </fo:table-cell>

                                    </fo:table-row>
                                </fo:table-body>
                            </fo:table>
                        </fo:block>


                        <fo:block margin="10pt 0pt" margin-bottom="0pt" margin-top="0pt"
                                  border="1pt solid black" border-top="0" font-size="9" text-align="right"
                                  line-height="1">

                            <fo:table line-height="1">

                                <fo:table-column column-width="11%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="11%"/>

                                <fo:table-body>
                                    <fo:table-row>
                                        <fo:table-cell padding="14pt 0pt">
                                            <fo:block text-align="left" font-size="10" font-weight="600">V.P.F. CONT A/C </fo:block>
                                        </fo:table-cell>


                                        <xsl:for-each select="settlementAnnexure/interestOnVpfContributions">
                                            <fo:table-cell padding="14pt 0pt">
                                                <fo:block> <xsl:value-of select="amount"/></fo:block>
                                            </fo:table-cell>
                                        </xsl:for-each>

                                        <fo:table-cell padding="14pt 0pt">
                                            <fo:block><xsl:value-of select="settlementAnnexure/totalInterestOnVpfContribution"/></fo:block>
                                        </fo:table-cell>

                                    </fo:table-row>
                                </fo:table-body>
                            </fo:table>
                        </fo:block>




                        <fo:block margin="10pt 0pt" margin-bottom="0pt" margin-top="0pt"
                                  border="1pt solid black" border-top="0" font-size="9" text-align="right"
                                  line-height="1">

                            <fo:table line-height="1">

                                <fo:table-column column-width="11%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="11%"/>

                                <fo:table-body>
                                    <fo:table-row>
                                        <fo:table-cell padding="14pt 0pt">
                                            <fo:block text-align="left" font-size="10" font-weight="600">Total Rs. </fo:block>
                                        </fo:table-cell>


                                        <xsl:for-each select="settlementAnnexure/totalInterestMonthWise">
                                            <fo:table-cell padding="14pt 0pt">
                                                <fo:block> <xsl:value-of select="amount"/></fo:block>
                                            </fo:table-cell>
                                        </xsl:for-each>

                                        <fo:table-cell padding="14pt 0pt">
                                            <fo:block><xsl:value-of select="settlementAnnexure/totalInterest"/></fo:block>
                                        </fo:table-cell>

                                    </fo:table-row>
                                </fo:table-body>
                            </fo:table>
                        </fo:block>

                    </fo:block>

                </fo:flow>
            </fo:page-sequence>


        </fo:root>
    </xsl:template>

</xsl:stylesheet>
', 'SETTLEMENT_WORKSHEET', 2);

insert into pdf_document_design(created_at, entity_id, is_active, updated_at, document, document_type,
                                tenant_id)
VALUES (sysdate(), '', 1, sysdate(), '<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.1" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:fo="http://www.w3.org/1999/XSL/Format" exclude-result-prefixes="fo">

    <xsl:template match="transferInRequest">
        <fo:root xmlns:fo="http://www.w3.org/1999/XSL/Format">

            <fo:layout-master-set>
                <fo:simple-page-master master-name="simpleA4"
                                       page-height="29.7cm" page-width="21cm"
                                       margin-top="1cm" margin-bottom="0.8cm"
                                       margin-left="1cm" margin-right="1cm">
                    <fo:region-body/>
                    <fo:region-after region-name="xsl-region-after" extent=".5in"/>
                </fo:simple-page-master>
            </fo:layout-master-set>


            <fo:page-sequence master-reference="simpleA4">

                <fo:static-content flow-name="xsl-region-after" font-size="10pt" line-height="1"
                                   font-family="Times-Roman" font-weight="600" word-spacing="4pt"
                                   letter-spacing="1pt">
                    <fo:block>CC : </fo:block>
                    <fo:block>THE REGIONAL PROVIDENT FUND OFFICE,</fo:block>
                    <fo:block>Employees Provident Fund Organisation
4th Floor, MTNL Building, Secotr-5,</fo:block>
                    <fo:block>Charkop Market, Kandivali (West),
RO Kandivali-II, Mumabi 400 067.</fo:block>
                </fo:static-content>



                <fo:flow flow-name="xsl-region-body" font-size="12pt" line-height="1.5"
                         font-family="Times-Roman" word-spacing="6pt" letter-spacing="1pt">


                    <!-- logo and address -->
                    <fo:table line-height="1" word-spacing="6pt">
                        <fo:table-column column-width="25%"/>
                        <fo:table-column column-width="75%"/>

                        <fo:table-body>
                            <fo:table-row>
                                <fo:table-cell>

                                    <fo:block>
                                        <fo:external-graphic src="https://developerspftrust.coreintegra.com/images/mahindra_logo.png"
                                                             content-height="110pt" content-width="110pt"/>
                                    </fo:block>
                                </fo:table-cell>
                                <fo:table-cell>
                                    <fo:block>
                                        <fo:block font-weight="600" font-size="14pt">Mahindra &amp; Mahindra Limited Staff Provident Fund</fo:block>
                                        <fo:block>6th Floor,MITC Building,Akurli Road,Kandivali (East),</fo:block>
                                        <fo:block>Mumbai 400 101</fo:block>
                                        <fo:block>Tel.: (022) 6813 5640</fo:block>
                                        <fo:block>Fax.: (022) 2965 3484</fo:block>
                                    </fo:block>
                                </fo:table-cell>
                            </fo:table-row>
                        </fo:table-body>
                    </fo:table>

                    <fo:block line-height="1.2" margin-top="20pt">
                        <fo:block>Ref.No. <xsl:value-of select="refNo"/> </fo:block>
                        <fo:block><xsl:value-of select="date"/></fo:block>
                    </fo:block>

                    <fo:table line-height="1.2" margin-top="15pt" >
                        <fo:table-column column-width="70%"/>
                        <fo:table-column column-width="30%"/>

                        <fo:table-body>

                            <fo:table-row>
                                <fo:table-cell>
                                    <fo:block>
                                        <fo:block>To,</fo:block>
                                        <fo:block><xsl:value-of select="addressLine1"/></fo:block>
                                        <fo:block><xsl:value-of select="addressLine2"/></fo:block>
                                        <fo:block><xsl:value-of select="addressLine3"/></fo:block>
                                        <fo:block><xsl:value-of select="addressLine4"/></fo:block>
                                    </fo:block>
                                </fo:table-cell>
                                <fo:table-cell><fo:block></fo:block></fo:table-cell>
                            </fo:table-row>

                        </fo:table-body>
                    </fo:table>


                    <fo:block margin-top="10pt">Dear Sir,</fo:block>

                    <fo:block margin-top="10pt" line-height="1.2" text-align="justify">
                        Sub: Transfer of Provident Fund accumulation &amp; EPS A/c of Mr./Ms.
                        <fo:inline font-weight="600"><xsl:value-of select="name"/></fo:inline> (Ex-employee of
                        <fo:inline font-weight="600"><xsl:value-of select="prevCompanyName"/>
                        </fo:inline> From P.F. A/c No.
                        <fo:inline font-weight="600"><xsl:value-of select="fromPfAccount"/></fo:inline> &amp; E.P.S. A/c No.
                        <fo:inline font-weight="600"><xsl:value-of select="fromEpsAccount"/></fo:inline> To P.F. A/c No.
                        <fo:inline font-weight="600"><xsl:value-of select="toPfAccount"/></fo:inline> &amp; E.P.S. A/c No.
                        <fo:inline font-weight="600"><xsl:value-of select="toEpsAccount"/></fo:inline>
                    </fo:block>

                    <fo:block margin-top="10pt">
                        <xsl:for-each select="reminder">
                            <fo:block margin-top="5pt" font-size="10pt" line-height="1">
                                Last Request Ref: <xsl:value-of select="ref"/>
                            </fo:block>
                        </xsl:for-each>
                    </fo:block>


                    <fo:block margin-top="20pt" line-height="1.2" text-align="justify">
                        <fo:inline padding-left="50pt">We enclose the</fo:inline> Form No.13 along with our PF Trust Bank particulars duly completed by the above member who
                        is now employed with Mahindra &amp; Mahindra Limited for the transfer of his Provident Fund accumulations to
                        us. We would like to inform you that Mahindra &amp; Mahindra Limited is an exempted establishment under Employees''
                        Provident Fund Act, 1952 and having its exempted provident fund.
                    </fo:block>

                    <fo:block margin-top="10pt" line-height="1.2" text-align="justify">
                        <fo:inline padding-left="50pt">In view of kindly</fo:inline> arrange to send us your at par cheque or electronic fund transfer (Bank details are given below)
                        in respect of Provident Fund accumulations of the above member in favors of
                        "Mahindra &amp; Mahindra Limited Staff Provident Fund", along with the Annexure "K", at the earliest.
                    </fo:block>

                    <fo:block margin-top="20pt" line-height="1.2" font-weight="600">
                        Our PF Trust''s Bank A/C Details for Electronics Transfer of Amount Thru NEFT / RTGS
                    </fo:block>

                    <fo:table line-height="1" word-spacing="4" letter-spacing="0" margin-top="10pt" >
                        <fo:table-column column-width="20%"/>
                        <fo:table-column column-width="80%"/>

                        <fo:table-body>

                            <fo:table-row>
                                <fo:table-cell><fo:block font-weight="600">Trust Name </fo:block></fo:table-cell>
                                <fo:table-cell><fo:block> : Mahindra &amp; Mahindra Limited Staff Provident Fund</fo:block></fo:table-cell>
                            </fo:table-row>

                            <fo:table-row>
                                <fo:table-cell><fo:block font-weight="600">Bank Account:</fo:block></fo:table-cell>
                                <fo:table-cell><fo:block> : 00601110000587</fo:block></fo:table-cell>
                            </fo:table-row>

                            <fo:table-row>
                                <fo:table-cell><fo:block font-weight="600">Bank Name </fo:block></fo:table-cell>
                                <fo:table-cell><fo:block> : HDFC Bank Ltd.Manekji Wadia Bldg,Nanik Motwani Marg,Fort,Mum 400001</fo:block></fo:table-cell>
                            </fo:table-row>

                            <fo:table-row>
                                <fo:table-cell><fo:block font-weight="600">IFSC Code :</fo:block></fo:table-cell>
                                <fo:table-cell><fo:block> : HDFC0000060</fo:block></fo:table-cell>
                            </fo:table-row>

                            <fo:table-row>
                                <fo:table-cell><fo:block font-weight="600">MICR Code </fo:block></fo:table-cell>
                                <fo:table-cell><fo:block> : 400240015</fo:block></fo:table-cell>
                            </fo:table-row>

                        </fo:table-body>
                    </fo:table>

                    <fo:block margin-top="20pt">Thanking you,</fo:block>
                    <fo:block margin-top="7pt">Yours faithfully,</fo:block>
                    <fo:block>For Mahindra &amp; Mahindra Limited Staff Provident Fund</fo:block>

                    <fo:block margin-top="35pt">Authorized Signatory</fo:block>
                    <fo:block margin-top="7pt">Encl: Form - 13 along with M&amp;M PF Trust Bank Cancelled Cheque Details</fo:block>
                </fo:flow>
            </fo:page-sequence>

        </fo:root>
    </xsl:template>

</xsl:stylesheet>
', 'TRANSFER_IN_REQUEST_LETTER', 2);

insert into pdf_document_design(created_at, entity_id, is_active, updated_at, document, document_type,
                                tenant_id)
VALUES (sysdate(), '', 1, sysdate(), '<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.1" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:fo="http://www.w3.org/1999/XSL/Format" exclude-result-prefixes="fo">

    <xsl:template match="transferOutDispatchLetter">
        <fo:root xmlns:fo="http://www.w3.org/1999/XSL/Format">

            <fo:layout-master-set>
                <fo:simple-page-master master-name="simpleA4"
                                       page-height="29.7cm" page-width="21cm"
                                       margin-top="1cm" margin-bottom="1cm"
                                       margin-left="1cm" margin-right="1cm">
                    <fo:region-body/>
                    <fo:region-after region-name="xsl-region-after" extent=".3in"/>
                </fo:simple-page-master>
            </fo:layout-master-set>


            <fo:page-sequence master-reference="simpleA4">

                <fo:static-content flow-name="xsl-region-after" font-size="12pt" line-height="1"
                                   font-family="Times-Roman" word-spacing="6pt" letter-spacing="1pt">
                    <fo:block>
                        Registered Office : Gateway Building, Apollo Bunder, Mumbai - 400 001
                    </fo:block>
                </fo:static-content>


                <fo:flow flow-name="xsl-region-body" font-size="12pt" line-height="1.5"
                         font-family="Times-Roman" word-spacing="6pt" letter-spacing="1pt">


                    <!-- logo and address -->
                    <fo:block>
                        <fo:external-graphic src="https://developerspftrust.coreintegra.com/images/mahindra-full-logo.png" content-height="75pt" content-width="75pt"/>
                    </fo:block>

                    <fo:block>

                        <fo:table line-height="1" word-spacing="6pt">
                            <fo:table-column column-width="15%"/>
                            <fo:table-column column-width="60%"/>
                            <fo:table-column column-width="25%"/>

                            <fo:table-body>
                                <fo:table-row>

                                    <fo:table-cell>
                                        <fo:block>
                                            <fo:block>User: <xsl:value-of select="createdBy"/></fo:block>
                                        </fo:block>
                                    </fo:table-cell>

                                    <fo:table-cell>
                                        <fo:block text-align="center" font-weight="600">
                                            <fo:block>Mahindra &amp; Mahindra Limited</fo:block>
                                            <fo:block>Staff Provident Fund</fo:block>
                                            <fo:block>MITC Building, 6th Floor</fo:block>
                                            <fo:block>Akurli Road, Kandivali (East)</fo:block>
                                            <fo:block>Mumbai - 400101</fo:block>
                                        </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell>
                                        <fo:block>Tel: (022) 68135627</fo:block>
                                        <fo:block>68135628</fo:block>
                                    </fo:table-cell>
                                </fo:table-row>
                            </fo:table-body>
                        </fo:table>
                    </fo:block>
                    <!-- logo and address end -->

                    <fo:table margin-top="20pt" line-height="1">
                        <fo:table-column column-width="50%"/>
                        <fo:table-column column-width="50%"/>

                        <fo:table-body>
                            <fo:table-row>
                                <fo:table-cell>
                                    <fo:block text-align="left">
                                        <fo:block>Ref. No. SPF/ <xsl:value-of select="refNo"/></fo:block>
                                        <fo:block>Stl.S. No. : <xsl:value-of select="stlNo"/></fo:block>
                                    </fo:block>
                                </fo:table-cell>
                                <fo:table-cell>
                                    <fo:block text-align="right">
                                        <fo:block><xsl:value-of select="date"/></fo:block>
                                    </fo:block>
                                </fo:table-cell>
                            </fo:table-row>
                        </fo:table-body>

                    </fo:table>



                    <fo:table margin-top="20pt" line-height="1">
                        <fo:table-column column-width="75%"/>
                        <fo:table-column column-width="25%"/>

                        <fo:table-body>
                            <fo:table-row>
                                <fo:table-cell>
                                    <fo:block text-align="left" font-size="11" font-weight="600">
                                        <fo:block><xsl:value-of select="payeeName"/></fo:block>
                                        <fo:block><xsl:value-of select="addressLine1"/></fo:block>
                                        <fo:block><xsl:value-of select="addressLine2"/></fo:block>
                                        <fo:block><xsl:value-of select="addressLine3"/></fo:block>
                                        <fo:block><xsl:value-of select="addressLine4"/></fo:block>
                                    </fo:block>
                                </fo:table-cell>
                                <fo:table-cell>
                                    <fo:block text-align="right">
                                        <fo:block>Registered A. D.</fo:block>
                                    </fo:block>
                                </fo:table-cell>
                            </fo:table-row>
                        </fo:table-body>
                    </fo:table>

                    <fo:block margin-top="20pt" margin-bottom="10pt">Dear Sir/Madam, </fo:block>

                    <fo:block margin-left="40pt" word-spacing="8pt" line-height="1.2">
                        <fo:block>Sub : Transfer of Provident fund Accumulation of</fo:block>
                        <fo:block>Mr./Mrs. <xsl:value-of select="name"/></fo:block>
                        <fo:block>
                            <fo:inline padding-right="100pt">From : <xsl:value-of select="fromPfNumber"/></fo:inline>
                            <fo:block padding-right="100pt">To : <xsl:value-of select="toPfNumber"/></fo:block>
                        </fo:block>
                    </fo:block>

                    <fo:block margin-top="20pt" word-spacing="6pt"
                              line-height="1.2" text-align="justify">This has reference to your Form-13 application for transfer of
                        Provident Fund accumulations of the above ex-employee.</fo:block>

                    <fo:block margin-top="20pt" word-spacing="6pt"
                              line-height="1.2" text-align="justify">Please find enclosed our UTR/RRN Sr.No/IFT Ref No/cheque/DD no. <fo:inline><xsl:value-of select="chequeNumber"/></fo:inline>
                        dated <fo:inline><xsl:value-of select="paymentDate"/></fo:inline>
                    for Rs. <fo:inline><xsl:value-of select="amount"/></fo:inline> (<fo:inline><xsl:value-of select="amountInWords"/></fo:inline>).
                    drawn on <fo:inline><xsl:value-of select="bank"/></fo:inline>., Mumbai in your favour being the Provident Fund accumulations of
                    Mr./Mrs. <fo:inline><xsl:value-of select="name"/></fo:inline> together with Annexure "K" for your record.</fo:block>

                    <fo:block margin-top="20pt" word-spacing="6pt"
                              line-height="1.2" text-align="justify">Kindly credit the amount to the member''s account no
                    maintained by you and send us your stamped receipt for this payment.</fo:block>

                    <fo:block margin-top="20pt" margin-right="10%" line-height="1">
                        Thanking you,
                    </fo:block>


                    <fo:block margin-top="20pt" margin-right="10%" line-height="1" word-spacing="8pt">
                        <fo:block>Yours faithfully,</fo:block>
                        <fo:block>FOR MAHINDRA &amp; MAHINDRA LIMITED</fo:block>
                        <fo:block>STAFF PROVIDENT FUND</fo:block>
                    </fo:block>


                    <fo:block margin-top="40pt" margin-right="10%" word-spacing="8pt" line-height="1">
                        Authorized Signatory.
                    </fo:block>


                    <fo:block margin-top="15pt" margin-right="10%" word-spacing="8pt" line-height="1">
                        <fo:block>C.C.C/O <fo:inline><xsl:value-of select="name"/></fo:inline></fo:block>
                        <fo:block margin-left="58pt"><xsl:value-of select="currentEmployerName"/></fo:block>
                        <fo:block margin-left="58pt"><xsl:value-of select="currentEmployerAddressLine1"/></fo:block>
                        <fo:block margin-left="58pt"><xsl:value-of select="currentEmployerAddressLine2"/></fo:block>
                        <fo:block margin-left="58pt"><xsl:value-of select="currentEmployerAddressLine3"/></fo:block>
                        <fo:block margin-left="58pt"><xsl:value-of select="currentEmployerAddressLine4"/></fo:block>
                    </fo:block>

                </fo:flow>
            </fo:page-sequence>

        </fo:root>
    </xsl:template>

</xsl:stylesheet>
', 'TRANSFER_OUT_DISPATCH_LETTER', 2);

insert into pdf_document_design(created_at, entity_id, is_active, updated_at, document, document_type,
                                tenant_id)
VALUES (sysdate(), '', 1, sysdate(), '<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.1" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:fo="http://www.w3.org/1999/XSL/Format" exclude-result-prefixes="fo">

    <xsl:template match="workSheet">
        <fo:root xmlns:fo="http://www.w3.org/1999/XSL/Format">

            <fo:layout-master-set>
                <fo:simple-page-master master-name="simpleA4"
                                       page-width="29.7cm" page-height="21cm"
                                       margin-top="0.3cm" margin-bottom="0.3cm"
                                       margin-left="1cm" margin-right="1cm"
                                       >
                    <fo:region-body/>

                </fo:simple-page-master>


                <fo:simple-page-master master-name="annexure"
                                       page-width="29.7cm" page-height="21cm"
                                       margin-top="0.3cm" margin-bottom="0.3cm"
                                       margin-left="1cm" margin-right="1cm"
                >
                    <fo:region-body/>

                </fo:simple-page-master>



            </fo:layout-master-set>


            <fo:page-sequence master-reference="simpleA4">
                <fo:flow flow-name="xsl-region-body" font-size="11pt" line-height="1.2"
                         font-family="Times-Roman" word-spacing="6pt" letter-spacing="1pt">

                    <fo:block padding="10pt"
                              padding-top="0pt" padding-bottom="5pt"
                              border="1pt solid red">

                        <fo:table line-height="1">
                            <fo:table-column column-width="10%"/>
                            <fo:table-column column-width="90%"/>

                            <fo:table-body>
                                <fo:table-row>
                                    <fo:table-cell margin="0" padding="0">
                                        <fo:block margin="0" padding="0">
                                            <fo:external-graphic src="https://developerspftrust.coreintegra.com/images/mahindra_logo.png" content-height="75pt" content-width="75pt"/>
                                        </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell margin="0" padding="0">
                                        <fo:block margin="0" padding="0" text-align="center" line-height="1.2">
                                            <fo:block font-weight="600">Mahindra &amp; Mahindra Limited. Staff Provident Fund</fo:block>
                                            <fo:block>Statement by the Trustees to a member seceding from the fund</fo:block>
                                        </fo:block>
                                    </fo:table-cell>
                                </fo:table-row>
                            </fo:table-body>
                        </fo:table>
                        <!-- logo and address end -->

                        <fo:block margin-top="7pt">
                            <fo:table line-height="1">
                                <fo:table-column/>
                                <fo:table-column/>
                                <fo:table-column/>

                                <fo:table-body>
                                    <fo:table-row>
                                        <fo:table-cell margin="0" padding="0">
                                            <fo:block margin="0" padding="0">
                                                TransferOut No. : <fo:inline><xsl:value-of select="settlementNumber"/></fo:inline>
                                            </fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell margin="0" padding="0">
                                            <fo:block>Date of Cessation: <fo:inline><xsl:value-of select="dateOfCessation"/></fo:inline></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell margin="0" padding="0">
                                            <fo:block>Document No. : <fo:inline><xsl:value-of select="documentNo"/></fo:inline></fo:block>
                                        </fo:table-cell>
                                    </fo:table-row>
                                </fo:table-body>
                            </fo:table>
                        </fo:block>

                        <fo:block margin="10pt 0pt" margin-bottom="0pt" margin-top="0pt"
                                  border="1pt solid black" line-height="1" padding="5pt pt0">

                            <fo:block>
                                <fo:inline>Employee Name : <fo:inline><xsl:value-of select="name"/></fo:inline></fo:inline>
                                <fo:inline padding-left="30pt">Pern No. : <fo:inline><xsl:value-of select="pernNumber"/></fo:inline></fo:inline>
                                <fo:inline padding-left="30pt">P.F.No. : <fo:inline><xsl:value-of select="pfNumber"/></fo:inline></fo:inline>
                                <fo:inline padding-left="30pt">Unit Code. : <fo:inline><xsl:value-of select="unitCode"/></fo:inline></fo:inline>
                                <fo:inline padding-left="30pt">Status : <xsl:value-of select="status"/></fo:inline>
                            </fo:block>

                            <fo:block margin-top="5pt">
                                <fo:inline>Dt.of Birth : <fo:inline><xsl:value-of select="dateOfBirth"/></fo:inline></fo:inline>
                                <fo:inline padding-left="30pt">Dt.of Joining PF : <fo:inline><xsl:value-of select="dateOfJoiningPf"/></fo:inline></fo:inline>
                                <fo:inline padding-left="30pt">DOJ Previous Employer : <fo:inline><xsl:value-of select="dateOfJoiningPrior"/></fo:inline></fo:inline>
                                <fo:inline padding-left="30pt">Last Rec Dt. : <fo:inline><xsl:value-of select="lastRecoveryDate"/></fo:inline></fo:inline>
                            </fo:block>

                        </fo:block>

                        <fo:block margin="10pt 0pt" margin-top="0pt" margin-bottom="0"
                                  border="1pt solid black" border-top="0" line-height="1">

                            <fo:table >
                                <fo:table-column column-width="50%"/>
                                <fo:table-column column-width="50%"/>

                                <fo:table-body>
                                    <fo:table-row>
                                        <fo:table-cell margin="0" padding="0" border-right="1pt solid black">

                                            <fo:table >
                                                <fo:table-column column-width="50%"/>
                                                <fo:table-column column-width="25%"/>
                                                <fo:table-column column-width="25%"/>


                                                <fo:table-body>

                                                    <fo:table-row font-weight="600" line-height="1" font-size="11">
                                                        <fo:table-cell>
                                                            <fo:block>Nominees</fo:block>
                                                        </fo:table-cell>
                                                        <fo:table-cell>
                                                            <fo:block>Relationship</fo:block>
                                                        </fo:table-cell>
                                                        <fo:table-cell>
                                                            <fo:block>Share%</fo:block>
                                                        </fo:table-cell>
                                                    </fo:table-row>

                                                </fo:table-body>
                                            </fo:table>

                                        </fo:table-cell>
                                        <fo:table-cell margin="0" padding="0">

                                            <fo:table >


                                                <fo:table-column column-width="50%"/>
                                                <fo:table-column column-width="25%"/>
                                                <fo:table-column column-width="25%"/>


                                                <fo:table-body>

                                                    <fo:table-row font-weight="600" line-height="1" font-size="11">
                                                        <fo:table-cell>
                                                            <fo:block>Nominees</fo:block>
                                                        </fo:table-cell>
                                                        <fo:table-cell>
                                                            <fo:block>Relationship</fo:block>
                                                        </fo:table-cell>
                                                        <fo:table-cell>
                                                            <fo:block>Share%</fo:block>
                                                        </fo:table-cell>
                                                    </fo:table-row>

                                                </fo:table-body>
                                            </fo:table>

                                        </fo:table-cell>
                                    </fo:table-row>

                                </fo:table-body>
                            </fo:table>

                        </fo:block>


                        <fo:block margin="0pt"
                                  border="1pt solid black"
                                  border-top="0" line-height="1">

                            <fo:table >
                                <fo:table-column column-width="50%"/>
                                <fo:table-column column-width="50%"/>

                                <fo:table-body>
                                    <fo:table-row>
                                        <fo:table-cell margin="0" padding="0" border-right="1pt solid black">

                                            <fo:table >

                                                <fo:table-column column-width="50%"/>
                                                <fo:table-column column-width="25%"/>
                                                <fo:table-column column-width="25%"/>

                                                <fo:table-body>

                                                    <fo:table-row font-size="10" line-height="1.2">
                                                        <fo:table-cell padding="1pt" >
                                                            <fo:block><xsl:value-of select="/workSheet/nominee[1]/name"/></fo:block>
                                                        </fo:table-cell>
                                                        <fo:table-cell padding="1pt">
                                                            <fo:block><xsl:value-of select="/workSheet/nominee[1]/relationship"/></fo:block>
                                                        </fo:table-cell>
                                                        <fo:table-cell padding="1pt">
                                                            <fo:block><xsl:value-of select="/workSheet/nominee[1]/share"/></fo:block>
                                                        </fo:table-cell>
                                                    </fo:table-row>

                                                    <fo:table-row font-size="10" line-height="1.2">
                                                        <fo:table-cell padding="1pt" >
                                                            <fo:block><xsl:value-of select="/workSheet/nominee[2]/name"/></fo:block>
                                                        </fo:table-cell>
                                                        <fo:table-cell padding="1pt">
                                                            <fo:block><xsl:value-of select="/workSheet/nominee[2]/relationship"/></fo:block>
                                                        </fo:table-cell>
                                                        <fo:table-cell padding="1pt">
                                                            <fo:block><xsl:value-of select="/workSheet/nominee[2]/share"/></fo:block>
                                                        </fo:table-cell>
                                                    </fo:table-row>

                                                </fo:table-body>
                                            </fo:table>


                                        </fo:table-cell>
                                        <fo:table-cell margin="0" padding="0">

                                            <fo:table >
                                                <fo:table-column column-width="50%"/>
                                                <fo:table-column column-width="25%"/>
                                                <fo:table-column column-width="25%"/>

                                                <fo:table-body>


                                                    <fo:table-row font-size="10" line-height="1.2">
                                                        <fo:table-cell padding="1pt" >
                                                            <fo:block><xsl:value-of select="/workSheet/nominee[3]/name"/></fo:block>
                                                        </fo:table-cell>
                                                        <fo:table-cell padding="1pt">
                                                            <fo:block><xsl:value-of select="/workSheet/nominee[3]/relationship"/></fo:block>
                                                        </fo:table-cell>
                                                        <fo:table-cell padding="1pt">
                                                            <fo:block><xsl:value-of select="/workSheet/nominee[3]/share"/></fo:block>
                                                        </fo:table-cell>
                                                    </fo:table-row>

                                                    <fo:table-row font-size="10" line-height="1.2">
                                                        <fo:table-cell padding="1pt" >
                                                            <fo:block><xsl:value-of select="/workSheet/nominee[4]/name"/></fo:block>
                                                        </fo:table-cell>
                                                        <fo:table-cell padding="1pt">
                                                            <fo:block><xsl:value-of select="/workSheet/nominee[4]/relationship"/></fo:block>
                                                        </fo:table-cell>
                                                        <fo:table-cell padding="1pt">
                                                            <fo:block><xsl:value-of select="/workSheet/nominee[4]/share"/></fo:block>
                                                        </fo:table-cell>
                                                    </fo:table-row>

                                                </fo:table-body>
                                            </fo:table>

                                        </fo:table-cell>
                                    </fo:table-row>

                                </fo:table-body>
                            </fo:table>

                        </fo:block>


                        <fo:block line-height="1" margin-top="10pt" font-size="11" font-weight="600">

                            <fo:table>
                                <fo:table-column column-width="40%"/>
                                <fo:table-column column-width="15%"/>
                                <fo:table-column column-width="15%"/>
                                <fo:table-column column-width="15%"/>
                                <fo:table-column column-width="15%"/>

                                <fo:table-body>

                                    <fo:table-row>
                                        <fo:table-cell text-align="left">
                                            <fo:block>Account Particulars (Figures in Rupees)</fo:block>
                                            <fo:block>====================</fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right">
                                            <fo:block>Member</fo:block>
                                            <fo:block>Contribution</fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right">
                                            <fo:block>Company</fo:block>
                                            <fo:block>Contribution</fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right">
                                            <fo:block>Vpf</fo:block>
                                            <fo:block>Contribution</fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right">
                                            <fo:block>Total</fo:block>
                                            <fo:block>(Rupees)</fo:block>
                                        </fo:table-cell>
                                    </fo:table-row>

                                </fo:table-body>
                            </fo:table>
                        </fo:block>

                        <fo:block margin-top="10pt">Additions: </fo:block>

                        <fo:block margin-top="5pt">

                            <fo:table>
                                <fo:table-column column-width="40%" />
                                <fo:table-column column-width="15%"/>
                                <fo:table-column column-width="15%"/>
                                <fo:table-column column-width="15%"/>
                                <fo:table-column column-width="15%"/>

                                <fo:table-body>

                                    <fo:table-row>
                                        <fo:table-cell text-align="left">
                                            <fo:block>Opening Balance as on <fo:inline><xsl:value-of select="yearOpeningDate"/></fo:inline></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right">
                                            <fo:block><xsl:value-of select="yearOpeningMemberContribution"/></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right">
                                            <fo:block><xsl:value-of select="yearOpeningCompanyContribution"/></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right">
                                            <fo:block><xsl:value-of select="yearOpeningVpfContribution"/></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right">
                                            <fo:block><xsl:value-of select="yearOpeningTotalContribution"/></fo:block>
                                        </fo:table-cell>
                                    </fo:table-row>

                                    <fo:table-row>
                                        <fo:table-cell text-align="left" padding-top="10pt">
                                            <fo:block>Interest on Opening Balance @ <fo:inline><xsl:value-of select="interestRate"/></fo:inline>% p.a</fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right"  padding-top="10pt">
                                            <fo:block><xsl:value-of select="interestOnYearOpeningMemberContribution"/></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right" padding-top="10pt">
                                            <fo:block><xsl:value-of select="interestOnYearOpeningCompanyContribution"/></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right" padding-top="10pt">
                                            <fo:block><xsl:value-of select="interestOnYearOpeningVpfContribution"/></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right" padding-top="10pt">
                                            <fo:block><xsl:value-of select="interestOnYearOpeningTotalContribution"/></fo:block>
                                        </fo:table-cell>
                                    </fo:table-row>

                                    <fo:table-row>
                                        <fo:table-cell text-align="left" padding-top="10pt">
                                            <fo:block>Contributions for the year</fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right" padding-top="10pt">
                                            <fo:block><xsl:value-of select="currentYearMemberContribution"/></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right" padding-top="10pt">
                                            <fo:block><xsl:value-of select="currentYearCompanyContribution"/></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right" padding-top="10pt">
                                            <fo:block><xsl:value-of select="currentYearVpfContribution"/></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right" padding-top="10pt">
                                            <fo:block><xsl:value-of select="currentYearTotalContribution"/></fo:block>
                                        </fo:table-cell>
                                    </fo:table-row>

                                    <fo:table-row>
                                        <fo:table-cell text-align="left" padding-top="10pt">
                                            <fo:block>Interest on Contribution for the year @ <fo:inline><xsl:value-of select="interestRate"/></fo:inline>% p.a</fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right"  padding-top="10pt">
                                            <fo:block><xsl:value-of select="interestOnCurrentYearMemberContribution"/></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right" padding-top="10pt">
                                            <fo:block><xsl:value-of select="interestOnCurrentYearCompanyContribution"/></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right" padding-top="10pt">
                                            <fo:block><xsl:value-of select="interestOnCurrentYearVpfContribution"/></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right" padding-top="10pt">
                                            <fo:block><xsl:value-of select="interestOnCurrentYearTotalContribution"/></fo:block>
                                        </fo:table-cell>
                                    </fo:table-row>


                                    <fo:table-row>
                                        <fo:table-cell text-align="left" padding-top="10pt">
                                            <fo:block>Transfer from Other Funds</fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right" padding-top="10pt">
                                            <fo:block><xsl:value-of select="currentYearTransferInMemberContribution"/></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right" padding-top="10pt">
                                            <fo:block><xsl:value-of select="currentYearTransferInCompanyContribution"/></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right" padding-top="10pt">
                                            <fo:block><xsl:value-of select="currentYearTransferInVpfContribution"/></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right" padding-top="10pt">
                                            <fo:block><xsl:value-of select="currentYearTransferInTotalContribution"/></fo:block>
                                        </fo:table-cell>
                                    </fo:table-row>

                                    <fo:table-row>
                                        <fo:table-cell text-align="left" padding-top="10pt">
                                            <fo:block>Interest on Transfer In @ <fo:inline><xsl:value-of select="interestRate"/></fo:inline>% p.a</fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right"  padding-top="10pt">
                                            <fo:block><xsl:value-of select="interestOnCurrentYearTransferInMemberContribution"/></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right" padding-top="10pt">
                                            <fo:block><xsl:value-of select="interestOnCurrentYearTransferInCompanyContribution"/></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right" padding-top="10pt">
                                            <fo:block><xsl:value-of select="interestOnCurrentYearTransferInVpfContribution"/></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right" padding-top="10pt">
                                            <fo:block><xsl:value-of select="interestOnCurrentYearTransferInTotalContribution"/></fo:block>
                                        </fo:table-cell>
                                    </fo:table-row>

                                </fo:table-body>
                            </fo:table>

                        </fo:block>

                        <fo:block margin-top="10pt">Less: </fo:block>


                        <fo:block margin-top="5pt">

                            <fo:table>
                                <fo:table-column column-width="40%" />
                                <fo:table-column column-width="15%"/>
                                <fo:table-column column-width="15%"/>
                                <fo:table-column column-width="15%"/>
                                <fo:table-column column-width="15%"/>

                                <fo:table-body>

                                    <fo:table-row>
                                        <fo:table-cell text-align="left">
                                            <fo:block>NRL Withdrawals during the year</fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right">
                                            <fo:block><xsl:value-of select="currentYearLoanWithdrawalMemberContribution"/></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right">
                                            <fo:block><xsl:value-of select="currentYearLoanWithdrawalCompanyContribution"/></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right">
                                            <fo:block><xsl:value-of select="currentYearLoanWithdrawalVpfContribution"/></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right">
                                            <fo:block><xsl:value-of select="currentYearLoanWithdrawalTotalContribution"/></fo:block>
                                        </fo:table-cell>
                                    </fo:table-row>

                                    <fo:table-row>
                                        <fo:table-cell text-align="left" padding-top="10pt">
                                            <fo:block>Interest on NR withdrawals @ <fo:inline><xsl:value-of select="interestRate"/></fo:inline> % p.a</fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right"  padding-top="10pt">
                                            <fo:block><xsl:value-of select="interestOnCurrentYearLoanWithdrawalMemberContribution"/></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right" padding-top="10pt">
                                            <fo:block><xsl:value-of select="interestOnCurrentYearLoanWithdrawalCompanyContribution"/></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right" padding-top="10pt">
                                            <fo:block><xsl:value-of select="interestOnCurrentYearLoanWithdrawalVpfContribution"/></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right" padding-top="10pt">
                                            <fo:block><xsl:value-of select="interestOnCurrentYearLoanWithdrawalTotalContribution"/></fo:block>
                                        </fo:table-cell>
                                    </fo:table-row>

                                </fo:table-body>
                            </fo:table>

                        </fo:block>




                        <fo:block margin-top="10pt" border-top="1pt dashed black"
                                  border-bottom="1pt dashed black" padding="5pt 0pt">

                            <fo:table>
                                <fo:table-column column-width="40%"/>
                                <fo:table-column column-width="15%"/>
                                <fo:table-column column-width="15%"/>
                                <fo:table-column column-width="15%"/>
                                <fo:table-column column-width="15%"/>

                                <fo:table-body>

                                    <fo:table-row>
                                        <fo:table-cell text-align="left">
                                            <fo:block>PF Balance Due/Settlement Date: <fo:inline><xsl:value-of select="dueDate"/></fo:inline></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right">
                                            <fo:block><xsl:value-of select="totalMemberContribution"/></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right">
                                            <fo:block><xsl:value-of select="totalCompanyContribution"/></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right">
                                            <fo:block><xsl:value-of select="totalVpfContribution"/></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right">
                                            <fo:block><xsl:value-of select="totalContribution"/></fo:block>
                                        </fo:table-cell>
                                    </fo:table-row>

                                </fo:table-body>
                            </fo:table>

                        </fo:block>


                        <fo:block margin-top="10pt">
                            <fo:table>
                                <fo:table-column column-width="20%" />
                                <fo:table-column column-width="10%"/>
                                <fo:table-column column-width="20%"/>
                                <fo:table-column column-width="10%"/>
                                <fo:table-column column-width="15%"/>
                                <fo:table-column column-width="25%"/>

                                <fo:table-body>

                                    <fo:table-row>
                                        <fo:table-cell text-align="center">
                                            <fo:block></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="center">
                                            <fo:block></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right">
                                            <fo:block></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right">
                                            <fo:block></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right">
                                            <fo:block>NET Amount:</fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right">
                                            <fo:block><xsl:value-of select="netContribution"/></fo:block>
                                        </fo:table-cell>
                                    </fo:table-row>


                                </fo:table-body>
                            </fo:table>

                        </fo:block>

                        <fo:block margin-top="10pt" margin-left="60%">
                            <fo:block text-align="center">For Mahindra &amp; Mahindra Limited</fo:block>
                            <fo:block text-align="center">Staff Provident Fund</fo:block>
                            <fo:block text-align="center" margin-top="35pt">Authorized Signatory</fo:block>
                        </fo:block>

                        <fo:block>
                            <fo:inline>Date:- <xsl:value-of select="processedOnDate"/></fo:inline>
                        </fo:block>

                    </fo:block>

            </fo:flow>
            </fo:page-sequence>




            <fo:page-sequence master-reference="annexure">
                <fo:flow flow-name="xsl-region-body" font-size="11pt" line-height="1.2"
                         font-family="Times-Roman" word-spacing="6pt" letter-spacing="1pt">

                    <fo:block>

                        <fo:table line-height="1">
                            <fo:table-column column-width="13%"/>
                            <fo:table-column column-width="87%"/>

                            <fo:table-body>
                                <fo:table-row>
                                    <fo:table-cell margin="0" padding="0">
                                        <fo:block margin="0" padding="0">
                                            <fo:external-graphic src="https://developerspftrust.coreintegra.com/images/mahindra_logo.png" content-height="100pt" content-width="100pt"/>
                                        </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell margin="0" padding="0">
                                        <fo:block margin="0" padding="0" text-align="center" line-height="1.2">
                                            <fo:block font-weight="600">MAHINDRA &amp; MAHINDRA LIMITED STAFF PROVIDENT FUND</fo:block>
                                            <fo:block>ANNEXURE TO SETTLEMENT STATEMENT AS ON <xsl:value-of select="settlementAnnexure/date"/></fo:block>
                                        </fo:block>
                                    </fo:table-cell>
                                </fo:table-row>
                            </fo:table-body>
                        </fo:table>
                        <!-- logo and address end -->

                        <fo:block margin="10pt 0pt" margin-bottom="0pt" margin-top="0pt"
                                  border="1pt solid black" line-height="1" padding="5pt pt0">

                            <fo:inline>Employee Name : <xsl:value-of select="settlementAnnexure/name"/></fo:inline>
                            <fo:inline padding-left="30pt">Token No. :   <xsl:value-of select="tokenNumber"/></fo:inline>
                            <fo:inline padding-left="30pt">P.F.No. :  <xsl:value-of select="pfNumber"/></fo:inline>
                            <fo:inline padding-left="30pt">Unit Code. :  <xsl:value-of select="unitCode"/></fo:inline>
                            <fo:inline padding-left="30pt">Dept.Cd :  <xsl:value-of select="deptCode"/></fo:inline>

                        </fo:block>

                        <fo:block margin-top="10pt" font-weight="600">Month Wise contribution and Interest (Figures in Rupees)</fo:block>

                        <fo:block margin="20pt 0pt" margin-bottom="0pt" margin-top="0pt"
                                  border="1pt solid black" text-align="center"
                                  line-height="1">

                            <fo:table line-height="1">
                                <fo:table-column column-width="11%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="11%"/>

                                <fo:table-body>
                                    <fo:table-row>
                                        <fo:table-cell padding="2pt 0pt">
                                            <fo:block></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell padding="2pt 0pt">
                                            <fo:block>Apr</fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell padding="2pt 0pt">
                                            <fo:block>May</fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell padding="2pt 0pt">
                                            <fo:block>Jun</fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell padding="2pt 0pt">
                                            <fo:block>Jul</fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell padding="2pt 0pt">
                                            <fo:block>Aug</fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell padding="2pt 0pt">
                                            <fo:block>Sep</fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell padding="2pt 0pt">
                                            <fo:block>Oct</fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell padding="2pt 0pt">
                                            <fo:block>Nov</fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell padding="2pt 0pt">
                                            <fo:block>Dec</fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell padding="2pt 0pt">
                                            <fo:block>Jan</fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell padding="2pt 0pt">
                                            <fo:block>Feb</fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell padding="2pt 0pt">
                                            <fo:block>Mar</fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell padding="2pt 0pt">
                                            <fo:block>Total</fo:block>
                                        </fo:table-cell>
                                    </fo:table-row>
                                </fo:table-body>
                            </fo:table>
                        </fo:block>

                        <fo:block margin="10pt 0pt" margin-bottom="0pt" margin-top="0pt"
                                  border="1pt solid black" border-top="0" font-size="9" text-align="right"
                                  line-height="1">

                            <fo:table line-height="1">

                                <fo:table-column column-width="11%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="11%"/>

                                <fo:table-body>
                                    <fo:table-row>
                                        <fo:table-cell padding="14pt 0pt">
                                            <fo:block text-align="left"
                                                      font-size="10" font-weight="600">MEM. CONT A/C</fo:block>
                                        </fo:table-cell>

                                        <xsl:for-each select="settlementAnnexure/memberContributions">
                                            <fo:table-cell padding="14pt 0pt">
                                                <fo:block> <xsl:value-of select="amount"/></fo:block>
                                            </fo:table-cell>
                                        </xsl:for-each>

                                        <fo:table-cell padding="14pt 0pt">
                                            <fo:block> <xsl:value-of select="settlementAnnexure/totalMemberContributions"/></fo:block>
                                        </fo:table-cell>

                                    </fo:table-row>
                                </fo:table-body>
                            </fo:table>
                        </fo:block>



                        <fo:block margin="10pt 0pt" margin-bottom="0pt" margin-top="0pt"
                                  border="1pt solid black" border-top="0" font-size="9" text-align="right"
                                  line-height="1">

                            <fo:table line-height="1">

                                <fo:table-column column-width="11%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="11%"/>

                                <fo:table-body>
                                    <fo:table-row>
                                        <fo:table-cell padding="14pt 0pt">
                                            <fo:block text-align="left" font-size="10"
                                                      font-weight="600">COMP. CONT A/C</fo:block>
                                        </fo:table-cell>

                                        <xsl:for-each select="settlementAnnexure/CompanyContributions">
                                            <fo:table-cell padding="14pt 0pt">
                                                <fo:block> <xsl:value-of select="amount"/></fo:block>
                                            </fo:table-cell>
                                        </xsl:for-each>

                                        <fo:table-cell padding="14pt 0pt">
                                            <fo:block> <xsl:value-of select="settlementAnnexure/totalCompanyContributions"/></fo:block>
                                        </fo:table-cell>

                                    </fo:table-row>
                                </fo:table-body>
                            </fo:table>
                        </fo:block>


                        <fo:block margin="10pt 0pt" margin-bottom="0pt" margin-top="0pt"
                                  border="1pt solid black" border-top="0" font-size="9" text-align="right"
                                  line-height="1">

                            <fo:table line-height="1">

                                <fo:table-column column-width="11%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="11%"/>

                                <fo:table-body>
                                    <fo:table-row>
                                        <fo:table-cell padding="14pt 0pt">
                                            <fo:block text-align="left" font-size="10" font-weight="600">V.P.F. CONT A/C</fo:block>
                                        </fo:table-cell>

                                        <xsl:for-each select="settlementAnnexure/vpfContributions">
                                            <fo:table-cell padding="14pt 0pt">
                                                <fo:block> <xsl:value-of select="amount"/></fo:block>
                                            </fo:table-cell>
                                        </xsl:for-each>

                                        <fo:table-cell padding="14pt 0pt">
                                            <fo:block> <xsl:value-of select="settlementAnnexure/totalVpfContributions"/></fo:block>
                                        </fo:table-cell>

                                    </fo:table-row>
                                </fo:table-body>
                            </fo:table>
                        </fo:block>




                        <fo:block margin="10pt 0pt" margin-bottom="0pt" margin-top="0pt"
                                  border="1pt solid black" border-top="0" font-size="9" text-align="right"
                                  line-height="1">

                            <fo:table line-height="1">

                                <fo:table-column column-width="11%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="11%"/>

                                <fo:table-body>
                                    <fo:table-row>
                                        <fo:table-cell padding="14pt 0pt">
                                            <fo:block text-align="left" font-size="10" font-weight="600">Total Rs.</fo:block>
                                        </fo:table-cell>

                                        <xsl:for-each select="settlementAnnexure/totalContributionsMonthWise">
                                            <fo:table-cell padding="14pt 0pt">
                                                <fo:block> <xsl:value-of select="amount"/></fo:block>
                                            </fo:table-cell>
                                        </xsl:for-each>

                                        <fo:table-cell padding="14pt 0pt">
                                            <fo:block><xsl:value-of select="settlementAnnexure/totalContribution"/></fo:block>
                                        </fo:table-cell>


                                    </fo:table-row>
                                </fo:table-body>
                            </fo:table>
                        </fo:block>



                        <fo:block margin-top="10pt" font-weight="600"><xsl:value-of select="settlementAnnexure/interestRate"/> Month wise Interest (Figures in Rupees)
                            [on monthly running balance met]</fo:block>

                        <fo:block margin="20pt 0pt" margin-bottom="0pt" margin-top="0pt"
                                  border="1pt solid black" text-align="center"
                                  line-height="1">

                            <fo:table line-height="1">
                                <fo:table-column column-width="11%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="11%"/>

                                <fo:table-body>
                                    <fo:table-row>
                                        <fo:table-cell padding="2pt 0pt">
                                            <fo:block></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell padding="2pt 0pt">
                                            <fo:block>Apr</fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell padding="2pt 0pt">
                                            <fo:block>May</fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell padding="2pt 0pt">
                                            <fo:block>Jun</fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell padding="2pt 0pt">
                                            <fo:block>Jul</fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell padding="2pt 0pt">
                                            <fo:block>Aug</fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell padding="2pt 0pt">
                                            <fo:block>Sep</fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell padding="2pt 0pt">
                                            <fo:block>Oct</fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell padding="2pt 0pt">
                                            <fo:block>Nov</fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell padding="2pt 0pt">
                                            <fo:block>Dec</fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell padding="2pt 0pt">
                                            <fo:block>Jan</fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell padding="2pt 0pt">
                                            <fo:block>Feb</fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell padding="2pt 0pt">
                                            <fo:block>Mar</fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell padding="2pt 0pt">
                                            <fo:block>Total</fo:block>
                                        </fo:table-cell>
                                    </fo:table-row>
                                </fo:table-body>
                            </fo:table>
                        </fo:block>

                        <fo:block margin="10pt 0pt" margin-bottom="0pt" margin-top="0pt"
                                  border="1pt solid black" border-top="0" font-size="9" text-align="right"
                                  line-height="1">

                            <fo:table line-height="1">

                                <fo:table-column column-width="11%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="11%"/>

                                <fo:table-body>
                                    <fo:table-row>
                                        <fo:table-cell padding="14pt 0pt">
                                            <fo:block text-align="left"
                                                      font-size="10" font-weight="600">MEM. CONT A/C</fo:block>
                                        </fo:table-cell>

                                        <xsl:for-each select="settlementAnnexure/interestOnMemberContributions">
                                            <fo:table-cell padding="14pt 0pt">
                                                <fo:block> <xsl:value-of select="amount"/></fo:block>
                                            </fo:table-cell>
                                        </xsl:for-each>

                                        <fo:table-cell padding="14pt 0pt">
                                            <fo:block><xsl:value-of select="settlementAnnexure/totalInterestOnMemberContribution"/></fo:block>
                                        </fo:table-cell>



                                    </fo:table-row>
                                </fo:table-body>
                            </fo:table>
                        </fo:block>



                        <fo:block margin="10pt 0pt" margin-bottom="0pt" margin-top="0pt"
                                  border="1pt solid black" border-top="0" font-size="9" text-align="right"
                                  line-height="1">

                            <fo:table line-height="1">

                                <fo:table-column column-width="11%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="11%"/>

                                <fo:table-body>
                                    <fo:table-row>
                                        <fo:table-cell padding="14pt 0pt">
                                            <fo:block text-align="left" font-size="10"
                                                      font-weight="600">COMP. CONT A/C</fo:block>
                                        </fo:table-cell>

                                        <xsl:for-each select="settlementAnnexure/interestOnCompanyContributions">
                                            <fo:table-cell padding="14pt 0pt">
                                                <fo:block> <xsl:value-of select="amount"/></fo:block>
                                            </fo:table-cell>
                                        </xsl:for-each>

                                        <fo:table-cell padding="14pt 0pt">
                                            <fo:block><xsl:value-of select="settlementAnnexure/totalInterestOnCompanyContribution"/></fo:block>
                                        </fo:table-cell>

                                    </fo:table-row>
                                </fo:table-body>
                            </fo:table>
                        </fo:block>


                        <fo:block margin="10pt 0pt" margin-bottom="0pt" margin-top="0pt"
                                  border="1pt solid black" border-top="0" font-size="9" text-align="right"
                                  line-height="1">

                            <fo:table line-height="1">

                                <fo:table-column column-width="11%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="11%"/>

                                <fo:table-body>
                                    <fo:table-row>
                                        <fo:table-cell padding="14pt 0pt">
                                            <fo:block text-align="left" font-size="10" font-weight="600">V.P.F. CONT A/C </fo:block>
                                        </fo:table-cell>


                                        <xsl:for-each select="settlementAnnexure/interestOnVpfContributions">
                                            <fo:table-cell padding="14pt 0pt">
                                                <fo:block> <xsl:value-of select="amount"/></fo:block>
                                            </fo:table-cell>
                                        </xsl:for-each>

                                        <fo:table-cell padding="14pt 0pt">
                                            <fo:block><xsl:value-of select="settlementAnnexure/totalInterestOnVpfContribution"/></fo:block>
                                        </fo:table-cell>

                                    </fo:table-row>
                                </fo:table-body>
                            </fo:table>
                        </fo:block>




                        <fo:block margin="10pt 0pt" margin-bottom="0pt" margin-top="0pt"
                                  border="1pt solid black" border-top="0" font-size="9" text-align="right"
                                  line-height="1">

                            <fo:table line-height="1">

                                <fo:table-column column-width="11%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="11%"/>

                                <fo:table-body>
                                    <fo:table-row>
                                        <fo:table-cell padding="14pt 0pt">
                                            <fo:block text-align="left" font-size="10" font-weight="600">Total Rs. </fo:block>
                                        </fo:table-cell>


                                        <xsl:for-each select="settlementAnnexure/totalInterestMonthWise">
                                            <fo:table-cell padding="14pt 0pt">
                                                <fo:block> <xsl:value-of select="amount"/></fo:block>
                                            </fo:table-cell>
                                        </xsl:for-each>

                                        <fo:table-cell padding="14pt 0pt">
                                            <fo:block><xsl:value-of select="settlementAnnexure/totalInterest"/></fo:block>
                                        </fo:table-cell>

                                    </fo:table-row>
                                </fo:table-body>
                            </fo:table>
                        </fo:block>

                    </fo:block>

                </fo:flow>
            </fo:page-sequence>



        </fo:root>
    </xsl:template>

</xsl:stylesheet>
', 'TRANSFER_OUT_WORK_SHEET', 2);

-- 21. Create Email Design Templates
insert into notification_email_design(created_at, entity_id, is_active, updated_at, document, document_type,
                                      created_by, updated_by, tenant_id)
values (sysdate(), '', 1, sysdate(), '<!doctype html>
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
    <meta name="viewport" content="width=device-width">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <!-- Turn off iOS phone number autodetect -->
    <meta name="format-detection" content="telephone=no">
    <style>
        body, p {
            font-family: ''Helvetica Neue'', Helvetica,Arial, sans-serif;
            -webkit-font-smoothing: antialiased;
            -webkit-text-size-adjust: none;
        }
        table {
            border-collapse: collapse;
            border-spacing: 0;
            border: 0;
            padding: 0;
        }
        img {
            margin: 0;
            padding: 0;
        }

        .content {
            width: 600px;
        }

        .no_text_resize {
            -moz-text-size-adjust: none;
            -webkit-text-size-adjust: none;
            -ms-text-size-adjust: none;
            text-size-adjust: none;
        }

        /* Media Queries */
        @media all and (max-width: 600px) {

            table[class="content"] {
                width: 100% !important;
            }

            tr[class="grid-no-gutter"] td[class="grid__col"] {
                padding-left: 0 !important;
                padding-right: 0 !important;
            }

            td[class="grid__col"] {
                padding-left: 18px !important;
                padding-right: 18px !important;
            }

            table[class="small_full_width"] {
                width: 100% !important;
                padding-bottom: 10px;
            }

            a[class="header-link"] {
                margin-right: 0 !important;
                margin-left: 10px !important;
            }

            a[class="btn"] {
                width: 100%;
                border-left-width: 0px !important;
                border-right-width: 0px !important;
            }

            table[class="col-layout"] {
                width: 100% !important;
            }

            td[class="col-container"] {
                display: block !important;
                width: 100% !important;
                padding-left: 0 !important;
                padding-right: 0 !important;
            }

            td[class="col-nav-items"] {
                display: inline-block !important;
                padding-left: 0 !important;
                padding-right: 10px !important;
                background: none !important;
            }

            img[class="col-img"] {
                height: auto !important;
                max-width: 520px !important;
                width: 100% !important;
            }

            td[class="col-center-sm"] {
                text-align: center;
            }

            tr[class="footer-attendee-cta"] > td[class="grid__col"] {
                padding: 24px 0 0 !important;
            }

            td[class="col-footer-cta"] {
                padding-left: 0 !important;
                padding-right: 0 !important;
            }

            td[class="footer-links"] {
                text-align: left !important;
            }

            .hide-for-small {
                display: none !important;
            }

            .ribbon-mobile {
                line-height: 1.3 !important;
            }

            .small_full_width {
                width: 100% !important;
                padding-bottom: 10px;
            }

            .table__ridge {
                height: 7px !important;
            }

            .table__ridge img {
                display: none !important;
            }

            .table__ridge--top {
                background-image: url(https://cdn.evbstatic.com/s3-s3/marketing/emails/modules/ridges_top_fullx2.jpg) !important;
                background-size: 170% 7px;
            }

            .table__ridge--bottom {
                background-image: url(https://cdn.evbstatic.com/s3-s3/marketing/emails/modules/ridges_bottom_fullx2.jpg) !important;
                background-size: 170% 7px;
            }

            .summary-table__total {
                padding-right: 10px !important;
            }

            .app-cta {
                display: none !important;
            }

            .app-cta__mobile {
                width: 100% !important;
                height: auto !important;
                max-height: none !important;
                overflow: visible !important;
                float: none !important;
                display: block !important;
                margin-top: 12px !important;
                visibility: visible;
                font-size: inherit !important;
            }

            /* List Event Cards */
            .list-card__header {
                width: 130px !important;
            }

            .list-card__label {
                width: 130px !important;
            }

            .list-card__image-wrapper {
                width: 130px !important;
                height: 65px !important;
            }

            .list-card__image {
                max-width: 130px !important;
                max-height: 65px !important;
            }

            .list-card__body {
                padding-left: 10px !important;
            }

            .list-card__title {
                margin-bottom: 10px !important;
            }

            .list-card__date {
                padding-top: 0 !important;
            }
        }

        @media all and (device-width: 768px) and (device-height: 1024px) and (orientation:landscape) {
            .ribbon-mobile {
                line-height: 1.3 !important;
            }

            .ribbon-mobile__text {
                padding: 0 !important;
            }
        }

        @media all and (device-width: 768px) and (device-height: 1024px) and (orientation:portrait) {
            .ribbon-mobile {
                line-height: 1.3 !important;
            }

            .ribbon-mobile__text {
                padding: 0 !important;
            }
        }

        @media screen and (min-device-height:480px) and (max-device-height:568px), (min-device-width : 375px) and (max-device-width : 667px) and (-webkit-min-device-pixel-ratio : 2), (min-device-width : 414px) and (max-device-width : 736px) and (-webkit-min-device-pixel-ratio : 3) {

            .hide_for_iphone {
                display: none !important;
            }

            .passbook {
                width: auto !important;
                height: auto !important;
                line-height: auto !important;
                visibility: visible !important;
                display: block !important;
                max-height: none !important;
                overflow: visible !important;
                float: none !important;
                text-indent: 0 !important;
                font-size: inherit !important;
            }
        }
    </style>
</head>
<!-- Global container with background styles. Gmail converts BODY to DIV so we
  lose properties like BGCOLOR. -->

<body border="0" cellpadding="0" cellspacing="0" height="100%" width="100%" bgcolor="#F7F7F7" style="margin: 0;">
<table border="0" cellpadding="0" cellspacing="0" height="100%" width="100%" bgcolor="#F7F7F7">
    <tr>
        <td>
            <!--[if (gte mso 9)|(IE)]>
            <table width="600" align="center" cellpadding="0" cellspacing="0" border="0" bgcolor="#FFFFFF">
                <tr>
                    <td>
            <![endif]-->
            <table class="content" align="center" cellpadding="0" cellspacing="0" border="0" bgcolor="#F7F7F7" style="width: 600px; max-width: 600px;">
                <tr>
                    <td colspan="2" style="background: #fff; border-radius: 8px;">
                        <table border="0" cellpadding="0" cellspacing="0" width="100%">
                            <tr>
                                <td style="font-family: ''Helvetica Neue'', Helvetica, Arial, sans-serif;">
                            <tr class="">
                                <td class="grid__col" style="font-family: ''Helvetica neue'', Helvetica, arial, sans-serif; padding: 32px 40px; ">

                                    <h2 style="color: #404040; font-weight: 300; margin: 0 0 12px 0; font-size: 24px; line-height: 30px; font-family: ''Helvetica neue'', Helvetica, arial, sans-serif; ">

                                        Dear {{name}},

                                    </h2>

                                    <p style="color: #282828; font-weight: 400; font-size: 15px; line-height: 21px; font-family: ''Helvetica neue'', Helvetica, arial, sans-serif; " class="">We have credited to your account number :
                                        {{accountNumber}} with on {{paymentDate}} for amount of {{netCredit}} Rupees on behalf of MAHINDRA & MAHINDRA LIMITED STAFF PROVIDENT FUND  FUND towards your PF Loan/Withdrawal application.</p>
                                    <br>
                                    <br>
                                    <br>
                                    <br>
                                    <p style="color: #282828; font-weight: 400; font-size: 15px; line-height: 21px; font-family: ''Helvetica neue'', Helvetica, arial, sans-serif; " class="">This is system generated mail. Do not reply.</p>
                                    <br>
                                    <br>
                                    <br>
                                    <p style="color: #282828; font-weight: 400; font-size: 15px; line-height: 21px; font-family: ''Helvetica neue'', Helvetica, arial, sans-serif; " class="">Thanks and Regards</p>
                                    <br>
                                    <p style="color: #282828; font-weight: 400; font-size: 15px; line-height: 21px; font-family: ''Helvetica neue'', Helvetica, arial, sans-serif; " class="">M&M PF Trust</p>
                                </td>
                            </tr>
                            </td>
                            </tr>
                        </table>
                    </td>
                </tr>
            </table>
            <!--[if (gte mso 9)|(IE)]>
            </td>
            </tr>
            </table>
            <![endif]-->
            <!--[if (gte mso 9)|(IE)]>
            <table width="600" align="center" cellpadding="0" cellspacing="0" border="0">
                <tr>
                    <td>
            <![endif]-->
            <table class="content" align="center" cellpadding="0" cellspacing="0" border="0" style="width: 600px; max-width: 600px; font-family: Helvetica, Arial, sans-serif;">
                <tr>
                    <td style="padding-top: 24px;">
                        <table cellspacing="0" cellpadding="0" width="100%">
                            <tr>
                                <td style="background-color: #dedede;  width: 100%; font-size: 1px; height: 1px; line-height: 1px;">&nbsp;</td>
                            </tr>
                        </table>
                    </td>
                </tr>
            </table>
            <!--[if (gte mso 9)|(IE)]>
            </td>
            </tr>
            </table>
            <![endif]-->
        </td>
    </tr>
</table>
</body>

</html>', 'LOAN_COMPLETION', null, null, 2),
       (sysdate(), '', 1, sysdate(), '<!doctype html>
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
    <meta name="viewport" content="width=device-width">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <!-- Turn off iOS phone number autodetect -->
    <meta name="format-detection" content="telephone=no">
    <style>
        body, p {
            font-family: ''Helvetica Neue'', Helvetica,Arial, sans-serif;
            -webkit-font-smoothing: antialiased;
            -webkit-text-size-adjust: none;
        }
        table {
            border-collapse: collapse;
            border-spacing: 0;
            border: 0;
            padding: 0;
        }
        img {
            margin: 0;
            padding: 0;
        }

        .content {
            width: 600px;
        }

        .no_text_resize {
            -moz-text-size-adjust: none;
            -webkit-text-size-adjust: none;
            -ms-text-size-adjust: none;
            text-size-adjust: none;
        }

        /* Media Queries */
        @media all and (max-width: 600px) {

            table[class="content"] {
                width: 100% !important;
            }

            tr[class="grid-no-gutter"] td[class="grid__col"] {
                padding-left: 0 !important;
                padding-right: 0 !important;
            }

            td[class="grid__col"] {
                padding-left: 18px !important;
                padding-right: 18px !important;
            }

            table[class="small_full_width"] {
                width: 100% !important;
                padding-bottom: 10px;
            }

            a[class="header-link"] {
                margin-right: 0 !important;
                margin-left: 10px !important;
            }

            a[class="btn"] {
                width: 100%;
                border-left-width: 0px !important;
                border-right-width: 0px !important;
            }

            table[class="col-layout"] {
                width: 100% !important;
            }

            td[class="col-container"] {
                display: block !important;
                width: 100% !important;
                padding-left: 0 !important;
                padding-right: 0 !important;
            }

            td[class="col-nav-items"] {
                display: inline-block !important;
                padding-left: 0 !important;
                padding-right: 10px !important;
                background: none !important;
            }

            img[class="col-img"] {
                height: auto !important;
                max-width: 520px !important;
                width: 100% !important;
            }

            td[class="col-center-sm"] {
                text-align: center;
            }

            tr[class="footer-attendee-cta"] > td[class="grid__col"] {
                padding: 24px 0 0 !important;
            }

            td[class="col-footer-cta"] {
                padding-left: 0 !important;
                padding-right: 0 !important;
            }

            td[class="footer-links"] {
                text-align: left !important;
            }

            .hide-for-small {
                display: none !important;
            }

            .ribbon-mobile {
                line-height: 1.3 !important;
            }

            .small_full_width {
                width: 100% !important;
                padding-bottom: 10px;
            }

            .table__ridge {
                height: 7px !important;
            }

            .table__ridge img {
                display: none !important;
            }

            .table__ridge--top {
                background-image: url(https://cdn.evbstatic.com/s3-s3/marketing/emails/modules/ridges_top_fullx2.jpg) !important;
                background-size: 170% 7px;
            }

            .table__ridge--bottom {
                background-image: url(https://cdn.evbstatic.com/s3-s3/marketing/emails/modules/ridges_bottom_fullx2.jpg) !important;
                background-size: 170% 7px;
            }

            .summary-table__total {
                padding-right: 10px !important;
            }

            .app-cta {
                display: none !important;
            }

            .app-cta__mobile {
                width: 100% !important;
                height: auto !important;
                max-height: none !important;
                overflow: visible !important;
                float: none !important;
                display: block !important;
                margin-top: 12px !important;
                visibility: visible;
                font-size: inherit !important;
            }

            /* List Event Cards */
            .list-card__header {
                width: 130px !important;
            }

            .list-card__label {
                width: 130px !important;
            }

            .list-card__image-wrapper {
                width: 130px !important;
                height: 65px !important;
            }

            .list-card__image {
                max-width: 130px !important;
                max-height: 65px !important;
            }

            .list-card__body {
                padding-left: 10px !important;
            }

            .list-card__title {
                margin-bottom: 10px !important;
            }

            .list-card__date {
                padding-top: 0 !important;
            }
        }

        @media all and (device-width: 768px) and (device-height: 1024px) and (orientation:landscape) {
            .ribbon-mobile {
                line-height: 1.3 !important;
            }

            .ribbon-mobile__text {
                padding: 0 !important;
            }
        }

        @media all and (device-width: 768px) and (device-height: 1024px) and (orientation:portrait) {
            .ribbon-mobile {
                line-height: 1.3 !important;
            }

            .ribbon-mobile__text {
                padding: 0 !important;
            }
        }

        @media screen and (min-device-height:480px) and (max-device-height:568px), (min-device-width : 375px) and (max-device-width : 667px) and (-webkit-min-device-pixel-ratio : 2), (min-device-width : 414px) and (max-device-width : 736px) and (-webkit-min-device-pixel-ratio : 3) {

            .hide_for_iphone {
                display: none !important;
            }

            .passbook {
                width: auto !important;
                height: auto !important;
                line-height: auto !important;
                visibility: visible !important;
                display: block !important;
                max-height: none !important;
                overflow: visible !important;
                float: none !important;
                text-indent: 0 !important;
                font-size: inherit !important;
            }
        }
    </style>
</head>
<!-- Global container with background styles. Gmail converts BODY to DIV so we
  lose properties like BGCOLOR. -->

<body border="0" cellpadding="0" cellspacing="0" height="100%" width="100%" bgcolor="#F7F7F7" style="margin: 0;">
<table border="0" cellpadding="0" cellspacing="0" height="100%" width="100%" bgcolor="#F7F7F7">
    <tr>
        <td>
            <!--[if (gte mso 9)|(IE)]>
            <table width="600" align="center" cellpadding="0" cellspacing="0" border="0" bgcolor="#FFFFFF">
                <tr>
                    <td>
            <![endif]-->
            <table class="content" align="center" cellpadding="0" cellspacing="0" border="0" bgcolor="#F7F7F7" style="width: 600px; max-width: 600px;">
                <tr>
                    <td colspan="2" style="background: #fff; border-radius: 8px;">
                        <table border="0" cellpadding="0" cellspacing="0" width="100%">
                            <tr>
                                <td style="font-family: ''Helvetica Neue'', Helvetica, Arial, sans-serif;">
                            <tr class="">
                                <td class="grid__col" style="font-family: ''Helvetica neue'', Helvetica, arial, sans-serif; padding: 32px 40px; ">

                                    <h2 style="color: #404040; font-weight: 300; margin: 0 0 12px 0; font-size: 24px; line-height: 30px; font-family: ''Helvetica neue'', Helvetica, arial, sans-serif; ">

                                        Dear {{name}},

                                    </h2>

                                    <p style="color: #282828; font-weight: 400; font-size: 15px; line-height: 21px; font-family: ''Helvetica neue'', Helvetica, arial, sans-serif; " class="">We have credited to your account number : {{accountNumber}} with {{bankName}}. on {{paymentDate}} for amount of {{amount}}
                                        on behalf of MAHINDRA &amp; MAHINDRA LIMITED STAFF PROVIDENT FUND towards full & Final Settlement of your Provident Fund A/c as per statement in the attachment.</p>
                                    <br>
                                    <br>
                                    <br>
                                    <br>
                                    <p style="color: #282828; font-weight: 400; font-size: 15px; line-height: 21px; font-family: ''Helvetica neue'', Helvetica, arial, sans-serif; " class="">This is system generated mail. Do not reply.</p>
                                    <br>
                                    <br>
                                    <br>
                                    <p style="color: #282828; font-weight: 400; font-size: 15px; line-height: 21px; font-family: ''Helvetica neue'', Helvetica, arial, sans-serif; " class="">Thanks and Regards</p>
                                    <br>
                                    <p style="color: #282828; font-weight: 400; font-size: 15px; line-height: 21px; font-family: ''Helvetica neue'', Helvetica, arial, sans-serif; " class="">M&M PF Trust</p>
                                </td>
                            </tr>
                            </td>
                            </tr>
                        </table>
                    </td>
                </tr>
            </table>
            <!--[if (gte mso 9)|(IE)]>
            </td>
            </tr>
            </table>
            <![endif]-->
            <!--[if (gte mso 9)|(IE)]>
            <table width="600" align="center" cellpadding="0" cellspacing="0" border="0">
                <tr>
                    <td>
            <![endif]-->
            <table class="content" align="center" cellpadding="0" cellspacing="0" border="0" style="width: 600px; max-width: 600px; font-family: Helvetica, Arial, sans-serif;">
                <tr>
                    <td style="padding-top: 24px;">
                        <table cellspacing="0" cellpadding="0" width="100%">
                            <tr>
                                <td style="background-color: #dedede;  width: 100%; font-size: 1px; height: 1px; line-height: 1px;">&nbsp;</td>
                            </tr>
                        </table>
                    </td>
                </tr>
            </table>
            <!--[if (gte mso 9)|(IE)]>
            </td>
            </tr>
            </table>
            <![endif]-->
        </td>
    </tr>
</table>
</body>

</html>', 'SETTLEMENT_COMPLETION', null, null, 2),
       (sysdate(), '', 1, sysdate(), '<!doctype html>
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
    <meta name="viewport" content="width=device-width">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <!-- Turn off iOS phone number autodetect -->
    <meta name="format-detection" content="telephone=no">
    <style>
        body, p {
            font-family: ''Helvetica Neue'', Helvetica,Arial, sans-serif;
            -webkit-font-smoothing: antialiased;
            -webkit-text-size-adjust: none;
        }
        table {
            border-collapse: collapse;
            border-spacing: 0;
            border: 0;
            padding: 0;
        }
        img {
            margin: 0;
            padding: 0;
        }

        .content {
            width: 600px;
        }

        .no_text_resize {
            -moz-text-size-adjust: none;
            -webkit-text-size-adjust: none;
            -ms-text-size-adjust: none;
            text-size-adjust: none;
        }

        /* Media Queries */
        @media all and (max-width: 600px) {

            table[class="content"] {
                width: 100% !important;
            }

            tr[class="grid-no-gutter"] td[class="grid__col"] {
                padding-left: 0 !important;
                padding-right: 0 !important;
            }

            td[class="grid__col"] {
                padding-left: 18px !important;
                padding-right: 18px !important;
            }

            table[class="small_full_width"] {
                width: 100% !important;
                padding-bottom: 10px;
            }

            a[class="header-link"] {
                margin-right: 0 !important;
                margin-left: 10px !important;
            }

            a[class="btn"] {
                width: 100%;
                border-left-width: 0px !important;
                border-right-width: 0px !important;
            }

            table[class="col-layout"] {
                width: 100% !important;
            }

            td[class="col-container"] {
                display: block !important;
                width: 100% !important;
                padding-left: 0 !important;
                padding-right: 0 !important;
            }

            td[class="col-nav-items"] {
                display: inline-block !important;
                padding-left: 0 !important;
                padding-right: 10px !important;
                background: none !important;
            }

            img[class="col-img"] {
                height: auto !important;
                max-width: 520px !important;
                width: 100% !important;
            }

            td[class="col-center-sm"] {
                text-align: center;
            }

            tr[class="footer-attendee-cta"] > td[class="grid__col"] {
                padding: 24px 0 0 !important;
            }

            td[class="col-footer-cta"] {
                padding-left: 0 !important;
                padding-right: 0 !important;
            }

            td[class="footer-links"] {
                text-align: left !important;
            }

            .hide-for-small {
                display: none !important;
            }

            .ribbon-mobile {
                line-height: 1.3 !important;
            }

            .small_full_width {
                width: 100% !important;
                padding-bottom: 10px;
            }

            .table__ridge {
                height: 7px !important;
            }

            .table__ridge img {
                display: none !important;
            }

            .table__ridge--top {
                background-image: url(https://cdn.evbstatic.com/s3-s3/marketing/emails/modules/ridges_top_fullx2.jpg) !important;
                background-size: 170% 7px;
            }

            .table__ridge--bottom {
                background-image: url(https://cdn.evbstatic.com/s3-s3/marketing/emails/modules/ridges_bottom_fullx2.jpg) !important;
                background-size: 170% 7px;
            }

            .summary-table__total {
                padding-right: 10px !important;
            }

            .app-cta {
                display: none !important;
            }

            .app-cta__mobile {
                width: 100% !important;
                height: auto !important;
                max-height: none !important;
                overflow: visible !important;
                float: none !important;
                display: block !important;
                margin-top: 12px !important;
                visibility: visible;
                font-size: inherit !important;
            }

            /* List Event Cards */
            .list-card__header {
                width: 130px !important;
            }

            .list-card__label {
                width: 130px !important;
            }

            .list-card__image-wrapper {
                width: 130px !important;
                height: 65px !important;
            }

            .list-card__image {
                max-width: 130px !important;
                max-height: 65px !important;
            }

            .list-card__body {
                padding-left: 10px !important;
            }

            .list-card__title {
                margin-bottom: 10px !important;
            }

            .list-card__date {
                padding-top: 0 !important;
            }
        }

        @media all and (device-width: 768px) and (device-height: 1024px) and (orientation:landscape) {
            .ribbon-mobile {
                line-height: 1.3 !important;
            }

            .ribbon-mobile__text {
                padding: 0 !important;
            }
        }

        @media all and (device-width: 768px) and (device-height: 1024px) and (orientation:portrait) {
            .ribbon-mobile {
                line-height: 1.3 !important;
            }

            .ribbon-mobile__text {
                padding: 0 !important;
            }
        }

        @media screen and (min-device-height:480px) and (max-device-height:568px), (min-device-width : 375px) and (max-device-width : 667px) and (-webkit-min-device-pixel-ratio : 2), (min-device-width : 414px) and (max-device-width : 736px) and (-webkit-min-device-pixel-ratio : 3) {

            .hide_for_iphone {
                display: none !important;
            }

            .passbook {
                width: auto !important;
                height: auto !important;
                line-height: auto !important;
                visibility: visible !important;
                display: block !important;
                max-height: none !important;
                overflow: visible !important;
                float: none !important;
                text-indent: 0 !important;
                font-size: inherit !important;
            }
        }
    </style>
</head>
<!-- Global container with background styles. Gmail converts BODY to DIV so we
  lose properties like BGCOLOR. -->

<body border="0" cellpadding="0" cellspacing="0" height="100%" width="100%" bgcolor="#F7F7F7" style="margin: 0;">
<table border="0" cellpadding="0" cellspacing="0" height="100%" width="100%" bgcolor="#F7F7F7">
    <tr>
        <td>
            <!--[if (gte mso 9)|(IE)]>
            <table width="600" align="center" cellpadding="0" cellspacing="0" border="0" bgcolor="#FFFFFF">
                <tr>
                    <td>
            <![endif]-->
            <table class="content" align="center" cellpadding="0" cellspacing="0" border="0" bgcolor="#F7F7F7" style="width: 600px; max-width: 600px;">
                <tr>
                    <td colspan="2" style="background: #fff; border-radius: 8px;">
                        <table border="0" cellpadding="0" cellspacing="0" width="100%">
                            <tr>
                                <td style="font-family: ''Helvetica Neue'', Helvetica, Arial, sans-serif;">
                            <tr class="">
                                <td class="grid__col" style="font-family: ''Helvetica neue'', Helvetica, arial, sans-serif; padding: 32px 40px; ">

                                    <h2 style="color: #404040; font-weight: 300; margin: 0 0 12px 0; font-size: 24px; line-height: 30px; font-family: ''Helvetica neue'', Helvetica, arial, sans-serif; ">

                                        Hi {{name}},

                                    </h2>

                                    <p style="color: #282828; font-weight: 400; font-size: 15px; line-height: 21px; font-family: ''Helvetica neue'', Helvetica, arial, sans-serif; " class="">We have credited <b>Rs. {{amount}}</b> to your M&M. Staff PF Account,
                                        <b>{{pfnumber}}</b> on <b>{{date}}</b> being the PF Transfer received from your previous Employer
                                        <b>{{company}}.</b></p>
                                    <br>
                                    <br>
                                    <br>
                                    <br>
                                    <p style="color: #282828; font-weight: 400; font-size: 15px; line-height: 21px; font-family: ''Helvetica neue'', Helvetica, arial, sans-serif; " class="">This is system generated mail. Do not reply.</p>
                                    <br>
                                    <br>
                                    <br>
                                    <p style="color: #282828; font-weight: 400; font-size: 15px; line-height: 21px; font-family: ''Helvetica neue'', Helvetica, arial, sans-serif; " class="">Thanks and Regards</p>
                                    <br>
                                    <p style="color: #282828; font-weight: 400; font-size: 15px; line-height: 21px; font-family: ''Helvetica neue'', Helvetica, arial, sans-serif; " class="">M&M PF Trust</p>
                                </td>
                            </tr>
                            </td>
                            </tr>
                        </table>
                    </td>
                </tr>
            </table>
            <!--[if (gte mso 9)|(IE)]>
            </td>
            </tr>
            </table>
            <![endif]-->
            <!--[if (gte mso 9)|(IE)]>
            <table width="600" align="center" cellpadding="0" cellspacing="0" border="0">
                <tr>
                    <td>
            <![endif]-->
            <table class="content" align="center" cellpadding="0" cellspacing="0" border="0" style="width: 600px; max-width: 600px; font-family: Helvetica, Arial, sans-serif;">
                <tr>
                    <td style="padding-top: 24px;">
                        <table cellspacing="0" cellpadding="0" width="100%">
                            <tr>
                                <td style="background-color: #dedede;  width: 100%; font-size: 1px; height: 1px; line-height: 1px;">&nbsp;</td>
                            </tr>
                        </table>
                    </td>
                </tr>
            </table>
            <!--[if (gte mso 9)|(IE)]>
            </td>
            </tr>
            </table>
            <![endif]-->
        </td>
    </tr>
</table>
</body>

</html>', 'TRANSFER_IN_COMPLETION', null, null, 2),
       (sysdate(), '', 1, sysdate(), '<!doctype html>
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
    <meta name="viewport" content="width=device-width">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <!-- Turn off iOS phone number autodetect -->
    <meta name="format-detection" content="telephone=no">
    <style>
        body, p {
            font-family: ''Helvetica Neue'', Helvetica,Arial, sans-serif;
            -webkit-font-smoothing: antialiased;
            -webkit-text-size-adjust: none;
        }
        table {
            border-collapse: collapse;
            border-spacing: 0;
            border: 0;
            padding: 0;
        }
        img {
            margin: 0;
            padding: 0;
        }

        .content {
            width: 600px;
        }

        .no_text_resize {
            -moz-text-size-adjust: none;
            -webkit-text-size-adjust: none;
            -ms-text-size-adjust: none;
            text-size-adjust: none;
        }

        /* Media Queries */
        @media all and (max-width: 600px) {

            table[class="content"] {
                width: 100% !important;
            }

            tr[class="grid-no-gutter"] td[class="grid__col"] {
                padding-left: 0 !important;
                padding-right: 0 !important;
            }

            td[class="grid__col"] {
                padding-left: 18px !important;
                padding-right: 18px !important;
            }

            table[class="small_full_width"] {
                width: 100% !important;
                padding-bottom: 10px;
            }

            a[class="header-link"] {
                margin-right: 0 !important;
                margin-left: 10px !important;
            }

            a[class="btn"] {
                width: 100%;
                border-left-width: 0px !important;
                border-right-width: 0px !important;
            }

            table[class="col-layout"] {
                width: 100% !important;
            }

            td[class="col-container"] {
                display: block !important;
                width: 100% !important;
                padding-left: 0 !important;
                padding-right: 0 !important;
            }

            td[class="col-nav-items"] {
                display: inline-block !important;
                padding-left: 0 !important;
                padding-right: 10px !important;
                background: none !important;
            }

            img[class="col-img"] {
                height: auto !important;
                max-width: 520px !important;
                width: 100% !important;
            }

            td[class="col-center-sm"] {
                text-align: center;
            }

            tr[class="footer-attendee-cta"] > td[class="grid__col"] {
                padding: 24px 0 0 !important;
            }

            td[class="col-footer-cta"] {
                padding-left: 0 !important;
                padding-right: 0 !important;
            }

            td[class="footer-links"] {
                text-align: left !important;
            }

            .hide-for-small {
                display: none !important;
            }

            .ribbon-mobile {
                line-height: 1.3 !important;
            }

            .small_full_width {
                width: 100% !important;
                padding-bottom: 10px;
            }

            .table__ridge {
                height: 7px !important;
            }

            .table__ridge img {
                display: none !important;
            }

            .table__ridge--top {
                background-image: url(https://cdn.evbstatic.com/s3-s3/marketing/emails/modules/ridges_top_fullx2.jpg) !important;
                background-size: 170% 7px;
            }

            .table__ridge--bottom {
                background-image: url(https://cdn.evbstatic.com/s3-s3/marketing/emails/modules/ridges_bottom_fullx2.jpg) !important;
                background-size: 170% 7px;
            }

            .summary-table__total {
                padding-right: 10px !important;
            }

            .app-cta {
                display: none !important;
            }

            .app-cta__mobile {
                width: 100% !important;
                height: auto !important;
                max-height: none !important;
                overflow: visible !important;
                float: none !important;
                display: block !important;
                margin-top: 12px !important;
                visibility: visible;
                font-size: inherit !important;
            }

            /* List Event Cards */
            .list-card__header {
                width: 130px !important;
            }

            .list-card__label {
                width: 130px !important;
            }

            .list-card__image-wrapper {
                width: 130px !important;
                height: 65px !important;
            }

            .list-card__image {
                max-width: 130px !important;
                max-height: 65px !important;
            }

            .list-card__body {
                padding-left: 10px !important;
            }

            .list-card__title {
                margin-bottom: 10px !important;
            }

            .list-card__date {
                padding-top: 0 !important;
            }
        }

        @media all and (device-width: 768px) and (device-height: 1024px) and (orientation:landscape) {
            .ribbon-mobile {
                line-height: 1.3 !important;
            }

            .ribbon-mobile__text {
                padding: 0 !important;
            }
        }

        @media all and (device-width: 768px) and (device-height: 1024px) and (orientation:portrait) {
            .ribbon-mobile {
                line-height: 1.3 !important;
            }

            .ribbon-mobile__text {
                padding: 0 !important;
            }
        }

        @media screen and (min-device-height:480px) and (max-device-height:568px), (min-device-width : 375px) and (max-device-width : 667px) and (-webkit-min-device-pixel-ratio : 2), (min-device-width : 414px) and (max-device-width : 736px) and (-webkit-min-device-pixel-ratio : 3) {

            .hide_for_iphone {
                display: none !important;
            }

            .passbook {
                width: auto !important;
                height: auto !important;
                line-height: auto !important;
                visibility: visible !important;
                display: block !important;
                max-height: none !important;
                overflow: visible !important;
                float: none !important;
                text-indent: 0 !important;
                font-size: inherit !important;
            }
        }
    </style>
</head>
<!-- Global container with background styles. Gmail converts BODY to DIV so we
  lose properties like BGCOLOR. -->

<body border="0" cellpadding="0" cellspacing="0" height="100%" width="100%" bgcolor="#F7F7F7" style="margin: 0;">
<table border="0" cellpadding="0" cellspacing="0" height="100%" width="100%" bgcolor="#F7F7F7">
    <tr>
        <td>
            <!--[if (gte mso 9)|(IE)]>
            <table width="600" align="center" cellpadding="0" cellspacing="0" border="0" bgcolor="#FFFFFF">
                <tr>
                    <td>
            <![endif]-->
            <table class="content" align="center" cellpadding="0" cellspacing="0" border="0" bgcolor="#F7F7F7" style="width: 600px; max-width: 600px;">
                <tr>
                    <td colspan="2" style="background: #fff; border-radius: 8px;">
                        <table border="0" cellpadding="0" cellspacing="0" width="100%">
                            <tr>
                                <td style="font-family: ''Helvetica Neue'', Helvetica, Arial, sans-serif;">
                            <tr class="">
                                <td class="grid__col" style="font-family: ''Helvetica neue'', Helvetica, arial, sans-serif; padding: 32px 40px; ">

                                    <h2 style="color: #404040; font-weight: 300; margin: 0 0 12px 0; font-size: 24px; line-height: 30px; font-family: ''Helvetica neue'', Helvetica, arial, sans-serif; ">

                                        Dear Sir/Madam,

                                    </h2>

                                    <p style="color: #282828; font-weight: 400; font-size: 15px; line-height: 21px; font-family: ''Helvetica neue'', Helvetica, arial, sans-serif; " class="">Transfer of Provident Fund Accumulation of Mr/Mrs. {{name}} from PF A/c No. {{fromPfAccount}} To PF A/c No. {{toPfAccount}} of Rs. {{amount}} with attached copy of Annexure K & Dispatch Letter.</p>
                                    <br>
                                    <br>
                                    <br>
                                    <br>
                                    <p style="color: #282828; font-weight: 400; font-size: 15px; line-height: 21px; font-family: ''Helvetica neue'', Helvetica, arial, sans-serif; " class="">This is system generated mail. Do not reply.</p>
                                    <br>
                                    <br>
                                    <br>
                                    <p style="color: #282828; font-weight: 400; font-size: 15px; line-height: 21px; font-family: ''Helvetica neue'', Helvetica, arial, sans-serif; " class="">Thanks and Regards</p>
                                    <br>
                                    <p style="color: #282828; font-weight: 400; font-size: 15px; line-height: 21px; font-family: ''Helvetica neue'', Helvetica, arial, sans-serif; " class="">M&M PF Trust</p>
                                </td>
                            </tr>
                            </td>
                            </tr>
                        </table>
                    </td>
                </tr>
            </table>
            <!--[if (gte mso 9)|(IE)]>
            </td>
            </tr>
            </table>
            <![endif]-->
            <!--[if (gte mso 9)|(IE)]>
            <table width="600" align="center" cellpadding="0" cellspacing="0" border="0">
                <tr>
                    <td>
            <![endif]-->
            <table class="content" align="center" cellpadding="0" cellspacing="0" border="0" style="width: 600px; max-width: 600px; font-family: Helvetica, Arial, sans-serif;">
                <tr>
                    <td style="padding-top: 24px;">
                        <table cellspacing="0" cellpadding="0" width="100%">
                            <tr>
                                <td style="background-color: #dedede;  width: 100%; font-size: 1px; height: 1px; line-height: 1px;">&nbsp;</td>
                            </tr>
                        </table>
                    </td>
                </tr>
            </table>
            <!--[if (gte mso 9)|(IE)]>
            </td>
            </tr>
            </table>
            <![endif]-->
        </td>
    </tr>
</table>
</body>

</html>', 'TRANSFER_OUT_COMPLETION', null, null, 2),
       (sysdate(), '', 1, sysdate(), '<!doctype html>
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
    <meta name="viewport" content="width=device-width">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <!-- Turn off iOS phone number autodetect -->
    <meta name="format-detection" content="telephone=no">
    <style>
        body, p {
            font-family: ''Helvetica Neue'', Helvetica,Arial, sans-serif;
            -webkit-font-smoothing: antialiased;
            -webkit-text-size-adjust: none;
        }
        table {
            border-collapse: collapse;
            border-spacing: 0;
            border: 0;
            padding: 0;
        }
        img {
            margin: 0;
            padding: 0;
        }

        .content {
            width: 600px;
        }

        .no_text_resize {
            -moz-text-size-adjust: none;
            -webkit-text-size-adjust: none;
            -ms-text-size-adjust: none;
            text-size-adjust: none;
        }

        /* Media Queries */
        @media all and (max-width: 600px) {

            table[class="content"] {
                width: 100% !important;
            }

            tr[class="grid-no-gutter"] td[class="grid__col"] {
                padding-left: 0 !important;
                padding-right: 0 !important;
            }

            td[class="grid__col"] {
                padding-left: 18px !important;
                padding-right: 18px !important;
            }

            table[class="small_full_width"] {
                width: 100% !important;
                padding-bottom: 10px;
            }

            a[class="header-link"] {
                margin-right: 0 !important;
                margin-left: 10px !important;
            }

            a[class="btn"] {
                width: 100%;
                border-left-width: 0px !important;
                border-right-width: 0px !important;
            }

            table[class="col-layout"] {
                width: 100% !important;
            }

            td[class="col-container"] {
                display: block !important;
                width: 100% !important;
                padding-left: 0 !important;
                padding-right: 0 !important;
            }

            td[class="col-nav-items"] {
                display: inline-block !important;
                padding-left: 0 !important;
                padding-right: 10px !important;
                background: none !important;
            }

            img[class="col-img"] {
                height: auto !important;
                max-width: 520px !important;
                width: 100% !important;
            }

            td[class="col-center-sm"] {
                text-align: center;
            }

            tr[class="footer-attendee-cta"] > td[class="grid__col"] {
                padding: 24px 0 0 !important;
            }

            td[class="col-footer-cta"] {
                padding-left: 0 !important;
                padding-right: 0 !important;
            }

            td[class="footer-links"] {
                text-align: left !important;
            }

            .hide-for-small {
                display: none !important;
            }

            .ribbon-mobile {
                line-height: 1.3 !important;
            }

            .small_full_width {
                width: 100% !important;
                padding-bottom: 10px;
            }

            .table__ridge {
                height: 7px !important;
            }

            .table__ridge img {
                display: none !important;
            }

            .table__ridge--top {
                background-image: url(https://cdn.evbstatic.com/s3-s3/marketing/emails/modules/ridges_top_fullx2.jpg) !important;
                background-size: 170% 7px;
            }

            .table__ridge--bottom {
                background-image: url(https://cdn.evbstatic.com/s3-s3/marketing/emails/modules/ridges_bottom_fullx2.jpg) !important;
                background-size: 170% 7px;
            }

            .summary-table__total {
                padding-right: 10px !important;
            }

            .app-cta {
                display: none !important;
            }

            .app-cta__mobile {
                width: 100% !important;
                height: auto !important;
                max-height: none !important;
                overflow: visible !important;
                float: none !important;
                display: block !important;
                margin-top: 12px !important;
                visibility: visible;
                font-size: inherit !important;
            }

            /* List Event Cards */
            .list-card__header {
                width: 130px !important;
            }

            .list-card__label {
                width: 130px !important;
            }

            .list-card__image-wrapper {
                width: 130px !important;
                height: 65px !important;
            }

            .list-card__image {
                max-width: 130px !important;
                max-height: 65px !important;
            }

            .list-card__body {
                padding-left: 10px !important;
            }

            .list-card__title {
                margin-bottom: 10px !important;
            }

            .list-card__date {
                padding-top: 0 !important;
            }
        }

        @media all and (device-width: 768px) and (device-height: 1024px) and (orientation:landscape) {
            .ribbon-mobile {
                line-height: 1.3 !important;
            }

            .ribbon-mobile__text {
                padding: 0 !important;
            }
        }

        @media all and (device-width: 768px) and (device-height: 1024px) and (orientation:portrait) {
            .ribbon-mobile {
                line-height: 1.3 !important;
            }

            .ribbon-mobile__text {
                padding: 0 !important;
            }
        }

        @media screen and (min-device-height:480px) and (max-device-height:568px), (min-device-width : 375px) and (max-device-width : 667px) and (-webkit-min-device-pixel-ratio : 2), (min-device-width : 414px) and (max-device-width : 736px) and (-webkit-min-device-pixel-ratio : 3) {

            .hide_for_iphone {
                display: none !important;
            }

            .passbook {
                width: auto !important;
                height: auto !important;
                line-height: auto !important;
                visibility: visible !important;
                display: block !important;
                max-height: none !important;
                overflow: visible !important;
                float: none !important;
                text-indent: 0 !important;
                font-size: inherit !important;
            }
        }
    </style>
</head>
<!-- Global container with background styles. Gmail converts BODY to DIV so we
  lose properties like BGCOLOR. -->

<body border="0" cellpadding="0" cellspacing="0" height="100%" width="100%" bgcolor="#F7F7F7" style="margin: 0;">
<table border="0" cellpadding="0" cellspacing="0" height="100%" width="100%" bgcolor="#F7F7F7">
    <tr>
        <td>
            <!--[if (gte mso 9)|(IE)]>
            <table width="600" align="center" cellpadding="0" cellspacing="0" border="0" bgcolor="#FFFFFF">
                <tr>
                    <td>
            <![endif]-->
            <table class="content" align="center" cellpadding="0" cellspacing="0" border="0" bgcolor="#F7F7F7" style="width: 600px; max-width: 600px;">
                <tr>
                    <td colspan="2" style="background: #fff; border-radius: 8px;">
                        <table border="0" cellpadding="0" cellspacing="0" width="100%">
                            <tr>
                                <td style="font-family: ''Helvetica Neue'', Helvetica, Arial, sans-serif;">
                            <tr class="">
                                <td class="grid__col" style="font-family: ''Helvetica neue'', Helvetica, arial, sans-serif; padding: 32px 40px; ">

                                    <h2 style="color: #404040; font-weight: 300; margin: 0 0 12px 0; font-size: 24px; line-height: 30px; font-family: ''Helvetica neue'', Helvetica, arial, sans-serif; ">

                                        Hi {{firstName}} {{lastName}},

                                    </h2>

                                    <p style="color: #666666; font-weight: 400; font-size: 15px; line-height: 21px; font-family: ''Helvetica neue'', Helvetica, arial, sans-serif; " class="">Please click on the button to complete the verification process for PF Trust Management User account.</p>
                                    <table width="100%" border="0" cellspacing="0" cellpadding="0" style="margin-top: 12px; margin-bottom: 12px; margin: 24px 0">
                                        <tr>
                                            <td>
                                                <table border="0" cellspacing="0" cellpadding="0" width="100%">
                                                    <tr>
                                                        <td style="-webkit-border-radius: 3px; -moz-border-radius: 3px; border-radius: 3px;">
                                                            <a href="{{link}}" target="_blank" style="display:inline-block; color: #fff; font-weight: 400; border-left: 15px solid; border-right: 15px solid; border-top: 12px solid; border-bottom: 12px solid; font-size: 17px; text-decoration: none; text-align: center; -webkit-text-size-adjust: none; -webkit-border-radius: 3px; -moz-border-radius: 3px; border-radius: 3px; font-family: ''Helvetica neue'', Helvetica, arial, sans-serif; background-color: #7ad108; border-color: #7ad108;"
                                                               class="btn"> <span style="padding-left: 5px; padding-right: 5px;">
                                Verify Your Email Address
                            </span>

                                                            </a>
                                                        </td>
                                                    </tr>
                                                </table>
                                            </td>
                                        </tr>
                                    </table>
                                    <p style="color: #666666; font-weight: 400; font-size: 15px; line-height: 21px; font-family: ''Helvetica neue'', Helvetica, arial, sans-serif; " class="">By doing this you''re agreeing to terms and conditions of Pf Trust.</p>
                                    <p style="color: #666666; font-weight: 400; font-size: 17px; line-height: 24px; font-family: ''Helvetica neue'', Helvetica, arial, sans-serif; margin-bottom: 6px; margin-top: 24px;" class="">Thank you,</p>
                                </td>
                            </tr>
                            </td>
                            </tr>
                        </table>
                    </td>
                </tr>
            </table>
            <!--[if (gte mso 9)|(IE)]>
            </td>
            </tr>
            </table>
            <![endif]-->
            <!--[if (gte mso 9)|(IE)]>
            <table width="600" align="center" cellpadding="0" cellspacing="0" border="0">
                <tr>
                    <td>
            <![endif]-->
            <table class="content" align="center" cellpadding="0" cellspacing="0" border="0" style="width: 600px; max-width: 600px; font-family: Helvetica, Arial, sans-serif;">
                <tr>
                    <td style="padding-top: 24px;">
                        <table cellspacing="0" cellpadding="0" width="100%">
                            <tr>
                                <td style="background-color: #dedede;  width: 100%; font-size: 1px; height: 1px; line-height: 1px;">&nbsp;</td>
                            </tr>
                        </table>
                    </td>
                </tr>
            </table>
            <!--[if (gte mso 9)|(IE)]>
            </td>
            </tr>
            </table>
            <![endif]-->
        </td>
    </tr>
</table>
</body>

</html>', 'USER_ACCOUNT_INVITATION_EMAIL', null, null, 2),
       (sysdate(), '', 1, sysdate(), '<!doctype html>
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
    <meta name="viewport" content="width=device-width">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <!-- Turn off iOS phone number autodetect -->
    <meta name="format-detection" content="telephone=no">
    <style>
        body, p {
            font-family: ''Helvetica Neue'', Helvetica,Arial, sans-serif;
            -webkit-font-smoothing: antialiased;
            -webkit-text-size-adjust: none;
        }
        table {
            border-collapse: collapse;
            border-spacing: 0;
            border: 0;
            padding: 0;
        }
        img {
            margin: 0;
            padding: 0;
        }

        .content {
            width: 600px;
        }

        .no_text_resize {
            -moz-text-size-adjust: none;
            -webkit-text-size-adjust: none;
            -ms-text-size-adjust: none;
            text-size-adjust: none;
        }

        /* Media Queries */
        @media all and (max-width: 600px) {

            table[class="content"] {
                width: 100% !important;
            }

            tr[class="grid-no-gutter"] td[class="grid__col"] {
                padding-left: 0 !important;
                padding-right: 0 !important;
            }

            td[class="grid__col"] {
                padding-left: 18px !important;
                padding-right: 18px !important;
            }

            table[class="small_full_width"] {
                width: 100% !important;
                padding-bottom: 10px;
            }

            a[class="header-link"] {
                margin-right: 0 !important;
                margin-left: 10px !important;
            }

            a[class="btn"] {
                width: 100%;
                border-left-width: 0px !important;
                border-right-width: 0px !important;
            }

            table[class="col-layout"] {
                width: 100% !important;
            }

            td[class="col-container"] {
                display: block !important;
                width: 100% !important;
                padding-left: 0 !important;
                padding-right: 0 !important;
            }

            td[class="col-nav-items"] {
                display: inline-block !important;
                padding-left: 0 !important;
                padding-right: 10px !important;
                background: none !important;
            }

            img[class="col-img"] {
                height: auto !important;
                max-width: 520px !important;
                width: 100% !important;
            }

            td[class="col-center-sm"] {
                text-align: center;
            }

            tr[class="footer-attendee-cta"] > td[class="grid__col"] {
                padding: 24px 0 0 !important;
            }

            td[class="col-footer-cta"] {
                padding-left: 0 !important;
                padding-right: 0 !important;
            }

            td[class="footer-links"] {
                text-align: left !important;
            }

            .hide-for-small {
                display: none !important;
            }

            .ribbon-mobile {
                line-height: 1.3 !important;
            }

            .small_full_width {
                width: 100% !important;
                padding-bottom: 10px;
            }

            .table__ridge {
                height: 7px !important;
            }

            .table__ridge img {
                display: none !important;
            }

            .table__ridge--top {
                background-image: url(https://cdn.evbstatic.com/s3-s3/marketing/emails/modules/ridges_top_fullx2.jpg) !important;
                background-size: 170% 7px;
            }

            .table__ridge--bottom {
                background-image: url(https://cdn.evbstatic.com/s3-s3/marketing/emails/modules/ridges_bottom_fullx2.jpg) !important;
                background-size: 170% 7px;
            }

            .summary-table__total {
                padding-right: 10px !important;
            }

            .app-cta {
                display: none !important;
            }

            .app-cta__mobile {
                width: 100% !important;
                height: auto !important;
                max-height: none !important;
                overflow: visible !important;
                float: none !important;
                display: block !important;
                margin-top: 12px !important;
                visibility: visible;
                font-size: inherit !important;
            }

            /* List Event Cards */
            .list-card__header {
                width: 130px !important;
            }

            .list-card__label {
                width: 130px !important;
            }

            .list-card__image-wrapper {
                width: 130px !important;
                height: 65px !important;
            }

            .list-card__image {
                max-width: 130px !important;
                max-height: 65px !important;
            }

            .list-card__body {
                padding-left: 10px !important;
            }

            .list-card__title {
                margin-bottom: 10px !important;
            }

            .list-card__date {
                padding-top: 0 !important;
            }
        }

        @media all and (device-width: 768px) and (device-height: 1024px) and (orientation:landscape) {
            .ribbon-mobile {
                line-height: 1.3 !important;
            }

            .ribbon-mobile__text {
                padding: 0 !important;
            }
        }

        @media all and (device-width: 768px) and (device-height: 1024px) and (orientation:portrait) {
            .ribbon-mobile {
                line-height: 1.3 !important;
            }

            .ribbon-mobile__text {
                padding: 0 !important;
            }
        }

        @media screen and (min-device-height:480px) and (max-device-height:568px), (min-device-width : 375px) and (max-device-width : 667px) and (-webkit-min-device-pixel-ratio : 2), (min-device-width : 414px) and (max-device-width : 736px) and (-webkit-min-device-pixel-ratio : 3) {

            .hide_for_iphone {
                display: none !important;
            }

            .passbook {
                width: auto !important;
                height: auto !important;
                line-height: auto !important;
                visibility: visible !important;
                display: block !important;
                max-height: none !important;
                overflow: visible !important;
                float: none !important;
                text-indent: 0 !important;
                font-size: inherit !important;
            }
        }
    </style>
</head>
<!-- Global container with background styles. Gmail converts BODY to DIV so we
  lose properties like BGCOLOR. -->

<body border="0" cellpadding="0" cellspacing="0" height="100%" width="100%" bgcolor="#F7F7F7" style="margin: 0;">
<table border="0" cellpadding="0" cellspacing="0" height="100%" width="100%" bgcolor="#F7F7F7">
    <tr>
        <td>
            <!--[if (gte mso 9)|(IE)]>
            <table width="600" align="center" cellpadding="0" cellspacing="0" border="0" bgcolor="#FFFFFF">
                <tr>
                    <td>
            <![endif]-->
            <table class="content" align="center" cellpadding="0" cellspacing="0" border="0" bgcolor="#F7F7F7" style="width: 600px; max-width: 600px;">
                <tr>
                    <td colspan="2" style="background: #fff; border-radius: 8px;">
                        <table border="0" cellpadding="0" cellspacing="0" width="100%">
                            <tr>
                                <td style="font-family: ''Helvetica Neue'', Helvetica, Arial, sans-serif;">
                            <tr class="">
                                <td class="grid__col" style="font-family: ''Helvetica neue'', Helvetica, arial, sans-serif; padding: 32px 40px; ">

                                    <h2 style="color: #404040; font-weight: 300; margin: 0 0 12px 0; font-size: 24px; line-height: 30px; font-family: ''Helvetica neue'', Helvetica, arial, sans-serif; ">

                                        Hi {{firstName}} {{lastName}},

                                    </h2>

                                    <p style="color: #666666; font-weight: 400; font-size: 15px; line-height: 21px; font-family: ''Helvetica neue'', Helvetica, arial, sans-serif; " class="">Below are the credentials for you {{tenantName}} PF Trust Management Account.</p>
                                    <table width="100%" border="0" cellspacing="0" cellpadding="0" style="margin-top: 12px; margin-bottom: 12px; margin: 24px 0">
                                        <tr>
                                            <td>
                                                <table border="0" cellspacing="0" cellpadding="0" width="100%">
                                                    <tr>
                                                        <td style="-webkit-border-radius: 3px; -moz-border-radius: 3px; border-radius: 3px;">
                                                            <h4>User Name: {{username}}</h4>
                                                            <h4>Password: {{password}}</h4>
                                                        </td>
                                                    </tr>
                                                </table>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td style="-webkit-border-radius: 3px; -moz-border-radius: 3px; border-radius: 3px;">
                                                <a href="{{link}}" target="_blank" style="display:inline-block; color: #fff; font-weight: 400; border-left: 15px solid; border-right: 15px solid; border-top: 12px solid; border-bottom: 12px solid; font-size: 17px; text-decoration: none; text-align: center; -webkit-text-size-adjust: none; -webkit-border-radius: 3px; -moz-border-radius: 3px; border-radius: 3px; font-family: ''Helvetica neue'', Helvetica, arial, sans-serif; background-color: #7ad108; border-color: #7ad108;"
                                                   class="btn"> <span style="padding-left: 5px; padding-right: 5px;">Login</span>
                                                </a>
                                            </td>
                                        </tr>
                                    </table>
                                    <p style="color: #666666; font-weight: 400; font-size: 15px; line-height: 21px; font-family: ''Helvetica neue'', Helvetica, arial, sans-serif; " class="">After login please reset the password for your account.</p>
                                    <p style="color: #666666; font-weight: 400; font-size: 17px; line-height: 24px; font-family: ''Helvetica neue'', Helvetica, arial, sans-serif; margin-bottom: 6px; margin-top: 24px;" class="">Thank you,</p>
                                </td>
                            </tr>
                            </td>
                            </tr>
                        </table>
                    </td>
                </tr>
            </table>
            <!--[if (gte mso 9)|(IE)]>
            </td>
            </tr>
            </table>
            <![endif]-->
            <!--[if (gte mso 9)|(IE)]>
            <table width="600" align="center" cellpadding="0" cellspacing="0" border="0">
                <tr>
                    <td>
            <![endif]-->
            <table class="content" align="center" cellpadding="0" cellspacing="0" border="0" style="width: 600px; max-width: 600px; font-family: Helvetica, Arial, sans-serif;">
                <tr>
                    <td style="padding-top: 24px;">
                        <table cellspacing="0" cellpadding="0" width="100%">
                            <tr>
                                <td style="background-color: #dedede;  width: 100%; font-size: 1px; height: 1px; line-height: 1px;">&nbsp;</td>
                            </tr>
                        </table>
                    </td>
                </tr>
            </table>
            <!--[if (gte mso 9)|(IE)]>
            </td>
            </tr>
            </table>
            <![endif]-->
        </td>
    </tr>
</table>
</body>

</html>', 'USER_ACCOUNT_DETAILS', null, null, 2);

-- password -> 3wj1_#OD
-- encode -> $2a$10$wNmrMOXpCobgnQbqVyeDwee76yZEPEdU8/.Hg6lUWmdzdh822J5lG