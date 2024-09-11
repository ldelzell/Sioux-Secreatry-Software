CREATE TABLE appointment (
    id INT NOT NULL AUTO_INCREMENT,
    emp_id INT NOT NULL,
    date DATE NOT NULL,
    time TIME NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (emp_id) REFERENCES employee(id)
);