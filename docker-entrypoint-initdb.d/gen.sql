--ALTER DATABASE taskmngmtdb OWNER TO dev;
--\connect taskmngmtdb;

CREATE TABLE status (
	status_id	BIGSERIAL PRIMARY KEY,
	name		VARCHAR(20) NOT NULL UNIQUE
);

CREATE TABLE priority (
	priority_id	BIGSERIAL    PRIMARY KEY,
	name		VARCHAR(100) NOT NULL UNIQUE,
	plevel		INTEGER
);

CREATE TABLE task (
	task_id 	 	BIGSERIAL PRIMARY KEY,
	title		 	VARCHAR(255) NOT NULL,
	description	 	TEXT,
	due_date	 	TIMESTAMP, -- запланированная дата
	completion_date TIMESTAMP, -- дата выполнения
	created_date 	TIMESTAMP NOT NULL DEFAULT NOW(),
	updated_date 	TIMESTAMP,
	deleted_date 	TIMESTAMP,
	status_id		BIGSERIAL REFERENCES status(status_id),
	priority_id		BIGSERIAL REFERENCES priority(priority_id)
);
-- связь на статус неидентифицирующая
ALTER TABLE task ALTER COLUMN status_id DROP NOT NULL;

CREATE TABLE attached_file (
	at_file_id	BIGSERIAL PRIMARY KEY,
	file_uri	VARCHAR(255) NOT NULL,
	task_id		BIGSERIAL REFERENCES task(task_id)
);

CREATE TABLE tag (
	tag_id	BIGSERIAL PRIMARY KEY,
	name	VARCHAR(20) NOT NULL UNIQUE
);

-- у записи может быть много тегов
CREATE TABLE task_tag (
	task_id BIGSERIAL REFERENCES task(task_id) ON DELETE CASCADE,
	tag_id	BIGSERIAL REFERENCES tag(tag_id) ON DELETE CASCADE,
	PRIMARY KEY(task_id, tag_id)
);

CREATE TABLE user_account (
	user_account_id BIGSERIAL PRIMARY KEY,
	username		VARCHAR(20) NOT NULL,
	name			VARCHAR(255),
	surname			VARCHAR(255),
	patronymic		VARCHAR(255), 
	email			VARCHAR(100),
	password 		VARCHAR(255) NOT NULL,
	UNIQUE(username, email)
);

CREATE TABLE role (
	role_id	BIGSERIAL    PRIMARY KEY,
	name	VARCHAR(255) NOT NULL UNIQUE
);

CREATE TABLE user_role (
	role_id			BIGSERIAL REFERENCES role(role_id) ON DELETE CASCADE,
	user_account_id BIGSERIAL REFERENCES user_account(user_account_id) ON DELETE CASCADE,
	PRIMARY KEY(role_id, user_account_id)
);

-- задача по желанию может принадлежать сразу нескольким пользователям
CREATE TABLE project_tasks (
	task_id 		BIGSERIAL REFERENCES task(task_id) ON DELETE CASCADE,
	user_account_id BIGSERIAL REFERENCES user_account(user_account_id) ON DELETE CASCADE,
	PRIMARY KEY(task_id, user_account_id)
);


-- запланированная дата не позднее текущей
CREATE OR REPLACE FUNCTION due_date_check()
	RETURNS TRIGGER
	AS $$
	DECLARE
		curtime TIMESTAMP;
	BEGIN
		curtime := CURRENT_TIMESTAMP;
		IF date_trunc('minute', NEW.due_date) < date_trunc('minute', curtime) THEN
			RAISE EXCEPTION 'The task.due_date=% must be greater than or equal to the current_date=%', NEW.due_date, curtime;
		END IF;
		
		RETURN NEW;
	END;
	$$ LANGUAGE PLPGSQL;

CREATE OR REPLACE TRIGGER due_date_create 
	BEFORE INSERT ON task
	FOR EACH ROW
	EXECUTE FUNCTION due_date_check();
	
CREATE OR REPLACE TRIGGER due_date_update 
	BEFORE UPDATE ON task
	FOR EACH ROW
	EXECUTE FUNCTION due_date_check();

-- удаление задач вместе с тегом
--CREATE OR REPLACE FUNCTION delete_tasks_width_tag()
	--RETURNS TRIGGER
	--AS $$
	--DECLARE
	--  tid INTEGER;
	--BEGIN
		--FOR tid IN (SELECT task.task_id FROM task 
		--		   INNER JOIN task_tag 
		--		   ON task.task_id = task_tag.task_id AND tag_id = OLD.tag_id)
		--LOOP
		--	DELETE FROM task WHERE task_id = tid;
		--END LOOP;
		
		--DELETE FROM task
		--WHERE task_id IN (
		--	SELECT task_id
		--	FROM task_tag
		--	WHERE tag_id = OLD.tag_id
		--);
		
	--	RETURN NULL;
	--END;
	--$$ LANGUAGE PLPGSQL;
	
--CREATE OR REPLACE TRIGGER tr_delete_tasks_width_tag
--	BEFORE DELETE ON tag
--	FOR EACH ROW
--	EXECUTE FUNCTION delete_tasks_width_tag();
	

-- тестовые начальные данные
INSERT INTO status(name)
VALUES ('в работе'),
	  ('выполнена'),
	  ('отменена');
	  
INSERT INTO priority(name, plevel)
VALUES ('обычная', 40),
	   ('важная', 60),
	   ('срочная', 90),
	   ('по возможности', 0);

INSERT INTO role(name) 
VALUES ('USER'),
	  ('ADMIN');
	  
INSERT INTO user_account(name, username, email, password)
VALUES ('Вадим', 'vadimka', 'vadim@vadim.com', 'vadim123'),
	   ('Семён', 'semen', 'semen@semen.com', 'semen123'),
	   ('Евгений', 'evgen', 'evgen@evgen.com', 'evgen123');

INSERT INTO user_role(role_id, user_account_id)
VALUES ((SELECT role_id FROM role WHERE name='USER'), 
		(SELECT user_account_id FROM user_account WHERE username='vadimka')),
		((SELECT role_id FROM role WHERE name='USER'), 
		(SELECT user_account_id FROM user_account WHERE username='semen')),
		((SELECT role_id FROM role WHERE name='USER'), 
		(SELECT user_account_id FROM user_account WHERE username='evgen'));
		
INSERT INTO tag(name)
VALUES ('поездка'),
	   ('тестирование'),
	   ('договоры'),
	   ('отчётность'),
	   ('аренда'),
	   ('домашниеДела'),
	   ('поискРаботы'),
	   ('программирование'),
	   ('поставщики');
	   
INSERT INTO task(title, description, due_date)
VALUES ('Тестовое задание', 
		'Нужно написать тестовое задание в Биллинговый центр', 
		TO_TIMESTAMP('10-09-2024 12:00:00', 'DD-MM-YYYY HH24:MI:SS')),
		('Сделать лабу', 
		'ЛР №1', 
		TO_TIMESTAMP('10-09-2024 12:00:00', 'DD-MM-YYYY HH24:MI:SS')),
		('Написать отчёт', 
		'Закончить отчёт', 
		TO_TIMESTAMP('17-09-2024 18:00:00', 'DD-MM-YYYY HH24:MI:SS'));
		
INSERT INTO task_tag(task_id, tag_id)
VALUES ((SELECT task_id FROM task WHERE title='Тестовое задание'), 
		(SELECT tag_id FROM tag WHERE name='поискРаботы')),
		((SELECT task_id FROM task WHERE title='Тестовое задание'), 
		(SELECT tag_id FROM tag WHERE name='программирование')),
		((SELECT task_id FROM task WHERE title='Сделать лабу'), 
		(SELECT tag_id FROM tag WHERE name='программирование')),
		((SELECT task_id FROM task WHERE title='Написать отчёт'), 
		(SELECT tag_id FROM tag WHERE name='отчётность')),
		((SELECT task_id FROM task WHERE title='Написать отчёт'), 
		(SELECT tag_id FROM tag WHERE name='договоры'));

INSERT INTO project_tasks(task_id, user_account_id)
VALUES ((SELECT task_id FROM task WHERE title='Тестовое задание'),
		(SELECT user_account_id FROM user_account WHERE username='vadimka')),
		((SELECT task_id FROM task WHERE title='Написать отчёт'),
		(SELECT user_account_id FROM user_account WHERE username='evgen')),
		((SELECT task_id FROM task WHERE title='Написать отчёт'),
		(SELECT user_account_id FROM user_account WHERE username='semen'));