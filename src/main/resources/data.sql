INSERT INTO articles (name, price_in_cents, attributes) values
	('Article 0', 9999,
		'{
			"attr1": "good",
			"attr2": "fair",
			"attr3": "excellent"
		}'
	),
	('Article 1', 19999,
		'{
			"quality": "new",
			"status": "ready"
		}'
	),
	('Article 2', 29999,
		'{
			"condition": "bad",
			"attachments":
				[
					1,
					2,
					3
				],
			"grade": "A"
		}'
	)
;

INSERT INTO customers (email, firstname, lastname) values
	('customer0@mail', 'Firstname', 'Lastname'),			
	('customer1@mail', 'Firstname', 'Lastname'),			
	('customer2@mail', 'Firstname', 'Lastname')
;
