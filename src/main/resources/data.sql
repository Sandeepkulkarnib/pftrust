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
       (15, sysdate(), sysdate(), 'CUSTROMER_SERVICE', 'customer service', 'pf', 'mdi-account-supervisor', '/customer-service');

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
      (sysdate(), '', 0, sysdate(), 'Generate Bank Sheet', '', 'BANK_SHEET', '/generate-bank-sheet', 'loan', 3, 'CREATE|UPDATE|DELETE');

insert into sub_department(created_at, entity_id, is_active, updated_at, label, mdi_icon, name, path, type, pf_department_id, permission)
VALUES(sysdate(), '', 1, sysdate(), 'Search TransferIns', '', 'SEARCH_TRANSFER_IN', '', 'transferin', 4, 'READ|CREATE|UPDATE|DELETE'),
      (sysdate(), '', 1, sysdate(), 'Create TransferIns', '', 'CREATE_TRANSFER_IN', '/create', 'transferin', 4, 'CREATE|UPDATE|DELETE'),
      (sysdate(), '', 1, sysdate(), 'Send Emails', '', 'SEND_EMAILS', '/send-emails', 'transferin', 4, 'READ|CREATE|UPDATE|DELETE');

insert into sub_department(created_at, entity_id, is_active, updated_at, label, mdi_icon, name, path, type, pf_department_id, permission)
VALUES(sysdate(), '', 1, sysdate(), 'Search TransferOuts', '', 'SEARCH_TRANSFER_OUT', '', 'transferout', 6, 'READ|CREATE|UPDATE|DELETE'),
      (sysdate(), '', 1 , sysdate(), 'Create TransferOuts', '', 'CREATE_TRANSFER_OUT', '/create', 'transferout', 6, 'CREATE|UPDATE|DELETE'),
      (SYSDATE(), '', 1, sysdate(), 'Send Emails', '', 'SEND_EMAILS', '/send-emails', 'transferout', 6, 'CREATE|UPDATE|DELETE'),
      (sysdate(), '', 0, sysdate(), 'Generate Bank Sheet', '', 'BANK_SHEET', '/generate-bank-sheet', 'transferout', 6, 'CREATE|UPDATE|DELETE');

insert into sub_department(created_at, entity_id, is_active, updated_at, label, mdi_icon, name, path, type, pf_department_id, permission)
VALUES(sysdate(), '', 1, sysdate(), 'Search Settlements', '', 'SEARCH_SETTLEMENTS', '', 'settlement', 5, 'READ|CREATE|UPDATE|DELETE'),
      (sysdate(), '', 1, sysdate(), 'Create Settlements', '', 'CREATE_SETTLEMENTS', '/create', 'settlement', 5, 'CREATE|UPDATE|DELETE'),
      (sysdate(), '', 1, sysdate(), 'Send Emails', '', 'SEND_EMAILS', '/send-emails', 'settlement', 5, 'CREATE|UPDATE|DELETE'),
      (sysdate(), '', 0, sysdate(), 'Generate Bank Sheet', '', 'BANK_SHEET', '/generate-bank-sheet', 'settlement', 5, 'CREATE|UPDATE|DELETE');

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

insert into pf_department_role_group(id, created_at, entity_id, is_active, updated_at, description, name)
VALUES (4, sysdate(), 'f88fda13-4761-4ce9-9e43-2fad276fc0sa', 1, sysdate(), '', 'Customer-Service Head');

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
       (14, sysdate(), sysdate(), 'ROLE_TENANT_ADMIN', '/tenants', 11, 1, '05ffa5f0-ecd1-4efc-91b1-ff4b8c73b175'),
       (15, sysdate(), sysdate(), 'ROLE_CUSTOMER_SERVICE_VIEW', '/customer-service', 15, 3, '0fc92ced-998f-41d2-b8ab-a56957a02062');

insert into pf_department_role_group_mapping(pf_department_role_id, pf_department_role_group_id)
VALUES (1, 1),(2, 1), (3, 1), (4, 1), (5, 1), (6, 1), (7, 1), (8, 1), (10, 1), (11, 1), (12, 1), (13, 1),
       (9, 1), (14, 1);

insert into pf_department_role_group_mapping(pf_department_role_id, pf_department_role_group_id)
VALUES (1, 2),(2, 2),(3, 2),(11, 2),(8, 2);

insert into pf_department_role_group_mapping(pf_department_role_id, pf_department_role_group_id)
VALUES (1, 3),(2, 3),(5, 3), (6, 3),(11, 3),(8, 3);

insert into pf_department_role_group_mapping(pf_department_role_id, pf_department_role_group_id)
VALUES (8, 4),(11, 4),(15, 4);

insert into user(id, created_at, updated_at, email, first_name, is_account_non_expired, is_account_non_locked,
                 is_credentials_non_expired, is_default_password, is_enabled, last_name, password, username,
                 profile_image, entity_id)
values (1, sysdate(), sysdate(), 'shaikhrayeesahmed@gmail.com', 'Rayees Ahmed', 1, 1, 1, 1, 1, 'Shaikh',
        '$2a$10$wNmrMOXpCobgnQbqVyeDwee76yZEPEdU8/.Hg6lUWmdzdh822J5lG', 'shaikhrayeesahmed',
        'https://cdn.vuetifyjs.com/images/lists/ali.png', '53adf409-2ba8-4546-8e65-eb41dd47aa01');

insert into user(id, created_at, updated_at, email, first_name, is_account_non_expired, is_account_non_locked,
                 is_credentials_non_expired, is_default_password, is_enabled, last_name, password, username,
                 profile_image, entity_id)
values (2, sysdate(), sysdate(), 'rayeesahmed@gmail.com', 'Rayees Ahmed', 1, 1, 1, 1, 1, 'Shaikh',
        '$2a$10$yi88LjVuPcgTSqCXyTQjJ.TduYSOfFWvNmcF0539fWzNyuodFB6zW', 'rayeesahmed',
        'https://cdn.vuetifyjs.com/images/lists/ali.png', '23f753d2-5fcc-4a6b-ba58-1557a8e17bcc');

insert into user(id, created_at, updated_at, email, first_name, is_account_non_expired, is_account_non_locked,
                 is_credentials_non_expired, is_default_password, is_enabled, last_name, password, username,
                 profile_image, entity_id)
values (3, sysdate(), sysdate(), 'sameerk@gmail.com', 'Sameer', 1, 1, 1, 1, 1, 'Kulkarni',
        '$2a$10$yi88LjVuPcgTSqCXyTQjJ.TduYSOfFWvNmcF0539fWzNyuodFB6zW', 'sameerk',
        'https://cdn.vuetifyjs.com/images/lists/ali.png', '8ac46f98-a310-4240-a543-b92c5ea5f1c8');

insert into user(id, created_at, updated_at, email, first_name, is_account_non_expired, is_account_non_locked,
                 is_credentials_non_expired, is_default_password, is_enabled, last_name, password, username,
                 profile_image, entity_id)
values (4, sysdate(), sysdate(), 'shaikhrayeesahmed@gmail.com', 'Rayees Ahmed', 1, 1, 1, 1, 1, 'Shaikh',
        '$2a$10$wNmrMOXpCobgnQbqVyeDwee76yZEPEdU8/.Hg6lUWmdzdh822J5lG', 'customerservice',
        'https://cdn.vuetifyjs.com/images/lists/ali.png', '53adf409-2ba8-4546-8e65-eb41dd47aa02');

insert into tenant(id, entity_id, created_at, updated_at, name, code, description, tenant_id, theme, clock,
                   logo_image, height, width,
                   style_classes)
VALUES (1, '4aad20c3-b00d-4cc4-9d05-fa05f32a4dfe', sysdate(), sysdate(), 'Demo Tenant', 'DEMO', 'Demo Tenant', '0ba5d1d6-d2a8-4f4f-b78b-dd33c1bcc895', '{
    "primary": "#6de5c6",
    "secondary": "#3f51b5",
    "accent": "#4caf50",
    "error": "#ef2517",
    "warning": "#299a9d",
    "info": "#4fe0ba",
    "success": "#8eec21"
}', 'https://free.timeanddate.com/clock/i8easafx/n54/fs18/fcfff/tc6de5c6/tt0/tm1',
        'data:image/png;base64,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',
        '55px', '240px', ''),
       (3, '1ac4b566-fcce-4a38-a5d5-3f610e4cb188', sysdate(), sysdate(), 'Coreintegra PF Trust', 'COREINTEGRA', 'Coreintegra PF Trust', '52f44d40-d3d7-4e78-983d-70418d8e798a',
        '{
    "primary": "#6de5c6",
    "secondary": "#3f51b5",
    "accent": "#4caf50",
    "error": "#ef2517",
    "warning": "#299a9d",
    "info": "#4fe0ba",
    "success": "#8eec21"
}', 'https://free.timeanddate.com/clock/i8easafx/n54/fs18/fcfff/tc6de5c6/tt0/tm1',
        'data:image/png;base64,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',
        '65px', '145px', 'ml-10');

insert into user_tenant_mapping(created_at, is_active, updated_at, tenant_id, user_id)
VALUES (sysdate(), 1, sysdate(), 1, 1),
       (sysdate(), 1, sysdate(), 3, 1),
       (sysdate(), 1, sysdate(), 3, 2),
       (sysdate(), 1, sysdate(), 3, 3),
       (sysdate(), 1, sysdate(), 3, 4);


insert into user_tenant_pf_department_role_mapping
(created_at, is_active, updated_at, pf_department_role_group_id, tenant_id, user_id)
VALUES (sysdate(), 1, sysdate(), 1, 1, 1);

insert into user_tenant_pf_department_role_mapping
(created_at, is_active, updated_at, pf_department_role_group_id, tenant_id, user_id)
VALUES (sysdate(), 1, sysdate(), 1, 3, 1);

insert into user_tenant_pf_department_role_mapping
(created_at, is_active, updated_at, pf_department_role_group_id, tenant_id, user_id)
VALUES (sysdate(), 1, sysdate(), 2, 3, 2);

insert into user_tenant_pf_department_role_mapping
(created_at, is_active, updated_at, pf_department_role_group_id, tenant_id, user_id)
VALUES (sysdate(), 1, sysdate(), 3, 3, 3);

insert into user_tenant_pf_department_role_mapping
(created_at, is_active, updated_at, pf_department_role_group_id, tenant_id, user_id)
VALUES (sysdate(), 1, sysdate(), 4, 3, 4);

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


insert into document( created_at, entity_id, is_active, updated_at, name, created_by, updated_by, tenant_id)
VALUES(sysdate(), '6fbb3ce1-201c-41c3-a6a7-fff51b641a88', 1, sysdate(), 'Death Certificate', 1, 1, 3),
      (sysdate(), 'a62f0304-84fc-4488-8c66-b504bb3121ca', 1, sysdate(), 'Pan Card', 1, 1, 3),
      (sysdate(), '8b14773a-d06e-44af-89aa-e8895895e0c8', 1, sysdate(), 'Cancelled Cheque', 1, 1, 3),
      (sysdate(), '462a9c9c-b8e9-4b50-a6e9-ad37a7c5ab65', 1, sysdate(), 'Bank Passbook', 1, 1, 3),
      (sysdate(), '49363e4c-daca-49ce-817c-18ad74a4e57a', 1, sysdate(), 'Form 20', 1, 1, 3),
      (sysdate(), 'ab941cc6-5aab-4fd8-a187-cf7dcbc2bd99', 1, sysdate(), 'Form 19', 1, 1, 3),
      (sysdate(), '61a925df-9afa-4932-b47c-aa64188516d2', 1, sysdate(), 'Cover Letter', 1, 1, 3),
      (sysdate(), '23384f4b-de7c-4978-87fe-e16b3a54ca57', 1, sysdate(), 'Annexure K', 1, 1, 3),
      (sysdate(), '5f7da96f-c08d-4ecf-a3c0-fa1b8ea66f6a', 1, sysdate(), 'Form 13', 1, 1, 3),
      (sysdate(), '1c3e1cee-2c40-4d1a-9ce0-d3c54771cf8d', 1, sysdate(), 'Form 16', 1, 1, 3),
      (sysdate(), '0f2b51dd-7a2b-43b8-8aa5-ccfe8f6d5eb5', 1, sysdate(), 'Pay Slip', 1, 1, 3),
      (sysdate(), 'afb1849d-2ec7-467b-b54f-5580877744f4', 1, sysdate(), 'Sale Agreemen', 1, 1, 3),
      (sysdate(), '2191095c-a1fe-44ad-ab63-fc63ff02c627', 1, sysdate(), 'Stamp Duty/Registration Receipt', 1, 1, 3),
      (sysdate(), 'a24938ea-bdd8-484a-aec7-bbee144cedda', 1, sysdate(), 'Builders Receipt', 1, 1, 3),
      (sysdate(), '7bf84f45-9778-4a81-ae2d-779740d67a2e', 1, sysdate(), 'NOC from Society', 1, 1, 3),
      (sysdate(), '5fa408ff-2d8e-462b-a5b6-1ca661875213', 1, sysdate(), 'Estimate of Construction', 1, 1, 3),
      (sysdate(), 'aa708204-22f8-4369-8070-ac72603b11a2', 1, sysdate(), 'Financial Institute Certificate', 1, 1, 3),
      (sysdate(), 'd5598106-22bd-4721-a49c-d28de8410c8f', 1, sysdate(), 'Land Purchase Document', 1, 1, 3),
      (sysdate(), '465064d0-9c99-4d51-a8f7-08f38bbb8c6e', 1, sysdate(), '7 X 12 Extract', 1, 1, 3),
      (sysdate(), '10dde3b6-7a4a-45fc-9ab5-48694b748873', 1, sysdate(), 'NA Certificate', 1, 1, 3),
      (sysdate(), 'd5e45e41-519b-47b4-800b-2109357e06a2', 1, sysdate(), 'Proof of House/Building is 5years Old', 1, 1, 3),
      (sysdate(), '62950f13-b35b-4620-bc66-70ad8b0b91bf', 1, sysdate(), 'Estimate of Cost of Repair', 1, 1, 3),
      (sysdate(), 'eb914f8d-b063-438f-9067-268b91e60852', 1, sysdate(), 'Certificate from Doctor', 1, 1, 3),
      (sysdate(), '8f5e57f4-22f3-4b5a-8671-a7923ea5d200', 1, sysdate(), 'Certificate from Employer', 1, 1, 3),
      (sysdate(), '1e5f006e-ee29-42b4-86ac-1c84c732c713', 1, sysdate(), 'Marriage Invitation Card', 1, 1, 3),
      (sysdate(), 'bd9a4f76-a491-4247-81e5-fb02c528834f', 1, sysdate(), 'Hall Booking Receipt', 1, 1, 3),
      (sysdate(), '342a2a1b-73d2-441b-95b4-eae59a9c12ea', 1, sysdate(), 'Certificate from Grampanchayat, Priest, Church or Budha Vihar', 1, 1, 3),
      (sysdate(), '7c9a01ff-f445-4a50-b249-8185dc6cdc33', 1, sysdate(), 'Estimate Marriage Expenses', 1, 1, 3),
      (sysdate(), 'bb331a63-4982-4489-9b2f-3a4b4cb7fe78', 1, sysdate(), 'Payment Receipt', 1, 1, 3),
      (sysdate(), 'a9c0d52c-2976-48ac-a999-b76380c96088', 1, sysdate(), 'Admission Receipt', 1, 1, 3),
      (sysdate(), '4634d36b-30a4-4561-9613-5822972cadf6', 1, sysdate(), 'Admit Card', 1, 1, 3),
      (sysdate(), '3d57d9fb-9fe2-487e-aab6-faa0e8679725', 1, sysdate(), 'Certificate from Institution giving the Cost of Education', 1, 1, 3),
      (sysdate(), '2371cf49-7a8e-4bfd-bc13-6ef4be1544aa', 1, sysdate(), 'Certificate from HR or ER Dept', 1, 1, 3);

insert into settlement_type(created_at, entity_id, is_active, updated_at, code, title, created_by, updated_by, tenant_id)
VALUES(sysdate(), 'a2d9b38f-f330-46cb-be2b-e52f03fbbe5b', 1, sysdate(), '01', 'Death', 1, 1, 3),
      (sysdate(), 'c35f0175-b499-4bc5-bbbc-7e2f115e8e78', 1, sysdate(), '02', 'Retirement VRS', 1, 1, 3),
      (sysdate(), '4e04770d-2154-49a3-8f3e-452ab47bd258', 1, sysdate(), '03', 'Retirement Normal', 1, 1, 3),
      (sysdate(), 'b433c518-d397-4222-9d64-c7adaaebb70d', 1, sysdate(), '04', 'Resignation', 1, 1, 3),
      (sysdate(), '20926900-357f-4ffd-a4df-e114b2db95da', 1, sysdate(), '05', 'Termination', 1, 1, 3),
      (sysdate(), 'e0b56648-c788-4239-87a0-e3a4fa64a8c7', 1, sysdate(), '08', 'Migration', 1, 1, 3),
      (sysdate(), '13e77130-4bd5-4b64-8b1d-f7239466cbe8', 1, sysdate(), '09', 'Dismissal', 1, 1, 3),
      (sysdate(), '95e767cb-4bf3-4832-b125-3ff33866bf2b', 1, sysdate(), '10', 'Closure of Establishment', 1, 1, 3);

INSERT INTO `settlement_documents`(settlement_type_id, document_id) VALUES (1,1),(1,5),(1,3),(1,4);
INSERT INTO `settlement_documents`(settlement_type_id, document_id) VALUES (2,6),(2,2),(2,3),(2,4);
INSERT INTO `settlement_documents`(settlement_type_id, document_id) VALUES (3,6),(3,2),(3,3),(3,4);
INSERT INTO `settlement_documents`(settlement_type_id, document_id) VALUES (4,6),(4,2),(4,3),(4,4);
INSERT INTO `settlement_documents`(settlement_type_id, document_id) VALUES (5,6),(5,2),(5,3),(5,4);
INSERT INTO `settlement_documents`(settlement_type_id, document_id) VALUES (6,6),(6,2),(6,3),(6,4);
INSERT INTO `settlement_documents`(settlement_type_id, document_id) VALUES (7,6),(7,2),(7,3),(7,4);
INSERT INTO `settlement_documents`(settlement_type_id, document_id) VALUES (8,6);

insert into transfer_out_type(created_at, entity_id, is_active, updated_at, code, title, created_by, updated_by, tenant_id)
VALUES (sysdate(), '6ccb15b9-321d-450b-b7c6-56ff51693477', 1, sysdate(), '06', 'RPFC', 1, 1, 3),
       (sysdate(), '9a6351da-5f14-4295-aa9c-83c0f110ae5e', 1, sysdate(), '07', 'TRUST', 1, 1, 3);

INSERT INTO `tranferout_documents`(transferout_type_id, document_id) VALUES (1,9);
INSERT INTO `tranferout_documents`(transferout_type_id, document_id) VALUES (2,9);

insert into bank(created_at, entity_id, is_active, updated_at, name, created_by, updated_by, tenant_id)
VALUES (sysdate(), '5c50f010-8a14-405d-99f6-a7c286f486d3', 1, sysdate(), 'Kotak Mahindra Bank', 1, 1, 3),
       (sysdate(), '4d1434bf-e4c7-4ae8-8f69-3d4215b92953', 1, sysdate(), 'HDFC Bank', 1, 1, 3);

insert into payment_mode(created_at, entity_id, is_active, updated_at, code, mode, created_by, updated_by, tenant_id)
VALUES(sysdate(), '47890a46-0ad9-4208-8858-c8c0132f3145', 1, sysdate(), 'A', 'Cheque', 1, 1, 3),
      (sysdate(), '0e78a024-d7b9-4601-b720-b87805f378ea', 1, sysdate(), 'E', 'NEFT', 1, 1, 3),
      (sysdate(), '675a0f81-18e2-4f11-8180-d5713cad422f', 1, sysdate(), 'L', 'NECS', 1, 1, 3),
      (sysdate(), '0e5bbe46-a2d6-4c90-8be7-d5087c07ab1d', 1, sysdate(), 'I', 'DEMAND DRAFT', 1, 1, 3);

insert into pf_account_holder(created_at, entity_id, is_active, updated_at, name, code, created_by, updated_by, tenant_id)
VALUES (sysdate(), 'e3007ba2-28cd-4471-ab75-266fc8b38f6e', 1, sysdate(), 'RPFC', '06', 1, 1, 3),
       (sysdate(), 'c993658b-f596-45ad-b1e7-70e8fd037497', 1, sysdate(), 'Trust', '07', 1, 1, 3);

insert into transfer_in_status(created_at, entity_id, is_active, updated_at, code, title, created_by, updated_by, tenant_id)
VALUES(sysdate(), '297c1e3c-213c-42da-8b1b-a30e74c4f79f', 1, sysdate(), 'P', 'In Process', 1, 1, 3),
      (sysdate(), '64df6096-77d6-4130-8482-202bd5940717', 1, sysdate(), 'R', 'Completed', 1, 1, 3),
      (sysdate(), 'e0a022a3-5574-4d82-8bb5-5e259bfd61fd', 1, sysdate(), 'C', 'Canceled', 1, 1, 3);

insert into loan_status(created_at, is_active, updated_at, code, title, created_by, updated_by, tenant_id, entity_id)
VALUES (sysdate(), 1, sysdate(), 'P', 'Pending', 1, 1, 3, 'b2fb26ed-9f02-47ff-b90f-f491b39b8540'),
       (sysdate(), 1, sysdate(), 'R', 'Rejected', 1, 1, 3, '7cb5d7ff-86f7-4bf7-8bc6-f9151937ac26'),
       (sysdate(), 1, sysdate(), 'A', 'Approved', 1, 1, 3, '3baf8959-c765-43f0-9c26-e946a63d6e32');

insert into transfer_out_status(created_at, entity_id, is_active, updated_at, title, created_by, updated_by, tenant_id)
VALUES (sysdate(), '2b9b8309-f1e3-4667-a0cb-524fd9195bcf', 1, sysdate(), 'In Process', 1, 1, 3),
       (sysdate(), '0f5f91b3-3337-4dc3-995a-0618727dbc55', 1, sysdate(), 'Completed', 1, 1, 3),
       (sysdate(), '4fc23a3f-facc-4c12-a3a0-9e43e18d1218', 1, sysdate(), 'Canceled', 1, 1, 3);

insert into settlement_status(created_at, entity_id, is_active, updated_at, title, created_by, updated_by, tenant_id)
VALUES (sysdate(), '27ec9ae0-211d-4b9d-b99f-5ea05b029714', 1, sysdate(), 'In Process', 1, 1, 3),
       (sysdate(), '393acbc9-0061-4139-bdaf-846b9d260260', 1, sysdate(), 'Completed', 1, 1, 3),
       (sysdate(), '7ee27f72-5129-4d19-ade6-e861eadb4593', 1, sysdate(), 'Canceled', 1, 1, 3);

insert into loan_type(id, created_at, is_active, updated_at, code, loan_group, maximum_number_of_withdrawals,
                      minimum_membership_tenure_in_months, title, created_by, updated_by, tenant_id, entity_id,
                      from_retirement_date, next_eligibility, pf_base_salary_in_month, member_balance_in_percentage,
                      company_balance_in_percentage, vpf_balance_in_percentage, total_cost_in_percentage,
                      applied_amount_in_percentage)
VALUES (1, sysdate(), 1, sysdate(), '01', 'A', 100, 60, 'Purchase of Residential House/Flat', 1, 1, 3, '06a09079-9529-474a-aca4-03af4a8e2325', 0, 0, 36, 100, 100, 100, 100, 100),
       (2, sysdate(), 1, sysdate(), '13', 'A', 1, 120, 'Repayment of Housing Loan', 1, 1, 3, 'be23e2a7-b766-4db3-aa6b-4c058d6ee7c8', 0, 0, 36, 100, 100, 100, 100, 100),
       (3, sysdate(), 1, sysdate(), '02', 'A', 5, 60, 'Alteration/Improvement/Additions to Residential House/Flat', 1, 1, 3, '0aac2310-e87d-4cd1-87b7-e794c72ef078', 0, 10, 12, 100, 0, 100, 0, 100),
       (4, sysdate(), 1, sysdate(), '11', 'A', 1, 60, 'Construction of Residential House/Flat', 1, 1, 3, 'ca761eb1-6a84-44fd-a1f8-4343e7e0e5a2', 0, 0, 36, 100, 100, 100, 100, 100),
       (5, sysdate(), 1, sysdate(), '12', 'A', 100, 60, 'Purchase of Site/Plot House', 1, 1, 3, '32d99a27-39f4-45be-8d0b-045c4f2dd1ae', 0, 0, 24, 100, 100, 100, 100, 100),
       (6, sysdate(), 1, sysdate(), '03', 'B', 3, 84, 'Marriage', 1, 1, 3, 'd148c329-117b-4bfc-9168-ad40f25cde5d', 0, 0, 0, 50, 0, 50, 0, 100),
       (7, sysdate(), 1, sysdate(), '04', 'C', 5, 0, 'Medical(Hospitalization & Major Operations/Sickness)', 1, 1, 3, '19912a53-7298-4626-b4b8-d7c6bbf5f3ce', 0, 0, 6, 100, 0, 100, 0, 100),
       (8, sysdate(), 1, sysdate(), '08', 'D', 3, 84, 'Education(Post Matriculation) of Children', 1, 1, 3, '6f3b43b6-cb7f-4efd-adda-656c697ff28d', 0, 0, 0, 100, 0, 100, 0, 100),
       (9, sysdate(), 1, sysdate(), '99', 'E', 0, 1, 'Pre-Retirement Withdrawal(Within 1 Year from retirement date)', 1, 1, 3, '0fbc53f2-1fa9-4b08-9885-34d77e4cb99b', 1, 0, 0, 90, 90, 90, 0, 0),
       (10, sysdate(), 1, sysdate(), '98', 'E', 2, 3, 'Outbreak of any epidemic or pandemic Situation', 1, 1, 3, '6305ddcb-3158-4606-93ef-9305892f4479', 0, 0, 3, 75, 75, 75, 0, 100);

insert into loan_documents(loan_type_id, document_id) values (1, 11), (1, 12), (1, 13), (1, 14),
                                  (1, 15), (1, 16), (1, 17);

insert into loan_documents(loan_type_id, document_id) values (2, 11), (2, 12), (2, 13), (2, 14),
                                  (2, 15), (2, 16), (2, 17);

insert into loan_documents(loan_type_id, document_id) values (4, 11), (4, 12), (4, 13), (4, 14),
                                  (4, 15), (4, 16), (4, 17);

insert into loan_documents(loan_type_id, document_id) values (5, 11), (5, 18), (5, 19), (5, 20);

insert into loan_documents(loan_type_id, document_id) values (3, 21), (3, 15), (3, 22);

insert into loan_documents(loan_type_id, document_id) values (7, 11), (7, 23), (7, 24);

insert into loan_documents(loan_type_id, document_id) values (6, 25), (6, 26), (6, 27), (6, 28);

insert into loan_documents(loan_type_id, document_id) values (8, 29), (8, 30), (8, 31), (8, 32);

insert into loan_documents(loan_type_id, document_id) values (9, 33);

insert into loan_amount(created_at, entity_id, is_active, updated_at, applied_amount, own_account_pf_balance, pf_salary,
                        total_cost, total_pf_balance, created_by, updated_by, loan_type_id, tenant_id)
VALUES (sysdate(), '', 1, sysdate(), 100, 0, 36, 100, 100, 1, 1, 1, 3),
       (sysdate(), '', 1, sysdate(), 100, 0, 36, 100, 100, 1, 1, 2, 3),
       (sysdate(), '', 1, sysdate(), 100, 0, 36, 100, 100, 1, 1, 4, 3),
       (sysdate(), '', 1, sysdate(), 100, 0, 24, 100, 100, 1, 1, 5, 3),
       (sysdate(), '', 1, sysdate(), 100, 100, 12, 0, 0, 1, 1, 3, 3),
       (sysdate(), '', 1, sysdate(), 100, 100, 6, 0, 0, 1, 1, 7, 3),
       (sysdate(), '', 1, sysdate(), 100, 50, 0, 0, 0, 1, 1, 6, 3),
       (sysdate(), '', 1, sysdate(), 100, 50, 0, 0, 0, 1, 1, 8, 3),
       (sysdate(), '', 1, sysdate(), 0, 0, 0, 0, 90, 1, 1, 9, 3),
       (sysdate(), '', 1, sysdate(), 100, 0, 3, 0, 75, 1, 1, 10, 3);

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

insert into interest_rate(created_at, entity_id, is_active, updated_at, description, interest_rate,
                          year, tenant_id, created_by, updated_by)
values (sysdate(), '', 1, sysdate(), '', 8.10, 2024, 3, 3, 3);

insert into tds_deduction(created_at, entity_id, is_active, updated_at, minimum_limit, tds_to_deduct, year, tenant_id)
VALUES(sysdate(), '', 1, sysdate(), 5000, 10.0, 2024, 3);

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
                                        <fo:external-graphic src="http://3.110.35.227:8080/images/CoreIntegra.png"
                                                             content-height="110pt" content-width="110pt"/>
                                    </fo:block>
                                </fo:table-cell>
                                <fo:table-cell>
                                    <fo:block margin-top="15pt">
                                        <fo:block font-weight="600" font-size="12pt">COREINTEGRA GLOBAL SERVICES PVT LIMITED  .  STAFF  PROVIDENT  FUND</fo:block>
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


                    <fo:block margin-top="30pt">COREINTEGRA GLOBAL SERVICES PVT LIMITED</fo:block>
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
                        Received from COREINTEGRA GLOBAL SERVICES PVT LIMITED  .  STAFF  PROVIDENT  FUND
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


</xsl:stylesheet>
', 'LOAN_RECEIPT', 3);

insert into pdf_document_design(created_at, entity_id, is_active, updated_at, document, document_type,
                                tenant_id)
    VALUE (sysdate(), '', 1, sysdate(), '<?xml version="1.0" encoding="UTF-8"?>
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
                                            <fo:external-graphic src="url(''http://3.110.35.227:8080/images/CoreIntegra.png'')"
                                                                 content-height="75pt" content-width="75pt"/>
                                        </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell>
                                        <fo:block  text-align="center" margin-top="10pt">
                                            <fo:block>
                                                COREINTEGRA GLOBAL SERVICES PVT LIMITED  .  STAFF  PROVIDENT  FUND
                                            </fo:block>
                                            <fo:block>
                                                VINMAR HOUSE, PLOT NO. A/41, ROAD NO. 2, MIDC,
                                            </fo:block>
                                            <fo:block>
                                                ANDHERI (EAST)  MUMBAI - 400093
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
                                        <fo:block text-align="center">COREINTEGRA GLOBAL SERVICES PVT LIMITED</fo:block>
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
', 'ANNUXURE_K', 3);

insert into pdf_document_design(created_at, entity_id, is_active, updated_at, document, document_type,
                                tenant_id)
    VALUE (sysdate(), '', 1, sysdate(), '<?xml version="1.0" encoding="UTF-8"?>
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
                                            <fo:external-graphic src="http://3.110.35.227:8080/images/CoreIntegra.png" content-height="100pt" content-width="100pt"/>
                                        </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell margin="0" padding="0">
                                        <fo:block margin="0" padding="0" text-align="center" line-height="1.2">
                                            <fo:block font-weight="600">COREINTEGRA GLOBAL SERVICES PVT LIMITED  .  STAFF  PROVIDENT  FUND</fo:block>
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
                                                <fo:block text-align="center">For COREINTEGRA GLOBAL SERVICES PVT LIMITED</fo:block>
                                                <fo:block text-align="center">Staff Provident Fund</fo:block>
                                                <fo:block margin-top="5pt" text-align="center">

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
                                            <fo:external-graphic src="http://3.110.35.227:8080/images/CoreIntegra.png" content-height="100pt" content-width="100pt"/>
                                        </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell margin="0" padding="0">
                                        <fo:block margin="0" padding="0" text-align="center" line-height="1.2">
                                            <fo:block font-weight="600">COREINTEGRA GLOBAL SERVICES PVT LIMITED  .  STAFF  PROVIDENT  FUND</fo:block>
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
', 'ANNUAL_STATEMENT', 3);

insert into pdf_document_design(created_at, entity_id, is_active, updated_at, document, document_type,
                                tenant_id)
    VALUE (sysdate(), '', 1, sysdate(), '<?xml version="1.0" encoding="UTF-8"?>
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
                                            <fo:external-graphic src="http://3.110.35.227:8080/images/CoreIntegra.png" content-height="100pt" content-width="100pt"/>
                                        </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell margin="0" padding="0">
                                        <fo:block margin="0" padding="0" text-align="center" line-height="1.2">
                                            <fo:block font-weight="600">COREINTEGRA GLOBAL SERVICES PVT LIMITED  .  STAFF  PROVIDENT  FUND</fo:block>
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
', 'LOAN_HISTORY', 3);

insert into pdf_document_design(created_at, entity_id, is_active, updated_at, document, document_type,
                                tenant_id)
    VALUE (sysdate(), '', 1, sysdate(), '<?xml version="1.0" encoding="UTF-8"?>
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
                                            <fo:external-graphic src="http://3.110.35.227:8080/images/CoreIntegra.png" content-height="100pt" content-width="100pt"/>
                                        </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell margin="0" padding="0">
                                        <fo:block margin="0" padding="0" text-align="center" line-height="1.2">
                                            <fo:block font-weight="600">COREINTEGRA GLOBAL SERVICES PVT LIMITED  .  STAFF  PROVIDENT  FUND</fo:block>
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
                                                <fo:block text-align="center">For COREINTEGRA GLOBAL SERVICES PVT LIMITED</fo:block>
                                                <fo:block text-align="center">Staff Provident Fund</fo:block>
                                                 <fo:block text-align="center" margin-top="35pt">Trustee</fo:block>
                                                      <!--  <fo:block margin-top="05pt" text-align="center">
                                                <fo:external-graphic src=""
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
                                            <fo:external-graphic src="http://3.110.35.227:8080/images/CoreIntegra.png" content-height="100pt" content-width="100pt"/>
                                        </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell margin="0" padding="0">
                                        <fo:block margin="0" padding="0" text-align="center" line-height="1.2">
                                            <fo:block font-weight="600">COREINTEGRA GLOBAL SERVICES PVT LIMITED  .  STAFF  PROVIDENT  FUND</fo:block>
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
', 'LOAN_WORKSHEET', 3);

insert into pdf_document_design(created_at, entity_id, is_active, updated_at, document, document_type,
                                tenant_id)
    VALUE (sysdate(), '', 1, sysdate(), '<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.1" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:fo="http://www.w3.org/1999/XSL/Format" exclude-result-prefixes="fo">

    <xsl:template match="monthlyStatement">
        <fo:root xmlns:fo="http://www.w3.org/1999/XSL/Format">

            <fo:layout-master-set>
                <fo:simple-page-master master-name="simpleA4"
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

                        <fo:table line-height="1">
                            <fo:table-column column-width="10%"/>
                            <fo:table-column column-width="90%"/>

                            <fo:table-body>
                                <fo:table-row>
                                    <fo:table-cell margin="0" padding="0">
                                        <fo:block margin="0" padding="0">
                                            <fo:external-graphic src="http://3.110.35.227:8080/images/CoreIntegra.png" content-height="75pt" content-width="75pt"/>
                                        </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell margin="0" padding="0">
                                        <fo:block margin="0" padding="0" text-align="center" line-height="1.2">
                                            <fo:block font-weight="600">COREINTEGRA GLOBAL SERVICES PVT LIMITED  .  STAFF  PROVIDENT  FUND</fo:block>
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
', 'MONTHLY_STATEMENT', 3);

insert into pdf_document_design(created_at, entity_id, is_active, updated_at, document, document_type,
                                tenant_id)
    VALUE (sysdate(), '', 1, sysdate(), '<?xml version="1.0" encoding="UTF-8"?>
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
                                            <fo:external-graphic src="http://3.110.35.227:8080/images/CoreIntegra.png" content-height="100pt" content-width="100pt"/>
                                        </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell margin="0" padding="0">
                                        <fo:block margin="0" padding="0" text-align="center" line-height="1.2">
                                            <fo:block font-weight="600">COREINTEGRA GLOBAL SERVICES PVT LIMITED  .  STAFF  PROVIDENT  FUND</fo:block>
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
', 'SETTLEMENT_ANNEXURE', 3);

insert into pdf_document_design(created_at, entity_id, is_active, updated_at, document, document_type,
                                tenant_id)
    VALUE (sysdate(), '', 1, sysdate(), '<?xml version="1.0" encoding="UTF-8"?>
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
                        <fo:external-graphic src="http://3.110.35.227:8080/images/CoreIntegra.png" content-height="75pt" content-width="75pt"/>
                    </fo:block>

                    <fo:block text-align="center" line-height="1.2">
                        <fo:block font-weight="600">COREINTEGRA GLOBAL SERVICES PVT LIMITED  .  STAFF  PROVIDENT  FUND</fo:block>
                        <fo:block>VINMAR HOUSE, PLOT NO. A/41, ROAD NO. 2, MIDC,</fo:block>
                        <fo:block>ANDHERI (EAST)  MUMBAI - 400093</fo:block>
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
                    <fo:block>For COREINTEGRA GLOBAL SERVICES PVT LIMITED,</fo:block>
                    <fo:block>STAFF PROVIDENT FUND</fo:block>

                    <fo:block margin-top="40pt">Authorized Signatory</fo:block>

                </fo:flow>
            </fo:page-sequence>

        </fo:root>
    </xsl:template>

</xsl:stylesheet>
', 'SETTLEMENT_DISPATCH_LETTER', 3);

insert into pdf_document_design(created_at, entity_id, is_active, updated_at, document, document_type,
                                tenant_id)
    VALUE (sysdate(), '', 1, sysdate(), '<?xml version="1.0" encoding="UTF-8"?>
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
                                            <fo:external-graphic src="http://3.110.35.227:8080/images/CoreIntegra.png" content-height="75pt" content-width="75pt"/>
                                        </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell margin="0" padding="0">
                                        <fo:block margin="0" padding="0" text-align="center" line-height="1.2">
                                            <fo:block font-weight="600">COREINTEGRA GLOBAL SERVICES PVT LIMITED  .  STAFF  PROVIDENT  FUND</fo:block>
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
                            <fo:block text-align="center">For COREINTEGRA GLOBAL SERVICES PVT LIMITED</fo:block>
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
                                            <fo:external-graphic src="http://3.110.35.227:8080/images/CoreIntegra.png" content-height="100pt" content-width="100pt"/>
                                        </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell margin="0" padding="0">
                                        <fo:block margin="0" padding="0" text-align="center" line-height="1.2">
                                            <fo:block font-weight="600">COREINTEGRA GLOBAL SERVICES PVT LIMITED  .  STAFF  PROVIDENT  FUND</fo:block>
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
', 'SETTLEMENT_WORKSHEET', 3);

insert into pdf_document_design(created_at, entity_id, is_active, updated_at, document, document_type,
                                tenant_id)
    VALUE (sysdate(), '', 1, sysdate(), '<?xml version="1.0" encoding="UTF-8"?>
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
                                        <fo:external-graphic src="http://3.110.35.227:8080/images/CoreIntegra.png"
                                                             content-height="110pt" content-width="110pt"/>
                                    </fo:block>
                                </fo:table-cell>
                                <fo:table-cell>
                                    <fo:block>
                                        <fo:block font-weight="600" font-size="14pt">COREINTEGRA GLOBAL SERVICES PVT LIMITED  .  STAFF  PROVIDENT  FUND</fo:block>
                                        <fo:block>VINMAR HOUSE, PLOT NO. A/41, ROAD NO. 2, MIDC,</fo:block>
                                        <fo:block>ANDHERI (EAST)  MUMBAI - 400093</fo:block>
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
                        is now employed with COREINTEGRA GLOBAL SERVICES PVT LIMITED for the transfer of his Provident Fund accumulations to
                        us. We would like to inform you that COREINTEGRA GLOBAL SERVICES PVT LIMITED is an exempted establishment under Employees''
                        Provident Fund Act, 1952 and having its exempted provident fund.
                    </fo:block>

                    <fo:block margin-top="10pt" line-height="1.2" text-align="justify">
                        <fo:inline padding-left="50pt">In view of kindly</fo:inline> arrange to send us your at par cheque or electronic fund transfer (Bank details are given below)
                        in respect of Provident Fund accumulations of the above member in favors of
                        "COREINTEGRA GLOBAL SERVICES PVT LIMITED", along with the Annexure "K", at the earliest.
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
                                <fo:table-cell><fo:block> : COREINTEGRA GLOBAL SERVICES PVT LIMITED</fo:block></fo:table-cell>
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
                    <fo:block>For COREINTEGRA GLOBAL SERVICES PVT LIMITED</fo:block>

                    <fo:block margin-top="35pt">Authorized Signatory</fo:block>
                    <fo:block margin-top="7pt">Encl: Form - 13 along with Coreintegra Global Services PF Trust Bank Cancelled Cheque Details</fo:block>
                </fo:flow>
            </fo:page-sequence>

        </fo:root>
    </xsl:template>

</xsl:stylesheet>
', 'TRANSFER_IN_REQUEST_LETTER', 3);

insert into pdf_document_design(created_at, entity_id, is_active, updated_at, document, document_type,
                                tenant_id)
    VALUE (sysdate(), '', 1, sysdate(), '<?xml version="1.0" encoding="UTF-8"?>
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
                        <fo:external-graphic src="http://3.110.35.227:8080/images/CoreIntegra.png" content-height="75pt" content-width="75pt"/>
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
                                            <fo:block>COREINTEGRA GLOBAL SERVICES PVT LIMITED</fo:block>
                                            <fo:block>Staff Provident Fund</fo:block>
                                            <fo:block>VINMAR HOUSE, PLOT NO. A/41, ROAD NO. 2, MIDC,</fo:block>
                                            <fo:block>ANDHERI (EAST)</fo:block>
                                            <fo:block>MUMBAI - 400093</fo:block>
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
                        <fo:block>FOR COREINTEGRA GLOBAL SERVICES PVT LIMITED</fo:block>
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
', 'TRANSFER_OUT_DISPATCH_LETTER', 3);

insert into pdf_document_design(created_at, entity_id, is_active, updated_at, document, document_type,
                                tenant_id)
    VALUE (sysdate(), '', 1, sysdate(), '<?xml version="1.0" encoding="UTF-8"?>
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
                                            <fo:external-graphic src="http://3.110.35.227:8080/images/CoreIntegra.png" content-height="75pt" content-width="75pt"/>
                                        </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell margin="0" padding="0">
                                        <fo:block margin="0" padding="0" text-align="center" line-height="1.2">
                                            <fo:block font-weight="600">COREINTEGRA GLOBAL SERVICES PVT LIMITED  .  STAFF  PROVIDENT  FUND</fo:block>
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
                            <fo:block text-align="center">For COREINTEGRA GLOBAL SERVICES PVT LIMITED</fo:block>
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
                                            <fo:external-graphic src="http://3.110.35.227:8080/images/CoreIntegra.png" content-height="100pt" content-width="100pt"/>
                                        </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell margin="0" padding="0">
                                        <fo:block margin="0" padding="0" text-align="center" line-height="1.2">
                                            <fo:block font-weight="600">COREINTEGRA GLOBAL SERVICES PVT LIMITED  .  STAFF  PROVIDENT  FUND</fo:block>
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
', 'TRANSFER_OUT_WORK_SHEET', 3);

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
                                        {{accountNumber}} with on {{paymentDate}} for amount of {{netCredit}} Rupees on behalf of COREINTEGRA GLOBAL SERVICES PVT LIMITED  .  STAFF  PROVIDENT  FUND towards your PF Loan/Withdrawal application.</p>
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
                                    <p style="color: #282828; font-weight: 400; font-size: 15px; line-height: 21px; font-family: ''Helvetica neue'', Helvetica, arial, sans-serif; " class="">Coreintegra PF Trust</p>
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

</html>', 'LOAN_COMPLETION', null, null, 3),
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
                                        on behalf of COREINTEGRA GLOBAL SERVICES PVT LIMITED  .  STAFF  PROVIDENT  FUND Fund towards full & Final Settlement of your Provident Fund A/c as per statement in the attachment.</p>
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
                                    <p style="color: #282828; font-weight: 400; font-size: 15px; line-height: 21px; font-family: ''Helvetica neue'', Helvetica, arial, sans-serif; " class="">Coreintegra PF Trust</p>
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

</html>', 'SETTLEMENT_COMPLETION', null, null, 3),
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

                                    <p style="color: #282828; font-weight: 400; font-size: 15px; line-height: 21px; font-family: ''Helvetica neue'', Helvetica, arial, sans-serif; " class="">We have credited <b>Rs. {{amount}}</b> to your Coreintegra. Staff PF Account,
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
                                    <p style="color: #282828; font-weight: 400; font-size: 15px; line-height: 21px; font-family: ''Helvetica neue'', Helvetica, arial, sans-serif; " class="">Coreintegra PF Trust</p>
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

</html>', 'TRANSFER_IN_COMPLETION', null, null, 3),
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
                                    <p style="color: #282828; font-weight: 400; font-size: 15px; line-height: 21px; font-family: ''Helvetica neue'', Helvetica, arial, sans-serif; " class="">Coreintegra PF Trust</p>
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

</html>', 'TRANSFER_OUT_COMPLETION', null, null, 3),
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

</html>', 'USER_ACCOUNT_INVITATION_EMAIL', null, null, 3),
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

</html>', 'USER_ACCOUNT_DETAILS', null, null, 3);

insert into unit_code(created_at, entity_id, is_active, updated_at, unit_code, tenant_id)
VALUES (sysdate(), '', 1, sysdate(), '899000', 3),
       (sysdate(), '', 1, sysdate(), '899001', 3),
       (sysdate(), '', 1, sysdate(), '899005', 3),
       (sysdate(), '', 1, sysdate(), '899010', 3),
       (sysdate(), '', 1, sysdate(), '899011', 3),
       (sysdate(), '', 1, sysdate(), '899012', 3);

insert into tds_deduction(created_at, entity_id, is_active, updated_at, minimum_limit, tds_to_deduct, year,
                          tenant_id)
VALUES (sysdate(), '', 1, sysdate(), 5000, 10, 2022, 3)