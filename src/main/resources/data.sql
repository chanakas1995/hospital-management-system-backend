INSERT INTO `branches` (`uuid`, `branch_name`, `address`,`telephone`,`email`) VALUES
  ('3b0f990c-e9ee-46b8-ad60-afa199ba704a', 'Colombo Branch', 'Main Street, Colombo 7','0111111111','colombo@abchospitals.com'),
  ('3f9f3473-d23d-46ab-8265-077a2ef53655', 'Matale Branch', 'Main Street, Matale','0661111111','matale@abchospitals.com'),
  ('0235ea0d-5b04-463c-9538-8dcada0234c9', 'Kandy Branch', 'Main Street, Kandy','0811111111','kandy@abchospitals.com'),
  ('504c4955-7f6a-410c-ac81-48e8f655006b', 'Hambantota Branch', 'Main Street, Hambantota','0471111111','hambantota@abchospitals.com'),
  ('5a6acb8a-be58-499a-836f-c207031a5826', 'Kurunegala Branch', 'Main Street, Kurunegala','0371111111','kurunegala@abchospitals.com');

INSERT INTO `wards` (`uuid`, `ward_name`, `doctor_in_charge`,`branch_id`,`price_per_day`) VALUES
  ('13197f0b-8e62-4d95-b258-632d95011ba9', 'Male Ward', 'Dr.Naween Sanjaya','1','500.00'),
  ('1ad09dbc-4a59-47a5-aacc-9508b3568af6', 'Accident Ward', 'Dr.Sugath Jayamanna','1','450.00');

INSERT INTO `patients` (`uuid`, `name`, `nic`,`email`,`mobile`,`gender`,`blood_group`,`date_of_birth`,`address`,`contact_person_name`,`contact_person_mobile`,`contact_person_email`) VALUES
  ('268b2fb5-60f2-429a-b951-e87295749131', 'Nimal Perera', '711523658V', 'nimal@example.com','0711111111','Male','A+','1971-07-28','Main Street, Matale','Sunil Nishantha','0712222222','sunil@example.com');

INSERT INTO `admissions` (`uuid`, `admission_date`, `discharge_date`,`notes`,`branch_id`,`patient_id`,`ward_id`) VALUES
  ('624f3bf6-8650-49ae-aadb-516bb04fb2a9', '2021-10-20', null, 'Back pain and headache','1','1','1');